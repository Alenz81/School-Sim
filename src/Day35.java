import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Day35{
  private JButton nextB;
  private int nextCheck;
  private JFrame days; 
  private ImageIcon iBack;
  private JLabel lBack;
  private JPanel bPanel;
  private CFlip c;
  private int endCheck;
  
  public Day35(){
    days = new JFrame();
    iBack = new ImageIcon("screens/day 35.png");
    lBack = new JLabel(iBack); //needed to create the background, converts imageicon to jlabel
    nextB = new JButton(new ImageIcon("screens/next.png"));
    nextCheck = 0;
    bPanel = new JPanel();
    c = new CFlip();
    endCheck = 0;
  }

  public void startDay35(){
    days.setSize(new Dimension(600, 600));
    days.setTitle("Day 3.5");
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

  public void endDay(){
    endCheck = 1;
    days.setSize(new Dimension(600, 600));
    days.setTitle("Day 3.5");
    //creates frame
    lBack.setBounds(0, 0, iBack.getIconWidth(), iBack.getIconHeight());
    lBack.setIcon(new ImageIcon("stats/long day.png"));
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

  public void listeners(){
    ActionListener nListener = new ActionListener(){
      @Override 
      public void actionPerformed(ActionEvent e){
        if (nextCheck == 0 && endCheck != 1){ //calls coin flip immediately
          CFlip c = new CFlip();
          c.startCFlip();
          days.setVisible(false);
        }
        if (nextCheck == 0 && endCheck == 1){ //this button is activated after the coin flip is complete
          nextCheck++;
          JOptionPane.showMessageDialog(days, "It's been a long day watching your art teacher get abducted and \nalmost accidently stomped on by STATSZILLA!!!\n(that is now his legal name).", "Very VERY Tired", JOptionPane.INFORMATION_MESSAGE);
          JOptionPane.showMessageDialog(days, "From exhaustion you collapse on the spot", "Darkness", JOptionPane.WARNING_MESSAGE);
        } else if(nextCheck == 1 && endCheck == 1){ //after coin flip and button pressed day 4 is called
          nextCheck++;
          days.setVisible(false);
          Day4 d4 = new Day4();
          d4.startDay4();
        } 
      }
    };
    nextB.addActionListener(nListener);
  }
}
