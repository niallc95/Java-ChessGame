import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

 
public class ChessProject extends JFrame implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    int xAdjustment;
    int yAdjustment;
	int startX;
	int startY;
	int initialX;
	int initialY;
	int landingY; 
	int landingX; 
	String turn; 
	JPanel panels;
	JLabel pieces;
	
 
    public ChessProject(){
        Dimension boardSize = new Dimension(600, 600);

        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

/*****************************************************************************************************************************************/
/* CREATE CHESSBOARD*********************************************************************************************************************/	
/*****************************************************************************************************************************************/
        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout( new GridLayout(8, 8) );
        chessBoard.setPreferredSize( boardSize );
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);
 
        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel( new BorderLayout() );
            chessBoard.add( square );
 
            int row = (i / 8) % 2;
            if (row == 0)
                square.setBackground( i % 2 == 0 ? Color.gray : Color.red );
            else
                square.setBackground( i % 2 == 0 ? Color.red : Color.gray );
        }
 
/*****************************************************************************************************************************************/
/* PIECE SET UP **************************************************************************************************************************/	
/*****************************************************************************************************************************************/
		for(int i=8;i < 16; i++){			
       		pieces = new JLabel( new ImageIcon("WhitePawn.png") );
			panels = (JPanel)chessBoard.getComponent(i);
	        panels.add(pieces);	        
		}
		pieces = new JLabel( new ImageIcon("WhiteRook.png") );
		panels = (JPanel)chessBoard.getComponent(0);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
		panels = (JPanel)chessBoard.getComponent(1);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
		panels = (JPanel)chessBoard.getComponent(6);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteBishop.png") );
		panels = (JPanel)chessBoard.getComponent(2);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteBishop.png") );
		panels = (JPanel)chessBoard.getComponent(5);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKing.png") );
		panels = (JPanel)chessBoard.getComponent(3);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
		panels = (JPanel)chessBoard.getComponent(4);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteRook.png") );
		panels = (JPanel)chessBoard.getComponent(7);
	    panels.add(pieces);
		for(int i=48;i < 56; i++){			
       		pieces = new JLabel( new ImageIcon("BlackPawn.png") );
			panels = (JPanel)chessBoard.getComponent(i);
	        panels.add(pieces);	        
		}
		pieces = new JLabel( new ImageIcon("BlackRook.png") );
		panels = (JPanel)chessBoard.getComponent(56);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKnight.png") );
		panels = (JPanel)chessBoard.getComponent(57);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKnight.png") );
		panels = (JPanel)chessBoard.getComponent(62);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackBishop.png") );
		panels = (JPanel)chessBoard.getComponent(58);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackBishop.png") );
		panels = (JPanel)chessBoard.getComponent(61);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKing.png") );
		panels = (JPanel)chessBoard.getComponent(59);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackQueen.png") );
		panels = (JPanel)chessBoard.getComponent(60);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackRook.png") );
		panels = (JPanel)chessBoard.getComponent(63);
	    panels.add(pieces);		
    }

/*****************************************************************************************************************************************/
/* PIECE PRESENT**************************************************************************************************************************/	
/*****************************************************************************************************************************************/
	private Boolean piecePresent(int x, int y){
		Component c = chessBoard.findComponentAt(x, y);
		if(c instanceof JPanel){
			return false;
		}
		else{
			return true;
		}
	}
	
/*****************************************************************************************************************************************/
/* CHECK PIECES BY COLOR *****************************************************************************************************************/	
/*****************************************************************************************************************************************/
	private Boolean checkWhiteOpponent(int newX, int newY){
		Boolean opponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();			
		if(((tmp1.contains("Black")))){
			opponent = true;
			if(((tmp1.contains("King")))){
				JOptionPane.showMessageDialog(null,"Whites win!!");
				System.exit(0);
			}
		}
		else{
			opponent = false; 
		}		
		return opponent;
	}	
	
	private Boolean checkBlackOpponent(int newX, int newY){
		Boolean opponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();			
		if(((tmp1.contains("White")))){
			opponent = true;
			if(((tmp1.contains("King")))){
				JOptionPane.showMessageDialog(null,"Blacks win!!");
				System.exit(0);
			}
		}
		else{
			opponent = false; 
		}		
		return opponent;
	}
 
/*****************************************************************************************************************************************/
/* MOUSE CLICK ON PIECE ******************************************************************************************************************/	
/*****************************************************************************************************************************************/
    public void mousePressed(MouseEvent e){
        chessPiece = null;
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
        if (c instanceof JPanel) 
			return;
 
        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        chessPiece = (JLabel)c;
		initialX = e.getX();
		initialY = e.getY();
		startX = (e.getX()/75);
		startY = (e.getY()/75);
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
    }
   
    public void mouseDragged(MouseEvent me) {
        if (chessPiece == null) return;
         chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
     }
     
/*****************************************************************************************************************************************/
/* DROP PIECE ****************************************************************************************************************************/	
/*****************************************************************************************************************************************/
    public void mouseReleased(MouseEvent e) {
        if(chessPiece == null) return;
 
        chessPiece.setVisible(false);
		Boolean success =false;
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
		String tmp = chessPiece.getIcon().toString();
		String pieceName = tmp.substring(0, (tmp.length()-4));
		Boolean validMove = false;
		landingX = e.getX()/75; 
		landingY = e.getY()/75; 
		Boolean progressionB = false; 
		Boolean progressionW = false; 

		
/*****************************************************************************************************************************************/
/* WHITE PAWN ****************************************************************************************************************************/	
/*****************************************************************************************************************************************/	
	if(pieceName.equals("WhitePawn")){
			if((startY == 1)&&(startX == landingX)&&(((landingY-startY)==1)||(landingY-startY)==2)){
			//Check if a piece is in the way
				if(!piecePresent(e.getX(), e.getY())){ 
					validMove = true; 
				}else{ 
					validMove = false; 
				}
			}
			else if((Math.abs(landingX-startX)==1)&&(((landingY-startY)==1))){
				if(piecePresent(e.getX(),e.getY())){ 
					if(checkWhiteOpponent(e.getX(),e.getY())){
						validMove = true; 
					if(landingY == 7){ 
						progressionW = true; 
					}
					
				}else{ 
					validMove = false; 
				}
				}else{ 
					validMove = false; 
				}
			}
		
			else if((startY != 1)&&((startX == landingX)&&(((landingY-startY)==1)))){ 
			//If there is a piece in the way
			if(!piecePresent(e.getX(),e.getY())){ 
				validMove = true; 
				if(landingY == 7){ 
					progressionW = true;
				}
			}
			else{ 
				validMove = false; 
			}
		}
		else{ 
			validMove = false; 
		}
	}
/*****************************************************************************************************************************************/
/* BLACK PAWN ****************************************************************************************************************************/	
/*****************************************************************************************************************************************/	
		else if(pieceName.equals("BlackPawn")){
			if((startY == 6)&&(startX == landingX)&&(((startY-landingY)==1)||(startY-landingY)==2)){
			//Check if a piece is in the way
				if(!piecePresent(e.getX(), e.getY())){ 
					validMove = true; 
				}else{ 
					validMove = false; 
				}
			}
			else if((Math.abs(startX-landingX)==1)&&(((startY-landingY)==1))){
				if(piecePresent(e.getX(),e.getY())){ 
					if(checkBlackOpponent(e.getX(),e.getY())){
						validMove = true; 
					if(landingY == 0){ 
						progressionB = true; 
					}
					
				}else{ 
					validMove = false; 
				}
				}else{ 
					validMove = false; 
				}
			}
		
			else if((startY != 6)&&((startX == landingX)&&(((startY-landingY)==1)))){ 
			//If there is a piece in the way
			if(!piecePresent(e.getX(),e.getY())){ 
				validMove = true; 
				if(landingY == 0){ 
					progressionB = true;
				}
			}
			else{ 
				validMove = false; 
			}
		}
		else{ 
			validMove = false; 
		}
	}
/*****************************************************************************************************************************************/
/* KNIGHT ********************************************************************************************************************************/	
/*****************************************************************************************************************************************/	
	else if(pieceName.contains("Knight")){ 
		if(((landingX < 0)||(landingX > 7))||((landingY < 0)||landingY >7)){
			validMove = false; 
		}
		else{ 
			if(((landingX == startX+1)&&(landingY == startY+2))||((landingX == startX-1)&&(landingY ==startY+2))||((landingX == startX+2)
			&& (landingY == startY +1))||((landingX == startX-2)&&(landingY == startY+1))||((landingX == startX+1)&& (landingY == startY-2))
			||((landingX == startX-1)&&(landingY == startY-2))||((landingX == startX+2)&&(landingY == startY-1))||((landingX == startX-2)
			&&(landingY == startY-1))){
				if(piecePresent(e.getX(),(e.getY()))){
					if(pieceName.contains("White")){ 
						if(checkWhiteOpponent(e.getX(),e.getY())){
							validMove = true; 
				
						}
					else{ 
						validMove = false; 
					}
				}
				else{ 
					if(checkBlackOpponent(e.getX(), e.getY())){ 
						validMove = true; 
					}
					else{ 
						validMove = false; 
					}
				}
			}
			else{ 
				validMove = true; 
			}
		}
		else{ 
			validMove = false; 
		}
	}
}
/*****************************************************************************************************************************************/
/*BISHOP ********************************************************************************************************************************/	
/*****************************************************************************************************************************************/
else if(pieceName.contains("Bishop")){ 
	Boolean inTheWay = false; 
	int distance = Math.abs(startX-landingX); 
	if(((landingX < 0) || (landingX > 7))||((landingY < 0)||(landingY > 7))){
		validMove = false; 
	}
	else{ 
		validMove = true; 
		if(Math.abs(startX-landingX)==Math.abs(startY-landingY)){
			if((startX-landingX < 0)&&(startY-landingY < 0)){ 
				for(int i=0; i < distance; i++){ 
					if(piecePresent((initialX+(i*75)), (initialY+(i*75)))){
						inTheWay = true; 
					}
				}
			}
			else if((startX-landingX < 0)&&(startY-landingY > 0)){
				for(int i=0; i < distance; i++){ 
					if(piecePresent((initialX+(i*75)), (initialY-(i*75)))){
						inTheWay = true; 
					}
				}
			}
			
			else if((startX-landingX > 0)&&(startY-landingY > 0)){ 
				for(int i=0; i < distance; i++){ 
					if(piecePresent((initialX-(i*75)), (initialY-(i*75)))){
						inTheWay = true; 
					}
				}
			}
			else if((startX-landingX > 0)&&(startY-landingY < 0)){
				for(int i=0; i < distance; i++){ 
					if(piecePresent((initialX-(i*75)), (initialY+(i*75)))){
						inTheWay = true; 
					}
				}
			}
			
			if(inTheWay){ 
				validMove = false; 
			}
			
			else{ 
				if(piecePresent(e.getX(), (e.getY()))){
					if(pieceName.contains("White")){ 
						if(checkWhiteOpponent(e.getX(), e.getY())){ 
							validMove = true; 
						}
						else{ 
							validMove = false; 
						}
					}
					else{ 
						if(checkBlackOpponent(e.getX(), e.getY())){
							validMove = true; 
						}
						else{ 
							validMove = false; 
						}
					}
				}
				else{ 
					validMove = true; 
				}
			}
		}
		else{ 
			validMove = false; 
		}
	}
}
/*****************************************************************************************************************************************/
/* ROOK***********************************************************************************************************************************/	
/*****************************************************************************************************************************************/	
else if(pieceName.contains("Rook")){ 
	Boolean inTheWay = false; 
	if(((landingX < 0) || (landingX > 7))||((landingY < 0)||(landingY > 7))){
		validMove = false;
	}
	else{ 
		if(((Math.abs(startX-landingX)!=0)&&(Math.abs(startY-landingY)== 0))||((Math.abs(startX-landingX)==0)&&(Math.abs(landingY-startY)!=0))){ 
			if(Math.abs(startX-landingX)!=0){
				int xMovement = Math.abs(startX-landingX);
				if(startX-landingX > 0){
					for(int i=0; i < xMovement;i++){
						if(piecePresent(initialX-(i*75), e.getY())){
							inTheWay = true; 
							break; 
						}else{ 
							inTheWay = false; 
						}
					}
				}else{ 
					for(int i=0; i < xMovement; i++){ 
						if(piecePresent(initialX+(i*75),e.getY())){
							inTheWay = true; 
							break; 
						}else{ 
							inTheWay = false; 
						}
					}
				}
			}else{ 
				int yMovement = Math.abs(startY-landingY);
				if(startY-landingY > 0){ 
					for(int i=0; i < yMovement; i++){
						if(piecePresent(e.getX(),initialY-(i*75))){
							inTheWay = true;
							break; 
						}else{ 
							inTheWay = false; 
						}
					}
				}else{ 
					for(int i=0; i < yMovement; i++){
						if(piecePresent(e.getX(),initialY+(i*75))){
							inTheWay = true; 
							break; 
						}else{ 
							inTheWay = false; 
						}
					}
				}
			}
			
			if(inTheWay){ 
				validMove = false; 
				
			}else{ 
				if(piecePresent(e.getX(), (e.getY()))){
					if(pieceName.contains("White")){ 
						if(checkWhiteOpponent(e.getX(), e.getY())){ 
							validMove = true; 
						}
						else{ 
							validMove = false; 
						}
					}
					else{ 
						if(checkBlackOpponent(e.getX(), e.getY())){
							validMove = true; 
						}
						else{ 
							validMove = false; 
						}
					}
				}
				else{ 
					validMove = true; 
				}
			}
		}
		else{ 
			validMove = false; 
		}
	}
}
/*****************************************************************************************************************************************/
/* QUEEN**********************************************************************************************************************************/	
/*****************************************************************************************************************************************/	
else if(pieceName.contains("Queen")){ 
	Boolean inTheWay = false; 
	int distance = Math.abs(startX-landingX); 
	if(((landingX < 0) || (landingX > 7))||((landingY < 0)||(landingY > 7))){
		validMove = false;
	}
	else{ 
		validMove = true; 
		
		if(((Math.abs(startX-landingX)!=0)&&(Math.abs(startY-landingY)== 0))||((Math.abs(startX-landingX)==0)&&(Math.abs(landingY-startY)!=0))
		||Math.abs(startX-landingX)==Math.abs(startY-landingY)){ 
			
			if(Math.abs(startX-landingX)!=0){
				int xMovement = Math.abs(startX-landingX);
				//Valid moves
				if(startX-landingX > 0){
					for(int i=0; i < xMovement;i++){
						if(piecePresent(initialX-(i*75), e.getY())){
							inTheWay = true; 
							break; 
						}else{ 
							inTheWay = false; 
						}
					}
				}else if((startX-landingX < 0)&&(startY-landingY > 0)){
					for(int i=0; i < distance; i++){ 
						if(piecePresent((initialX+(i*75)), (initialY-(i*75)))){
							inTheWay = true; 
						}
					}
				}else if((startX-landingX < 0)&&(startY-landingY < 0)){ 
					for(int i=0; i < distance; i++){ 
						if(piecePresent((initialX+(i*75)), (initialY+(i*75)))){
							inTheWay = true; 
						}
					}
				}else if((startX-landingX > 0)&&(startY-landingY > 0)){ 
					for(int i=0; i < distance; i++){ 
						if(piecePresent((initialX-(i*75)), (initialY-(i*75)))){
							inTheWay = true; 
						}
					}
				}else if((startX-landingX > 0)&&(startY-landingY < 0)){
					for(int i=0; i < distance; i++){ 
						if(piecePresent((initialX-(i*75)), (initialY+(i*75)))){
							inTheWay = true; 
						}
					}
				}else{ 
					for(int i=0; i < xMovement; i++){ 
						if(piecePresent(initialX+(i*75),e.getY())){
							inTheWay = true; 
							break; 
						}else{ 
							inTheWay = false; 
						}
					}
				}
			}else{ 
				int yMovement = Math.abs(startY-landingY);
				if(startY-landingY > 0){ 
					for(int i=0; i < yMovement; i++){
						if(piecePresent(e.getX(),initialY-(i*75))){
							inTheWay = true;
							break; 
						}else{ 
							inTheWay = false; 
						}
					}
				}else{ 
					for(int i=0; i < yMovement; i++){
						if(piecePresent(e.getX(),initialY+(i*75))){
							inTheWay = true; 
							break; 
						}else{ 
							inTheWay = false; 
						}
					}
				}
			}
			
			if(inTheWay){ 
				validMove = false; 
				
			}else{ 
				if(piecePresent(e.getX(), (e.getY()))){
					if(pieceName.contains("White")){ 
						if(checkWhiteOpponent(e.getX(), e.getY())){ 
							validMove = true; 
						}
						else{ 
							validMove = false; 
						}
					}
					else{ 
						if(checkBlackOpponent(e.getX(), e.getY())){
							validMove = true; 
						}
						else{ 
							validMove = false; 
						}
					}
				}
				else{ 
					validMove = true; 
				}
			}
		}
		else{ 
			validMove = false; 
		}
	}
}
/*****************************************************************************************************************************************/
/*KING************************************************************************************************************************************/	
/*****************************************************************************************************************************************/	
else if(pieceName.contains("King")){
	Boolean inTheWay = false; 
	int distance = Math.abs(startX-landingX);
	
	if(((landingX < 0) || (landingX > 7	))||((landingY < 0)||(landingY > 7))){
		validMove = false;
	}
	
	else{ 
		if(Math.abs(startX-landingX)<2 && Math.abs(startY-landingY)<2){
			if((startX-landingX < 0)&&(startY-landingY < 0)){
				for(int i=0; i < distance; i++){
					if(piecePresent((initialX+(i*75)),initialY+(i*75))){
						inTheWay=true;
					}
				}
			}
					
			else if((startX-landingX < 0)&&(startY-landingY > 0)){
				for(int i=0; i < distance; i++){
					if(piecePresent((initialX+(i*75)),initialY-(i*75))){
						inTheWay=true;
					}
				}
			}
			
			else if((startX-landingX > 0)&&(startY-landingY > 0)){
				for(int i=0; i <distance; i++){
					if(piecePresent((initialX-(i*75)),initialY-(i*75))){
						inTheWay=true;
					}
				}
			}
					
			else if((startX-landingX > 0)&&(startY-landingY <0 )){
				for(int i=0; i <distance; i++){
					if(piecePresent((initialX-(i*75)),initialY+(i*75))){
						inTheWay=true;
					}
				}
			}

			if(inTheWay){
				validMove=false;
			}else{
				if(piecePresent(e.getX(),e.getY())){
					if(pieceName.contains("White")){
						if(checkWhiteOpponent(e.getX(),e.getY())){
							validMove=true;
						}else{
							validMove=false;
						}
					}else{
						if(checkBlackOpponent(e.getX(),e.getY())){
							validMove=true;
						}else{
							validMove=false;
						}
					}
				}else{
					validMove=true;
				}
			}
		}			
		 
		else if(((Math.abs(startX-landingX)<2)&&(Math.abs(startY-landingY)== 0))||((Math.abs(startX-landingX)==0)&&(Math.abs(landingY-startY)<2))){ 
			if(Math.abs(startX-landingX)<2){
				int xMovement = Math.abs(startX-landingX);
				if(startX-landingX > 0){
					for(int i=0; i < xMovement;i++){
						if(piecePresent(initialX-(i*75), e.getY())){
							inTheWay = true; 
							break; 
						}else{ 
							inTheWay = false; 
						}
					}
				}else{ 
					for(int i=0; i < xMovement; i++){ 
						if(piecePresent(initialX+(i*75),e.getY())){
							inTheWay = true; 
							break; 
						}else{ 
							inTheWay = false; 
						}
					}
				}
			}else{ 
				int yMovement = Math.abs(startY-landingY);
				if(startY-landingY > 0){ 
					for(int i=0; i < yMovement; i++){
						if(piecePresent(e.getX(),initialY-(i*75))){
							inTheWay = true;
							break; 
						}else{ 
							inTheWay = false; 
						}
					}
				}else{ 
					for(int i=0; i < yMovement; i++){
						if(piecePresent(e.getX(),initialY+(i*75))){
							inTheWay = true; 
							break; 
						}else{ 
							inTheWay = false; 
						}
					}
				}
			}

			
			if(inTheWay){ 
				validMove = false; 
				
			}else{ 
				if(piecePresent(e.getX(), (e.getY()))){
					if(pieceName.contains("White")){ 
						if(checkWhiteOpponent(e.getX(), e.getY())){ 
							validMove = true; 
						}
						else{ 
							validMove = false; 
						}
					}
					else{ 
						if(checkBlackOpponent(e.getX(), e.getY())){
							validMove = true; 
						}
						else{ 
							validMove = false; 
						}
					}
				}
				else{ 
					validMove = true; 
				}
			}
		}
		else{ 
			validMove = false; 
		}
	}
}
/*****************************************************************************************************************************************/
/* END OF PIECE MOVEMENTS ****************************************************************************************************************/	
/*****************************************************************************************************************************************/	

/*****************************************************************************************************************************************/
/* CHANGE PAWN TO QUEEN ON LAST ROW ******************************************************************************************************/	
/*****************************************************************************************************************************************/
		if(!validMove){		
			int location=0;
			if(startY ==0){
				location = startX;
			}
			else{
				location  = (startY*8)+startX;
			}
			String pieceLocation = pieceName+".png"; 
			pieces = new JLabel( new ImageIcon(pieceLocation) );
			panels = (JPanel)chessBoard.getComponent(location);
		    panels.add(pieces);			
		}
		else{
			//Black pawn 
			if(progressionB){ 
				int location = 0 + (e.getX()/75); 
				if(c instanceof JLabel){
					Container parent = c.getParent(); 
					parent.remove(0); 
					pieces = new JLabel( new ImageIcon("BlackQueen.png"));
					parent = (JPanel)chessBoard.getComponent(location);
			    	parent.add(pieces);	
					
				}
			}
			//White pawn
			else if(progressionW){
				int location = 56 + (e.getX()/75);
				if (c instanceof JLabel){
	            	Container parent = c.getParent();
	            	parent.remove(0);
					pieces = new JLabel( new ImageIcon("WhiteQueen.png"));
					parent = (JPanel)chessBoard.getComponent(location);
			    	parent.add(pieces);			
				}
			}
			else{
				if (c instanceof JLabel){
	            	Container parent = c.getParent();
	            	parent.remove(0);
	            	parent.add(chessPiece);
	        	}
	        	else {
	            	Container parent = (Container)c;
	            	parent.add( chessPiece );
	        	}
	    		chessPiece.setVisible(true);									
			}
		}
    }
 
    public void mouseClicked(MouseEvent e) {
	
    }
    public void mouseMoved(MouseEvent e) {
   }
    public void mouseEntered(MouseEvent e){
	
    }
    public void mouseExited(MouseEvent e) {
	
    }
 	
/*****************************************************************************************************************************************/
/* MAIN METHOD ****************************************************************************************************************************/	
/*****************************************************************************************************************************************/
    public static void main(String[] args) {
        JFrame frame = new ChessProject();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);
     }
}
