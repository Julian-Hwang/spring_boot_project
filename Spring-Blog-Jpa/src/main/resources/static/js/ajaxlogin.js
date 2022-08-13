$(document).ready(function () {
	$("#loginForm").submit(function (event) {
		event.preventDefault();
		ajax_login_submit();
	});
});

function ajax_login_submit() {
		var user = {};
		user["username"] = $("#username").val();
		user["password"] = $("#password").val();
		
		console.log(JSON.stringify(user));
		
		$.ajax({
			type: "POST",
			contentType: "application/json; charset=utf-8",
			url: "rest/user/login",
			data: JSON.stringify(user),
			dataType: 'json',
			cache : false,
			success: function (data) {
				$('#helloUserDiv').html('로그인 성공');
				console.log("SUCCESS : ", data);
				alert('로그인이 성공했습니다.');
			},
			error: function (e) {
				var resultJson = e.responseText;
				$('#helloUserDiv').html('로그인 실패');
				console.log("ERROR : ", resultJson);
			}
		});
}