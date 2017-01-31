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
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import puissance4.Model.Joueur;

/**
 * FXML Controller class
 *
 */
public class EcranVictoireController implements Initializable {

    private Scene scene;

    private Joueur j1;
    private Joueur j2;

    @FXML
    private Label gagnant;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void set(Scene scene, Joueur jGagnant, Joueur j1, Joueur j2) {
        this.scene = scene;
        this.j1 = j1;
        this.j2 = j2;
        gagnant.setText(jGagnant.getPseudo());
    }

    @FXML
    public void rejouer() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/puissance4/View/JeuLocal.fxml"));

            scene.setRoot(loader.load());

            JeuLocalController jlc = loader.getController();
            jlc.set(scene, j1.getPseudo(), j2.getPseudo(), j1.getCouleur(), j2.getCouleur());
        } catch (IOException ex) {
            Logger.getLogger(SelectionJoueurLocalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void accueil() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/puissance4/View/Accueil.fxml"));

            scene.setRoot(loader.load());

            AccueilController ac = loader.getController();
            ac.set(scene);
        } catch (IOException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void selectionJoueur() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/puissance4/View/SelectionJoueurLocal.fxml"));

            scene.setRoot(loader.load());

            SelectionJoueurLocalController sjlc = loader.getController();
            sjlc.set(scene);
        } catch (IOException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void quitter() {
        Platform.exit();
    }

}
