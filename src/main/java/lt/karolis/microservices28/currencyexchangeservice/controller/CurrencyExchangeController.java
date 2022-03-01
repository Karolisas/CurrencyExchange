package lt.karolis.microservices28.currencyexchangeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurencyExchange getExchangeRate(@PathVariable String from, @PathVariable String to) {

        CurencyExchange curencyExchange = new CurencyExchange(1000L, from, to, new BigDecimal(50));
        String port = environment.getProperty("local.server.port");
        curencyExchange.setEnvironment(port);
        return curencyExchange;
    }
}
