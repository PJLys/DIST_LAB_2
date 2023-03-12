package dist2.rest.banksystem;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


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
    private BigDecimal bal;

    public Account(String name) {
        this.name = name;
        this.bal = new BigDecimal(0);
    }

    public Account(String name, Long init_bal) {
        this.name = name;
        this.bal = new BigDecimal(init_bal);
    }
}
