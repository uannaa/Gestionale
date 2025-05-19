package Frame;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorPicker {

    private String color = "#000000";
    private JColorChooser colorChooser;
    private CategoriaFrame f;

    public ColorPicker(CategoriaFrame f) {
        this.f = f;
        createWindow();
    }

    public String getColor() {
        return this.color;
    }

    public void createWindow() {
        JFrame frame = new JFrame("Color Picker");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createUI(frame);
        frame.setSize(650, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void createUI(final JFrame frame) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel colorLabel = new JLabel("Color Chooser Example");
        colorChooser = new JColorChooser();
        colorChooser.setPreviewPanel(new JPanel());
        colorChooser.setBorder(BorderFactory.createTitledBorder("Choose Label Color"));

        // Rimuovi pannelli inutili
        AbstractColorChooserPanel[] panels = colorChooser.getChooserPanels();
        for (int i = 0; i < panels.length - 1; i++) {
            colorChooser.removeChooserPanel(panels[i]);
        }

        // Bottone per confermare
        JButton confirmButton = new JButton("Conferma colore");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color selectedColor = colorChooser.getColor();
                if (selectedColor != null) {
                    color = String.format("%02x%02x%02x", 
                              selectedColor.getRed(), 
                              selectedColor.getGreen(), 
                              selectedColor.getBlue());
                    JOptionPane.showMessageDialog(frame, "Colore selezionato: " + color);
                    f.setColor(color);
                    frame.dispose();
                }
            }
        });

        panel.add(colorLabel);
        panel.add(colorChooser);
        panel.add(confirmButton);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
    }
}
