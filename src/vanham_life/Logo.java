/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vanham_life;

import java.awt.Image;
import javax.swing.*;

/**
 *
 * @author vanhk5054
 */
public class Logo extends JPanel {
    private final JLabel logo;
    
    /**
     * Constructor - Initializes and creates a logo image in the form of a
     * JPanel
     * 
     * Pre: none
     * Post: A Logo JPanel has been created
     */
    public Logo() {
        ImageIcon imageIcon = new ImageIcon("logo.png"); //load the image to a
                                                         //imageIcon
        Image image = imageIcon.getImage(); //transform it 
        Image newimg = image.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH); //scale it  
        imageIcon = new ImageIcon(newimg);  //transform it back

        logo = new JLabel(imageIcon);
        logo.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        add(logo);
        
        setOpaque(false);
    }
}
