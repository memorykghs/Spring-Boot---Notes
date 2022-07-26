# 02 - Actuator

教學資源：
* https://www.baeldung.com/spring-boot-actuators

## Emdpoints
Actuator Endpoints 可以讓使用者監控並與 Application 進行互動。Spring Boot 提供一些內建的 endpoints 供使用，例如 `health` endpoint 就提供了 Application 的健康資訊。

每一個獨立的 endpoint 都可以被 `enabled` 和 `disabled`，而是否啟用取決於 endpoint 的 bean 有沒有存在於 Application Context。如果想要訪問遠端 ( remote ) 的端點，必須通過**JMX**或是**HTTP**。

大部分的 Application 會選擇使用 HTTP，如上所述，如果想要訪問某個 Application 的端點，必須在 URL 加上 `/actuator` 的前綴。例如，要取得 `health` 端點，其對應的 URL 是 `/actuator/health`。

## 與技術無關的端點
| ID | Description | Enabled by default |
| --- | --- | --- |
| `auditevents` | Exposes audit events information for the current application. | Yes |
| `beans` | Displays a complete list of all the Spring beans in your application. | Yes |
| `caches` | Exposes available caches. | Yes |
| `conditions` | Shows the conditions that were evaluated on configuration and auto-configuration classes and the reasons why they did or did not match. | Yes |
| `configprops` | Displays a collated list of all @ConfigurationProperties. | Yes |
| `env` | Exposes properties from Spring’s ConfigurableEnvironment. | Yes |
| `flyway` | Shows any Flyway database migrations that have been applied. | Yes |
| `health` | Shows application health information. | Yes |
| `info` | Displays arbitrary application info. | Yes |
| `integrationgraph` | Shows the Spring Integration graph. | Yes |
| `loggers` | Shows and modifies the configuration of loggers in the application. | Yes |
| `liquibase` | Shows any Liquibase database migrations that have been applied. | Yes |
| `metrics` | Shows 'metrics' information for the current application. | Yes |
| `mappings` | Displays a collated list of all `@RequestMapping` paths. | Yes |
| `scheduledtasks` | Displays the scheduled tasks in your application. | Yes |
| `sessions` | Allows retrieval and deletion of user sessions from a Spring Session-backed session store. Not available when using Spring Session's support for reactive web applications. | Yes |
| `shutdown` | Lets the application be gracefully shutdown. | No |
| `threaddump` | Performs a thread dump. | Yes |

## Web Application 額外可用的端點
如果是 Web Application 如 Spring MVC、Spring WebFlux 或 Jersey，就可以使用以下這些額外的 endpoints。

| ID | Description | Enabled by default |
| --- | --- | --- |
| `heapdump` | Returns an hprof heap dump file. | Yes |
| `jolokia` | Exposes JMX beans over HTTP (when Jolokia is on the classpath, not available for WebFlux). | Yes |
| `logfile` | Returns the contents of the logfile (if logging.file or logging.path properties have been set). Supports the use of the HTTP Range header to retrieve part of the log file's content. | Yes |
| `prometheus` | Exposes metrics in a format that can be scraped by a Prometheus server. | Yes |

Actuator Endpoints 更多資訊可以參考 [HTML](https://docs.spring.io/spring-boot/docs/2.1.7.RELEASE/actuator-api/html/) 或 [PDF](https://docs.spring.io/spring-boot/docs/2.1.7.RELEASE/actuator-api//pdf/spring-boot-actuator-web-api.pdf) 文件。