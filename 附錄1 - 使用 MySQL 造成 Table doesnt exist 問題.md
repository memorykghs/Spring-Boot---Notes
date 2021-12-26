# 附錄1 - 使用 MySQL 造成 Table doesnt exist 問題
使用 MySQL 作為資料庫，執行時出現如下錯誤訊息：
![](/images/A-1.png)

查看 log 中 Hibernate 的 SQL 語句：
```
select userinfo0_.user_id as user_id1_8_, userinfo0_.email as email2_8_, userinfo0_.password as password3_8_, userinfo0_.user_name as user_name4_8_ from user_info userinfo0_ where userinfo0_.user_name=?
```

仔細看會發現 MySQL 產出的對應 table 名稱是 `user_info`，然而實際上 table 名稱是大寫 `USER_INFO`，所以找不到 table。

只要補上以下設定即可：
```yml
spring: 
  jpa:
    hibernate:
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
```

## 參考
* https://blog.csdn.net/jiangyu1013/article/details/80395579