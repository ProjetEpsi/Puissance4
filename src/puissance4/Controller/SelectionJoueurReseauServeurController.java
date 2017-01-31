package puissance4.Controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
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
import javafx.scene.text.Text;
/**
 * FXML Controller class
 *
 */
public class SelectionJoueurReseauServeurController {
private Scene scene;


@FXML
private Button valider;

@FXML
private Button retour;

@FXML
private TextField pseudoJ1;

@FXML
private ColorPicker couleurJ1;

	public void set(Scene scene) {
		this.scene = scene;
		
	}
	
	
    @FXML
    public void validation() {
        if (!(pseudoJ1.getText().isEmpty())) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/puissance4/View/AttenteJoueurReseau.fxml"));
                
                scene.setRoot(loader.load());
                
                AttenteJoueurReseauController ajrc = loader.getController();
                ajrc.set(scene, pseudoJ1.getText(), couleurJ1.getValue());
            } catch (IOException ex) {
                Logger.getLogger(AttenteJoueurReseauController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
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


}
