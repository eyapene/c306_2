package solveur;

/**
 *
 * @author ATSOU Komi Bi-Ayéfo and BOKOBOSSO Eyapènè
 */
public interface InterfaceSolveur {
    
    /**
     * Teste la validité de la grille.
     *
     * @return boolean
     */
    boolean verifierGrille();

    /**
     * Résoud la grille passé en paramètre.
     *
     * @throws IllegalArgumentException si le grille à résoudre n'est pas
     * valable ou si aucune solution n'a pu être calculée
     * @return boolean
     */
    boolean resoudre();

    /**
     * Affiche la solution trouvée à la grille.
     */
    void afficherSolution();
    
    
}
