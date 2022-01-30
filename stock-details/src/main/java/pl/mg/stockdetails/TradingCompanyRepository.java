package pl.mg.stockdetails;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface TradingCompanyRepository extends ReactiveMongoRepository<TradingCompany, String> {

    Mono<TradingCompany> findByTicker(String ticker);

}
