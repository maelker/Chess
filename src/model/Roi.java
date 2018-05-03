package model;



/**
 * Classe de la pièce Roi.
 * Se déplace de un dans toutes les directions.
 */
public final class Roi extends AbstractPiece{

	/**
	 * Ce constructeur permet d'initialiser un roi avec 
	 * aucun paramètres.
	 */
	public Roi(){
		super(Couleur.BLANC,new Coord(0,0));
		this.name = "Roi";
	}
	
	/**
	 * Ce constructeur permet d'initialiser un Roi avec 
	 * une couleur et des coordonnées en paramètres.
	 * @param couleur
	 * @param coord
	 */
	public Roi(Couleur couleur_de_piece, Coord coord){
		super(couleur_de_piece,coord);
		this.name = "Roi";
	}
	
	/**
	 * Permet de vérifier si les mouvements de le Roi 
	 * peuvent se déplacer aux coordonnées (xFinal,yFinal).
	 * @param xFinal
	 * @param yFinal
	 */
	@Override
	public boolean isMoveOk(int xFinal, int yFinal) {
		boolean retour = false;
		if (Coord.coordonnees_valides(xFinal, yFinal) && ((this.coord.x != xFinal) || (this.coord.y != yFinal))){
			retour = (((xFinal == this.coord.x) || (xFinal == this.coord.x+1) || (xFinal == this.coord.x-1)) && ((yFinal == this.coord.y) || (yFinal == this.coord.y+1) || (yFinal == this.coord.y-1)));
		} else{
			return retour;
		}
		return retour;
	}
	
}
