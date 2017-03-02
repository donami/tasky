package com.tasky.gui;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by markus on 2017-02-28.
 */

public class TaskListCard extends JPanel {
    final static String TASK_LIST_CARD = "Task List Card";

    private BaseFrame baseFrame;
    private JLabel titleLabel;
    private JButton deleteTaskButton;
    private JButton addTaskButton;
    private JList taskList;

    public TaskListCard(BaseFrame baseFrame) {
        this.baseFrame = baseFrame;

        this.initComponents();
        this.createGUI();
        this.addEvents();
    }

    private void initComponents() {
        this.setLayout(new MigLayout("fill"));

        this.taskList = new JList(this.baseFrame.getTaskHandler().getListModel());
        this.taskList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        this.taskList.setVisibleRowCount(-1);

        this.titleLabel = new JLabel("Your tasks");
        this.addTaskButton = new JButton("Add task");
        this.deleteTaskButton = new JButton("Remove task");
        this.deleteTaskButton.setEnabled(false);
    }

    private void createGUI() {
        this.add(this.titleLabel, "w 80%");
        this.add(this.addTaskButton, "w 10%");
        this.add(this.deleteTaskButton, "w 10%, wrap");
        this.add(this.taskList, "w 100%, h 100%, span, wrap");
    }

    private void addEvents() {
        this.deleteTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (taskList.getSelectedIndex() > -1) {
                    baseFrame.getTaskHandler().deleteTask(taskList.getSelectedIndex());

                    // If there are no remaining tasks, or no task is selected,
                    // disable the button
                    if (baseFrame.getTaskHandler().getListModel().getSize() <= 0 ||
                            taskList.getSelectedIndex() == -1)
                    {
                        deleteTaskButton.setEnabled(false);
                    }
                }
            }
        });

        this.addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskName = (String) JOptionPane.showInputDialog("Task name");

                // If a task name is provided, write to file
                if ((taskName != null) && (taskName.length() > 0)) {
                    baseFrame.getTaskHandler().addTask(taskName);
                }
            }
        });

        this.taskList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    deleteTaskButton.setEnabled(true);
                }
            }
        });
    }
}
