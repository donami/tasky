package com.tasky.app;

import com.tasky.gui.BaseFrame;
import com.tasky.util.Encrypt;

import javax.swing.*;
import java.io.*;

/**
 * Created by markus on 2017-03-02.
 */
public class TaskHandler {
    private BaseFrame baseFrame;
    private DefaultListModel listModel;

    public TaskHandler(BaseFrame baseFrame) {
        this.baseFrame = baseFrame;
        this.listModel = new DefaultListModel();
    }

    /**
     * Get the filename to use based on the users username
     * @return String   Filename
     */
    private String getFilename() {
        return Encrypt.encrypt(this.baseFrame.getAuth().getUsername()) + ".txt";
    }

    public void addTask(String taskName) {
        // Add the task to the file, appending to previous tasks
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.getFilename(), true))) {

            String content = taskName + "\n";

            bw.write(content);

        } catch (IOException e) {
            e.printStackTrace();
        }

        this.listModel.addElement(taskName);
    }

    public void loadFromFile() {
        // Load the tasks from file to the list model
        try {
            File file = new File(this.getFilename());

            if(file.exists() && !file.isDirectory()) {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    this.listModel.addElement(line);
                }
                fileReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteTask(int index) {
        // Remove element from list model
        this.listModel.removeElementAt(index);

        // Write the tasks to the files, overwriting the old file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.getFilename()))) {
            for (Object taskName : this.listModel.toArray()) {
                bw.write((String)taskName + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DefaultListModel getListModel() {
        return this.listModel;
    }

}
