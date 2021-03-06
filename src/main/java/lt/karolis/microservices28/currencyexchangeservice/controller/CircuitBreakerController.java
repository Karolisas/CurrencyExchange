package lt.karolis.microservices28.currencyexchangeservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/sample-api")
//    @Retry(name = "sample-api", fallbackMethod = "harcodedMethod")
    @CircuitBreaker(name = "default", fallbackMethod = "harcodedMethod")
    @RateLimiter(name = "default") // 10s => 10000 calls ->limitting calls
    @Bulkhead(name = "default") // concurrent calls
    public String sampleApi(){
        logger.info("ASD Sample API call received");
        HttpEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url",
                String.class);
        return forEntity.getBody();
    }

    public String harcodedMethod(Exception e){
        return "fallback-responce";
    }
}
