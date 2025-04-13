package Practice;

import Practice.FrameTry;
import Practice.FrameTry2;
import Practice.TryPanel;
import javax.swing.*;
import java.awt.*;

public class GestionaleGUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gestionale");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 600);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout());
            frame.getContentPane().setBackground(Color.decode("#f4f4f4"));

            // Pannello superiore
            JPanel topPanel = new JPanel(new BorderLayout());
            topPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
            topPanel.setBackground(Color.decode("#ffffff"));

            // Label "Gestionale"
            JLabel titleLabel = new JLabel("Gestionale");
            titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
            titleLabel.setForeground(Color.decode("#333333"));
            topPanel.add(titleLabel, BorderLayout.WEST);

            // Pannello per i bottoni/combo
            JPanel buttonPanel = new JPanel();
            buttonPanel.setBackground(Color.decode("#ffffff"));

            // Bottone "Gestisci"
            JButton gestisciButton = new JButton("Gestisci");
            styleButton(gestisciButton);
            buttonPanel.add(gestisciButton);
            buttonPanel.add(Box.createHorizontalStrut(10));

            // ComboBox "Aggiungi"
            String[] opzioniAggiunta = {"Aggiungi evento", "Aggiungi categoria"};
            JComboBox<String> aggiungiComboBox = new JComboBox<>(opzioniAggiunta);
            styleComboBox(aggiungiComboBox);
            buttonPanel.add(aggiungiComboBox);

            topPanel.add(buttonPanel, BorderLayout.EAST);

            // Pannello contenente i TryPanel
            JPanel contentPanel = new JPanel();
            contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
            contentPanel.setBackground(Color.decode("#f4f4f4"));

//            // Esempio: aggiunta di alcuni TryPanel
//            for (int i = 0; i < 5; i++) {
//                TryPanel panel = new TryPanel();
//                panel.setMaximumSize(new Dimension(666, 150));
//                panel.setPreferredSize(new Dimension(666, 150));
//                panel.setAlignmentX(Component.CENTER_ALIGNMENT);
//                contentPanel.add(panel);
//                contentPanel.add(Box.createVerticalStrut(15));
//            }

            // ScrollPane centrale
            JScrollPane container = new JScrollPane(contentPanel);
            container.setBorder(null);
            container.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            container.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            container.getViewport().setBackground(Color.decode("#f4f4f4"));

            // Azione ComboBox
            aggiungiComboBox.addActionListener(e -> {
                String selezione = (String) aggiungiComboBox.getSelectedItem();
                if (selezione.equals("Aggiungi evento")) {
                    System.out.println("Evento da aggiungere");
                    Frame f = new FrameTry();
                    f.setVisible(true);
                } else if (selezione.equals("Aggiungi categoria")) {
                    System.out.println("Categoria da aggiungere");
                    Frame f = new FrameTry2();
                    f.setVisible(true);
                }
            });

            // Aggiunta componenti
            frame.add(topPanel, BorderLayout.NORTH);
            frame.add(container, BorderLayout.CENTER);

            frame.setVisible(true);
        });
    }

    private static void styleButton(JButton button) {
        button.setFont(new Font("SansSerif", Font.PLAIN, 16));
        button.setFocusPainted(false);
        button.setBackground(new Color(66, 133, 244));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private static void styleComboBox(JComboBox<String> comboBox) {
        comboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
        comboBox.setBackground(Color.WHITE);
        comboBox.setForeground(Color.DARK_GRAY);
        comboBox.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        comboBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
