<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp"/>
    <h2>All Department</h2>
        <table class="table">
          <thead>
            <tr>
              <th scope="col">Department name</th>
              <th scope="col">Description</th>
              <th scope="col">Date of formation</th>
              <th scope="col">Phone number</th>
            </tr>
          </thead>
          <tbody>
          <c:forEach items="${allDepartment}" var="department">
            <tr>
              <td>${department.name}</td>
              <td>${department.description}</td>
              <td>${department.dateOfFormation}</td>
              <td>${department.phoneNumber}</td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
<jsp:include page="footer.jsp"/>
