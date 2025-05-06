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

        if (from == to) {
            return false; // Can't move to the same square
        }
        Piece targetPiece = to.getPiece();

        // If there's a piece on the target square and it's the same color, can't move there
        if (targetPiece != null && targetPiece.getColor().equalsIgnoreCase(movingPiece.getColor())) {
            return false;
        }

        if (!(movingPiece.isMove(to.getRow(), to.getCol()))){
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
                    for(int i = 0; i < dist; i++){
                        if(squares[trow][tcol + i].hasPiece()){
                            return false;
                        }
                    }
                    
                }else{
                    for(int i = 0; i < dist; i++){
                        if(squares[trow][tcol - i].hasPiece()){
                            return false;
                        }
                    }
                }
                
            }else{
                dist = Math.abs(trow - frow);
                if(trow < frow){
                    for(int i = 0; i < dist; i++){
                        if(squares[trow + i][tcol].hasPiece()){
                            return false;
                        }
                    }
                    
                }else{
                    for(int i = 0; i < dist; i++){
                        if(squares[trow - i][tcol].hasPiece()){
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
                    for(int i = 0; i < dist; i++){
                        if(squares[trow + i][tcol +i].hasPiece()){
                            return false;
                        }
                    }
                }else{
                    for(int i = 0; i < dist; i++){
                        if(squares[trow + i][tcol - i].hasPiece()){
                            return false;
                        }
                    }
                }
            }else{
                dist = Math.abs(frow - trow);
                if(tcol < fcol){
                    for(int i = 0; i < dist; i++){
                        if(squares[trow - i][tcol +i].hasPiece()){
                            return false;
                        }
                    }
                }else{
                    for(int i = 0; i < dist; i++){
                        if(squares[trow - i][tcol - i].hasPiece()){
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
