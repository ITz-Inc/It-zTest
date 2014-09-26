$(function() {
	var btn_ok = $("#btn_next");

	btn_ok.click(function() {
		disableButton($(this));

		var pass = $("#pwd_password").val();
		var pass_conf = $("#pwd_password_confirm").val();
		if (pass != pass_conf) {
			alert("password error");
		};

		var data = {
			name: $("#txt_name").val(),
			id: $("#txt_id").val(),
			password: pass
		};


		$.ajax({
			type: "post",
			scriptCharset: "UTF-8",
			url: "MainServlet",
			data: JSON.stringify(data),
			contentType: "application/json",
			dataType: "json",
			success: function(res) {
				alert(res.name);
			},
			error: function() {
				alert("error");
			},
			complete: function() {
				alert("complete");
			}
		});
	});

	function disableButton(buttonObject) {
		buttonObject.css({"background-color":"#a0a0a0", "pointer-events":"none"});
	}
});