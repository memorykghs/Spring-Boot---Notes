# 01 - DIP、DI、IoC

![](/images/spring-core/1-1.png)

[圖片來源](https://blog.csdn.net/itguangit/article/details/108420667)

## Design Principle v.s. Design Pattern
Design Principle 和 Design Pattern 是兩個不同的概念。

### Design Principle
Design Principle 提供了一個 high-level guidline，意旨提供設計原則，但不講述實際操作。如何時做可以有很多方式。

平常提到的 SOLID Design Principle 包含了：

* 單一職責原則 Single-Responsibility Principle
* 開放封閉原則 Open-Closed Principle
* 鋰式替換原則 Liskov Substitution Principle
* 接口隔離原則 Interface Segregation Principle
* 依賴反轉原則 Dependency Inversion Principle

### Design Pattern
* 針對 OOP 常遇到或特定的問題提供 low-level solution，代表至少會提供一組解決的可行設計方式

* ex：如果想建立只有一個實例的類別，可以使用單例模式

## DIP
DIP 講的就是上面提到的 Dependency Inversion Principle，是 Rorbert Martin 提出的面相物件的概念之一。

## 控制反轉 IoC
Inverse of Control，實現依賴反轉或鬆耦合的一種最佳的設計方式，主要概念為反轉 OOP 中對各個物件的控制，實現鬆耦合。

* 實作 IoC 的方式不只有 DI
* IoC 的範疇包含 DI，但不僅限於 DI

## DI
Denpendency Injection，依賴注入，用來實現 IoC 的設計模式。DI 架構本身要解決的問題不限於 OO ( 物件導向 )，不過我們比較常碰到的狀況都是基於 OO 的領域。

* DI 是實現 IoC 的手段，不是目標
* 可以達到鬆耦合的架構，提高程式的可維護性

> When you go and get things out of the refrigerator for yourself, you can cause problems. You might leave the door open, you might get something Mommy or Daddy doesn,t want you to have. You might even be looking for something we don,t even have or which has expired.
>
>What you should be doing is stating a need, "I need something to drink with lunch," and then we will make sure you have something when you sit down to eat.

👆 蠻好笑的，放上來。

來源：https://medium.com/wenchin-rolls-around/%E6%B7%BA%E5%85%A5%E6%B7%BA%E5%87%BA-dependency-injection-ea672ba033ca

#### 為什要使用 DI?
比較實際的例子就是不同的插頭。
* 緊耦合：台灣自己的 iPhone 充電插頭，只能接 Type C 的線，也只能用於台灣
* 鬆耦合：萬用插頭，可以連接各過的插座，也可以使用各種不同的線充電 ( 符合 SOLID 的鋰式替換原則 )

#### 實作概念
* 在類別之外建立依賴對象，並透過不同的方式提供給需要的類別使用

## 結論
* DIP 是在設計架構時針對抽象依賴關係規劃一個原則，IoC 則是說明了依賴關係控制的方向，DI 則是實作依賴反轉的一種方法

## 參考
* https://blog.csdn.net/itguangit/article/details/108420667
* https://ithelp.ithome.com.tw/articles/10191553
* https://www.digitalocean.com/community/conceptual_articles/s-o-l-i-d-the-first-five-principles-of-object-oriented-design#single-responsibility-principle