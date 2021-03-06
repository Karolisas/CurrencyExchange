package lt.karolis.microservices28.currencyexchangeservice.controller;

import lt.karolis.microservices28.currencyexchangeservice.repository.ExchangeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CurrencyExchangeController {

    private Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());

    @Autowired
    private Environment environment;

    @Autowired
    ExchangeRepository repository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange getExchangeRate(@PathVariable String from, @PathVariable String to) {

//        CurrencyExchange curencyExchange = new CurrencyExchange(1000L, from, to, new BigDecimal(50));

//A
        //B
        logger.info("getExchangeRate called with {} to {} ", from ,to);

        CurrencyExchange curencyExchange = repository.findByFromAndTo(from, to);
        if (curencyExchange == null) {
            throw new RuntimeException("currency not found from = " + from + " to " + to);
        }
        String port = environment.getProperty("local.server.port");
        curencyExchange.setEnvironment(port);
        return curencyExchange;
    }
}
