package Practice;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import javax.accessibility.Accessible;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MainPage extends JFrame {

    public MainPage() {
        setTitle("Gestionale");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);

        // —— PALETTE —— 
        Color headerColor      = Color.decode("#222831");  // header e selezione
        Color buttonComboColor = Color.decode("#EEEEEE");  // sfondo bottone & combo chiusa
        Color optionListColor  = Color.decode("#EEEEEE");  // sfondo popup lista
        Color textColor        = Color.decode("#222831");            // testo bianco
        Color bgColor          = Color.decode("#EEEEEE");  // sotto header
        Color arrowColor       = Color.decode("#393E46");  // colore della freccia

        // Border arrotondato 3px, spessore 1px, colore bianco
        RoundedBorder whiteBorder = new RoundedBorder(3, 1, Color.WHITE);

        // —— HEADER —— 
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(headerColor);
        header.setPreferredSize(new Dimension(800, 80));
        JLabel title = new JLabel("Gestionale");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        title.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
        header.add(title, BorderLayout.WEST);

        // —— PANNELLO DESTRO —— 
        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,22));
        right.setOpaque(false);

        // — Bottone Gestisci — 
        JButton btn = new JButton("Gestisci");
        btn.setFont(new Font("SansSerif", Font.BOLD,14));
        btn.setForeground(textColor);
        btn.setBackground(buttonComboColor);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(130,36));
        btn.setBorder(whiteBorder);

        // — Combo — 
        String[] opts = {" ","Add Evento","Add Categoria"};
        JComboBox<String> combo = new JComboBox<>(opts);
        combo.setPreferredSize(new Dimension(150,36));
        combo.setFont(new Font("SansSerif", Font.BOLD,14));
        combo.setForeground(textColor);
        combo.setBackground(buttonComboColor);
        combo.setFocusable(false);
        combo.setBorder(whiteBorder);

        // UI custom per risolvere il bug dello sfondo bianco
        combo.setUI(new BasicComboBoxUI(){
            @Override
            protected void installDefaults(){
                super.installDefaults();
                combo.setOpaque(false);
            }

            @Override
            public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus){
                g.setColor(buttonComboColor);
                g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
            }

            @Override
            protected JButton createArrowButton(){
                JButton arrow = new JButton(){
                    @Override
                    protected void paintComponent(Graphics g){
                        super.paintComponent(g);
                        Graphics2D g2 = (Graphics2D)g.create();
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                           RenderingHints.VALUE_ANTIALIAS_ON);
                        int w = getWidth(), h = getHeight(), s = 4;
                        int x = w/2, y = h/2;
                        g2.setColor(Color.WHITE);  // Cambia il colore della freccia
                        g2.fillPolygon(
                            new int[]{x-s, x+s, x},
                            new int[]{y-s/2, y-s/2, y+s},
                            3
                        );
                        g2.dispose();
                    }
                };
                arrow.setBorder(BorderFactory.createEmptyBorder());
                arrow.setContentAreaFilled(false);
                arrow.setBackground(buttonComboColor);
                return arrow;
            }
        });

        // Renderer per il popup (opzioni)
        combo.setRenderer(new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list,
                                                          Object value,
                                                          int idx,
                                                          boolean selected,
                                                          boolean focus){
                JLabel lab = (JLabel)super.getListCellRendererComponent(
                        list, value, idx, selected, focus);
                lab.setHorizontalAlignment(SwingConstants.CENTER);
                lab.setFont(new Font("SansSerif", Font.BOLD,14));
                lab.setForeground(Color.WHITE);
                lab.setOpaque(true);
                lab.setBackground(selected ? new Color(60,66,75): headerColor );
                return lab;
            }
        });

        // Forza il background del JList dentro il popup
        combo.addPopupMenuListener(new PopupMenuListener(){
            @Override public void popupMenuWillBecomeVisible(PopupMenuEvent e){
                SwingUtilities.invokeLater(() -> {
                    Accessible a = combo.getAccessibleContext().getAccessibleChild(0);
                    if(a instanceof JPopupMenu){
                        JPopupMenu popup = (JPopupMenu)a;
                        Component c = popup.getComponent(0);
                        if(c instanceof JScrollPane){
                            JScrollPane sp = (JScrollPane)c;
                            JViewport vp = sp.getViewport();
                            Component view = vp.getView();
                            if(view instanceof JList){
                                JList<?> list = (JList<?>)view;
                                list.setBackground(optionListColor);
                                list.setSelectionBackground(new Color(60,66,75));
                                list.setSelectionForeground(Color.WHITE);
                            }
                        }
                    }
                });
            }
            @Override public void popupMenuWillBecomeInvisible(PopupMenuEvent e){}
            @Override public void popupMenuCanceled(PopupMenuEvent e){}
        });

        // — COMPOSIZIONE — 
        right.add(btn);
        right.add(combo);
        header.add(right, BorderLayout.EAST);

        JPanel content = new JPanel();
        content.setBackground(bgColor);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(header, BorderLayout.NORTH);
        getContentPane().add(content, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(MainPage::new);
    }

    // Classe di bordo arrotondato
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
                g2.drawRoundRect(x + i, y + i, width - 1 - 2*i, height - 1 - 2*i, radius, radius);
            }
            g2.dispose();
        }
    }
}
