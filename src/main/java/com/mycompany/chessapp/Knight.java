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
public class Knight extends Piece{
    public Knight(int row, int col, String color) {
        super(loadImage(color), row, col, color);

    }

    private static BufferedImage loadImage(String color) {

        try {
            return ImageIO.read(Knight.class.getResource("/images/" + color + "-knight.png"));
        } catch (IOException ex) {
            Logger.getLogger(Knight.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
    
    @Override
    public boolean isMove(int toRow, int toCol) {
        int currentCol = this.getCol();
        int currentRow = this.getRow();
        
        if(Math.abs(toCol - currentCol) == 2){
            if(Math.abs(toRow - currentRow)==1){
                return true;
            }
        }else if(Math.abs(toRow - currentRow) == 2){
            if(Math.abs(toCol - currentCol)==1){
                return true;
            }
        }
        return false;
    }
}
