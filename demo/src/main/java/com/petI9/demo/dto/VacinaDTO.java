package com.petI9.demo.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public class VacinaDTO {
    @NotBlank(message = "Name cannot be blank")
    private String type;
    private LocalDate date;
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public VacinaDTO() {}
    public VacinaDTO(String type, LocalDate date) {
        this.type = type;
        this.date = date;
    }
    
}
