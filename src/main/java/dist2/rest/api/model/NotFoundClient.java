package dist2.rest.api.model;

public class NotFoundClient extends BankClient {
    private final Integer id = -1;
    private final String name = "Client Not Found!";
    private final static BankClient instance = new NotFoundClient();

    public static BankClient get() {
        return instance;
    }
}
