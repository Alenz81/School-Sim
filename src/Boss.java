import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Boss{
  private JButton nextB;
  private int nextCheck;
  private double debt;
  private JFrame days; 
  private ImageIcon iBack;
  private JLabel lBack;
  private JPanel bPanel;
  
  public Boss(double d){
    days = new JFrame();
    iBack = new ImageIcon("boss/boss screen.png");
    lBack = new JLabel(iBack); //needed to create the background, converts imageicon to jlabel
    nextB = new JButton(new ImageIcon("screens/next.png"));
    nextCheck = 0;
    debt = d;
    bPanel = new JPanel();
  }

  public void startBoss(){
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
      public void actionPerformed(ActionEvent e){
        if (nextCheck == 0){
          nextCheck++;
          days.setVisible(false);
          lBack.setIcon(new ImageIcon("boss/target comp.png"));
          JOptionPane.showMessageDialog(days, "You wander over to the computer section of the store and you see him", "I would like to speak to your manager", JOptionPane.INFORMATION_MESSAGE);
          days.setVisible(true);
          JOptionPane.showMessageDialog(days, "Its Mr Cuellar ruler of all computers", "Mr. Cuellar", JOptionPane.INFORMATION_MESSAGE);  
        } else if (nextCheck == 1){
          nextCheck++;
          lBack.setIcon(new ImageIcon("boss/computers w aj.png"));
          JOptionPane.showMessageDialog(days, "Your only choice is to play his game", "Do you wanna play a game?", JOptionPane.INFORMATION_MESSAGE);
        } else if (nextCheck == 2){
          nextCheck++;
          days.setVisible(false);
          FGame f = new FGame(debt);
          f.startFGame();
        }
      }
    };
    nextB.addActionListener(nListener);
  }
}