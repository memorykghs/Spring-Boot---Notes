# 01 - Spring Integration
* [Spring Integration](https://docs.spring.io/spring-framework/docs/current/reference/html/integration.html#spring-integration)

## Why Integration?
##### 什麼是 Endpoints?
> Endpoints are a more specific or peculiar version of a Controller.

Endpoints 是一個比較獨特或特別的 Controller 版本。

> Rather than rely on a view (such as JSP) to render model data in HTML, an endpoint simply returns the data to be written directly to the body of the response(Similar to doing @ResponseBody in Controller).

比起將依賴 view ( 像是 JSP ) 將資料渲染到 HTML 上，Endpoints 直接將資料寫進 Response 的 Body 中 ( 比較像是在 Controller 中使用 `@ResponseBody` )。

> Actuator Endpoint is a better option because of the following reasons :
> 1. Endpoints are meant to perform the highly specific task of printing your Object(Json) on HTTP which is exactly what you want to do here.
> 2. To separate monitor-and-manage code from your application-specific code.
> 3. To keep things cleaner and cohesive

Actuator Endpoints 是一個比較好的選擇，因為：
1. Endpoints 旨在執行一些較高層次的任務像是將 Object ( JSON ) 在 HTTP 上印出 ( 感覺比較像是要表達站在 high-level 的角度俯瞰整個應用程式的狀況 )
2. 分離監控 ( monitor ) 與控制 ( manage ) 的程式碼
3. 維持程式的整潔與聚焦

💡 monitor 的意思是使用者去察看某些東西，沒有影響的權限；而控制 ( manage ) 則代表說可以實際對某些東西進行調整，並影響他們產出的結果。
 
參考：https://stackoverflow.com/questions/36008076/difference-between-spring-controller-and-endpoint

## 大綱
* Endpoints
    * REST Endpoints
        * RestTemplate
    * [Actuator](https://spring.io/guides/gs/actuator-service/)
    * prometheus
* JMS
* JMX  
* Email
* Task Execution and Scheduling
* Cache Abstraction

## 其他
* [Endpoints](https://docs.spring.io/spring-boot/docs/2.1.7.RELEASE/reference/html/production-ready-endpoints.html)
* [Get All Endpoints in Spring Boot](https://www.baeldung.com/spring-boot-get-all-endpoints)