
### 1. **获取所有用户**
- **方法**：`GET`
- **URL**：`http://localhost:8080/users`


#### 说明：
- 返回所有用户的列表。
- 响应数据格式为 JSON 数组，每个元素是一个 `UserDto` 对象。

---

### 2. **根据 ID 获取用户信息**
- **方法**：`GET`
- **URL**：`http://localhost:8080/users/{id}`


#### 说明：
- 通过用户 ID 获取用户的详细信息。
- `{id}` 替换为具体的用户 ID。
- 响应数据格式为 `UserDto` 对象。

---

### 3. **创建用户**
- **方法**：`POST`
- **URL**：`http://localhost:8080/users`
- **请求头**：`Content-Type: application/json`
- **请求体**：一个 JSON 对象，包含以下字段：
  - `username`（用户名，字符串）
  - `email`（邮箱，字符串，格式校验）
  - `password`（密码，字符串，长度 >= 8）
  - `role`（角色，字符串，如 "学生" 或 "教师"）

#### 示例：
```bash
curl -X POST http://localhost:8080/users \
-H "Content-Type: application/json" \
-d '{
  "username": "john_doe",
  "email": "john@example.com",
  "password": "password123",
  "role": "学生"
}'
```

#### 说明：
- 创建一个新用户。
- 请求体数据会被映射到 `User` 实体类中。
- 成功后，返回创建的用户信息（`UserDto` 格式）。

---

### 4. **删除用户**
- **方法**：`DELETE`
- **URL**：`http://localhost:8080/users/{id}`

#### 示例：
```bash
curl -X DELETE http://localhost:8080/users/10
```

#### 说明：
- 根据用户 ID 删除对应用户。
- `{id}` 替换为具体的用户 ID。
- 无响应体，成功返回状态码 `204 No Content`。

---

### 发送请求注意事项：
1. **工具选择**：
   - 使用 Postman 或其他 REST API 测试工具。
   - 使用 `curl` 或编写前端代码发送 HTTP 请求。

2. **错误处理**：
   - 确保请求数据格式正确，字段名和内容类型与 `UserDto` 中定义一致。
   - 如果出现错误，如字段校验失败或 ID 不存在，API 会返回对应的 HTTP 状态码和错误信息。

3. **启动服务**：
   确保 Spring Boot 服务已启动，并运行在 `localhost:8080` 上。

通过这些 API，您可以完成用户管理的基本功能，包括用户的查询、创建和删除。
