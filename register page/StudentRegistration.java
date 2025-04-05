import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class StudentRegistration {
    private JFrame frame;
    private JTextField nameField, mobileField;
    private JComboBox<Integer> dayBox, yearBox;
    private JComboBox<String> monthBox;
    private JTextArea addressArea;
    private JRadioButton maleButton, femaleButton;
    private JCheckBox termsCheckBox;
    private JLabel outputLabel;
    private JButton submitButton, resetButton;

    public StudentRegistration() {
        frame = new JFrame("Student Registration");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(7, 2, 5, 5));
        
        frame.add(new JLabel("Name:"));
        nameField = new JTextField();
        frame.add(nameField);
        
        frame.add(new JLabel("Mobile:"));
        mobileField = new JTextField();
        frame.add(mobileField);
        
        frame.add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel();
        maleButton = new JRadioButton("Male");
        femaleButton = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        frame.add(genderPanel);
        
        frame.add(new JLabel("DOB:"));
        JPanel dobPanel = new JPanel();
        dayBox = new JComboBox<>(createNumberArray(1, 31));
        monthBox = new JComboBox<>(new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"});
        yearBox = new JComboBox<>(createNumberArray(1900, 2010));
        dobPanel.add(dayBox);
        dobPanel.add(monthBox);
        dobPanel.add(yearBox);
        frame.add(dobPanel);
        
        frame.add(new JLabel("Address:"));
        addressArea = new JTextArea(3, 20);
        frame.add(new JScrollPane(addressArea));
        
        termsCheckBox = new JCheckBox("Accept Terms and Conditions");
        frame.add(termsCheckBox);
        frame.add(new JLabel(""));
        
        submitButton = new JButton("Submit");
        resetButton = new JButton("Reset");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        buttonPanel.add(resetButton);
        frame.add(buttonPanel);
        
        outputLabel = new JLabel("", SwingConstants.CENTER);
        frame.add(outputLabel);
        
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateAndDisplay();
            }
        });
        
        resetButton.addActionListener(e -> resetForm());
        
        frame.setVisible(true);
    }
    
    private Integer[] createNumberArray(int start, int end) {
        Integer[] array = new Integer[end - start + 1];
        for (int i = 0; i < array.length; i++) {
            array[i] = start + i;
        }
        return array;
    }
    
    private void validateAndDisplay() {
        if (!termsCheckBox.isSelected()) {
            outputLabel.setText("You must accept the terms and conditions!");
            return;
        }
        
        String name = nameField.getText();
        String mobile = mobileField.getText();
        int day = (int) dayBox.getSelectedItem();
        int year = (int) yearBox.getSelectedItem();
        String month = monthBox.getSelectedItem().toString();
        String gender = maleButton.isSelected() ? "Male" : femaleButton.isSelected() ? "Female" : "Not specified";
        String address = addressArea.getText();
        
        if (!mobile.matches("\\d{10}")) {
            outputLabel.setText("Invalid Mobile Number! Must be 10 digits.");
            return;
        }
        
        outputLabel.setText("Registered: " + name + ", " + gender + ", Mobile: " + mobile + ", DOB: " + day + " " + month + " " + year + ", Address: " + address);
    }
    
    private void resetForm() {
        nameField.setText("");
        mobileField.setText("");
        addressArea.setText("");
        maleButton.setSelected(false);
        femaleButton.setSelected(false);
        termsCheckBox.setSelected(false);
        outputLabel.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentRegistration::new);
    }
}
