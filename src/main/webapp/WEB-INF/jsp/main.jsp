<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Pyramid</title>

    <link rel="stylesheet" type="text/css" href="js/main.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

</head>
<body>

<form:form action="/update" modelAttribute="User" method="POST">
   <form:input type="text" path="answer"></form:input>
    <p><input type="submit" value="Submit" /> <input type="reset" value="Reset" /></p>
 </form:form>
<div>
    <div id="table-container"> </div>
</div>

<script>
    var model={};
    model.answer="${User.answer}";
    model.userList = "${Application.getUserList()}"
    model.columns = "${Application.getTable().getColumns()}"
    model.rows = "${Application.getTable().getRows()}"
</script>
<script src="js/main.js"></script>
<script src="js/session.js"></script>
</body>
</html>