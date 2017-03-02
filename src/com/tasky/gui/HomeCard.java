package com.tasky.gui;

import javax.swing.*;

/**
 * Created by markus on 2017-02-28.
 */

public class HomeCard extends JPanel {
    final static String HOME_CARD = "Home Card";

    private BaseFrame baseFrame;
    private JLabel welcomeLabel;

    HomeCard(BaseFrame baseFrame) {
        this.baseFrame = baseFrame;

        this.initComponents();
        this.createGUI();
    }

    private void initComponents() {
        if (this.baseFrame.getIsAuthed()) {
            this.welcomeLabel = new JLabel("Welcome, " + this.baseFrame.getAuth().getUsername());
        }
        else {
            this.welcomeLabel = new JLabel("Welcome, guest");
        }
    }

    private void createGUI() {
        this.add(this.welcomeLabel);
    }
}
