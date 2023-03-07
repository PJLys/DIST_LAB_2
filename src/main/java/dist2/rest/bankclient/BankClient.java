package dist2.rest.bankclient;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@ToString
@Entity
@Getter
@Setter
@AllArgsConstructor
public class BankClient {
    private String name;
    @Id
    private Integer id;
    private double bal;

    public BankClient(String name) {
        this.name = name;
        this.id = -1;
        this.bal = 0.0;
    }

    @Deprecated
    public BankClient() {
        this.name = null;
        this.id = -1;
        this.bal = 0;
    }
}
