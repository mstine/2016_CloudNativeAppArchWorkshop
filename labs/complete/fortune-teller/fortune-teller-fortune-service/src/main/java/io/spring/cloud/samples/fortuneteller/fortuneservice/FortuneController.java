package io.spring.cloud.samples.fortuneteller.fortuneservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FortuneController {

    Logger logger = LoggerFactory.getLogger(FortuneController.class);

    @Autowired
    FortuneRepository repository;

    @RequestMapping("/fortunes")
    public Iterable<Fortune> fortunes() {
        return repository.findAll();
    }

    @RequestMapping("/random")
    public Fortune randomFortune() {
        logger.debug("Handling request to /random!");
        logger.info("SR: received call to /random.");
        List<Fortune> randomFortunes = repository.randomFortunes(new PageRequest(0, 1));
        logger.info("SS: responding to call to /random.");
        return randomFortunes.get(0);
    }
}
