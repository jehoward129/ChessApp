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
public class Bishop extends Piece{
    public BufferedImage image;
    
    
    public Bishop(int row, int col, int color) {
        if(color == 0){
            image = //white image
        }else{
            image = //black image
        }
        super(image, row, col, color);
    }

    public Bishop() {
    }
    @Override
     public boolean isMove(int toRow, int toCol){
        int currentCol = this.getCol();
        int currentRow = this.getRow();
        
        int distance = Math.abs(toCol - currentCol);
        
        if(Math.abs(currentCol - toCol) == distance && Math.abs(currentRow - toRow) == distance){
            return true;
        }else{
            return false;    
                    
        }
     }
}
