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
	boolean progression;
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
                square.setBackground( i % 2 == 0 ? Color.white : Color.gray );
            else
                square.setBackground( i % 2 == 0 ? Color.gray : Color.white );
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
	private Boolean checkWhiteOponent(int newX, int newY){
		Boolean oponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();			
		if(((tmp1.contains("Black")))){
			oponent = true;
		}
		else{
			oponent = false; 
		}		
		return oponent;
	}	
	
	private Boolean checkBlackOponent(int newX, int newY){
		Boolean oponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();			
		if(((tmp1.contains("White")))){
			oponent = true;
		}
		else{
			oponent = false; 
		}		
		return oponent;
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
		Boolean progression = false; 

		
/*****************************************************************************************************************************************/
/* WHITE PAWN ****************************************************************************************************************************/	
/*****************************************************************************************************************************************/	
		if(pieceName.equals("WhitePawn")){
			if(startY == 1)
			{
				if((startX == (e.getX()/75))&&((((e.getY()/75)-startY)==1)||((e.getY()/75)-startY)==2))
				{
					if((((e.getY()/75)-startY)==2)){
						if((!piecePresent(e.getX(), (e.getY())))&&(!piecePresent(e.getX(), (e.getY()+75)))){
							validMove = true;					
						}
						else{
							validMove = false;
						}							
					}
					else{
						if((!piecePresent(e.getX(), (e.getY()))))
						{
							validMove = true;					
						}	
						else{
							validMove = false;
						}													
					}
				}
				else{
					validMove = false;					
				}
			}
			else{
				int newY = e.getY()/75;
				int newX = e.getX()/75;				
				if((startX-1 >=0)||(startX +1 <=7))
				{
					if((piecePresent(e.getX(), (e.getY())))&&((((newX == (startX+1)&&(startX+1<=7)))||((newX == (startX-1))&&(startX-1 >=0)))))
					{
						if(checkWhiteOponent(e.getX(), e.getY())){
							validMove = true;
							if(startY == 6){
								success = true;
							}						
						}
						else{
							validMove = false;
						}
					}
					else{
						if(!piecePresent(e.getX(), (e.getY()))){
							if((startX == (e.getX()/75))&&((e.getY()/75)-startY)==1){
								if(startY == 6){
									success = true;
								}
								validMove = true;
							}
							else{
								validMove = false;
							}				
						}
						else{
							validMove = false;	
						}
					}
				}
				else{
					validMove = false;
				}				
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
					if(checkBlackOponent(e.getX(),e.getY())){
						validMove = true; 
					if(landingY == 0){ 
						progression = true; 
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
					progression = true;
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
						if(checkWhiteOponent(e.getX(),e.getY())){
							validMove = true; 
				
						}
					else{ 
						validMove = false; 
					}
				}
				else{ 
					if(checkBlackOponent(e.getX(), e.getY())){ 
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
						if(checkWhiteOponent(e.getX(), e.getY())){ 
							validMove = true; 
						}
						else{ 
							validMove = false; 
						}
					}
					else{ 
						if(checkBlackOponent(e.getX(), e.getY())){
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
			if(progression){ 
				int location = 0 + (e.getX()/75); 
				if(c instanceof JLabel){
					Container parent = c.getParent(); 
					parent.remove(0); 
					pieces = new JLabel( new ImageIcon("BlackQueen.png"));
					parent = (JPanel)chessBoard.getComponent(location);
			    	parent.add(pieces);	
					
				}
			}
			else if(success){
				int location = 56 + (e.getX()/75);
				if (c instanceof JLabel){
	            	Container parent = c.getParent();
	            	parent.remove(0);
					pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
					parent = (JPanel)chessBoard.getComponent(location);
			    	parent.add(pieces);			
				}
				else{
					Container parent = (Container)c;
	            	pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
					parent = (JPanel)chessBoard.getComponent(location);
			    	parent.add(pieces);	            	
				}
			}
			else{
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
