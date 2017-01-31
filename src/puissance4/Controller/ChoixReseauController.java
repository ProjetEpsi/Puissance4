package puissance4.Controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
/**
 * FXML Controller class
 *
 */
public class ChoixReseauController {

    private Scene scene;

    public void set(Scene scene) {
        this.scene = scene;
    }

    @FXML
    public void selectionClient() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/puissance4/View/SelectionJoueurReseauClient.fxml"));

            scene.setRoot(loader.load());

            SelectionJoueurReseauClientController sjrcc = loader.getController();
            sjrcc.set(scene);
        } catch (IOException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void selectionServeur() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/puissance4/View/SelectionJoueurReseauServeur.fxml"));

            scene.setRoot(loader.load());

            SelectionJoueurReseauServeurController sjrsc = loader.getController();
            sjrsc.set(scene);
        } catch (IOException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
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
    public void quitter() {
        Platform.exit();
    }

}
