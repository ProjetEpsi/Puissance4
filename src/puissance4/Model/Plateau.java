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
public class Plateau {

    private Case[][] plateau;

    public Plateau() {
        plateau = new Case[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                plateau[i][j] = new Case(i, j);
            }
        }
    }

    public Case[][] getPlateau() {
        return plateau;
    }

    public Case getCase(int i, int j) {
        return plateau[i][j];
    }

    public boolean firstCase(int x, int y) {
        boolean retour = true;
        for (int i = x + 1; i < 6; i++) {
            if (plateau[i][y].EstVide()) {
                retour = false;
            }
        }

        return retour;
    }

    public Case getFirstCase(int y) {
        if (this.getCase(0, y).EstVide()) {
            Case retour = this.getCase(0, y);         
            for (int i = 1; i < 6; i++) {
                if (this.getCase(i, y).EstVide()) {
                    retour = this.getCase(i, y);
                }
            }
            return retour;
        }else{
            return null;
        }
    }

    public boolean gagnerHorizontale(Joueur j, int x) {
        boolean retour = false;
        int cpt = 0;

        for (int i = 0; i < 7; i++) {
            if (plateau[x][i].getJoueur() != null) {
                if (plateau[x][i].getJoueur().equals(j)) {
                    cpt += 1;
                    if (cpt == 4) {
                        retour = true;
                    }
                } else {
                    cpt = 0;
                }
            } else {
                cpt = 0;
            }
        }
        return retour;
    }

    public boolean gagnerVerticale(Joueur j, int y) {
        boolean retour = false;
        int cpt = 0;

        for (int i = 0; i < 6; i++) {
            if (plateau[i][y].getJoueur() != null) {
                if (plateau[i][y].getJoueur().equals(j)) {
                    cpt += 1;
                    if (cpt == 4) {
                        retour = true;
                    }
                } else {
                    cpt = 0;
                }
            } else {
                cpt = 0;
            }
        }
        return retour;
    }

    public boolean gagnerDiagonaleNE(Joueur j, int x, int y) {
        boolean retour = false;
        while (x < 5 && y > 0) {
            x += 1;
            y -= 1;
        }

        int cpt = 0;
        while (x >= 0 && y < 7) {
            if (plateau[x][y].getJoueur() != null) {
                if (plateau[x][y].getJoueur().equals(j)) {
                    cpt += 1;
                    if (cpt == 4) {
                        retour = true;
                    }
                } else {
                    cpt = 0;
                }
            } else {
                cpt = 0;
            }
            x -= 1;
            y += 1;
        }
        return retour;
    }

    public boolean gagnerDiagonaleSE(Joueur j, int x, int y) {
        boolean retour = false;
        while (x > 0 && y > 0) {
            x -= 1;
            y -= 1;
        }
        int cpt = 0;
        while (x < 6 && y < 7) {
            if (plateau[x][y].getJoueur() != null) {
                if (plateau[x][y].getJoueur().equals(j)) {
                    cpt += 1;
                    if (cpt == 4) {
                        retour = true;
                    }
                } else {
                    cpt = 0;
                }
            } else {
                cpt = 0;
            }
            x += 1;
            y += 1;
        }
        return retour;
    }

    public boolean estPlein() {
        boolean retour = true;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (plateau[i][j].EstVide()) {
                    retour = false;
                }
            }
        }
        return retour;
    }
}
