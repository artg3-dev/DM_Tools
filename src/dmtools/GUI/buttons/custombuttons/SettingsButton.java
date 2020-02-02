/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmtools.GUI.buttons.custombuttons;

import dmtools.GUI.LayoutConstants;
import dmtools.GUI.buttons.CustomButton;

/**
 *
 * @author A3
 */
public class SettingsButton extends CustomButton {

    public SettingsButton() {
        super("SETTINGS",
                LayoutConstants.NAVIGATOR_ICON_COLOR,
                LayoutConstants.NAVIGATOR_ICON_CLICK,
                LayoutConstants.NAVIGATOR_BUTTON_SIZE,
                "Customize Settings");
    }
}
