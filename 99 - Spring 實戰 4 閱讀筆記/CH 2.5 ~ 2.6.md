# CH 2.5 導入和混合配置
在 Spring 中可以將 JavaConfig 的組件操掃描與自動裝配，或是 XML 裝配混合使用。Spring 在自動裝配時並不在意要裝配的 Bean 用什麼方法設置，而是如何引用不同配置的 Bean。

## 在 JavaConfig 中引用 XML 配置
假設我們不想要在同一個 JavaConfig 檔案中同時出現兩個 Bean 方法，我們就將 BlankDisc 拆到他自己的 JavaConfig 當中。
```java
package soundsystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CDPlayerConfig {
  
    @Bean
    public CompactDisc compactDisc() {
        return new SgtPeppers();
    }
}
```

`compactDisc()` 方法已經從 CDPlayerConfig 移除掉了，但我們需要有一種方式將這兩個類別組合再一起，其中一個辦法就是在 CDPlayerConfig 中使用 `@Import` 註解導入 CDConfig：
```java
package soundsystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CDConfig.class)
public class CDPlayerConfig {

    @Bean
    public CDPlayer cdPlayer(CompactDisc compactDisc) {
        return new CDPlayer(compactDisc);
    }
}
```

另一個更好的方法，就是建立另一個更高級別的 SoundSystemConfig 類別，並在這個類別終將兩個配置類別組合在一起：
```java
package soundsystem;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CDPlayerConfig.class, CDConfig.class})
public class CDPlayerConfig {
}
```

假設現在基於某接原因，希望通過 XML 來設置 BlankDisc：
```xml
<bean id="compactDisc" class="soundsystem.BlankDisc"
  c:_0="Sgt. Pepper's Lonely Hearts Club Band"
  c:_1="The Beatles" >
    <constructor-arg>
        <list>
            <value>Sgt. Pepper's Lonely Hearts Club Band</value>
            <value>With a Little Help from My Friends</value>
            <value>Lucy in the Sky with Diamonds</value>
            <value>Getting Better</value>
            <value>Fixing a Hole</value>
            <!-- ...other tracks omitted for brevity... -->
        </list>
    </constructor-arg>
</bean>
```

要如何讓 Spring 同時加載 BlankDisc 類別和其他基於 Java 的配置呢?

答案是 `@ImportResource` 註解，如果 BlankDisc 定義在名為 cdconfig.xml 的文件當中，該文件位於跟目錄路徑下，那麼可以修改 SoundSystemConfig，使用 `@ImportResource` 
```java
package soundsystem;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@Import(CDPlayerConfig.class)
@ImportResource("classpath:")
public class SoundSystemConfig {

}
```

#### 小結
* 可以透過 `@Import` 以及 `@ImportResource` 來拆分 JavaConfig 類別。
* `@Import(Java.class)`：載入特定的配置檔 ( 有 `@Configuration` 標註的檔案 )。
* `@ImportResource("classpath:")`：載入指定目錄下的所有 XML 設定的 Bean。

## 在 XML 配置中引用 JavaConfig
反過來，如果希望將 BlankDisc Bean 拆分 ( BlankDisc 是用 XML 配置 )，並在 XML 文件中 ( cd-config.xml ) 配置，可以使用 `<import>` 元素來引用該文件：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:c="http://www.springframework.org/schema/c"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans.xsd">
  
    <bean class="soundsystem.CDConfig" />
  
    <import resource="cdplayer-config.xml" />
</beans>
```

如果要配置一個 JavaConfig 類型的 Bean，要如何在 XML 中引用呢?事實上，`<import>` 元素無法在 XML 中導入 JavaConfig 類別。但 `<bean>` 元素可以，我們可以這樣在 XML 中聲明 Bean：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:c="http://www.springframework.org/schema/c"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans.xsd">
  
    <bean class="soundsystem.CDConfig" />
  
    <bean id="cdPlayer" class="soundsystem.CDPlayer"
        c:cd-ref="compactDisc" />
</beans>
```

现在，我们假设不再将 BlankDisc 配置在 XML 之中，而是将其配置在 JavaConfig 中，CDPlayer 则继续配置在 XML 中。基于 XML 的配置该如何引用一个 JavaConfig 类呢？
事实上，答案并不那么直观。<import> 元素只能导入其他的 XML 配置文件，并没有 XML 元素能够导入 JavaConfig 类。

## CH 2.6 結論
* Spring 框架的核心是 Spring 容器，他負責管理應用中組件的生命週期，建立這些類別的實力並保證他們的依賴能夠得到滿足。
* 在 Spring 主要有3種方式裝配 Bean：
   1. 自動化配置
   2. 基於 Java 的顯式配置
   3. 基於 XML 的顯式配置

* 建議盡可能使用自動化配置，可以避免使用顯式配置的維護成本，且自動化配置的功能更加強大，並且易於重構。

* 好的 Bean 裝配技術能更充分的發揮 Spring 容器的威力。

## 參考
Spring 實戰 4