<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp"/>
    <p>Enter department name for search:</p>
    <form action="/web/department/showOne" method="post">
        <label for="name">Department name name:</label><br>
        <input type="text" id="name" name="search" value=""><br>
        <br>
        <input type="submit" value="Submit">
<jsp:include page="footer.jsp"/>
