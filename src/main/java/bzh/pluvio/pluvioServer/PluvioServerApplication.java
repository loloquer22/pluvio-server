package bzh.pluvio.pluvioServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Pluvio-server
 *
 */
@SpringBootApplication
public class PluvioServerApplication {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(PluvioServerApplication.class);	
	
	
    public static void main( String[] args )
    {
    	SpringApplication.run(PluvioServerApplication.class, args);
    }
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/pluvio/*").allowedOrigins("http://localhost");
            }
        };
    }
}
