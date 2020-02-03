/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmtools.GUI.buttons;

import dmtools.GUI.LayoutConstants;
import java.awt.Image;
import java.awt.Insets;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

/**
 *
 * @author A3
 */
public abstract class CustomButton extends JButton {

    private final String hoverText, iconName, iconColor, clickColor;
    private final int buttonSize;

    public CustomButton(String iconName, String iconColor, 
            String clickColor, int buttonSize, String hoverText) {
        super();
        this.iconName = iconName;
        this.iconColor = iconColor;
        this.clickColor = clickColor;
        this.buttonSize = buttonSize;
        this.hoverText = hoverText;
        formatButton();
        customizeIcons();
    }
    
    private void formatButton() {
        setBorderPainted(false);
        setOpaque(false);
        setBorder(null);
        setMargin(new Insets(0, 0, 0, 0));
        setContentAreaFilled(false);
        setToolTipText(hoverText);
        setForeground(LayoutConstants.getColor("yellow"));
        setVerticalTextPosition(SwingConstants.BOTTOM);
        setHorizontalTextPosition(SwingConstants.CENTER);
    }
    
    private void customizeIcons() {
        String[] iconNames = getIconNames();
        setIcon(getCustomIcon(iconNames[0]));
        setRolloverIcon(getCustomIcon(iconNames[1]));
        setPressedIcon(getCustomIcon(iconNames[2]));
    }
    
    private String[] getIconNames() {
        String[] iconNames = new String[3];
        StringBuilder sb = new StringBuilder();
        
        // Icon
        sb.append(iconName);
        sb.append("_");
        sb.append(iconColor);
        sb.append(".png");
        iconNames[0] = sb.toString();
        
        // Hover
        sb = new StringBuilder();
        sb.append(iconName);
        sb.append("_");
        sb.append(iconColor);
        sb.append("_HOVER.png");
        iconNames[1] = sb.toString();
        
        // Click
        sb = new StringBuilder();
        sb.append(iconName);
        sb.append("_");
        sb.append(clickColor);
        sb.append(".png");
        iconNames[2] = sb.toString();
        
        return iconNames;
    }
            
    private ImageIcon getCustomIcon (String iconName) {
         ImageIcon original = new ImageIcon(getURL(iconName));
        Image scaledImg = original.getImage();
        scaledImg = scaledImg.getScaledInstance(buttonSize, buttonSize, 
                Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }
    
    private URL getURL(String iconName) {
        StringBuilder sb = new StringBuilder();
        sb.append("icons/");
        sb.append(iconName);
        URL url = CustomButton.class.getResource(sb.toString());
        return url;
    }
}
