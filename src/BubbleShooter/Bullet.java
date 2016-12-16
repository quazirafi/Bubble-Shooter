package BubbleShooter;

import java.applet.*;
import java.awt.*;
import java.io.File;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

/**
 * this class is used to handle the characteristics of bullet
*/
public class Bullet {

	private int x ;
	private int y ;
	private int height;
	private int width;
	private int dx = 0;
        private Image arrow;
        private Image arrow2;
    MainGame bk;
    /**
     * constructor for bullet class
     * @param b : an instance of the MAinGame class
     */
	public Bullet (MainGame b){

		x = 1330;
		y = 1330;
		bk = b;
        arrow = new ImageIcon("arrow3.png").getImage();
        arrow2 = new ImageIcon("arrow5.png").getImage();
	}

        /**
         * gets you the x coordinate of a bullet
         * @return x position of bullet
         */
        
	public int getX(){
        return x;
    }
        
/**
 * sets the x coordinate of a bullet
 * @param a : x position of a bullet
 */
	public void setX(int a){
        x = a;
    }

        /**
         * instantiate the bullet 
         * @param a : x position
         * @param b : y position
         * @param c : width
         * @param d : height
         * @param e : velocity
         */       
	public void shoot(int a,int b,int c,int d,int e){
		x = a;
		y = b;
		width = c;
		height = d;
		dx = e;
	}
        
        /**
         * updates the position of the bullet per frame
         * @param sp : an instance of ScreenManager class
         * @param b : an array of instances of bubble class
         * @param bbdiv : an array of instances of bubble class
         * @param lenb : number of big bubbles
         * @param len : number of medium bubbles
         */
	public void update (ScreenManager sp,Bubble b[],Bubble bbdiv[],int lenb,int len){
		if (x+dx > sp.getWidth() - 1){
			dx = 0;
			x = sp.getWidth() + 400;
		}
		else {
			x += dx ;
		}
                checkForCollusion(sp,b,bbdiv,lenb,len);
	}
        /**
         * checks whether the bullet collides with a bubble or not
         * @param sp : an instance of ScreenManager class
         * @param b : an array of instances of bubble class
         * @param bbdiv : an array of instances of bubble class
         * @param lenb : number of big bubbles
         * @param len : number of medium bubbles
         */
	private void checkForCollusion(ScreenManager sp,Bubble b[],Bubble bbdiv[],int lenb,int len){
		for (int i=0 ; i<lenb ; ++i){
			if (x+arrow.getWidth(null) >= b[i].getX()-b[i].getRad() && x+arrow.getWidth(null) <= b[i].getX()+b[i].getRad()){
				if (y+(height/2) >= b[i].getY()-b[i].getRad() && y+(height/2) <= b[i].getY()+b[i].getRad()){
					b[i].setX(1350);
					b[i].setY(1350);
					b[i].setdx(0);
					b[i].setdy(0);
                                        b[i].hit = true;
					this.x = 1400;
					this.y = 1400;
					int add = bk.getScore();
					bk.setScore(add + 4);
                                        ++MainGame.bubbleBlasted;
                                         try {
                     AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("blop.wav").getAbsoluteFile());
                     Clip clip = AudioSystem.getClip();
                     clip.open(audioInputStream);
                     clip.start();
                 } 
                 catch (Exception ex) {
                     ex.printStackTrace();
                 }
				}
			}
		}
                //
                for (int i=0 ; i<len ; ++i){
			if (x+arrow.getWidth(null) >= bbdiv[i].getX()-bbdiv[i].getRad() && x+arrow.getWidth(null) <= bbdiv[i].getX()+bbdiv[i].getRad()){
				if (y+(height/2) >= bbdiv[i].getY()-bbdiv[i].getRad() && y+(height/2) <= bbdiv[i].getY()+bbdiv[i].getRad()){
					bbdiv[i].setX(1350);
					bbdiv[i].setY(1350);
					bbdiv[i].setdx(0);
					bbdiv[i].setdy(0);
                                        bbdiv[i].hit = true;
					this.x = 1400;
					this.y = 1400;
					int add = bk.getScore();
					bk.setScore(add + 4);
                                        ++MainGame.bubbleBlasted;
                                         try {
                     AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("blop.wav").getAbsoluteFile());
                     Clip clip = AudioSystem.getClip();
                     clip.open(audioInputStream);
                     clip.start();
                 } 
                 catch (Exception ex) {
                     ex.printStackTrace();
                 }
				}
			}
		}
	}
        
/**
 * 
 * @param g : Graphics2D object to draw bullet in appropriate position
 */
	public void paint(Graphics g){

		if (x>=0){
                    if (MainGame.level5 == true || MainGame.level6 == true ){
                        //g.setColor(Color.black);
			g.drawImage(arrow2,x+width,y+(height/2),null);
                    }
                    else if (MainGame.level1 == true || MainGame.level2 == true || MainGame.level3 == true || MainGame.level4 == true )
                       // g.setColor(Color.black);
			g.drawImage(arrow,x+width,y+(height/2),null);

		}

	}

}
