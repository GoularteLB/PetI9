package com.petI9.demo.domain;

import java.time.LocalDate;

public class Vacina {
    private String tipo;
    private LocalDate data;
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
}
