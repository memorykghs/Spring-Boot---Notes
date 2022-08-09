# 04 - The IoC Container

## Conatiner Overview 容器概述
`org.springframework.context.ApplicationContext` 介面代表了 Spring IoC 容器，負責實例化、配置和組裝 Beans。透過讀取 configuration metadata 像是 XML、Java Annotations 或是 Java code 來獲得要實例化、配置和組裝哪些 Beans 的資訊。

比較常見實現 `ApplicationContext` 的類別有： 
1. `ClassPathXmlApplicationContext`
  可以讀取指定路徑的 XML 檔案並對裡面的 Bean 實例化。
  ```java
  ApplicationContext context = new ClassPathXmlApplicationContext("services.xml", "daos.xml");
  ```
2. `FileSystemXmlApplicationContext`

但是在大多數的情況下，不需要使用 `ApplicationContext` 來實例化對象。下圖用一個 high-level 的角度顯示 Spring 如何運作。

![](/images/spring-core/4-1.png)

#### Configuration Metadata 配置元數據
在 Spring 3.0 之後，就可以使用 Java Config 來進行配置。可用的方法有：

* XML
* Annotation-based configuration：引入了基於 Annotation 配置的支援
* Java-based configuration：可以使用 Java Code 搭配 Annotation 像是 `@Configuration`、`@Bean`、 `@Import` 和 `@DependsOn` 等等對 Bean 進行設定

#### Use Container 使用容器
