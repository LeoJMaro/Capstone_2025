package QuotingApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"controller", "factories", "dataaccess"})
@EntityScan(basePackages = {"pojos"})
@EnableJpaRepositories(basePackages = {"dataaccess"})
public class SpringApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringApp.class, args);
    }
}
/**
 * These annotations were added to properly configure the Spring Boot application after
 * restructuring the packages. They explicitly tell Spring where to look for different components:
 *
 * @ComponentScan specifies where to find controllers, services, and other components
 * @EntityScan directs Spring to the location of JPA entity classes
 * @EnableJpaRepositories points to where the repository interfaces are stored
 *
 * Without these annotations, Spring would only scan within the application's package and miss components
 * in the reorganized directory structure.
 */
