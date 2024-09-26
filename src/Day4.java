import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Day4{
  private JButton nextB;
  private int nextCheck;
  private JFrame days; 
  private ImageIcon iBack;
  private JLabel lBack;
  private JPanel bPanel;
  

  
  public Day4(){
    days = new JFrame();
    iBack = new ImageIcon("day4/day 4.png");
    lBack = new JLabel(iBack); //needed to create the background, converts imageicon to jlabel
    nextB = new JButton(new ImageIcon("screens/next.png"));
    nextCheck = 0;
    bPanel = new JPanel();
    
  }

  public void startDay4(){
    days.setSize(new Dimension(600, 600));
    days.setTitle("Day 4");
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


  
  public void listeners(){
    ActionListener nListener = new ActionListener(){
      @Override 
      public void actionPerformed(ActionEvent e){//starts waking in abandon building
        if (nextCheck == 0){
          nextCheck++;
          lBack.setIcon(new ImageIcon("day4/atlas.png"));
          JOptionPane.showMessageDialog(days, "You have waken up in an abandoned Costco like building.", "Where am I?", JOptionPane.INFORMATION_MESSAGE);
          JOptionPane.showMessageDialog(days, "You see a green figure emerge from the shelves", "Who?", JOptionPane.INFORMATION_MESSAGE);
        } else if (nextCheck == 1){ //money mo talks to you
          nextCheck++;
          lBack.setIcon(new ImageIcon("day4/econ game screen.png"));
          JOptionPane.showMessageDialog(days, "Money Mo is your econ teacher, he's really cool", "Professor Morris", JOptionPane.INFORMATION_MESSAGE);
        } else if (nextCheck == 2){ //calls econ game
          nextCheck++;
          days.setVisible(false);
          EGame ega = new EGame();
          ega.startEGame();
        }
      }
    };
    nextB.addActionListener(nListener);
  }
}