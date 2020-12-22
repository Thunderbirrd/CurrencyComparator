package models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = {"name"})
public class Currency {
    private String name;
    private double courseToday;
    private double courseYesterday;

    public Currency(String name) {
        this.name = name;
    }
}
