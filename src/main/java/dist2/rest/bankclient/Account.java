package dist2.rest.bankclient;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;


@ToString
@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Account {
    private @Id @GeneratedValue Integer id;
    private String name;
    private double bal;

    public Account(String name) {
        this.name = name;
        this.id = -1;
        this.bal = 0.0;
    }

    @Deprecated
    public Account() {
        this.name = null;
        this.id = -1;
        this.bal = 0;
    }
}
