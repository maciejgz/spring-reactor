package pl.mg.stockdetails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class StockDetailsApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockDetailsApplication.class, args);
    }

}
