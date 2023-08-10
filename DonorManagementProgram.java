import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DonorManagementProgram extends JFrame {
    private JTextField nameField;
    private JTextField emailField;
    private JTextField donationAmountField;
    private JTextArea donorListArea;
    private JButton addButton;
    private List<Donor> donors;

    public DonorManagementProgram() {
        setTitle("Non-Profit Donor Management");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        donors = new ArrayList<>();

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 10, 10));
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        inputPanel.add(emailField);
        inputPanel.add(new JLabel("Donation Amount:"));
        donationAmountField = new JTextField();
        inputPanel.add(donationAmountField);

        addButton = new JButton("Add Donor");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDonor();
            }
        });

        donorListArea = new JTextArea(10, 30);
        donorListArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(donorListArea);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(inputPanel, BorderLayout.NORTH);
        contentPane.add(addButton, BorderLayout.CENTER);
        contentPane.add(scrollPane, BorderLayout.SOUTH);

        setContentPane(contentPane);
    }

    private void addDonor() {
        String name = nameField.getText();
        String email = emailField.getText();
        String donationAmountStr = donationAmountField.getText();

        if (name.isEmpty() || email.isEmpty() || donationAmountStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        try {
            double donationAmount = Double.parseDouble(donationAmountStr);
            Donor donor = new Donor(name, email, donationAmount);
            donors.add(donor);
            updateDonorList();
            clearFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid donation amount.");
        }
    }

    private void updateDonorList() {
        donorListArea.setText("");
        for (Donor donor : donors) {
            donorListArea.append(donor.toString() + "\n");
        }
    }

    private void clearFields() {
        nameField.setText("");
        emailField.setText("");
        donationAmountField.setText("");
    }

    private class Donor {
        private String name;
        private String email;
        private double donationAmount;

        public Donor(String name, String email, double donationAmount) {
            this.name = name;
            this.email = email;
            this.donationAmount = donationAmount;
        }

        @Override
        public String toString() {
            return "Name: " + name + " | Email: " + email + " | Donation Amount: $" + donationAmount;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DonorManagementProgram().setVisible(true);
            }
        });
    }
}
