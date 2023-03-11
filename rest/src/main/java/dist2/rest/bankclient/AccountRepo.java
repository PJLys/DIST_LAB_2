package dist2.rest.bankserver;
import org.springframework.data.jpa.repository.JpaRepository;

interface AccountRepo extends JpaRepository<Account, Integer> {
}
