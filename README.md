Spring Boot RESTful APIs Example
---
- 使用 gradle 進行專案 dependency 管理
  - ```shell
    gradle build
    ```
  - 打包完是個可執行直接 war , 亦可部署到一版 tomcat server 後執行
- 使用 Spring boot 進行 RESTful 開發
- 使用標準　3-tier 架構，唯此範例，無後端 DAO 架構 (JPA)
- 錯誤處理，一徑參考有統一處理，及各別客製處理方式
  - 請參考： [ExceptionHandlerController](./src/main/java/tw/noah/spring/boot/api/example/control/ExceptionHandlerController.java) 及 [exception package](./src/main/java/tw/noah/spring/boot/api/example/exception/)
- api 文件是使用 swagger ，並且有使用 springfox-swagger-ui
  - swagger ui : http://localhost:8080/swagger-ui.html
  - swagger 文檔，可參考 [swagger.md](doc/swagger.md)
