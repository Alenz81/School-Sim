import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FGame{
  private JButton nextB;
  private JButton b1;
  private JButton b2; //act as choices for favorites
  private JButton b3;
  private int nextCheck;
  private int runCount; //keeps track of which item you are on/how much runfavs is run
  private double debt;
  private boolean win; //determines if you get good or bad ending
  private boolean card; //determins if you get card or not
  private JFrame days; 
  private ImageIcon iBack;
  private JLabel lBack;
  private JPanel bPanel;
  private ImageIcon[] imageScreens; //images for correct answers
  private ImageIcon[] imageButtons; //images for buttons
  private ImageIcon[] emojiScreens; //images for prompting what to guess
  
  public FGame(double d){
    days = new JFrame();
    iBack = new ImageIcon("boss/favorite screen.png");
    lBack = new JLabel(iBack); //needed to create the background, converts imageicon to jlabel
    nextB = new JButton(new ImageIcon("screens/next.png"));
    nextCheck = 0;
    runCount = 0;
    debt = d;
    win = true;
    card = false;
    bPanel = new JPanel();
    imageButtons = new ImageIcon[15];
    imageButtons[0] = new ImageIcon("bossbuttons/appleb.png");
    imageButtons[1] = new ImageIcon("bossbuttons/popcornb.png");
    imageButtons[2] = new ImageIcon("bossbuttons/horseb.png");
    imageButtons[3] = new ImageIcon("bossbuttons/saladb.png");
    imageButtons[4] = new ImageIcon("bossbuttons/cakeb.png");
    imageButtons[5] = new ImageIcon("bossbuttons/jellob.png");
    imageButtons[6] = new ImageIcon("bossbuttons/savingb.png");
    imageButtons[7] = new ImageIcon("bossbuttons/star wars.png");
    imageButtons[8] = new ImageIcon("bossbuttons/shreak.png");
    imageButtons[9] = new ImageIcon("bossbuttons/22b.png");
    imageButtons[10] = new ImageIcon("bossbuttons/13b.png");
    imageButtons[11] = new ImageIcon("bossbuttons/6b.png");
    imageButtons[12] = new ImageIcon("bossbuttons/Docb.png");
    imageButtons[13] = new ImageIcon("bossbuttons/grumpyb.png");
    imageButtons[14] = new ImageIcon("bossbuttons/dopeyb.png");
    imageScreens = new ImageIcon[5];
    imageScreens[0] = new ImageIcon("boss/popcorn.png");
    imageScreens[1] = new ImageIcon("boss/salad.png");
    imageScreens[2] = new ImageIcon("boss/saving private.png");
    imageScreens[3] = new ImageIcon("boss/6.png");
    imageScreens[4] = new ImageIcon("boss/grumpy.png");
    emojiScreens = new ImageIcon[5];
    emojiScreens[0] = new ImageIcon("bewin/emoji snack.png");
    emojiScreens[1] = new ImageIcon("bewin/emoji dessertpng.png");
    emojiScreens[2] = new ImageIcon("bewin/emoji movie.png");
    emojiScreens[3] = new ImageIcon("bewin/emoji number.png");
    emojiScreens[4] = new ImageIcon("bewin/emoji dwarf.png");
    b1 = new JButton(imageButtons[0]);
    b2 = new JButton(imageButtons[1]);
    b3 = new JButton(imageButtons[2]);
  }

  public void startFGame(){
    days.setSize(new Dimension(600, 600));
    days.setTitle("A Few of His Favorite Things");
    //creates frame
    lBack.setBounds(0, 0, iBack.getIconWidth(), iBack.getIconHeight());
    days.add(lBack);
    //sets background
    nextB.setBackground(Color.BLACK);
    b1.setBackground(Color.BLACK);
    b2.setBackground(Color.BLACK);
    b3.setBackground(Color.BLACK);
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
    //days.add(stringLabel);
  
    
  }


  public void runFavs(){
    bPanel.remove(nextB);
    b1.setIcon(imageButtons[0 + 3 * runCount]);
    b2.setIcon(imageButtons[1 + 3 * runCount]); 
    b3.setIcon(imageButtons[2 + 3 * runCount]);//makes buttons have correct images
    bPanel.add(b1);
    bPanel.add(b2);
    bPanel.add(b3);
    bPanel.repaint();
    lBack.setIcon(emojiScreens[runCount]);
    days.pack();
    runCount++;
    //removes next and adds new buttons with correct images as well as setting background
  }

  public void after3Choice(){
    bPanel.remove(b1);
    bPanel.remove(b2);
    bPanel.remove(b3);
    bPanel.add(nextB);
    bPanel.repaint();
    //days.pack();
    //removes options and adds back next button
  }

  public void win(){
    lBack.setIcon(imageScreens[runCount - 1]);
    JOptionPane.showMessageDialog(days, "That was the right answer", "Nice", JOptionPane.INFORMATION_MESSAGE);
    //determins if you got right answer, is called by b1, b2, and b3
  }

  public void lose(){
    lBack.setIcon(imageScreens[runCount - 1]);
    JOptionPane.showMessageDialog(days, "That was the wrong answer", "Yikes", JOptionPane.INFORMATION_MESSAGE);
    win = false;
    //determins if you got wrong answer, is called by b1, b2, and b3
  }
  
  public void listeners(){
    ActionListener nListener = new ActionListener(){
      @Override 
      public void actionPerformed(ActionEvent e){
        if (nextCheck < 5){ //plays game until all 5 options have been played
          runFavs();
          nextCheck++;
        } else if (nextCheck == 5){//if you win you get the good ending, if not cuellar threatens you 
          if (win == true){
            lBack.setIcon(new ImageIcon("fuego/angry win.png"));
          } else {
            lBack.setIcon(new ImageIcon("fuego/angry lose.png"));
          }
          days.pack();
          nextCheck++;
        } else if (nextCheck == 6){ //the introduction of fuego
          days.setVisible(false);
          lBack.setIcon(new ImageIcon("fuego/target employee.png"));
          JOptionPane.showMessageDialog(days, "He sends you off to see one last person", "Almost done", JOptionPane.INFORMATION_MESSAGE);
          days.setVisible(true);
          JOptionPane.showMessageDialog(days, "Is it really him? You walk closer.", "No Way", JOptionPane.INFORMATION_MESSAGE);
          lBack.setIcon(new ImageIcon("fuego/target name.png"));
          JOptionPane.showMessageDialog(days, "He begins to talk.", "Speak", JOptionPane.INFORMATION_MESSAGE);
          nextCheck++;
        } else if (nextCheck == 7){ //if you got all your questions fuego wll offer the gift card as normal, if you lost he will grant you protection
          if (win == true){
            lBack.setIcon(new ImageIcon("fuego/fuego speaks.png"));
          } else {
            lBack.setIcon(new ImageIcon("fuego/fuego speaksL.png"));
          }
          bPanel.remove(nextB);
          b1.setIcon(new ImageIcon("fuego/15wage.png"));
          b2.setIcon(new ImageIcon("fuego/1wage.png"));
          bPanel.add(b1);
          bPanel.add(b2);
          //reuses favorite options as options for fuego's wage
          bPanel.repaint();
          days.pack();
          nextCheck++;
        } else if (nextCheck == 8){
          if (card == true){ //if you guess right and you get Fuego's wage (could have won or lost to cuellar)
            lBack.setIcon(new ImageIcon("fuego/card.png"));
          } else {
            if (win == true) { //if you win but don't get fuego's wage the game ends 
              days.setVisible(false);
            JOptionPane.showMessageDialog(days, "The game is over.\nYou are now $" + debt + " in debt.", "Bye", JOptionPane.INFORMATION_MESSAGE);
              System.exit(0);
            } else { //if you get both wrong you become a computer
              lBack.setIcon(new ImageIcon("fuego/computers end.png"));
            }
          }
          nextCheck++;
        } else if (nextCheck == 9){
          if (card == true){ //if the game hasn't ended from two options this ends it
            days.setVisible(false);
            JOptionPane.showMessageDialog(days, "The game is over.\nYou are now $" + debt + " in debt.", "Bye", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
          } else {
              days.setVisible(false);
            JOptionPane.showMessageDialog(days, "The game is over.\nYou are now $" + debt + " in debt.", "Bye", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
          }
          nextCheck++;
        }
      }
    };
    ActionListener b1Listener = new ActionListener(){
      @Override 
      public void actionPerformed(ActionEvent e){
        if (runCount == 2 || runCount == 3 && nextCheck != 8){ //corresponds to correct answers in array
          after3Choice(); //removes favorite options
          win(); //represents winning
        } else if (nextCheck != 8){ //next check cannot be eight becuase when these buttons are used for guessing wage this would run when it shouldn't
          after3Choice();
          lose(); //represents losing
        }
        if (nextCheck == 8){ //makes the buttons function as guessing for wage
          card = false; //removes buttons when clicked and adds next back to progress after your guess, also this is the incorrect answer
          bPanel.remove(b1);
          bPanel.remove(b2);
          bPanel.add(nextB);
          bPanel.repaint();
          days.pack();
          if (win == true){
            lBack.setIcon(new ImageIcon("fuego/fuego leave good.png"));
          } else {
            lBack.setIcon(new ImageIcon("fuego/fuego leave bad.png"));
          }
        }
      }
    };
    ActionListener b2Listener = new ActionListener(){
      @Override 
      public void actionPerformed(ActionEvent e){
        if (runCount == 1 || runCount == 5 && nextCheck != 8){ //reference b1 listener, these are almost identical
          after3Choice();
          win();
        } else if (nextCheck != 8){
          after3Choice();
          lose();
        }
        if (nextCheck == 8){
          card = true; //correct answer for wage
          bPanel.remove(b1);
          bPanel.remove(b2);
          bPanel.add(nextB);
          bPanel.repaint();
          days.pack();
          if (win == true){
            lBack.setIcon(new ImageIcon("fuego/fuego card good.png"));
          } else {
            lBack.setIcon(new ImageIcon("fuego/fuego card bad.png"));
          }
        }
      }
    };
    ActionListener b3Listener = new ActionListener(){
      @Override 
      public void actionPerformed(ActionEvent e){ //refernece b1 listener, these have the same first if statements however this one is simpler as it is not used for the wage guess
        if (runCount == 4){
          after3Choice();
          win();
        } else {
          after3Choice();
          lose();
        }
      }
    };
    nextB.addActionListener(nListener);
    b1.addActionListener(b1Listener);
    b2.addActionListener(b2Listener);
    b3.addActionListener(b3Listener);
  }
}