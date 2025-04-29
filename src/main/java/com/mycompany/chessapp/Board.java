/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chessapp;

/**
 *
 * @author jehow
 */
public class Board {
    private Square[][] squares;
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
    }

    public Square getSquare(int row, int col) {
        return squares[row][col];
    }
}
