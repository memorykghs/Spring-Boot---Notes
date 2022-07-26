# CH 2.4 通過 XML 裝配 Bean
## 建立 XML 配置規範
這個章節只會大概帶過如果不使用 JavaConfig 與 `@Configuration` ，而是使用 XML 要怎麼設定。以下是一個最簡單的 Spring XML 配置範例。
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://www.springframework.org/schema/beans 
  http://www.springframework.org/schema/beans/spring-beans.xsd
  http://www.springframework.org/schema/context" >
  
  <!-- configuration details go here />

</beans>
```

使用 XML 配置會比使用 JavaConfig 複雜許多。我們在使用 JavaConfig 只需要添加 `@Configuration` 註解就可以了，但在使用 XML 時，需要在檔案頂部宣告 XML 模式 ( XSD ) 文件，這些文件定義了配置 Spring 的 XML 元素。但由於還沒有聲明 Bean，這個 XML 配置文件是沒有作用的。

> 借助 Spring Tool Suite 建立 XML 文件和管理 Spring XML 檔案室一種簡便的方式。在 Spring Tool Suite 選單中，選擇 File &rarr; New &rarr; Spring Bean Configuration File，就可以新增 Spring XML 文件。

## 聲明一個簡單的 Bean
要在 XML 中聲明配置 Bean，就需要使用 `<bean>` 這個 tag，功能類似於 JavaConfig 中的 `@Bean` 註解。<bean> 元素类似于 JavaConfig 中的 @Bean 注解。
```xml
<<bean id="compactDisc" class="soundsystem.SgtPeppers" />
```

* `class`：用來指定是哪一個 Class。
* `id`：可以為 Bean 實例命名。如果沒有設定 `id` 屬性的話，會根據全限定類別名稱來命名，如 soundsystem.SgtPeppers#0，如果出現了第二個 CompactDisc 實例，那麼別名就會是 soundsystem.SgtPeppers#1。

> 通常只需要對那些需要按名字注入的 Bean 命名即可，減少繁瑣的 XML 設定。

到目前為止，使用 XML 來聲明 Bean 有幾個特徵：
1. 不會直接去 new 出 SgtPeppers 實例，如果是使用 JavaConfig 來聲明，就必須在程式中返回 new 出的物件。
<br>

2. 雖然 XML 配置較為被動 ( 不用 new 出物件 )，但使用 JavaConfig 可以用更加靈活的方式來建立 Bean ( 如使用 switch case 來判斷建立哪個實例 )。
<br>

3. 在 XML 聲明中，我們將 Bean 的類別以字串的形式設置在 `class` 屬性中，所以無法保證設定給 `class` 這個屬性的字串是指向一個真的存在的類別。因為 XML 設置不能在編譯時期檢查語法，當使用兩個一樣的 `id` 屬性來設定 ID，runtime 時期會報錯誤。

## 借助構造器注入並初始化 Bean
在 Spring XML 中聲明 Bean 只有一種方式，就是使用 `<bean>` 元素並指定 `class` 屬性。不過將 Bean 注入到構造器的方法有下面兩種：

* `<constructor-arg>` 元素
* 使用 Spring 3.0 所引入的 `c-` 命名空間

而兩者的區別在於：
1. 雖然 `<constructor-arg>` 元素在使用上較 `c-` 更為冗長及繁瑣
2. 但有些事情用 `c-` 元素無法做到

#### 構造器注入 Bean 引用
在 CH 2.3 章倒數第二個例子，我們用這種方式在 JavaConfig 中注入需要的 Bean。
```java
@Bean
public CDPlayer cdPlayer(CompactDisc compactDisc) {
    return new CDPlayer(compactDisc);
}
```

如果使用 `<constructor-arg>` 來住入，就要透過 Bean ID 來引用。
```xml
<bean id="cdPlayer" class="soundsystem.CDPlayer">
  <constructor-arg ref="compactDisc">
</bean>
```

若選擇 `c-` 元素的模式，要先在 XML 中聲明 `c-` 命名空間，才能使用。
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
  xmlns:c="http://www.springframework.org/schema/c"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://www.springframework.org/schema/beans 
  http://www.springframework.org/schema/beans/spring-beans.xsd" >
  
  ...
  
</beans>
```
```xml
<bean id="cdPlayer" class="soundsystem.CDPlayer" c:cd-ref="compactDisc" />
```

屬性名稱以 `c:` 開頭，也就是命名空間的前墜。`-ref` 是用來告訴 Spring 正在裝配的是一ˋ個 Bean，等號後面接的是 Bean 的 ID `compactDisc`。

![](/images/2.4-1.png)

更詳細內容可以參考 [Spring 實戰4](https://potoyang.gitbook.io/spring-in-action-v4/di-2-zhang-zhuang-pei-bean/2.4-tong-guo-xml-zhuang-pei-bean/2.4.3-jie-zhu-gou-zao-qi-zhu-ru-chu-shi-hua-bean)。

## 參考
Spring 實戰 4