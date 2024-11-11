打算做个股票交易系统
待完成api
5. 股票信息管理 API
   这些 API 用于查询和管理股票信息，例如查看股票列表、获取单只股票的详细信息等。

2.1 获取所有股票列表
URL: /stocks
方法: GET
请求体: 无
功能: 获取所有可交易的股票列表，包括股票名称、股票代码、当前价格等。
2.2 获取单只股票的详细信息
URL: /stocks/{stockId}
方法: GET
请求体: 无
功能: 获取指定股票的详细信息，包括股票价格、历史涨跌、交易量等。
2.3 获取股票实时价格
URL: /stocks/{stockId}/price
方法: GET
请求体: 无
功能: 获取指定股票的最新实时交易价格。
3. 交易相关 API
   这些 API 用于股票买卖、订单管理等交易操作。

3.1 下单（买入股票）
URL: /orders
方法: POST
请求体: 包含买入股票的相关信息，例如股票ID、买入数量、价格等。
功能: 用户提交买入订单。
3.2 下单（卖出股票）
URL: /orders
方法: POST
请求体: 包含卖出股票的相关信息，例如股票ID、卖出数量、价格等。
功能: 用户提交卖出订单。
3.3 获取当前订单
URL: /orders
方法: GET
请求头: Authorization: Bearer {token}
功能: 获取当前用户的所有订单（买入、卖出、已完成、未完成等）。
3.4 获取指定订单
URL: /orders/{orderId}
方法: GET
请求头: Authorization: Bearer {token}
功能: 获取指定订单的详细信息。
3.5 取消订单
URL: /orders/{orderId}/cancel
方法: POST
请求头: Authorization: Bearer {token}
功能: 用户取消一个未成交的订单。
4. 账户管理 API
   这些 API 用于查询和管理用户账户信息。

4.1 获取账户余额
URL: /accounts/balance
方法: GET
请求头: Authorization: Bearer {token}
功能: 获取当前用户的账户余额，包括现金余额、股票余额等。
4.2 获取账户历史交易记录
URL: /accounts/transactions
方法: GET
请求头: Authorization: Bearer {token}
功能: 获取当前用户的历史交易记录，包括买入、卖出、账户充值等。
5. 股票市场相关 API
   这些 API 用于获取市场动态、股票价格历史记录等。

5.1 获取股票价格历史记录
URL: /stocks/{stockId}/history
方法: GET
请求体: 可选查询条件（时间范围、交易类型等）。
功能: 获取某只股票的历史价格数据，支持查询指定时间段内的价格波动。
5.2 获取市场动态
URL: /market/news
方法: GET
功能: 获取最新的股市新闻和动态，以帮助用户做出交易决策。
6. 风险控制和通知 API
   这些 API 用于风险控制和向用户发送通知（例如，交易成功、余额不足等）。

6.1 账户风控检查
URL: /risk/check
方法: POST
请求体: 包含用户交易相关的请求（买入、卖出等）。
功能: 检查用户账户是否符合交易要求，例如账户余额是否足够、是否超过交易限额等。
6.2 发送交易通知
URL: /notifications
方法: POST
请求体: 包含通知内容（如交易成功、余额不足等）。
功能: 向用户发送交易通知，确保用户及时了解账户状态。