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
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 */
public class AccueilController implements Initializable {

    @FXML
    private BorderPane bp;

    private BorderPane bpParent;
    private Scene scene;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void set(Scene scene) {
        this.scene = scene;
    }

    @FXML
    public void jeuLocal() {
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
    public void jeuReseau() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/puissance4/View/ChoixReseau.fxml"));

            scene.setRoot(loader.load());

            ChoixReseauController crc = loader.getController();
            crc.set(scene);
        } catch (IOException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void regles() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/puissance4/View/Regles.fxml"));

            scene.setRoot(loader.load());

            ReglesController rc = loader.getController();
            rc.set(scene);
        } catch (IOException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void quitter() {
        Platform.exit();
    }
}
