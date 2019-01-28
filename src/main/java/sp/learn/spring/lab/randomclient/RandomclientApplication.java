package sp.learn.spring.lab.randomclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
public class RandomclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(RandomclientApplication.class, args);
	}

}

