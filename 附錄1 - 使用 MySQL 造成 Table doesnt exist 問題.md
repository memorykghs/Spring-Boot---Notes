# 附錄1 - 使用 MySQL 造成 Table doesnt exist 問題
使用 MySQL 作為資料庫，執行時出現如下錯誤訊息：



可以補上以下設定即可：
```yml
spring: 
  jpa:
    hibernate:
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
```