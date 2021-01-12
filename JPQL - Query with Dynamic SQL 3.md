# 8. Query with Dynamic SQL - 3
剛剛完成了動態解開 SQL 

``` SQL
select *
 from STUDENT.TB_EMP
 where 1 = 1
 [ and EMP_ID = ':EMP_ID']
 [ and EMP_NAME = ':EMP_NAME']
 [ and CREAT_USER = ':CREAT_USER']
 [ and CREAT_DATE = ':CREAT_DATE']
```

當有傳入相對應的參數時，才解開對應的 SQL，也就是說如果傳入 EMP_ID 參數，那麼最後得到的 SQL 應該要長成下面這個樣子：

``` SQL
select *
 from STUDENT.TB_EMP
 where 1 = 1
 and EMP_ID = :EMP_ID
```

為了達到這個目的，我們就必須在 `SqlUtils.java` 中新增一些方法，來對既有的 SQL 做處理，處理動態 SQL 的讀取以及設定查尋欄位等功能。

``` 
|--com.example.demospringboot
   |--DemospringbootApplication.java
|--com.example.demospringboot.configuration
   |--SwaggerConfig.java
   |--RestConfiguration.java
|--com.example.demospringboot.controller
   |--TestController.java
   |--ProductController.java
   |--EmpController.java // 修改的檔案
|--com.example.demospringboot.entity
   |--Car.java
   |--CarPK.java
|--com.example.demospringboot.model
   |--Product.java
|--com.example.demospringboot.repository
   |--CarRepository.java
|--com.example.demospringboot.service
   |--CarService.java
   |--ProductService.java
|--com.example.demospringboot.service.impl
   |--CarServiceImpl.java
   |--ProductServiceImpl.java
|--com.example.demospringboot.service.sql
   |--SqlAction.java // 修改的檔案
   |--SqlUtils.java // 修改的檔案
|--sql
   |--FIND_BY_PK_001
   |--FIND_001 // 新增的檔案
```

首先先在 `sql` 資料夾 ( folder ) 建立一個新 SQL 檔 `FIND_001` ：

``` SQL
select *
 from STUDENT.TB_EMP
 where 1 = 1
 [ and EMP_ID = :EMP_ID]
 [ and EMP_NAME = :EMP_NAME]
 [ and CREAT_USER = :CREAT_USER]
 [ and CREAT_DATE = :CREAT_DATE]
```

在修改 `SqlUtils.java` 前，我們需要先在 `pom.xml` 中加入等一下會使用到的 `StringUtils` 套件依賴， `StringUtils` 中有許多方法，可以用來對字串做檢核或是判斷。

``` xml
<!-- https://mvnrepository.com/artifact/org.apache.commons/commons-lang3 -->
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
    <version>3.0</version>
</dependency>
```

加入依賴後修改 `SqlUtils.java` Class：

``` java
public class SqlUtils {

   /**
    * 取得並組成動態查詢SQL
    * @param sqlName
    * @param dataMap
    * @return
    * @throws IOException
    */
   public String getDynamicQuerySQL(String sqlName, Map<String, Object> paramsMap) throws IOException {
      String originSql = getSQLStr(sqlName);
      return getQuerySql(originSql, paramsMap);
    }

   /**
    * 取得查詢SQL
    * @param sqlName
    * @throws IOException
    */
   public String getQuerySql(String sqlName) throws IOException {
      return getSQLStr(sqlName, null, null);
   }

   private String getSQLStr(String sqlName, String[] queryFields, String[] orderBy) throws IOException {
      ClassPathResource resource = new ClassPathResource("sql/" + sqlName);
      InputStream dbAsStream = resource.getInputStream();
      StringBuilder sb = new StringBuilder();

      try (BufferedReader br = new BufferedReader(new InputStreamReader(dbAsStream))) {
         while (br.ready()) {
            sb.append(br.readLine());
         }
      }

      String sql = sb.toString();
      return sql;
   }
}
```

需要注意的是， `Matcher` 及 `Pattern` 兩個套件都是引入正規表達式相關的 `java.util.regex` 套件。而 `StringUtils` 則引入剛剛加入的 `org.apache.commons.lang3.StringUtils` 套件。

import 套件內容如下：

``` java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
```

完成後在 `SqlAction.java` 中新增查詢方法，所有跟 SQL 檔案的操作都會透過這支程式來做。要取得相對應的 SQL 檔只需要傳入相對應的檔案名稱就可以了，其他的解析交給剛剛新增的 `SqlUtils.java` 。

``` java
@Configuration
public class SqlAction {

   @Autowired
   private EntityManager entityManager;

   public <T> List<T> queryMagicianBySql(String sql, Map<String, Object> paramsMap) {
      Query query = entityManager.createNativeQuery(sql);
        
      Set<String> keySet = paramsMap.keySet();
      for(String key : keySet){
         query.setParameter(key, paramsMap.get(key));
      }

      return query.getResultList();
   }
}
```

最後在 EmpController.java 中新增方法：

``` java
@RestController
public class EMPController {

   @Autowired
   private SqlAction sqlAction;
    
   @Autowired
   private SqlUtils sqlUtils;
 
   @RequestMapping(value = "/empNameByDynamicSql", method = RequestMethod.GET)
   public <T> List<T> queryEmpByDynamicSql2(RequestEntity<Map<String, Object>> requestEntity) throws IOException{
      String sql = sqlUtils.getDynamicQuerySQL("FIND_001", requestEntity.getBody());
      return sqlAction.queryBySql(sql);
   }
}
```

在 Postman 輸入相對應的 url 便可以拿到我們想要的資料，若要查詢結果的資料想要按照格式顯示，在 SqlAction.java 的 createNativeQuery() 方法多傳入一個寫好的 Entity 即可。

## 參考

## 資源

https://mvnrepository.com/artifact/org.apache.commons/commons-lang3/3.0 
