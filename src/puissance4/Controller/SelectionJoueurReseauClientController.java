package puissance4.Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
public class SelectionJoueurReseauClientController {

    private Scene scene;

    @FXML
    private Button valider;

    @FXML
    private Button retour;

    @FXML
    private TextField pseudoJ2;

    @FXML
    private ColorPicker couleurJ2;

    @FXML
    private TextField ipConfig;

    public void set(Scene scene) {
        this.scene = scene;

    }

    @FXML
    public void valider() {
        if (!(pseudoJ2.getText().isEmpty()) && !(ipConfig.getText().isEmpty())) {
            Socket socket;
            try {

                socket = new Socket(ipConfig.getText(), 2009);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter pred = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

                
                pred.println(pseudoJ2.getText() + "-"+couleurJ2.getValue());
                String repJ1 = in.readLine();
                
                String[] infoJ1 = repJ1.split("-");
                
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/puissance4/View/JeuReseauClient.fxml"));

                scene.setRoot(loader.load());
                
                String ip = ipConfig.getText();

                JeuReseauClientController jrcc = loader.getController();
                jrcc.set(scene,infoJ1[0] , pseudoJ2.getText() ,Color.web(infoJ1[1]) , couleurJ2.getValue(), in, pred, socket, ip);
            } catch (UnknownHostException e) {

                e.printStackTrace();
            } catch (IOException e) {

                e.printStackTrace();
            }
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

}
