package com.petI9.demo.dto;

import java.time.LocalDate;

public class VaccineDTO {
    private String type;
    private LocalDate date;
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}
