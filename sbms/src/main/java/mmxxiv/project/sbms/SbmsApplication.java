package mmxxiv.project.sbms;

import mmxxiv.project.core.property.JwtConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan({"mmxxiv.project.core.model"})
@EnableJpaRepositories({"mmxxiv.project.core.repository"})
@EnableDiscoveryClient
@EnableEurekaServer
@EnableConfigurationProperties(value = JwtConfiguration.class)
@ComponentScan("mmxxiv.project")
public class SbmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbmsApplication.class, args);
	}

}
