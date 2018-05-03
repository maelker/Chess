package model;



/**
 * @author mael.kerloch
 * 
 * Cette classe définis les différentes méthodes auxquelles
 * on peut avoir accès pour chaques pièces.
 *
 */
public abstract class AbstractPiece implements Pieces{
	
	protected String name;
	protected Couleur couleur;
	protected Coord coord;
	
	public AbstractPiece(Couleur couleur, Coord coord){
		this.couleur = couleur;
		this.coord = coord;
	}
	
	
	public int getX() {
		return coord.x;
	}
	
	public int getY() {
		return coord.y;
	}

	public Couleur getCouleur() {
		return couleur;
	}
	
	public boolean move(int x, int y) {
		boolean retour = false;
		if (isMoveOk( x, y)){
			this.coord.x = x;
			this.coord.y = y;
			retour = true;
		} else{
			retour = false;
		}
		return retour;
	}
	
	public boolean capture() {
		this.coord.x = -1;
		this.coord.y = -1;
		return true;
	}
	
	
	public java.lang.String toString() {
		String string = this.name+" : coord(" + this.coord + ") "+couleur+".\n";
		return string;
	}
	
	public abstract boolean isMoveOk(int xFinal, int yFinal);
	
	public static void main(String arg[]){
		/*
		Pieces P1 = new Pion(Couleur.BLANC,new Coord(2,6));
		Pieces P2 = new Pion(Couleur.NOIR,new Coord(1,1));

		System.out.print(P1+"\n");
		System.out.print(P1.move(2, 3)+" ");
		System.out.print(P1+"\n");
		
		System.out.print(P2+"\n");
		System.out.print(P2.move(1, 2)+" ");
		System.out.print(P2+"\n");
		*/
	}
	
}
