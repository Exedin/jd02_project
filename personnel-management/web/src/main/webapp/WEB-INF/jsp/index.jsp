<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp"/>
<a href="hello">Hello Spring</a> |
<a href="contact">Contact</a>
<body>
<h1>Welcome Home page</h1>
    <p>Message is: ${greeting}</p>
</body>
<jsp:include page="footer.jsp"/>
