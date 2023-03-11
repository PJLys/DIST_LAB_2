package dist2.rest.bankserver;

import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ClientController {
    private final AccountRepo repository;

    public ClientController(AccountRepo repository) {
        this.repository = repository;
    }

    @GetMapping("/accounts/{id}")
    EntityModel<Account> getById(@PathVariable Integer id) {
        Account a = repository.getReferenceById(id);

        return EntityModel.of(a,
                linkTo(methodOn(ClientController.class).getById(id)).withSelfRel(),
                linkTo(methodOn(ClientController.class).getAllAccounts()).withRel("accounts"));
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
    Account replaceAccount(@RequestBody Account new_acc, @PathVariable Integer id) {
        Account acc =  repository.getReferenceById(id);
        acc.setName(new_acc.getName());
        acc.setBal(new_acc.getBal());
        return acc;
    }

    @DeleteMapping("/accounts/{id}")
    void deleteEmployee(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}
