package dist2.rest.banksystem.bankclient;

import dist2.rest.banksystem.Account;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class SingleClient implements ClientIF{
    private String name;

    @Override
    public Account createAccount() {
        return null;
    }

    @Override
    public Account withdraw(BigDecimal amount) {
        return null;
    }

    @Override
    public Account deposit(BigDecimal amount) {
        return null;
    }
}
