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
 *
 */
public class EcranEgaliteClientController implements Initializable {

	private Scene scene;

	private Joueur j1;
	private Joueur j2;

	private Socket socket;
	private BufferedReader in;
	private PrintWriter pred;
	private String ip;
	
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

	public void set(Scene scene, Joueur j1, Joueur j2, Socket socket, String ip) {
		this.scene = scene;
		this.j1 = j1;
		this.j2 = j2;

		this.socket = socket;
		this.in = in;
		this.pred = pred;
		this.ip = ip;

        joueur1.setText(j1.getPseudo());
        joueur2.setText(j2.getPseudo());
	}


	@FXML
	public void rejouer() {
		try {
			socket.close();
			Socket socketR= new Socket(ip,2009);
			in = new BufferedReader(new InputStreamReader(socketR.getInputStream()));
			pred = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketR.getOutputStream())), true);
			String str = in.readLine();          // lecture du message
			if (!str.isEmpty()) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/puissance4/View/JeuReseauClient.fxml"));

				scene.setRoot(loader.load());

				JeuReseauClientController jrcc = loader.getController();
				jrcc.set(scene, j1.getPseudo(), j2.getPseudo(), j1.getCouleur(), j2.getCouleur(), in, pred, socketR, ip);
			}
		} catch (IOException ex) {
			Logger.getLogger(SelectionJoueurLocalController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@FXML
	public void accueil() {
		try {
			socket.close();



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
