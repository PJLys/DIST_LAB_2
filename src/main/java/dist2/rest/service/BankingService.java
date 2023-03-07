package dist2.rest.service;

import dist2.rest.api.model.BankClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BankingService {

    private List<BankClient> clients;

    public BankingService() {
        clients = new ArrayList<>();
    }

    public Optional<BankClient> getClient(Integer id) {
        return clients.stream().filter(x-> Objects.equals(x.getId(), id)).findFirst();
    }
}
