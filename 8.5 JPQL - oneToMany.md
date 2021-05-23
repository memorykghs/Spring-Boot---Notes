# 8.5. ( 補充 ) @oneToMany

JPQL ( Java Persistence Query Language ) 是

* 不會直接對DB進行操作

接下來會進行操做的是 EMP 及 ORDER_INFO 兩張 Table，紀錄員工與訂單之間的關，一個員工可以有多筆訂單。

## @OneToMany
在一對多單向關係中，多的一方 ( ORDER_INFO ) 沒有注解，一的一方 ( EMP ) 有注解，如果一的一方不加 `@JoinColumn` 指定外鍵欄位的話，Hibernate會自動生成一張中間表 EMP_ORDER_INFO 來對 EMP 和 ORDER_INFO 進行綁定。

現在有一個表 ORDER_INFO，用來記錄客戶每次買車的訂單詳情，EMP 表和 ORDER_INFO 表是**一對多**的關係，所以 ORDER_INFO 表中有一定有 `EMP_ID` 欄位。

``` 
|--com.example.demospringboot
   |--DemospringbootApplication.java
|--com.example.demospringboot.configuration
   |--SwaggerConfig.java
   |--RestConfiguration.java
|--com.example.demospringboot.controller
   |--TestController.java
   |--ProductController.java
   |--EMPController.java // 新增的檔案
|--com.example.demospringboot.entity
   |--Car.java
   |--CarPK.java
   |--EMP.java // 新增的檔案
   |--OrderInfo.java // 新增的檔案
|--com.example.demospringboot.model
   |--Product.java
|--com.example.demospringboot.repository
   |--CarRepository.java
   |--EMPRepository.java // 新增的檔案
|--com.example.demospringboot.service
   |--CarService.java
   |--ProductService.java
|--com.example.demospringboot.service.impl
   |--CarServiceImpl.java
   |--ProductServiceImpl.java
```
首先建立 EMP Entity 類別 ：

```java
@Entity
@Table(name = "TB_EMP")
public class EMPEntity implements Serializable {
    private static final long serialVersionUID = 1L; 

    // 由於是一對多的關係，由一的角度出發加上 @JoinColumn，後面指的是另一個表的外來鍵
    @OneToMany(targetEntity = OrderInfoEntity.class)
    @JoinColumn(name = "EMP_ID")
    private List<OrderInfoEntity> orderInfoList;

    @Id
    @Column(name = "EMP_ID")
    private String empID;

    @Column(name = "EMP_NAME")
    private String empName;

    @Column(name = "CREAT_USER")
    private String createUser;

    @Column(name = "CREAT_DATE")
    private Timestamp createDate;

    public List<OrderInfoEntity> getOrderInfo() {
        return orderInfoList;
    }

    public void setOrderInfo(List<OrderInfoEntity> orderInfo) {
        this.orderInfoList = orderInfo;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createuser) {
        this.createUser = createuser;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}
```

由於是一對多的關係，查出來的資料我們預期會長下面這個樣子：
```
{
    "empID": "12345",
    "empName": "上山打老虎2",
    "createUser": null,
    "createDate": null,
    "orderInfo": [
        {
            "orderNo": "20180328142522072",
            "manufacturer": "000000000",
            "orderName": "AAA",
            "totalPrice": 111111111111,
            "arriveDate": "2018-05-28",
            "orderTime": "2018-03-28T06:25:22.072+00:00"
        },
        {
            "orderNo": "20180502170416044",
            "manufacturer": "0",
            "orderName": "AAA",
            "totalPrice": 8888,
            "arriveDate": "2018-07-02",
            "orderTime": "2018-05-02T09:04:16.044+00:00"
        }
    ]
}    
```
也就是一筆 EMP 資料會含有多個 ORDER_INFO 資料，所以站在一 ( EMP ) 的角度，需要在 `EMPEntity.java` 中加入一個用來裝 ORDER_INFO 資料的集合：

```java
@OneToMany(targetEntity = OrderInfoEntity.class)
@JoinColumn(name = "EMP_ID")
private List<OrderInfoEntity> orderInfoList
```

並在這個屬性上加上 `@JoinColumn` 及 ` @OneToMany` 兩個 Annotation。

其中 `@JoinColumn` 用來指定對應 Table 的外來鍵 ( Foriegn Key ) 是哪一個欄位。欄位名稱要以對應的Table 為主，以目前這個例子來說就是 ORDER_INFO 的 EMP_ID 。

`@OneToMany` 則用來指定要做Mapping的表是哪一張。

接著建立 `OrderInfoEntity.java`：

```java
@Entity
@Table(name = "ORDER_INFO")
public class OrderInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ORDER_NO")
    private String orderNo;

    // @Column(name = "EMP_ID")
    // private String empID;

    @Column(name = "MANUFACTURER")
    private String manufacturer;

    @Column(name = "ORDER_NM")
    private String orderName;

    @Column(name = "TOTAL_PRICE")
    private BigDecimal totalPrice;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    // public String getEmpID() {
    //     return empID;
    // }
    //
    // public void setEmpID(String empID) {
    //     this.empID = empID;
    // }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(Date arriveDate) {
        this.arriveDate = arriveDate;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    @Column(name = "ARRIVE_DT")
    private Date arriveDate;

    @Column(name = "ORDER_TM")
    private Timestamp orderTime;
}
```
<font color="red">由於 ORDER_INFO 的 EMP_ID 為外來鍵，所以</font>

再來新增 `EMPRepository.java` 及 `EMPController.java`：
```java
@Repository
public interface EMPRepository extends JpaRepository<EMPEntity, String>{

}
```

```java
@RestController
public class EmpController {
    
    @Autowired
    private EmpRepository empRpository;
    
    @RequestMapping(value = "/empJoinByPK/{empID}", method = RequestMethod.GET)
    public EMPEntity queryEmpJoinByPK(@PathVariable String empID){
        return empRpository.findById(empID).get();
    }
}
```
這邊只是為了看取得資料之後的格式長怎樣，所以直接透過 Repository 操作即可。

最後透過 PostMan 測試，在 url 的部分輸入 `http://localhost:8080/empJoinByPK/12345`，就可以拿到查詢的資料。
<img src="/images/8.5-2.png">

## 參考
https://blog.csdn.net/u010588262/article/details/76667283 
https://www.baeldung.com/jpa-join-column

