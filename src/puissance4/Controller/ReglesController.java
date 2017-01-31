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

/**
 * FXML Controller class
 *
 */
public class ReglesController implements Initializable {

    private Scene scene;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void set(Scene scene) {
        this.scene = scene;
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
    public void quitter() {
        Platform.exit();
    }

}
