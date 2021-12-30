# Spring-Boot---Notes
- [ ] 正規表達式
- [ ] `@Component` 最終也會被掃起來成為 `@Bean`?
#### CH 5
- [ ] CH5 高內具低耦合?介面宣告?
- [ ] Bean生命週期
- [ ] Postman圖片截圖 & 講義圖片更換
- [ ] 8.3. Query with SQL File 圖片
#### CH 6
- [ ] `@Component` v.s. `@Bean`
   * `@Component` 加在 Class 上
   * `@Bean` 通常搭配 `@Configuration` 使用，標註在方法上
   * 雖然最後都會成為 `@Bean`
- [ ] `@Bean` 一定要跟 `@Configuration` 搭配使用嗎?
- [ ] 如何控制 `@Configuration` 或是 `@ControllerAdvice` 只針對某幾個 Class?
#### CH 7
- [ ] ORM 表現層 v.s. 業務層
- [ ] `hashCode()` &rArr; Set 切入點?
- [ ] 除了 JPA 外什麼時候還會覆寫 `hashCode()` 跟 `equals()` 方法?
- [ ] 如果資料庫沒有 PK，是不是就不能在 field 上加 `@Id`?
#### CH 9
- [ ] ObjectMapper - MapperFeature
- [ ] ObjectMapper - DeserializationFeature
#### CH 10
- [ ] WebExceptionHandler運作以及如何抓到被丟出的Exception
- [ ] package名稱後墜與功能是否跟Annotation有關係

#### 先放著，有空再說
- [ ] Entity 映設
  * https://super-passbook-3e3.notion.site/JPA-Associations-7b46fe01bf04482c9fd4f53865884605
- [ ] @ConfigurationProperties & @EnableConfigurationProperties
- [ ] yml 檢查
```properties
management:
  endpoint:
    metrics:
      enabled: true
    prometheus:
      enabled: true
  endpoints:
    web:
      base-path: /
      exposure:
        include: health, info, env, prometheus, metrics, httptrace, threaddump, heapdump, loggers
  metrics:
    export:
      prometheus:
        enabled: true
```
-[ ] ObjectMapper
   * https://super-passbook-3e3.notion.site/ObjectMapper-2367ffacf7a14beca69ae869ccb13d9a
   * Jackson：https://github.com/FasterXML/jackson-annotations/wiki/Jackson-Annotations
   * @JsonSetter、@JsonGetter
   * @JsonAlias

## Transaction
- [ ] Transactional Propagation
   * https://www.javainuse.com/spring/boot-transaction-propagation
   * https://codertw.com/%E7%A8%8B%E5%BC%8F%E8%AA%9E%E8%A8%80/299872/

- [ ] EntityManager
   * https://openhome.cc/Gossip/EJB3Gossip/FirstContainerManagedJPA.html
   * https://openhome.cc/Gossip/EJB3Gossip/EntityManagerScope.html
   * [SharedEntityManager](https://docs.spring.io/spring-framework/docs/3.2.4.RELEASE_to_4.0.0.RELEASE/Spring%20Framework%203.2.4.RELEASE/org/springframework/orm/jpa/SharedEntityManagerCreator.html)
   
```java
EntityManager em = entityManagerFactory.createEntityManager();
EntityTransaction tx = em.getTransaction();

try {
  tx.begin();
  ...
  tx.commit();
  
} catch (Exception e) {
  if (tx != null) {
    tx.rollback();
  }
}
```
   
- [ ] Persistence Context
   * https://www.baeldung.com/jpa-hibernate-persistence-context

## Store Procedure
* https://iter01.com/471453.html

## 監控 & 其他
```xml
<!-- Spring boot actuator to expose metrics endpoint -->
<dependency>
 <groupId>org.springframework.boot</groupId>
 <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
<!-- Micrometer Monitoring -->
<dependency>
 <groupId>io.micrometer</groupId>
 <artifactId>micrometer-core</artifactId>
</dependency>
<dependency>
 <groupId>io.micrometer</groupId>
 <artifactId>micrometer-registry-prometheus</artifactId>
</dependency>

<!-- https://mvnrepository.com/artifact/org.owasp.antisamy/antisamy -->
<dependency>
 <groupId>org.owasp.antisamy</groupId>
 <artifactId>antisamy</artifactId>
 <version>1.6.3</version>
</dependency>
```

* [JVM應用度量框架Micrometer](https://iter01.com/437347.html)
* [Actuator+Prometheus+Grafana監控視覺化簡介](https://www.tpisoftware.com/tpu/articleDetails/2446)

## Log
* https://segmentfault.com/a/1190000037598528

## 其它
* https://javabeat.net/spring-framework-interview-questions/
