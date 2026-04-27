import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModernCalculator extends JFrame implements ActionListener {
    private JTextField display;
    private JPanel panel;
    private String[] buttons = {
        "C", "±", "%", "÷",
        "7", "8", "9", "×",
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "="
    };
    private double num1 = 0, num2 = 0, result = 0;
    private char operator;

    public ModernCalculator() {
        setTitle("Calculator");
        setSize(350, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(30, 30, 30));

        // Modern Display
        display = new JTextField("0");
        display.setEditable(false);
        display.setFont(new Font("Segoe UI", Font.BOLD, 40));
        display.setBackground(new Color(30, 30, 30));
        display.setForeground(Color.WHITE);
        display.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        // Buttons Panel
        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 8, 8));
        panel.setBackground(new Color(30, 30, 30));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (String text : buttons) {
            JButton btn = createModernButton(text);
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }

    private JButton createModernButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);

        // Styling based on button type
        if (text.matches("[0-9.]")) {
            btn.setBackground(new Color(60, 60, 60));
            btn.setForeground(Color.WHITE);
        } else if (text.equals("=")) {
            btn.setBackground(new Color(0, 120, 215)); // Modern Blue
            btn.setForeground(Color.WHITE);
        } else {
            btn.setBackground(new Color(45, 45, 45));
            btn.setForeground(new Color(200, 200, 200));
        }
        return btn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9.]")) {
            if (display.getText().equals("0")) display.setText(command);
            else display.setText(display.getText() + command);
        } else if (command.equals("C")) {
            display.setText("0");
            num1 = num2 = result = 0;
        } else if (command.matches("[+÷×-]")) {
            num1 = Double.parseDouble(display.getText());
            operator = command.charAt(0);
            display.setText("");
        } else if (command.equals("=")) {
            num2 = Double.parseDouble(display.getText());
            switch (operator) {
                case '+': result = num1 + num2; break;
                case '-': result = num1 - num2; break;
                case '×': result = num1 * num2; break;
                case '÷': result = num1 / num2; break;
            }
            display.setText(String.valueOf(result));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ModernCalculator().setVisible(true));
    }
}