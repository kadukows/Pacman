package com.company;

import com.company.fieldmatrixfactory.JsonMatrixFieldFactory;
import javax.swing.*;

public class Main {
    private static void createAndShowGui() {
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Board board = new Board(new JsonMatrixFieldFactory("test1.json"));
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
