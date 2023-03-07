package dist2.rest.bankclient;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    public List<BankClient> getClients() {
        return List.of(
                new BankClient("Bart"),
                new BankClient("Benny", 500, 10.20),
                new BankClient("Rita")
        );
    }

}
