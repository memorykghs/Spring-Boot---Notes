# 狀況五：使用 mySQL 資料欄位大小寫問題
在使用 mySQL 作為資料庫，發生了幾個問題：

第一個是原本的 yml 設定檔將 `ddl-auto` 屬性設為 `update`，所以 Hibernate 會根據 Entity 屬性自動建立 Table，但建立的 Table 名稱及欄位都是小寫，導致查詢出來的結果都是 `null`。

解決方法：
1. 在

2. 


```java
CarResponse.Data responseInnerData = new CarResponse.Data();
CarEntity carEntity = optional.get();

// 一般方法
responseInnerData.setManufacturer(carEntity.getManufacturer());
responseInnerData.setType(carEntity.getType());
responseInnerData.setPrice(carEntity.getPrice());
responseInnerData.setMinPrice(carEntity.getMinPrice());
List<CarResponse.Data> datas = new ArrayList<>();
datas.add(responseInnerData);
```

假設某一個專案有超過10個以上的欄位，這件事情就變得有點浪費時間了，所以接下來我們要用 ObjectMapper 這個物件，幫我們做到 Entity 與 DTO 之間的轉換。

## ObjectMapper
ObjectMapper 是 Jackson 資源庫中的一個類別，主要用來幫助我們完成 JSON 和 Java 的 Object 的互相轉換。

Java的 JSON 資源庫，比較常見的至少應該用過/聽過這三種：Jackson、Gson、Fastjson，而 Jackson 是 Spring 家族的預設 `JSON/XML` 解析器，所以只要 Maven 中有引`spring-boot-starter-web` 的 dependency 就可以直接使用：
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

現在需要調整的是 ServiceImpl 中的內容：
```
|--com.example.demospringboot
   |--DemospringbootApplication.java
|--com.example.demospringboot.configuration
   |--SwaggerConfig.java
   |--RestConfiguration.java
|--com.example.demospringboot.controller
   |--CarController.java
   |--TestController.java
   |--ProductController.java
|--com.example.demospringboot.dto
   |--CarRequest.java 
   |--CarResponse.java
|--com.example.demospringboot.entity
   |--CarEntity.java
   |--CarPKEntity.java
|--com.example.demospringboot.model
   |--Product.java
|--com.example.demospringboot.repository
   |--CarRepository.java
|--com.example.demospringboot.service
   |--CarService.java
   |--ProductService.java
|--com.example.demospringboot.service.impl
   |--CarServiceImpl.java // 修改的檔案
   |--ProductServiceImpl.java
```

我們需要先建立一個 `ObjectMapper` 物件，並使用 `convertValue()` 方法進行轉換。看到下面那張圖，`convertValue()` 有三個多載，分別傳入兩個參數：第一個傳入的 `fromValue` 代表來源物件，第二個 `toValueType` 則是代表要轉換成的目標類別。第一個與第二個方法的第二個傳入參數 `toValueType` 差別在於第一個方法可以傳入自定義的物件，例如我們自己定義的 DTO `CarResponse`；第二個方法則是只能傳入原本 Java 中就有的物件類別，如：List、Map等。

![ ](/images/objectMapper-1.png)

完整的 ServiceeImpl 內容如下：

```java
@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;

    @Override
    public List<CarEntity> queryAllCar() {
        return carRepository.findAll();
    }

    @Override
    public CarResponse queryCar(CarRequest carRequest) {
        String manufacturer = carRequest.getManufacturer();
        String type = carRequest.getType();
        List<CarEntity> list = carRepository.findByManufacturerAndType(manufacturer, type);

        CarEntity carEntity = list.get(0);
        CarResponse carResponse = new CarResponse();

        CarResponse.Data responseInnerData = new CarResponse.Data();

	// ObjectMapper：Java Code 轉 json
	ObjectMapper objectMapper = new ObjectMapper();
	objectMapper.convertValue(carEntity, CarResponse.Data.class);

        List<CarResponse.Data> datas = new ArrayList<>();
        datas.add(responseInnerData);
        
        carResponse.setDatas(datas);
        carResponse.setMessage("success");

        return carResponse;
    }
}		
```
<br/>

## 參考
https://blog.csdn.net/qq_36666651/article/details/80719259
https://www.itread01.com/content/1546632025.html
https://programmer.group/full-capitalization-full-lowercase-hump-hungary.html
