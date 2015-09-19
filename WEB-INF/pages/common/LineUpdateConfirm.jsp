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
		document.fm.action="LineUpdateServlet";
		alert("更新しました");
	}
	else{
		document.fm.action="LineUpdateInputServlet";

	}
	document.fm.submit();
}

</script>

<title>Insert title here</title>
</head>
<body>

<h3>路線変更確認</h3>
<p>以下の内容を更新します。よろしいですか？</p>

<fieldset style=width:300px >
<legend>路線情報</legend>

<table>
<tr>
<td>路線ＩＤ</td><td><c:out value="${param.line_id }"/></td>
</tr>
<tr></tr>
 <tr>
 <td width="150">出発停留所</td><td>出発時刻</td>
 </tr>
 <tr>
 <td><c:out value="${lineDTO.arrival_stop_id}"/></td><td><c:out value="${lineDTO.start_time}"/></td>
 </tr>
 <tr>
 <td>到着停留所</td><td>到着時刻</td>
 </tr>
 <tr>
 <td><c:out value="${lineDTO.departure_stop_id}"/></td><td><c:out value="${lineDTO.end_time}"/></td>
 </tr>
</table>

<p>料金<br><fmt:formatNumber value="${lineDTO.fee}" pattern="###,###円"/></p>

<p>バス<br><c:out value="${lineDTO.bus_id}"/>　　　　<c:out value="${seat_colum}"/>列シート×<c:out value="${seat_row}"/></p>

<table>
 <tr>
 <td width="120">運行開始日</td><td>運行終了日</td>
 </tr>
 <tr>
 <td><fmt:formatDate value="${lineDTO.start_date}" pattern="yyyy年MM月dd日"/></td><td><fmt:formatDate value="${lineDTO.end_date}" pattern="yyyy年MM月dd日"/></td>
 </tr>
</table>

<form name="fm" method="get">
<input type="hidden" name="line_id" value='<c:out value="${param.line_id}"/>'/>
<input type="hidden" name="arrival_stop_id" value='<c:out value="${arrival_stop_id_before}"/>'/>
<input type="hidden" name="departure_stop_id" value='<c:out value="${departure_stop_id_before}"/>'/>
<input type="hidden" name="fee" value='<c:out value="${param.fee}"/>'/>
<input type="hidden" name="bus_id" value='<c:out value="${param.bus_id}"/>'/>
<input type="hidden" name="start_date" value='<c:out value="${lineDTO.start_date }"/>'/>
<input type="hidden" name="end_date" value='<c:out value="${lineDTO.end_date }"/>'/>
<input type="hidden" name="start_time" value='<c:out value="${start_time_before }"/>'/>
<input type="hidden" name="end_time" value='<c:out value="${end_time_before }"/>'/>
<input type="button" value="OK"  onclick="doSubmit('next')"/>
<input type="button" value="戻る" onclick="doSubmit('prev')"/>

</form>
</fieldset>
</body>
</html>