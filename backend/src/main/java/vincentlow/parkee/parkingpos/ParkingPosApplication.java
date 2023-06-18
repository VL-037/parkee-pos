package vincentlow.parkee.parkingpos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application-dev.properties")
public class ParkingPosApplication {

  public static void main(String[] args) {

    SpringApplication.run(ParkingPosApplication.class, args);
  }
}
