import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class RAnimation{
  private BufferedImage[] images; //array of images
  private int kTrack; //ticks
  private int countFlip;
  private int detSide;
  private BufferedImage back;
  private boolean playedOnce;

  public RAnimation(String args[]){ //accepts any amount of strins
    kTrack = 0;
    countFlip = 0;
    detSide = 0;
    playedOnce = false;
    images = new BufferedImage[args.length];
    for (int i = 0; i < args.length; i++){
      try{
        images[i] = ImageIO.read(new File(args[i]));
      } catch (IOException e) {
        e.printStackTrace();
      } //sets array equal to the strings contanied in args which become files
    }
    try{
        back = ImageIO.read(new File("stats/white.png"));
      } catch (IOException e) {
        e.printStackTrace();
      } 
  }

  public int stopFlipMethod(){ //returns the amount of times the animation has run(used for understanding when the game stops)
    return countFlip;
  }
  
  public void setCountFlip(int n){ //sets count to whatever the parameter is (to zero as it is called whenever the game begins)
    countFlip = n;
  }

  public void setPlayedOnce(){ //gurantees if statements below won't run during the wrong time (i was having a lot of issues with that)
    playedOnce = false;
  }
  
  public void draw(Graphics2D g2){
    BufferedImage image = null;
    if (images.length == 3){
      if (kTrack < 33){
        image = images[0];
      } else if (kTrack < 66){
        image = images[1];
      } else if (kTrack < 99){
        image = images[2];
      } 
    } else if (images.length > 3){
      if (kTrack < 10) {
        image = images[0];
      } else if (kTrack < 20) {
        image = images[1];
      } else if (kTrack < 30) {
        image = images[2];
      } else if (kTrack < 40) {
        image = images[3];
      } else if (kTrack < 50) {
        image = images[4];
      } else if (kTrack < 60) {
        image = images[5];
      } else if (kTrack < 70) {
        image = images[6];
      } else if (kTrack < 80) {
        image = images[7];
      } else if (kTrack < 90) {
        image = images[8];
      } else if (kTrack < 100) {
        image = images[9];
      } 
      if (countFlip == 3 && detSide == 0 && playedOnce == false){
        detSide++;
        image = images[3];
        //System.out.println("detSide 1: " + detSide); 
        playedOnce = true;
      } else if (countFlip == 3 && detSide == 1 && playedOnce == false){
        detSide++;
        image = images[5];
        //System.out.println("detSide 2: " + detSide);
        playedOnce = true;
      } else if (countFlip == 3 && detSide == 2 && playedOnce == false){
        detSide++;
        image = images[5];
        //System.out.println("detSide 3: " + detSide);
        playedOnce = true;
      }
    }
      //ktrack can only get to 100, the if statements are thirds of 100. Since ktrack goes up every 0.0166... seconds this will change the image every third of a second. (draw is called every tick)
    g2.drawImage(back, 0, 0, null);
    g2.drawImage(image, (600 - image.getWidth())/2, (600 - image.getHeight())/2, image.getWidth(), image.getHeight(), null);
  }

  public boolean ended(){ //returns that the program has done its animation loop three times
    return (countFlip == 3);
  }
      
  public void update(){
    //System.out.println(kTrack);
    kTrack++;
    if (kTrack >= 99 && images.length == 3){
      kTrack = 0;
    } else if (kTrack >= 100 && images.length > 3){
      kTrack = 0;
      countFlip++;
    }//limits ktrack to 99
  }
}