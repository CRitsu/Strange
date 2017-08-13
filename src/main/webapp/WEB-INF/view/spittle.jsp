<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Find the Spittle</title>
</head>
<body>
  <h1>Found the Spittle</h1>
  <div id='spittle_<c:out value="${spittle.id}" />'>
    <h3>
    △&nbsp;<c:out value="${spittle.id}" />#
      <c:out value="${spittle.message}" />
    </h3>
    <span><c:out value="${spittle.time}" />
    </span> <span>(<c:out value="${spittle.latitude}" />,<c:out value="${spittle.longitude}" />)
    </span>
  </div>
</body>
</html>