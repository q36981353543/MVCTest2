<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script type="text/javascript">
        function sendAjax() {
            var json = JSON.stringify({
                id: $("#id").val(),
                content: $("#content").val()
            });
            $.ajax({
                url: "${pageContext.servletContext.contextPath}/add",
                type: 'POST',
                data: json,
                contentType: 'application/json',
//                success: function(data) {
//                    alert("Input data: " + data.id + " " + data.content);
//                },
                error:function(data,status,er) {
                    alert("error: " + data + " status: " + status + " er:" + er);
                }
            });
        }
    </script>
</head>
<body>
<table>
    <tr>
        <td>Add new command</td>
    </tr>
    <tr>
        <td>id</td>
        <td>content</td>
    </tr>
    <tr>
        <td><input type="text" id="id"></td>
        <td><input type="text" id="content"></td>
    </tr>
</table>
<br/><input type="button" value="Send JSON" onclick="sendAjax()"><br/>
</body>
</html>