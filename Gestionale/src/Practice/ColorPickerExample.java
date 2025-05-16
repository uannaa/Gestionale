import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ColorPickerExample {
   public static void main(String[] args) {
      createWindow();
   }

   private static void createWindow() {    
      JFrame frame = new JFrame("Swing Tester");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      createUI(frame);
      frame.setSize(650, 300);      
      frame.setLocationRelativeTo(null);  
      frame.setResizable(false);
      frame.setVisible(true);
   }

   private static void createUI(final JFrame frame){  
      JPanel panel = new JPanel();
      LayoutManager layout = new FlowLayout();  
      panel.setLayout(layout);       
      final JLabel colorLabel = new JLabel("Color Chooser Example");     
      final JColorChooser colorChooser = new JColorChooser();

      //No preview panel
      colorChooser.setPreviewPanel(new JPanel());
      //Add border 
      colorChooser.setBorder(BorderFactory.createTitledBorder("Choose Label Color"));
      AbstractColorChooserPanel[] panels = colorChooser.getChooserPanels();
      
      for(int i = 0; i < panels.length - 1; i++){
         colorChooser.removeChooserPanel(panels[i]);
      }     

      panel.add(colorLabel);
      panel.add(colorChooser);
      frame.getContentPane().add(panel, BorderLayout.CENTER);    
   }  
}