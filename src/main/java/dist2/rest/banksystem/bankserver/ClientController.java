package dist2.rest.banksystem.bankserver;

import dist2.rest.banksystem.Account;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ClientController {
    private final AccountRepo repository;
    private final AccountModelAssembler assembler;
    private final ExecutorService executorService;

    public ClientController(AccountRepo repository, AccountModelAssembler assembler, ExecutorService executorService) {
        this.repository = repository;
        this.assembler = assembler;
        this.executorService = executorService;
    }

    @GetMapping("/accounts/{id}")
    EntityModel<Account> getById(@PathVariable Long id) {
        Account acc = repository.findById(id) //
                .orElseThrow(() -> new RuntimeException("Id Not found"));
        return assembler.toModel(acc);
    }

    @GetMapping("/accounts")
    CollectionModel<EntityModel<Account>> getAllAccounts()  {
        List<EntityModel<Account>> accs = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(accs, linkTo(methodOn(ClientController.class).getAllAccounts()).withSelfRel());
    }

    @PostMapping("/accounts")
    ResponseEntity<?> addAccount(@RequestBody Account newAcc) {
        AtomicReference<Account> acc = new AtomicReference<>(new Account());
        executorService.submit(() -> {
            synchronized (acc)  {
                 acc.set(repository.save(newAcc));
            }
        });

        EntityModel<Account> entityModel = assembler.toModel(acc.get());
        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @PutMapping("/accounts/{id}/replace")
    ResponseEntity<?> replaceAccount(@RequestBody Account new_acc, @PathVariable Long id) {
        AtomicReference<Account> acc = new AtomicReference<>(new Account());

        executorService.submit(() -> {
            synchronized (acc) {
                 acc.set(repository.findById(id)
                         .map(a -> {
                             a.setName(new_acc.getName());
                             a.setBal(new_acc.getBal());
                             return repository.save(a);
                         })
                         .orElseGet(() -> {
                             new_acc.setId(id);
                             return repository.save(new_acc);
                         }));
            }
        });

        EntityModel<Account> entityModel = assembler.toModel(acc.get());
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/accounts/{id}/withdraw")
    ResponseEntity<?> withdraw(@PathVariable Long id, @RequestBody BigDecimal amount) {
        // Check if Account exists
        Account acc = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID not found"));

        // Withdraw DINERO
        executorService.submit(() -> {
            synchronized (acc) {
                if ((acc.getBal().compareTo(amount) == -1)) {
                    throw new RuntimeException("Insufficient funds!");
                }
                acc.setBal(acc.getBal().add(amount.negate()));
                repository.save(acc);
            }
        });

        EntityModel<Account> entityModel = assembler.toModel(acc);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/accounts/{id}/deposit")
    ResponseEntity<?> deposit(@PathVariable Long id, @RequestBody BigDecimal amount) {
        // Check if account exists
        Account acc = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID not found"));

        // Deposit DINERO
        executorService.submit(() -> {
            synchronized (acc) {
                acc.setBal(acc.getBal().add(amount));
                repository.save(acc);
            }
        });
        EntityModel<Account> entityModel = assembler.toModel(acc);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/accounts/{id}")
    void deleteAccount(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
