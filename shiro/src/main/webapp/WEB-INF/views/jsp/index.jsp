<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<script type="text/javascript" src="static/jquery/jquery-2.1.4.min.js"></script>
</head>
<body>
<h2>Hello World!</h2>

	<script>
		$.post("model/test/xxxx",function(data) {
			console.log(data);
		})
	</script>
</body>
</html>
