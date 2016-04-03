<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/purchase.css">
<link rel="stylesheet" href="./css/style.css">
<title>カートリスト</title>
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
			<li id="login-show">ログイン</li>
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
		<div class="purchase">
			<h2>カートの中身</h2>
			<table id="item">
				<tr>
					<th>商品画像</th>
					<th>商品名</th>
					<th>数量</th>
					<th>（税込）</th>
					<th>編集</th>
				</tr>
				<s:iterator value="cartList" status="rs">
					<tr>
						<s:iterator value="cartList[#rs.index]">
							<td><div id="img">
									<img src=<s:property value="imgPath"/> width="100px"
										height="100px">
								</div></td>
							<s:form action="CartUpdateAction">
								<td><s:property value="itemName" /></td>
								<s:hidden name="itemId" />
								<td>数量 ： <s:textfield name="numberOfItems" onKeyup="this.value=this.value.replace(/[^0-9]+/,'')" maxlength="2" id="textarea"/>個
								</td>
								<td>&yen; <s:property value="totalPrice" />
								</td>
								<td><button class="button">カート内更新</button></td>
							</s:form>
						</s:iterator>
					</tr>
				</s:iterator>
			</table>
			<br> <br>
			<h1>
				合計：&yen;
				<s:property value="sumOfPrice" />
				（税込）
			</h1>
		</div>

		<s:form action="GoPurchaseCheckAction">
			<button class="button">購入画面へ</button>
		</s:form>
		<br> <br>
		<!-- メインここまで -->
		<div id="footer">
			<p>&copy;Copyright IWATA cafe. All rights reserved.</p>
		</div>
	</div>
	<script src="./js/script.js"></script>
</body>
</html>