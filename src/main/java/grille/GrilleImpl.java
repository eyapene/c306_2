package grille;


/**
 *
 * Implementation d'une interface modelisant une grille Sudoku.
 *
 * @author ATSOU Komi Bi-Ayéfo and BOKOBOSSO Eyapènè
 *
 */
public class GrilleImpl implements Grille {

    /**
     * Tableau permettant de representer la grille Sudoku.
     */
    private char[][] grille;

    /**
     * Permet de recuperer la grille.
     *
     * @return Tableau bidimensionnel de caracteres permettant de representer la
     * grille.
     */
    public char[][] getGrille() {
        return grille;
    }

    /**
     * Constructeur de la classe GrilleImpl.
     *
     * @param grilleSudoku Tableau bidimensionnel de caracteres permettant de
     * representer la grille.
     */
    public GrilleImpl(final char[][] grilleSudoku) {
        this.grille = grilleSudoku;
    }

    /**
     * Permet d'avoir la dimension d'une grille Sudoku.
     * @return Dimension de la grille.
     */
    public int getDimension() {
        return this.getGrille().length;
    }

    /**
     * Permet de renseigner une valeur dans la grille Sudoku.
     * @param x Index de ligne
     * @param y Index de colonne
     * @param value Valeur a verifier
     * @throws IllegalArgumentException si x ou y incorrect.
     */
    public void setValue(final int x, final int y, final char value)
            throws IllegalArgumentException {
        if (possible(x, y, value)) {
            this.grille[x - 1][y - 1] = value;
        } else { 
            throw new IllegalArgumentException("Valeur interdite");
        }
    }

    /**
     * Permet de recuperer une valeur de la grille.
     * @param x Index de ligne
     * @param y Index de colonne
     * @return Caractere a la ligne x et la colonne y
     * @throws IllegalArgumentException si x ou y incorrect.
     */
    public char getValue(final int x, final int y)
            throws IllegalArgumentException {

        if (x < 0 || x >= this.getDimension()) {
            throw new IllegalArgumentException("Index de ligne hors-borne");
        }
        if (y < 0 || y >= this.getDimension()) {
            throw new IllegalArgumentException("Index de colonne hors-borne");
        }

        return this.grille[x - 1][y - 1];

    }

    /**
     * Permet de verifier qu'une grille est complete.
     *
     * @return true si grille complete, false sinon
     */
    public boolean complete() {
        for (int x = 0; x < this.getDimension(); x++) {
            for (int y = 0; y < this.getDimension(); y++) {
                if (this.grille[x][y] == Grille.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Permet de verifier qu'une valeur a mettre dans la grille est possible.
     *
     * @param x Index de ligne
     * @param y Index de colonne
     * @param value Valeur a verifier
     * @return true si valeur possible, false sinon
     * @throws IllegalArgumentException si x ou y incorrect.
     */
    public boolean possible(final int x, final int y, final char value)
            throws IllegalArgumentException {

        if (x < 0 || x >= this.getDimension()) {
            throw new IllegalArgumentException("Index de ligne hors-borne");
        }
        if (y < 0 || y >= this.getDimension()) {
            throw new IllegalArgumentException("Index de colonne hors-borne");
        }
        if (!caracterePossible(value)) {
            throw new IllegalArgumentException("Valeur non autorisee");
        }

        // Verification sur la ligne
        for (int i = 0; i < this.getDimension(); i++) {
            if (this.grille[x][i] == value) {
                return false;
            }
        }

        // Verification sur la colonne
        for (int i = 0; i < this.getDimension(); i++) {
            if (this.grille[i][y] == value) {
                return false;
            }
        }

        // Verification dans le bloc
        int dimensionBloc = (int) Math.sqrt(this.getDimension());
        int i = (x / dimensionBloc) * dimensionBloc;
        int j = (y / dimensionBloc) * dimensionBloc;
        for (int l = 0; l < dimensionBloc; l++) {
            for (int c = 0; c < dimensionBloc; c++) {
                if (this.grille[i + l][j + c] == value) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Permet de modifier le tableau representant la grille.
     *
     * @param grilleTab Nouvelle grille
     */
    public void setGrille(final char[][] grilleTab) {
        this.grille = grilleTab;
    }

    /**
     * Methode utilitaire permettant de verifier qu'un caractere est possible.
     *
     * @param caractere Caractere a verifier.
     * @return true si oui, false sinon
     */
    private boolean caracterePossible(final char caractere) {
        for (int i = 0; i < this.getDimension(); i++) {
            if (Grille.POSSIBLE_9[i] == caractere) {
                return true;
            }
        }
        return false;
    }

}
