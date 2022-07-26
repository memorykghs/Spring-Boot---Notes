# CH 3 ~ 3.1
## CH 3.2 條件化的 Bean
如果想要在 Spring 內做到某個 Bean 在另外一個 Bean 也聲明的情況下才創建、或是設定某個特別的環境變數時，才建立特定的 Bean，在 Spring 4 之前很難實現。

但 Spring 4 引入了一個新的 `@Conditional` 註解，可以放在帶有 `@Bean` 註解的方法上。如果滿足給定的條件 ( true )，就會創建這個 Bean，否則被忽略。

> Q：方法上沒有 `@Bean` 註解也可以使用嗎?( 一定是無法達成目的，但編譯器會不會報錯? )

以下的例子是有一個名為 MagicBean 的類別，如果我們希望只有設置 magic 環境屬性的時候，Spring 才實力化這個類別，就可以使用 `@Conditional` 註解條件化配置 MagicBean：
```java
@Bean
@Conditioal(MagicExistsCondition.class)
public MagicBean magicBean() {
    return new MagicBean();
}
```
`@Conditional` 標註將會通過 Condition 介面提供的方法進行對比：
```java
public interface Condition {
    boolean matches(ConditionContext ctxt, AnnotatedTypeMetadata metadata);
}
```

所以設定給 `@Conditional` 註解後面的類別必須是任意實作 Condition 介面的類別。實現 Condition 界面的類別需要提供 `matches()` 方法，並回傳一個 boolean 值。

所以在這個例子中，需要建立一個實作 Condition 介面的類別 MagicExistsCondition 來判斷條件是否達成：
```java
package com.habuma.restfun;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MagicExistsCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment env = context.getEnvironment();
        return env.containsProperty("magic");
    }
}
```

在 MagicExistsCondition 中傳入了 ConditionContext 物件與 AnnotatedTypeMetadata 兩個物件。使用 ConditionContext 物件的 `getEnvironment()` 方法取得 Environment物件。ConditionContext 是一個介面，如下所示：
```java
public interface ConditionContext {
    BeandefinitionRegistry getRegistry();
    ConfigurationListableBeanFactory getBeanFactory();
    Environment getEnvironment();
    ResourceLoader getResourceLoader();
    ClassLoader getClassLoader();
}
```

我們可以借助這個介面的實例做到幾件事：
* `getRegistry()`：回傳 BeanDefinitionRegistry 物件，用來檢查 Bean 定義。

* `getBeanFactory()`：回傳 ConfigurableListableBeanFactory 物件，可檢查 Bean 是否存在，及檢視 Bean 的屬性。

* `getEnvironment()`：回傳 Environment 物件，用於檢查環境變量及其參數值。

* `getResourceLoader()`：回傳 ResourceLoader 加載的資源。

* `getClassLoader()`：回傳 ClassLoader 加載資源並檢查類別是否存在。

AnnotatedTypeMetadata 也是一個介面，其中`isAnnotated()` 方法能夠讓我們檢查帶有 `@Bean` 註解的方法上還有什麼其他的註解；其他的方法也可以用來檢查 `@Bean` 註解的方法上其它註解的屬性。
```java
public interface AnnotatedTypeMetadata {
    boolean isAnnotated(String annotationType);
    Map<String, Object> getAnnotationAttributes(String annotationType);
    Map<String, Object> getAnnotationAttributes(String annotationType, boolean classValuesAsString);
    MultiValueMap<String, Object> getAllAnnotationAttributes(String annotationType);
    MultiValueMap<String, Object> getAllAnnotationAttributes(String annotationType, boolean classValuesAsString);
}
```

從 Spring 4 開始，`@Profile` 進行了重構，讓它可以基於 `@Conditional` 和 `Condition` 類別被實現。
```java
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional({ProfileCondition.class})
public @interface Profile {
    String[] value();
}
```