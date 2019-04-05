<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Pyramid</title>

    <link rel="stylesheet" type="text/css" href="js/main.css">
</head>
<body>
<div id="header">


    <span>How size?</span>
    <input type="range" id="tableSize" value="5" min="1" max="10" step="1"></input>
</div>
<form:form action="/update" modelAttribute="appData" method="POST">
   <form:input type="text" path="answer"></form:input>
    <p><input type="submit" value="Submit" /> <input type="reset" value="Reset" /></p>
 </form:form>
<div>
    <div id="table-container"> </div>
</div>
<div id='answerID'>${answer}</div>
<script src="js/main.js"></script>
</body>
</html>