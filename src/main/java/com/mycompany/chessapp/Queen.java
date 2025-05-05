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
public class Queen extends Piece {
    public Queen(int row, int col, String color) {
        super(loadImage(color), row, col, color);

    }

    private static BufferedImage loadImage(String color) {

        try {
            return ImageIO.read(Queen.class.getResource("/images/" + color + "-queen.png"));
        } catch (IOException ex) {
            Logger.getLogger(Queen.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

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
