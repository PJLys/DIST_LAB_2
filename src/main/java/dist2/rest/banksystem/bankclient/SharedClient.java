package dist2.rest.banksystem.bankclient;

import dist2.rest.banksystem.Account;

import java.math.BigDecimal;

public class SharedClient implements ClientIF {
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
