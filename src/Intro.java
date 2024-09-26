import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Intro{
  private JButton playB;
  private JButton exitB;
  private JFrame intro; 
  private ImageIcon iBack;
  private JLabel lBack;
  
  public Intro (){
    intro = new JFrame();
    iBack = new ImageIcon("screens/school sim.png");
    lBack = new JLabel(iBack); //needed to create the background, converts imageicon to jlabel
    playB = new JButton(new ImageIcon("screens/play button.png"));
    exitB = new JButton(new ImageIcon("screens/exit button.png"));
  }
  
  public void startIntro(){
    
    intro.setSize(new Dimension(600, 600));
    intro.setTitle("oopsie");
    //creates frame
    lBack.setBounds(0, 0, iBack.getIconWidth(), iBack.getIconHeight());
    intro.add(lBack);
    //sets background
    playB.setBackground(Color.BLACK);
    exitB.setBackground(Color.BLACK);
    //sets background of buttons to black

    //creates jpanel for buttons
    JPanel bPanel = new JPanel();
    bPanel.add(playB);
    bPanel.add(exitB);
    //sets jpanel background to black
    bPanel.setBackground(Color.BLACK);
    intro.add(bPanel, BorderLayout.SOUTH);
    //moves jpanel to bottom could not find a better way to move it
    
    intro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    intro.pack();
    intro.repaint();
    intro.setVisible(true);
    listeners();
    //makes visible and close
  }
  
  public void listeners(){
    ActionListener pListener = new ActionListener(){
      @Override 
      public void actionPerformed(ActionEvent e){
        intro.setVisible(false);
        //closes intro
        Day1 d1 = new Day1();
        d1.startDay1();
        //starts new day
        
      }
    };
    
    ActionListener eListener = new ActionListener(){
      @Override 
      public void actionPerformed(ActionEvent e){
         JOptionPane.showMessageDialog(intro, "So you thought you were to cool for school\n\nThink again", "No", JOptionPane.ERROR_MESSAGE);
        //exit button that does not let you leave
      }
    };
    playB.addActionListener(pListener);
    exitB.addActionListener(eListener);
  }
}