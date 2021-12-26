## Q. 可以讓Spring Boot不要是Singleton嗎?
因為一開始的時候 SpringBoot 就會實例化物件，所以並不會去改變已經實例後的 可使用 `@RequestScope` 設定不要 Singleton。

###### 那麼，如果有兩個 Class 實作 ProductServiceImpl.java 會有問題嗎?
答案是會，因為SpringBoot無法辨識到底要使用哪一個 ServiceImpl。如果要指定要用哪一個實例，可以用 `@Primaray`、`@Qualify` 等 Annotation 來指定使用哪一個 ServiceImpl。
<br/>

## 解決辦法

#### 方法一： `@Primrary`
在 ServiceImpl Class 加上 `@Primrary` Annotation，之後 `@Autowired` 取得的實例物件就會是這個 Class。
```java
@Primrary
@Component
public class MagicianServiceImpl {
     // do sth here...
}
```

#### 方法二： `@Qualifier`
先在 `ServiceImpl` Class 加上 `@Qualifier` 以及別名。
```java
@Service
@Qualifier("magician1")
public class MagicianServiceImpl {
   // do sth here...
}
```

在 Controller 引用的時候加入 `@Qualifier` 並指定此用哪一個 ServiceImpl。使用這個方法時，變數名稱不需要跟 `@Qualifier` 中的別名相同。

```java
@RestController
public class MagicianController {
   @Autowired
   @Qualifier("magician1")
   private MagicianService magicianService1; // 變數名稱可跟 @Qualifier 別名不同
}
```

#### 方法三： `@Qualifier` & `@Component`
在任何一個有 `@Component` 的 Annotation 上加入別名，在 Controller 取出來的時候，宣告的別名需要跟參數名稱相同
```java
@Service("magician1")
@Qualifier
public class MagicianServiceImpl {
   // do sth here...
}
```
```java
@RestController
public class MagicianController {
   @Autowired
   private MagicianService magician1; // 變數名稱需要跟宣告的 @Component 別名相同
}
```
<br/>

## 參考
https://matthung0807.blogspot.com/2019/12/spring-autowired-with-qualifier-inject.html https://www.baeldung.com/spring-qualifier-annotation
