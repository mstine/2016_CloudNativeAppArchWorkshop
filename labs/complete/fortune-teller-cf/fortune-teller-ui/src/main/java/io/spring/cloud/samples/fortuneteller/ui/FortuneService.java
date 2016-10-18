package io.spring.cloud.samples.fortuneteller.ui;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@EnableConfigurationProperties(FortuneProperties.class)
public class FortuneService {

    Logger logger = LoggerFactory.getLogger(FortuneService.class);

    @Autowired
    FortuneProperties fortuneProperties;

    @Autowired
    @LoadBalanced
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallbackFortune")
    public Fortune randomFortune() {
        logger.info("CS: calling http://fortunes/random.");
        Fortune fortune = restTemplate.getForObject("http://fortunes/random", Fortune.class);
        logger.info("CR: received response from http://fortunes/random.");
        return fortune;
    }

    private Fortune fallbackFortune() {
        return new Fortune(42L, fortuneProperties.getFallbackFortune());
    }
}
