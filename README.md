# MobileShop_SSM

参照之前的MobileShop项目改造完成，使用SpringMVC + Spring + MyBatis 框架重构，（代码规范参照阿里巴巴Java开发手册）

更新内容：
---
1. 通过Cookie实现自动登录
2. 数据库连接池使用阿里开源druid数据库连接池（实现SQL的监控）
3. 部分页面使用Ajax与后端异步交互（登录、注册、商品收藏、加购、购物车内商品修改删除）
4. MyBatis开启二级缓存减轻数据库压力
5. 支付环节开启Spring事务管理机制防止订单异常
