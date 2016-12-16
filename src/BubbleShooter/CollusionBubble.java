package BubbleShooter;

import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.util.Random;

/**
 * this class is used to handle the collusion between bubbles
*/
public class CollusionBubble {
    /**
     * checks collusion between undivided bubbles
     * @param b : an array of instances of Bubble class
     * @param lenb : number of big bubbles
     */

    public void bubbleCollide(Bubble b[],int lenb){
        
        for (int i = 0 ; i < lenb ; ++i){
            for (int j = i + 1 ; j < lenb ; ++j){
		int a,bb,collide;
		a = b[i].getX() - b[j].getX();
		bb = b[i].getY() - b[j].getY();
		double c;
		collide = b[i].getRad()+b[j].getRad();
		c = Math.sqrt((double) (a*a) + (double) (bb*bb));
		if (c < collide){
                    b[i].setdx(b[i].getdx()*(-1));
                    b[j].setdx(b[j].getdx()*(-1));
                    int xi = b[i].getX();
                    int xj = b[j].getX();
                    if (b[i].getX() < b[j].getX()){
                        b[i].setX(xj - b[i].getRad()*2);
			b[j].setX(xi + b[i].getRad()*2);
                    }
                    else if (b[i].getX() > b[j].getX()){
                        b[j].setX(xi - b[i].getRad()*2);
                        b[i].setX(xj + b[i].getRad()*2);
                    }
                }
            }
        }
    }
    /**
     * checks collusion between divided bubbles
     * @param bbdiv : an array of instances of divided bubbles
     * @param b : an array of instances of undivided bubbles
     * @param len : length of divided bubbles
     */
    
     public void bubbleCollide2(Bubble bbdiv[],Bubble b[],int len){
        
        for (int i = 0 ; i < len ; ++i){
            for (int j = i + 1 ; j < len ; ++j){
		int a,bb,collide;
		a = bbdiv[i].getX() - bbdiv[j].getX();
		bb = bbdiv[i].getY() - bbdiv[j].getY();
		double c;
		collide = bbdiv[i].getRad()+bbdiv[j].getRad();
		c = Math.sqrt((double) (a*a) + (double) (bb*bb));
		if (c < collide){
                    bbdiv[i].setdx(bbdiv[i].getdx()*(-1));
                    bbdiv[j].setdx(bbdiv[j].getdx()*(-1));
                    int xi = bbdiv[i].getX();
                    int xj = bbdiv[j].getX();
                    if (bbdiv[i].getX() < bbdiv[j].getX()){
                        bbdiv[i].setX(xj - bbdiv[i].getRad()*2-10);
			bbdiv[j].setX(xi + bbdiv[i].getRad()*2+10);
                    }
                    else if (bbdiv[i].getX() > bbdiv[j].getX()){
                        bbdiv[j].setX(xi - bbdiv[i].getRad()*2-10);
                        bbdiv[i].setX(xj + bbdiv[i].getRad()*2+10);
                    }
                }
            }
        }
    }
     /**
      * method to check collusion between divided and undivided bubble
      * @param bbdiv : an array of instances of divided bubbles
      * @param b : an array of instances of undivided bubbles
      * @param lenb : length of undivided bubbles
      * @param len : length of divided bubbles
      */
     
     public void bubbleCollide3(Bubble bbdiv[],Bubble b[],int lenb,int len){
        
        for (int i = 0 ; i < len ; ++i){
            for (int j = 0 ; j < lenb ; ++j){
		int a,bb,collide;
		a = bbdiv[i].getX() - b[j].getX();
		bb = bbdiv[i].getY() - b[j].getY();
		double c;
		collide = bbdiv[i].getRad()+b[j].getRad();
		c = Math.sqrt((double) (a*a) + (double) (bb*bb));
		if (c < collide){
                    bbdiv[i].setdx(bbdiv[i].getdx()*(-1));
                    b[j].setdx(b[j].getdx()*(-1));
                    int xi = bbdiv[i].getX();
                    int xj = b[j].getX();
                    if (bbdiv[i].getX() < b[j].getX()){
                        bbdiv[i].setX(xj - bbdiv[i].getRad()*2-10);
			b[j].setX(xi + bbdiv[i].getRad()*2+10);
                    }
                    else if (bbdiv[i].getX() > b[j].getX()){
                        b[j].setX(xi - bbdiv[i].getRad()*2-10);
                        bbdiv[i].setX(xj + bbdiv[i].getRad()*2+10);
                    }
                }
            }
        }
    }
}
