package com.tasky.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by markus on 2017-03-01.
 */
public class AppMenu extends JMenuBar {
    private BaseFrame baseFrame;
    private JMenu fileMenu;
    private JMenu taskMenu;
    private JMenuItem aboutItem;
    private JMenuItem loginItem;
    private JMenuItem homeItem;
    private JMenuItem addTaskItem;
    private JMenuItem taskListItem;

    public AppMenu(BaseFrame baseFrame) {
        this.baseFrame = baseFrame;

        this.initComponents();
        this.createGUI();
        this.addEvents();
    }

    private void initComponents() {
        this.fileMenu = new JMenu("File");

        this.homeItem = new JMenuItem("Start");
        this.loginItem = new JMenuItem("Login");
        this.aboutItem = new JMenuItem("About");

        this.taskMenu = new JMenu("Tasks");

        this.addTaskItem = new JMenuItem("Add task");
        this.taskListItem = new JMenuItem("Tasks");
    }

    private void createGUI() {
        this.add(this.fileMenu);

        if (this.baseFrame.getIsAuthed()) {
            this.fileMenu.add(this.homeItem);
            this.add(this.taskMenu);

            this.taskMenu.add(this.addTaskItem);
            this.taskMenu.add(this.taskListItem);
        }
        else {
            this.fileMenu.add(this.loginItem);
        }
        this.fileMenu.add(this.aboutItem);
    }

    private void addEvents() {
        this.homeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomeCard homeCard = new HomeCard(baseFrame);
                baseFrame.getCardHandler().add(homeCard, HomeCard.HOME_CARD);
                baseFrame.getCardHandler().getCardLayout().show(baseFrame.getCardHandler(), HomeCard.HOME_CARD);
            }
        });

        this.loginItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginCard loginCard = new LoginCard(baseFrame);
                baseFrame.getCardHandler().add(loginCard, LoginCard.LOGIN_CARD);
                baseFrame.getCardHandler().getCardLayout().show(baseFrame.getCardHandler(), LoginCard.LOGIN_CARD);
            }
        });

        this.aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AboutCard aboutCard = new AboutCard(baseFrame);
                baseFrame.getCardHandler().add(aboutCard, AboutCard.ABOUT_CARD);
                baseFrame.getCardHandler().getCardLayout().show(baseFrame.getCardHandler(), AboutCard.ABOUT_CARD);
            }
        });

        this.taskListItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TaskListCard taskListCard = new TaskListCard(baseFrame);
                baseFrame.getCardHandler().add(taskListCard, TaskListCard.TASK_LIST_CARD);
                baseFrame.getCardHandler().getCardLayout().show(baseFrame.getCardHandler(), TaskListCard.TASK_LIST_CARD);
            }
        });

        this.addTaskItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String taskName = (String) JOptionPane.showInputDialog("Task name");

                // If a task name is provided, write to file
                if ((taskName != null) && (taskName.length() > 0)) {
                    baseFrame.getTaskHandler().addTask(taskName);
                }
            }
        });
    }

    public void refresh() {
        this.fileMenu.removeAll();
        this.createGUI();
    }

}
