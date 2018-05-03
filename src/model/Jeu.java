package model;

import java.util.LinkedList;
import java.util.List;

import tools.ChessPiecesFactory;

/**
 * Permet de créer et de manipuler le Set de pièces d'un joueur.
 */
public class Jeu {
	
	private Couleur couleur;
	private List<Pieces> pieces = null;
	
	/**
	 *  Constructeur de la class Jeu
	 *  Elle créé un Jeu pour la couleur passée en paramètre
	 *  @param couleur
	 */
	public Jeu(Couleur couleur){
		super();
		this.couleur = couleur;
		pieces = ChessPiecesFactory.newPieces(this.couleur);
	}
	
	/**
	 *  Cette fonction affiche les pièces du Jeu avec leurs attributs
	 *  (name, coord, couleur).
	 *  @return un string avec les pièces et leurs attributs.
	 */
	@Override
	public String toString() {
		return "Jeu "+couleur+"[pieces =" + pieces + "]";
	}
	
	/**
	 *  Cette fonction permet de regarder s'il y a une pièce aux
	 *  coordonnées (x, y) et de la retourner s'il y'en a une.
	 *  @param x
	 *  @param y
	 *  @return La pièce trouvée ou 'null' s'il n'y a pas de pièces
	 *  		à ces coordonnées.
	 */
	private Pieces findPiece(int x, int y){
		Pieces retour = null;
		for (int i = 0; i < pieces.size(); i++){
			if ((pieces.get(i).getX() == x) && (pieces.get(i).getY() == y)){
				retour = pieces.get(i);
			}
		}
		
		return retour;
	}
	
	/**
	 *  Cette fonction permet de regarder s'il y a une pièce aux
	 *  coordonnées (x, y) et de retourner 'true' s'il y'en a une.
	 *  @param x
	 *  @param y
	 *  @return true si une pièce est présente, sinon false.
	 */
	public boolean isPieceHere(int x, int y){
		boolean retour = false;
		if (findPiece(x,y) != null){
			retour = true;
		}
		return retour;
	}
	
	/**
	 *  Cette fonction permet de regarder s'il y a une pièce aux
	 *  coordonnées (x, y) et de retourner 'true' s'il y'en a une.
	 *  @param x
	 *  @param y
	 *  @return true si une pièce est présente, sinon false.
	 */
	public boolean isMoveOk(int xInit,int yInit,int xFinal,int yFinal){
		boolean retour = false;
		
		if (Coord.coordonnees_valides(xInit, yInit) && Coord.coordonnees_valides(xFinal, yFinal)){
			retour = findPiece(xInit, yInit).isMoveOk(xFinal, yFinal);
		}else{
			retour = false;
		}
		return retour;
	}
    
	/**
	 *  Cette fonction permet de bouger une pièce de coordonnées (xInit,yInit)
	 *  aux positions (xFinal,yFinal) si c'est possible.
	 *  @param x
	 *  @param y
	 *  @return true si la pièce à bougé, sinon false.
	 */
    public boolean move(int xInit,int yInit,int xFinal,int yFinal){
		boolean retour = false;
		
		if (isMoveOk(xInit,yInit,xFinal,yFinal)){
			findPiece(xInit, yInit).move(xFinal, yFinal);
			retour = true;
		}else{
			retour = false;
		}
		
		return retour;
    }
    
    /* A ne pas coder pour l'instant
    public void setPossibleCapture(){
    	//TODO
    }
    
    public boolean capture(int xCatch,int yCatch){
    	boolean retour = false;
		//TODO
		return retour;
    }
    */
    
    /**
	 *  Cette fonction permet de retourner la couleur de la 
	 *  piece aux coordonnées (x, y).
	 *  @param x
	 *  @param y
	 *  @return couleur de la pièce, sinon "null".
	 */
    public Couleur getPieceColor(int x,int y){
    	Couleur retour = null;
    	if (isPieceHere(x,y)){
    		retour = findPiece(x, y).getCouleur();
    	}else{
    		retour = null;
    	}
    	return retour;
    }
    
    /**
	 *  Cette fonction permet de retourner le type de la 
	 *  piece aux coordonnées (x, y).
	 *  @param x
	 *  @param y
	 *  @return Type de la pièce, sinon "null".
	 */
    public java.lang.String getPieceType(int x,int y){
    	java.lang.String retour = null;
    	if (isPieceHere(x,y)){
    		return findPiece(x, y).getClass().getSimpleName();
    	}else{
    		retour = null;
    	}
    	return retour;
    }
    
    /**
	 *  Cette fonction permet de retourner la couleur du jeu
	 *  courant.
	 *  @param x
	 *  @param y
	 *  @return couleur du jeu.
	 */
    public Couleur getCouleur(){
		return this.couleur;
    }
    
    /**
    * @return une vue de la liste des pièces en cours
    * ne donnant que des accès en lecture sur des PieceIHM
    * (type piece + couleur + liste de coordonnées)
    */
    public List<PieceIHM> getPiecesIHM(){
	    PieceIHM newPieceIHM = null;
	    List<PieceIHM> list = new LinkedList<PieceIHM>();
	    for (Pieces piece : pieces){
		    boolean existe = false;
		    // si le type de piece existe déjà dans la liste de PieceIHM
		    // ajout des coordonnées de la pièce dans la liste de Coord de ce type
		    // si elle est toujours en jeu (x et y != -1)
		    for ( PieceIHM pieceIHM : list){
			    if ((pieceIHM.getTypePiece()).equals(piece.getClass().getSimpleName())){
				    existe = true;
				    if (piece.getX() != -1){
				    	pieceIHM.add(new Coord(piece.getX(), piece.getY()));
				    }
			    }
		    }
		    // sinon, création d'une nouvelle PieceIHM si la pièce est toujours en jeu
		    if (! existe) {
			    if (piece.getX() != -1){
				    newPieceIHM = new PieceIHM(piece.getClass().getSimpleName(),
				    piece.getCouleur());
				    newPieceIHM.add(new Coord(piece.getX(), piece.getY()));
				    list.add(newPieceIHM);
			    }
		    }
	    }
	    return list;
    }
    
    public void setCastling(){
	//TODO
    }
    /* A ne pas faire tout de suite

    public void undoMove(){
	//TODO
	}
    
    public void undoCapture(){
	//TODO
	}

     */
    
    /**
     * Cette fonction permet de changer un pion en reine si il 
     * atteint la dernière ligne de l'échiquier.
     * @param xFinal
     * @param yFinal
     * @return true si la pièce peux évoluer, sinon false.
     */
    public boolean isPawnPromotion(int xFinal,int yFinal){
    	boolean retour = false;
    	Pieces piece = findPiece(xFinal,yFinal);
    	
    	int y = piece.getY();
    	if (piece.getCouleur() == Couleur.BLANC){
    		retour = (y == 0);
    	}else{
    		retour = (y == 7);
    	}
    	
		return retour;
    }
    
    /**
     * Cette fonction permet de changer un pion en reine si il
     * est sur la dernière ligne de l'échiquier. 
     * @param xFinal
     * @param yFinal
     * @param type
     * @return
     */
    //supp pion && add reine
    public boolean pawnPromotion(int xFinal,int yFinal,java.lang.String type){
    	boolean retour = false;
		//TODO
		return retour;
    }
    
    /**
     * Cette fonction parcours toutes les pièces d'un joueur et 
     * retourne les coordonnées de son roi.
     * @return Coord du Roi
     */
    public Coord getKingCoord(){
    	Coord retour = null;
    	
    	for (int i = 0; i < pieces.size(); i++){
    		if (pieces.get(i).getClass().getSimpleName().equals("Roi")){
    			retour = new Coord(pieces.get(i).getX(),pieces.get(i).getY());
    			break;
    		}else{
    			retour = null;
    		}
    	}
    	return retour;
    }
    
    public static void main(String[] args) {
    	//Jeu j = new Jeu(Couleur.BLANC);
    	
    	/// TEST GET_PIECE_COLOR/TYPE GET_COULEUR
    	/*
		System.out.println(j);
		System.out.println(j.findPiece(1, 7));
		System.out.println(j.isMoveOk(1, 7, 2, 5));
		System.out.println(j.move(1, 7, 2, 5));
		System.out.println(j.findPiece(1, 7));
		System.out.println(j.findPiece(2, 5));
		System.out.println("couleur piece :"+j.getPieceColor(2, 5));
		System.out.println("type piece :"+j.getPieceType(2, 5));
		System.out.println("couleur jeu :"+j.getCouleur());
		*/
		
    	/// TEST PAWN_PROMOTION
    	/*
		System.out.println(j.move(0, 6, 0, 5));
		System.out.println(j.move(0, 5, 0, 4));
		System.out.println(j.move(0, 4, 0, 3));
		System.out.println(j.move(0, 3, 0, 2));
		System.out.println(j.move(0, 2, 0, 1));
		System.out.println(j.move(0, 1, 0, 0));
		System.out.print("Promotion : "+j.isPawnPromotion(0, 0));
		*/
    	
    	
    	/// TEST J.GET_KING_COORD()
    	/*
		System.out.println(j.move(4, 6, 4, 4));
		System.out.println("type piece :"+j.getPieceType(4, 4));
		System.out.println("type piece :"+j.getPieceType(4, 6));
		System.out.println("type piece :"+j.getPieceType(4, 7));
		System.out.println(j.move(4, 7, 4, 6));
    	System.out.print("Coord Roi : " + j.getKingCoord());
    	*/
    	
    	
		
	}
    
    
}
