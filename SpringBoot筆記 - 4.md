# SpringBoot - 4
## 4. 可以讓Spring Boot不要是Singleton嗎?
因為一開始的時候 SpringBoot 就會實例化物件，所以並不會去改變已經實例後的
可使用`@RequestScope`設定不要 Singleton。
<br/>

## 5. 呈4.，如果有兩個 Class 實作 `ProductServiceImpl.java` 會有問題嗎?
會，因為SpringBoot無法辨識到底要使用哪一個ServiceImpl。
使用`@primaray`（指定以哪一個為主）、`@qualify`可以指定使用哪一個ServiceImpl。
<br/>

> 解決辦法

使用 `@Qualifier` 或 `@Primaray`：
#### 方法一： `@Primrary`
```java
@Primrary
@Component
public class MagicianServiceImpl {
     // do sth here...
}
```
<br/>

#### 方法二： `@Qualifier`
```java
@Service
@Qualifier("magician1")
public class MagicianServiceImpl {
   // do sth here...
}
```
```java
public class MagicianController {
   @Autowired
   @Qualifier("magician1")
   private MagicianService magicianService1; // 變數名稱可跟 @Qualifier 別名不同
}
```
<br/>

#### 方法三： `@Qualifier` & `@Component`
```java
@Service("magician1")
@Qualifier
public class MagicianServiceImpl {
   // do sth here...
}
```
```java
public class MagicianController {
   @Autowired
   private MagicianService magician1; // 變數名稱需要跟宣告的 @Component 別名相同
}
```
<br/>

###### 4-1
控制 Spring Boot 是否為 Singleton：5種方法
<br/>

###### 4-2 Spring Boot 物件生命週期
[生命週期](https://www.itread01.com/content/1545438969.html)

## 參考
https://matthung0807.blogspot.com/2019/12/spring-autowired-with-qualifier-inject.html 
https://www.baeldung.com/spring-qualifier-annotation 