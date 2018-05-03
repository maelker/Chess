package model;



/**
 * Classe de la pièce Cavalier.
 * Se déplace de 2 sur une ligne/colonne puis de plus ou moins 1 sur une colonne/ligne.
 */
public final class Cavalier extends AbstractPiece{

	/**
	 * Ce constructeur permet d'initialiser un cavalier avec 
	 * aucun paramètres.
	 */
	public Cavalier(){
		super(Couleur.BLANC,new Coord(0,0));
		this.name = "Cavalier";
	}
	
	/**
	 * Ce constructeur permet d'initialiser un Cavalier avec 
	 * une couleur et des coordonnées en paramètres.
	 * @param couleur
	 * @param coord
	 */
	public Cavalier(Couleur couleur_de_piece, Coord coord){
		super(couleur_de_piece,coord);
		this.name = "Cavalier";
	}
	
	/**
	 * Permet de vérifier si les mouvements de le Cavalier 
	 * peuvent se déplacer aux coordonnées (xFinal,yFinal).
	 * @param xFinal
	 * @param yFinal
	 */
	@Override
	public boolean isMoveOk(int xFinal, int yFinal) {
		boolean retour = false;
		if (Coord.coordonnees_valides(xFinal, yFinal) && ((this.coord.x != xFinal) || (this.coord.x != xFinal))){
			if (Math.abs(xFinal - this.coord.x) == 2){
				retour = (Math.abs(yFinal - this.coord.y) == 1);
			} else if(Math.abs(yFinal - this.coord.y) == 2){
				retour = (Math.abs(xFinal - this.coord.x) == 1);
			} else{
				retour = false;
			}
		} else{
			retour = false;
		}
		return retour;
	}
	
}
