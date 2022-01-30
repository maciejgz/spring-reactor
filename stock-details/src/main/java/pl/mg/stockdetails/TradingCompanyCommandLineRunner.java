package pl.mg.stockdetails;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@Component
public class TradingCompanyCommandLineRunner implements CommandLineRunner {

    private final TradingCompanyRepository repository;

    public TradingCompanyCommandLineRunner(TradingCompanyRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<TradingCompany> companies = Arrays.asList(
                new TradingCompany("Pivotal Software", "PVTL"),
                new TradingCompany("Dell Technologies", "DELL"),
                new TradingCompany("Google", "GOOG"),
                new TradingCompany("Microsoft", "MSFT"),
                new TradingCompany("Oracle", "ORCL"),
                new TradingCompany("Red Hat", "RHT"),
                new TradingCompany("Vmware", "VMW")
        );
        this.repository.insert(companies).blockLast(Duration.ofSeconds(30));
    }
}
