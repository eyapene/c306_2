package solveur;

import grille.Grille;
import grille.GrilleImpl;

/**
 *
 * @author ATSOU Komi Bi-Ayéfo and BOKOBOSSO Eyapènè.
 */
public final class SolveurImpl implements InterfaceSolveur {

    /**
     * Grille de Sudoku.
     */
    private GrilleImpl grille;

    /**
     * Constructeur de la classe.
     *
     * @param newgrille GrilleImpl
     */
    public SolveurImpl(final GrilleImpl newgrille) {
        this.grille = newgrille;
    }

    /**
     * constructeur par défaut de la classe.
     */
    public SolveurImpl() {
    }

    /**
     * Getter de la grille.
     *
     * @return GrilleImpl
     */
    public GrilleImpl getGrille() {
        return grille;
    }

    /**
     * Setter de la gille.
     *
     * @param newgrille GrilleImpl
     */
    public void setGrille(final GrilleImpl newgrille) {
        this.grille = newgrille;
    }

    /**
     * Vérifie la validité de la grille à résoudre.
     *
     * @return boolean
     */
    public boolean verifierGrille() {
        char[][] grillecontent = grille.getGrille();
        int dimension = grille.getDimension();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (grillecontent[i][j] != Grille.EMPTY) {
                    char tmp = grillecontent[i][j];
                    grillecontent[i][j] = Grille.EMPTY;
                    try {
                        if (!grille.possible(i, j, tmp)) {
                            return false;
                        }
                    } catch (IllegalArgumentException e) {
                        return false;
                    }
                    grillecontent[i][j] = tmp;
                }
            }
        }
        return true;
    }

    /**
     * Verification de la résolution de la grille.
     *
     * @return boolean
     */
    public boolean resolu() {
        if (!verifierGrille()) {
            throw new IllegalArgumentException("Grille non valide.");
        }
        return resoudre();
    }

    /**
     * Résolution de la grille de Sudoku.
     *
     * @return boolean
     */
    public boolean resoudre() {
        int dimension = grille.getDimension();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (grille.getGrille()[i][j] == GrilleImpl.EMPTY) {
                    for (int k = 0; k < dimension; k++) {
                        char val = Grille.POSSIBLE_9[k];
                        try {
                            grille.setValue(i, j, val);
                            if (resoudre()) {
                                return true;
                            }
                        } catch (IllegalArgumentException ex) {
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Affichage de la grille complète après résolution.
     */
    public void afficherSolution() {
        int dim = grille.getDimension();
        int part = 3;
        try {
            if (resolu()) {
                for (int i = 0; i < dim; ++i) {
                    if (i % part == 0) {
                        System.out.println(" -----------------------");
                    }
                    for (int j = 0; j < dim; ++j) {
                        if (j % part == 0) {
                            System.out.print("| ");
                        }
                        System.out.print(grille.getGrille()[i][j]);
                        System.out.print(' ');
                    }
                    System.out.println("|");
                }
                System.out.println(" -----------------------");
            } else {
                System.out.println("Aucune solution trouvée.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Erreur");
        }
    }

}
