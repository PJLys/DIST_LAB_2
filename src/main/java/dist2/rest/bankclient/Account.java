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
        this.bal = 0.0;
    }

    @Deprecated
    public Account() {}

    public Account(String name, int init_bal) {
        this.name = name;
        this.bal = init_bal;
    }
}
