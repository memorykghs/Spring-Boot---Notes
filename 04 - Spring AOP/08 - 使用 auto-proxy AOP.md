# 08 - 使用 auto-proxy AOP
除了使用 `ProxyFactoryBean` 或其他類似功能的 factory bean 之外，也可以使用 auto-proxy bean definitions 功能。這個是建立在 Spring 的 **bean post processor** 基礎功能上的，它可以在容器載入時修改要使用的 bean definition。

在下面的 model 中，可以在 `xml` 中配置要使用 auto-proxy 設定的 bean 而不需要使用 `ProxyFactoryBean`。

有兩種方法可以進行設定：
1. 依當前的執行環境決定要讓 auto-proxy creator 使用哪一個特定的 bean
2. 另外一個是比較特殊的情況：由 source-level metadata 驅動的 auto proxy ( 來源層級的 metadata 來決定，個人目前定義為 source code 等級的來源資料 ) 

## 參考
* https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#aop-autoproxy