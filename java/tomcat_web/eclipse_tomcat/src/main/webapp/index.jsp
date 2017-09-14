<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>http://localhost:8080/eclipse_tomcat/</title>
</head>
<body>
<h2>Hello World!</h2>

<c:if test="${!empty param.name}">
	<c:out value="${param.name }" />
</c:if>
</body>
</html>
