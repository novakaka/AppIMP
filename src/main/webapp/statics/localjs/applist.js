	//监听对象的改变
$("#queryCategoryLevel1").change(function(){
	//获取参数
	var queryCategoryLevel1 = $("#queryCategoryLevel1").val();
	//非空判断
	if(queryCategoryLevel1 != '' && queryCategoryLevel1 != null){
		//Ajax 发送异步请求
		$.ajax({
			type:"GET",//请求类型
			url:"categorylevellist.json",//请求的url
			data:{pid:queryCategoryLevel1},//请求参数
			dataType:"json",//ajax接口（请求url）返回的数据类型
			success:function(data){//data：返回数据（json对象）
				$("#queryCategoryLevel2").html("");
				var options = "<option value=\"\">--请选择--</option>";
				for(var i = 0; i < data.length; i++){
					//根据ID显示名称
					options += "<option value=\""+data[i].id+"\">"+data[i].categoryName+"</option>";
				}
				//赋值
				$("#queryCategoryLevel2").html(options);
			},
			error:function(data){//当访问时候，404，500 等非200的错误状态码
				alert("加载二级分类失败！");
			}
		});
	}else{
		//清空 二三级列表
		$("#queryCategoryLevel2").html("");
		var options = "<option value=\"\">--请选择--</option>";
		$("#queryCategoryLevel2").html(options);
	}
	$("#queryCategoryLevel3").html("");
	var options = "<option value=\"\">--请选择--</option>";
	$("#queryCategoryLevel3").html(options);
});

$("#queryCategoryLevel2").change(function(){
	var queryCategoryLevel2 = $("#queryCategoryLevel2").val();
	if(queryCategoryLevel2 != '' && queryCategoryLevel2 != null){
		$.ajax({
			type:"GET",//请求类型
			url:"categorylevellist.json",//请求的url
			data:{pid:queryCategoryLevel2},//请求参数
			dataType:"json",//ajax接口（请求url）返回的数据类型
			success:function(data){//data：返回数据（json对象）
				$("#queryCategoryLevel3").html("");
				var options = "<option value=\"\">--请选择--</option>";
				for(var i = 0; i < data.length; i++){
					//alert(data[i].id);
					//alert(data[i].categoryName);
					options += "<option value=\""+data[i].id+"\">"+data[i].categoryName+"</option>";
				}
				$("#queryCategoryLevel3").html(options);
			},
			error:function(data){//当访问时候，404，500 等非200的错误状态码
				alert("加载三级分类失败！");
			}
		});
	}else{
		$("#queryCategoryLevel3").html("");
		var options = "<option value=\"\">--请选择--</option>";
		$("#queryCategoryLevel3").html(options);
	}
});

$(".checkApp").on("click",function(){
	var obj = $(this);
	var status = obj.attr("status");
	var vid = obj.attr("versionid");
	if(status == "1" && vid != "" && vid != null){//待审核状态下才可以进行审核操作
		window.location.href="check?aid="+ obj.attr("appinfoid") + "&vid="+ obj.attr("versionid");
	}else if(vid != "" || vid != null){
		alert("该APP应用没有上传最新版本,不能进行审核操作！");
	}else if(status != "1"){
		alert("该APP应用的状态为：【"+obj.attr("statusname")+"】,不能进行审核操作！");
	}
});



	
