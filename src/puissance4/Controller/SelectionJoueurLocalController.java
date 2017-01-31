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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 */
public class SelectionJoueurLocalController implements Initializable {

    private Scene scene;

    @FXML
    private TextField pseudoJ1;

    @FXML
    private ColorPicker couleurJ1;

    @FXML
    private TextField pseudoJ2;

    @FXML
    private ColorPicker couleurJ2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        couleurJ1.setValue(Color.RED);
        couleurJ2.setValue(Color.YELLOW);
    }

    public void set(Scene scene) {
        this.scene = scene;
    }

    @FXML
    public void validation() {
        if (!(pseudoJ1.getText().isEmpty()) && !(pseudoJ2.getText().isEmpty()) && !(pseudoJ1.getText().equals(pseudoJ2.getText())) && !(couleurJ1.getValue().equals(couleurJ2.getValue()))) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/puissance4/View/JeuLocal.fxml"));
                
                scene.setRoot(loader.load());
                
                JeuLocalController jlc = loader.getController();
                jlc.set(scene, pseudoJ1.getText(), pseudoJ2.getText(), couleurJ1.getValue(), couleurJ2.getValue());
            } catch (IOException ex) {
                Logger.getLogger(SelectionJoueurLocalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Erreur lors de la selection des joueurs");
            alert.setContentText(null);
            alert.showAndWait();
        }
    }

    @FXML
    public void retourAccueil() {
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
