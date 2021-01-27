package backend;

import backend.security.ApplicationContextImpl;
import backend.security.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Run {

	public static void main( String[] args ) {
		SpringApplication.run( Run.class, args );
	}

	@Bean
	public ApplicationContextImpl applicationContextImpl() {
		return new ApplicationContextImpl();
	}

	@Bean( name = "ApplicationProperties" )
	public ApplicationProperties getApplicationProperties() {
		return new ApplicationProperties();
	}
}
