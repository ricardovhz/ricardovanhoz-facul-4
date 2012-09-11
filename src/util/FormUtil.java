/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Dimension;
import java.awt.Window;

/**
 *
 * @author 808012
 */
public class FormUtil {

    public static void centraliza(Window frame) {
        Dimension df = frame.getSize();
        Dimension ds = frame.getToolkit().getScreenSize();

        frame.setLocation((ds.width - df.width) / 2, (ds.height - df.height) / 2);
    }
}
