<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/common/define.jsp"%>
<jsp:include page="/WEB-INF/pages/common/lineHeader.jsp"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">

function doSubmit(choice){
//flag=1の場合遷移する
//flag=0
	var flag=0;
	var day=document.fm2.day.value;


	if(document.fm.line_id.value!=""){
		flag=1;
	}
	if(choice=='available' && flag==1){
		document.fm.action="Line_ReserveServlet";
	}
	else if(choice=='change'){
		if(flag==1){
			document.fm.action="LineUpdateInputServlet";
		}else{
			document.fm.action="LineListServlet";
			alert("路線を選択してください");
		}
	}
	else if(choice=='focus'){
		if(document.fm2.day.value == ""){
			document.fm2.action="LineListServlet";
		}
		else{
			document.fm2.action="LineListFocusServlet";
		}
	}
	else if(choice=='new'){
		document.fm1.action="LineInsertInputServlet";
	}
	else{
		if(flag==1){
			document.fm.action="LineDeleteConfirmServlet";
		}else{
			document.fm.action="LineListServlet";
			alert("路線を選択してください");
		}
	}
	document.fm.submit();
}


</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form name="fm1">
<h3>路線一覧　　　
<input type="submit"  value="新規登録" onclick="doSubmit('new')"/>
</h3>
</form>
<fieldset style="width:40em;height:2em">
<legend>運行日</legend>
<form name="fm2">
<input type="text" name="day" style="width:8em;height:1em"/>
　　<input type="submit" value="絞り込む" onclick="doSubmit('focus')"/>
</form>
</fieldset><br>

	<form name="fm" >
	<table border="1">
	<tr>
	<th>選択</th><th>路線ID</th><th>出発</th><th>到着</th><th>運行開始日</th><th>運行終了日</th><th>料金</th><th>バスID</th>
	</tr>
	<c:forEach items="${ lineList }" var="line">
	<tr>
	<td><input type="radio" name="line_id" value="${ line.line_id }"></td>
	<td><c:out value="${line.line_id }" /></td>
	<td><c:out value="${line.departure_stop_id }" /><c:out value="("/><c:out value="${line.start_time }" /><c:out value=")"/></td>
	<td><c:out value="${line.arrival_stop_id }" /><c:out value="("/><c:out value="${line.end_time }" /><c:out value=")"/></td>
	<td><fmt:formatDate value="${line.start_date }" pattern="yyyy年MM月dd日"/></td>
	<td><fmt:formatDate value="${line.end_date }" pattern="yyyy年MM月dd日"/></td>
	<td><fmt:formatNumber value="${line.fee }" pattern="\#,###"/></td>
	<td><c:out value="${line.bus_id }" /></td>


	</tr>

	</c:forEach>
	</table>
	<br>
	<input type="button" value="予約状況" onclick="doSubmit('available')"/>
	　　<input type="button" value="変更" onclick="doSubmit('change')"/>
	　<input type="button" value="削除" onclick="doSubmit('delete')"/>
	</form>

</body>
</html>