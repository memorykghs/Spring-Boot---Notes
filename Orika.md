一、	目前由於ObjectMapper在進行convertValue(Object2Object)時太吃效能，因此目前改用新的OrikaMapperUtil，使用時須注意以下幾點：
1.	若專案內原先沒有OrikaMapper，pom.xml中須自行引入orika-core，可參考lip-svc-pln-flow，並將OrikaMapperUtil及CapitalizePropertyResolver放置於cub.cathaybk.util.mapper下
2.	內有方法map/mapAsList/mapAsCapitalizeMap三種，分別為Object2Object，Collection2List，Object2大寫駝峰Map(轉小寫駝峰直接使用map方法即可)
3.	mapAsCapitalizeMap傳入的DTO/Entity/Object中不可有基本型別(如：int, long等)，且僅能轉換單層，第二層則會維持原先Object的型別，若有需要請自行轉換
4.	若是使用map或是mapAsList方法，可以多層映射，不須每層自行賦值，但變數名稱需相同
5.	轉換時是以存取getter/setter方法進行映射，因此對應時是以變數名稱進行對應，而非JsonProperty，記得一定要加lombok或是自行產生getter/setter
6.	若是由SqlAction查回的List<Map<String, Object>>轉為Object，仍使用原先的MapReflectUtil
7.	僅於物件轉換(原先使用ObjectMapper.convertValue)時使用OrikaMapper，若是要將物件轉為Json字串或是由Json字串轉回物件，仍使用ObjectMapper


二、	日期相關：
1.	遇到時間格式須轉為String時，除非有另行指定，否則Date與Timestamp的字串格式皆轉為yyyy-MM-dd或yyyy-MM-dd’T’HH:mm:ss
2.	盡量避免使用SimpleDateFormat(Thread Unsafe)，建議可改用LocalDate與LocalDateTime搭配DateTimeFormatter

三、	JPA及SQL使用相關：
1.	使用時若是更新仍要在最後使用repository.save()
2.	除非有特別註明，否則若有進行DB的insert/update/delete，皆須於service加上@Transactional(rollbackFor = Exception.class)
3.	使用JPA進行insert/update，需於Entity中加上@NotBlank或@NotNull進行檢核(參考DB中設定非null欄位)，Hibernate會在commit時進行檢查，避免在DB報錯。
若有檢核失敗的欄位，會於ErrorHandler中Cache並回傳E903(必填欄位不完整)(各專案設定可能不同)
