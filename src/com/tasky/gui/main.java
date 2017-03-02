package com.tasky.gui;

import javax.swing.*;

/**
 * Created by markus on 2017-02-28.
 */
public class main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        Tasky app = new Tasky();
    }
}
