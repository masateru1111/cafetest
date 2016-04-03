<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link rel="stylesheet" type="text/css" href="./css/style.css">
<link rel="stylesheet" type="text/css" href="./css/create.css">
<title>新規登録</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.1/jquery.min.js">
</script>
<script src="//code.jquery.com/jquery-1.11.0.min.js"
	type="text/javascript"></script>
<script src="//jpostal.googlecode.com/svn/trunk/jquery.jpostal.js"
	type="text/javascript"></script>
</head>
<body>
	<!-- ヘッダー -->

	<!-- パスワード表示機能 -->
	<script>
		function checkPassword(e) {
			pass = document.getElementById("password");
			pass1 = document.getElementById("confirmPass");
			if (e.checked) {
				pass.setAttribute("type", "text");
				pass1.setAttribute("type", "text");
			} else {
				pass.setAttribute("type", "password");
				pass1.setAttribute("type", "password")
			}
		}
	</script>
	<!-- パスワード表示機能　ここまで -->
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
		<br>
		<s:iterator value="message">
			<font color="red"><s:property value="message" /></font>
		</s:iterator>

		<h2>新規登録</h2>
		<s:form action="CreateUserAction">
			<div class="form">
				<s:iterator value="cardErrorMessage">
					<font color="red"><s:property value="cardErrorMessage" /></font>
				</s:iterator>
				<br> <br>
				<h3>
					<span style="color: red">※</span>は入力必須です
				</h3>

				<table class="create">
					<tr>
						<td><span style="color: red">※</span> 氏名</td>
					</tr>
					<tr>
						<td><s:textfield name="userName" /></td>
					</tr>
					<tr>
						<th style="color: red"><s:fielderror>
								<s:param value="%{'userName'}" />
							</s:fielderror></th>
					</tr>
					<tr>
						<td><span style="color: red">※</span> フリガナ</td>
					</tr>
					<tr>
						<td><s:textfield name="nameKana" /></td>
					</tr>
					<tr>
						<th style="color: red"><s:fielderror>
								<s:param value="%{'nameKana'}" />
							</s:fielderror></th>
					</tr>
					<tr>
						<td><span style="color: red">※</span> パスワード(半角英数字)</td>
					</tr>
					<tr>
						<td><s:password type="password" id="password" name="password" /></td>
					</tr>
					<tr>
						<th style="color: red"><s:fielderror>
								<s:param value="%{'password'}" />
							</s:fielderror></th>
					</tr>
					<tr>
						<td><span style="color: red">※</span> 確認用パスワード</td>
					</tr>
					<tr>
						<td><s:password type="password" id="confirmPass"
								name="confirmPass" /></td>
					</tr>
					<tr>
						<th style="color: red"><s:fielderror>
								<s:param value="%{'confirmPass'}" />
							</s:fielderror></th>
					</tr>
					<tr>
						<td><input type="checkbox" onclick="checkPassword(this)" />
							パスワード表示</td>
					</tr>
					<tr>

					</tr>
					<tr>
						<td><span style="color: red">※</span> メールアドレス(半角英数字)</td>
					</tr>
					<tr>
						<td><s:textfield name="mailAddress" /></td>
					</tr>
					<tr>
						<th style="color: red"><s:fielderror>
								<s:param value="%{'mailAddress'}" />
							</s:fielderror></th>
					</tr>
					<tr>
						<td><span style="color: red">※</span> 確認用メールアドレス</td>
					</tr>
					<tr>
						<td><s:textfield name="confirmMail" /></td>
					</tr>
					<tr>
						<th style="color: red"><s:fielderror>
								<s:param value="%{'confirmMail'}" />
							</s:fielderror></th>
					</tr>
					<tr>
						<td><h3>＜配送先住所＞</h3></td>
					</tr>
					<tr>
						<td><span style="color: red">※</span> 郵便番号(半角数字)</td>
					</tr>
					<tr>
						<td><input type="text" id="postalCode" name="postalCode" maxlength="7"onKeyup="this.value=this.value.replace(/[^0-9]+/,'')"/></td>
					</tr>
					<tr>
						<th style="color: red"><s:fielderror>
								<s:param value="%{'postalCode'}" />
							</s:fielderror></th>
					</tr>
					<tr>
						<td><span style="color: red">※</span>住所</td>
					</tr>
					<tr>
						<td><input type="text" id="address" name="address"></td>
					</tr>
					<tr>
						<th style="color: red"><s:fielderror>
								<s:param value="%{'address'}" />
							</s:fielderror></th>
					</tr>
					<tr>
						<td><span style="color: red">※</span>電話番号(半角数字)</td>
					</tr>
					<tr>
						<td><s:textfield name="cellNumber" onKeyup="this.value=this.value.replace(/[^0-9]+/,'')"/></td>
					</tr>
					<tr>
						<th style="color: red"><s:fielderror>
								<s:param value="%{'cellNumber'}" />
							</s:fielderror></th>
					</tr>
					<tr>
						<td></td>
					</tr>
					<tr>
						<td>クレジットカード種類</td>
					</tr>
					<tr>
						<td><s:select name="card"
								list="#{'選択してください':'選択してください','VISA':'VISA', 'JCB':'JCB','MASTER':'MASTER'}" /></td>
					</tr>
					<tr>
						<td>クレジットカード番号(半角数字)</td>
					</tr>
					<tr>
						<td><s:password type="password" name="creditNumber" onKeyup="this.value=this.value.replace(/[^0-9]+/,'')"/></td>
					</tr>
					<tr>
						<th style="color: red"><s:fielderror>
								<s:param value="%{'creditNumber'}" />
							</s:fielderror></th>
					</tr>
					<tr>
						<td>クレジットカード 期限</td>
					</tr>
					<tr>
						<td>MONTH / YEAR</td>
					</tr>
					<tr>
						<td><select name="cardMonth">
								<option value=""></option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
						</select> / <select name="cardYear">
								<option value=""></option>
								<option value="17">17</option>
								<option value="18">18</option>
								<option value="19">19</option>
								<option value="20">20</option>
								<option value="21">21</option>
								<option value="22">22</option>
								<option value="23">23</option>
						</select></td>
					</tr>
					<tr>
						<td>クレジットカード名義（苗字と名前の間に半角スペースを入れてください）</td>
					</tr>
					<tr>
						<td><s:textfield label="cardHolder" name="cardHolder" /></td>
					</tr>
					<tr>
						<th style="color: red"><s:fielderror>
								<s:param value="%{'cardHolder'}" />
							</s:fielderror></th>
					</tr>
					<tr>
						<td>セキュリティコード(半角数字)</td>
					</tr>
					<tr>
						<td><s:textfield name="securityCode" /></td>
					</tr>
					<tr>
						<th style="color: red"><s:fielderror>
								<s:param value="%{'securityCode'}" />
							</s:fielderror></th>
					</tr>
				</table>
			</div>
			<br>
			<br>
			<button class="button">確定</button>
			<br>
		</s:form>
		<!-- メインここまで -->
		<div id="footer">
			<p>&copy;Copyright IWATA cafe. All rights reserved.</p>
		</div>
	</div>
	<script src="./js/script.js"></script>
</body>
</html>
