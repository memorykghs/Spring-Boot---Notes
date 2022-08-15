# 01 - Spring AOP

## Aspect Oriented Programming with Spring
Aspect-oriented Programming ( AOP ) 用另外一種思考方向來完善 Object-oriented Programming ( OOP ) 的程式架構。在 OOP 中，模組化的單元是 class ( 類別 )，但是在 AOP 中則是 aspect ( 切面 )。

Aspect 支援讓模組化的關注點 ( concern ) 跨多種類別與物件，這種關注點通常被稱為 "crosscutting" ( 橫切 )。

根據 API 的分類，以下這些都屬於 Spring AOP：

![](/images/spring-aop/1-1.png)

* [Spring API document](https://docs.spring.io/spring-framework/docs/current/javadoc-api/overview-summary.html)

* [Spring 官方說明文件](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#aop-api)

## AspectJ
* 參考：https://openhome.cc/Gossip/Spring/AspectJ.html

基於 Java 動態代理，在執行時其動態產生目標物件的子類別作為代理類別。也就是在執行時期將關切點切入主要流程。

AspectJ 就是支援編譯時期切入的方案。
* 針對 Java 做了一些擴充，透過 `aspect`、`before` 等語法來定義主要流程的關注點
* Spring AOP 也受其觀念和設計影響
* Spring 從 2.0 開始支援 AspectJ