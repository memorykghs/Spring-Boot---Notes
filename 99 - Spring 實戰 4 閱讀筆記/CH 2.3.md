# CH 2.3
## 透過 Java 代碼裝備 Bean
在某些情況下是無法透過自動裝配來配置 Spring 內的依賴，例如在專案中使用第三方套件時就無法使用自動裝配，因為無法在要用到的組件上加上 `@Component` 或是 `@Autowired`。這時候就就必須進行顯示配置：在 Java code ( JavaConfig ) 中配置或是 XML。

要在 JavaConfig 中配置，就是在 Class 上添加 `@Configuration` 註解，表明這是一個用來配置的類別，此類別應包含在 Spring 執行環境中如何建立 Bean的細節。
```java
package soundsystem;

import org.spingframework.context.annotation.Configuration;

@Configuration
public class CDPlayerConfig {
}
```
上面的例子單純就是告訴 Spring 這是一個配置類別，還沒填寫實際創建 Bean 的細節。由於 `@Configuration` 只負聲明這是一個配置類別，沒有組件掃描的功能，在沒有 `@ComponentScan` 註解的情況下，測試會失敗並拋出 BeanCreation-Exception。

## 聲明簡單的 Bean
接下來就要 JavaConfig 類別中聲明 Bean，也就是添加建立 Bean 實例的細節。

我們需要建立一個方法，方法內的內容需要**回傳目標類別實例**，然後在這個方法上面加上 `@Bean` 註解。`@Bean` 註解會告訴 Spring 這個方法將會回傳一個類別實例，且要將該實例物件註冊為 Spring 執行環境中的 Bean。
```java
@Bean
public CompactDisc sgtPeppers() {
    return new SgtPeppers();
}
```

前面有提到我們可以為 `@Component` 實例物件命名，在默認情況下是將類別名稱首字改為小姐當作是實例物件的 ID。`@Bean` 的運作邏輯也是一樣的，所以如果想要為實例指定不同的 ID 的話，可以通過 `name` 屬性來命名。
```java
@Bean(name="lonelyHeartsClubBand")
public CompactDisc sgtPeppers() {
    return new SgtPeppers();
}
```

`@Bean` 的功能其實很強大，只要該方法可以回傳一個實例物件，那麼我們也可以根據不同情況回傳不同的實例物件。
```java
@Bean
public CompactDisc randomBeatlesCD() {
    int choice = (int) Math.floor(Math.random() * 4);
    if (choice == 0) {
        return new SgtPeppers();
    } else if (choice == 1) {
        return new WhiteAlbum();
    } else if (choice == 2) {
        return new HardDaysNight();
    } else {
        return new Revolver();
    }
}
```

## 借助 Config 實現注入
到剛剛為止，我們分別說明了使用 `@Configuration` 設定配置類別，以及透過 `@Bean` 註解告訴 Spring 此方法回傳的實例要被注入 Spring 容器內。

前面的例子都是單純的對 Spring 注入一個新的 Bean，程式本身並沒有其他 Bean 的依賴。但如果注入 Bean 的過程，依賴於另外一個物件實例的話要怎麼裝配呢?
```java
@Bean
public CompactDisc sgtPeppers() {
    return new SgtPeppers();
}
```
```java
@Bean
public CDPlayer cdPlayer() {
    return new CDPlayer(sgtPeppers());
}
```
最簡單的方式就是呼叫創建 Bean 的方法，`sgtPeppers()` 也使用 `@Bean` 註解來注入 CDPlayer 需要的 Bean。

但 `cdPlayer()` 與 `sgtPeppers()` 兩個方法稍微有些區別，看起來在建立 CDPlayer 實例的時候調用了 `sgtPeppers()`，試想如果有兩個方法都在建立實例時調用了 `sgtPeppers()` 方法，Spring 會產生2個 CompactDisc 實例嗎?
```java
@Bean
public CDPlayer cdPlayer() {
    return new CDPlayer(sgtPeppers());
}

@Bean
public CDPlayer anotherCDPlayer() {
    return new CDPlayer(sgtPeppers());
}
```

其實並不會，這兩個方法調用的是同一個 CompactDisc 實例，因為 `sgtPeppers()` 方法上有添加 `@Bean` 注解， Spring 會攔截所有對他的調用，並確保直接回傳該方法建立的 Bean，而不是每次都對他進行實際的調用。

所以實際上上面那段程式碼做的事情，就像是這樣：
```java
@Bean
public CDPlayer cdPlayer(CompactDisc compactDisc) {
    return new CDPlayer(compactDisc);
}
```

也就是當調用 `cdPlayer()` 的時候，Spring 會自動去幫我們尋找符合依賴的 Bean 並注入到 `cdPlayer()` 方法中，而不需要調用到宣告為 `@Bean` 的方法 ( 如上面例子的 `sgtPeppers()` 方法 )。這是一種 Spring DI 的使用方式，我們也可以用不同的風格的 DI 配置，如透過 setter 注入。
```java
@Bean
public CDPlayer cdPlayer(CompactDisc compactDisc) {
    CDPlayer cdPlayer = new CDPlayer(compactDisc);
    cdPlayer.setCompactDisc(compactDisc);
    return cdPlayer;
}
```

## 參考
Spring 實戰 4