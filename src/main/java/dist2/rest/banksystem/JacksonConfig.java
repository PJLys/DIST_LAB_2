package dist2.rest.banksystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        Hibernate5JakartaModule module = new Hibernate5JakartaModule();
        module.enable(Hibernate5JakartaModule.Feature.FORCE_LAZY_LOADING);
        mapper.registerModule(module);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.enable(SerializationFeature.INDENT_OUTPUT); // enable pretty-printing
        return mapper;
    }
}
