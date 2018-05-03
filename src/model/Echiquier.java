package model;


import model.Jeu;

import java.util.List;

public class Echiquier implements BoardGames {
	
	Jeu jeuBlanc = new Jeu(Couleur.BLANC);
	Jeu jeuNoir = new Jeu(Couleur.NOIR);
	// De base on place le jeu courant sur le jeu Blanc.
	Jeu jeuCourant = jeuBlanc;
	
	private String message = "";
	
	public Echiquier(){
		super();
	}
	
	public List<PieceIHM> getPiecesIHM() {
		List<PieceIHM> retour = jeuBlanc.getPiecesIHM();
		for (PieceIHM piece : jeuNoir.getPiecesIHM()){
			retour.add(piece);
		}
		
		return retour;
	}

	public String getMessage() {
		return message;
	}
	
	private void setMessage(String message) {
		this.message = message;
	}
	
	
	// Condition de capture
	// Switch joueur color pour voir si pièce adverse sur coord(xfinal,yfinal)
	public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal) {
		boolean retour = false;
		if (jeuCourant.isMoveOk(xInit, yInit, xFinal, yFinal)) {
			retour = true;
			setMessage("OK : Déplacement sans capture");
		} else {
			retour = false;
			setMessage("KO : Déplacement impossible");
		}
		
		return retour;
	}

	public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
		boolean retour = false;
		if(jeuCourant.isMoveOk(xInit, yInit, xFinal, yFinal)){
			jeuCourant.move(xInit, yInit, xFinal, yFinal);
			retour = true;
		}
		return retour;
	}

	@Override
	public String toString() {
		//TODO
		return jeuBlanc+"\n"+jeuNoir+"\n";
	}

	public void switchJoueur() {
		if (jeuCourant.getCouleur().equals(Couleur.BLANC)){
			jeuCourant = jeuNoir;
		}else{
			jeuCourant = jeuBlanc;
		}
	}
	
	public boolean isEnd() {
		// TODO Auto-generated method stub
		return false;
	}

	public Couleur getColorCurrentPlayer() {
		return jeuCourant.getCouleur();
	}

	public Couleur getPieceColor(int x, int y) {
		Couleur retour = null;
		
		if (jeuBlanc.getPieceColor(x, y) == (null) && jeuNoir.getPieceColor(x, y) == (null)){
			retour = null;
		} else if (jeuBlanc.getPieceColor(x, y) == Couleur.BLANC) {
			retour = Couleur.BLANC;
		} else {

			retour = Couleur.NOIR;
		}
		return retour;
	}
	
	public static void main(String[] args) {
		
		//Echiquier ech = new Echiquier();
		// TEST GET_PIECE_COLOR
		/*
		System.out.print(ech.getPieceColor(0, 0)+"\n");// Devrait afficher noir
		System.out.print(ech.getPieceColor(7, 7)+"\n");// Devrait afficher blanc
		*/
		
		// TEST TO_STRING
		/*
		System.out.print(ech);
		*/
		
		
	}
	
}
