/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chessapp;

import java.awt.image.BufferedImage;

/**
 *
 * @author jehow
 */
public class Queen extends Piece {
    public BufferedImage image;
    
    
    public Queen() {
    }

    public Queen(int row, int col, int color) {
        if (color == 0) {
            image
                    = //white image
        } else {
            image
                    = //black image
        }
        super(image, row, col, color);
    }

    @Override
    public boolean isMove(int toRow, int toCol) {
        int currentCol = this.getCol();
        int currentRow = this.getRow();

        int distance = Math.abs(toCol - currentCol);

        if (toCol == currentCol || toRow == currentRow) {
            return true;
        } else {
            if (Math.abs(currentCol - toCol) == distance && Math.abs(currentRow - toRow) == distance) {
                return true;
            }
        }
        return false;
    }
}
