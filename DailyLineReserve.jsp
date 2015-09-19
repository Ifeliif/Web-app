<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/common/define.jsp"%>
<jsp:include page="/WEB-INF/pages/common/membersHeader.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

function doSubmit(choice){
	//遷移先を決める.fmの場合は無条件に遷移する。
	if(choice=='re'){
		document.fm.action="ReserveServlet";
		document.fm.submit();
	}
	else if(choice=='dlr'){
		document.fm2.action="DailyLineReserveFocusServlet";
	}
//flag=0の場合遷移する
	var flag=0;
	var date=document.fm2.date.value;
	var month=date.substring(4,6);
	var day=date.substring(6,8);
	if(NotNumber(date)){
		alert("日付はyyyyMMdd方式で入力してください。");
		flag=1;
	}

	if(date.length!=8){
		alert("日付の値が不正です。");
		flag=1;
	}

	if(month>12){
		alert("日付の値が不正です。");
		flag=1;
	}

	if(day>31){
		alert("日付の値が不正です。");
		flag=1;
	}

	if(flag==0){
		document.fm2.submit();
	}
}

function NotNumber(str){
	var len=str.length;
	for(var i=0;i<len;i++){
		var c=str.charAt(i);
		if(c<'0'||c>'9'){
			return true;
		}
	}
	return false;
}
</script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name="fm1">
	<h3>日別路線予約状況</h3>
	</form>
	<fieldset style="width:20em;height:2.4em">
	<legend>日付指定</legend>
	<form name="fm2">
	　日付　　<input type="text" name="date" style="width:8em;height:1em"/>
		<input type="hidden" name="line_id" value='<c:out value="${line_id}"/>'/>
	<input type="hidden" name="bus_id" value='<c:out value="${bus_id}"/>'/>
	　　　<input type="button" value="再表示" onclick="doSubmit('dlr')"/>

	</form>
	</fieldset><br>

	<div id="message">

	<c:out value="${oneTimeMessage}"></c:out></div>


	<form name="fm" >
	<table border="1" style="float:left;">
	<tr><td>日付</td><td><fmt:formatDate value="${reserve_date }" pattern="yyyy年MM月dd日"/></td></tr>
	<tr><td>出発</td><td><c:out value="${departure_stop_id }" /><c:out value="("/><c:out value="${start_time }" /><c:out value=")"/></td></tr>
	<tr><td>到着</td><td><c:out value="${arrival_stop_id }" /><c:out value="("/><c:out value="${end_time }" /><c:out value=")"/></td></tr>
	<tr>
	<td>1席料金</td><td><fmt:formatNumber value="${fee }" pattern="\#,###"/></td>

	<table>
	<tr><td>　</td></tr>
	<tr><td>　</td></tr>
	<tr><td>　</td></tr>
	<tr><td>　</td></tr>
	<tr><td>　　　</td></tr>
	</table>
	</tr>
	</table>

	<h4>予約状況　　　　　　　　　空席一覧</h4>
	<table border="1" style="float:left;">
	<tr>
	<th>座席番号</th><th>会員ID</th>
	</tr>
	<c:forEach items="${ reserveList }" var="seat1">
	<tr>
	<td><c:out value="${seat1.seat_number }" /></td>
	<td><c:out value="${seat1.member_id }" /></td>
	</tr>
	</c:forEach>
	</table>

	<table style="float:left;">
	<tr><td>　</td></tr>
	<tr><td>　</td></tr>
	<tr><td>　</td></tr>
	<tr><td>　</td></tr>
	<tr><td>　</td></tr>
	</table>
	<table style="float:left;">
	<tr><td>　</td></tr>
	<tr><td>　</td></tr>
	<tr><td>　</td></tr>
	<tr><td>　</td></tr>
	<tr><td>　</td></tr>
	</table>

	<table border="1">
	<tr>
	<th>座席番号</th>
	</tr>
	<c:forEach items="${ seatList }" var="seatList">
	<tr>
	<td><c:out value="${seatList.seat_colum }" /><c:out value="${seatList.seat_row }" /></td>
	</tr>
	</c:forEach>
	</table>
	<br>

	</form>
</body>
</html>