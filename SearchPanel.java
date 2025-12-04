package views;

import controllers.ItemController;
import models.Item;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

public class SearchPanel extends JPanel {
    private ItemController controller = new ItemController();
    private JTextField keywordField;
    private JComboBox<String> categoryCombo;
    private JSpinner dateSpinner;
    private JTable resultsTable;
    private DefaultTableModel tableModel;

    public SearchPanel() {
        setLayout(new BorderLayout());

        // Search Panel
        JPanel searchPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0;
        searchPanel.add(new JLabel("Keyword:"), gbc);
        keywordField = new JTextField(15);
        gbc.gridx = 1;
        searchPanel.add(keywordField, gbc);

        gbc.gridx = 2; gbc.gridy = 0;
        searchPanel.add(new JLabel("Category:"), gbc);
        categoryCombo = new JComboBox<>(new String[]{"", "Electronics", "Clothing", "Books", "Other"});
        gbc.gridx = 3;
        searchPanel.add(categoryCombo, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        searchPanel.add(new JLabel("Date:"), gbc);
        dateSpinner = new JSpinner(new SpinnerDateModel());
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd"));
        gbc.gridx = 1; gbc.gridwidth = 3;
        searchPanel.add(dateSpinner, gbc);

        JButton searchBtn = new JButton("Search");
        searchBtn.addActionListener(e -> performSearch());
        gbc.gridx = 4; gbc.gridy = 1; gbc.gridwidth = 1;
        searchPanel.add(searchBtn, gbc);

        add(searchPanel, BorderLayout.NORTH);

        // Results Table
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Category", "Date", "Contact", "Status"}, 0);
        resultsTable = new JTable(tableModel);
        add(new JScrollPane(resultsTable), BorderLayout.CENTER);
    }

    private void performSearch() {
        String keyword = keywordField.getText();
        String category = (String) categoryCombo.getSelectedItem();
        if ("".equals(category)) category = null;
        LocalDate date = null;
        if (dateSpinner.getValue() != null) {
            date = LocalDate.ofInstant(((java.util.Date) dateSpinner.getValue()).toInstant(), ZoneId.systemDefault());
        }

        List