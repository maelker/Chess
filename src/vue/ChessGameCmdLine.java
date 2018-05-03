package vue;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import model.Coord;
import model.Couleur;
import model.PieceIHM;
import controler.controlerLocal.ChessGameControler;



/**
 * @author francoise.perrin
 * Inspiration Jacques SARAYDARYAN, Adrien GUENARD *
 * 
 * Vue console d'un jeu d'échec
 * Cette classe est un observateur et le damier est mis à jour à chaque changement dans la classe métier
 */
public class ChessGameCmdLine implements Observer{

	private ChessGameControler chessGameControler;

	public   ChessGameCmdLine(ChessGameControler chessGameControler) {
		this.chessGameControler = chessGameControler;
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 * 
	 * Lorsque l'échiquier est MAJ, cette méthode est invoquée
	 * elle récupère la liste de PieceIHM fabriquée par l'échiquier
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		
		System.out.println(chessGameControler.getMessage() + "\n");	

		
		List<PieceIHM> piecesIHM = null;
		try{
			piecesIHM = (List<PieceIHM>) arg1;
		} catch(ClassCastException e){
		    System.out.println("L'objet n'est pas integre");
		}
		
		String[][] damier = new String[8][8];
		
		// création d'un tableau 2D avec les noms des pièces
		for(PieceIHM pieceIHM : piecesIHM) {

			Couleur color = pieceIHM.getCouleur();
			String stColor = (Couleur.BLANC == color ? "B_" : "N_" );
			String type = (pieceIHM.getTypePiece()).substring(0, 2);
			
			for(Coord coord : pieceIHM.getList()) {
				damier[coord.y][coord.x] = stColor + type;
			}			
		}
		
		// Affichage du tableau formatté
		String st = "    0     1     2     3     4     5    6     7 \n";
		for ( int i = 0; i < 8; i++) {
			st += i + " ";
			for ( int j = 0; j < 8; j++) {				 
				String nomPiece = damier[i][j];				
				if (nomPiece != null) {						
					st += nomPiece + "  ";	
				} 
				else {
					st += "____  ";
				}
			}
			st +="\n";
		}
		
		System.out.println(st);
	}

	public void go() {

		System.out.print("\n Déplacement de 2,6 vers 2,4 = ");
		chessGameControler.move(new Coord(2,6), new Coord(2, 4));	// true
		
		// dans ce cas, update non appelé et pas d'affichage 
		// controleur empêche le move car pas le bon joueur
		System.out.print("\n Déplacement de 2,1 vers 2,3 = ");
		chessGameControler.move(new Coord(2,1), new Coord(2,3));	// true

		System.out.print("\n Déplacement de 1,7 vers 2,5 = ");
		chessGameControler.move(new Coord(1,7), new Coord(2,5));	// true

		System.out.print("\n Déplacement de 3,1 vers 3,3 = ");
		chessGameControler.move(new Coord(3, 1), new Coord(3, 3));	// true
		
		System.out.print("\n Déplacement de 2,1 vers 5,3 = ");
		chessGameControler.move(new Coord(2, 1), new Coord(5, 3));	// false

		System.out.print("\n Déplacement de 2,7 vers 4,5 = ");
		chessGameControler.move(new Coord(2, 7), new Coord(4, 5));	// true
		
		System.out.print("\n Déplacement de 2,0 vers 5,3 = ");
		chessGameControler.move(new Coord(2, 0), new Coord(5, 3));	// true
		
		System.out.print("\n Déplacement de 4,5 vers 5,5 = ");
		chessGameControler.move(new Coord(4, 5), new Coord(5, 5));	// false
		
		System.out.print("\n Déplacement de 5,5 vers 6,5 = ");
		chessGameControler.move(new Coord(5, 5), new Coord(6, 5));	// false
		
		System.out.print("\n Déplacement de 2,4 vers 3,3 = ");
		chessGameControler.move(new Coord(2, 4), new Coord(3, 3));	// true
		
		System.out.print("\n Déplacement de 5,3 vers 1,7 = ");
		chessGameControler.move(new Coord(5, 3), new Coord(1, 7));	// true
		
		System.out.print("\n Déplacement de 3,3 vers 3,2 = ");
		chessGameControler.move(new Coord(3, 3), new Coord(3, 2));	// true
		
		System.out.print("\n Déplacement de 0,1 vers 1,2 = ");
		chessGameControler.move(new Coord(0, 1), new Coord(1, 2));	// true
	}
	
}
