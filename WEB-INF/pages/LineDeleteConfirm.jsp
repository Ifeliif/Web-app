<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/pages/common/lineHeader.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">

function doSubmit(choice){

	if(choice=='next'){
		document.fm.action="LineDeleteServlet";
		alert("削除しました")
	}
	else{
		document.fm.action="LineListServlet";
	}
	document.fm.submit();
}

</script>

<title>Insert title here</title>
</head>
<body>

<h3>路線削除確認</h3>
<p>以下の内容を削除します。よろしいですか？</p>

<fieldset style=width:300px >
<legend>路線情報</legend>

<table>
<tr>
<td>路線ＩＤ</td><td><c:out value="${line_id }"/></td>
</tr>
<tr></tr>
 <tr>
 <td width="150">出発停留所</td><td>出発時刻</td>
 </tr>
 <tr>
 <td><c:out value="${arrival_stop_id}"/></td><td><c:out value="${start_time}"/></td>
 </tr>
 <tr>
 <td>到着停留所</td><td>到着時刻</td>
 </tr>
 <tr>
 <td><c:out value="${departure_stop_id}"/></td><td><c:out value="${end_time}"/></td>
 </tr>
</table>

<p>料金<br><fmt:formatNumber value="${fee}" pattern="###,###円"/></p>

<p>バス<br><c:out value="${bus_id}"/>　　<c:out value="${seat_colum}"/>列シート×<c:out value="${seat_row}"/></p>

<table>
 <tr>
 <td width="120">運行開始日</td><td>運行終了日</td>
 </tr>
 <tr>
 <td><fmt:formatDate value="${start_date}" pattern="yyyy年MM月dd日"/></td><td><fmt:formatDate value="${end_date}" pattern="yyyy年MM月dd日"/></td>
 </tr>
</table>

<form name="fm" method="post">
<input type="hidden" name="line_id" value='<c:out value="${line_id}"/>'/>

<input type="button" value="OK"  onclick="doSubmit('next')"/>
<input type="button" value="戻る" onclick="doSubmit('prev')"/>

</form>
</fieldset>
</body>
</html>