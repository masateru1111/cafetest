<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link rel="stylesheet" type="text/css" href="./css/admin_add_item.css">
<link rel="stylesheet" type="text/css" href="./css/style.css">
<title>商品追加</title>
</head>
<body>
	<!-- 管理者ヘッダー -->
	<%
	    boolean loginState = false;
	    if (session.getAttribute("admin") == null) {
	        loginState = false;
	    } else {
	        loginState = true;
	    }
	%>


	<div id="backimg">
		<h1 class="top">IWATA CAFE</h1>
		<div id="logo">
			<s:a href="AdminItemsAction"><img src="./img/keyvisual.jpg" width="900" height="362" alt=""></s:a>
		</div>

		<%
		    if (loginState == true) {
		%>
		<!-- ログイン時 -->
		<ul id="nav">
			<li><s:a href="AdminItemsAction">商品リスト</s:a></li>
			<li><s:a href="AdminUserAction">ユーザーリスト</s:a></li>
			<li><s:a href="admin_add_item.jsp">商品追加</s:a></li>
			<li><s:a href="AdminLogoutAction">ログアウト</s:a></li>
		</ul>
		<%
		    } else {
		%>
		<ul id="nav">
			<!-- ログアウト時 -->
			<li><s:a href="admin_login.jsp">ログイン</s:a></li>

		</ul>
		<%
		    }
		%>

		<!-- ヘッダーここまで -->


		<s:form action="AdminAddItemsAction">
			<br>
			<s:iterator value="cardErrorMessage">
				<s:property value="cardErrorMessage" />
			</s:iterator>
			<h2>商品追加</h2>
			<div class="form">
				<s:iterator value="cardErrorMessage">
					<s:property value="cardErrorMessage" />
				</s:iterator>
				<br> <br>
				<table class="add_item">
					<tr>
						<td>※⑴〜⑷を入力してください<br><br></td>
					</tr>
					<tr>
						<td>⑴　商品名</td>
					</tr>
					<tr>
						<td><s:textfield name="itemName"  /></td>
					</tr>
					<tr>
						<td>⑵　値段</td>
					</tr>
					<tr>
						<td><s:textfield name="price"/></td>
					</tr>
					<tr>
						<td>⑶　商品説明</td>
					</tr>
					<tr>
						<td><s:textarea name="explanation" class="text1" /></td>
					</tr>
					<tr>
						<td>⑷　画像パス</td>
					</tr>
					<tr>
						<td><s:textfield name="imgPath" class="text2" /></td>
					</tr>

				</table>
			</div>
			<button class="button">作成</button>

		</s:form>
	</div>
</body>
</html>
