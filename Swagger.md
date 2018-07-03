Spring Boot RESTful API 搭配 swagger 及 swagger ui 基本用法:
---
### build.gradle 加入相關 dependencies
```bash
	compile group: 'io.springfox', name: 'springfox-swagger2', version: '2.9.2'
	compile group: 'io.springfox', name: 'springfox-swagger-ui', version: '2.9.2'
```

### 增加基本說明檔
[Swagger Config Class](src/tw/noah/spring/boot/api/example/config/SwaggerConfig)

### 基本語法
- `@Api`：用在類上，說明該類別的作用，基本上無用途
- `@ApiOperation`：用在方法上，說明方法的作用
- `@ApiImplicitParams`：用在方法上包含一組參數說明
- `@ApiImplicitParam`：用在 `@ApiImplicitParams` 註解中，指定一個請求參數的各個方面
  - `paramType` ：參數放在哪個地方
    - `header` -->請求參數的獲取： `@RequestHeader`
    - `query` -->請求參數的獲取： `@RequestParam` 
    - `path`（用於restful接口）-->請求參數的獲取： `@PathVariable`
    - body（不常用） 
    - form（不常用） 
  - `name`：參數名
  - `dataType`：參數類型
  - `required`：參數是否必須傳
  - `value`：參數的意思
  - `defaultValue`：參數的默認值
- `@ApiResponses`：用於表示一組響應(response)
- `@ApiResponse`：用在 `@ApiResponses` 中，一般用於表達一個錯誤的響應信息
  - `code`：數字，例如 400 
  - `message` ：信息，例如 "請求參數沒填好" 
  - `response` ：拋出異常的類別
- `@ApiModel`：描述一個Model的信息（這種一般用在post創建的時候，使用 `@RequestBody` 這樣的場景，請求參數無法使用`@ApiImplicitParam`註解進行描述的時候） 
  - `@ApiModelProperty`：描述一個model的屬性
  
 
### 文件參考
 - https://swagger.io/tools/open-source/open-source-integrations/
 - https://swagger.io/docs/