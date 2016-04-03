<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/admin_main.css">
<title>管理者メイン</title>
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
				perPage : 10, //1ページに表示する個数
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
			<li><s:a href="PurchaseAction">サイトへ移動</s:a></li>
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


		<br> <br>

		<s:form action="AdminDeleteItemAction">
			<table class="admin_delete_table">
				<tr>
					<th><h4>＜商品削除＞</h4></th>
				</tr>
				<tr>
					<td>削除したい商品IDを入れてください</td>
				</tr>
				<tr>
					<td><s:textfield name="itemId" /></td>
				</tr>
				<tr>
					<td><button class="button">削除</button></td>
				</tr>
			</table>
		</s:form>
		<br> <br> <br>
		<s:iterator value="message">
			<font color="red"><s:property value="message" /></font>
		</s:iterator>
		<br>
		<table class="admin_cart">
			<tr>
				<th>商品ID</th>
				<th>商品名</th>
				<th>値段</th>
				<th>商品説明</th>
				<th>商品画像パス</th>
			</tr>

			<!-- メインここから -->
			<tbody id="CList">
				<s:iterator value="itemList">
					<tr>
						<td><s:property value="itemId" /></td>
						<td><s:property value="itemName" /></td>
						<td><s:property value="price" /></td>
						<td><s:property value="explanation" /></td>
						<td><s:property value="imgPath" /></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<div class="List"></div>
	</div>
</body>
</html>