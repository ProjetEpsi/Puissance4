/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puissance4.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Romain
 */
public class Partie {

    private Joueur j1;
    private Joueur j2;
    private Plateau plateau;
    private Joueur jCourant;
    private StringProperty jTour = new SimpleStringProperty();

    public Partie(Joueur j1, Joueur j2) {
        this.j1 = j1;
        this.j2 = j2;
        this.plateau = new Plateau();
        this.jCourant = j1;
        jTour.set(jCourant.getPseudo());
    }

    public void changementTour() {
        if (this.jCourant.equals(j1)) {
            this.jCourant = j2;
        } else {
            this.jCourant = j1;
        }
        jTour.set(jCourant.getPseudo());
    }

    public void jouerTour(int x, int y) {
        Case c = plateau.getCase(x, y);
        c.setJoueur(jCourant);
        c.setEstVide(false);
    }

    public boolean gagner(int x, int y) {
        return plateau.gagnerHorizontale(jCourant, x) || plateau.gagnerVerticale(jCourant, y) 
                || plateau.gagnerDiagonaleNE(jCourant, x, y) || plateau.gagnerDiagonaleSE(jCourant, x, y);
    }
    
    public boolean estPlein(){
        return plateau.estPlein();
    }

    public Joueur getJ1() {
        return j1;
    }

    public Joueur getJ2() {
        return j2;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public Joueur getjCourant() {
        return jCourant;
    }
    
    public StringProperty getjTour(){
        return jTour;
    }

}
