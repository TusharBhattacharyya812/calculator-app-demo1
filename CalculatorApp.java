import javax.swing.*;//For building frames, button etc. 
import java.awt.*;//Abstract window toolkit package
import java.awt.event.ActionEvent;//For events triggered by Button action
import java.awt.event.ActionListener;//For receiving notifications and processing actions when Button is clicked

//Introducing the elements of the calculator imported from JavaX Swing Package - Frame, Text Display, Number and Operation Buttons and Button Panel 
public class CalculatorApp extends JFrame {
    private JTextField displayField;
    private JButton[] numberButtons;
    private JButton addButton, subtractButton, multiplyButton, divideButton, equalsButton, clearButton;
    private JPanel buttonPanel;

    private String currentInput = "";
    private double currentValue = 0.0;
    private char currentOperator = ' ';

    public CalculatorApp() {
        setTitle("Calculator");//Title displayed on top of the frame 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Close Button on top of the frame
        setLayout(new BorderLayout());//Border layout

        displayField = new JTextField();
        displayField.setEditable(false);
        add(displayField, BorderLayout.NORTH);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(Integer.toString(i));
            final int num = i;
            numberButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentInput += num;
                    displayField.setText(currentInput);
                }
            });
            buttonPanel.add(numberButtons[i]);
        }

        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");
        equalsButton = new JButton("=");
        clearButton = new JButton("C");

        ActionListener operatorListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!currentInput.isEmpty()) {
                    currentValue = Double.parseDouble(currentInput);
                    currentInput = "";
                    currentOperator = e.getActionCommand().charAt(0);
                }
            }
        };

        addButton.addActionListener(operatorListener);
        subtractButton.addActionListener(operatorListener);
        multiplyButton.addActionListener(operatorListener);
        divideButton.addActionListener(operatorListener);

        equalsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!currentInput.isEmpty()) {
                    double secondValue = Double.parseDouble(currentInput);
                    double result = 0.0;

                    switch (currentOperator) {
                        case '+':
                            result = currentValue + secondValue;
                            break;
                        case '-':
                            result = currentValue - secondValue;
                            break;
                        case '*':
                            result = currentValue * secondValue;
                            break;
                        case '/':
                            if (secondValue != 0) {
                                result = currentValue / secondValue;
                            } else {
                                displayField.setText("Error: Division by zero");
                                return;
                            }
                            break;
                    }

                    displayField.setText(Double.toString(result));
                    currentInput = "";
                    currentValue = result;
                    currentOperator = ' ';
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentInput = "";
                currentValue = 0.0;
                currentOperator = ' ';
                displayField.setText("");
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(subtractButton);
        buttonPanel.add(multiplyButton);
        buttonPanel.add(divideButton);
        buttonPanel.add(equalsButton);
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CalculatorApp().setVisible(true);
            }
        });
    }
}
