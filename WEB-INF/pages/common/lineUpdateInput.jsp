<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/pages/common/lineHeader.jsp"/>
<%@include file="/WEB-INF/pages/common/define.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">

function doSubmit(choice){

	if(choice=='next'){
		document.fm.action="LineUpdateConfirmServlet";
	}
	else if(choice=='prev'){
		document.fm.action="LineListServlet";
	}
	document.fm.submit();
}

</script>

</head>
<body>
<form name="fm">
<h3>路線変更</h3>
<fieldset style=width:500px>
	<legend>路線情報</legend>
	<p>
		路線ID
		<c:out value="${line_id}" />
	</p>
	<table>
		<tr>
			<td width="200">出発停留所</td><td>出発時刻</td>
		</tr>
		<tr><td><select name="departure_stop_id">
				<option value="<c:out value="${departure_stop_id_before}"/>" ><c:out value="${departure_stop_id}"/></option>
					<c:forEach items="${stopsDTOList}" var="stopsDTO">
				<option value="<c:out value="${stopsDTO.stop_id}"/>">
				<c:if test="${param.stop_id==stopsDTO.stop_id}">
					selected
				</c:if>
				<c:out value="${stopsDTO.stop_name}"/></option>
				</c:forEach>
			</select></td>

			<td><input type="text" name="start_time" value='<c:out value="${start_time}"/>' /></td>
			<tr><td></td><td>　※半角数字4桁のみ入力してください</tr>

		<tr>
			<td width="200">到着停留所</td><td>到着時刻</td>
		</tr>
		<tr><td><select name="arrival_stop_id">
				<option value="<c:out value="${arrival_stop_id_before}"/>" ><c:out value="${arrival_stop_id}"/></option>
				<c:forEach items="${stopsDTOList}" var="stopsDTO">
				<option value="<c:out value="${stopsDTO.stop_id}"/>" >
				<c:out value="${stopsDTO.stop_name}"/></option>
				</c:forEach>
			</select></td>
			<td><input type="text" name="end_time" value='<c:out value="${end_time}"/>' /></td>
			<tr><td></td><td>　※半角数字4桁のみ入力してください</tr>
		</tr>
			<tr><td>料金</td></tr>
			<tr><td><input type="text" name="fee" value='<c:out value="${fee}"/>' />円</td></tr>
			<tr><td>　※半角数字のみ入力してください</tr>
			<tr><td>バス</td></tr>
			<tr><td><select name="bus_id">
				<option value="<c:out value="${bus_id}"/>">
				<c:out value="${bus_id}"/>　(
				<c:out value="${seat_colum}"/>列シート×
				<c:out value="${seat_row}"/>)
				</option>
				<c:forEach items="${busesDTOList}" var="busesDTO">
				<option value="<c:out value="${busesDTO.bus_id}"/>">
				<c:out value="${busesDTO.bus_id}"/>　(
				<c:out value="${busesDTO.seat_colum}"/>列シート×
				<c:out value="${busesDTO.seat_row}"/>)
				</option>
				</c:forEach>
				</select></td>
			</tr>

			<tr><td width="150">運行開始日</td><td>運行終了日</td></tr>
			<tr><td><input type="text"
					name="start_date" value='<c:out value="${start_date}"/>' /></td>
				<td><input type="text" name="end_date"
					value='<c:out value="${end_date}"/>' /></td>
			</tr>
		</table>
		<p>　※半角数字8桁のみ入力してください</p>
		<input type="hidden" name="line_id" value='<c:out value="${line_id}"/>'/>
		<input type="button" value="更新"  onclick="doSubmit('next')"/>　　
		<input type="button" value="戻る" onclick="doSubmit('prev')"/>

	</fieldset>
</form>
</body>
</html>