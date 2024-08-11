package org.murapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class StopwatchApp extends JFrame {
    private static final int INITIAL_TIME = 25 * 60; // 25 minutes in seconds
    private int remainingTime = INITIAL_TIME;
    private Timer timer;
    private JLabel timeLabel;
    private JButton startButton;
    private JButton stopButton;
    private JButton resetButton;

    public StopwatchApp() {
        // Create UI components
        timeLabel = new JLabel(formatTime(remainingTime), SwingConstants.CENTER);
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        resetButton = new JButton("Reset");

        // Create a timer that updates every second
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (remainingTime > 0) {
                    remainingTime--;
                    timeLabel.setText(formatTime(remainingTime));
                } else {
                    timer.stop();
                    JOptionPane.showMessageDialog(null, "Time's up!");
                }
            }
        });

        // Set up the button actions
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.start();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                remainingTime = INITIAL_TIME;
                timeLabel.setText(formatTime(remainingTime));
            }
        });

        // Arrange components in the window
        setLayout(new BorderLayout());
        add(timeLabel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Set up the window
        setTitle("Stopwatch App");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private String formatTime(int seconds) {
        long minutes = TimeUnit.SECONDS.toMinutes(seconds);
        long remainingSeconds = seconds - TimeUnit.MINUTES.toSeconds(minutes);
        return String.format("%02d:%02d", minutes, remainingSeconds);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StopwatchApp().setVisible(true);
            }
        });
    }
}