package com.hungryhero;

// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.io.Decoders;
// import io.jsonwebtoken.security.Keys;
// import java.security.Key;
// import java.time.ZoneOffset;
// import java.time.ZonedDateTime;
// import java.util.HashMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@EntityScan("com.hungryhero")
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
