
package principal;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class Propiedades extends BasicComboBoxUI{
    
    Color c = new Color(0,112,192);
    
    public static ComboBoxUI createUI(JComponent com){
        return new Propiedades();
    }

    @Override
    protected JButton createArrowButton() {
        JButton btn = new JButton();
        btn.setIcon(new ImageIcon(getClass().getResource("/img/downn.png")));
        btn.setBorder(BorderFactory.createLineBorder(c, 1));
        btn.setBackground(c);
        
        btn.setContentAreaFilled(false);
        return btn;
    }

    @Override
    public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean bln) {
        g.setColor(c);
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
   

    @Override
    protected ListCellRenderer createRenderer() {
        return new DefaultListCellRenderer(){          
            
            @Override
            public Component getListCellRendererComponent(JList<?> jlist, Object o, int i, boolean bln, boolean bln1) {
                super.getListCellRendererComponent(jlist, o, i, bln, bln1); //To change body of generated methods, choose Tools | Templates.
                jlist.setSelectionBackground(Color.white);
                jlist.setBackground(c);
                return this;
            }            
        };
    }
    
    
    
    
    
    
    
    
    
}
