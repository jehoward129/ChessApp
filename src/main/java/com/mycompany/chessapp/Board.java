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
    private Square[][] squares;
    private List<Piece> pieces = new ArrayList<>();
    
    int tempColor;
    public Board() {
        squares = new Square[8][8]; // Create the array

        // Initialize each Square object in the array
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if(row + col == 0){
                    tempColor = 0;
                }else{
                    tempColor = 1;
                }
                squares[row][col] = new Square(row, col, tempColor);
                
            }
        }
        
        pieces.add(new Rook(7, 0, 0));
        pieces.add(new Rook(7, 7, 0));
        pieces.add(new Knight(7, 1, 0));
        pieces.add(new Knight(7, 6, 0));
        pieces.add(new Bishop(7, 2, 0));
        pieces.add(new Bishop(7, 5, 0));
        pieces.add(new Queen(7, 3, 0));
        pieces.add(new King(7, 4, 0));
        pieces.add(new Rook(0, 0, 0));
        pieces.add(new Rook(0, 7, 0));
        pieces.add(new Knight(0, 1, 0));
        pieces.add(new Knight(0, 6, 0));
        pieces.add(new Bishop(0, 2, 0));
        pieces.add(new Bishop(0, 5, 0));
        pieces.add(new Queen(0, 3, 0));
        pieces.add(new King(0, 4, 0));
        
        for(Piece piece: pieces){
            int row = piece.getRow();
            int col = piece.getCol();
            
            squares[row][col].setPiece(piece);
        }
        
    }

    public Square getSquare(int row, int col) {
        return squares[row][col];
    }
}
