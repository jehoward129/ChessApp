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
public class Knight extends Piece{
    public BufferedImage image;
    
    
    public Knight() {
    }

    public Knight(int row, int col, int color) {
        if(color == 0){
            image = //white image
        }else{
            image = //black image
        }
        super(image, row, col, color);
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
