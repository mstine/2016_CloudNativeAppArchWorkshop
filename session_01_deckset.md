slidenumbers: true

# [fit] Delivering
# [fit] Cloud Native
# [fit] Architectures
# [fit] with Spring Cloud and Cloud Foundry

---

![right fit](Common/images/cloud-native-book.jpg)

# [fit] FREE

Compliments of Pivotal

[http://bit.ly/cloud-native-book](http://bit.ly/cloud-native-book)

---

Cloud Native Applications compose simple patterns with predictable performance, scaling, security, and failure characteristics to create solutions to complex problems that can be quickly and flexibly adapted to take advantage of new information.

---

# [fit] Why?

---

# [fit] Speed
# [fit] Safety
# [fit] Scale
# [fit] Ubiquity

---

# [fit] What?

---

# What?

- Twelve Factor Apps ([http://12factor.net](http://12factor.net))
- Microservices
- Self-Service Agile Infrastructure
- API-based Collaboration
- Antifragility

---

![](Common/images/12factor.png)
# [fit] http://12factor.net

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

![](Common/images/spring-boot.png)
# [fit] Spring Boot
# [fit] https://projects.spring.io/spring-boot/
---

# Twelve Factor Principles

- Immutable Code Artifacts with Externalized Configuration
- Disposable Code Processes with Externalized State
- Identical Process Environments with Externalized Operational Capabilities
- “If microservices are the what, then twelve factor apps are the how.”

---

# MICROSERVICES
![](Common/images/unicorn.jpg)

---

![inline fit](Common/images/naming_disaster.png)

---

# Define: Microservice
> Loosely coupled service oriented architecture with bounded contexts...
-- Adrian Cockcroft

---

# Loosely Coupled

If every service has to be updated in concert, it’s not loosely coupled!

---

# Bounded Contexts

If you have to know about surrounding services you don’t have a bounded context.

---

# Bounded Contexts
![inline fit](Common/images/bounded_contexts.png)

---

# Enabling Continuous Delivery
![inline fit](Common/images/cont_deliv.png)

---

# Enabling Continuous Delivery
![inline fit](Common/images/cont_deliv_2.png)

---

# Agile: Iterative Feedback Loops
![inline fit](Common/images/agile.png)

---

# Waterscrumfall
![inline fit](Common/images/waterscrumfall.png)

---

# Monolithic Delivery
![inline fit](Common/images/mono_delivery.png)

---

# Conway's Law

> Any organization that designs a system (defined broadly) will produce a design whose structure is a copy of the organization's communication structure.
-- Melvyn Conway, 1967

---

# The Inverse Conway Manuever
![inline fit](Common/images/inverse_conway.png)

---

![fit](Common/images/CloudFoundry.jpg)

---

# Continuous Delivery
![inline fit](Common/images/bus_cap_teams.png)

---

# Supporting Rapid Change
![inline fit](common/images/operational_arch.png)

---

> Architecture is abstract until it is operationalized.
-- Neal Ford, 2014

---

> Architectures that aren’t operationalized exist only on whiteboards.
-- Matt Stine, 2014

---

# [fit] Architectural
# [fit] Operationalization
![](Common/images/island-house.jpg)

---

# Challenges of Distributed Systems

* Configuration Management
* Service Topology Composition
* Cascading Failures
* Troubleshooting Complex Call Graphs
* API Versioning

---

# Central Configuration Server

![inline fit](Common/images/Config_Server.png)

---

# Cloud Config Management Bus

![inline fit](Common/images/Cloud_Bus.png)

---

# Service Discovery

![inline fit](Common/images/Service_Registry.png)

---

# Client-Side Intelligent Load Balancing

![inline fit](Common/images/Ribbon.png)

---

# Circuit Breaker Pattern
![inline](Common/images/circuit-breaker.gif)

---

![](https://raw.githubusercontent.com/spring-projects/spring-cloud/gh-pages/img/project-icon-large.png)
# [fit] Spring Cloud
# [fit] http://cloud.spring.io

---

![](Common/images/netflix_oss.jpeg)
# [fit] https://netflix.github.io

---

# Complex Call Graphs
![inline](Common/images/call_graph.png)

---

# Distributed Tracing
![inline](Common/images/zipkin.png)

---

# Zipkin
![inline](Common/images/zipkin_site.png)
# http://zipkin.io

---

# [fit] BREAK
# [fit] TIME!
