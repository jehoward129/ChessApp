/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chessapp;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jehow
 */
public class Board {

//    public void draw(Graphics g){
//        for(int i = 0; i < 8; i++){
//            for(int i = 0; i < 8; i++){
//                
//            }
//        }
//    }
    private Square[][] squares;
    private List<Piece> pieces = new ArrayList<>();

    int tempColor;

    public Board() {
        squares = new Square[8][8]; // Create the array

        // Initialize each Square object in the array
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (row + col == 0) {
                    tempColor = 0;
                } else {
                    tempColor = 1;
                }
                squares[row][col] = new Square(row, col, tempColor);

            }
        }

        //White
        pieces.add(new Rook(7, 0, "white"));
        pieces.add(new Rook(7, 7, "white"));
        pieces.add(new Knight(7, 1, "white"));
        pieces.add(new Knight(7, 6, "white"));
        pieces.add(new Bishop(7, 2, "white"));
        pieces.add(new Bishop(7, 5, "white"));
        pieces.add(new Queen(7, 3, "white"));
        pieces.add(new King(7, 4, "white"));
        for (int i = 0; i < 8; i++) {
            pieces.add(new Pawn(6, i, "white"));
        }

        //Black
        pieces.add(new Rook(0, 0, "black"));
        pieces.add(new Rook(0, 7, "black"));
        pieces.add(new Knight(0, 1, "black"));
        pieces.add(new Knight(0, 6, "black"));
        pieces.add(new Bishop(0, 2, "black"));
        pieces.add(new Bishop(0, 5, "black"));
        pieces.add(new Queen(0, 3, "black"));
        pieces.add(new King(0, 4, "black"));
        for (int i = 0; i < 8; i++) {
            pieces.add(new Pawn(1, i, "black"));
        }

        for (Piece piece : pieces) {
            int row = piece.getRow();
            int col = piece.getCol();

            squares[row][col].setPiece(piece);
        }

    }

    public Square getSquare(int row, int col) {
        return squares[row][col];
    }

    
    
    public boolean isMove(Square from, Square to) {
        Piece movingPiece = from.getPiece();

        // pawns cannot take forward
        if (movingPiece instanceof com.mycompany.chessapp.Pawn){
            if (from.getRow() - to.getRow() == 1 || from.getRow() - to.getRow() == -1){ // if the pawn is moving one up or down it cannot take.
                if (to.getPiece() != null){
                    return false;
                }
            }
        }

        if (from == to) {
            System.out.println("same square");
            return false; // Can't move to the same square
        }
        Piece targetPiece = to.getPiece();

        // If there's a piece on the target square and it's the same color, can't move there
        if (targetPiece != null){
            if(targetPiece.getColor().equalsIgnoreCase(movingPiece.getColor())){
                System.out.println("same color");
                return false;
            }
               
        }

        if (!(movingPiece.isMove(to.getRow(), to.getCol()))){
            System.out.println("Piece doesn't move like that");
            return false;
        }
        

        if (movingPiece instanceof Rook || movingPiece instanceof Queen) {
            int frow = from.getRow();
            int fcol = from.getCol();
            int trow = to.getRow();
            int tcol = to.getCol();
            int dist;
            if(frow == trow){
                dist = Math.abs(fcol - tcol);
                if(tcol < fcol){
                    for(int i = 1; i < dist; i++){
                        if(squares[frow][fcol - i].hasPiece()){
                            System.out.println("Piece in the way1");
                            return false;
                        }
                    }
                    
                }else{
                    for(int i = 1; i < dist; i++){
                        if(squares[frow][fcol + i].hasPiece()){
                            System.out.println("Piece in the way2");
                            return false;
                        }
                    }
                }
                
            }else if(fcol == tcol){
                dist = Math.abs(trow - frow);
                if(trow < frow){
                    for(int i = 1; i < dist; i++){
                        if(squares[frow - i][fcol].hasPiece()){
                            System.out.println("Piece in the way3");
                            return false;
                        }
                    }
                    
                }else{
                    for(int i = 1; i < dist; i++){
                        if(squares[frow + i][fcol].hasPiece()){
                            int badpieceloc = trow + i;
                                    
                            System.out.println("Piece in the way4" + badpieceloc + " ," + tcol);
                            
                            return false;
                        }
                    }
                }
            }
                    
        }
        
        if(movingPiece instanceof Queen || movingPiece instanceof Bishop){
            int frow = from.getRow();
            int fcol = from.getCol();
            int trow = to.getRow();
            int tcol = to.getCol();
            int dist;
            if(trow < frow){
                dist = Math.abs(frow - trow);
                if(tcol < fcol){
                    for(int i = 1; i < dist; i++){
                        if(squares[frow - i][fcol -i].hasPiece()){
                            System.out.println("Piece in the way5");
                            return false;
                        }
                    }
                }else if(fcol < tcol){
                    for(int i = 1; i < dist; i++){
                        if(squares[frow - i][fcol + i].hasPiece()){
                            System.out.println("Piece in the way6");
                            return false;
                        }
                    }
                }
            }else if(frow < trow){
                dist = Math.abs(frow - trow);
                if(tcol < fcol){
                    for(int i = 1; i < dist; i++){
                        if(squares[frow + i][fcol -i].hasPiece()){
                            System.out.println("Piece in the way8");
                            return false;
                        }
                    }
                }else if(fcol < tcol){
                    for(int i = 1; i < dist; i++){
                        if(squares[frow + i][fcol + i].hasPiece()){
                            System.out.println("Piece in the way9");
                            return false;
                        }
                    }
                }
            }
        }

        // Check if the move is legal based on piece movement
        return true;
    }
}
