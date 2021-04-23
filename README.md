# 大数据报名项目API接口文档

### 错误码信息

```java
10000, "系统未知异常"
10001, "该方法仅限内部调用"

11001, "管理员模块数据校验异常"
11002, "管理员模块数据封装数据空指针异常"
11003, "管理员信息异常：该管理员用户名已存在"
11004, "管理员信息异常：该管理员用户没有找到"
11005, "请求被拒绝，权限不足，内容不允许访问"
11006, "数据异常：该推荐码无效"

12001, "用户模块数据校验异常"
12002, "用户模块数据封装数据空指针异常"
12003, "用户信息异常：该用户已存在"
12004, "用户信息异常：该用户没有找到"
12005, "用户登陆失败：用户名、密码不匹配"
12006, "用户报名异常：该用户已报名该课程，不可重复报名"
12007, "用户报名异常：找不到该用户报名信息"

13001, "流量及其他模块数据校验异常"
13002, "流量及其他模块数据封装数据空指针异常"
13011, "该证书信息存在"
13012, "该证书信息不存在"
13013, "讲师不存在"
```



#### 1. (POST) http://localhost:88/api/oauth/token

#### 接口描述：获取资源访问令牌（token）

#### 是否为请求体：否

##### 密码模式

|      Key      |       Value        |                       描述                        |
| :-----------: | :----------------: | :-----------------------------------------------: |
|  grant_type   | String("password") |  指定获取token为密码模式（必须指定为“password”）  |
|   username    |       String       |                      用户名                       |
|   password    |       String       |                       密码                        |
|     scope     |       String       | 获取的密匙权限范围{ROLE_ADMIN,ROLE_USER,ROLE_API} |
|   client_id   |       String       |                     客户端id                      |
| client_secret |       String       |                    客户端密钥                     |

##### 客户端模式

|      Key      | Value  |                           描述                            |
| :-----------: | :----: | :-------------------------------------------------------: |
|  grant_type   | String | 指定获取token为密码模式（必须指定为“client_credentials”） |
|     scope     | String |           获取的密匙范围{ROLE_USER,ROLE_API）}            |
|   client_id   | String |                         客户端id                          |
| client_secret | String |                        客户端密钥                         |

###### 注：获取密钥权限范围为（ROLE_ADMIN,ROLE_USER,ROLE_API）三选一：ROLE_ADMIN可以访问管理员权限的功能，ROLE_USER可以访问一般资源，ROLE_API只能访问极少数资源，一般不推荐使用。

------



#### 2. (GET) http://localhost:88/api/administration/admin-account/sms/sendCode

#### 接口描述：管理员用户获取验证码

#### 是否为请求体：否

| Key  | Value  |  描述  |
| :--: | :----: | :----: |
| user | String | 用户名 |





------

#### 3. (POST) http://localhost:88/api/administration/admin-account/password/update

#### 接口描述：管理员用户修改密码

#### 是否为请求体：是

|    Key     | Value  |  描述  |
| :--------: | :----: | :----: |
|    user    | String | 用户名 |
|  password  | String |  密码  |
| verifyCode | String | 验证码 |



------

#### 4. (PUT) http://localhost:88/api/administration/admin-account/add

#### 接口描述：管理员用户添加

#### 需要权限：root；region；company

#### 是否为请求体：是

#### 资源范围：ROLE_ADMIN

|    Key    |   Value   |           描述            |
| :-------: | :-------: | :-----------------------: |
|   user    |  String   |          用户名           |
| password  |  String   |           密码            |
|   phone   |  String   |          手机号           |
|   mail    |  String   |      邮箱（非必需）       |
| authority |  String   | 权限{region,company,base} |
| adminInfo | AdminInfo |      管理员信息对象       |

##### AdminInfo对象字段：

|    Key    |  Value  |                             描述                             |
| :-------: | :-----: | :----------------------------------------------------------: |
|   user    | String  |                   字段内容等于上方user字段                   |
|   name    | String  |                          管理员姓名                          |
|  gender   | String  |                       性别{“男”，“女”}                       |
|   phone   | String  |                  字段内容等于上方phone字段                   |
|   mail    | String  |                   字段内容等于上方mail字段                   |
|   mark    | String  |                        备注（非必需）                        |
|  region   | String  |                             地区                             |
|  company  | String  |                        企业/机构名称                         |
| authority | String  |                字段内容等于上方authority字段                 |
|   level   | Integer | 权限等级{0（“root”），1（“region”）<br />，2（“company”），3（“base”）}<br />权限等级要于权限对应，对应关系如上：<br />如果权限为root，则权限等级：0 |

###### 注：标明非必需字段可以不提交，但不可以提交空字符串等空值，如example：“ ”，此提交可能会有系统校验不通过异常。



------

#### 5. (POST) http://localhost:88/api/administration/admin-account/update

#### 接口描述：管理员用户修改

#### 需要权限：root；region；company

#### 是否为请求体：是

#### 资源范围：ROLE_ADMIN

|    Key    |   Value   |                描述                 |
| :-------: | :-------: | :---------------------------------: |
|   user    |  String   |               用户名                |
| password  |  String   |           密码（非必需）            |
|   phone   |  String   |          手机号（非必需）           |
|   mail    |  String   |           邮箱（非必需）            |
| authority |  String   | 权限{region,company,base}（非必需） |
| adminInfo | AdminInfo |           管理员信息对象            |

##### AdminInfo对象字段：

|    Key    |  Value  |                             描述                             |
| :-------: | :-----: | :----------------------------------------------------------: |
|   user    | String  |                   字段内容等于上方user字段                   |
|   name    | String  |                     管理员姓名（非必需）                     |
|  gender   | String  |                  性别{“男”，“女”}（非必需）                  |
|   phone   | String  |             字段内容等于上方phone字段（非必需）              |
|   mail    | String  |              字段内容等于上方mail字段（非必需）              |
|   mark    | String  |                        备注（非必需）                        |
|  region   | String  |                        地区（非必需）                        |
|  company  | String  |                   企业/机构名称（非必需）                    |
| authority | String  |           字段内容等于上方authority字段（非必需）            |
|   level   | Integer | 权限等级{0（“root”），1（“region”）<br />，2（“company”），3（“base”）}<br />权限等级要于权限对应，对应关系如上：<br />如果权限为root，则权限等级：0（非必需） |

```json
{
    "user": "test",
    "password": "12345",
    "phone": "13425255255",
    "mail": "136@163.com",
    "authority": "region",
    "adminInfo": {
        "user": "test",
        "name": "test",
        "gender": "男",
        "phone": "13425255255",
        "mail": "136@163.com",
        "mark": "无",
        "region": "test",
        "company": "test",
        "authority": "region",
        "level": 1
    }
}

```



###### 注：标明非必需字段可以不提交，但不可以提交空字符串等空值，如example：“ ”，此提交可能会有系统校验不通过异常。

 

------

#### 6. (DELETE) http://localhost:88/api/administration/admin-account/delete

#### 接口描述：管理员用户删除

#### 需要权限：root；region；company

#### 是否为请求体：否

#### 资源范围：ROLE_ADMIN

| Key  | Value  |     描述     |
| :--: | :----: | :----------: |
| user | String | 管理员用户名 |



------



####  7. (GET) http://localhost:88/api/administration/admin-info/info

#### 接口描述：获取管理员用户信息

#### 需要权限：root；region；company

#### 是否为请求体：否

#### 资源范围：ROLE_ADMIN

#### 

------

#### 8. (GET) http://localhost:88/api/administration/admin-info/tree

#### 接口描述：获取管理员下辖用户信息

#### 需要权限：root；region；company

#### 是否为请求体：否

#### 资源范围：ROLE_ADMIN



------

#### 9. (GET) http://localhost:88/api/administration/user/list

#### 接口描述：查询注册所有用户的状况

#### 需要权限：root

#### 是否为请求体：否

#### 资源范围：ROLE_ADMIN



------

#### 10. (PUT) http://localhost:88/api/administration/user/open/registration

#### 接口描述：对外用户报名

#### 需要权限：root；region；company； base

#### 是否为请求体：是

#### 资源范围：ROLE_ADMIN

|       Key        | Value  |       描述        |
| :--------------: | :----: | :---------------: |
|       name       | String |       姓名        |
|      gender      | String | 性别 {“男”，“女”} |
|  identityNumber  | String |     身份证号      |
|      phone       | String |      手机号       |
| certificateName  | String |    报考证书名     |
| certificateGrade | String |   报考证书等级    |
|      photo       | String |    照片URL地址    |
|    reference     | String |      推荐码       |

```json
//数值参考：
{
    "name": "test",
    "gender": "男",
    "identityNumber": "440555200009080475",
    "phone": "13544774474",
    "certificateName": "大数据资产规划师",
    "certificateGrade": "中级",
    "photo": "http://www.xxxxx.com",
    "reference": "47187103"
}
//正确返回结果：
{
    "msg": "报名成功",
    "code": 200,
    "data": 1
}
```



------

#### 11. (POST) http://localhost:88/api/administration/user/root/update

#### 接口描述：用户报名信息修改

#### 需要权限：root

#### 是否为请求体：是

#### 资源范围：ROLE_ADMIN

|       Key        |  Value  |               描述               |
| :--------------: | :-----: | :------------------------------: |
|        id        | Integer |              id编号              |
|       name       | String  |          姓名（非必需）          |
|      gender      | String  |   性别 {“男”，“女”}（非必需）    |
|  identityNumber  | String  |        身份证号（非必需）        |
|      phone       | String  |         手机号（非必需）         |
| certificateName  | String  |       报考证书名（非必需）       |
| certificateGrade | String  |      报考证书等级（非必需）      |
|      photo       | String  |      照片URL地址（非必需）       |
|    reference     | String  |         推荐码（非必需）         |
|    userPhone     | String  | 提交报名信息的用户账号（非必需） |
|     userCode     | String  |  提交报名信息的用户码（非必需）  |

```json
//数值示例：
{   
    "id": 1,
    "name": "test01",
    "gender": "男",
    "identityNumber": "440555200009080475",
    "phone": "13544774474",
    "certificateName": "大数据资产规划师",
    "certificateGrade": "中级",
    "photo": "http://www.xxxxx.com",
    "reference": "47187103"
}
//正确返回结果：
{
    "msg": "修改成功",
    "code": 200,
    "data": 1
}
```



###### 注：标明非必需字段可以不提交，但不可以提交空字符串等空值，如example：“ ”，此提交可能会有系统校验不通过异常。



------

#### 12. (POST) http://localhost:88/api/administration/user/open/update

#### 接口描述：对外用户报名信息修改

#### 需要权限：root；region；company； base

#### 是否为请求体：是

#### 资源范围：ROLE_ADMIN

|       Key        |  Value  |            描述             |
| :--------------: | :-----: | :-------------------------: |
|        id        | Integer |           id编号            |
|       name       | String  |       姓名（非必需）        |
|      gender      | String  | 性别 {“男”，“女”}（非必需） |
|  identityNumber  | String  |     身份证号（非必需）      |
|      phone       | String  |      手机号（非必需）       |
| certificateName  | String  |    报考证书名（非必需）     |
| certificateGrade | String  |   报考证书等级（非必需）    |
|      photo       | String  |    照片URL地址（非必需）    |
|    reference     | String  |           推荐码            |

```json
//数值示例：
{   
    "id": 1,
    "name": "test01",
    "gender": "男",
    "identityNumber": "440555200009080475",
    "phone": "13544774474",
    "certificateName": "大数据资产规划师",
    "certificateGrade": "中级",
    "photo": "http://www.xxxxx.com",
    "reference": "47187103"
}
//正确返回结果：
{
    "msg": "修改成功",
    "code": 200,
    "data": 1
}
```



###### 注：标明非必需字段可以不提交，但不可以提交空字符串等空值，如example：“ ”，此提交可能会有系统校验不通过异常。



------

#### 13. (GET) http://localhost:88/api/administration/user/registration/list

#### 接口描述：对外用户报名信息查询

#### 需要权限：root；region；company； base

#### 是否为请求体：否

#### 资源范围：ROLE_ADMIN

| Key  | Value  |     描述     |
| :--: | :----: | :----------: |
| like | String | 模糊查询字段 |

```json
{
    "msg": "success",
    "code": 200,
    "data": [
        {
            "id": 1,
            "name": "test01",
            "gender": "男",
            "identityNumber": "440555200009080475",
            "phone": "13544774474",
            "certificateName": "大数据资产规划师",
            "certificateGrade": "中级",
            "photo": "http://www.xxxxx.com",
            "region": "region01",
            "company": "cc01",
            "base": "test",
            "reference": "47187103",
            "userCode": null,
            "userPhone": null,
            "operateTime": "2020-12-25 11:18:18",
            "operate": "root",
            "removeState": 1
        }
    ]
}
```



###### 注：like为模糊查询字端，若like:“ ”则表示不需要模糊查询，若like:"r"则查询包含r的词条



------

#### 14. (DELETE) http://localhost:88/api/administration/user/root/delete

#### 接口描述：用户报名信息删除

#### 需要权限：root

#### 是否为请求体：否

#### 资源范围：ROLE_ADMIN

| Key  |  Value  |  描述  |
| :--: | :-----: | :----: |
|  id  | Integer | id编号 |

```json
{
    "msg": "用户编号：1,删除成功",
    "code": 200,
    "data": 1
}
```





------

#### 15. (DELETE) http://localhost:88/api/administration/user/open/delete

#### 接口描述：对外用户报名信息删除

#### 需要权限：root；region；company； base

#### 是否为请求体：否

#### 资源范围：ROLE_ADMIN

|    Key    |  Value  |  描述  |
| :-------: | :-----: | :----: |
|    id     | Integer | id编号 |
| reference | String  | 推荐码 |

```json
{
    "msg": "用户编号：1,删除成功",
    "code": 200,
    "data": 1
}
```



------

#### 16. (GET) http://localhost:88/api/administration/user-other/user-commit

#### 接口描述：获取用户提交的测评信息

#### 需要权限：root

#### 是否为请求体：否

#### 资源范围：ROLE_ADMIN

| Key  |  Value  |   描述   |
| :--: | :-----: | :------: |
| page | Integer |   页码   |
| size | Integer | 每页个数 |

```json
//返回结果(page=1,size=2)：
{
    "msg": "success",
    "code": 200,
    "data": {
        "records": [
            {
                "id": 1,
                "name": "a",
                "phone": "aaa",
                "graduate": "aa",
                "age": "aa",
                "goal": "aa",
                "removeState": 1
            },
            {
                "id": 2,
                "name": "b",
                "phone": "bbb",
                "graduate": "bb",
                "age": "bb",
                "goal": "bb",
                "removeState": 1
            }
        ],
        "total": 23,
        "size": 2,
        "current": 1,
        "orders": [],
        "optimizeCountSql": true,
        "hitCount": false,
        "countId": null,
        "maxLimit": null,
        "searchCount": true,
        "pages": 12
    }
}
```



------

#### 17. (DELETE) http://localhost:88/api/administration/user-other/user-commit

#### 接口描述：删除用户提交的测评信息

#### 需要权限：root

#### 是否为请求体：否

#### 资源范围：ROLE_ADMIN

| Key  |   Value    |    描述    |
| :--: | :--------: | :--------: |
| Ids  | Integer[ ] | id编号数组 |

```json
//数值示例：http://localhost:88/api/administration/user-other/user-commit?Ids=1,2,3
//返回结果：
{
    "msg": "提交成功,删除数据:成功3条,失败0条!",
    "code": 200,
    "data": 3
}
```



------

#### 18. (GET) http://localhost:88/api/administration/user-other/user-forecast-name

#### 接口描述：获取所有用户提交的预报名信息

#### 需要权限：root

#### 是否为请求体：否

#### 资源范围：ROLE_ADMIN

| Key  |  Value  |   描述   |
| :--: | :-----: | :------: |
| page | Integer |   页码   |
| size | Integer | 每页个数 |

```json
//返回结果(page=1,size=2)：
{
    "msg": "success",
    "code": 200,
    "data": {
        "records": [
            {
                "id": 1,
                "name": "wew",
                "phone": "2323",
                "removeState": 1
            },
            {
                "id": 2,
                "name": "zx",
                "phone": "141224141",
                "removeState": 1
            }
        ],
        "total": 26,
        "size": 2,
        "current": 1,
        "orders": [],
        "optimizeCountSql": true,
        "hitCount": false,
        "countId": null,
        "maxLimit": null,
        "searchCount": true,
        "pages": 13
    }
}
```



------

#### 19. (DELETE) http://localhost:88/api/administration/user-other/user-forecast-name

#### 接口描述：删除用户提交的预报名信息

#### 需要权限：root

#### 是否为请求体：否

#### 资源范围：ROLE_ADMIN

| Key  |   Value    |    描述    |
| :--: | :--------: | :--------: |
| Ids  | Integer[ ] | id编号数组 |

```json
//数值示例：http://localhost:88/api/administration/user-other/user-forecast-name?Ids=1,2,3
//返回结果:
{
    "msg": "提交成功,删除数据:成功3条,失败0条!",
    "code": 200,
    "data": 3
}
```





------

#### 20. (PUT) http://localhost:88/api/administration/user-operate/certificate-info

#### 接口描述：添加证书信息

#### 需要权限：root

#### 是否为请求体：是

#### 资源范围：ROLE_ADMIN

|       Key        | Value  |               描述               |
| :--------------: | :----: | :------------------------------: |
| certificateName  | String |              证书名              |
| certificateGrade | String | 证书等级{“初级”，“中级”，“高级”} |
|      price       | Float  |             证书价格             |

```json
//数值示例：
{
    "certificateName": "区块链工程师",
    "certificateGrade": "初级",
    "price": 1999.00
}
//返回结果：
{
    "msg": "证书信息添加成功",
    "code": 200,
    "data": 1
}
```



------

#### 21. (DELETE) http://localhost:88/api/administration/user-operate/certificate-info

#### 接口描述：删除证书信息

#### 需要权限：root

#### 是否为请求体：否

#### 资源范围：ROLE_ADMIN

| Key  |  Value  |    描述    |
| :--: | :-----: | :--------: |
|  id  | Integer | 证书id编号 |

```json
{
    "msg": "删除成功",
    "code": 200,
    "data": true
}
```



------

#### 22. (POST) http://localhost:88/api/administration/user-operate/certificate-info

#### 接口描述：添加证书信息

#### 需要权限：root

#### 是否为请求体：是

#### 资源范围：ROLE_ADMIN

|       Key        |  Value  |                    描述                    |
| :--------------: | :-----: | :----------------------------------------: |
|        id        | Integer |                 证书id编号                 |
| certificateName  | String  |              证书名（非必需）              |
| certificateGrade | String  | 证书等级{“初级”，“中级”，“高级”}（非必需） |
|      price       |  Float  |             证书价格（非必需）             |

```json
//数值示例：
{   
    "id": 7,
    "certificateName": "区块链工程师",
    "certificateGrade": "初级",
    "price": 199.99
}
//返回结果：
{
    "msg": "证书修改成功",
    "code": 200,
    "data": 1
}
```



------

#### 23. (PUT) http://localhost:88/api/administration/user-operate/lecture-info

#### 接口描述：添加讲师信息

#### 需要权限：root

#### 是否为请求体：是

#### 资源范围：ROLE_ADMIN

|         Key         | Value  |     描述     |
| :-----------------: | :----: | :----------: |
|        name         | String |   讲师姓名   |
|     description     | String |   讲师描述   |
| researchOrientation | String | 讲师研究方向 |
|        mark         | String |     备注     |
|        photo        | String | 照片URL地址  |

```json
//数值示例：
{   
    "name": "Test",
    "description": "该讲师为测试对象",
    "researchOrientation": "该讲师为测试对象",
    "mark": "无",
    "photo": "http://www.test.com"
}
//返回结果：
{
    "msg": "讲师信息添加成功",
    "code": 200,
    "data": 1
}
```





------

#### 24. (DELETE) http://localhost:88/api/administration/user-operate/lecture-info

#### 接口描述：删除讲师信息

#### 需要权限：root

#### 是否为请求体：否

#### 资源范围：ROLE_ADMIN

| Key  |  Value  |    描述    |
| :--: | :-----: | :--------: |
|  id  | Integer | 讲师id编号 |

```json
{
    "msg": "删除成功",
    "code": 200,
    "data": true
}
```



------

#### 25. (POST) http://localhost:88/api/administration/user-operate/lecture-info

#### 接口描述：添加讲师信息

#### 需要权限：root

#### 是否为请求体：是

#### 资源范围：ROLE_ADMIN

|         Key         |  Value  |     描述     |
| :-----------------: | :-----: | :----------: |
|         id          | Integer |  讲师id编号  |
|        name         | String  |   讲师姓名   |
|     description     | String  |   讲师描述   |
| researchOrientation | String  | 讲师研究方向 |
|        mark         | String  |     备注     |
|        photo        | String  | 照片URL地址  |

```json
//数值示例：
{   
    "id": 4,
    "name": "Test01",
    "description": "该讲师为测试对象",
    "researchOrientation": "该讲师为测试对象",
    "mark": "无",
    "photo": "http://www.test.com"
}
//返回结果：
{
    "msg": "讲师信息修改成功",
    "code": 200,
    "data": 1
}
```



------

#### 26. (DELETE) http://localhost:88/api/administration/user-operate/user-traffic

#### 接口描述：删除用户流量

#### 需要权限：root

#### 是否为请求体：否

#### 资源范围：ROLE_ADMIN

| Key  |   Value    |    描述    |
| :--: | :--------: | :--------: |
| Ids  | Integer[ ] | id编号数组 |

```json
//数值示例：http://localhost:88/api/administration/user-operate/user-traffic?Ids=1,2,3
//返回结果：
{
    "msg": "提交成功,删除数据:成功3条,失败0条!",
    "code": 200,
    "data": 3
}
```



------

#### 27. (GET) http://localhost:88/api/administration/user-operate/user-traffic

#### 接口描述：查询用户流量列表

#### 需要权限：root

#### 是否为请求体：否

#### 资源范围：ROLE_ADMIN

| Key  |  Value  |     描述     |
| :--: | :-----: | :----------: |
| page | Integer |     页码     |
| size | Integer |   每页个数   |
| like | String  | 模糊查询字段 |

```json
//数值示例：http://localhost:88/api/administration/user-operate/user-traffic?page=1&size=2&like=湖南
//返回结果：
{
    "msg": "success",
    "code": 200,
    "data": {
        "records": [
            {
                "id": 4,
                "ipAddress": "12.252.44.11",
                "region": "湖南长沙",
                "visitTime": "2020-12-25 14:52:59"
            }
        ],
        "total": 1,
        "size": 2,
        "current": 1,
        "orders": [],
        "optimizeCountSql": true,
        "hitCount": false,
        "countId": null,
        "maxLimit": null,
        "searchCount": true,
        "pages": 1
    }
}
```



##### 注：like为模糊查询字端，若like:“ ”则表示不需要模糊查询，若like:"r"则查询包含r的词条



------

#### 28. (GET) http://localhost:88/api/operate/user-data/sms/sendCode

#### 接口描述：发送验证码

#### 是否为请求体：否

#### 资源范围：ROLE_USER

|  Key   | Value  |   描述   |
| :----: | :----: | :------: |
| Number | String | 手机号码 |

```json
{
    "msg": "验证码发送成功",
    "code": 200,
    "data": true
}
```



------

#### 29. (PUT) http://localhost:88/api/operate/user-data/registration

#### 接口描述：用户注册

#### 是否为请求体：是

#### 资源范围：ROLE_USER

|    Key     | Value  |   描述   |
| :--------: | :----: | :------: |
|   phone    | String | 手机号码 |
|  password  | String |   密码   |
| verifyCode | String |  验证码  |

```json
//数值示例：
{   
    "phone": "13300425877",
    "password": "123456",
    "verifyCode": "121373"
}
//返回结果：
{
    "msg": "用户注册成功",
    "code": 200,
    "data": 1
}
```



------

#### 30. (POST) http://localhost:88/api/operate/user-data/update

#### 接口描述：用户信息修改

#### 是否为请求体：是

#### 资源范围：ROLE_USER

|    Key     | Value  |   描述   |
| :--------: | :----: | :------: |
|   phone    | String | 手机号码 |
|  password  | String |   密码   |
| verifyCode | String |  验证码  |

```json
//数值示例：
{   
    "phone": "13300425877",
    "password": "123456",
    "verifyCode": "862527"
}
//返回结果：
{
    "msg": "用户信息重置成功",
    "code": 200,
    "data": 1
}
```



------

#### 31. (POST) http://localhost:88/api/operate/user-data/login

#### 接口描述：用户登录

#### 是否为请求体：是

#### 资源范围：ROLE_USER

|   Key    | Value  |   描述   |
| :------: | :----: | :------: |
|  phone   | String | 手机号码 |
| password | String |   密码   |

```json
//数值示例：
{   
    "phone": "13300425877",
    "password": "123456"
}
//返回结果：
{
    "msg": "success",
    "code": 200,
    "data": {
        "phone": "13300425877",
        "userCode": "de172b11-8367-426d-bfff-b6cf53b623df",
        "success": 1
    }
}
```



------

#### 32. (PUT) http://localhost:88/api/operate/user-commit/save

#### 接口描述：保存用户提交的测评信息

#### 是否为请求体：是

#### 资源范围：ROLE_USER

|   Key    | Value  |   描述   |
| :------: | :----: | :------: |
|   name   | String |   姓名   |
|  phone   | String | 电话号码 |
| graduate | String |   学历   |
|   age    | String |   年龄   |
|   goal   | String |   目的   |

```json
//数值示例：
{   
    "name": "cxk",
    "phone": "13300425877",
    "graduate": "本科",
    "age": "30-40",
    "goal": "提升学历"
}
//返回结果：
{
    "msg": "提交成功,我们会及时联系您",
    "code": 200,
    "data": 1
}
```



------

#### 33. (PUT) http://localhost:88/api/operate/user-forecast-name/save

#### 接口描述：保存用户提交的预报名信息

#### 是否为请求体：是

#### 资源范围：ROLE_USER

|  Key  | Value  |   描述   |
| :---: | :----: | :------: |
| name  | String |   姓名   |
| phone | String | 电话号码 |

```json
//数值示例：
{   
    "name": "cxk",
    "phone": "13300425877"
}
//返回结果：
{
    "msg": "预报名成功,我们会及时联系您",
    "code": 200,
    "data": 1
}
```



------

#### 34. (GET) http://localhost:88/api/operate/registration-information/oss/token

#### 接口描述：获取照片对象存储token，需要用户登录后方可获取

#### 是否为请求体：否

#### 资源范围：ROLE_USER

|  Key  | Value  |   描述   |
| :---: | :----: | :------: |
| phone | String | 电话号码 |
| code  | String |  用户码  |

```json
//数值示例：http://localhost:88/api/operate/registration-information/oss/token?phone=13300425877&code=de172b11-8367-426d-bfff-b6cf53b623df
//返回结果：
{"credentials":{"tmpSecretId":"AKIDQHTph59xZblrofbYZl8VjytOOlNz0xXZrR2TeLJMk3fe3TOLWulE6ZF-XlpUXy3Y","tmpSecretKey":"mVrhVbvT9k6NJSLEceXB3QE4EDOfoKf3iV7fJ7E5Jw4=","sessionToken":"JMHrE5Llv4fJD2HBdllNUU1KGkU3gwNa29787df00cfb6ce02f530c9560bc02c0HxuguyDiPwIoInkfWGTsX8vQ1KxobkM7fSdpBNRWpCZqbA--sMtFx5aVZ6QWQvVbiOQVkCAvgRX2vCasaJsygLuPTyc9tHqxtELxla2tZB8Ip1Ep7vMu0kN6VzhIPF8nj0CM0fGqMAqKGFQL9IHQ3rFJC3wiL8dBlJannlPdk6xt5zmmZtLWw67JeRXXfTAaW0uJbj7XfHrmea903pMGI_2CmDkquLMV8_u7zfoXLTwRImLIMRQ8XWBuRUxdpewE9MGSatxFZ_yxxyGPTuMnH3URyt5D5qREsr8OIIhz25x0Ish-pwQmafYA62WTicUlNjvfQDAivY43v0IAFX113c-AF9zorQhZ0X-F9nJ1i38"},
 "requestId":"78f6adbb-8d49-4746-877b-130af346def4",
 "expiration":"2020-12-25T08:14:34Z",
 "startTime":1608882274,
 "expiredTime":1608884074
}
```



------

#### 35. (PUT) http://localhost:88/api/operate/registration-information/website/registration

#### 接口描述：网站用户报名

#### 是否为请求体：是

#### 资源范围：ROLE_USER

|       Key        | Value  |       描述       |
| :--------------: | :----: | :--------------: |
|       name       | String |       姓名       |
|      gender      | String | 性别{"男"，"女"} |
|  identityNumber  | String |     身份证号     |
|      phone       | String |      手机号      |
| certificateName  | String |    报考证书名    |
| certificateGrade | String |   报考证书等级   |
|      photo       | String |   照片URL地址    |
|     userCode     | String |      用户码      |
|    userPhone     | String |      用户名      |

```json
//数值示例：
{   
    "name": "cxk",
    "gender": "男",
    "identityNumber": "130425********2057",
    "phone": "13655885574",
    "certificateName": "大数据资产规划师",
    "certificateGrade": "中级",
    "photo": "http://www.test.com",
    "userCode": "de172b11-8367-426d-bfff-b6cf53b623df",
    "userPhone": "13300425877"
}
//返回结果：
{
    "msg": "报名成功",
    "code": 200,
    "data": 1
}
```



------

#### 36. (GET) http://localhost:88/api/traffic/certificate-info/list

#### 接口描述：获取所有证书列表

#### 是否为请求体：否

#### 资源范围：ROLE_API

```json
{
    "msg": "success",
    "code": 200,
    "data": [
        {
            "id": 1,
            "certificateName": "大数据资产规划师",
            "certificateGrade": "初级",
            "price": 999.99,
            "removeState": 1
        },
        {
            "id": 2,
            "certificateName": "大数据资产规划师",
            "certificateGrade": "中级",
            "price": 1999.99,
            "removeState": 1
        },
        {
            "id": 3,
            "certificateName": "大数据资产规划师",
            "certificateGrade": "高级",
            "price": 2999.99,
            "removeState": 1
        },
        {
            "id": 4,
            "certificateName": "大数据分析师",
            "certificateGrade": "初级",
            "price": 999.99,
            "removeState": 1
        },
        {
            "id": 5,
            "certificateName": "大数据分析师",
            "certificateGrade": "中级",
            "price": 1999.99,
            "removeState": 1
        },
        {
            "id": 6,
            "certificateName": "大数据分析师",
            "certificateGrade": "高级",
            "price": 2999.99,
            "removeState": 1
        }
    ]
}
```





------

#### 37. (GET) http://localhost:88/api/traffic/lecture-info/list

#### 接口描述：获取所有讲师信息列表

#### 是否为请求体：否

#### 资源范围：ROLE_API

```json
{
    "msg": "查询成功",
    "code": 200,
    "data": [
        {
            "id": 1,
            "name": "司亚清",
            "description": "北京邮电大学经济管理学院副教授，硕导，数据资产管理研究中心主任，区块链技术与应用研究中心常务副主任。",
            "researchOrientation": "研究方向：企业管理的信息化，大数据工程与治理、数据市场治理，智能决策，区块链，科研教学。",
            "mark": null,
            "photo": null,
            "removeState": 1
        },
        {
            "id": 2,
            "name": "苏静",
            "description": "北京邮电大学经济管理学院副教授，法学及管理学双硕士。任北京邮电大学经济管理学院《数据资产管理研究中心》副主任。",
            "researchOrientation": "研究方向：法学及管理，数据资产管理，法律风险识别、科研教学。",
            "mark": null,
            "photo": null,
            "removeState": 1
        },
        {
            "id": 3,
            "name": "赵启阳",
            "description": "北京航空航天大学计算机学院讲师，工学博士，硕士生导师。中国计算机学会NOI 科学委员会副主席。",
            "researchOrientation": "研究方向：研究方向：深度学习、计算机视觉、数据产权界定和安全。",
            "mark": null,
            "photo": null,
            "removeState": 1
        }
    ]
}
```



------

#### 38. (PUT) http://localhost:88/api/traffic/user-traffic/add

#### 接口描述：用户流量信息添加

#### 是否为请求体：是

#### 资源范围：ROLE_API

|    Key    | Value  |  描述  |
| :-------: | :----: | :----: |
| ipAddress | String | IP地址 |
|  region   | String |  地区  |

```json
//数值示例：
{   
    "ipAddress": "19.56.23.75",
    "region": "湖北武汉"
}
//返回结果：
{
    "msg": "用户流量添加成功",
    "code": 200,
    "data": 1
}
```

