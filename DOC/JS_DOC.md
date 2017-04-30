# JavaScript 相关说明
对象：[strange.js](../src/main/webapp/res/strange.js)

### 通用部分
定义了一个简易的Map工具，方法签名类似Java，拥有 get(key)，put(key,value)，entrySet()，remove(key) 方法。

### 关于登陆
0. ready(fn)绑定  

    遮罩层绑定关闭登录窗口控制器。  
    登陆按钮绑定登录窗口显示控制器。  
    登录窗口登陆按钮绑定表单验证方法。  
    用户名及密码输入框绑定各自的验证方法。  
    绑定页面背景图片。  
    
0. keydown(fn)绑定  

    ESC（27）键按下事件触发时关闭遮罩层及登陆窗。  
    
0. resize(fn)绑定  

    浏览器窗口分辨率变化事件触发时改变登陆窗口位置。  
    
0. 登陆用工具类  

    关于登陆的JS统一在LogonUtil类中。  
    类变量：  
    * 短句定义  
    * flag集，MapUtil对象  
    * flag名定义  
    * 表单验证相关正则表达式定义  
    * 表单验证错误信息定义  
    * 表单验证状态定义  
    * Cookies操作用变量定义  
    
1. 登录窗口的显示控制  

    登陆窗口。  
    * showLogon()方法    
    * 显示登录窗口    
    * 取出cookies  
    * （二回打开之后）  
    * 抑制上回状态显示  
    * 抑制上回错误信息显示   
    
2. 登录窗口短句更新器  

    窗口短句。  
    * updateSentence()方法  
    登录窗口显示时，表单验证错误状态变为正确状态等时对登录窗口上方显示的信息进行随机更新。  
    
3. 操作cookies

    操作cookies，get和set。  
    * getCookie(key)获取cookie  
    * setCookie(key,value,expiredays)设定cookie  
    
4. 表单验证

    登陆界面输入框验证和反应。  
    * 统和在execute()方法执行。
    * checkUsername()用户名验证，标flag  
    * checkPassword()密码验证，标flag  
    * checkServer()本地验证通过，交服务器验证正确性，标flag  
    * 判flag，改变输入框状态，提示错误    
    * 通过验证时，判保存密码checkbox状态，操作cookies
    
5. 登陆用JS详细

    1. 关于checkUsername()方法  
        * 非空  
        * 字母开头  
        * 位数5-20之间，/^\w{5,20}$/  
    1. 关于checkPassword()方法
        * 非空  
        * 位数6-18之间，/^\w{6,18}$/  
    2. 关于checkServer()方法
        * 本地验证通过的前提下，交服务器认证
        * 接收结果并显示（正确：绿字跳转；错误：红字报错）
    3. 以上验证都会对flag集进行操作，根据flag集做如下操作
        * flag.get(FLAG_SERVER) 首先判断服务器信息，服务器报信息时去掉输入框颜色
        * flag.get(ID_USERNAME) 其次判断用户名报错，在没提交服务器认证时用户名信息优先  
        * flag.get(ID_PASSWORD) 最后判断密码报错，本地验证用户名无报错时才报密码错  
        * （用户名和密码报错相应会更改输入框状态颜色，只有服务器认证通过才出绿字）  
    4. onBlur()方法  
        * 用户名和密码输入框绑定onBlur()方法，失焦即刻验证表单  
        * 失焦timing使用登录按钮相同验证方法，分别绑定到用户名和密码框  
    5. createCookie()方法
        * 本地+服务器认证通过时
        * 保存密码checkbox选中时
        * 保存当前用户名密码和checkbox选中状态进入cookie
        * 保存密码checkbox未选中时
        * 清除cookie
    
更新中……  



