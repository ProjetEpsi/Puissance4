/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puissance4.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 *
 */
public class JeuLocalController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void set(Scene scene, String pseudo1, String pseudo2, Color couleur1, Color couleur2) {
        this.scene = scene;

        this.pseudoJ1 = pseudo1;
        this.couleurJ1 = couleur1;

        this.pseudoJ2 = pseudo2;
        this.couleurJ2 = couleur2;

        this.j1 = new Joueur(0, pseudoJ1, couleurJ1);
        this.j2 = new Joueur(1, pseudoJ2, couleurJ2);
        this.p = new Partie(j1, j2);
        joueurTour.textProperty().bind(p.getjTour());
    }

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
            source = (FlowPane) gridPane.getChildren().get((x * 6) + (y + x));
            Joueur tmp = p.getjCourant();
            String couleur = String.format("#%02X%02X%02X",
                    (int) (tmp.getCouleur().getRed() * 255),
                    (int) (tmp.getCouleur().getGreen() * 255),
                    (int) (tmp.getCouleur().getBlue() * 255));

            Button bouton = (Button) source.getChildren().get(0);
            bouton.setVisible(true);
            bouton.setStyle("-fx-background-color: " + couleur + "; -fx-background-radius : 700;");


            p.jouerTour(x, y);
            if (p.gagner(x, y)) {
                ecranVictoire(p.getjCourant());
            }
            if (p.estPlein()) {
                ecranEgalite();
            }
            p.changementTour();
        }

    }

    public void ecranVictoire(Joueur j) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/puissance4/View/EcranVictoire.fxml"));

            scene.setRoot(loader.load());

            EcranVictoireController evc = loader.getController();
            evc.set(scene, j, j1, j2);
        } catch (IOException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ecranEgalite() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/puissance4/View/EcranEgalite.fxml"));

            scene.setRoot(loader.load());

            EcranEgaliteController evc = loader.getController();
            evc.set(scene, j1, j2);
        } catch (IOException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
