# Java-Shopping
>JavaWeb实习项目（实在没时间做，一晚上做完，做的挺垃圾，但是能用，而且个人感觉代码格式写的还行）
前端jsp使用了少量Bootstrap框架，利用了JSTL标签库和JQuery库。后台利用Servlet


### 项目结构解释：
#### web文件夹下:
* css 存放CSS文件
* img 存放项目用到的图片
* js 存放js文件
* WEB-INF 存放项目第三方jar包
* demo.jpg 示例图片
* index.jsp 首页页面
* login.jsp 登录页面
* productInfo.jsp 商品详情页面
* register.jsp 注册页面
* shoppingcar.jsp 购物车页面
* zoumadeng.gif 商城首页走马灯特效

#### resource文件夹:
* c3p0-config.xml c3p0配置，可以根据自己数据库调整
#### src文件夹
##### controller文件夹
* ProductsController 商品控制
* ShoppingCarController 购物车控制
* UserController 用户控制

> dao文件夹 对应数据库操作
> po文件夹 对应数据库模型
> service文件夹 中间服务层
> utils 常用基本操作
