import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CFlip{
  private JFrame stats;
  private ImageIcon iBack;
  private JLabel lBack;
  private JPanel bPanel;
  private RJpanel Cj;
  private MyThread thread;

  private boolean letB;
  private int countFlip;
  
  private JButton flipB;
  
  public CFlip(){
    countFlip = 0; //keeps track of how many times coin is flipped
    letB = true; //deactivates button/runs thread
    stats = new JFrame();
    //change this to start screen
    iBack = new ImageIcon("stats/godzilla approach.png");
    lBack = new JLabel(iBack);
    bPanel = new JPanel(); 
    Cj = new RJpanel(("stats/quarter front 1.png"),("stats/quarter front 2.png"),("stats/quarter front 3.png"),("stats/quarter back 3.png"),("stats/quarter back 2.png"),("stats/quarter back 1.png"), ("stats/quarter back 2 f.png"),("stats/quarter back 3 f.png"),("stats/quarter front 3 f.png"),("stats/quarter front 2 f.png")); //parameters for creating animation
    
    flipB = new JButton(new ImageIcon("screens/next.png"));
  }

  
  public void startCFlip(){
    listeners();
    stats.setSize(new Dimension(600, 600));
    stats.setTitle("Heads or Tales");
    stats.add(lBack);
    //creates frame and sets background
    
    flipB.setBackground(Color.BLACK);
    bPanel.setBackground(Color.BLACK);
    //sets color
    
    bPanel.add(flipB);
    stats.add(bPanel, BorderLayout.SOUTH);
    stats.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    stats.setVisible(true);
    //System.out.println("Start complete");
    stats.pack();
    JOptionPane.showMessageDialog(stats, "Godzilla is your stats teacher. He sometimes calls himself Statszilla", "Dude its STATSZILLA", JOptionPane.INFORMATION_MESSAGE);
    //adds button to jpanel, adds jpanal to frame, and makes windows appear/closeable
  }
  
  public void play(){ //this method plays everytime a button is pressed and you have not won three times (two at the moment)
    //System.out.println("play");
    stats.remove(lBack);
    stats.add(Cj);
    stats.pack();
    Cj.setCount(); //sets count to ensure the rps animation starts at 0 and all if statements work
    Cj.startGameThread(); //starts game loop
    
  }
  
  public class MyThread extends Thread{
     public void run(){
      while (thread != null){
        while (letB == false){
          if (Cj.rEnded() == true){ //checks if the animation has ended
            letB = true; //reactivates button
            bPanel.add(flipB); //readds button
          }
          stats.pack();
          
          }
        
          stats.remove(Cj);
        //stats.pack();
          //System.out.print("barf");
          if (countFlip == 1){ //displats coin face depending on turn. It is fixed and you win everytime
            lBack.setIcon(new ImageIcon("stats/godzilla win.png"));
          } else if (countFlip == 2){
            lBack.setIcon(new ImageIcon("stats/godzilla lose 1.png"));
          } else if (countFlip == 3){
            lBack.setIcon(new ImageIcon("stats/godzilla lose 2.png"));
          } 
        
        stats.add(lBack);
        stats.repaint();
        stats.pack();
        thread = null;
        }
     }
  }

  
  public void listeners(){
    ActionListener flipListener = new ActionListener(){
      @Override 
      public void actionPerformed(ActionEvent e){
        if (countFlip <= 2 && letB == true){
          if (countFlip == 0){ //tells you you are heads, and changes next to flip
            JOptionPane.showMessageDialog(stats, "Godzilla is heads and you are tails.", "Which Side", JOptionPane.INFORMATION_MESSAGE);
            flipB.setIcon(new ImageIcon("stats/flip.png"));
          }
          if (countFlip == 2){ //once you win buttion goes back to next
            flipB.setIcon(new ImageIcon("screens/next.png"));
          }
          thread = new MyThread(); //creates thread to remove button during animation
          Cj.setPlayedOnce(); //tells cj that the game has been played
          letB = false; //deactivates button
          bPanel.remove(flipB); //removes button
          countFlip++;
          play(); //runs animation method and adds animation to jframe
          thread.start(); //runs thread from above
        } else if (countFlip == 3){
          countFlip++;
          lBack.setIcon(new ImageIcon("stats/zilla face.png"));
        } else if (countFlip == 4){
          countFlip++;
          stats.setVisible(false);
          Day35 d35 = new Day35();
          d35.endDay();
        }
      }
    };
    flipB.addActionListener(flipListener);
  }
}