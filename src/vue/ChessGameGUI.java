package vue;

import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import model.observable.ChessGame;
import controler.ChessGameControlers;
import controler.controlerLocal.ChessGameControler;
import tools.ChessPieceImage;
import tools.ChessPiecePos;
	
public class ChessGameGUI extends JFrame implements MouseListener, MouseMotionListener, Observer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6873083739027006683L;
	JLayeredPane layeredPane;
	  JPanel chessBoard;
	  JLabel chessPiece;
	  
	  int xAdjustment;
	  int yAdjustment;
	  
	  public ChessGameGUI(String string, ChessGameControlers chessGameControler, Dimension dim){
		  Dimension boardSize = dim;
		  
//  Use a Layered Pane for this this application
		  layeredPane = new JLayeredPane();
		  getContentPane().add(layeredPane);
		  layeredPane.setPreferredSize(boardSize);
		  layeredPane.addMouseListener(this);
		  layeredPane.addMouseMotionListener(this);
		  
//Add a chess board to the Layered Pane 
		  
		  chessBoard = new JPanel();
		  layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
		  chessBoard.setLayout( new GridLayout(8, 8) );
		  chessBoard.setPreferredSize( boardSize );
		  chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);
		  
		  for (int i = 0; i < 8; i++) {
			  for (int j = 0; j < 8; j++) {
				  JPanel square = new JPanel( new BorderLayout() );
				  chessBoard.add( square );
				  int row = ((i*8)+j) % 2; // i correspond to lines and j to columns
				  if (row == 0)
					  square.setBackground( i % 2 == 0 ? Color.white : Color.black );
				  else
					  square.setBackground( i % 2 == 0 ? Color.black : Color.white );
			  }
		  }
		  
//Add pieces to the board
		  
		  JLabel piece;
		  JPanel panel = null;
		  for (int i = 0; i < ChessPieceImage.values().length; i++) {
				for (int j = 0; j < (ChessPiecePos.values()[i].coords).length; j++) {
					piece = new JLabel( new ImageIcon("/fs03/share/users/mael.kerloch/home/workspace/Echiquier/images/"+
							ChessPieceImage.values()[i].imageFile) );
					panel = (JPanel)chessBoard.getComponent((ChessPiecePos.values()[i].coords[j].y*8)+ChessPiecePos.values()[i].coords[j].x);
					panel.add(piece);
				}
		  }
	  }
	  
	  public void mousePressed(MouseEvent e){
		  chessPiece = null;
		  Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
		  System.out.print(c);
		  if (c instanceof JPanel) 
			  return;
		  
		  Point parentLocation = c.getParent().getLocation();
		  xAdjustment = parentLocation.x - e.getX();
		  yAdjustment = parentLocation.y - e.getY();
		  chessPiece = (JLabel)c;
		  chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
	      chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
	  	  layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
	  }
	  
//Move the chess piece around

	  public void mouseDragged(MouseEvent me) {
		  if (chessPiece == null) return;
		  chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
	  }

//Drop the chess piece back onto the chess board
	 
	  public void mouseReleased(MouseEvent e) {
		  if(chessPiece == null) return;
		  
		  chessPiece.setVisible(false);
		  Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
		 
		  if (c instanceof JLabel){
			  Container parent = c.getParent();
			  parent.remove(0);
			  parent.add( chessPiece );
		  }
		  else {
			  Container parent = (Container)c;
			  parent.add( chessPiece );
		  }
		  
		  chessPiece.setVisible(true);
	  }
	  
	  public void mouseClicked(MouseEvent e) {
	  }
	  public void mouseMoved(MouseEvent e) {
	  }
	  public void mouseEntered(MouseEvent e){
	  }
	  public void mouseExited(MouseEvent e) {
	  }
	  
	  public static void main(String[] args) {

		  ChessGame chessGame;
		  ChessGameControlers chessGameControler;
		  chessGame = new ChessGame();	
		  chessGameControler = new ChessGameControler(chessGame);
		  Dimension dim;
		  dim = new Dimension(700, 700);
		  JFrame frame = new ChessGameGUI("Jeu d'échec", chessGameControler,  dim);
		  frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
		  frame.pack();
		  frame.setResizable(true);
		  frame.setLocationRelativeTo( null );
		  frame.setVisible(true);
	  }

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
