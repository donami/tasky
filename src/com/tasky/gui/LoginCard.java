package com.tasky.gui;

import com.tasky.app.User;
import com.tasky.db.dao.UserDao;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.xml.soap.Text;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by markus on 2017-02-28.
 */
public class LoginCard extends JPanel {

    final static String LOGIN_CARD = "Login Card";

    private BaseFrame baseFrame;
    private JButton loginButton;
    private TextField usernameTextField;
    private TextField passwordTextField;
    private JLabel welcomeLabel;
    private UserDao userDao;

    LoginCard(BaseFrame baseFrame) {
        this.baseFrame = baseFrame;

        this.userDao = new UserDao(this.baseFrame.getConnection());

        this.initComponents();
        this.createGUI();
        this.addEvents();
    }

    private void initComponents() {
        this.welcomeLabel = new JLabel("Welcome! Please enter your credentials");
        this.usernameTextField = new TextField();
        this.passwordTextField = new TextField();
        this.loginButton = new JButton("Login");
    }

    private void createGUI() {
        this.setLayout(new MigLayout("fillx, insets 60 20 20 20"));

        this.usernameTextField.setPreferredSize(new Dimension(500, 30));
        this.usernameTextField.setMaximumSize(this.usernameTextField.getPreferredSize());
        this.passwordTextField.setPreferredSize(new Dimension(500, 30));
        this.passwordTextField.setMaximumSize(this.passwordTextField.getPreferredSize());

        this.add(this.welcomeLabel, "align center, span");
        this.add(this.usernameTextField, "align center, span");
        this.add(this.passwordTextField, "align center, span");
        this.add(this.loginButton, "align center, wrap");
    }

    private void addEvents() {
        this.loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login(usernameTextField.getText(), passwordTextField.getText());
            }
        });
    }

    private void login(String username, String password) {
        User user = new User(username, password);

        if (this.validLogin(user)) {
            this.baseFrame.setIsAuthed(true, user);

            HomeCard homeCard = new HomeCard(baseFrame);
            baseFrame.getCardHandler().add(homeCard, HomeCard.HOME_CARD);
            baseFrame.getCardHandler().getCardLayout().show(baseFrame.getCardHandler(), HomeCard.HOME_CARD);
        }
        else {
            JOptionPane.showMessageDialog(this.baseFrame, "Invalid credentials");
        }
    }

    private boolean validLogin(User user) {
        return this.userDao.auth(user);
    }

}
