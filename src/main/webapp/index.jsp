<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	//创建异步对象
	function createXMLHttpRequest() {
		try {
			return new XMLHttpRequest();//大多数浏览器
		} catch (e) {
			try {
				return ActvieXObject("Msxml2.XMLHTTP");//IE6.0
			} catch (e) {
				try {
					return ActvieXObject("Microsoft.XMLHTTP");//IE5.5及更早版本   
				} catch (e) {
					alert("哥们儿，您用的是什么浏览器啊？");
					throw e;
				}
			}
		}
	}

	function doSubmit() {
		var xmlHttp = createXMLHttpRequest();
		var info = $('#info').val();
		/************修改open方法，指定请求方式为POST**************/
		xmlHttp.open("post", "/chat/chatServlet", true);
		/************设置请求头：Content-Type************/
		xmlHttp.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		/*
		3. 发送请求
		 */
		/**********发送时指定请求体***********/
		xmlHttp.send("info=" + info);//GET请求没有请求体，但也要给出null，不然FireFox可能会不能发送！
		/*
		4. 给异步对象的onreadystatechange事件注册监听器
		 */
		xmlHttp.onreadystatechange = function() {//当xmlHttp的状态发生变化时执行
			// 双重判断：xmlHttp的状态为4（服务器响应结束），以及服务器响应的状态码为200（响应成功）
			if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				// 获取服务器的响应结束
				var text = xmlHttp.responseText;
				// 获取h1元素
				if (text != null) {
					document.getElementById("res").innerText =text;
				} else {
					document.getElementById("res").innerText = '无返回值';
				}
			}
		};
	};
</script>
</head>
<body>
<body>
	<textarea rows="5" cols="" placeholder="请输入内容" name="info" id="info"></textarea>
	<br>
	<input type="button" onclick="doSubmit()" value="提交">
	<br>
	<label id="res"></label>
</body>
</body>

</html>