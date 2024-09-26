import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Day3{
  private JButton nextB;
  private int nextCheck;
  private JFrame days; 
  private ImageIcon iBack;
  private JLabel lBack;
  private JPanel bPanel;
  
  public Day3(){
    days = new JFrame();
    iBack = new ImageIcon("screens/day 3.png");
    lBack = new JLabel(iBack); //needed to create the background, converts imageicon to jlabel
    nextB = new JButton(new ImageIcon("screens/next.png"));
    nextCheck = 0;
    bPanel = new JPanel();
  }

  public void startDay3(){
    days.setSize(new Dimension(600, 600));
    days.setTitle("Day 3");
    //creates frame
    lBack.setBounds(0, 0, iBack.getIconWidth(), iBack.getIconHeight());
    days.add(lBack);
    //sets background
    nextB.setBackground(Color.BLACK);
    //sets background of buttons to black

    //creates jpanel for buttons
    bPanel.add(nextB);
    //sets jpanel background to black
    bPanel.setBackground(Color.BLACK);
    days.add(bPanel, BorderLayout.SOUTH);
    //moves jpanel to bottom could not find a better way to move it
    
    days.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    days.repaint();
    days.pack();
    days.setVisible(true);
    listeners();
    }

  public void end(){
    nextCheck = 3;
    listeners();
    days.setSize(new Dimension(600, 600));
    days.setTitle("Aliens");
    //creates frame
    lBack.setBounds(0, 0, iBack.getIconWidth(), iBack.getIconHeight());
    bPanel.add(nextB);
    lBack.setIcon(new ImageIcon("rps/art teacher abduction.png"));
    days.add(bPanel, BorderLayout.SOUTH);
    nextB.setBackground(Color.BLACK);
    bPanel.setBackground(Color.BLACK);
    //makes button backgrounds black and sets background
    days.add(lBack);
    days.setVisible(true);
    
  }
  
  public void listeners(){
    ActionListener nListener = new ActionListener(){
      @Override 
      public void actionPerformed(ActionEvent e){
        if (nextCheck == 0){ //art teacher breaks in
          nextCheck++;
          lBack.setIcon(new ImageIcon("screens/bedroom arts teacher with paint.png"));
          JOptionPane.showMessageDialog(days, "There is no time to wake up. You'r art teacher has broken into your room and is \nchallenging you to a battle. ", "Danger! Danger! Danger! Danger!", JOptionPane.WARNING_MESSAGE);  

        } else if (nextCheck == 1){ //hallway screen
          nextCheck++;
          lBack.setIcon(new ImageIcon("screens/hallway and art teacher.png"));
          days.pack();
          bPanel.repaint();
        } else if (nextCheck == 2){ //calls rock paper scissors
          nextCheck++;
          RPS r  = new RPS();
          r.startRPS();
          days.setVisible(false);
        } else if (nextCheck == 3){ //after rock paper scissors in meadow
          nextCheck++;
          lBack.setIcon(new ImageIcon("screens/meadows.png"));
          days.repaint();
          bPanel.repaint();
        JOptionPane.showMessageDialog(days, "You have defeated the arts and crafts teacher and the view is beautiful", "Its Beautiful", JOptionPane.INFORMATION_MESSAGE);  
          JOptionPane.showMessageDialog(days, "But wait!! You feel the ground shake and see your stats teacher standing\nabove you!!!", "Oh NO", JOptionPane.WARNING_MESSAGE); 
        } else if (nextCheck == 4){ //calls day 3.5 with statszilla
          days.setVisible(false);
          Day35 d = new Day35();
          d.startDay35();
        }
      }
    };
    nextB.addActionListener(nListener);
  }
}