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
public class Pawn extends Piece{
    public Pawn(int row, int col, String color) {
        super(loadImage(color), row, col, color);

    }

    private static BufferedImage loadImage(String color) {

        try {
            return ImageIO.read(Pawn.class.getResource("/images/" + color + "-pawn.png"));
        } catch (IOException ex) {
            Logger.getLogger(Pawn.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

//    public Pawn() {
//    }
    
    
    @Override
    public boolean isMove(int toRow, int toCol){
        int currentCol = this.getCol();
        int currentRow = this.getRow();
        int team;
        if(color == "white"){
            team = 0;
        }else{
            team = 1;
        }
        
        
        if(currentCol == toCol){
            if(team == 0){ //for white moves up the board so decrease row
                
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
