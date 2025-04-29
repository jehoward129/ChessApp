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
public class Pawn extends Piece{
    public BufferedImage image;
    public Pawn(int row, int col, int color) {
        if(color == 0){
            image = //white image
        }else{
            image = //black image
        }
        super(image, row, col, color);
    }

//    public Pawn() {
//    }
    
    
    @Override
    public boolean isMove(int toRow, int toCol){
        int currentCol = this.getCol();
        int currentRow = this.getRow();
        int color = this.getColor();
        
        
        if(currentCol == toCol){
            if(color == 0){ //for white moves up the board so decrease row
                
                //normal move
                if(toRow == (currentRow -1)){
                    return true;
                }
                
                //double for first move
                if(toRow == (currentRow -2) && currentRow == 6){
                    return true;
                }
                
                //still needs for if capture
            }else{                              //For Black
                if(toRow == (currentRow + 1)){
                    return true;
                }
                
                //double for first move
                if(toRow == (currentRow +2) && currentRow == 1){
                    return true;
                }
                
                //Still needs capture
            }
        }
        return false; //if no conditions met, return false
     }
}
