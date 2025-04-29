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
public class King extends Piece {
    public BufferedImage image;
    
    
    public King() {
    }

    public King(int row, int col, int color) {
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

        int colDiff = Math.abs(toCol - currentCol);
        int rowDiff = Math.abs(toRow - currentRow);

        return colDiff <= 1 && rowDiff <= 1;
    }

}
