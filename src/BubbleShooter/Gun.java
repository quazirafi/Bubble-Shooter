package BubbleShooter;

import java.awt.*;
import javax.swing.*;

/**
 * this class handles the characteristics of the gun
*/

public class Gun {

	private int x = 0;
	private int y = 0;
	private int dy = 2;
        private Image shooter;
        private Image shooter2;
        
        /**
         * 
         * @param a : sets x position of gun
         * @param b : sets y position of gun
         * also loads the images of the gun
         */
	public Gun (int a,int b){
		x = a;
		y = b;
                shooter = new ImageIcon("shooter.png").getImage();
                shooter2 = new ImageIcon("shooter2.png").getImage();
	}
        
/**
 * 
 * @param a : sets the y position of gun
 */
    public void setY(int a){
            y = a;
    }

/**
 * 
 * @return : the y position of the gun
 */  
    public int getY(){
        return(y);
    }

/**
 * 
 * @return : the width of the gun
 */
	public int getWidth(){
		return (shooter.getWidth(null));
	}
        
/**
 * 
 * @return : the height of the gun
 */
	public int getHeight(){
		return (shooter.getHeight(null));
	}
        
/**
 * 
 * @return : the x position of the gun
 */  
	public int getX(){
		return (x);
	}
        
/**
 * 
 * @param sp : an instance of ScreenManager class
 * @param b : an array of instances 
 * @param bk : an instance of MainGame class
 * updates the position of the gun
 */
	public void update (ScreenManager sp,Bubble b[],MainGame bk,int lenb){
		if (y+dy > sp.getHeight() - shooter.getHeight(null) - 1){
			y = sp.getHeight() - shooter.getHeight(null) - 1;
                        if (MainGame.level3 == true || MainGame.level5 == true || MainGame.level7 == true)
			dy = -dy;
		}
		else if (y+dy < 0 ){
			y = 0;
                         if (MainGame.level3 == true || MainGame.level5 == true || MainGame.level7 == true)
			dy = -dy;
		}
		else {
                     if (MainGame.level3 == true || MainGame.level5 == true || MainGame.level7 == true)
                        y += dy ;
		}
		
	}

        /**
         * paints the gun in appropriate position         
         * @param g : Graphics2D object
         */
	public void paint(Graphics g){
                if (MainGame.level6 == true || MainGame.level5 == true)
                    g.drawImage(shooter2, 0, y, null);
                else
		g.drawImage(shooter, 0, y, null);
	}
}
