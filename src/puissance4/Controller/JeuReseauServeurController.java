/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puissance4.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import puissance4.Model.Case;
import puissance4.Model.Joueur;
import puissance4.Model.Partie;

/**
 * FXML Controller class
 */
public class JeuReseauServeurController implements Initializable {

    @FXML
    private GridPane gridPane;

    @FXML
    private Label joueurTour;

    //Selection
    private Scene scene;

    private String pseudoJ1;
    private Color couleurJ1;

    private String pseudoJ2;
    private Color couleurJ2;

    //Model
    private Joueur j1;
    private Joueur j2;
    private Partie p;

    //Com
    private BufferedReader in;
    private PrintWriter pred;
    Socket socketduserveur;
    ServerSocket socketserver;

    private Service<Void> calculateService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void set(Scene scene, String pseudo1, String pseudo2, Color couleur1, Color couleur2, BufferedReader in, PrintWriter pred, Socket socketduserveur, ServerSocket socketserver) {
        this.scene = scene;

        this.pseudoJ1 = pseudo1;
        this.couleurJ1 = couleur1;

        this.pseudoJ2 = pseudo2;
        this.couleurJ2 = couleur2;

        this.socketduserveur = socketduserveur;
        this.socketserver = socketserver;

        this.j1 = new Joueur(0, pseudoJ1, couleurJ1);
        this.j2 = new Joueur(1, pseudoJ2, couleurJ2);
        this.p = new Partie(j1, j2);
        joueurTour.textProperty().bind(p.getjTour());

        this.in = in;
        this.pred = pred;

        calculateService = new Service<Void>() {

            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {

                    @Override
                    protected Void call() throws Exception {

                        if (p.getjCourant().equals(j1)) {
                            gridPane.setDisable(false);
                        } else {
                            gridPane.setDisable(true);
                            communicationJoueur();
                        }
                        return null;
                    }
                };
            }
        };

        calculateService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                if (p.getjCourant().equals(j1)) {
                    gridPane.setDisable(false);
                } else {
                    gridPane.setDisable(true);
                    communicationJoueur();

                }
            }
        });

        calculateService.start();
    }

    @FXML

    public void jouerTour(MouseEvent event) {
        FlowPane source = (FlowPane) event.getSource();
        int x;
        int y;

        if (GridPane.getRowIndex(source) != null) {
            x = GridPane.getRowIndex(source);
        } else {
            x = 0;
        }

        if (GridPane.getColumnIndex(source) != null) {
            y = GridPane.getColumnIndex(source);
        } else {
            y = 0;
        }

        Case firstCase = p.getPlateau().getFirstCase(y);

        if (firstCase != null) {
            x = firstCase.getX();
            y = firstCase.getY();
            FlowPane casePane = (FlowPane) gridPane.getChildren().get((x * 6) + (y + x));
            Joueur tmp = p.getjCourant();
            String couleur = String.format("#%02X%02X%02X",
                    (int) (tmp.getCouleur().getRed() * 255),
                    (int) (tmp.getCouleur().getGreen() * 255),
                    (int) (tmp.getCouleur().getBlue() * 255));

            Button bouton = (Button) casePane.getChildren().get(0);
            bouton.setVisible(true);
            bouton.setStyle("-fx-background-color: " + couleur + "; -fx-background-radius : 700;");

            pred.println(x + "-" + y);

            p.jouerTour(x, y);
            if (p.gagner(x, y)) {
                ecranVictoire(p.getjCourant());
            }
            if (p.estPlein()) {
                ecranEgalite();
            }
            p.changementTour();

            System.out.println("Click serveur");
            calculateService.restart();
        }

    }

    public void communicationJoueur() {

        try {
            String repJ2 = in.readLine();
            System.out.println("Rep " + repJ2);

            String[] infoJ2 = repJ2.split("-");
            FlowPane source;
            int x = Integer.valueOf(infoJ2[0]);
            int y = Integer.valueOf(infoJ2[1]);

            Case firstCase = p.getPlateau().getFirstCase(y);

            if (firstCase != null) {
                int x2 = firstCase.getX();
                int y2 = firstCase.getY();
                FlowPane casePane = (FlowPane) gridPane.getChildren().get((x * 6) + (y2 + x2));
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Joueur tmp = p.getjCourant();
                        String couleur = String.format("#%02X%02X%02X",
                                (int) (tmp.getCouleur().getRed() * 255),
                                (int) (tmp.getCouleur().getGreen() * 255),
                                (int) (tmp.getCouleur().getBlue() * 255));

                        Button bouton = (Button) casePane.getChildren().get(0);
                        bouton.setVisible(true);
                        bouton.setStyle("-fx-background-color: " + couleur + "; -fx-background-radius : 700;");

                        p.jouerTour(x2, y2);
                        if (p.gagner(x2, y2)) {
                            ecranVictoire(p.getjCourant());
                        }
                        if (p.estPlein()) {
                            ecranEgalite();
                        }
                        p.changementTour();

                        calculateService.restart();
                    }
                });

            }
        } catch (IOException ex) {
            Logger.getLogger(JeuReseauClientController.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public void ecranVictoire(Joueur j) {
        try {
            calculateService.cancel();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/puissance4/View/EcranVictoireServeur.fxml"));

            scene.setRoot(loader.load());

            EcranVictoireServeurController evsc = loader.getController();
            evsc.set(scene, j, j1, j2, socketduserveur, socketserver);
        } catch (IOException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ecranEgalite() {
        try {
            calculateService.cancel();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/puissance4/View/EcranEgaliteServeur.fxml"));

            scene.setRoot(loader.load());

            EcranEgaliteServeurController eesc = loader.getController();
            eesc.set(scene, j1, j2, socketduserveur, socketserver);
        } catch (IOException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
