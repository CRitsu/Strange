/*
    Mr.Strange's Order.
*/


/*
 * Map集合工具
 */
var MapUtil = function(){
    // 数组管理keys
    var keys = new Array();
    // 用JSON管理vlues
    var data = new Object();
    
    /*
     * put 方法
     * 插入键值对，更新键值对
     * 返还Map对象
     */
    this.put = function(key,value){
        var temp = $.inArray(key,keys);
        if (temp == -1) keys.push(key);
        data[key] = value;
        return this;
    }
    
    /*
     * get 方法
     * 获得指定key的value值
     */
    this.get = function(key){
        return data[key];
    }
    
    /*
     * remove 方法
     * 如果存在该key则删除，不存在不作处理
     * 返回Map对象
     */
    this.remove = function(key){
        var temp = $.inArray(key,keys);
        if (temp == -1) return this;
        keys.splice(temp,1);
        data[key] = null;
        return this;
    }
    
    /*
     * entrySet 方法
     * 以JSON格式获得所有键值对，同时刷新data对象，去除null值
     */
    this.entrySet = function(){
        var temp = new Object();
        for (var i = 0; i < keys.length; i++){
            temp[keys[i]] = data[keys[i]];
        }
        data = temp;
        return data;
    }
}

/*
 * 登陆用工具集对象定义
 */
var LogonUtil = function(){
    // 句子
    var sentence = Array(
            "看吧，星星只有在夜里才璀璨夺目啊。",
            "人啊，总是盲目地去爱啊……",
            "我并不能一直在你身边帮助你。",
            "和她相遇的那瞬间，我的人生改变了。",
            "继续踏上旅途，在没有你的春天。",
            "星星只有在夜里才璀璨夺目啊。",
            "我能想起来的一直都是你的背影。",
            "欸嘿嘿。",
            "哎呀，疼！",
            "别减肥了，你丑不仅是因为胖。",
            "多照照镜子，很多事情你就明白原因了。",
            "低头吧，你根本就没有皇冠。",
            "一提到钱，大家就不是那么亲热了。",
            "别低估任何人。",
            "你没那么多观众，别那么累。",
            "尽量不要讲同事朋友的八卦。",
            "懂得倾听别人的忠告。",
            "即使现在，对手也不停地翻动书页。",
            "只要是石头，到哪里都不会发光。",
            "善良没用，你得漂亮。",
            "人作的程度不能超过自己的颜值。",
            "为什么总是天妒英才呢？",
            "世上无难事，只要肯放弃。",
            "别说自己是单身狗，狗还可以三妻四妾。",
            "转角一般不会遇到爱，只会遇到乞丐。",
            "你并不是一无所有，你还有病啊！",
            "虽然你长得丑，但是你想得美啊！",
            "别人都有背景，而你只有背影。",
            "你努力做到最好，还不如别人随便搞搞。",
            "没有你想不到，只有你做不到…",
            "只能帮你到这了朋友。",
            "吃得苦中苦，心里会更堵。",
            "你不优秀，认识谁都没用。",
            "妹萌根硕。",
            "条条大路通罗马，而有些人就生在罗马。",
            "是金子到哪里都发光，可是你是石头啊。",
            "万事开头难，然后中间难，最后结束难。",
            "努力不一定成功，但不努力一定很轻松。",
            "加油，你是最差的。",
            "一鼓作气，再而卒。",
            "加油，你是最胖的。",
            "比三观更重要的是五官。",
            "谢谢那些击倒我的人，躺着真舒服。",
            "一觉醒来，是不是感觉离梦想又远了呢？",
            "人生就是起起落落落落落落落落的过程。",
            "没有钱包的充实，哪来内心的宁静。",
            "等忙完这一阵，就可以接着忙下一阵了。",
            "有时不努力下，你都不知道什么叫绝望。",
            "只要你肯努力，没有什么是不能搞砸的。",
            "谁说你不会乐器，你退堂鼓打的可好了！",
            "各位壮士，请干了这碗鸡汤！",
            "不爱你的人，比你想象中还不爱你。",
            "隐藏物品一般藏在箭头相反的方向。",
            "天气真好。"
    );
    
    // 表单提示状态，输入框颜色及图标
    var FEEDBACK_SUCCESS = "has-success";
    var FEEDBACK_ERROR = "has-error";
    var ERROR_ICON = "glyphicon-remove";
    var SUCCESS_ICON = "glyphicon-ok";
    
    // 错误提示内容
    // 用户名错误内容
    var ERROR_USERNAME_BEGIN_WITH_LETTER = "用户名需以字母开头！";
    var ERROR_USERNAME_LENGTH = "用户名长度需要在5位到20位之间！";
    var ERROR_USERNAME_ILLEGAL = "用户名只能以字母、数字、下划线构成！";
    var ERROR_USERNAME_UNDEFINED = "用户名未输入！";
    var ERROR_USERNAME_UNAVAILABLE = "用户名被占用！"; // 注册界面，服务器返回
    // 密码错误内容
    var ERROR_PASSWORD_LENGTH = "密码长度需要在6位到18位之间！";
    var ERROR_PASSWORD_ILLEGAL = "密码不可以包含空格！";
    var ERROR_PASSWORD_CONFIRM = "两次密码不一致！";
    var ERROR_PASSWORD_UNDEFINED = "密码未输入！";
    // 服务器返回
    var ERROR_SERVER_FAILED = "用户名或密码不正确，请重试！";
    var SUCCESS_SERVER_SUCCESSED = "登陆成功！";
    // 文本颜色
    var COLOR_ERROR = "text-danger";
    var COLOR_SUCCESS = "text-success";
    
    // 表单验证，正则表达式定义
    // 检查用户名是否以字母开头
    var userCheck1 = /^[a-zA-Z]{1}.*/;
    // 检查用户名是否有特殊符号
    var userCheck2 = /^\w+$/;
    // 检查用户名长度（5-25）
    var userCheck3 = /^\w{5,20}$/;
    // 检查密码是否有空格
    var passCheck1 = /^[^\s]+$/;
    // 检查密码长度（6-18）
    var passCheck2 = /^\w{6,18}$/;
    
    // cookies操作相关
    // 方便的“=”
    var equal = "=";
    // 方便的“;”
    var semicolon = ";";
    // 方便的过期时间key名 
    var expiredays_equal = "expires=";
    // 日期对象 
    var date = new Date();
    
    
    /*
     * flag集
     * 1.管理FLAG
     * 2.存储错误内容
     */
    var flag = new MapUtil();
    // flag名定义 
    // 输入框着色flag 
    var FLAG_COLOR = "color";
    // 最优先 服务端报错
    var PRIORITY_SERVER = "msg_server";
    // 次优先 用户名JS报错
    var PRIORITY_USER = "msg_username";
    // 最后 密码JS报错
    var PRIORITY_PASS = "msg_pass";
    // id username
    var ID_USERNAME = "un";
    // id password
    var ID_PASSWORD = "pw";
    // flag 服务端报错
    var FLAG_SERVER = "flag_server";
    // bug-fix 重新显示登陆窗口时阻止显示上回错误信息 根据输入框状态显示错误
    var INPUT_STATES = "inpuf_states";
    // onsubmit用flag
    var SUBMIT = "submit";
    
    // 测试用 确认flag正确性 
    this.getFlag = function(){
        return flag;
    }
    
    
    /*
     * 随机更新句子显示 
     */
    this.updateSentence = function(){
        var i = parseInt(Math.random() * sentence.length);
        $("#sentence").html(sentence[i]);
    };
    
    /*
     * 表单状态颜色图标修改器 
     * @id html标签的ID 
     */
    this.setResultUtil = function(id){
        
        var result = flag.get(id);
        
        // 设置需要被移除的状态，无result则为清空flag
        var remove = (FEEDBACK_ERROR == result && FEEDBACK_SUCCESS)
                || (FEEDBACK_SUCCESS == result && FEEDBACK_ERROR) || "";
        // 设置需要被移除的图标，无则空
        var removeIcon = (FEEDBACK_ERROR == result && SUCCESS_ICON)
                || (FEEDBACK_SUCCESS == result && ERROR_ICON) || "";
        // remove 为空时清除OK和ERROR状态
        if (!remove) {
            $("#" + id).attr("class").indexOf(FEEDBACK_ERROR)
                    && $("#" + id).removeClass(FEEDBACK_ERROR);
            $("#" + id).attr("class").indexOf(FEEDBACK_SUCCESS)
                    && $("#" + id).removeClass(FEEDBACK_SUCCESS);
            $("#" + id + "-icon").attr("class").indexOf(ERROR_ICON)
                    && $("#" + id + "-icon").removeClass(ERROR_ICON);
            $("#" + id + "-icon").attr("class").indexOf(SUCCESS_ICON)
                    && $("#" + id + "-icon").removeClass(SUCCESS_ICON);
            // 清除所有OK和error状态之后，控制错误信息不显示（上回残留）
            flag.put(INPUT_STATES,false);
            return;
        }
        // 正常情况下取得对应的图标然后替换
        var icon = (FEEDBACK_ERROR == result && ERROR_ICON)
                || (FEEDBACK_SUCCESS == result && SUCCESS_ICON) || "";
        $("#" + id).attr("class").indexOf(remove)
                && $("#" + id).removeClass(remove);
        $("#" + id).addClass(result);
        $("#" + id + "-icon").attr("class").indexOf(removeIcon)
                && $("#" + id + "-icon").removeClass(removeIcon);
        $("#" + id + "-icon").addClass(icon);
        // OK或error存在，需要输出错误信息
        flag.put(INPUT_STATES,true);
    }
    /*
     * 修改器调用方法
     */
    this.setResult = function(){
        this.setResultUtil(ID_USERNAME);
        this.setResultUtil(ID_PASSWORD);
    }
    
    
    // 着色方法
    var setColor = function(){
        // 先去除一次着色
        if (flag.get(FLAG_COLOR)){
            $("#sentence").removeClass(COLOR_ERROR);
            $("#sentence").removeClass(COLOR_SUCCESS);
        }
        // 着色
        if (!!flag.get(FLAG_SERVER)) $("#sentence").addClass(COLOR_SUCCESS);
        else $("#sentence").addClass(COLOR_ERROR);
        flag.put(FLAG_COLOR,true);
    }
    /*
     * 错误信息内容更新器
     */
    this.setMsg = function(){
        // bug-fix 本地验证根据输入框状态判断，但是服务器信息优先不看输入框状态
        // OLD:根据输入框的状态判断，无状态时清除错误信息，阻止显示
        if (!flag.get(INPUT_STATES)){
            flag.remove(PRIORITY_USER);
            flag.remove(PRIORITY_PASS);
        }
        var temp;
        // 最优先判 服务端 
        temp = flag.get(PRIORITY_SERVER);
        // 第二判 用户名 
        if (!temp) temp = flag.get(PRIORITY_USER);
        // 三判 密码 
        if (!temp) temp = flag.get(PRIORITY_PASS);
        // 有错误信息时 
        if (!!temp){
            // 文本着色 
            setColor();
            // 置换信息 
            $('#sentence').html(temp);
        // 无错误信息时 
        } else {
            // 着色的场合
            if (flag.get(FLAG_COLOR)){
                // 去除着色
                $("#sentence").removeClass(COLOR_ERROR);
                $("#sentence").removeClass(COLOR_SUCCESS);
                flag.put(FLAG_COLOR,false);
                // 更新句子
                this.updateSentence();
            } // end if
        } // end if
    } // end function
    
    
    /*
     * 操作cookies
     */
    // 计算过期时间
    var countExpiredays = function(days){
        return days * 24 * 3600 * 1000;
    }
    // 设置cookie
    this.setCookie = function(key,value,expiredays){
        date.setTime(date.getTime() + countExpiredays(expiredays));
        document.cookie = key + equal + escape(value) + semicolon + expiredays_equal 
            + date.toGMTString();
    };
    // 从cookie中获取值
    this.getCookie = function(key){
        var cookies = document.cookie;
        var c_start = cookies.indexOf(key) + key.length + 1;
        if(!c_start) return "";
        var c_end = cookies.indexOf(";",c_start);
        c_end = c_end != -1 ? c_end : cookies.length;
        return unescape(cookies.substring(c_start,c_end));
    };
    // 创建cookie
    this.createCookie = function(){
        if($("#hold").prop("checked")){
            this.setCookie("username", $("#username1").val(), 30);
            this.setCookie("password", $("#password1").val(), 30);
            this.setCookie("checkbox", 1, 30);
        } else {
            this.setCookie("checkbox", 0, -1);
            this.setCookie("username", 0, -1);
            this.setCookie("password", 0, -1);
        }
    };
    
    // 重置登陆窗口位置 
    this.resizeLogin = function(){
        var w = $(window).width() / 2;
        var h = $(window).height() / 2;
        var lw = w - $("#login").width() / 2;
        var lh = h - $("#login").height() / 2;
        $("#login").css("left",lw > 0 ? lw : 0);
        $("#login").css("top",lh > 0 ? lh : 0);
    }
    
    // 点击登陆按钮显示登陆框和遮罩 
    this.showLogon = function() {
        this.resizeLogin();
        $("#login, #mask").removeClass("hide");
        this.updateSentence();
        // 设置cookie
        if(!!document.cookie){
            var c_checkbox = this.getCookie("checkbox");
            if(c_checkbox == 1){
                var c_username = this.getCookie("username");
                var c_password = this.getCookie("password");
                $("#username1").val(c_username);
                $("#password1").val(c_password);
                $("#hold").attr("checked",true);
            }
        }
        // 初始化登陆窗口
        flag.put(FLAG_SERVER,false);
        // 去除服务器的反馈信息
        flag.put(PRIORITY_SERVER,false);
        flag.put(ID_USERNAME,false);
        flag.put(ID_PASSWORD,false);
        this.setResult();
        this.setMsg();
    }
    
    // 关闭登陆窗口和遮罩
    this.closeLogon = function(){
        $("#username1").val("");
        $("#password1").val("");
        $("#login, #mask").addClass("hide");
    }
    
    // server验证
    this.checkServer = function(){
        
        // 暂时直接放行，服务器备好之后开始验证
        flag.put(FLAG_SERVER,true);
        
        // 设定 服务端获得结果，转换为JSON对象
        var result = {"flag_server":true}; // 测试
        // 因为服务器端来的错误信息需要覆盖本地验证错误信息，需要保证最后执行
        if (!!result){
            // 服务器使用flag_server标记结果状态
            if (!!result[FLAG_SERVER]){
                // 验证通过，登录成功
                flag.put(FLAG_SERVER,true);
                flag.put(PRIORITY_SERVER,SUCCESS_SERVER_SUCCESSED);
                // 这里对登录成功后的跳转做设定，暂定，可能会采用异步局部刷新的方式
                $("#f1").attr("action","test2.html");
            // 未通过验证
            } else {
                // 去除输入框状态
                flag.put(ID_USERNAME,false);
                flag.put(ID_PASSWORD,false);
                // 验证失败flag
                flag.put(FLAG_SERVER,false);
                // 储存错误信息
                flag.put(PRIORITY_SERVER,ERROR_SERVER_FAILED);
                // 清空密码
                $("#password1").val("");
            }
        }
        
        // TODO 服务器部分写好之后补充
    }
    
    // username验证 
    this.checkUsername = function(){
        // 获取输入的用户名
        var username = $("#username1").val();
        // 验证是否为空
        if(!username){
            flag.put(ID_USERNAME,FEEDBACK_ERROR);
            flag.put(PRIORITY_USER,ERROR_USERNAME_UNDEFINED);
            return;
        // 首字母check
        } else if(!userCheck1.test(username)){
            flag.put(ID_USERNAME,FEEDBACK_ERROR);
            flag.put(PRIORITY_USER,ERROR_USERNAME_BEGIN_WITH_LETTER);
            return;
        // 特殊字符check
        } else if (!userCheck2.test(username)) {
            flag.put(ID_USERNAME,FEEDBACK_ERROR);
            flag.put(PRIORITY_USER,ERROR_USERNAME_ILLEGAL);
            return;
        // 长度check
        } else if (!userCheck3.test(username)) {
            flag.put(ID_USERNAME,FEEDBACK_ERROR);
            flag.put(PRIORITY_USER,ERROR_USERNAME_LENGTH);
            return;
        // 符合条件的用户名
        } else {
            flag.put(ID_USERNAME,FEEDBACK_SUCCESS);
            flag.put(PRIORITY_USER,false);
        }
    };
    // password验证
    this.checkPassword = function(){
        // 获取输入的密码
        var password = $("#password1").val();
        // 验证是否为空
        if(!password){
            flag.put(ID_PASSWORD,FEEDBACK_ERROR);
            flag.put(PRIORITY_PASS,ERROR_PASSWORD_UNDEFINED);
            return;
        // 密码空格check
        }else if(!passCheck1.test(password)){
            flag.put(ID_PASSWORD,FEEDBACK_ERROR);
            flag.put(PRIORITY_PASS,ERROR_PASSWORD_ILLEGAL);
            return;
        // 密码长度check
        } else if(!passCheck2.test(password)){
            flag.put(ID_PASSWORD,FEEDBACK_ERROR);
            flag.put(PRIORITY_PASS,ERROR_PASSWORD_LENGTH);
            return;
        // 符合规则的密码
        } else {
            flag.put(ID_PASSWORD,FEEDBACK_SUCCESS);
            flag.put(PRIORITY_PASS,false);
        }
    };
    
    /*
     * 表单验证执行器
     */ 
    this.execute = function(){
        // 执行客户端check 
        this.checkUsername();
        this.checkPassword();
        // 客户端check通过时 
        if (FEEDBACK_SUCCESS == flag.get(ID_USERNAME) 
                && FEEDBACK_SUCCESS == flag.get(ID_PASSWORD)){
            // 执行服务端check 
            this.checkServer();
            // 服务端通过时
            if (!!flag.get(FLAG_SERVER)){
                flag.put(SUBMIT,true);
                this.createCookie();
            }
            // 未通过 
            else flag.put(SUBMIT,false);
        }
        // 显示结果 
        this.setResult();
        this.setMsg();
        if (flag.get(SUBMIT)){
            setTimeout(function(){
                $("#f1").submit();
            },1000);
        }
        return setTimeout(function(){
            return flag.get(SUBMIT);
        },1000);
    }
    /*
     * 输入框绑定方法，绑定给失焦事件
     */
    this.onBlur = function(name){
        // name 为空退出
        if (!name) return;
        // id = un 用户名输入框状态
        if ("un" == name){
            this.checkUsername();
        // id = pw 密码输入框状态
        } else if ("pw" == name){
            this.checkPassword();
        // 其他的场合 退出
        } else return;
        // 防止服务器信息显示，在onBlur方法应该阻止服务器信息
        flag.put(PRIORITY_SERVER,false);
        // 显示信息
        this.setResultUtil(name);
        this.setMsg();
    }

};



/* --------------------以上定义方法，以下调用方法-----------------------------  */

var logon = new LogonUtil();
/* 设定背景图片 */
var backName = "back-paper";
/*
 * 登陆窗口设定
 * 
 */

/* 绑定隐藏遮罩和登陆框的事件 */
$(document).ready(function(){
    $("html").addClass(backName);
    $("#close, #mask").on("click", function(){
        logon.closeLogon();
    })
    $("#logon_btn").on("click", function(){
        logon.showLogon();
    });
    $("#exec").on("click", function(){
        logon.execute();
    });
    $("#username1").on("blur", function(){
        logon.onBlur("un");
    });
    $("#password1").on("blur", function(){
        logon.onBlur("pw");
    });
})
$(document).keydown(function(e) {
    e.keyCode === 27 && $("#mask").attr("class").indexOf("show") !== -1 
        && $("#login, #mask").removeClass("show").addClass("hide");
})

/* resize时重新设定登陆窗口位置为居中 */
$(window).resize(function(){
    logon.resizeLogin();
})

