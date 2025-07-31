package com.petI9.demo.domain;

import jakarta.persistence.Embeddable;
import java.time.LocalDate;
<<<<<<< HEAD

=======
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
>>>>>>> master
@Embeddable
public class Vacina {
    private String type;
    private LocalDate date;
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}
