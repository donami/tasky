package com.tasky.gui;

import com.tasky.app.TaskHandler;
import com.tasky.app.User;
import com.tasky.db.MysqlAccess;

import javax.smartcardio.Card;
import javax.swing.*;
import java.sql.Connection;

/**
 * Created by markus on 2017-03-01.
 */
public class BaseFrame extends JFrame {
    private CardHandler cardHandler;
    private HomeCard homeCard;
    private Boolean isAuthed;
    private User auth;
    private AppMenu menuBar;
    private TaskHandler taskHandler;

    private Connection conn;

    BaseFrame() {
        this.isAuthed = false;
        this.auth = null;

        try {
            this.conn = new MysqlAccess().getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        this.menuBar = new AppMenu(this);
        this.setJMenuBar(this.menuBar);

        this.initComponents();
//        this.loadTasks();
        this.createGUI();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initComponents() {
        this.cardHandler = new CardHandler();
        this.homeCard = new HomeCard(this);
        this.taskHandler = new TaskHandler(this);
    }

    public void createGUI() {
        this.setSize(800, 600);
        this.add(this.cardHandler);
        if (isAuthed) {
            this.cardHandler.add(this.homeCard);
        }
        else {
            this.cardHandler.add(new LoginCard(this));
        }
    }

    public void refreshMenu() {
        this.menuBar.refresh();
    }

    public CardHandler getCardHandler() {
        return this.cardHandler;
    }

    public void setIsAuthed(Boolean isAuthed) {
        this.isAuthed = isAuthed;
        this.refreshMenu();
    }

    public void setIsAuthed(Boolean isAuthed, User auth) {
        this.setIsAuthed(isAuthed);
        this.auth = auth;
        this.loadTasks();
    }

    public Boolean getIsAuthed() {
        return this.isAuthed;
    }

    public User getAuth() {
        return this.auth;
    }

    public TaskHandler getTaskHandler() {
        return this.taskHandler;
    }

    private void loadTasks() {
        this.getTaskHandler().loadFromFile();
    }

    public Connection getConnection() {
        return this.conn;
    }
}
