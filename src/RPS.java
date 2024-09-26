import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RPS{
  private int twoWin;
  private boolean switchNext;
  private int com; //computer guess
  private boolean win;
  private boolean tie;
  
  private JFrame rps;
  private ImageIcon iBack;
  private JLabel lBack;
  private JPanel bPanel;
  private RJpanel Rj;
  
  private JButton rock;
  private JButton paper;
  private JButton scissor;
  private JButton next;
  
  public RPS(){
    twoWin = 0;
    switchNext = true;
    com = 0;
    win = false;
    tie = false;
    
    rps = new JFrame();
    //change this to start screen
    iBack = new ImageIcon("screens/rps screen.png");
    lBack = new JLabel(iBack);
    bPanel = new JPanel(); 
    Rj = new RJpanel(("rps/rock.png"),("rps/paper.jpg"),("rps/scissor.jpg"));
    
    rock = new JButton(new ImageIcon("rps/rockb.png"));
    paper = new JButton(new ImageIcon("rps/paperb.png"));
    scissor = new JButton(new ImageIcon("rps/scissorb.png"));
    next = new JButton(new ImageIcon("screens/next.png"));
    //don't forget to add images to buttons and frame
  }

  
  public void startRPS(){
    listeners();
    rps.setSize(new Dimension(600, 600));
    rps.setTitle("Rock Paper and last but not least Scissors");
    rps.add(lBack);
    
    //creates frame and sets background
    
    next.setBackground(Color.BLACK);
    rock.setBackground(Color.BLACK);
    paper.setBackground(Color.BLACK);
    scissor.setBackground(Color.BLACK);
    bPanel.setBackground(Color.BLACK);
    //sets color
    
    bPanel.add(next);
    rps.add(bPanel, BorderLayout.SOUTH);
    rps.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    rps.setVisible(true);
    //System.out.println("Start complete");
    rps.pack();
    //adds button to jpanel, adds jpanal to frame, and makes windows appear/closeable
  }
  
  public void play(){ //this method plays everytime a button is pressed and you have not won three times (two at the moment)
    bPanel.remove(next);
    bPanel.add(rock);
    bPanel.add(paper);
    bPanel.add(scissor);
    //removes next adds rps choices
    Rj.startGameThread();
    rps.add(Rj);
    rps.pack();
    //starts game (most importantly starts game loop and this is all in another thread)
    //bPanel.repaint();
    rps.pack();
    //System.out.println("before cycle");
    com = (int)(Math.random()*3);
    //this is the computer's choice for rps
  }

  public void winScreen(){ //plays after everybutton press and dictates if play is played or the player has won
    
    
    if (win == true){
      twoWin++;
    } else if (tie == true){
      tie = false;
    } else {
      twoWin = 0;
      //three win gets checked and if it is 32 then the player wins (this can be found in the next button listener)
    }
  } 

  public void listeners(){
    ActionListener rockListener = new ActionListener(){
      @Override 
      public void actionPerformed(ActionEvent e){
        bPanel.remove(rock);
        bPanel.remove(paper);
        bPanel.remove(scissor);
        bPanel.add(next);
        rps.repaint();
        bPanel.repaint();
        Rj.stop();
        rps.remove(Rj);
        //stops game thread and removes it from panel to display background
        if (com == 2){ //checks if this is the winning for the actionlistener (this is rock, and for rock to win you must choose scissors which is 2)
          //System.out.println("w");
          win = true;
          lBack.setIcon(new ImageIcon("rps/scissor win.png"));
          rps.setSize(new Dimension(600, 600));
          lBack.setBounds(0, 0, 600, 600);
          JOptionPane.showMessageDialog(rps, "Computer chose scissors, you win", "W", JOptionPane.INFORMATION_MESSAGE); 
        } else if (com == 0) { //checks for tie
          tie = true;
          win = false;
          lBack.setIcon(new ImageIcon("rps/rock win.png"));
          rps.setSize(new Dimension(600, 600));
          lBack.setBounds(0, 0, 600, 600);
          JOptionPane.showMessageDialog(rps, "Computer chose rock, you tie", "Tie", JOptionPane.INFORMATION_MESSAGE);  
        } else { //losing statement
          //System.out.println("l");
          win = false;
          lBack.setIcon(new ImageIcon("rps/paper win.png"));
          rps.setSize(new Dimension(600, 600));
          lBack.setBounds(0, 0, 600, 600);
          JOptionPane.showMessageDialog(rps, "Computer chose paper moron, you lose", "L", JOptionPane.INFORMATION_MESSAGE);  
        } //else statments show if you win or not in every listener (they are almost identical)
        winScreen();
      }
    };
    
    ActionListener paperListener = new ActionListener(){ //same as rock listener
      @Override 
      public void actionPerformed(ActionEvent e){
        bPanel.remove(rock);
        bPanel.remove(paper);
        bPanel.remove(scissor);
        bPanel.add(next);
        rps.repaint();
        bPanel.repaint();
        Rj.stop();
        rps.remove(Rj);
        if (com == 0){
          //System.out.println("w");
          win = true;
          lBack.setIcon(new ImageIcon("rps/rock win.png"));
          rps.setSize(new Dimension(600, 600));
          lBack.setBounds(0, 0, 600, 600);
          JOptionPane.showMessageDialog(rps, "Computer chose rock, you win", "W", JOptionPane.INFORMATION_MESSAGE);  
        } else if (com == 1) {
          tie = true;
          win = false;
          lBack.setIcon(new ImageIcon("rps/paper win.png"));
          rps.setSize(new Dimension(600, 600));
          lBack.setBounds(0, 0, 600, 600);
          JOptionPane.showMessageDialog(rps, "Computer chose paper, you tie", "Tie", JOptionPane.INFORMATION_MESSAGE);  
        } else {
          //System.out.println("l");
          win = false;
          lBack.setIcon(new ImageIcon("rps/scissor win.png"));
          bPanel.add(next);
          rps.setSize(new Dimension(600, 600));
          lBack.setBounds(0, 0, 600, 600);
          JOptionPane.showMessageDialog(rps, "Computer chose scissors moron, you lose", "L", JOptionPane.INFORMATION_MESSAGE);  
        }
        winScreen();
      }
    };
    
    ActionListener scissorListener = new ActionListener(){ //same as other rock listener
      @Override 
      public void actionPerformed(ActionEvent e){
        bPanel.remove(rock);
        bPanel.remove(paper);
        bPanel.remove(scissor);
        bPanel.add(next);
        rps.repaint();
        bPanel.repaint();
        Rj.stop();
        rps.remove(Rj);
        if (com == 1){
          //System.out.println("w");
          win = true;
          lBack.setIcon(new ImageIcon("rps/paper win.png"));
          rps.setSize(new Dimension(600, 600));
          lBack.setBounds(0, 0, 600, 600);
          JOptionPane.showMessageDialog(rps, "Computer chose paper, you win", "W", JOptionPane.INFORMATION_MESSAGE); 
        } else if (com == 2) {
          tie = true;
          win = false;
          lBack.setIcon(new ImageIcon("rps/scissor win.png"));
          rps.setSize(new Dimension(600, 600));
          lBack.setBounds(0, 0, 600, 600);
          JOptionPane.showMessageDialog(rps, "Computer chose scissors, you tie", "Tie", JOptionPane.INFORMATION_MESSAGE); 
        } else {
          //System.out.println("l");
          win = false;
          lBack.setIcon(new ImageIcon("rps/rock win.png"));
          rps.setSize(new Dimension(600, 600));
          lBack.setBounds(0, 0, 600, 600);
          JOptionPane.showMessageDialog(rps, "Computer chose rock moron, you lose", "L", JOptionPane.INFORMATION_MESSAGE);  
        }
        winScreen();
      }
    };
    
    ActionListener nextListener = new ActionListener(){
      @Override 
      public void actionPerformed(ActionEvent e){
        if (twoWin != 2){ //if you haven't won three times plays play (for now two)
          play();
        } else if (twoWin == 2 && switchNext == true){ //once you win three times if statement runs which shows trophy 
          //System.out.println("wowie");
          rps.setSize(new Dimension(600, 600));
          lBack.setIcon(new ImageIcon("rps/trophy for art.png"));
          lBack.setBounds(0, 0, 600, 600);
          rps.repaint();
          rps.revalidate();
          //displays that you won three times
          switchNext = false;
        } else if (switchNext == false){ //swtichnext is made false when you have won three times and this statement is called 
          rps.setVisible(switchNext);
          Day3 d = new Day3();
          d.end();
          
          //closes the screen
        }
      }
    };
    rock.addActionListener(rockListener);
    paper.addActionListener(paperListener);
    scissor.addActionListener(scissorListener);
    next.addActionListener(nextListener);
  }
}