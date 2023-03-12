package dist2.rest.banksystem.bankclient;

import dist2.rest.banksystem.Account;

import java.math.BigDecimal;

public interface ClientIF {
    Account createAccount();
    Account withdraw(BigDecimal amount);
    Account deposit(BigDecimal amount);
}
