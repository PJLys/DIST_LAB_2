package dist2.rest.banksystem.bankserver;

import dist2.rest.banksystem.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(AccountRepo repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Account("Ronny")));
            log.info("Preloading " + repository.save(new Account("Rütger", 500L)));
        };
    }
}
