package BubbleShooter;

import java.awt.*;
import java.util.Random;
import javax.swing.ImageIcon;
import java.awt.*;
import java.util.Random;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * this class handles all the characteristics of the Bubbles
 */

public class Bubble {

    private int dx = 3;
    private int dy = -3;
    private int x;
    private int y;
    private int vx;
    private int vy;
    private Image bubble;
    private Image bubble40;
    private Image bubble20;
    public boolean hit = false;
    private boolean bubblediv40 = false;
    private boolean bubblediv20 = false;
    public ScreenManager bk ;
    
    
    /**
     * constructor for Bubble class
     * @param a : sets x position of the Bubble
     * @param b : sets x position of the Bubble
     * @param bk : an instance of ScreenManager class
     */
     
    
    Bubble (int a,int b,ScreenManager bk){
        x = a;
        y = b;
        this.bk = bk;

       bubble = new ImageIcon("bubble_1.png").getImage();
       bubble40 = new ImageIcon("bubble_40.png").getImage();
       bubble20 = new ImageIcon("bubble3.png").getImage();
       hit = false ;
       boolean bubblediv40 = false;
       boolean bubblediv20 = false;
    }
    
    /**
     * method to get the X position of a bubble
     * @return 
     */
 
     public int getX(){
        return (x);
    }

    /**
     * method to get the Y position of a bubble
     * @return 
     */
    public int getY(){
        return (y);
    }
    
    /**
     * 
     * @param x : X position of the bubble
     */
    public void setX(int x){
        this.x = x;
    }
    
     /**
     * 
     * @param y : Y position of the bubble
     */
    public void setY(int y){
        this.y = y;
    }

    /**
     * 
     * @return : x velocity of bubble
     */
    public int getdx(){
        return (dx);
    }

    /**
     * 
     * @return : y velocity of bubble
     */
    public int getdy(){
        return (dy);
    }
    
    /**
     * method to set the velocity through X axis of a bubble
     * @param dx : x velocity of the bubble
     */
    public void setdx(int dx){
        this.dx = dx;
    }
    
    /**
     * 
     * @param dy : y velocity of bubble
     */
    public void setdy(int dy){
        this.dy = dy;
    }
    
    /**
     * method to judge whether a large bubble should be divided into medium bubble or not
     * @param b : to detect whether the bubble is divided or not
     */
    void setbubblediv40(boolean b) {
       bubblediv40 = b;
    }
    
    /**
     * method to judge whether a medium bubble should be divided into small bubble or not
     * @param b : to detect whether the bubble is divided or not
     */
    void setbubblediv20(boolean b) {
       bubblediv20 = b;
    }
    
    /**
     * 
     * @return : radius of different bubbles
     */
    public int getRad (){
        if (MainGame.level1 == true || MainGame.level2 == true || MainGame.level3 == true)
            return (bubble.getWidth(null)/2);
       else if (MainGame.level4 == true || MainGame.level5 == true){
           if (bubblediv40 == true)
                return (bubble40.getWidth(null)/2);
           else 
               return (bubble.getWidth(null)/2);
       }
       else if (MainGame.level6 == true || MainGame.level7 == true){
           if (bubblediv20 == true)
               return (bubble20.getWidth(null)/2);
           else 
               return (bubble40.getWidth(null)/2);
       }
        return 0;
    }

    /**
     * method to update the bubbles position 
     */
    public void update(){
                
                if (MainGame.level1 == true || MainGame.level2 == true || MainGame.level3 == true){
                    
                    if (x + dx > bk.getWidth() - (bubble.getWidth(null)/2)){
			x = bk.getWidth() - (bubble.getWidth(null)/2) - 1;
			dx = -dx;
                    }

                    else if (x+dx < 0 + (bubble.getWidth(null))/2){
			x = 0 + (bubble.getWidth(null)/2);
			dx = -dx;
                    }

                    else {
			x += dx;
                    }
                    
                }
                else if (MainGame.level4 == true || MainGame.level5 == true){
                    
                    if (bubblediv40 == true){
                        if (x + dx > bk.getWidth() - (bubble40.getWidth(null)/2)){
                            x = bk.getWidth() - (bubble40.getWidth(null)/2) - 1;
                            dx = -dx;
                        }

                        else if (x+dx < 0 + (bubble40.getWidth(null))/2){
                            x = 0 + (bubble40.getWidth(null)/2);
                            dx = -dx;
                        }

                        else {
                            x += dx;
                        }
                    }
                    else {
                        if (x + dx > bk.getWidth() - (bubble.getWidth(null)/2)){
                            x = bk.getWidth() - (bubble.getWidth(null)/2) - 1;
                            dx = -dx;
                        }

                        else if (x+dx < 0 + (bubble.getWidth(null))/2){
                            x = 0 + (bubble.getWidth(null)/2);
                            dx = -dx;
                        }

                        else {
                            x += dx;
                        }
                    }
                }
                
                else if (MainGame.level6 == true || MainGame.level7 == true){
                    if (bubblediv20 == true){
                        if (x + dx > bk.getWidth() - (bubble20.getWidth(null)/2)){
                            x = bk.getWidth() - (bubble20.getWidth(null)/2) - 1;
                            dx = -dx;
                        }

                        else if (x+dx < 0 + (bubble20.getWidth(null))/2){
                            x = 0 + (bubble20.getWidth(null)/2);
                            dx = -dx;
                        }

                        else {
                            x += dx;
                        }
                    }
                     else {
                        if (x + dx > bk.getWidth() - (bubble40.getWidth(null)/2)){
                            x = bk.getWidth() - (bubble40.getWidth(null)/2) - 1;
                            dx = -dx;
                        }

                        else if (x+dx < 0 + (bubble40.getWidth(null))/2){
                            x = 0 + (bubble40.getWidth(null)/2);
                            dx = -dx;
                        }

                        else {
                            x += dx;
                        }
                    }
                }

		if (y+dy < 0 ){
			y = bk.getHeight() - 1;
			Random r = new Random();
			x = r.nextInt(bk.getWidth());
		}
		else {
			y += dy ;
		}
    }
    
    /**
     * method to paint the bubbles according to levels
     * @param g : an instance of the Graphics2D object 
     */
     public void paint (Graphics2D g){
       // if (hit == true || done == true)
         //   g.drawImage(bubble40, x-(bubble.getWidth(null)/2), y-(bubble.getHeight(null)/2), null);
        if (MainGame.level1 == true || MainGame.level2 == true || MainGame.level3 == true)
            g.drawImage(bubble, x-(bubble.getWidth(null)/2), y-(bubble.getHeight(null)/2), null);
        else if (MainGame.level4 == true || MainGame.level5 == true){
            if (bubblediv40 == true)
                 g.drawImage(bubble40, x-(bubble40.getWidth(null)/2), y-(bubble40.getHeight(null)/2), null);
            else 
                 g.drawImage(bubble, x-(bubble.getWidth(null)/2), y-(bubble.getHeight(null)/2), null);
        }
        else if (MainGame.level6 == true || MainGame.level7 == true){
            if (bubblediv20 == true)
                g.drawImage(bubble20, x-(bubble20.getWidth(null)/2), y-(bubble20.getHeight(null)/2), null);
            else 
                g.drawImage(bubble40, x-(bubble40.getWidth(null)/2), y-(bubble40.getHeight(null)/2), null);
        }
       
    }

}
