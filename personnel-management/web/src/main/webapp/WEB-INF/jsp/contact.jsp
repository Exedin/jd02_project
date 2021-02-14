<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Spring Tiles Contact Form</title>
</head>
<body>
<h2>Contact Manager</h2>
<form action="/web/new-product.html" method="post">
    <label for="fid">Contact id:</label><br>
    <input type="text" id="fid" name="product.id" value=""><br>
    <label for="pname">Contact name:</label><br>
    <input type="text" id="pname" name="product.name" value=""><br>
    <label for="pdetls">Contact details:</label><br>
    <input type="text" id="pdetls" name="product.details" value=""><br>
    <br>
    <input type="submit" value="Submit">
</form>
</body>
</html>