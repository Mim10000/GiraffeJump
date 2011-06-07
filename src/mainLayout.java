import java.awt.*;
import java.applet.*;
import java.util.Random;
import javax.swing.Timer;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.lang.Thread.sleep;


import javax.imageio.ImageIO;

public class mainLayout extends Applet implements KeyListener,Runnable 
{
    Thread runner;
    private Image Buffer;
    private Graphics gBuffer;
    Random n=new Random();
    private int[] Giry_pos = new int[1];    
    
    private  long timer; 
    private long cTime;
    
    private boolean jumping;    

    public void keyTyped(KeyEvent evt) {
        char key = evt.getKeyChar();
        if (key == 'a' && jumping == false)
        {
        	jumping = true;
        	
             cTime = System.currentTimeMillis();

        	//Giraffe Jump Part1
        	while((cTime+1000) > timer)  //moves 20 pixils up
        	{        		
            	timer = System.currentTimeMillis(); //updates 
        		
            	Giry_pos[0] -= 2;
            	System.out.println(Giry_pos[0]);
            	
            	 this.repaint();
            	try
                {
                     sleep(100);
                }
               catch (InterruptedException ex)
                    {
                        System.out.println("interrupted");
                    }
               repaint();
        	}
             cTime = System.currentTimeMillis();
        	
        	while((cTime+1000) > timer)  //moves 20 pixils up
        	{        		
            	timer = System.currentTimeMillis();
        		
            	Giry_pos[0] += 2;
            	System.out.println(Giry_pos[0]);
                this.repaint();
            	
            	try
                {
                     sleep(100);
                }
               catch (InterruptedException ex)
                    {
                        System.out.println("interrupted");
                    }
              
        	}
        	jumping = false;
            repaint();
        }
        else if (key == 'd')
        {
        	
        }
}

public void keyPressed(KeyEvent evt) {
}

public void keyReleased(KeyEvent evt) {
}   
    
    
    //Init is called first, do any initialisation here
    public void init()
    {
            setVisible(true);
            addKeyListener(this);
            //create graphics buffer, the size of the applet
            Buffer=createImage(size().width,size().height);
            gBuffer=Buffer.getGraphics();
    }

    public void start()
    {
    		Giry_pos[0]=225;

            if (runner == null)
            {
                runner = new Thread (this);
                runner.start();
            }
    }
    public void stop()
    {                
           if (runner != null)
           {
                runner.stop();
                runner = null;
           }
    }
    public void run()
    {
    	
    	timer = System.currentTimeMillis();
    		
            //paint background blue
            gBuffer.setColor(Color.blue);
            gBuffer.fillRect(0,0,800,400);

            //move red circle down until it
            //leaves applet then let it reappear at the top in another random location
         
            //Giraffe
            gBuffer.setColor(Color.black);
            gBuffer.fillRect(150, Giry_pos[0], 150, 150);
           
            //Road
            gBuffer.setColor(Color.gray);
            gBuffer.fillRect(0,375, 800, 25);
            
            //tank 
            BufferedImage tank = null;
            
            try {
            	tank = ImageIO.read(new File("pictures/tankGreen.png"));
            } catch (IOException e) 
            {
            	System.out.println("File note found!");
            }
           
            gBuffer.drawImage(tank, 625, 325, Color.blue,this);
            
            gBuffer.setColor(Color.green);
           // gBuffer.fillOval(625, 325, 50, 50);
            gBuffer.drawString("Score:",700,30);
            
            repaint();     
    }

    //is needed to avoid erasing the background by Java
    public void update(Graphics g)
    {             
            paint(g);
    }

    public void paint(Graphics g)
    {
			System.out.println("Workin?");

            g.drawImage (Buffer,0,0, this);
    }
}