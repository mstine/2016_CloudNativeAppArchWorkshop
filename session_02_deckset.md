slidenumbers: true

# [fit] Cloud
# [fit] Native
# [fit] Application Architecture
# [fit] Workshop
![](https://raw.githubusercontent.com/spring-projects/spring-cloud/gh-pages/img/project-icon-large.png)

---

# [fit] Session
# [fit] Two
![](Common/images/cf_logo.png)

---

# [fit] Building  
# [fit] and Composing
# [fit] Cloud Native Applications

---

# [fit] Building
# [fit] Twelve-Factor
# [fit] Apps
# [fit] with Spring Boot

---

![](Common/images/12factor.png)
# [fit] http://12factor.net

---

# Patterns

- Cloud-native application architectures
- Optimized for speed, safety, & scale
- Declarative configuration
- Stateless/shared-nothing processes
- Loose coupling to application environment

---

# Twelve Factors (1/2)

- One Codebase in Version Control
- Explicit Dependencies
- Externalized Config
- Attached Backing Services
- Separate Build, Release, and Run Stages
- Stateless, Shared-Nothing Processes

---

# Twelve Factors (2/2)

- Export Services via Port binding
- Scale Out Horizontally for Concurrency
- Instances Should Be Disposable
- Dev/Prod Parity
- Logs Are Event Streams
- Admin Processes

---

![fit](Common/images/heroku.png)
# [fit] http://heroku.com

---

![125%](Common/images/cloud_foundry.png)
# [fit] http://cloudfoundry.org

---

![filter](Common/images/dropwizard.png)
![](Common/images/spring-boot.png)
# Microframeworks

- Dropwizard ([http://www.dropwizard.io/](http://www.dropwizard.io/))
- Spring Boot ([http://projects.spring.io/spring-boot/](http://projects.spring.io/spring-boot/))

---

# Spring Boot
- [http://projects.spring.io/spring-boot](http://projects.spring.io/spring-boot)
- Opinionated convention over configuration
- Production-ready Spring applications
- Embed Tomcat, Jetty or Undertow
- *STARTERS*
- Actuator: Metrics, health checks, introspection

---

# [http://start.spring.io](start.spring.io)
![](Common/images/start_spring.png)

---

# [fit] Writing a Single Service is
# [fit] Nice...

---

# [fit] But No Microservice
# [fit] is an Island
![](Common/images/island-house.jpg)

---

# Challenges of Distributed Systems

* Configuration Management
* Service Registration & Discovery
* Routing & Load Balancing
* Fault Tolerance (Circuit Breakers!)
* Monitoring

---

![](Common/images/netflix_oss.jpeg)

---

![](https://raw.githubusercontent.com/spring-projects/spring-cloud/gh-pages/img/project-icon-large.png)
# [fit] Spring Cloud
# [fit] Distributed System Patterns FTW!

---

![](https://raw.githubusercontent.com/spring-projects/spring-cloud/gh-pages/img/project-icon-large.png)
# [fit] Configuration
# [fit] Management

---

# [fit] Distributed?

---

![](https://raw.githubusercontent.com/spring-projects/spring-cloud/gh-pages/img/project-icon-large.png)
# [fit] Config
# [fit] Server!

---

![inline fit](Common/images/Config_Server.png)

---

# Config Server

```java
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }

}
```

---

# Config Server `application.yml`

```
server:
  port: 8888

spring:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/mstine/config-repo.git
```

---

![](Common/images/github.jpeg)

# `https://github.com/mstine/config-repo/blob/master/demo.yml`

```
greeting: Bonjour
```

---

# Config Client

```java
@SpringBootApplication
@RestController
public class DistConfigApplication {

    @Autowired
    private Greeter greeter;

    @RequestMapping("/")
    public String home() {
        return String.format("%s World!", greeter.getGreeting());
    }

    public static void main(String[] args) {
        SpringApplication.run(DistConfigApplication.class, args);
    }
}
```
---

# Config Client

```java
@Component
@RefreshScope
public class Greeter {

    @Value("${greeting}")
    private String greeting;

    public String getGreeting() {
        return greeting;
    }
}
```

---

# Config Client `bootstrap.yml`

```
spring:
  application:
    name: demo
```

---

![right fit](Common/images/rabbitmq.png)
# [fit] Cloud
# [fit] Bus!

---

![inline fit](Common/images/Cloud_Bus.png)

---

# [fit] `curl -X POST http://localhost:8080/bus/refresh`

---

![](https://raw.githubusercontent.com/spring-projects/spring-cloud/gh-pages/img/project-icon-large.png)
# [fit] Service
# [fit] Registration &
# [fit] Discovery

---

![inline fit](Common/images/Service_Registry.png)

---

# [fit] Eureka
![](Common/images/netflix_oss.jpeg)

---

# [fit] Producer
# [fit] Consumer

---

# Eureka Service Registry

```java
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }
}
```

---

# Producer

```java
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ProducerApplication {

    private Log log = LogFactory.getLog(ProducerApplication.class);
    private AtomicInteger counter = new AtomicInteger(0);

    @RequestMapping(value = "/", produces = "application/json")
    public String produce() {
        int value = counter.incrementAndGet();

        log.info(String.format("Produced a value: %s", value));
        return String.format("{\"value\": %s}", value);
    }

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }
}
```

---

# Consumer

```java
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ConsumerApplication {

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/")
    public String consume() {
        InstanceInfo instance = discoveryClient.getNextServerFromEureka("PRODUCER", false);

        RestTemplate restTemplate = new RestTemplate();
        ProducerResponse response = restTemplate.getForObject(instance.getHomePageUrl(), ProducerResponse.class);

        return String.format("{\"value\": %s}", response.getValue());
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
```

---

![](https://raw.githubusercontent.com/spring-projects/spring-cloud/gh-pages/img/project-icon-large.png)
# [fit] Routing &
# [fit] Load Balancing

---

![inline fit](Common/images/Ribbon.png)

---

# [fit] Ribbon
![](Common/images/netflix_oss.jpeg)

---

# Consumer with Load Balancer

```java
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ConsumerRibbonApplication {

    @Autowired
    private LoadBalancerClient loadBalancer;

    @RequestMapping("/")
    public String consume() {
        ServiceInstance instance = loadBalancer.choose("producer");
        URI producerUri = URI.create(String.format("http://%s:%s", instance.getHost(), instance.getPort()));

        RestTemplate restTemplate = new RestTemplate();
        ProducerResponse response = restTemplate.getForObject(producerUri, ProducerResponse.class);

        return String.format("{\"value\": %s}", response.getValue());
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerRibbonApplication.class, args);
    }
}
```

---

# Consumer with Ribbon-enabled `RestTemplate`

```java
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ConsumerRestTemplateApplication {

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    @RequestMapping("/")
    public String consume() {
        ProducerResponse response = restTemplate.getForObject("http://producer", ProducerResponse.class);

        return String.format("{\"value\": %s}", response.getValue());
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerRestTemplateApplication.class, args);
    }
}
```

---

# Feign Client

```java
@FeignClient("producer")
public interface ProducerClient {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    ProducerResponse getValue();
}
```

---

# Consumer with Feign Client

```java
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@RestController
public class ConsumerFeignApplication {

    @Autowired
    ProducerClient client;

    @RequestMapping("/")
    String consume() {
        ProducerResponse response = client.getValue();
        return String.format("{\"value\": %s}", response.getValue());
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerFeignApplication.class, args);
    }
}
```

---

![](https://raw.githubusercontent.com/spring-projects/spring-cloud/gh-pages/img/project-icon-large.png)
# [fit] Fault
# [fit] Tolerance

---

# [fit] Hystrix
![](Common/images/netflix_oss.jpeg)

---

# Circuit Breaker
![inline](Common/images/circuit-breaker.gif)

---

# Consumer with Hystrix

```java
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@RestController
public class ConsumerHystrixApplication {

    @Autowired
    ProducerClient client;

    @RequestMapping("/")
    String consume() {
        ProducerResponse response = client.getValue();
        return String.format("{\"value\": %s}", response.getValue());
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerHystrixApplication.class, args);
    }
}
```

---

# Producer Client

```java
@Component
public class ProducerClient {

    @Autowired
    @LoadBalanced
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getProducerFallback")
    public ProducerResponse getValue() {
        return restTemplate.getForObject("http://producer", ProducerResponse.class);
    }

    private ProducerResponse getProducerFallback() {
        return new ProducerResponse(42);
    }
}
```

---

![](https://raw.githubusercontent.com/spring-projects/spring-cloud/gh-pages/img/project-icon-large.png)
# [fit] Monitoring

---

# [fit] Hystrix
# [fit] Dashboard
![](Common/images/netflix_oss.jpeg)

---

# Hystrix Dashboard

![inline](Common/images/hystrix-dashboard.png)

---

# Hystrix Dashboard

```java
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardApplication.class, args);
    }
}
```

---

# [fit] TO THE
# [fit] LABS!
