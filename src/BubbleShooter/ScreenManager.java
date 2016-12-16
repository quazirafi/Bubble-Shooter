package BubbleShooter;




import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JFrame;

/**
 * this class is used to give you the screen
 */
public class ScreenManager {
    private GraphicsDevice vc;
    
    /**
     * constructor for this class
     */
    public ScreenManager (){
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        vc = e.getDefaultScreenDevice();
        
    }
    
    /**
     * gets you all the display modes that your pc has
     */ 
    public DisplayMode[] getCompatibleDisplayModes(){
        return vc.getDisplayModes();
    }
    
    /**
     * gets you the display mode compatible with your current settings
     * @param DisplayMode : DisplayMode of PC
     */ 
    public DisplayMode findFirstCompatibleMode(DisplayMode modes[]){
        DisplayMode goodModes[] = vc.getDisplayModes();
        for (int x=0;x<modes.length;++x){
            for (int y=0;y<goodModes.length;++y){
                if (displayModesMatch(modes[x],goodModes[y])){
                    return (modes[x]);
                }
            }   
        }
        return null;
    }
    
    /**
     * 
     * @return : returns the DisplayMode that your PC currently has
     */ 
    public DisplayMode getCurrentDisplayMode(){
        return vc.getDisplayMode();
    }
    
    /**
     * 
     * @param m1 : DisplayMode object
     * @param m2 : DisplayMode object
     * @return : whether the displayModes match or not
     */
    public boolean displayModesMatch(DisplayMode m1,DisplayMode m2){
        if (m1.getWidth() != m2.getWidth() || m1.getHeight() != m2.getHeight()){
            return false;
        }
        if (m1.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && m2.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && m1.getBitDepth() != m2.getBitDepth()){
            return false;
        }
        if (m1.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && m2.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && m1.getRefreshRate() != m2.getRefreshRate()){
            return false;
        }
        return true;
    }
    
    /**
     * sets the Window to FullScreen
     * @param dm : DisplayMode object
     */
    public void setFullScreen (DisplayMode dm){
        JFrame f = new JFrame();
        f.setUndecorated(true);
        f.setIgnoreRepaint(true);
        f.setResizable(false);
        vc.setFullScreenWindow(f);
        
        if (dm!=null && vc.isDisplayChangeSupported()){
            try {
                vc.setDisplayMode(dm);
                
            }catch(Exception e){}
        }
        f.createBufferStrategy(2);
        
    }
    
    
    /**
     * gets you the graphics settings of your pc
     * @return : The Graphics settings of your PC
     */
    public Graphics2D getGraphics (){
        Window w = vc.getFullScreenWindow();
        if (w!=null){
            BufferStrategy s = w.getBufferStrategy();
            return (Graphics2D)s.getDrawGraphics();
        }
        else 
            return null;
    }
    
            /**
             * updates the screen of your PC
             */
    public void update(){
        Window w = vc.getFullScreenWindow();
        if (w!=null){
            BufferStrategy s = w.getBufferStrategy();
            if (!s.contentsLost()){
                s.show();
            }
        }
    }
    
    //gets you the fullscreenwindow
    /**
     * 
     * @return : The window where you are playing
     */
    public Window getFullScreenWindow(){
        return vc.getFullScreenWindow();
    }
    
    
    /**
     * 
     * @return : returns the width of your screen
     */
    public int getWidth (){
        Window w = vc.getFullScreenWindow();
        if (w!=null){
            return w.getWidth();
        }
        else {
            return 0;
        }
    }
    
    /**
     * 
     * @return : returns the height of your screen
     */
    public int getHeight (){
        Window w = vc.getFullScreenWindow();
        if (w!=null){
            return w.getHeight();
        }
        else {
            return 0;
        }
    }
    
    /**
     * restores your screen
     */
    public void restoreScreen (){
        Window w = vc.getFullScreenWindow();
        if (w!=null){
            w.dispose();
        }
        vc.setFullScreenWindow(null);
    }
    
    /**
     * 
     * @param w : width 
     * @param h : height
     * @return : BufferedImage Object
     */
    public BufferedImage createCompatibleImage(int w,int h,int t){
        Window win = vc.getFullScreenWindow();
        if (win!=null){
            GraphicsConfiguration gc = win.getGraphicsConfiguration();
            return (gc.createCompatibleImage(w, h, t));
        }
        return null;
    }
}

