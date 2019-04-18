<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Pyramid</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="js/main.css">

</head>
<body>

<form action="/update" method="POST" autocomplete="off">
   <input type="text" name="answer"></input>
    <p><input type="submit" value="Submit" /> <input type="reset" value="Reset" /></p>
 </form>
<div>

    <div><p>Active users:</p>
    <ul id ="user-list"></ul>
    </div>
    <div id="table-container"> </div>
</div>

<script>
    var model={};
    model.userList = "${ApplicationModel.getUserList()}"
    model.sessionInactiveIntervalInMilliseconds = "${ApplicationModel.getApplicationConfiguration().getSessionMaxInactiveIntervalInMilliseconds()}"
    model.columns = "${ApplicationModel.getTable().getColumns()}"
    model.rows = "${ApplicationModel.getTable().getRows()}"
    model.users  = JSON.parse("${ApplicationModel.getUsersInJson()}")
    model.activeCell = {};
</script>
<script src="js/main.js"></script>
<script src="js/session.js"></script>
<script src="js/userList.js"></script>

</body>
</html>