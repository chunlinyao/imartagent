package yao;

import javax.swing.*;

public class DebugUtil {

    static {
        debug();
    }

    public static void debug() {

        SwingUtilities.invokeLater(new StartAction());

    }

}
