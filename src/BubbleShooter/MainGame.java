package BubbleShooter;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.net.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import javax.sound.sampled.*;
import javax.swing.*;
/**
 * this class contains the whole game logic 
 * it also handles all the mouse and keyboard events
 */

public class MainGame implements Runnable,KeyListener,MouseListener,MouseMotionListener
{

    public static void main(String args[])
    {
        MainGame b = new MainGame();
        b.run();
    }
   
    ScreenManager s;
    Bubble bb[] = new Bubble[20];
    Bubble bbdiv[] = new Bubble[10];
    Gun gn;
    //
    Bullet blt[] = new Bullet[6];
    private Image bg,bg2,bg3,megh1,megh2,megh3,megh4,megh5,megh6,megh7,megh8,levelup1,levelfailed,gamePaused,gameUp;
    private Image openingScreen ;
    private Image menuCursor;
    private Image highScoreImage;
    private Image instructionsImage;
    private Image timeupimage;
    private int megh1x = 100;
    private int megh2x = 200;
    private int megh3x = 500;
    private int megh1y = 40;
    private int megh2y = 100;
    private int megh3y = 300;
    private int megh4x = 800;
    private int megh4y = 600;
    private int score = 0;
    private int shootCount = 0;
    private int bubbleHitGunCount = 0;
    private int soundFlag = 0;
    private int counter = 0;
    private long timePassed2 = 0;
    private int seconds = 0;
    private int prevX[] = new int [10];
    private int prevY[] = new int [10];
    private int flag1 = 1; 
    private int MyScore;
    private int mouseShooter = 0;
    private long timePassed4 = 0;
    private long level1Time = 30000;
    private int scorePercentage = 0;
    private int menuCursorX=502,menuCursorY=432;
    private int menuCursorX2=552,menuCursorY2=342;
    private int menuCursorOs=284;
    private int menuSpecial = 0;
    private boolean gameUpScreen = false;
    private boolean showScore = false;
    private boolean gameRunning = true;
    private boolean mouseIn = false;
    static boolean level1 = true,level2=false,level3=false,level4=false,level5=false,level6=false,level7=false;
    private boolean bubbleHitGun = false;
    private boolean mouseInHighScore = false;
    private boolean HighScore = false;
    private boolean removefailed = false;
    private boolean menu = false;
    private boolean Instructions = false;
    private boolean mouseInInstructions = false;
    static int bubbleBlasted = 0;
    private boolean win7=false,win8=false,os=true;
    private boolean madeHistory = false,showMessage = false;
    CollusionBubble cb ;
    private static final DisplayMode modes1[]=
    {   
        //new DisplayMode(800,600,32,0),
        //new DisplayMode(800,600,24,0),
        new DisplayMode(800,600,16,0),
        new DisplayMode(640,480,32,0),
        new DisplayMode(640,480,24,0),
        new DisplayMode(640,480,16,0),
        new DisplayMode(1366,768,16,0),
    };
    private static final DisplayMode modes2[]=
    {   
        new DisplayMode(800,600,32,0),
        new DisplayMode(800,600,24,0),
        new DisplayMode(800,600,16,0),
        new DisplayMode(640,480,32,0),
        new DisplayMode(640,480,24,0),
        new DisplayMode(640,480,16,0),
        new DisplayMode(1366,768,16,0),
    };

    /**
     * method to obtain current score
     * @return current score 
     */
    public int getScore(){
        return score;
    }
    
    /**
     * method to set current score 
     * @param a : score
     */
    public void setScore(int a){
        score = a;
    }

    public void setBubbleHitGunCount(int a){
        bubbleHitGunCount = a;
    }

    public int getBubbleHitGunCount(){
        return (bubbleHitGunCount);
    }

    public void setBubbleHitGun(boolean bln){
        bubbleHitGun = bln;
    }
    
/**
 * 
 * @param a : how many shots have been fired
 */
    public void setShootCount (int a){
        shootCount = a;
    }
    /**
    * 
    *controls the movement of the clouds in the background
    */
    public void updatingClouds(){

        Random r = new Random();
        if (megh1x > s.getWidth())
        {
            megh1x = megh1.getWidth(null) * (-1);
            megh1y = r.nextInt(499);
        }

        if (megh2x > s.getWidth())
        {
            megh2x = megh2.getWidth(null) * (-1);
            megh2y = r.nextInt(499);
        }

        if (megh3x > s.getWidth())
        {
            megh3x = megh3.getWidth(null) * (-1);
            megh3y = r.nextInt(499);
        }

        if (megh4x > s.getWidth())
        {
            megh4x = megh4.getWidth(null) * (-1);
            megh4y = r.nextInt(499);
        }
        megh1x += 1;
        megh2x += 2;
        megh3x += 1;
        megh4x += 1;
    }

    /**
     * 
     * initializes all the images,objects
    */
    public void init()
    {
        counter = 0;
        bg = new ImageIcon("cloud.png").getImage();
        bg2 = new ImageIcon("cloud2.png").getImage();
        bg3 = new ImageIcon("cloud5.png").getImage();
        levelup1 = new ImageIcon("levelup1.jpg").getImage();
        levelfailed = new ImageIcon("levelfailed.jpg").getImage();
        megh1 = new ImageIcon("megh1.png").getImage();
        megh2 = new ImageIcon("megh2.png").getImage();
        megh3 = new ImageIcon("megh3.png").getImage();
        megh4 = new ImageIcon("megh4.png").getImage();
        megh5 = new ImageIcon("megh5.png").getImage();
        megh6 = new ImageIcon("megh6.png").getImage();
        megh7 = new ImageIcon("megh7.png").getImage();
        megh8 = new ImageIcon("megh8.png").getImage();
        openingScreen = new ImageIcon("os.jpg").getImage();
        gameUp = new ImageIcon("Game_up1.jpg").getImage();
        gamePaused = new ImageIcon("Game_Paused.jpg").getImage();
        menuCursor = new ImageIcon("down.png").getImage();
        highScoreImage = new ImageIcon("bubble4.png").getImage();
        instructionsImage = new ImageIcon("bubble5.png").getImage();
        timeupimage = new ImageIcon("TimeUp.jpg").getImage();
        cb = new CollusionBubble();
         for (int i =0 ; i<6 ; ++i ){
                blt[i] = new Bullet (this);
            }
        Random r = new Random();
        if (level1 == true || flag1 ==1){
            for (int i =0 ; i<10 ; ++i ){
                bb[i] = new Bubble (r.nextInt(s.getWidth()),r.nextInt(s.getHeight()),s);
            }
        }
        
        else if (level2 == true || level3 == true || level4 == true || level5 == true || level6 == true || level7 == true){
            for (int i =0 ; i<5 ; ++i ){
                bb[i] = new Bubble (r.nextInt(s.getWidth()),r.nextInt(s.getHeight()),s);
            }
        }
        
        gn = new Gun (0,100);
    }
    
    /**
     * gets you the screen also contains main game logic
    */
    public void run()
    {
        s = new ScreenManager();
        try
        {
                
            DisplayMode dm = s.findFirstCompatibleMode(modes2);
            s.setFullScreen(dm);
            Window w = s.getFullScreenWindow();
            w.addKeyListener(this);
            w.addMouseListener(this);
            w.addMouseMotionListener(this);
            Graphics2D ggg = s.getGraphics();
            init();
            while (os == true){
                ggg = s.getGraphics();
                s.update();
                paintOs(ggg);
            }
            s.restoreScreen();
            
            if (win7 == true){
                dm = s.findFirstCompatibleMode(modes1);
                s.setFullScreen(dm);
                w = s.getFullScreenWindow();
                w.addKeyListener(this);
                w.addMouseListener(this);
                w.addMouseMotionListener(this);
            }
            else if (win8 == true){
                dm = s.findFirstCompatibleMode(modes2);
                s.setFullScreen(dm);
                w = s.getFullScreenWindow();
                w.addKeyListener(this);
                w.addMouseListener(this);
                w.addMouseMotionListener(this);
            }
            
            Toolkit toolkit = Toolkit.getDefaultToolkit(); 
            Image image = toolkit.getImage("Ab.gif"); 
            Point hotSpot = new Point(0,0); 
            Cursor cursor = toolkit.createCustomCursor(image, hotSpot, "Ab");
            w.setCursor(cursor);
            while(gameRunning == true)
            {
               Graphics2D g1;
               madeHistory = false;
               showMessage = false;
                while(flag1==1 && gameRunning==true){
                    while (HighScore == true){
                        g1 = s.getGraphics();
                        paintHighScores(g1);
                        g1.dispose();
                        s.update();
                    }
                    while (Instructions == true){
                        g1 = s.getGraphics();
                        paintInstructions(g1);
                        g1.dispose();
                        s.update();
                    }
                    bubbleBlasted = 0;
                    score = 0;
                    shootCount = 0;
                    seconds = 25;
                    init();
                    removefailed = false;
                    g1 = s.getGraphics();
                    paintOpeningScreen(g1);
                    g1.dispose();
                    s.update();
                    try {
                        Thread.sleep(25);
                    }
                    catch(Exception e){}
                }
                long startTime = System.currentTimeMillis();
                long cumTime = startTime;
                int menuflag = 0;
                while (level1 == true && gameRunning == true)
                {
                        Graphics2D g = s.getGraphics();
                        while (removefailed == true){                            
                            //Graphics2D g2 = s.getGraphics();  
                            if (menu == true){
                                menuflag = 1;
                                break;
                            }
                            g = s.getGraphics();
                            paintPauseScreen(g);                          
                            g.dispose();
                            s.update();      
                            try{
                                Thread.sleep(25);
                            }
                            catch(Exception e){}
                        }
                        if (menuflag == 1){
                            break;
                        }
//                        if (menu == true){
//                            break;
//                        }
                        
                        long timePassed = System.currentTimeMillis() - cumTime;
                        timePassed2 += timePassed;
                        if (timePassed2 >= 1000){
                            --seconds;
                            s.update();
                            g.dispose();
                            timePassed2 = 0;
                        }
                        cumTime += timePassed;
                        updatingClouds();
                    
                        for (int i = 0; i<10 ; ++i){
                            bb[i].update();
                        }
                        
                        for (int i =0 ; i<6 ; ++i ){
                             blt[i].update(s, bb, bbdiv, 10, counter);
                        }
                        
                        gn.update(s,bb,this,10);
                        cb.bubbleCollide(bb,10);
                       
                        if(seconds == 0){
                                g = s.getGraphics();
                                timeUpMessage(g);
                                MyScore-=(25-seconds);
                                g.dispose();
                                s.update();
                                try{
                                    Thread.sleep(4000);
                                }
                                catch(Exception e){}
                                score = 0;
                                bubbleBlasted = 0;
                                shootCount = 0;
                                seconds = 25;
                                init();
                                startTime = cumTime;
                                removefailed = false;
                            
                        }
                    
                        else if (score == 40)
                        {
                            scorePercentage = (int)((10.0/(double)shootCount)*100.0);
                            MyScore = (int)scorePercentage;
                            counter = 0;
                            if (MyScore >= 80.0){
                                MyScore-=(25-seconds);
                                 g = s.getGraphics();
                                paintLevelUpMsg(g);
                                g.dispose();
                                s.update();
                                g.dispose();
                                try {
                                    Thread.sleep(4000);
                                }
                                catch(Exception e){}
                                                                                   
                                score = 0;
                                shootCount = 0;
                                seconds = 40;
                                level1 = false;
                                level2 = true;
                                //HighScore = true;
                            }
                            else {
                                 g = s.getGraphics();
                                paintLevelFailedMsg(g);
                                MyScore-=(25-seconds);
                                g.dispose();
                                s.update();
                                try{
                                    Thread.sleep(4000);
                                }
                                catch(Exception e){}
                                score = 0;
                                bubbleBlasted = 0;
                                shootCount = 0;
                                seconds = 25;
                                init();
                                startTime = cumTime;
                                removefailed = false;
                            }
                        }
                         paint(g);
                        g.dispose();
                        s.update();
                        try{
                            Thread.sleep(25);
                        }
                        catch(Exception e) {}  
                        
                                                                                                    
                }
                
                init();
                Random r = new Random();
                startTime = System.currentTimeMillis();
                cumTime = startTime;
                timePassed2 = 0;
                score = 0;
                bubbleBlasted = 0;
                shootCount = 0;
                seconds = 40;
                while (level2 == true && gameRunning == true)
                {
                      Graphics2D g = s.getGraphics();
                        while (removefailed == true){                            
                            //Graphics2D g2 = s.getGraphics();  
                            if (menu == true){
                                menuflag = 1;
                                break;
                            }
                            g = s.getGraphics();
                            paintPauseScreen(g);                          
                            g.dispose();
                            s.update();      
                            try{
                                Thread.sleep(25);
                            }
                            catch(Exception e){}
                        }
                        if (menuflag == 1){
                            break;
                        }
   
                           long timePassed = System.currentTimeMillis() - cumTime;
                        timePassed2 += timePassed;
                        if (timePassed2 >= 1000){
                            --seconds;
                            s.update();
                            g.dispose();
                            timePassed2 = 0;
                        }
                        cumTime += timePassed;
                   
                    updatingClouds();
                    
                    for (int i = 0; i<5 ; ++i){
                       prevX[i] = bb[i].getX();
                       prevY[i] = bb[i].getY();
                    }
                    for (int i = 0; i<5 ; ++i)
                    {
                        bb[i].update();
                    }
                    for (int i =0 ; i<6 ; ++i ){
                        blt[i].update(s, bb, bbdiv, 10, counter);
                    }
                    gn.update(s,bb,this,5);
                    cb.bubbleCollide(bb,5);
                    
                     for (int i = 0; i<5 ; ++i){
                        if (bb[i].hit == true){
                            bbdiv[counter] = new Bubble(prevX[i]+8,prevY[i],s);
                            bbdiv[counter].setdx(3);
                            bbdiv[counter++].setdy(-5);
                            bbdiv[counter] = new Bubble(prevX[i],prevY[i]+9,s);
                            bbdiv[counter].setdx(-3);
                            bbdiv[counter++].setdy(-5);
                            bb[i].hit = false;                
                        }
                    }
                     /* for (int i = 0; i<counter ; ++i){
                          bbdiv[i].update();
                      }*/
                      try{
                         if (counter > 0){
                             for (int i = 0; i<counter ; ++i){
                          bbdiv[i].update();
                      }
                             cb.bubbleCollide2(bbdiv,bb,counter);
                             cb.bubbleCollide3(bbdiv,bb,5,counter);
                             
                         }
                        
                     }
                    catch(Exception ee){}
                      
                    if (seconds == 0)  {
                        g = s.getGraphics();
                        timeUpMessage(g);
                        MyScore-=(40-seconds);
                        g.dispose();
                        s.update();
                        try{
                            Thread.sleep(4000);
                        }
                        catch(Exception e){}
                        score = 0;
                        bubbleBlasted = 0;
                        shootCount = 0;
                        seconds = 40;
                        counter = 0;
                        init();
                        startTime = cumTime;
                        removefailed = false;
                    }

                    else if (score == 60)
                        {
                            scorePercentage = (int)((15.0/(double)shootCount)*100.0);
                            int Myscore2 = 0;
                            Myscore2 = (int)scorePercentage;
                            counter = 0;
                            if (Myscore2 >= 80.0){
                                Myscore2-=(40-seconds);
                                MyScore+=Myscore2;
                                g = s.getGraphics();
                                paintLevelUpMsg(g);
                                g.dispose();
                                s.update();
                                try {
                                    Thread.sleep(4000);
                                }
                                catch(Exception e){}
                                                                            
                                score = 0;
                                bubbleBlasted = 0;
                                shootCount = 0;
                                seconds = 60;
                                level2 = false;
                                counter = 0 ;
                                level3 = true;
                                //HighScore = true;
                            }
                            else {
                                g = s.getGraphics();
                                paintLevelFailedMsg(g);
                                MyScore-=seconds;
                                g.dispose();
                                s.update();
                                try{
                                    Thread.sleep(4000);
                                }
                                catch(Exception e){}
                                score = 0;
                                bubbleBlasted = 0;
                                shootCount = 0;
                                seconds = 40;
                                counter = 0;
                                init();
                                startTime = cumTime;
                                removefailed = false;
                            }
                        }
                    paint(g);
                    g.dispose();
                    s.update();
                    try
                    {
                        Thread.sleep(25);
                    }
                    catch(Exception e) {}
                    
                  
                }
                
                 init();
                r = new Random();
                startTime = System.currentTimeMillis();
                cumTime = startTime;
                timePassed2 = 0;
                score = 0;
                bubbleBlasted = 0;
                shootCount = 0;
                seconds = 60;
                while (level3 == true && gameRunning == true)
                {
                      Graphics2D g = s.getGraphics();
                        while (removefailed == true){                            
                            //Graphics2D g2 = s.getGraphics();  
                            if (menu == true){
                                menuflag = 1;
                                break;
                            }
                            g = s.getGraphics();
                            paintPauseScreen(g);                          
                            g.dispose();
                            s.update();      
                            try{
                                Thread.sleep(25);
                            }
                            catch(Exception e){}
                        }
                        if (menuflag == 1){
                            break;
                        }
   
                           long timePassed = System.currentTimeMillis() - cumTime;
                        timePassed2 += timePassed;
                        if (timePassed2 >= 1000){
                            --seconds;
                            s.update();
                            g.dispose();
                            timePassed2 = 0;
                        }
                        cumTime += timePassed;
                   
                    updatingClouds();
                    
                    for (int i = 0; i<5 ; ++i){
                       prevX[i] = bb[i].getX();
                       prevY[i] = bb[i].getY();
                    }
                    for (int i = 0; i<5 ; ++i)
                    {
                        bb[i].update();
                    }
                    for (int i =0 ; i<6 ; ++i ){
                        blt[i].update(s, bb, bbdiv, 10, counter);
                    }
                    gn.update(s,bb,this,5);
                    cb.bubbleCollide(bb,5);
                    
                     for (int i = 0; i<5 ; ++i){
                        if (bb[i].hit == true){
                            bbdiv[counter] = new Bubble(prevX[i]+8,prevY[i],s);
                            bbdiv[counter].setdx(3);
                            bbdiv[counter++].setdy(-5);
                            bbdiv[counter] = new Bubble(prevX[i],prevY[i]+9,s);
                            bbdiv[counter].setdx(-3);
                            bbdiv[counter++].setdy(-5);
                            bb[i].hit = false;                
                        }
                    }
                     /* for (int i = 0; i<counter ; ++i){
                          bbdiv[i].update();
                      }*/
                      try{
                         if (counter > 0){
                             for (int i = 0; i<counter ; ++i){
                          bbdiv[i].update();
                      }
                             cb.bubbleCollide2(bbdiv,bb,counter);
                             cb.bubbleCollide3(bbdiv,bb,5,counter);
                             
                         }
                        
                     }
                    catch(Exception ee){}
                      
                    if (seconds == 0){
                        g = s.getGraphics();
                        timeUpMessage(g);
                        MyScore-=(60-seconds);
                        g.dispose();
                        s.update();
                        try{
                            Thread.sleep(4000);
                        }
                        catch(Exception e){}
                        score = 0;
                        bubbleBlasted = 0;
                        shootCount = 0;
                        seconds = 60;
                        counter = 0;
                        init();
                        startTime = cumTime;
                        removefailed = false;
                    }

                    else if (score == 60)
                        {
                            scorePercentage = (int)((15.0/(double)shootCount)*100.0);
                            int Myscore2 = 0;
                            Myscore2 = (int)scorePercentage;
                            counter = 0;
                            if (Myscore2 >= 80.0){
                                Myscore2-=(60-seconds);
                                MyScore+=Myscore2;
                                g = s.getGraphics();
                                paintLevelUpMsg(g);
                                g.dispose();
                                s.update();
                                try {
                                    Thread.sleep(4000);
                                }
                                catch(Exception e){}                                                      
                                score = 0;
                                bubbleBlasted = 0;
                                shootCount = 0;
                                seconds = 60;
                                level3 = false;
                                counter = 0 ;
                                level4 = true;
                                //HighScore = true;
                            }
                            else {
                                g = s.getGraphics();
                                paintLevelFailedMsg(g);
                                MyScore-=(60-seconds);
                                g.dispose();
                                s.update();
                                try{
                                    Thread.sleep(4000);
                                }
                                catch(Exception e){}
                                score = 0;
                                bubbleBlasted = 0;
                                shootCount = 0;
                                seconds = 60;
                                counter = 0;
                                init();
                                startTime = cumTime;
                                removefailed = false;
                            }
                        }
                    paint(g);
                    g.dispose();
                    s.update();
                    try
                    {
                        Thread.sleep(25);
                    }
                    catch(Exception e) {}
                    
                  
                }
                
                 init();
                r = new Random();
                startTime = System.currentTimeMillis();
                cumTime = startTime;
                timePassed2 = 0;
                score = 0;
                bubbleBlasted = 0;
                shootCount = 0;
                seconds = 60;
                while (level4 == true && gameRunning == true)
                {
                      Graphics2D g = s.getGraphics();
                        while (removefailed == true){                            
                            //Graphics2D g2 = s.getGraphics();  
                            if (menu == true){
                                menuflag = 1;
                                break;
                            }
                            g = s.getGraphics();
                            paintPauseScreen(g);                          
                            g.dispose();
                            s.update();      
                            try{
                                Thread.sleep(25);
                            }
                            catch(Exception e){}
                        }
                        if (menuflag == 1){
                            break;
                        }
   
                           long timePassed = System.currentTimeMillis() - cumTime;
                        timePassed2 += timePassed;
                        if (timePassed2 >= 1000){
                            --seconds;
                            s.update();
                            g.dispose();
                            timePassed2 = 0;
                        }
                        cumTime += timePassed;
                   
                    updatingClouds();
                    
                    for (int i = 0; i<5 ; ++i){
                       prevX[i] = bb[i].getX();
                       prevY[i] = bb[i].getY();
                    }
                    for (int i = 0; i<5 ; ++i)
                    {
                        bb[i].update();
                    }
                    for (int i =0 ; i<6 ; ++i ){
                        blt[i].update(s, bb, bbdiv, 10, counter);
                    }
                    gn.update(s,bb,this,5);
                    cb.bubbleCollide(bb,5);
                    
                     for (int i = 0; i<5 ; ++i){
                        if (bb[i].hit == true){
                            bbdiv[counter] = new Bubble(prevX[i]+8,prevY[i],s);
                            bbdiv[counter].setdx(3);
                            bbdiv[counter].setbubblediv40(true);
                            bbdiv[counter++].setdy(-5);
                            bbdiv[counter] = new Bubble(prevX[i],prevY[i]+9,s);
                            bbdiv[counter].setdx(-3);
                            bbdiv[counter].setbubblediv40(true);
                            bbdiv[counter++].setdy(-5);
                            bb[i].hit = false;                
                        }
                    }
                     /* for (int i = 0; i<counter ; ++i){
                          bbdiv[i].update();
                      }*/
                      try{
                         if (counter > 0){
                             for (int i = 0; i<counter ; ++i){
                          bbdiv[i].update();
                      }
                             cb.bubbleCollide2(bbdiv,bb,counter);
                             cb.bubbleCollide3(bbdiv,bb,5,counter);
                             
                         }
                        
                     }
                    catch(Exception ee){}

                      if (seconds == 0){
                            g = s.getGraphics();
                           timeUpMessage(g);
                                MyScore-=(60-seconds);
                                g.dispose();
                                s.update();
                                try{
                                    Thread.sleep(4000);
                                }
                                catch(Exception e){}
                                score = 0;
                                bubbleBlasted = 0;
                                shootCount = 0;
                                seconds = 60;
                                counter = 0;
                                init();
                                startTime = cumTime;
                                removefailed = false;
                      
                      }
                      else if (score == 60)
                        {
                            scorePercentage = (int)((15.0/(double)shootCount)*100.0);
                            int Myscore2 = 0;
                            Myscore2 = (int)scorePercentage;
                            counter = 0;
                            if (Myscore2 >= 80.0){
                                Myscore2-=(60-seconds);
                                MyScore+=Myscore2;
                                g = s.getGraphics();
                                paintLevelUpMsg(g);
                                g.dispose();
                                s.update();
                                try {
                                    Thread.sleep(4000);
                                }
                                catch(Exception e){}                                                     
                                score = 0;
                                bubbleBlasted = 0;
                                shootCount = 0;
                                seconds = 60;
                                level4 = false;
                                counter = 0 ;
                                level5 = true;
                                //HighScore = true;
                            }
                            else {
                                g = s.getGraphics();
                                paintLevelFailedMsg(g);
                                MyScore-=(60-seconds);
                                g.dispose();
                                s.update();
                                try{
                                    Thread.sleep(4000);
                                }
                                catch(Exception e){}
                                score = 0;
                                bubbleBlasted = 0;
                                shootCount = 0;
                                seconds = 60;
                                counter = 0;
                                init();
                                startTime = cumTime;
                                removefailed = false;
                            }
                        }
                    paint(g);
                    g.dispose();
                    s.update();
                    try
                    {
                        Thread.sleep(25);
                    }
                    catch(Exception e) {}
                    
                  
                }
                
                  
                 init();
                r = new Random();
                startTime = System.currentTimeMillis();
                cumTime = startTime;
                timePassed2 = 0;
                score = 0;
                bubbleBlasted = 0;
                shootCount = 0;
                seconds = 60;
                while (level5 == true && gameRunning == true)
                {
                      Graphics2D g = s.getGraphics();
                        while (removefailed == true){                            
                            //Graphics2D g2 = s.getGraphics();  
                            if (menu == true){
                                menuflag = 1;
                                break;
                            }
                            g = s.getGraphics();
                            paintPauseScreen(g);                          
                            g.dispose();
                            s.update();      
                            try{
                                Thread.sleep(25);
                            }
                            catch(Exception e){}
                        }
                        if (menuflag == 1){
                            break;
                        }
   
                           long timePassed = System.currentTimeMillis() - cumTime;
                        timePassed2 += timePassed;
                        if (timePassed2 >= 1000){
                            --seconds;
                            s.update();
                            g.dispose();
                            timePassed2 = 0;
                        }
                        cumTime += timePassed;
                   
                    updatingClouds();
                    
                    for (int i = 0; i<5 ; ++i){
                       prevX[i] = bb[i].getX();
                       prevY[i] = bb[i].getY();
                    }
                    for (int i = 0; i<5 ; ++i)
                    {
                        bb[i].update();
                    }
                    for (int i =0 ; i<6 ; ++i ){
                        blt[i].update(s, bb, bbdiv, 10, counter);
                    }
                    gn.update(s,bb,this,5);
                    cb.bubbleCollide(bb,5);
                    
                     for (int i = 0; i<5 ; ++i){
                        if (bb[i].hit == true){
                            bbdiv[counter] = new Bubble(prevX[i]+8,prevY[i],s);
                            bbdiv[counter].setdx(3);
                            bbdiv[counter].setbubblediv40(true);
                            bbdiv[counter++].setdy(-5);
                            bbdiv[counter] = new Bubble(prevX[i],prevY[i]+9,s);
                            bbdiv[counter].setdx(-3);
                            bbdiv[counter].setbubblediv40(true);
                            bbdiv[counter++].setdy(-5);
                            bb[i].hit = false;                
                        }
                    }
                     /* for (int i = 0; i<counter ; ++i){
                          bbdiv[i].update();
                      }*/
                      try{
                         if (counter > 0){
                             for (int i = 0; i<counter ; ++i){
                          bbdiv[i].update();
                      }
                             cb.bubbleCollide2(bbdiv,bb,counter);
                             cb.bubbleCollide3(bbdiv,bb,5,counter);
                             
                         }
                        
                     }
                    catch(Exception ee){}
                      if (seconds == 0){
                          g = s.getGraphics();
                          timeUpMessage(g);
                                MyScore-=(60-seconds);
                                g.dispose();
                                s.update();
                                try{
                                    Thread.sleep(4000);
                                }
                                catch(Exception e){}
                                score = 0;
                                bubbleBlasted = 0;
                                shootCount = 0;
                                seconds = 60;
                                counter = 0;
                                init();
                                startTime = cumTime;
                                removefailed = false;
                      
                      }
                      else if (score == 60)
                        {
                            scorePercentage = (int)((15.0/(double)shootCount)*100.0);
                            int Myscore2 = 0;
                            Myscore2 = (int)scorePercentage;
                            counter = 0;
                            if (Myscore2 >= 80.0){
                                Myscore2-=(60-seconds);
                                MyScore+=Myscore2;
                                g = s.getGraphics();
                                paintLevelUpMsg(g);
                                g.dispose();
                                s.update();
                                try {
                                    Thread.sleep(4000);
                                }
                                catch(Exception e){}
                                
                                score = 0;
                                bubbleBlasted = 0;
                                shootCount = 0;
                                seconds = 60;
                                level5 = false;
                                counter = 0 ;
                                level6 = true;
                                //HighScore = true;
                            }
                            else {
                                g = s.getGraphics();
                                paintLevelFailedMsg(g);
                                MyScore-=(60-seconds);
                                g.dispose();
                                s.update();
                                try{
                                    Thread.sleep(4000);
                                }
                                catch(Exception e){}
                                score = 0;
                                bubbleBlasted = 0;
                                shootCount = 0;
                                seconds = 60;
                                counter = 0;
                                init();
                                startTime = cumTime;
                                removefailed = false;
                            }
                        }
                    paint(g);
                    g.dispose();
                    s.update();
                    try
                    {
                        Thread.sleep(25);
                    }
                    catch(Exception e) {}
                    
                  
                }
                
                 init();
                r = new Random();
                startTime = System.currentTimeMillis();
                cumTime = startTime;
                timePassed2 = 0;
                score = 0;
                bubbleBlasted = 0;
                shootCount = 0;
                seconds = 60;
                while (level6 == true && gameRunning == true)
                {
                      Graphics2D g = s.getGraphics();
                        while (removefailed == true){                            
                            //Graphics2D g2 = s.getGraphics();  
                            if (menu == true){
                                menuflag = 1;
                                break;
                            }
                            g = s.getGraphics();
                            paintPauseScreen(g);                          
                            g.dispose();
                            s.update();      
                            try{
                                Thread.sleep(25);
                            }
                            catch(Exception e){}
                        }
                        if (menuflag == 1){
                            break;
                        }
   
                           long timePassed = System.currentTimeMillis() - cumTime;
                        timePassed2 += timePassed;
                        if (timePassed2 >= 1000){
                            --seconds;
                            s.update();
                            g.dispose();
                            timePassed2 = 0;
                        }
                        cumTime += timePassed;
                   
                    updatingClouds();
                    
                    for (int i = 0; i<5 ; ++i){
                       prevX[i] = bb[i].getX();
                       prevY[i] = bb[i].getY();
                    }
                    for (int i = 0; i<5 ; ++i)
                    {
                        bb[i].update();
                    }
                    for (int i =0 ; i<6 ; ++i ){
                        blt[i].update(s, bb, bbdiv, 10, counter);
                    }
                    gn.update(s,bb,this,5);
                    cb.bubbleCollide(bb,5);
                    
                     for (int i = 0; i<5 ; ++i){
                        if (bb[i].hit == true){
                            bbdiv[counter] = new Bubble(prevX[i]+8,prevY[i],s);
                            bbdiv[counter].setdx(3);
                            bbdiv[counter].setbubblediv20(true);
                            bbdiv[counter++].setdy(-5);
                            bbdiv[counter] = new Bubble(prevX[i],prevY[i]+9,s);
                            bbdiv[counter].setdx(-3);
                            bbdiv[counter].setbubblediv20(true);
                            bbdiv[counter++].setdy(-5);
                            bb[i].hit = false;                
                        }
                    }
                     /* for (int i = 0; i<counter ; ++i){
                          bbdiv[i].update();
                      }*/
                      try{
                         if (counter > 0){
                             for (int i = 0; i<counter ; ++i){
                          bbdiv[i].update();
                      }
                             cb.bubbleCollide2(bbdiv,bb,counter);
                             cb.bubbleCollide3(bbdiv,bb,5,counter);
                             
                         }
                        
                     }
                    catch(Exception ee){}
                       if (seconds == 0){
                           g = s.getGraphics();
                           timeUpMessage(g);
                                MyScore-=(60-seconds);
                                g.dispose();
                                s.update();
                                try{
                                    Thread.sleep(4000);
                                }
                                catch(Exception e){}
                                score = 0;
                                bubbleBlasted = 0;
                                shootCount = 0;
                                seconds = 60;
                                counter = 0;
                                init();
                                startTime = cumTime;
                                removefailed = false;
                       }
                      else if (score == 60)
                        {
                            scorePercentage = (int)((15.0/(double)shootCount)*100.0);
                            int Myscore2 = 0;
                            Myscore2 = (int)scorePercentage;
                            counter = 0;
                            if (Myscore2 >= 80.0){
                                Myscore2-=(60-seconds);
                                MyScore+=Myscore2;
                                g = s.getGraphics();
                                paintLevelUpMsg(g);
                                g.dispose();
                                s.update();
                                try {
                                    Thread.sleep(4000);
                                }
                                catch(Exception e){}
                                int x[] = new int[10];
                                try {
                                    File f = new File("HighScores.txt");
                                    Scanner scanner = new Scanner(f);               
                                    int i = 0;
                                    while (scanner.hasNext()) { 
                                        x[i++] = scanner.nextInt();                            
                                    }
                                    scanner.close();
                                    //f.close();
                                } 
                                catch (Exception ex) {
                                    System.out.println("ERROR: Level data not found " + ex.getMessage());
                                }
                                Arrays.sort(x);
                                if (MyScore>=x[0]){
                                    madeHistory=true;
                                    
                                    x[0]=MyScore;
                                }
                                showMessage = true;
                                Arrays.sort(x);
                                BufferedWriter outputWriter = null;
                                try{
                                    outputWriter = new BufferedWriter(new FileWriter("HighScores.txt"));
                                    for (int i = 0; i < x.length; i++) {
                                        // Maybe:
                                        //outputWriter.write(x[i]+"");
                                        // Or:
                                        outputWriter.write(Integer.toString(x[i]));
                                        outputWriter.newLine();
                                    }
                                    outputWriter.flush();  
                                    outputWriter.close();  
                                }
                                catch(Exception e){}                                                          
                                score = 0;
                                shootCount = 0;
                                seconds = 60;
                                bubbleBlasted = 0;
                                level6 = false;
                                counter = 0 ;
                                //level7 = true;
                                HighScore = true;
                                gameUpScreen = true;
                            }
                            else {
                                g = s.getGraphics();
                                paintLevelFailedMsg(g);
                                MyScore-=seconds;
                                g.dispose();
                                s.update();
                                try{
                                    Thread.sleep(4000);
                                }
                                catch(Exception e){}
                                score = 0;
                                bubbleBlasted = 0;
                                shootCount = 0;
                                seconds = 60;
                                counter = 0;
                                init();
                                startTime = cumTime;
                                removefailed = false;
                            }
                        }
                    paint(g);
                    g.dispose();
                    s.update();
                    try
                    {
                        Thread.sleep(25);
                    }
                    catch(Exception e) {}
                    
                  
                }
                
                if (gameUpScreen == true){
                    Graphics2D gg = s.getGraphics();
                    gameUpScreen = false;
                    paintGameUp(gg);
                    gg.dispose();
                    s.update();
                    try{
                        Thread.sleep(3000);
                    }
                    catch(Exception e){}
                }
                
                
                while (HighScore == true){
                    Graphics2D g = s.getGraphics();
                    paintHighScores(g);
                    g.dispose();
                    s.update();
                    if (HighScore==false){
                        flag1=1;
                        break;
                    }
                }
            }    
        }

        finally
        {
            s.restoreScreen();
        }
       
         
    }

        /**
         * method to draw all the stuff in the running game
         * @param g : Graphics2D object
         */ 
        public void paint (Graphics2D g)
        {
            if (level5 == true || level6 == true)
                g.drawImage(bg2, 0, 0, null);
            else if (level1 == true || level2 == true)
                g.drawImage(bg, 0, 0, null);
             else if (level3 == true || level4 == true)
                g.drawImage(bg3, 0, 0, null);
            if (level1 == true || level2 == true || level5 == true || level6 == true){
                g.drawImage(megh1,megh1x,megh1y,null);
                g.drawImage(megh2,megh2x,megh2y,null);
                g.drawImage(megh3,megh3x,megh3y,null);
                g.drawImage(megh4,megh4x,megh4y,null);
            }
            /*else if (level5 == true || level6 == true){
                g.drawImage(megh5,megh1x,megh1y,null);
                g.drawImage(megh6,megh2x,megh2y,null);
                g.drawImage(megh7,megh3x,megh3y,null);
                g.drawImage(megh8,megh4x,megh4y,null);
            }*/
            if (level1 == true){
                for (int i = 0 ; i<10 ; ++i ){
                    bb[i].paint(g);
                }
            }
            else if (level2 == true || level3 == true || level4 == true || level5 == true || level6 == true || level7 == true){
                for (int i = 0 ; i<5 ; ++i ){
                    bb[i].paint(g);
                }
            }
           
            if (counter > 0){
                for (int i = 0; i<counter ; ++i){
                    bbdiv[i].paint(g);
                }
            }
            gn.paint(g);
             for (int i =0 ; i<6 ; ++i ){
                blt[i].paint(g);
            }
            Font f = new Font("Monotype Corsiva",Font.BOLD,40);
            g.setFont(f);
            g.setColor(Color.black);
            String str = Integer.toString(seconds);
            g.drawString(str,400-30,50);
            Color cc = new Color(255,193,37);
            g.setColor(cc);
            g.drawString(str,400-28,52);
            f = new Font("Monotype Corsiva",Font.BOLD,25);
            g.setFont(f);
            g.drawString("Press R to PAUSE",400-100,550);
        }
        
        /**
         * method to draw all the stuff in the pause screen
         * @param g : Graphics2D object
         */ 
        public void paintPauseScreen(Graphics2D g){
            g.drawImage(gamePaused,0,0,null);
            g.drawImage(menuCursor,menuCursorX,menuCursorY,null);
            g.setColor(Color.red);
            Font f = new Font("Monotype Corsiva",Font.BOLD,20);
            g.setFont(f);
            g.drawString("MainMenu", 580, 457);
            g.drawString("Restart", 580, 497);
            g.drawString("Back",580,537);
        }
        
        /**
         * method to draw all the stuff in the level up screen
         * @param g : Graphics2D object
         */ 
        public void paintLevelUpMsg(Graphics2D g)
        {
            g.drawImage(levelup1,0,0,null);
            Color cc = new Color(255,215,0);
            g.setColor(cc);
            String str = Integer.toString(MyScore);
            String str2 = Integer.toString(scorePercentage);
            Font f = new Font("Monotype Corsiva",Font.BOLD,30);
            g.setFont(f);
            g.drawString("Your Score : "+str,400-1,150);
            g.drawString("Accuracy : "+str2+"%",400-1,190);
             try {
                                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("gotitem.wav").getAbsoluteFile());
                                Clip clip = AudioSystem.getClip();
                                clip.open(audioInputStream);
                                clip.start();
                            }
                            catch(Exception ex){}
           
        }
        
        /**
         * method to draw all the stuff in the time up screen
         * @param g : Graphics2D object
         */ 
        public void timeUpMessage(Graphics2D g){
            g.drawImage(timeupimage,0,0,null);
             try {
                                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("lostitem.wav").getAbsoluteFile());
                                Clip clip = AudioSystem.getClip();
                                clip.open(audioInputStream);
                                clip.start();
                            }
                            catch(Exception ex){}
        }
        
        /**
         * method to draw all the stuff in the level failed screen
         * @param g : Graphics2D object
         */ 
         public void paintLevelFailedMsg(Graphics2D g)
        {
            
            g.drawImage(levelfailed,0,0,null);
            Color cc = new Color(205,0,0);
            g.setColor(cc);
            String str = Integer.toString(scorePercentage);
            Font f = new Font("Monotype Corsiva",Font.BOLD,30);
            g.setFont(f);
            g.drawString("Accuracy : "+str+"%",400-1,150);
             try {
                                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("lostitem.wav").getAbsoluteFile());
                                Clip clip = AudioSystem.getClip();
                                clip.open(audioInputStream);
                                clip.start();
                            }
                            catch(Exception ex){}
            
        }
         
        /**
         * method to draw all the stuff in the game up Screen
         * @param g : Graphics2D object
         */ 
        public void paintGameUp(Graphics2D g){
            g.drawImage(gameUp, 0, 0, null);
            try {
                                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("fireWorks.wav").getAbsoluteFile());
                                Clip clip = AudioSystem.getClip();
                                clip.open(audioInputStream);
                                clip.start();
                            }
                            catch(Exception ex){}
        }
        
        /**
         * method to draw all the stuff in the main menu
         * @param g : Graphics2D object
         */ 
        public void paintOpeningScreen(Graphics2D g1)
        {
            //g1.drawImage(openingScreen,0,0,null);
            g1.drawImage(highScoreImage,0,0,null);
            g1.drawImage(menuCursor,menuCursorX2,menuCursorY2,null);
            Color cc = new Color(255,193,37);
            g1.setColor(cc);
            //Font f = new Font("Monotype Corsiva",Font.BOLD,20);
            Font f = new Font("Monotype Corsiva",Font.BOLD,60);
            g1.setFont(f);
            g1.drawString("Bubble Shooter",260,150);
            g1.fillRect(264,160,365,3);
            f = new Font("Monotype Corsiva",Font.BOLD,20);
            g1.setFont(f);
            g1.drawString("Play",600+40,360+7);
            g1.drawString("Highscore",600+20,400+7);
            g1.drawString("Instructions",600+10,440+7);
            g1.drawString("Press ESC to Quit",320,550);
            g1.fillRect(30,530,3,50);
            g1.fillRect(770,20,3,50);
            g1.fillRect(30,580,50,3);
            g1.fillRect(720,20,50,3);
        }
        
        /**
         * method to draw all the stuff in the High Score Screen
         * @param g : Graphics2D object
         */ 
        public void paintHighScores(Graphics2D g){
            g.drawImage(highScoreImage,0,0,null);
            int x[] = new int[10];
            try {
                File f = new File("HighScores.txt");
                Scanner scanner = new Scanner(f);
                
                int i = 0;
                while (scanner.hasNext()) { 
                    x[i++] = scanner.nextInt();                            
                }
                scanner.close();
            } 
            catch (Exception ex) {
                System.out.println("ERROR: Level data not found " + ex.getMessage());
            }
            Arrays.sort(x);
            boolean flag = false;
            Color cc = new Color(255,193,37);
                g.setColor(cc);
            for (int j=9,i=0;j>=0;--j,++i){
                if (MyScore == x[j] && flag == false){
                    g.setColor(Color.red);
                    flag = true;
                }
                else {
                    g.setColor(cc);
                }
                String str = Integer.toString(x[j]);
                Font f = new Font("Monotype Corsiva",Font.BOLD,30);
                g.setFont(f);
                 g.drawString(str,30,140+i*40);
            }
             Font f = new Font("Monotype Corsiva",Font.BOLD,60);
                g.setFont(f);
                g.setColor(cc);
                 g.drawString("HighScores", 280, 60);
                 f = new Font("Monotype Corsiva",Font.BOLD,35);
                 g.setFont(f);
            if (madeHistory==true && showMessage == true){
                    g.setColor(Color.red);
                    g.drawString("Congrats !!! You've made history",200,320);
                    g.drawString("Your Score:"+Integer.toString(MyScore),320,350);
                }
                    
                else if (madeHistory==false && showMessage == true){
                    g.setColor(Color.red);
                    g.drawString("Sorry you couldn't make a HighScore",200,320);
                     g.drawString("Your Score:"+Integer.toString(MyScore),320,350);
                }
                f = new Font("Monotype Corsiva",Font.BOLD,30);
                g.setFont(f);
                 g.setColor(cc);
                g.drawString("Press B to go to MainMenu",240,550);
        }
        
        /**
         * method to draw all the stuff in the Instructions
         * @param g : Graphics2D object
         */ 
        public void paintInstructions(Graphics2D g){
            g.drawImage(bg2,0,0,null);
            Font f = new Font("Monotype Corsiva",Font.BOLD,60);
                Color cc = new Color(255,193,37);
                g.setColor(cc);
                g.setFont(f);
                g.drawString("Instructions", 260, 60);
                f = new Font("Monotype Corsiva",Font.BOLD,23);
                g.setFont(f);
                //g.setColor(Color.red);
                g.drawString("1.Use Up and Down keys or move your mouse to move your weapon.",50,200);
                g.drawString("2.Use Space or Left Mouse button to shoot.",50,240);
                g.drawString("3.Shoot all the bubbles with an accuracy of more than 80% to go to next level",50,280);
                g.drawString("4.Try to finish levels as quickly as possible to score more",50,320);
                g.drawString("5.Finish all the levels to place your name in HISTORY!!!!!!!",50,360);
                
                f = new Font("Monotype Corsiva",Font.BOLD,25);
                g.setFont(f);
                g.setColor(cc);
                g.drawString("Press B to go to MainMenu",240,550);
                g.fillRect(30,20,3,50);
            g.fillRect(770,530,3,50);
            g.fillRect(30,20,50,3);
            g.fillRect(723,580,50,3);
        }
        
        /**
         * method to draw all the stuff in the OS screen
         * @param g : Graphics2D object
         */ 
        public void paintOs(Graphics2D g){
            Font f = new Font("Monotype Corsiva",Font.BOLD,25);
                g.setFont(f);
            g.drawImage(bg2,0,0,null);
             Color cc = new Color(255,193,37);
                g.setColor(cc);
            g.drawString("Select your OS",250,200);
            g.drawString("Win7",300,310);
            g.drawString("Win8",300,340);
            g.drawImage(menuCursor,230,menuCursorOs,null);
            
        }
        
        public void keyTyped(KeyEvent e){

        }
        
        /**
         * method to detect which key is pressed by the user 
         * also taking appropriate actions when that key is pressed
         * @param e KeyEvent
         */
        public void keyPressed(KeyEvent e)
        {
            menuSpecial = 0;
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
                if (gameRunning == true && flag1 == 1 && HighScore != true && Instructions != true)
                gameRunning =  false;
            }
            
            if (e.getKeyCode() == KeyEvent.VK_Z){
                os =  false;
                win7 = true;
            }
            
            if (e.getKeyCode() == KeyEvent.VK_Q){
                os =  false;
                win8 = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_R){
                if(removefailed ==  false){
                    removefailed = true;
                }
            }
            
            if (e.getKeyCode() == KeyEvent.VK_B){
                removefailed = false;
                HighScore = false;
                Instructions = false;
                mouseShooter = 0;
            } 
            
            if (e.getKeyCode() == KeyEvent.VK_Q){
                if (removefailed == true){
                    removefailed = false;
                    HighScore = false;
                    Instructions = false;
                    init();
                    score = 0 ;
                    bubbleBlasted = 0;
                    shootCount = 0;
                    if (level1 == true)
                        seconds = 25;
                    else if (level2 == true)
                        seconds = 40;
                    else 
                        seconds = 60;
                    counter = 0;
                }
            }
            
            
                if (e.getKeyCode() == KeyEvent.VK_DOWN){
                    if (os == true){
                        if (menuCursorOs <314)
                            menuCursorOs+=30;
                    }
                    if (flag1==1){
                        if (menuCursorY2 < 422){
                            menuCursorY2+=40;
                        }
                    }
                    if (menuCursorY < 512){
                        menuCursorY+=40;
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_UP){
                    if (os == true){
                        if (menuCursorOs >284)
                            menuCursorOs-=30;
                    }
                    if (flag1==1){
                        if (menuCursorY2 > 342){
                            menuCursorY2-=40;
                        }
                    }
                    if (menuCursorY > 432){
                        menuCursorY-=40;
                    }
                }
             
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (os != true){
                        if (menuCursorY == 432){
                        if (menu == false && removefailed == true){
                            menu = true;
                            flag1 = 1;
                            level1 = false;
                            level2 = false;
                            level3 = false;
                            level4 = false;
                            level5 = false;
                            level6 = false;
                            level7 = false;
                            mouseShooter = 0;
                            menuSpecial = 1;
                        }
                    }
                    if (menuCursorY == 472){
                         if (removefailed == true){
                            removefailed = false;
                            HighScore = false;
                            Instructions = false;
                            init();
                            score = 0 ;
                            bubbleBlasted = 0;
                            shootCount = 0;
                            if (level1 == true)
                        seconds = 25;
                    else if (level2 == true)
                        seconds = 40;
                    else 
                        seconds = 60;
                            counter = 0;
                            //menuSpecial = 1;
                        } 
                    }
                    if (menuCursorY == 512){
                         removefailed = false;
                        HighScore = false;
                        Instructions = false;
                        mouseShooter = 0;
                        //menuSpecial = 1;
                    }
                    if (flag1 == 1 && menuSpecial == 0){
                        if (menuCursorY2 == 342){
                            level1 = true;
                            flag1 = 0;
                            menu = false;
                        }
                        if (menuCursorY2 == 382){
                            HighScore = true;
                        }
                        if (menuCursorY2 == 422){
                            Instructions = true;
                        }
                    }
                    
                    }
                    else {
                        if (menuCursorOs == 284){
                            os = false;
                            win7 = true;
                        }
                        if (menuCursorOs == 314){
                            os = false;
                            win8 = true;
                        }
                    }
                }
                
            
                        
            if (level1==true || level2==true || level4 == true || level6 == true){
                if (e.getKeyCode() == KeyEvent.VK_UP){
                    gn.setY(gn.getY()-6);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN){
                    gn.setY(gn.getY()+6);
                }
            }
            
            if ((level1 ==true || level2 ==true || level3 ==true || level4 ==true || level5 ==true || level6 ==true)&& removefailed != true && flag1 != 1){
                if (e.getKeyCode() == KeyEvent.VK_SPACE){
                    for (int i=0 ; i<6 ; ++i){
                        if (blt[i].getX()>800){
                            blt[i].shoot(gn.getX(),gn.getY(),gn.getWidth(),gn.getHeight(),8);
                            ++shootCount;
                            try {
                                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("arrowsound2.wav").getAbsoluteFile());
                                Clip clip = AudioSystem.getClip();
                                clip.open(audioInputStream);
                                clip.start();
                            }
                            catch(Exception ex){}
                            break;
                        }
                    } 
                }
            }
        }
        
        /**
         * method to detect which key is released by the user 
         * also taking appropriate actions when that key is pressed
         * @param e KeyEvent
         */
        public void keyReleased(KeyEvent e){
              if (e.getKeyCode() == KeyEvent.VK_M){
                if (menu == false && removefailed == true){
                    menu = true;
                    flag1 = 1;
                    level1 = false;
                    level2 = false;
                    level3 = false;
                    level4 = false;
                    level5 = false;
                    level6 = false;
                    level7 = false;
                    mouseShooter = 0;
                }
            }
        }

        public  void mousePressed(MouseEvent e)
        {
           
        }

        /**
         * method to detect which mouse is clicked by the user 
         * also taking appropriate actions when the mouse is clicked
         * @param e Mouse Event
         */
        public void mouseClicked(MouseEvent e){
            
            
            if ((level1==true || level2==true || level3==true || level4==true || level5==true || level6==true || level7==true) && flag1 != 1 && removefailed != true){
                for (int i=0 ; i<6 ; ++i){                   
                    if (blt[i].getX()>800){
                        blt[i].shoot(gn.getX(),gn.getY(),gn.getWidth(),gn.getHeight(),8);
                        ++shootCount;
                        try {
                            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("arrowsound2.wav").getAbsoluteFile());
                            Clip clip = AudioSystem.getClip();
                            clip.open(audioInputStream);
                            clip.start();
                        }
                        catch(Exception ex){}
                        break;
                    }
                }
            }
            /*if (os == true){
                if (menuCursorOs == 284){
                    os = false;
                    win7 = true;
                }
                if (menuCursorOs == 314){
                    os = false;
                    win8 = true;
                }
            }*/
        }
        
        /**
         * method to detect which mouse is moved by the user 
         * also taking appropriate actions when the mouse is moved
         * @param e MouseEvent
         */
        public  void mouseMoved(MouseEvent e)
        {
            
            try{
                
            if (level1==true || level2==true|| level4==true || level6==true)
                gn.setY((int)e.getY());
            }
            catch(Exception ex){}
            
            /*if (e.getY()<284)
                menuCursorOs = 284;
            if (e.getY()>284)
                menuCursorOs = 314;*/
            
        }

        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mouseDragged(MouseEvent e) {}

    }
