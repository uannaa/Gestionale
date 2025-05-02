import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PannelloStondato extends JPanel {
    
    // Metodo per dipingere il pannello con bordi stondati e bordo nero
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // Abilitare l'anti-aliasing per linee più lisce
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Impostare il colore di sfondo
        g2d.setColor(Color.CYAN);
        
        // Disegnare il rettangolo stondato (riempito)
        g2d.fillRoundRect(10, 10, getWidth() - 20, getHeight() - 20, 30, 30);
        
        // Impostare il colore del bordo
        g2d.setColor(Color.BLACK);
        
        // Disegnare il bordo del rettangolo stondato (solo contorno)
        g2d.setStroke(new BasicStroke(1)); // Imposta lo spessore del bordo a 1px
        g2d.drawRoundRect(10, 10, getWidth() - 20, getHeight() - 20, 30, 30);
    }

    public static void main(String[] args) {
        // Creazione della finestra
        JFrame frame = new JFrame("Pannello con Bordi Stondati e Bordo Nero");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        
        // Aggiungere il pannello customizzato
        frame.add(new PannelloStondato());
        
        // Rendere visibile la finestra
        frame.setVisible(true);
    }
}
