package dist2.rest.banksystem.bankclient;

import dist2.rest.banksystem.Account;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

@ToString
@Getter
@Setter
public class BankClient {
    private static final String BASE_URL = "http://localhost:8080";
    private static final RestTemplate restTemplate = new RestTemplate();
    private final String name;
    private final ArrayList<Long> ids = new ArrayList<>();


    public BankClient(String name) {
        this.name = name;
    }

    public Account createAccount() {
        // Create your account
        Account my_acc = new Account(this.name);

        // POST headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // Request body
        HttpEntity<Account> request_entity = new HttpEntity<>(my_acc, headers);

        // Receive response from POST
        ResponseEntity<Account> responseEntity = restTemplate.postForEntity(
                BASE_URL + "/accounts",
                request_entity,
                Account.class);

        // get the status code and the created account
        int statusCode = responseEntity.getStatusCode().value();
        Account createdAccount = responseEntity.getBody();

        // print the results
        System.out.println("Status code: " + statusCode);
        System.out.println("Created account: " + createdAccount);

        assert createdAccount != null;

        ids.add(createdAccount.getId());
        return createdAccount;
    }

    public int deleteAcc(Long id) {
        assert this.ids.stream().map(x -> x.equals(id)).findAny().isPresent();
        // headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // body
        HttpEntity<Long> req = new HttpEntity<>(id, headers);

        // req
        restTemplate.delete(BASE_URL + "/accounts/" + id, req);

        return HttpStatus.OK.value();
    }

    public Account getAcc(Long id) {
        //Retrieve response from get request to our account
        ResponseEntity<Account> responseEntity = restTemplate.getForEntity(
                BASE_URL + "/accounts/" + id,
                Account.class
        );

        // get the status code and the retrieved account
        int statusCode = responseEntity.getStatusCode().value();
        Account account = responseEntity.getBody();

        // print the results
        System.out.println("Status code: " + statusCode);
        System.out.println("Retrieved account: " + account);

        return account;
    }

    public BigDecimal withdraw(BigDecimal amount, Long id) {
        assert ids.stream().map(x -> x.equals(id)).findAny().isPresent();
        // headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // body
        HttpEntity<BigDecimal> req = new HttpEntity<>(amount, headers);
        // request
        ResponseEntity<Account> res = restTemplate.exchange(
                BASE_URL + "/accounts/" + id.toString() + "/withdraw",
                HttpMethod.PUT,
                req,
                Account.class
        );

        // Print the response status and body
        System.out.println("Response status code: " + res.getStatusCode());
        System.out.println("Response body: " + Objects.requireNonNull(res.getBody()));

        assert res.getBody() != null;

        return res.getBody().getBal();
    }

    public BigDecimal deposit(BigDecimal amount, Long id) {
        assert ids.stream().map(x -> x.equals(id)).findAny().isPresent();
        // headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // body
        HttpEntity<BigDecimal> req = new HttpEntity<>(amount, headers);
        // request
        ResponseEntity<Account> res = restTemplate.exchange(
                BASE_URL + "/accounts/" + id.toString() + "/deposit",
                HttpMethod.PUT,
                req,
                Account.class
        );
        // Print the response status and body
        System.out.println("Response status code: " + res.getStatusCode());
        System.out.println("Response body: " + Objects.requireNonNull(res.getBody()));

        assert res.getBody() != null;

        return res.getBody().getBal();
    }

    public static void main(String[] args) {
        System.out.println("This is a BankClient");

        BankClient alice = new BankClient("Alice");
        BankClient bob = new BankClient("Bob");

        alice.createAccount();
        Account acc_a_1 = alice.getAcc(alice.getIds().get(0));

        alice.createAccount();

        bob.createAccount();

        alice.deposit(BigDecimal.valueOf(1000), alice.getIds().get(1));
        Account acc_a_2 = alice.getAcc(alice.getIds().get(1));

        System.out.println(acc_a_1);
        System.out.println(acc_a_2);



    }
}
