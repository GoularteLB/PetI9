package com.petI9.demo.domain;

import jakarta.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class Vaccine {
    private String type;
    private LocalDate date;
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}
