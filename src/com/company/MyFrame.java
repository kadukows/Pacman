package com.company;

import com.company.fieldmatrixfactory.JsonMatrixFieldFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {
    private final JComboBox<String> comboBox_;
    private final JButton resetButton_;
    private final GridBagConstraints gbc_;
    private final KeyboardManager keyboardManager_;

    private Board board_;


    MyFrame() {
        keyboardManager_ = new KeyboardManager();

        setLayout(new GridBagLayout());

        gbc_ = new GridBagConstraints();
        Insets insets = new Insets(5, 5,5, 5);

        comboBox_ = new JComboBox<String>(new String[] {"test1.json", "test2.json"});
        gbc_.gridx = 0;
        gbc_.gridy = 1;
        gbc_.fill = GridBagConstraints.HORIZONTAL;
        gbc_.insets = insets;
        getContentPane().add(comboBox_, gbc_);

        resetButton_ = new JButton("Reset");
        resetButton_.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetBoard();
            }
        });
        gbc_.gridx = 1;
        gbc_.gridy = 1;
        gbc_.fill = GridBagConstraints.HORIZONTAL;
        getContentPane().add(resetButton_, gbc_);

        board_ = new Board(new JsonMatrixFieldFactory("test1.json"), keyboardManager_);
        gbc_.gridx = 0;
        gbc_.gridy = 0;
        gbc_.gridwidth = 2;
        gbc_.fill = GridBagConstraints.NONE;
        getContentPane().add(board_, gbc_);

        keyboardManager_.attachKeyStrokeToComponent(board_);
    }

    private void resetBoard() {
        String selectedLevel = (String) comboBox_.getSelectedItem();

        board_.forceStop();
        remove(board_);

        board_ = new Board(new JsonMatrixFieldFactory(selectedLevel), keyboardManager_);
        gbc_.gridx = 0;
        gbc_.gridy = 0;
        gbc_.gridwidth = 2;
        getContentPane().add(board_, gbc_);

        keyboardManager_.attachKeyStrokeToComponent(board_);

        pack();
        setVisible(true);
    }
}
