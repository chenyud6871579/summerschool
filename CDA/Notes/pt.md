# RequestMapping 列表

| Key     | Value          |
| ------- | -------------- |
| IP+端口 | localhost:8080 |
| 首页    | /index         |

# Day 1

> 总结：完成项目构建

---

# Day 2

完成 Python 端 Thrift 服务的实现

---

# Day 3

## 1.实现了热部署

> 就是支持了热部署，但是部署速度较慢
>
> 无法忍受或者比较着急可以手动按==ctrl+F9==手动快速重构项目
>
> 刷新项目速度：rebuild > auto > rerun

## 2. 实现 resources 的配置

==需要所有操作默认在**user-edge-service**中进行==

### ① 添加 HTML 文件

> 提前告诉我需要加页面这事儿、提供参数列表、提供文件名
>
> 将需要添加的 HTML 页面放进 templates 中

