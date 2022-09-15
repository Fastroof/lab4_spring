<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
  <jsp:include page="head.jsp"/>
  <title>Index</title>
</head>
<body>
<h4 class="h4 text-center">Орендувати квартиру</h4>
<div class="container p-0 border">
  <table class="table table-hover mb-1">
  <thead class="bg-secondary bg-opacity-50">
    <tr>
      <th scope="col">Адреса</th>
      <th scope="col">Опис</th>
      <th scope="col">Дата опублікування</th>
      <th scope="col">Площа (м²)</th>
      <th scope="col">Ціна</th>
      <th scope="col">Кількість 🛏</th>
      <th scope="col">Контакти</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach items="${rooms}" var="room">
    <tr>
      <td><c:out value="${room.description.address}"/></td>
      <td><c:out value="${room.description.description}"/></td>
      <td><c:out value="${room.description.creationDate}"/></td>
      <td><c:out value="${room.configuration.area}"/></td>
      <td><c:out value="${room.configuration.price}"/></td>
      <td><c:out value="${room.configuration.bedroomCount}"/></td>
      <td><c:out value="${room.user.fullName}"/> <c:out value="${room.user.email}"/></td>
    </tr>
    </c:forEach>
  </tbody>
</table>
  <p class="text-center mb-1 text-secondary">а більше буде згодом...</p>
</div>
</body>
</html>