
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/style.css">
<title>管理者ログイン</title>
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
			<img src="./img/keyvisual.jpg" width="900" height="362" alt="">
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

		<!-- 管理者とパスワードのログイン==================================== -->

		<s:form action="AdminLoginAction">
			<h2>管理者ログイン</h2>
			<p>
				管理者名<br>
				<s:textfield name="adminName" />
			</p>
			<p>
				パスワード<br>
				<s:password label="パスワード" name="password" />
			</p>
			<button class="button">ログイン</button>
		</s:form>
		<br>
	</div>
</body>
</html>