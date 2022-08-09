# 02 - SOLID
SOLID Design Principle 包含：

* 單一職責原則 Single-Responsibility Principle
* 開放封閉原則 Open-Closed Principle
* 鋰式替換原則 Liskov Substitution Principle
* 接口隔離原則 Interface Segregation Principle
* 依賴反轉原則 Dependency Inversion Principle

## 單一職責原則 Single-Responsibility Principle
> A class should have one and only one reason to change, meaning that a class should have only one job.

  * 比起一個類別應該只負責一件事，用 _**一個模組應且只有一個理由會使其改變**_ 來形容比較準確

###### 範例
假設現在需要計算形狀的面積，有兩個基本形狀：Circle、
Square。

```java
public class Square {
	
	public Square(int length) {
		this.length = length;
	}

	private int length;

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
}
```
```java
public class Circle {
	
	public Circle(int radius) {
		this.radius = radius;
	}
	
	private static final float pi = 3.14f;
	
	private int radius;

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public static float getPi() {
		return pi;
	}
}
```

然後還有一計算面積的類別 `AreaCalculate`。
```java
public class AreaCalculate {

	public float areaCalculate(Object shape) {
		
		if(shape instanceof Square) {
			return square((Square)shape);
			
		} else if(shape instanceof Circle) {
			return circle((Circle)shape);
			
		}else {
			return 0;
		}
	}
	
	private float square(Square square) {
		int length = square.getLength();
		return length * length;
	}
	
	private float circle(Circle circle) {
		return circle.getRadius() * Circle.getPi();
	}
}
```

當今天想要針對計算出來的物件以不同的包裝方式回傳，那個這個功能不應該被寫在 `AreaCalculate` 中，而是應該由另外一個類別作處理。

```java
public class SingleResponsibilityPrinciple {

	public static void main(String[] args) {

		AreaCalculate areaCalculate = new AreaCalculate();
		areaCalculate.areaCalculate(new Square(5));
	}

	public List<Float> getList(float data) {

		List<Float> list = new ArrayList<>();
		list.add(data);
		
		return list;
	}
	
	public Map<String, Float> getMap(float data) {

		Map<String, Float> map = new HashMap<>();
		map.put("area", data);
		
		return map;
	}
}
```

不過如果是新增了一個新的形狀像是 Triangle，要在 `AreaCalculate` 新增一個判斷並進行計算，這個則是被允許的，因為這個類別致使至終都是用來計算面積。

## 開放封閉原則 Open-Closed Principle
> Objects or entities should be open for extension but closed for modification.

* 一個物件或是實體應該要可以藉由增加新的程式碼來擴充功能，而不是藉由修改原本已經存在的程式碼來擴充

## 鋰式替換原則 Liskov Substitution Principle
> Functions that use pointers or references to base classes must be able to use objects of derived classes without knowing it.

看上面這句可能會看不懂到底在說什麼。

> Let q(x) be a property provable about objects of x of type T. Then q(y) should be provable for objects y of type S where S is a subtype of T.

簡單來說，當實作繼承了 interface 或 base-class的 sub-class，那麼在程式中，只要出現該 interface 或 base-class 的部份，都可以用 sub-class 替換。

## 接口隔離原則 Interface Segregation Principle
> A client should never be forced to implement an interface that it doesn’t use, or clients shouldn’t be forced to depend on methods they do not use.

針對不同需求的用戶，開放其對應需求的介面，提拱使用。可避免不相關的需求介面異動，造成被強迫一同面對異動的情況。

## 依賴反轉原則 Dependency Inversion Principle
> Entities must depend on abstractions, not on concretions. It states that the high-level module must not depend on the low-level module, but they should depend on abstractions.

當 A 模組在內部使用 B 模組的情況下，我們稱 A 為高階模組，B 為低階模組。高階模組不應該依賴於低階模組，兩者都該依賴抽象介面。