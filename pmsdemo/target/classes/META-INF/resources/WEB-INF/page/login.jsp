<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>用户统一登录</title>
    <link href="${pageContext.request.contextPath}/statics/dwz/themes/css/login.css" rel="stylesheet" type="text/css"/>
    <style type="text/css">
        .info {
            font-size: 12px;
            color: red;
            margin-left: 80px;
        }
    </style>
</head>
<body>
<div id="login">
    <div id="login_header">
        <h1 class="login_logo">
            <!-- <a href="#"><img src="statics/dwz/themes/default/images/login_logo.gif" /></a> -->
            <a style="font-size: 25px">管理后台</a>
        </h1>
        <div class="login_headerContent">
            <div class="navList">
                <ul>
                    <li><a href="#" target="_blank"></a></li>
                </ul>
            </div>
            <h2 class="login_title" style="font-size: 16px">用户登录</h2>
        </div>
    </div>
    <div id="login_content">
        <div class="loginForm">
            <form action="${pageContext.request.contextPath}/login" method="post">
                <p>
                    <label>用户名：</label> <input name="name" value="admin" type="text" style="width: 140px; height: 20px;" class="login_input"/> <br/>
                </p>
                <p>
                    <label>密&nbsp;&nbsp;&nbsp;码：</label> <input name="pwd" type="password" style="width: 140px; height: 20px;" class="login_input" value="123456"/> <br/>
                </p>
                <p>
                    <br/> <span class="info">${msg}</span>
                </p>
                <div class="login_bar" style="margin-left: 10px;">
                    <input class="sub" type="submit" value=""/>
                </div>
            </form>
        </div>
        <div class="login_banner">
            <img src="${pageContext.request.contextPath}/statics/dwz/themes/default/images/login_banner.jpg"/>
        </div>
        <div class="login_main">
            <ul class="helpList">
                <li><a href="#">测试帐号：admin</a></li>
                <li><a href="#">默认密码：123456</a></li>
            </ul>
        </div>
    </div>
    <div id="login_footer">Copyright &copy; 2017 JKOSS</div>
</div>
</body>
</html>