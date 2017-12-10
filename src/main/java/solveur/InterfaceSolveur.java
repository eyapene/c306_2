package solveur;

/**
 *
 * @author ATSOU Komi Bi-Ay�fo and BOKOBOSSO Eyap�n�
 */
public interface InterfaceSolveur {
    
    /**
     * Teste la validit� de la grille.
     *
     * @return boolean
     */
    boolean verifierGrille();

    /**
     * R�soud la grille pass� en param�tre.
     *
     * @throws IllegalArgumentException si le grille � r�soudre n'est pas
     * valable ou si aucune solution n'a pu �tre calcul�e
     * @return boolean
     */
    boolean resoudre();

    /**
     * Affiche la solution trouv�e � la grille.
     */
    void afficherSolution();
    
    
}
