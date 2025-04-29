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
public class Piece {
    private BufferedImage image;//image of piece
    private int col, row, color;//give the location of the piece and color. White = 0, Black = 1.
    private int x,y; //pixel location of peices
    
    
    
    private Piece() {
    }

    public Piece(BufferedImage image, int col, int row, int color) {
        this.image = image;
        this.col = col;
        this.row = row;
        this.color = color;
    }

    public boolean isMove(int toCol, int toRow){
        return false;
    }
    
    
    
    
    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
    
}
