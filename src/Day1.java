import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Day1{
  private JButton nextB;
  private JFrame days; 
  private ImageIcon iBack;
  private JLabel lBack;
  private JPanel bPanel;
  private int nChange;
  
  public Day1(){
    days = new JFrame();
    iBack = new ImageIcon("screens/Day 1.png");
    lBack = new JLabel(iBack); //needed to create the background, converts imageicon to jlabel
    nextB = new JButton(new ImageIcon("screens/next.png"));
    bPanel = new JPanel();
    nChange = 0;
    
  }
  public void startDay1(){
    days.setSize(new Dimension(600, 600));
    days.setTitle("Day 1");
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
    //makes visible and close
  }

  public void wakey(){ //first screen in the game when you wake up
    lBack.setIcon(new ImageIcon("screens/morning.png"));
    days.repaint();
    days.pack();
  JOptionPane.showMessageDialog(days, "You wake up to the sensation of sunlight on your forehead and \nrealise you have slept through half of the school day", "Rise and Shine", JOptionPane.INFORMATION_MESSAGE);
  }
  
  public void listeners(){
    ActionListener nListener = new ActionListener(){
      @Override 
      public void actionPerformed(ActionEvent e){
        if (nChange == 0){ //shows morning scene
          wakey();
          nChange++;
        } else if (nChange == 1){ //displays zoology teacher
          lBack.setIcon(new ImageIcon("screens/window z.png"));
          days.repaint();
          days.pack();
          JOptionPane.showMessageDialog(days, "You look outside and see your zoology teacher yelling at you.", "Rise and Shine", JOptionPane.INFORMATION_MESSAGE);
          nChange++;
        } else { //calls agame when everything else has been met
          AGame a = new AGame();
          days.setVisible(false);
          a.startAGame();
        }
      }
    };
    nextB.addActionListener(nListener);
  }
}