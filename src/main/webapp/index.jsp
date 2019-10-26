<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <!-- Bootstrap -->
    <link href="statics/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="statics/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="statics/css/nprogress.css" rel="stylesheet">
    <!-- Animate.css -->
    <link href="statics/css/animate.min.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="statics/css/custom.min.css" rel="stylesheet">
</head>

<body class="login">
<div>
    <a class="hiddenanchor" id="signup"></a>
    <a class="hiddenanchor" id="signin"></a>

    <div class="login_wrapper">
        <div class="animate form login_form">
            <section class="login_content">
                <form>
                    <h1>app 信息管理系统</h1>
                </form>
            </section>
            <section class="login_content">
                <form>
                    <div>
                        <a class="btn btn-default" href="index.html">后台管理系统入口</a>
                        <a class="btn btn-default" href="${pageContext.request.contextPath}/dev/login.do">开发者平台入口</a>
                    </div>
                </form>
            </section>
        </div>




    </div>
</div>
</body>
</html>
