package yao;

import jp.co.intra_mart.system.javascript.ContextFactory;
import jp.co.intra_mart.system.javascript.Scriptable;
import jp.co.intra_mart.system.javascript.tools.debugger.Main;
import jp.co.intra_mart.system.javascript.tools.debugger.ScopeProvider;
import jp.co.intra_mart.system.javascript.tools.shell.Global;

import javax.swing.*;

class StartAction implements Runnable {

    @Override
    public void run() {
        ContextFactory factory = ContextFactory.getGlobal();
        Global global = new Global();
        global.init(factory);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        Main main = new Main("Rhino JavaScript Debugger (embedded usage)");
        //main.doBreak();
        main.setBreakOnExceptions(true);
        main.attachTo(factory);
        if (global instanceof ScopeProvider) {
            main.setScopeProvider((ScopeProvider) global);
        } else {
            Scriptable scope = (Scriptable) global;
            global.setIn(main.getIn());
            global.setOut(main.getOut());
            global.setErr(main.getErr());
            main.setScope(global);
        }
        main.getDebugFrame().setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        main.pack();
        main.setSize(600, 460);
        main.setVisible(true);
    }
}
