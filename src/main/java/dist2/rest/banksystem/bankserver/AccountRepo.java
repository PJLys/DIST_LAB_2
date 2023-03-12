package dist2.rest.banksystem.bankserver;
import dist2.rest.banksystem.Account;
import org.springframework.data.jpa.repository.JpaRepository;

interface AccountRepo extends JpaRepository<Account, Long> {
}
