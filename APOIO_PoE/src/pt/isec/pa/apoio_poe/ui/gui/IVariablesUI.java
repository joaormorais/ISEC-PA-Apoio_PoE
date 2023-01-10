package pt.isec.pa.apoio_poe.ui.gui;

import java.awt.*;

public interface IVariablesUI {

    // find what resolution the user is using
    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    public final int heightScreen = gd.getDisplayMode().getHeight();
    public final int widthScreen = gd.getDisplayMode().getWidth();

    public static final int BTN_SIZE = 40;
    public static final int BTN_MINWIDTH = 200;

}
