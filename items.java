package models;

import java.time.LocalDate;

public class items {
    private int id;
    private String name;
    private String description;
    private String category;
    private LocalDate reportDate;
    private String contact;
    private boolean isLost;
    private String status;

    // Constructors
    public items() {}
    
    public items(String name, String description, String category, LocalDate reportDate, 
                String contact, boolean isLost) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.reportDate = reportDate;
        this.contact = contact;
        this.isLost = isLost;
        this.status = "Pending";
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public LocalDate getReportDate() { return reportDate; }
    public void setReportDate(LocalDate reportDate) { this.reportDate = reportDate; }
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
    public boolean isLost() { return isLost; }
    public void setLost(boolean lost) { isLost = lost; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
