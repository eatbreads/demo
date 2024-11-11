1. 用户注册接口
   接口：POST /user
   用于用户注册。
请求类型: POST
URL: http://localhost:8080/user
请求体（JSON 格式）:
```json

{
"userName": "testUser",
"password": "password123",
"email": "testuser@example.com"
}
```
响应（成功）:
json
```json
{
"code": 200,
"message": "success",
"data": {
"userId": 1,
"userName": "testUser",
"password": "password123",
"email": "testuser@example.com"
}
}
```

响应（失败）:
如果出现错误（例如，缺少字段或其他问题），将返回一个类似以下内容的错误信息：

```json
{
"code": 400,
"message": "Invalid input data",
"data": null
}
```
2. 用户登录接口
   接口：POST /user/login
   用于用户登录验证。

请求类型: POST
URL: http://localhost:8080/user/login
请求体（JSON 格式）:
```json
{
"userName": "testUser",
"password": "password123"
}
```
响应（成功）:
```json
{
"code": 200,
"message": "Login successful",
"data": null
}
```
响应（用户名或密码错误）:
```json
{
"code": 401,
"message": "Incorrect password",
"data": null
}
```
或：
```json
{
"code": 401,
"message": "User not found",
"data": null
}
```
3. 获取用户信息接口
   接口：GET /user/{userId}
   用于获取特定用户的详细信息。

请求类型: GET
URL: http://localhost:8080/user/{userId} (将 {userId} 替换为实际的用户 ID)
响应（成功）:
```json
{
  "code": 200,
  "message": "success",
  "data": {
    "userId": 1,
    "userName": "testUser",
    "password": "password123",
    "email": "testuser@example.com"
  }
}
```
}
响应（失败，用户不存在）:
```json
{
"code": 404,
"message": "User not found",
"data": null
}
```
4. 更新用户信息接口
   接口：PUT /user
   用于更新用户信息。

请求类型: PUT
URL: http://localhost:8080/user
请求体（JSON 格式）:
```json
{
"userId": 1,
"userName": "updatedUser",
"password": "newPassword123",
"email": "updateduser@example.com"
}
```
响应（成功）:
```json
{
"code": 200,
"message": "success",
"data": {
"userId": 1,
"userName": "updatedUser",
"password": "newPassword123",
"email": "updateduser@example.com"
}
}
```