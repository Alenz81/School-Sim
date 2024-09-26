import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Day2{
  private JButton nextB;
  private JFrame days; 
  private ImageIcon iBack;
  private JLabel lBack;
  private JPanel bPanel;
  
  public Day2(){
    days = new JFrame();
    iBack = new ImageIcon("screens/day 2.png");
    lBack = new JLabel(iBack); //needed to create the background, converts imageicon to jlabel
    nextB = new JButton(new ImageIcon("screens/next.png"));
    bPanel = new JPanel();
  }

  public void startDay2(){
    days.setSize(new Dimension(600, 600));
    days.setTitle("Day 2");
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
      public void actionPerformed(ActionEvent e){
        days.setVisible(false);
        JOptionPane.showMessageDialog(days, "Nothing happened the next day", "Eventful", JOptionPane.INFORMATION_MESSAGE);
        Day3 d3 = new Day3();
        d3.startDay3();
      }
    };
    nextB.addActionListener(nListener);
  }
}