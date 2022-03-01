package lt.karolis.microservices28.currencyexchangeservice.repository;

import lt.karolis.microservices28.currencyexchangeservice.controller.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends JpaRepository<CurrencyExchange, Long> {


    CurrencyExchange findByFromAndTo(String from, String to);
}
