$("#apkName").bind("blur",function () {
    //进行后台验证
    $.ajax({
        type:"get",
        url:"apkExist",
        date:{apkName:$("#APKName").val()},
        dateType:"json",
        success:function (data) {
            if(date.apkName=="empty"){alert("apk名称不能为空")}
            else if(date.apkName=="exist"){alert("apk名称已经存在")}
            else if(date.apkName=="notExist"){alert("apk可以使用")};
        },
        error:function (data) {
            alert("请求错误")

        }
    })
})