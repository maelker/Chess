package model;



/**
 * Classe de la pièce Tour.
 * Se déplace sur les lignes et colonnes.
 */
public final class Tour extends AbstractPiece{

	/**
	 * Ce constructeur permet d'initialiser une tour avec 
	 * aucun paramètres.
	 */
	public Tour(){
		super(Couleur.BLANC,new Coord(0,0));
		this.name = "Tour";
	}
	
	/**
	 * Ce constructeur permet d'initialiser une tour avec 
	 * une couleur et des coordonnées en paramètres.
	 * @param couleur
	 * @param coord
	 */
	public Tour(Couleur couleur_de_piece, Coord coord){
		super(couleur_de_piece,coord);
		this.name = "Tour";
	}
	
	/**
	 * Permet de vérifier si les mouvements de la tour 
	 * peuvent se déplacer aux coordonnées (xFinal,yFinal).
	 * @param xFinal
	 * @param yFinal
	 */
	@Override
	public boolean isMoveOk(int xFinal, int yFinal) {
		boolean retour = false;
		if (Coord.coordonnees_valides(xFinal, yFinal) && ((this.coord.x != xFinal) || (this.coord.x != xFinal))){
			retour =((xFinal == this.coord.x) || (yFinal == this.coord.y));
		} else{
			retour = false;
		}
		return retour;
	}
	
}
