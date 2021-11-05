# Spring-Boot---Notes
- [ ] 正規表達式
- [ ] `@Component` 最終也會被掃起來成為 `@Bean`?
#### CH 5
- [ ] CH5 高內具低耦合?介面宣告?
- [ ] Bean生命週期
- [ ] Postman圖片截圖 & 講義圖片更換
- [ ] 8.3. Query with SQL File 圖片
#### CH 9
- [ ] ObjectMapper - MapperFeature
- [ ] ObjectMapper - DeserializationFeature
#### CH 10
- [ ] WebExceptionHandler運作以及如何抓到被丟出的Exception
- [ ] package名稱後墜與功能是否跟Annotation有關係

#### 先放著，有空再說
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

## 其它
* https://javabeat.net/spring-framework-interview-questions/
