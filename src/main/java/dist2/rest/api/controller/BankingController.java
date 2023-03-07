package dist2.rest.api.controller;

import dist2.rest.api.model.BankClient;
import dist2.rest.api.model.NotFoundClient;
import dist2.rest.service.BankingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankingController {

    private BankingService bankingService;

    public BankingController(BankingService bankingService) {
        this.bankingService = bankingService;
    }

    @GetMapping
    public BankClient getClient(@RequestParam Integer id){
        return this.bankingService.getClient(id).orElse(NotFoundClient.get());
    }
}
