package puissance4.Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
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
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
/**
 * FXML Controller class
 *
 */
public class AttenteJoueurReseauController extends Thread implements Initializable {

    private Scene scene;
    @FXML
    private Text adressIP;

    ServerSocket socketserver;

    private String pseudoJ1;
    private Color couleurJ1;

    public void set(Scene scene, String pseudo1, Color couleur1) {
        this.scene = scene;
        this.pseudoJ1 = pseudo1;
        this.couleurJ1 = couleur1;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                connexion();
            }
        });

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        InetAddress adrLocale;

        try {

            adrLocale = InetAddress.getLocalHost();
            adressIP.setText(adrLocale.getHostAddress());
            socketserver = new ServerSocket(2009, 10, adrLocale);
            
            System.out.println("Lancement du serveur");

//			socketduserveur = socketserver.accept();
//			BufferedReader in = new BufferedReader(new InputStreamReader(socketduserveur.getInputStream()));
//			PrintStream out = new PrintStream(socketduserveur.getOutputStream());
        } catch (IOException e) {
            System.out.println("erreur recuperation IP");
            e.printStackTrace();
        }

    }

    public void connexion() {

        Socket socketduserveur = null;
        BufferedReader in = null;
        PrintWriter pred = null;
        try {
            socketduserveur = socketserver.accept();

            in = new BufferedReader(new InputStreamReader(socketduserveur.getInputStream()));
            pred = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketduserveur.getOutputStream())), true);

            String str = in.readLine();          // lecture du message
            if (!str.isEmpty()) {
                pred.println(pseudoJ1 + "-" + couleurJ1);

                String[] infoJ2 = str.split("-");

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/puissance4/View/jeuReseauServeur.fxml"));

                scene.setRoot(loader.load());

                JeuReseauServeurController jrsc = loader.getController();
                jrsc.set(scene, pseudoJ1, infoJ2[0], couleurJ1, Color.web(infoJ2[1]), in, pred , socketduserveur ,socketserver);

            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
