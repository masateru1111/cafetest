<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="./css/style.css">
<link rel="stylesheet" type="text/css" href="./css/mypage.css">
<title>マイページ</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.1/jquery.min.js"></script>
</head>
<body>
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
			<s:a href="PurchaseAction">
				<img src="./img/keyvisual.jpg" width="900" height="362" alt="">
			</s:a>
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
			<li><s:a href="LoginTestAction">ログイン</s:a></li>
			<li><s:a href="create_user.jsp">新規登録</s:a></li>
			<li><s:a href="contact.jsp">お問い合わせ</s:a></li>
		</ul>
		<%
		    }
		%>
		<!-- ヘッダーここまで -->
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
		
		<!-- メイン -->
		<h2>マイページ</h2>
		<table class="items">
			<tr>
				<td>名前</td>
				<td><s:property value="userName" /></td>
			</tr>
			<tr>
				<td>フリガナ</td>
				<td><s:property value="nameKana" /></td>
			</tr>
			<tr>
				<td>メールアドレス</td>
				<td><s:property value="mailAddress" /></td>
			</tr>
			<tr>
				<td>パスワード</td>
				<td><s:property value="password" /></td>
			</tr>
			<tr>
				<td>郵便番号</td>
				<td><s:property value="postalCode" /></td>
			</tr>
			<tr>
				<td>住所</td>
				<td><s:property value="address" /></td>
			</tr>
			<tr>
				<td>クレジットカード番号下４桁</td>
				<td><s:property value="card4" /></td>
			</tr>
		</table>

		<s:a href="edit_payment.jsp">
			<button class="button">情報編集</button>
		</s:a>
		<div class="history">
			<h2>購入履歴</h2>

			<table class="items">
				<tr>
					<th>商品名</th>
					<th>数量</th>
					<th>購入日付</th>
				<tr>
					<s:iterator value="history" status="rs">
						<s:iterator value="history[#rs.index]">
							<tr>
								<td><s:property value="itemName" /></td>
								<td><s:property value="numberOfItems" /></td>
								<td><s:property value="registrationDate" /></td>
							</tr>
						</s:iterator>
					</s:iterator>
			</table>

			<table class="items">
				<tr class="total_item">
					<td>合計購入数</td>
					<td><s:property value="sumOfNumberOfItems" /> 個</td>
				</tr>
				<tr class="total_item">
					<td>総額：</td>
					<td>&yen; <s:property value="totalPrice" /></td>
				</tr>
			</table>
		</div>
		<!-- メインここまで -->
		
		<div id="footer">
			<p>&copy;Copyright IWATA cafe. All rights reserved.</p>
		</div>
	</div>
	<script src="./js/script.js"></script>
</body>
</html>
