<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/common/header.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
function go(){
	if(document.Delete.bus_id.value!=""){
		document.Delete.submit();
	}
}


</script>
<title>Insert title here</title>
</head>
<body>
<h3>バス一覧</h3><br/>
<form action="BusInputServlet">
<input type="submit"  value="新規登録" /><br/>
</form>
<form name="Delete" action="/team03-backoffice/BusDeleteConfirmServlet">
<table border="1">
<tr>
<th>選択</th><th>バスID</th><th>ナンバープレート</th><th>一列あたりのシート数</th><th>シート列数</th>
</tr>
<c:forEach items="${ busList }" var="bus">
<tr>
<td><input type="radio" name="bus_id" value="${ bus.bus_id }"></td>
<td><c:out value="${bus.bus_id }" /></td>
<td>
<c:out value="${bus.np_name }" />
<c:out value="${bus.np_class_no }" />
<c:out value="${bus.np_hiragana }" />
<c:out value="${bus.np_no1 }" />-
<c:out value="${bus.np_no2 }" />
</td>
<td><c:out value="${bus.seat_colum }" /></td>
<td><c:out value="${bus.seat_row }" /></td>
</tr>
</c:forEach>

</table>
<input type="button" value="削除" onclick="go()"/>
</form>

</body>
</html>