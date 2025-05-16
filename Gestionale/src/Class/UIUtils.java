/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

public class UIUtils {

    // Colori principali
    public static final Color HEADER_COLOR      = Color.decode("#0353A4");
    public static final Color BUTTON_COMBO_COLOR = Color.decode("#FDFFFC");
    public static final Color OPTION_LIST_COLOR = Color.decode("#FDFFFC");
    public static final Color TEXT_COLOR        = Color.decode("#0353A4");
    public static final Color BG_COLOR          = Color.decode("#FDFFFC");
    public static final Color ARROW_COLOR       = Color.decode("#393E46");

    // Bordo bianco
    private static final RoundedBorder WHITE_BORDER = new RoundedBorder(3, 1, Color.WHITE);

    // Metodo per bottone
    public static void styleButton(JButton btn) {
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setForeground(TEXT_COLOR);
        btn.setBackground(BUTTON_COMBO_COLOR);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(130, 36));
        btn.setBorder(WHITE_BORDER);
    }

    // Metodo per comboBox
    public static void styleComboBox(JComboBox<?> combo) {
        combo.setFont(new Font("SansSerif", Font.BOLD, 14));
        combo.setForeground(TEXT_COLOR);
        combo.setBackground(BUTTON_COMBO_COLOR);
        combo.setFocusable(false);
        combo.setBorder(WHITE_BORDER);
        combo.setOpaque(false);
        combo.setPreferredSize(new Dimension(150, 36));

        combo.setUI(new BasicComboBoxUI() {
            @Override
            protected void installDefaults() {
                super.installDefaults();
                combo.setOpaque(false);
            }

            @Override
            public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
                g.setColor(BUTTON_COMBO_COLOR);
                g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
            }

            @Override
            protected JButton createArrowButton() {
                JButton arrow = new JButton() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        Graphics2D g2 = (Graphics2D) g.create();
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        int w = getWidth(), h = getHeight(), s = 4;
                        int x = w / 2, y = h / 2;
                        g2.setColor(Color.WHITE);
                        g2.fillPolygon(new int[]{x - s, x + s, x}, new int[]{y - s/2, y - s/2, y + s}, 3);
                        g2.dispose();
                    }
                };
                arrow.setBorder(BorderFactory.createEmptyBorder());
                arrow.setContentAreaFilled(false);
                arrow.setBackground(BUTTON_COMBO_COLOR);
                return arrow;
            }
        });

        // Renderer per opzioni nel popup
        combo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setFont(new Font("SansSerif", Font.BOLD, 14));
                label.setForeground(Color.WHITE);
                label.setOpaque(true);
                label.setBackground(isSelected ? new Color(60,66,75): HEADER_COLOR );
                return label;
            }
        });
    }

    // Metodo per tutti i bottoni/combobox insieme
    public static void styleAllComponents(Container container) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JButton) {
                styleButton((JButton) comp);
            } else if (comp instanceof JComboBox) {
                styleComboBox((JComboBox<?>) comp);
            } else if (comp instanceof Container) {
                styleAllComponents((Container) comp); // Ricorsione
            }
        }
    }

    // Classe bordo arrotondato
    static class RoundedBorder extends AbstractBorder {
        private final int radius;
        private final int thickness;
        private final Color color;

        public RoundedBorder(int radius, int thickness, Color color) {
            this.radius = radius;
            this.thickness = thickness;
            this.color = color;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            for (int i = 0; i < thickness; i++) {
                g2.drawRoundRect(x + i, y + i, width - 1 - 2 * i, height - 1 - 2 * i, radius, radius);
            }
            g2.dispose();
        }
    }
}