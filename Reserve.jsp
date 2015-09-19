<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/pages/common/membersHeader.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">

function doSubmit(choice){

	var flag=0;
	var date_line_reserve=document.fm.date_line_reserve.value;

	if(document.fm.date_line_reserve.value!=""){
		flag=1;
	}
	if(choice=='next'){
		if(flag==1){
			document.fm.action="DailyLineReserveServlet";
		}
		else{
			document.fm.action="ReserveServlet";
			alert("予約状況を選択してください");
		}
	}
		else if(choice=='prev'){
		document.fm.action="MembersListServlet";
	}
	document.fm.submit();
}


</script>

</head>
<body>

 <h3>会員予約状況</h3>

  <table border="1">
  <tr><td>会員ID</td><td><c:out value="${member_id }" /></td></tr>
  <tr><td>氏名</td><td><c:out value="${member_name }" /></td></tr>
  <tr><td>電話番号</td><td><c:out value="${telephone }" /></td></tr>
  <tr><td>メールアドレス</td><td><c:out value="${mail}" /></td></tr>
  </table>
  <br>


<form name="fm">
  <table border="1">
  <tr><th>選択</th><th>日付</th><th>路線ID</th><th>出発</th><th>到着</th><th>料金</th><th>バスID</th></tr>
  <c:forEach items="${ member_reserve }" var="member_reserve">
  <tr><td><input type="radio"name="date_line_reserve"
  value='<c:out value="${member_reserve.reserve_date}"/><c:out value="${member_reserve.line_id}"/><c:out value="${member_reserve.bus_id}"/>'/></td>
  <td><fmt:formatDate value="${member_reserve.reserve_date}" pattern="yyyy年MM月dd日"/></td>
  <td><c:out value="${member_reserve.line_id}" /></td>
  <td><c:out value="${member_reserve.departure_stop_id}" />  (<c:out value="${member_reserve.start_time}" />)</td>
  <td><c:out value="${member_reserve.arrival_stop_id }" />  (<c:out value="${member_reserve.end_time}" />)</td>
  <td><fmt:formatNumber value="${ member_reserve.fee}" pattern="###,###円"/></td>
  <td><c:out value="${member_reserve.bus_id}" /></td>
  </tr>
  </c:forEach>
  </table>

  <br>

<input type="button" value="日別路線予約状況" onclick="doSubmit('next')">
<input type="button" value="戻る" onclick="doSubmit('prev')">
<input type="hidden" name="member_id" value='<c:out value="${member_id}"/>'/>




</form>
</body>
</html>