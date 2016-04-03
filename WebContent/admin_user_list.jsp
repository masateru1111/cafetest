<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/admin_user_list.css">
<link rel="stylesheet" href="./css/style.css">
<title>ユーザー</title>
</head>
<body>
	<script type="text/javascript" src="./js/jquery-1.12.0.min.js"></script>
	<script type="text/javascript" src="./js/jPages.js"></script>
	<script>
		$(function() {
			$(".List").jPages({
				containerID : "CList",
				previous : "←", //前へのボタン
				next : "→", //次へのボタン
				perPage : 15, //1ページに表示する個数
				delay : 100, //要素間の表示速度
				animation : "-webkit-keyframes rotateIn" //アニメーションAnimate.cssを参考に
			});
		});
	</script>
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

		<h2>ユーザーリスト</h2>
		<br>
		<br> <br>
		<s:form action="AdminDeleteUserAction">
			<table class="admin_delete_table">
				<tr>
					<td>削除したいユーザーIDを入れてください</td>
				</tr>
				<tr>
					<td><s:textfield name="Id" /></td>
				</tr>
				<tr>
					<td><button class="button">削除</button></td>
				</tr>
			</table>
		</s:form>

		<s:iterator value="message">
			<font color="red"><s:property value="message" /></font>
		</s:iterator>

		<table class="user_list">
			<tr>
				<th>ID</th>
				<th>氏名</th>
				<th>フリガナ</th>
				<th>パスワード</th>
				<th>アドレス</th>
				<th>カード下４桁</th>
				<th>トークン</th>
				<th>登録日</th>
			</tr>
			<tbody id="CList">
				<s:iterator value="userList">
					<tr>
						<td><s:property value="id" /></td>
						<td><s:property value="userName" /></td>
						<td><s:property value="nameKana" /></td>
						<td><s:property value="password" /></td>
						<td><s:property value="email" /></td>
						<td><s:property value="cardNumber" /></td>
						<td><s:property value="token" /></td>
						<td><s:property value="registrationDate" /></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<div class="List"></div>
	</div>
</body>
</html>