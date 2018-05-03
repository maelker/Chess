package model;



/**
 * Classe de la pièce Fou.
 * Se déplace en diagonale.
 */
public final class Fou extends AbstractPiece{

	/**
	 * Ce constructeur permet d'initialiser un Fou avec 
	 * aucun paramètres.
	 */
	public Fou(){
		super(Couleur.BLANC,new Coord(0,0));
		this.name = "Fou";
	}
	
	/**
	 * Ce constructeur permet d'initialiser un Fou avec 
	 * une couleur et des coordonnées en paramètres.
	 * @param couleur
	 * @param coord
	 */
	public Fou(Couleur couleur_de_piece, Coord coord){
		super(couleur_de_piece,coord);
		this.name = "Fou";
	}
	
	/**
	 * Permet de vérifier si les mouvements du Fou 
	 * peuvent se déplacer aux coordonnées (xFinal,yFinal).
	 * @param xFinal
	 * @param yFinal
	 */
	@Override
	public boolean isMoveOk(int xFinal, int yFinal) {
		boolean retour = false;
		if (Coord.coordonnees_valides(xFinal, yFinal) && ((this.coord.x != xFinal) || (this.coord.x != xFinal))){
			retour = (Math.abs(xFinal - this.coord.x) == Math.abs(yFinal - this.coord.y));
		} else{
			retour = false;
		}
		return retour;
	}
	
}
