<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<html>

<form:form action="/login" modelAttribute="User" method="POST">
<form:label path="name">Name</form:label>
    <form:input path="name" />
    <input type="submit" value="Submit" />
</form:form>
</html>