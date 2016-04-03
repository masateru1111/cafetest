$(function(){
//	商品画像のエフェクト
	$('.image').fadeIn('slow');
	
//	ログインボタン押下によるモーダルエフェクト
	$('#login-show').click(
			function(){
				$('#login-modal').fadeIn();
			});
	$('#close').click(
	function(){
		$('.login-modal-wrapper').fadeOut();
	}		
	);
//<!-- 郵便番号住所自動入力機能 -->
		$(window).ready(function() {
			$('#postalCode').jpostal({
				postcode : [ '#postalCode' ],
				address : {
					'#address' : '%3%4%5'
				}
			});
		});
});
