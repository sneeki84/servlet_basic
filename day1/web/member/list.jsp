<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<span style="float:right">
    <a href="<%=request.getContextPath()%>/auth/logoff">로그아웃</a>
</span>

<h2><%=session.getAttribute("name")%> 님 반갑습니다.</h2>


<jsp:include page="header.jsp"/>
<%
    List<Map<String,String>> listMap = (List<Map<String,String>>)request.getAttribute("LIST");
    for(Map<String,String> map : listMap)
    {
%>
<%=map.get("code")%>
<%=map.get("email")%>
<%=map.get("name")%>
<%
    }
%>
<jsp:include page="footer.jsp"/>
</body>
</html>
