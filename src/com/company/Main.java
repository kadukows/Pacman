package com.company;

import com.company.field.AbstractField;
import com.company.field.WalkableField;
import com.company.field.WallField;
import com.company.fieldmatrixfactory.JsonMatrixFieldFactory;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;

public class Main {
    private static void createAndShowGui() {
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JButton button1 = new JButton("Button 1");
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(button1, c);

        JButton button2 = new JButton("Button 2");
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(button2, c);

        JButton button3 = new JButton("Button 3");
        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(button3, c);

        JButton button4 = new JButton("Long button 4");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        panel.add(button4, c);


        Board board = new Board(new JsonMatrixFieldFactory("test1.json"));
        c.gridx = 0;
        c.gridy = 2;
        frame.add(board);


        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args) {
	    javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGui();
            }
        });
    }
}
