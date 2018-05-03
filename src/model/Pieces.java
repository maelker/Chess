package model;


/**
 * Permet de récuperer des informations sur l'état d'une 
 * pièce.
 * @param couleur
 * @param coord
 */
public interface Pieces {
	
	/**
	 *  Permet de récuperer la colonne ou est positionnée
	 *  la pièce.
	 *  @return x
	 */
	int getX();
	
	/**
	 *  Permet de récuperer la ligne ou est positionnée
	 *  la pièce.
	 *  @return y
	 */
	int getY();
	
	/**
	 *  Permet de récuperer la couleur de la pièce.
	 *  @return couleur
	 */
	Couleur getCouleur();
	
	/**
	 *  Permet de savoir si la pièce peut bouger à la 
	 *  position (xFinal,yFinal).
	 *  @param xFinal
	 *  @param yFinal
	 *  @return true si la pièce peut bouger, sinon false.
	 */
	boolean isMoveOk(int xFinal, int yFinal);
	
	/**
	 *  Si la pièce peux bouger aux positions (xFinal,yFinal)
	 *  alors on changes les coordonnées de la pièce et on
	 *  retourne true.
	 *  @param xFinal
	 *  @param yFinal
	 *  @return true si la pièce à bougée, sinon false.
	 */
	boolean move(int xFinal, int yFinal);
	
	/**
	 *  Permet d'enlever une pièce si elle à été capturée.
	 *  Elle met les coordonnées de la pièce à -1.
	 *  @return true si la pièce à été capturée.
	 */
	boolean capture();
	
}
