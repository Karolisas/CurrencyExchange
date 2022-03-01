package lt.karolis.microservices28.currencyexchangeservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurencyExchange getExchangeRate(@PathVariable String from, @PathVariable String to) {

        return new CurencyExchange(1000L, from, to, new BigDecimal(50));
    }
}
