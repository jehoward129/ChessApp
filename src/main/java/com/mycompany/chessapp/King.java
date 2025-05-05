/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chessapp;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author jehow
 */
public class King extends Piece {
    public King(int row, int col, String color) {
        super(loadImage(color), row, col, color);

    }

    private static BufferedImage loadImage(String color) {

        try {
            return ImageIO.read(King.class.getResource("/images/" + color + "-king.png"));
        } catch (IOException ex) {
            Logger.getLogger(King.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

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
