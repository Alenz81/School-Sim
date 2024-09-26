import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EGame{
  private JButton nextB;
  private int nextCheck; //changes action of nextB increased by one every time nextB is pressed
  private JFrame days; 
  private ImageIcon iBack;
  private JLabel lBack;
  private JPanel bPanel;
  private double guess;
  private double debt;
  private int[] prices; //array for prices of item
  private String[] images; //images of items corresponds to prices array

  
  public EGame(){
    days = new JFrame();
    iBack = new ImageIcon("day4/mo game explain.png");
    lBack = new JLabel(iBack); //needed to create the background, converts imageicon to jlabel
    nextB = new JButton(new ImageIcon("screens/next.png"));
    nextCheck = 0;
    guess = 0;
    bPanel = new JPanel();
    prices = new int[5];
    prices[0] = 11;
    prices[1] = 13;
    prices[2] = 18;
    prices[3] = 88;
    prices[4] = 1798000;
    images = new String[5];
    images[0] = "day4/diaper.png";
    images[1] = "day4/nice cage.png";
    images[2] = "day4/jake.png";
    images[3] = "day4/among us.png";
    images[4] = "day4/house.png";
    
  }

  public void startEGame(){
    days.setSize(new Dimension(600, 600));
    days.setTitle("Guess the price");
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

  
    public void runPrice(){ //creates pane for guessing price
      lBack.setIcon(new ImageIcon(images[nextCheck]));
      String s = JOptionPane.showInputDialog("Guess the price\nEnter an integer with only numbers \nExample: 143");
      guess = Integer.parseInt(s);
      double off = Math.abs(prices[nextCheck] - guess); //calculates how much you are off by
      debt += off; //adds how mcuh you were off to debt
      JOptionPane.showMessageDialog(days, "The price was: $" + prices[nextCheck] + "\nYou were off by: $" + off + "\nYour debt is: $" + debt, "Money", JOptionPane.INFORMATION_MESSAGE); //creates pane to tell you how much you were off by
      nextCheck++;
    }
  
    public void listeners(){
    ActionListener nListener = new ActionListener(){
      @Override 
      public void actionPerformed(ActionEvent e){
        if (nextCheck < 5){
          runPrice();
        } else if (nextCheck == 5){
          if (debt < 100){ //if debt is less than 100 mo is proud, other wise he is dsiappointed
            lBack.setIcon(new ImageIcon("day4/mo with dollar.png"));
            JOptionPane.showMessageDialog(days, "Wow!!! You really know your money.\nI set you free at least until the boss gets to you", "Freedom", JOptionPane.INFORMATION_MESSAGE);
          } else {
            lBack.setIcon(new ImageIcon("day4/muscle mo.png"));
            JOptionPane.showMessageDialog(days, "Wow, I can't believe one of my students has accrued more than $100\nin debt. Now I'll let the boss have you.", "Freedom (BAD ENDING)", JOptionPane.INFORMATION_MESSAGE);
          }
          nextCheck++;
        } else if (nextCheck == 6){ //calls boss after everything is called
            days.setVisible(false);
            Boss b = new Boss(debt);
            b.startBoss();
        }
      }
    };
    nextB.addActionListener(nListener);
  }
}