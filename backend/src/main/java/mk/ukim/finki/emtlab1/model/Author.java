package mk.ukim.finki.emtlab1.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;

    @ManyToOne
    private Country country;

    public Author() {
    }

    public Author(Long id, String name, String surname,Country country) {
        this.id=id;
        this.name = name;
        this.surname=surname;
        this.country=country;
    }

    public Author(String name, String surname,Country country) {
        this.id=id;
        this.name = name;
        this.surname=surname;
        this.country=country;
    }

}
