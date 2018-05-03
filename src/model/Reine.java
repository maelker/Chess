package model;



/**
 * Classe de la pièce Reine.
 * Se déplace de sur les lignes, colonnes et diagonales.
 */
public final class Reine extends AbstractPiece{

	/**
	 * Ce constructeur permet d'initialiser un Reine avec 
	 * aucun paramètres.
	 */
	public Reine(){
		super(Couleur.BLANC,new Coord(0,0));
		this.name = "Reine";
	}
	
	/**
	 * Ce constructeur permet d'initialiser un Reine avec 
	 * une couleur et des coordonnées en paramètres.
	 * @param couleur
	 * @param coord
	 */
	public Reine(Couleur couleur_de_piece, Coord coord){
		super(couleur_de_piece,coord);
		this.name = "Reine";
	}
	
	/**
	 * Permet de vérifier si les mouvements de la Reine 
	 * peuvent se déplacer aux coordonnées (xFinal,yFinal).
	 * @param xFinal
	 * @param yFinal
	 */
	@Override
	public boolean isMoveOk(int xFinal, int yFinal) {
		boolean retour = false;
		if (Coord.coordonnees_valides(xFinal, yFinal) && ((this.coord.x != xFinal) || (this.coord.x != xFinal))){
			if ((xFinal == this.coord.x) || (yFinal == this.coord.y)){
				retour = true;
			} else if (Math.abs(xFinal - this.coord.x) == Math.abs(yFinal - this.coord.y)){
				retour = true;
			} else {
				retour = false;
			}
		} else{
			retour = false;
		}
		return retour;
	}
	
}
