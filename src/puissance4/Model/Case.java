/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puissance4.Model;

/**
 *
 * @author Romain
 */
public class Case {

    private boolean estVide;
    private int x;
    private int y;
    private Joueur joueur;

    public Case(int x, int y) {
        this.estVide = true;
        this.x = x;
        this.y = y;
    }

    public boolean EstVide() {
        return estVide;
    }

    public void setEstVide(boolean estVide) {
        this.estVide = estVide;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    @Override
    public String toString() {
        return "Case{" + "estVide=" + estVide + ", x=" + x + ", y=" + y + ", joueur=" + joueur + '}';
    }

    
}
