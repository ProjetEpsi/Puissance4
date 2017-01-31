/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puissance4.Model;

import javafx.scene.paint.Color;

/**
 *
 * @author Romain
 */
public class Joueur {

    private int id;
    private String pseudo;
    private Color couleur;

    public Joueur(int id, String pseudo, Color couleur) {
        this.id = id;
        this.pseudo = pseudo;
        this.couleur = couleur;
    }

    public int getId() {
        return id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public Color getCouleur() {
        return couleur;
    }

}
