
package com.mycompany.chessapp;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


/**
 *
 * @author jehow
 * @author tussky
 */
public class ChessApp extends javax.swing.JFrame {

    Board board;
    ActionListener listener;

    Square prevSquare = null;
    Square clickedSquare;

    Piece prevPiece = null;
    Piece clickedPiece;

    JButton prevBtn = null;

    int currentRow;
    int currentCol;
    int prevRow;
    int prevCol;

    int turn = 0;

    /**
     * Creates new form ChessApp
     */
    public ChessApp() {
        initComponents();
        setSize(900, 900);
        board = new Board();
        boardPanel.setVisible(true);

        listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JButton source = (JButton) e.getSource();
                BtnClicked(source);
            }
        };
        
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem restartItem = new JMenuItem("Restart Game");
        JMenuItem exitItem = new JMenuItem("Exit");

        exitItem.addActionListener(e -> System.exit(0));

        JOptionPane.showMessageDialog(null, "This is a pop-up message!");

        restartItem.addActionListener(e -> {
            boardPanel.removeAll();
            prevSquare = null;
            clickedSquare = null;
            prevPiece = null;
            clickedPiece = null;
            prevBtn = null;
            turn = 0;
            
            setSize(900, 900);
            board = new Board();
            fillGrid(); // re-add buttons and images
            boardPanel.revalidate();
            boardPanel.repaint();
        });

        fileMenu.add(restartItem);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        setJMenuBar(menuBar);
                
        fillGrid();
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {

        boardPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(640, 640));

        boardPanel.setBackground(new java.awt.Color(0, 0, 0));
        boardPanel.setLayout(new java.awt.GridLayout());
        boardPanel.setLayout(new java.awt.GridLayout(8,8));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(boardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(boardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChessApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChessApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChessApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChessApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChessApp().setVisible(true);
            }
        });
    }

    public void BtnClicked(JButton clickedBtn) {
        clickedBtn.setBackground(Color.green);
        String pos = clickedBtn.getActionCommand();
        String[] thing = pos.split(",");
        currentRow = Integer.parseInt(thing[0]);
        currentCol = Integer.parseInt(thing[1]);
        System.out.println(currentRow + "," + currentCol);

        clickedSquare = board.getSquare(currentRow, currentCol);
        clickedPiece = clickedSquare.getPiece();

        //If Piece already clicked than try to move piece
        if (prevPiece != null) {

            //check for legal move
            if (board.isMove(prevSquare, clickedSquare)) { //if legal
                makeMove(prevBtn, clickedBtn);
                return;
            } else if (prevPiece instanceof Pawn) { // check for pawn capturing diagnol
                if ((clickedSquare.getPiece() != null) && !(clickedSquare.getPiece().getColor().equalsIgnoreCase(prevPiece.getColor())) && (Math.abs(currentCol - prevCol) == 1)) {
                    if (((prevPiece.getColor().equalsIgnoreCase("white")) && ((prevRow - currentRow) == 1)) || ((prevPiece.getColor().equalsIgnoreCase("black")) && ((prevRow - currentRow) == -1))) {
                        makeMove(prevBtn, clickedBtn);
                        return;
                    }
                } else {
                    resetAll(prevBtn, clickedBtn);
                }
            } else if ((prevPiece instanceof King) && (clickedPiece instanceof Rook)) { //castling
                if (canCastle(prevPiece, clickedPiece)) {
                    castle(prevPiece, clickedPiece, prevBtn, clickedBtn);
                }
            } else { //if illigal reset all
                resetAll(prevBtn, clickedBtn);

            }

        }

        // if there is a piece in the selected square and as piece has been selected.
        if (prevPiece == null && clickedPiece != null) {

            //check for correct turn
            if ((turn == 0 && clickedPiece.getColor().equalsIgnoreCase("white")) || (turn == 1 && clickedPiece.getColor().equalsIgnoreCase("black"))) {
                prevPiece = clickedPiece;
                prevSquare = clickedSquare;
                prevBtn = clickedBtn;
                prevCol = currentCol;
                prevRow = currentRow;
            } else {

                //if incorrect turn, don't change color
                resetColor(clickedBtn);
            }

        }

        //No piece selected and clicked empty square don't change color
        if (prevPiece == null && clickedPiece == null) {
            resetColor(clickedBtn);
        }

    }

    public void resetColor(JButton btn) {
        String pos = btn.getActionCommand();
        String[] thing = pos.split(",");
        int row = Integer.parseInt(thing[0]);
        int col = Integer.parseInt(thing[1]);
        if ((row + col) % 2 == 0) {
            btn.setBackground(new Color(240, 217, 181)); // white
        } else {
            btn.setBackground(new Color(101, 67, 33)); // black
        }
    }

    public void makeMove(JButton fromSquare, JButton toSquare) {
        // move piece on board
        
        prevPiece.setCol(currentCol);
        prevPiece.setRow(currentRow);
        
        // A check to see if the rook or king is moving - for castling purposes.
        if (prevPiece instanceof com.mycompany.chessapp.King){
            ((com.mycompany.chessapp.King)prevPiece).moved();
        }

        if (prevPiece instanceof com.mycompany.chessapp.Rook){
            ((com.mycompany.chessapp.Rook)prevPiece).moved();
        }

        if (prevPiece instanceof com.mycompany.chessapp.Pawn){
            if (currentRow == 7 || currentRow == 0){

                Pawn currentPawn = (Pawn) prevPiece;

                if (currentPawn.getColor().equalsIgnoreCase("white")){
                    prevPiece = new Queen(currentRow, currentCol, "white");
                } else {
                    prevPiece = new Queen(currentRow, currentCol, "black");
                }
            }
        }
        System.out.println("Current ROW:" +currentRow);

        isCheck();

        clickedSquare.setPiece(prevPiece);
        prevSquare.setPiece(null);

        // move piece image on gui
        Image originalImage = prevPiece.getImage();
        Image scaledImage = originalImage.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        toSquare.setIcon(new ImageIcon(scaledImage));
        fromSquare.setIcon(null);
        

        //reset
        resetAll(fromSquare, toSquare);

        // Switch turn
        if (turn == 0) {
            turn = 1;
        } else {
            turn = 0;
        }
        System.out.println(turn);
    }

    public boolean isCheck() {
        Square kingSquare = null;
        
        //find king
        if (turn == 0) {
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    Square square = board.getSquare(row, col);
                    Piece piece = square.getPiece();
                    if (piece instanceof King && piece.getColor().equalsIgnoreCase("black")) {
                        kingSquare = square;
                    }
                }
            }
        }else{
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    Square square = board.getSquare(row, col);
                    Piece piece = square.getPiece();
                    if (piece instanceof King && piece.getColor().equalsIgnoreCase("white")) {
                        kingSquare = square;
                    }
                }
            }
        }
        
        int kingCol = kingSquare.getCol();
        int kingRow = kingSquare.getRow();
        boolean inCheck = prevPiece.isMove(kingRow, kingCol);
        if(inCheck){
            JButton KingBtn = (JButton) boardPanel.getComponent(kingRow * 8 + kingCol);
            KingBtn.setBackground(Color.red);
        }
        return inCheck;
    }
    

    //Resets everything if wrong move.
    public void resetAll(JButton fromSquare, JButton toSquare) {
        resetColor(fromSquare);
        resetColor(toSquare);
        prevPiece = null;
        prevSquare = null;
        prevBtn = null;
    }

    public boolean canCastle(Piece kingPiece, Piece rookPiece) {


        King king = (King) kingPiece;
        Rook rook = (Rook) rookPiece;

        if (king.hasMoved() || rook.hasMoved()){
            return false;
        }


        // Ensure both are on same row
        if (kingPiece.getRow() != rookPiece.getRow()) {
            return false;
        }

        int row = kingPiece.getRow();
        int kingCol = king.getCol();
        int rookCol = rook.getCol();

        int start = Math.min(kingCol, rookCol) + 1;
        int end = Math.max(kingCol, rookCol) - 1;

        // Check if all squares between are empty
        for (int col = start; col <= end; col++) {
            if (board.getSquare(row, col).getPiece() != null) {
                return false;
            }
        }
        return true;
    }

    public void castle(Piece king, Piece rook, JButton kingBtn, JButton rookBtn) {
        int kingCol = king.getCol();
        int rookCol = rook.getCol();

        int newKingCol;
        int newRookCol;
        if (kingCol < rookCol) {
            newKingCol = kingCol + 2;
            newRookCol = rookCol - 2;
        } else {
            newKingCol = kingCol - 2;
            newRookCol = rookCol + 3;
        }

        // Move king
        board.getSquare(prevRow, kingCol).setPiece(null);
        board.getSquare(prevRow, newKingCol).setPiece(king);
        king.setCol(newKingCol);

        // Move rook
        board.getSquare(prevRow, rookCol).setPiece(null);
        board.getSquare(prevRow, newRookCol).setPiece(rook);
        rook.setCol(newRookCol);

        // Update GUI
        JButton newKingBtn = (JButton) boardPanel.getComponent(prevRow * 8 + newKingCol);
        JButton newRookBtn = (JButton) boardPanel.getComponent(prevRow * 8 + newRookCol);

        Image kingImg = king.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        Image rookImg = rook.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);

        newKingBtn.setIcon(new ImageIcon(kingImg));
        newRookBtn.setIcon(new ImageIcon(rookImg));
        kingBtn.setIcon(null);
        rookBtn.setIcon(null);

        // Reset visuals and turn
        resetAll(kingBtn, rookBtn);
        turn = (turn == 0) ? 1 : 0;
    }

    public void fillGrid() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JButton square = new JButton();
                square.setVisible(true);
                square.setOpaque(true);
                square.setActionCommand(row + "," + col);
                square.addActionListener(listener);

                Square sq = board.getSquare(row, col);
                if (!(sq.getPiece() == null)) {
                    Piece p = sq.getPiece();
                    Image originalImage = p.getImage(); // Assuming this returns a java.awt.Image
                    Image scaledImage = originalImage.getScaledInstance(80, 80, Image.SCALE_SMOOTH); // Adjust size as needed
                    square.setIcon(new ImageIcon(scaledImage));
                }

                // Example: alternate colors for board
                if ((row + col) % 2 == 0) {
                    square.setBackground(new Color(240, 217, 181)); // white
                } else {
                    square.setBackground(new Color(101, 67, 33)); // black
                }

                boardPanel.add(square);
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel boardPanel;
    // End of variables declaration//GEN-END:variables
}
