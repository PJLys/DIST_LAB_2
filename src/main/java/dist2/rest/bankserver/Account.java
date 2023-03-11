package dist2.rest.bankserver;

import jakarta.persistence.*;
import lombok.*;


@ToString
@Entity
@Getter
@Table
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Account {
    @SequenceGenerator(
            name = "account_sequence",
            sequenceName = "account_sequence",
            allocationSize = 1
    )
    private @Id @GeneratedValue Long id;
    private String name;
    private double bal;

    public Account(String name) {
        this.name = name;
        this.bal = 0.0;
    }

    public Account(String name, Long init_bal) {
        this.name = name;
        this.bal = init_bal;
    }
}
