# Strange

### 建站练习

> [DEMO展示](https://critsu.github.io/Strange/src/main/webapp/home.html)  
完成度暂时只有首页框架及登录验证相关。

* JAVA
* HTML
    * JS（JQ）
    * CSS

工具：Eclipse

框架
* bootstrap

阶段
* HTML DEMO制作


HTML头部导入顺序
```html
<link rel="stylesheet" type="text/css" href="res/reset.css">
<link rel="stylesheet" type="text/css" href="res/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="res/css/bootstrap-theme.css">
<link rel="stylesheet" type="text/css" href="res/strange.css">
<script type="text/javascript" src="res/jquery-3.1.1.js"></script>
<script type="text/javascript" src="res/js/bootstrap.js"></script>
<script type="text/javascript" src="res/strange.js"></script>
```

### 说明传送门
* [JS部分说明](DOC/JS_DOC.md)

### 碎碎念
> ...eclipse之前的弄的乱七八糟一直报错，故重新装了一遍，弄颜色弄了半天。  
    总结经验：自带的黑色主题和color插件不能同时保存，自带主题会覆盖插件的主题。  
    解决方法：去color插件官网下载epf格式的主题，eclipse切到黑色主题后导入epf配置即可生效。  

>   Github Pages 不允许post方法，所以DEMO的登陆按钮会跳405页面去，真是悲伤...

### 资料和一些备忘
* [pushState 和 ajax](dummy)  

这里打算用pushState和ajax实现无刷单页网站。  
（这里是个假连接，等有时间整理出来 TODO ）