package com.tasky.gui;

import javax.swing.*;

/**
 * Created by markus on 2017-02-28.
 */

public class Tasky {
    private BaseFrame frame;

    Tasky() {
        this.frame = new BaseFrame();
//        JMenuBar menuBar = new AppMenu(this.frame);
//        this.frame.setJMenuBar(menuBar);
        this.frame.setVisible(true);
    }
}
