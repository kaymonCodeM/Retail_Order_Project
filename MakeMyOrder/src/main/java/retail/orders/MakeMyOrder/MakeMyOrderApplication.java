package retail.orders.MakeMyOrder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import retail.orders.MakeMyOrder.Config.RsaKeyProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class MakeMyOrderApplication {


	public static void main(String[] args) {
		SpringApplication.run(MakeMyOrderApplication.class, args);
	}

}
