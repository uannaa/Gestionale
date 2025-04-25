import Practice.TryPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GestionaleGUI extends JFrame {
    private JLabel titoloLabel;
    private JButton gestisciButton;
    private JComboBox<String> aggiungiComboBox;
    private JScrollPane containerScrollPane;
    private JPanel containerPanel;

    public GestionaleGUI() {
        setTitle("Gestionale – Dark Modern UI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Palette colori moderni
        Color bgColor = new Color(24, 24, 27);
        Color topBarColor = new Color(36, 36, 42);
        Color textColor = new Color(235, 235, 245);
        Color accentColor = new Color(98, 160, 255);
        Color comboBgColor = new Color(45, 45, 52);

        Font titleFont = new Font("Segoe UI", Font.BOLD, 22);
        Font baseFont = new Font("Segoe UI", Font.PLAIN, 15);

        // Top panel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        topPanel.setBackground(topBarColor);
        add(topPanel, BorderLayout.NORTH);

        // Titolo
        titoloLabel = new JLabel("Gestionale");
        titoloLabel.setFont(titleFont);
        titoloLabel.setForeground(textColor);
        topPanel.add(titoloLabel, BorderLayout.WEST);

        // Bottoni destra
        JPanel rightTopPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        rightTopPanel.setBackground(topBarColor);

        // Bottone gestisci
        gestisciButton = creaBottone("Gestisci", accentColor, textColor);
        rightTopPanel.add(gestisciButton);

        // ComboBox stile moderno
        String[] opzioni = {"Aggiungi evento", "Aggiungi categoria"};
        aggiungiComboBox = new JComboBox<>(opzioni);
        aggiungiComboBox.setFont(baseFont);
        aggiungiComboBox.setForeground(textColor);
        aggiungiComboBox.setBackground(comboBgColor);
        aggiungiComboBox.setFocusable(false);
        aggiungiComboBox.setPreferredSize(new Dimension(170, 34));
        aggiungiComboBox.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        aggiungiComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                                                          int index, boolean isSelected, boolean cellHasFocus) {
                JLabel lbl = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                lbl.setBackground(isSelected ? accentColor.darker() : comboBgColor);
                lbl.setForeground(textColor);
                lbl.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                return lbl;
            }
        });

        rightTopPanel.add(aggiungiComboBox);
        topPanel.add(rightTopPanel, BorderLayout.EAST);

        // Pannello container scrollabile
        containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.setBackground(bgColor);

        containerScrollPane = new JScrollPane(containerPanel);
        containerScrollPane.setBorder(null);
        containerScrollPane.getViewport().setBackground(bgColor);
        containerScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        containerScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        add(containerScrollPane, BorderLayout.CENTER);
        getContentPane().setBackground(bgColor);
        setVisible(true);
    }

    private JButton creaBottone(String text, Color bg, Color fg) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
        btn.setBorderPainted(false);

        // Effetto hover
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(bg.brighter());
            }
            public void mouseExited(MouseEvent e) {
                btn.setBackground(bg);
            }
        });
        return btn;
    }

    public void aggiungiTryPanel(TryPanel panel) {
        panel.setMaximumSize(new Dimension(666, 150));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        containerPanel.add(Box.createVerticalStrut(12));
        containerPanel.add(panel);
        containerPanel.revalidate();
        containerPanel.repaint();
    }   

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GestionaleGUI());
    }
}
