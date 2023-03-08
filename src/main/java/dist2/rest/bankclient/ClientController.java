package dist2.rest.bankclient;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {
    private final AccountRepo repository;

    public ClientController(AccountRepo repository) {
        this.repository = repository;
    }

    @GetMapping("/accounts/{id}")
    Account getById(@PathVariable int id) {
        return repository.getReferenceById(id);
    }

    @GetMapping("/accounts")
    List<Account> getAllAccounts()  {
        return repository.findAll();
    }

    @PostMapping("/accounts")
    Account addAccount(@RequestBody Account new_acc) {
        return repository.save(new_acc);
    }

    @PutMapping("/accounts/{id}")
    Account replaceAccount(@RequestBody Account new_acc, @PathVariable int id) {
        Account acc =  repository.getReferenceById(id);
        acc.setName(new_acc.getName());
        acc.setBal(new_acc.getBal());
        return acc;
    }

    @DeleteMapping("/accounts/{id}")
    void deleteEmployee(@PathVariable int id) {
        repository.deleteById(id);
    }
}
