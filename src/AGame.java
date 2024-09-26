import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AGame{
  private JButton bOne;
  private JButton bTwo;
  private JButton bThree;
  private JButton next;
  
  private JFrame zoology; 
  private ImageIcon iBack;
  private JLabel lBack;
  private JPanel bPanel;
  
  private int aCheck;
  
  public AGame(){
    zoology = new JFrame();
    iBack = new ImageIcon("animal/animal screen.png");
    lBack = new JLabel(iBack); //needed to create the background, converts imageicon to jlabel
    bOne = new JButton(new ImageIcon("animal/moo.png"));
    bTwo = new JButton(new ImageIcon("animal/oink.png"));
    bThree = new JButton(new ImageIcon("animal/cluck.png"));
    next = new JButton(new ImageIcon("screens/next.png"));
    bPanel = new JPanel();
    aCheck = 0;
    
  }
  public void startAGame(){
    zoology.setSize(new Dimension(600, 600));
    zoology.setTitle("Zoology");
    //creates frame
    zoology.add(lBack);
    zoology.pack();
    //sets background
    bOne.setBackground(Color.BLACK);
    bTwo.setBackground(Color.BLACK);
    bThree.setBackground(Color.BLACK);
    next.setBackground(Color.BLACK);
    //sets background of buttons to black

    zoology.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    zoology.setVisible(true);
    //makes visible and close
    bPanel.add(next);
    //sets jpanel background to black
    bPanel.setBackground(Color.BLACK);
    zoology.add(bPanel, BorderLayout.SOUTH);
    listeners();
  }

  public void play(){
    //creates jpanel for buttons
    aCheck = 0;
    lBack.setIcon(new ImageIcon("animal/piggie.png"));
    zoology.repaint();
    zoology.pack();
    bPanel.add(bOne);
    bPanel.add(bTwo);
    bPanel.add(bThree);
    zoology.pack();
  }

  public void defeat(){ //gets called after you win
    lBack.setIcon(new ImageIcon("screens/zoology end.png"));
    JOptionPane.showMessageDialog(zoology, "He loves animals, hopefully you won't have to incapacitate any other teachers that \nchallenge you to fight for your life.", "What Just Happened?", JOptionPane.WARNING_MESSAGE);
    JOptionPane.showMessageDialog(zoology, "After a long and intense battle you enter the shower to relinquish the filth from your body", "Ew Gross", JOptionPane.INFORMATION_MESSAGE);
  }
  
  public void listeners(){
    ActionListener nextListener = new ActionListener(){//responsible for progressing game, changes condition based on which panel you are on (this is kept track by aCheck)
      @Override 
      public void actionPerformed(ActionEvent e){
        if (aCheck == 0){ //before game played
          bPanel.remove(next);
          play();
        } else if (aCheck == 3){ //after you have won three times
          defeat();
          aCheck++;
        } else if (aCheck == 4){ //after defeat method which starts shower panel
          aCheck++;
          lBack.setIcon(new ImageIcon("screens/shower head.png"));
          zoology.repaint();
          zoology.pack();
          JOptionPane.showMessageDialog(zoology, "I am clean now", "You Are Clean Now", JOptionPane.INFORMATION_MESSAGE);
        } else if (aCheck == 5) { //after shower begins sleep panel
          aCheck++;
          lBack.setIcon(new ImageIcon("screens/no sleep.png"));
          zoology.repaint();
          zoology.pack();
          JOptionPane.showMessageDialog(zoology, "I couldn't stop thinking about what happened today hopefully it won't happen tomorrow.", "No Sleep", JOptionPane.INFORMATION_MESSAGE);
        } else { //finally starts a new day closing the zoology panel
          zoology.setVisible(false);
          Day2 d2 = new Day2();
          d2.startDay2();
        }
      }
    };
    ActionListener mListener = new ActionListener(){
      @Override 
      public void actionPerformed(ActionEvent e){
        if (aCheck == 1){ //checks if this is the proper image for the answer (if you click this button, which is the button for moo, while the image is  1, if the image is  the cow, you will progress. Otherwise you are forced to do it again. The next listeners are almost identical.
          aCheck++;
          JOptionPane.showMessageDialog(zoology, "yes", "Good Job", JOptionPane.INFORMATION_MESSAGE); 
          lBack.setIcon(new ImageIcon("animal/chick.png"));
        } else {
         JOptionPane.showMessageDialog(zoology, "wrong do it again", "No", JOptionPane.ERROR_MESSAGE); 
        }
      }
    };

    ActionListener oListener = new ActionListener(){//same as the first listener
      @Override 
      public void actionPerformed(ActionEvent e){
        if (aCheck == 0){
          aCheck++;
          JOptionPane.showMessageDialog(zoology, "yes", "Good Job", JOptionPane.INFORMATION_MESSAGE); 
          lBack.setIcon(new ImageIcon("animal/cow1.png"));
        } else {
         JOptionPane.showMessageDialog(zoology, "wrong do it again", "No", JOptionPane.ERROR_MESSAGE); 
        }
      }
    };

    ActionListener cListener = new ActionListener(){
      @Override 
      public void actionPerformed(ActionEvent e){
        if (aCheck == 2){ //same idea as other listeners however since the chicken is the final stage is removes the animal sound buttons and adds next to progress the game. 
          aCheck++;
          JOptionPane.showMessageDialog(zoology, "yes", "Good Job", JOptionPane.INFORMATION_MESSAGE); 
          bPanel.remove(bOne);
          bPanel.remove(bTwo);
          bPanel.remove(bThree);
          bPanel.add(next);
          bPanel.repaint();
          lBack.setIcon(new ImageIcon("animal/trophy.png"));
        } else {
          JOptionPane.showMessageDialog(zoology, "wrong do it again", "No", JOptionPane.ERROR_MESSAGE); 
        }
      }
    };
    
    bOne.addActionListener(mListener);
    bTwo.addActionListener(oListener);
    bThree.addActionListener(cListener);
    next.addActionListener(nextListener);
  }
}