package bzh.pluvio.pluvioServer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Pluvio-server
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
public class PluvioServerApplication {
	
	private static final Logger LOGGER = LogManager.getLogger(PluvioServerApplication.class);	
	
	
    public static void main( String[] args )
    {
    	SpringApplication.run(PluvioServerApplication.class, args);
    	
    	LOGGER.info("Info level log message");
        LOGGER.debug("Debug level log message");
        LOGGER.error("Error level log message");
    }
    
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/pluvio/*").allowedOrigins("http://localhost");
//            }
//        };
//    }
}
