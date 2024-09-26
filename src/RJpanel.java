import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.lang.Thread;

public class RJpanel extends JPanel implements Runnable{
  Thread gameThread;
  private int fps;
  private RAnimation rpsAnimation;
  private int fofun;
  private int height;
  private int width;
  
  public RJpanel(String... args){
    fps = 60;
    rpsAnimation = new RAnimation(args);
    fofun = 0;
    height = 600;
    width = 600;
    this.setPreferredSize(new Dimension(width, height));
    this.setBackground(Color.BLACK);
    this.setDoubleBuffered(true);
    //c.RAnimationSet();
  }

  public void startGameThread(){ //creates a new thread for the game to run independantly
    gameThread = new Thread(this);
    gameThread.start(); //calls run method
  }
  
  public void run(){ //creates game loop
    double drawInterval = 1000000000/fps; //0.0166 seconds
    double nextDrawTime = System.nanoTime() + drawInterval;
    
    while (gameThread != null){  //runs anything in here every drawInterval
      update();
      repaint(); //calls paintcomponent
      try {
        double remainingTime = nextDrawTime - System.nanoTime();
        remainingTime = remainingTime/1000000; //calculates how long the program must sleep for
        if (remainingTime < 0) {
          remainingTime = 0;
        }
        Thread.sleep((long)remainingTime); //stops the program for remainTime
        nextDrawTime += drawInterval;
      } catch (InterruptedException e){
        e.printStackTrace();
      }
    }
  }

  public void stop(){ //stops game loop when called
    gameThread = null;
    //System.out.println("bye");
  }
    
  public void update(){ //calls animation's update every tick
    rpsAnimation.update();
    if (rpsAnimation.stopFlipMethod() == 3){
      //System.out.println(rpsAnimation.stopFlipMethod());
      stop();
    }
  }

  public void setCount(){ //sets count of animation to zero
    rpsAnimation.setCountFlip(0);
  }

  public void setPlayedOnce(){ //sets rps animation has or has not run once
    rpsAnimation.setPlayedOnce();
  }
  
  public boolean rEnded(){ //returns if rps animation has ended or not
    return rpsAnimation.ended();
  }

  public void paintComponent(Graphics g){ //repaints the screen every tick
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    rpsAnimation.draw(g2); //draws graphcis, then gets rid of it for the next graphic
    g2.dispose();
  }
}