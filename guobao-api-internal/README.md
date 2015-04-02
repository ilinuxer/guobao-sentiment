
## 国保與情项目国内接口服务

> 基于Spring MVC + Jetty 实现。

### 授权用户信息接口

1.POST
`根信息`： http://localhost:8888/oauth/{sns}
`说明`：sns代表社交网站类型，如：tw/fb/gp；请求数据类型List<T>，T代表Twitter、Facebook和Google+的授权用户信息。

2.DELETE
`根信息`： http://localhost:8888/oauth/{sns}/{usernames}
`说明`：usernames，都好分割的用户名。

3.GET
`根信息`： http://localhost:8888/oauth/{sns}/{usernames}
`说明`： usernames，都好分割的用户名。

### 用户基本信息接口

1.POST
`根信息`： http://localhost:8888/user/{sns}
`说明`：sns代表社交网站类型，如：tw/fb/gp；请求数据类型List<T>，T代表Twitter、Facebook和Google+的用户基本信息。

2.DELETE
`根信息`： http://localhost:8888/user/{sns}/{uids}
`说明`：uids，都好分割的用户名。

3.GET
`根信息`： http://localhost:8888/user/{sns}/{uids}
`说明`： uids，都好分割的用户名。

### 用户状态信息接口或索引接口

1.POST
`根信息`： http://localhost:8888/persist
`说明`： 请求数据类型PostData，统一的数据类型。

2.DELETE
暂无。

3.GET
`根信息`： http://192.168.31.11:8983/solr/sentiment/select?
`说明`： 更多查询参数参考wiki。


### API文档
[项目wiki](http://192.168.3.23/wiki)

> **备注:** API文档统一放在公司的wiki上。

