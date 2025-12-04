package views;

import controllers.ItemController;
import models.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;

public class ReportPanel extends JPanel {
    private ItemController controller = new ItemController();
    private JTextField nameField, contactField;
    private JTextArea descriptionArea;
    private JComboBox<String> categoryCombo, typeCombo;
    private JSpinner dateSpinner;

    public ReportPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Name
        add(new JLabel("Item Name:"), createGbc(0, 0));
        nameField = new JTextField(20);
        add(nameField, createGbc(1, 0));

        // Description
        add(new JLabel("Description:"), createGbc(0, 1));
        descriptionArea = new JTextArea(3, 20);
        add(new JScrollPane(descriptionArea), createGbc(1, 1));

        // Category
        add(new JLabel("Category:"), createGbc(0, 2));
        categoryCombo = new JComboBox<>(new String[]{"Electronics", "Clothing", "Books", "Other"});
        add(categoryCombo, createGbc(1, 2));

        // Type (Lost/Found)
        add(new JLabel("Type:"), createGbc(0, 3));
        typeCombo = new JComboBox<>(new String[]{"Lost", "Found"});
        add(typeCombo, createGbc(1, 3));

        // Date
        add(new JLabel("Report Date:"), createGbc(0, 4));
        dateSpinner = new JSpinner(new SpinnerDateModel());
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd"));
        add(dateSpinner, createGbc(1, 4));

        // Contact
        add(new JLabel("Contact Info:"), createGbc(0, 5));
        contactField = new JTextField(20);
        add(contactField, createGbc(1, 5));

        // Submit Button
        JButton submitBtn = new JButton("Report Item");
        submitBtn.addActionListener(new SubmitListener());
        add(submitBtn, createGbc(0, 6, 2, 1));

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private GridBagConstraints createGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = GridBagConstraints.WEST;
        return gbc;
    }

    private GridBagConstraints createGbc(int x, int y, int width, int height) {
        GridBagConstraints gbc = createGbc(x, y);
        gbc.gridwidth = width;
        gbc.gridheight = height;
        return gbc;
    }

    private class SubmitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Item item = new Item(
                nameField.getText(),
                descriptionArea.getText(),
                (String) categoryCombo.getSelectedItem(),
                LocalDate.ofInstant(((java.util.Date) dateSpinner.getValue()).toInstant(), ZoneId.systemDefault()),
                contactField.getText(),
                "Lost".equals(typeCombo.getSelectedItem())
            );
            controller.addItem(item);
            JOptionPane.showMessageDialog(ReportPanel.this, "Item reported successfully!");
            clearFields();
        }
    }

    private void clearFields() {
        nameField.setText("");
        descriptionArea.setText("");
        contactField.setText("");
        categoryCombo.setSelectedIndex(0);
        typeCombo.setSelectedIndex(0);
        dateSpinner.setValue(new java.util.Date());
    }
}
