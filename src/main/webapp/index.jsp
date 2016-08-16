<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <body>
        <script>
          <c:choose>
            <c:when test="${empty loginBean.administrador.id}">
                location.href = "<%= request.getContextPath()%>/login/login.jsf";
            </c:when>
            <c:otherwise>
                location.href = "<%= request.getContextPath()%>/home/home.jsf";
            </c:otherwise>
          </c:choose>
        </script>
    </body>
</html>