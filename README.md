# 线上学习交流平台

## 主要组成部分

1. **聊天室管理**
   - 聊天室创建与管理
   - 消息记录与实时通信
   - 用户与聊天室关系
   - 文件与多媒体支持

2. **课程管理**
   - 课程信息
   - 学习进度
   - 测验与作业
   - 课程评价

3. **教师直播管理**
   - 直播间创建与启动
   - 实时观看与互动
   - 直播资源管理（课件与录播）
   - 数据统计与分析


### 1. 用户管理
- **users**（用户表）
  - id（主键）
  - username（用户名）
  - email（邮箱）
  - password（密码，需加密存储）
  - role（角色，如学生、教师、管理员）
  - created_at（注册时间）
  - updated_at（更新时间）


### 1. 聊天室管理
- **chat_rooms**（聊天室表）
  - `id`（主键）
  - `name`（聊天室名称，例如课程讨论组）
  - `course_id`（课程 ID，可选，关联 `courses` 表）
  - `created_at`（创建时间）
  - `updated_at`（更新时间）

### 2. 聊天消息
- **messages**（消息表）
  - `id`（主键）
  - `chat_room_id`（聊天室 ID，关联 `chat_rooms` 表）
  - `user_id`（用户 ID，关联 `users` 表）
  - `content`（消息内容）
  - `message_type`（消息类型，如文本、图片、文件等）
  - `created_at`（消息发送时间）

### 3. 用户与聊天室的关系
- **chat_room_members**（聊天室成员表）
  - `id`（主键）
  - `chat_room_id`（聊天室 ID，关联 `chat_rooms` 表）
  - `user_id`（用户 ID，关联 `users` 表）
  - `joined_at`（加入时间）
  - `is_active`（是否活跃，方便管理成员状态）

### 4. 文件与媒体支持（可选）
- **attachments**（附件表）
  - `id`（主键）
  - `message_id`（消息 ID，关联 `messages` 表）
  - `file_url`（文件存储路径）
  - `file_type`（文件类型，如图片、文档、视频）
  - `uploaded_at`（上传时间）

### 5. 通知功能（可选）
- **message_notifications**（消息通知表）
  - `id`（主键）
  - `user_id`（用户 ID）
  - `message_id`（消息 ID，关联 `messages` 表）
  - `is_read`（是否已读）
  - `notified_at`（通知时间）

---

### 具体功能设计建议
1. **支持群聊与私聊**
   - 对于群聊，关联 `chat_rooms` 表和 `chat_room_members` 表。
   - 对于私聊，可以跳过 `chat_rooms` 表，直接使用 `user_id` 和 `recipient_id` 字段（存储发消息和接收消息的双方）。

2. **搜索功能**
   - 为 `messages.content` 字段添加全文索引，方便实现消息搜索功能。

3. **消息持久化与优化**
   - 定期归档历史消息到冷数据存储（如云存储），减少数据库压力。
   - 使用分页查询加载历史消息。

4. **实时通信支持**
   - 使用 WebSocket 或 Server-Sent Events 实现实时消息推送。
   - 数据库中保存基础数据，实时消息通过内存缓存（如 Redis）处理。

为了支持教师在平台上进行直播功能，可以设计以下数据库表和功能模块：

---

### 数据库设计

#### 1. **直播间管理**
- **live_rooms**（直播间表）
  - `id`（主键）
  - `title`（直播标题）
  - `teacher_id`（教师 ID，关联 `users` 表）
  - `course_id`（课程 ID，关联 `courses` 表，可选）
  - `status`（直播状态，如未开始、直播中、已结束）
  - `start_time`（计划开始时间）
  - `end_time`（实际结束时间）
  - `created_at`（创建时间）
  - `updated_at`（更新时间）

#### 2. **直播观看记录**
- **live_viewers**（观看记录表）
  - `id`（主键）
  - `live_room_id`（直播间 ID，关联 `live_rooms` 表）
  - `user_id`（用户 ID，关联 `users` 表）
  - `join_time`（进入时间）
  - `leave_time`（离开时间，可为空表示还在观看）

#### 3. **聊天与互动**
（可复用聊天室的表结构，也可以单独为直播设计）
- **live_chat_messages**（直播聊天表）
  - `id`（主键）
  - `live_room_id`（直播间 ID）
  - `user_id`（用户 ID，关联 `users` 表）
  - `content`（聊天内容）
  - `message_type`（消息类型，如文本、表情、图片等）
  - `created_at`（发送时间）

#### 4. **直播资源管理**
- **live_assets**（直播资源表）
  - `id`（主键）
  - `live_room_id`（直播间 ID）
  - `file_url`（文件地址，如课件、录播视频）
  - `file_type`（资源类型，如视频、PDF、图片等）
  - `uploaded_at`（上传时间）

#### 5. **直播统计与数据分析**
- **live_statistics**（直播统计表）
  - `id`（主键）
  - `live_room_id`（直播间 ID）
  - `viewer_count`（总观看人数）
  - `peak_viewers`（同时在线人数峰值）
  - `average_watch_time`（人均观看时长）
  - `collected_at`（统计时间）

---

### 功能模块设计

#### 1. **创建直播间**
- 教师可以创建直播间，指定标题、课程关联、计划时间等。
- 保存到 `live_rooms` 表。

#### 2. **启动与结束直播**
- 教师在指定时间内启动直播，系统将更新 `live_rooms.status` 为 "直播中"。
- 结束直播时，更新状态为 "已结束"，并记录结束时间。

#### 3. **观看直播**
- 学生通过课程或首页推荐列表进入直播间。
- 在进入时，记录到 `live_viewers` 表，实时更新观看人数。
- 离开直播间时，更新离开时间。

#### 4. **直播互动**
- 支持文字聊天、发送表情、提问等互动，保存到 `live_chat_messages` 表。
- 可设置管理员权限，教师或助教可以删除不当消息。

#### 5. **直播资源管理**
- 教师可以上传课件或共享屏幕。
- 直播结束后，录播视频可以自动上传并关联到直播间。

#### 6. **直播统计**
- 实时统计观看人数，并记录峰值。
- 支持教师查看历史直播的观看数据。

---

### 技术实现建议

1. **实时推流技术**
   - 使用 WebRTC 或 RTMP 协议来实现视频直播。
   - 可以集成流媒体服务器（如 Wowza、Nginx-RTMP 或开源的 MediaSoup）。

2. **实时互动**
   - 使用 WebSocket 实现低延迟的聊天、弹幕、问答功能。

3. **录播功能**
   - 直播过程中支持录制流媒体并保存到文件服务器或云存储（如 AWS S3、阿里云 OSS）。

4. **负载均衡**
   - 对于大规模用户，使用 CDN 加速视频流分发（如 Cloudflare 或腾讯云 CDN）。
   - 使用 Redis 或 Kafka 管理实时消息队列。

5. **权限管理**
   - 直播间分权限（如教师、助教、学生），控制可操作的功能。
   - 限制未报名的学生观看直播。


### 2. 课程管理
- **courses**（课程表）
  - id（主键）
  - title（课程名称）
  - description（课程描述）
  - teacher_id（教师 ID，关联 `users` 表）
  - category_id（分类 ID，关联 `categories` 表）
  - created_at（创建时间）
  - updated_at（更新时间）

- **categories**（课程分类表）
  - id（主键）
  - name（分类名称）
  - parent_id（父分类 ID，支持多级分类）

### 3. 学习进度
- **enrollments**（课程报名表）
  - id（主键）
  - user_id（用户 ID，关联 `users` 表）
  - course_id（课程 ID，关联 `courses` 表）
  - enrolled_at（报名时间）

- **progress**（学习进度表）
  - id（主键）
  - user_id（用户 ID）
  - course_id（课程 ID）
  - lesson_id（课时 ID，关联 `lessons` 表）
  - status（状态，如未开始、进行中、已完成）
  - last_accessed_at（最后访问时间）

### 4. 内容管理
- **lessons**（课程内容表）
  - id（主键）
  - course_id（课程 ID）
  - title（课时标题）
  - content_url（内容链接，如视频或文档）
  - order（课时顺序）
  - created_at（创建时间）

### 5. 测验与作业
- **quizzes**（测验表）
  - id（主键）
  - course_id（课程 ID）
  - title（测验标题）
  - created_at（创建时间）

- **questions**（题目表）
  - id（主键）
  - quiz_id（测验 ID）
  - question_text（题目内容）
  - question_type（题目类型，如选择题、填空题）
  - options（选项，适用于选择题）

- **submissions**（提交记录表）
  - id（主键）
  - user_id（用户 ID）
  - quiz_id（测验 ID）
  - answer（提交答案）
  - score（得分）
  - submitted_at（提交时间）

### 6. 支付和订单
- **orders**（订单表）
  - id（主键）
  - user_id（用户 ID）
  - course_id（课程 ID）
  - amount（支付金额）
  - status（支付状态，如待支付、已支付、已取消）
  - created_at（创建时间）


### 8. 评价与反馈
- **reviews**（课程评价表）
  - id（主键）
  - user_id（用户 ID）
  - course_id（课程 ID）
  - rating（评分）
  - comment（评价内容）
  - created_at（创建时间）

### 9. 通知与消息
- **notifications**（通知表）
  - id（主键）
  - user_id（用户 ID）
  - message（通知内容）
  - is_read（是否已读）
  - created_at（创建时间）


