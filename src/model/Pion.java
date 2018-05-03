package model;


/**
 * Classe de la pièce Pion.
 * Se déplace de un en avant et peux avancer de deux en avant
 * lors de son premier tour.
 */
public final class Pion extends AbstractPiece{

	/**
	 * Ce constructeur permet d'initialiser un Pion avec 
	 * aucun paramètres.
	 */
	public Pion(){
		super(Couleur.BLANC,new Coord(0,0));
		this.name = "Pion";
	}
	
	/**
	 * Ce constructeur permet d'initialiser un Pion avec 
	 * une couleur et des coordonnées en paramètres.
	 * @param couleur
	 * @param coord
	 */
	public Pion(Couleur couleur_de_piece, Coord coord){
		super(couleur_de_piece,coord);
		this.name = "Pion";
	}
	
	/**
	 * Permet de vérifier si les mouvements d'un pion 
	 * peut se déplacer aux coordonnées (xFinal,yFinal).
	 * @param xFinal
	 * @param yFinal
	 */
	@Override
	public boolean isMoveOk(int xFinal, int yFinal) {
		boolean retour = false;
		int yInit = 1;
		int dir = 1;
		if (Coord.coordonnees_valides(xFinal, yFinal) && (this.coord.y != yFinal)){
			switch (couleur){
				case BLANC:
					yInit = 6;
					dir = -1;
					break;
				case NOIR:
					yInit = 1;
					dir = 1;
					break;
				default:
					break;
			}
			if (this.coord.y == yInit && this.coord.x == xFinal){
				retour = (((yFinal - this.coord.y) == 1 * dir) || ((yFinal - this.coord.y) == 2 * dir));
			} else if (this.coord.x == xFinal){
				retour = ((yFinal - this.coord.y) == 1 * dir);
			} else {
				retour = isMoveDiagOk(xFinal, yFinal);
			}
			
		} else {
			retour = false;
		}
		return retour;
	}
	
	/**
	 * Permet de vérifier si les mouvements de le Pion 
	 * peuvent se déplacer aux coordonnées (xFinal,yFinal)
	 * afin de manger une autre pièce
	 * @param xFinal
	 * @param yFinal
	 */
	public boolean isMoveDiagOk(int xFinal, int yFinal) {
		boolean retour = false;
		int dir = 1;
		if (Coord.coordonnees_valides(xFinal, yFinal)){
			switch (couleur){
			case BLANC:
				dir = -1;
				break;
			case NOIR:
				dir = 1;
				break;
			default:
				break;
			}
			if ( (this.coord.y  == (yFinal - dir)) && ((this.coord.x == (xFinal - 1)) || (this.coord.x == (xFinal + 1))) ){
				retour = true;
			} else {
				retour = false;
			}
		}
		return retour;
	}
	
}
