package GunShopSales;
 
import java.awt.Color;
import java.awt.FlowLayout;
 
import javax.swing.JFrame;
import javax.swing.JLabel;
 
public class Background extends JFrame {
 
    private static final long serialVersionUID = 1L;
 
    public Background() {
 

        this.getContentPane().setLayout(new FlowLayout());
 
        JLabel color = new JLabel("Color");
 
        color.setOpaque(true);

        color.setBackground(Color.BLUE);

        add(color);
 
    }
 
    private static void createAndShowGUI() {

 
  JFrame frame = new Background();
 
 
  frame.pack();
 
  frame.setVisible(true);
 
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
    }
 
    public static void main(String[] args) {
 
  javax.swing.SwingUtilities.invokeLater(new Runnable() {
 
public void run() {
 
    createAndShowGUI(); 
 
}
 
  });
    }
 
}
