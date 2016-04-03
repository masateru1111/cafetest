<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/purchase.css">
<link rel="stylesheet" href="./css/style.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.1/jquery.min.js"></script>
<title>ホーム</title>
</head>
<body>
	<!-- 画像関連jQuery -->
	<!-- ヘッダー -->
	<%
	    boolean loginState;
	    if (session.getAttribute("id") == null) {
	        loginState = false;
	    } else {
	        loginState = true;
	    }
	%>
	<div id="backimg">

		<h1 class="top">IWATA CAFE</h1>

		<div id="logo">
			<s:a href="PurchaseAction"><img src="./img/keyvisual.jpg" width="900" height="362" alt=""></s:a>
		</div>
		<%
		    if (loginState == true) {
		%>
		<s:iterator value="#session.user">
			<font color="maroon">ようこそ、<s:property value="#session.user" />
				様
			</font>
		</s:iterator>
		<%
		    } else {
		%>
		<font color="maroon">ようこそ、ゲスト様</font>

		<%
		    }
		%>
		<%
		    if (loginState == true) {
		%>
		<ul id="nav">
			<li><s:a href="PurchaseAction">ホーム</s:a></li>
			<li><s:a href="GoMypageAction">マイページ</s:a></li>
			<li><s:a href="contact.jsp">お問い合わせ</s:a></li>
			<li><s:a href="LogoutAction">ログアウト</s:a></li>
			<li><s:a href="withdrawal_check.jsp">退会</s:a></li>
			<li style="color: #f5f3eb"><s:a href="GoCartAction">カート<div
						id="cart_img">
						<img src="./img/cart_logo.png" width="25px" height="14px">
					</div>：<s:property value="sumOfItems" />
				個</s:a></li>
		</ul>
		<%
		    } else {
		%>
		<ul id="nav">
			<li><s:a href="PurchaseAction">ホーム</s:a></li>
			<li id="login-show">ログイン</li>
			<li><s:a href="create_user.jsp">新規登録</s:a></li>
			<li><s:a href="contact.jsp">お問い合わせ</s:a></li>
		</ul>
		<%
		    }
		%>

		<!-- ヘッダーここまで -->
		<!-- メイン -->
		<s:iterator value="message">
			<h3>
				<font color="#800000"><s:property value="message" /></font>
			</h3>
			<br>
			<br>
		</s:iterator>
		<!-- ログイン -->
		<div class="login-modal-wrapper" id="login-modal">
			<div class="modal">
				<s:form action="LoginTestAction">
					<h2>
						<img id="close" src="./img/クローズ.png" width="25px" height="25px">ようこそ
						IWATA CAFE へ
					</h2>
					<p>
						<font color="#800000">ユーザー名</font><br>
						<s:textfield label="ID1" name="user_name" />
					</p>
					<br>
					<p>
						<font color="#800000">パスワード</font><br>
						<s:password label="パスワード" name="password" />
					</p>
					<br>
					<button class="button">ログイン</button>
				</s:form>
			</div>
		</div>
		<!-- ログインここまで -->
		<h2>メニュー</h2>
		<s:iterator value="itemList" status="rs">
			<table id="main">

				<tr>
					<s:iterator value="itemList[#rs.index]">
						<td colspan="2"><img class="image"
							src=<s:property value ="imgPath"/> width="340px" height="300px"></td>
					</s:iterator>
				</tr>
				<tr>
					<s:iterator value="itemList[#rs.index]">
						<td style="text-align: center"><s:property value="itemName" /></td>
						<td style="text-align: center">&yen;<s:property value="price" /></td>
					</s:iterator>
				</tr>
				<tr>
					<s:iterator value="itemList[#rs.index]">
						<td style="text-align: center" colspan="2"><s:property
								value="explanation" /></td>
					</s:iterator>
				</tr>
				<tr>
					<s:iterator value="itemList[#rs.index]">
						<s:form action="CartAddItemAction">
							<td><s:hidden name="itemId" /> <select name="numberOfItems">
									<option value="0">選択してください</option>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
							</select></td>
							<td>
								<button class="button" type="submit">カートに追加</button>
							</td>
						</s:form>
					</s:iterator>
				</tr>
			</table>
		</s:iterator>
		<!-- メインここまで -->
		<div id="footer">
			<p>
				&copy;Copyright <a onclick="location.href='AdminItemsAction'">IWATA</a>
				cafe. All rights reserved.
			</p>
		</div>
	</div>
	<script src="./js/script.js"></script>
</body>
</html>