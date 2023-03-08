package dist2.rest.bankclient;
import org.springframework.data.jpa.repository.JpaRepository;

interface AccountRepo extends JpaRepository<Account, Integer> {
}
