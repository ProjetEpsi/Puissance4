/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puissance4.Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
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
 */
public class EcranEgaliteServeurController implements Initializable {

    private Scene scene;

    private Joueur j1;
    private Joueur j2;
    
    private Socket socketduserveur;
    private ServerSocket socketserver;
    private BufferedReader in;
    private PrintWriter pred;
    
    @FXML
    private Label joueur1;

    @FXML
    private Label joueur2;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void set(Scene scene, Joueur j1, Joueur j2, Socket socketduserveur, ServerSocket socketserver) {
        this.scene = scene;
        this.j1 = j1;
        this.j2 = j2;
        
        this.socketduserveur = socketduserveur;
        this.socketserver = socketserver;
        this.in = in;
        this.pred = pred;
        
        joueur1.setText(j1.getPseudo());
        joueur2.setText(j2.getPseudo());
    }

  
    @FXML
    public void rejouer() {
        	try {
    			
    			  socketserver.close();
    			  socketduserveur.close();
    			  socketserver = new ServerSocket(2009, 10, InetAddress.getLocalHost());
    			  socketduserveur = socketserver.accept();
    			  in = new BufferedReader(new InputStreamReader(socketduserveur.getInputStream()));
    	          pred = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketduserveur.getOutputStream())), true);
    			  pred.println("replay");
    			
    				FXMLLoader loader = new FXMLLoader(getClass().getResource("/puissance4/View/JeuReseauServeur.fxml"));

    				scene.setRoot(loader.load());

    				JeuReseauServeurController jrsc = loader.getController();
    				jrsc.set(scene, j1.getPseudo(), j2.getPseudo(), j1.getCouleur(), j2.getCouleur(), in, pred, socketduserveur, socketserver);
    			
    		} catch (IOException ex) {
    			Logger.getLogger(SelectionJoueurLocalController.class.getName()).log(Level.SEVERE, null, ex);
    		}
    	}

    @FXML
    public void accueil() {
        try {
        	socketserver.close();
        	socketduserveur.close();
        	
        	
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
