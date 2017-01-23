//Class to load string properties in ConfigServer.properties file into variables for use in the application.

package nikedeck;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Service
public class ConfigService {

@Value("$(shuffle.class)")
public String shuffleClass;

@Value("$(shuffle.method)")
public String shuffleMethod;

    //To resolve ${} in @Value
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
