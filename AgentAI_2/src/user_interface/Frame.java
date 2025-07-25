package user_interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Main;

public class Frame extends JFrame implements ActionListener {

    private JTextField emailField;
    private JTextField titleField;
    private JButton submitButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Frame::new); 
        
    }

    public Frame() {
        setTitle("Email Submission Form");
        setSize(450, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen

        // Set background color
        getContentPane().setBackground(new Color(245, 245, 245));

        // Main panel with padding
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create fields with labels
        emailField = createTextField("kristjan.toplana20@gmail.com", "Enter your email...");
        titleField = createTextField("The brain and the importance of using it", "Enter the title...");

        JPanel emailPanel = createLabeledPanel("Email:", emailField);
        JPanel titlePanel = createLabeledPanel("Title:", titleField);

        // Submit button
        submitButton = new JButton("Submit");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.setBackground(new Color(0x4CAF50));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitButton.setPreferredSize(new Dimension(120, 40));
        submitButton.addActionListener(this);

        // Add all components
        panel.add(emailPanel);
        panel.add(titlePanel);
        panel.add(Box.createVerticalStrut(15));
        panel.add(submitButton);

        add(panel);
        setVisible(true);
    }

    private JTextField createTextField(String defaultText, String tooltip) {
        JTextField field = new JTextField(25);
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        field.setText(defaultText);
        field.setToolTipText(tooltip);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return field;
    }

    private JPanel createLabeledPanel(String labelText, JTextField field) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(new Color(0x333333));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBackground(new Color(245, 245, 245));
        panel.add(label);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(field);

        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String email = emailField.getText().trim();
        String title = titleField.getText().trim();

        if (email.isEmpty() || title.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in both fields.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Your logic here
        Main main = new Main(email, title);
        main.do_something();

        JOptionPane.showMessageDialog(this, "✅ Email sent successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}
