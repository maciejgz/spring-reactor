package pl.mg.tradingservice;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

@Controller
public class QuotesController {


    private final QuotesClient quotesClient;
    private final TradingCompanyClient tradingCompanyClient;

    public QuotesController(QuotesClient quotesClient, TradingCompanyClient tradingCompanyClient) {
        this.quotesClient = quotesClient;
        this.tradingCompanyClient = tradingCompanyClient;
    }

    @GetMapping(path = "/quotes/feed", produces = TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public Flux<Quote> quotesFeed() {
        return this.quotesClient.quotesFeed();
    }


    @GetMapping(path = "/quotes/summary/{ticker}", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public Mono<TradingCompanySummary> quotesDetails(@PathVariable String ticker) {
        return tradingCompanyClient.getTradingCompany(ticker)
                .zipWith(this.quotesClient.getLatestQuote(ticker),
                        TradingCompanySummary::new);
    }
}
