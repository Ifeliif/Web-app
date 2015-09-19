<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/pages/common/header.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
function doSubmit(choice){
	if(choice=='next'){

		document.fm.action="BusDeleteServlet";
	}
	else{
		document.fm.action="BusListServlet";
	}
	document.fm.submit();
}
</script>
<title>Insert title here</title>
</head>
<body>
<h3>バス削除確認</h3>
<p>以下の情報を削除します。よろしいですか?</p>
<fieldset>
<legend>バス情報</legend>


<form name="fm">
<c:forEach items="${ busList }" var="bus">
<dl>
<dt>シート列数</dt>
<dd><c:out value="${bus.seat_row }" /></dd>
<dt>一列あたりシート数</dt>
<dd><c:out value="${bus.seat_colum }" /></dd>
<dt>ナンバー</dt>
<div style="border:1px solid black; text-align:justify; width:200px; height :120px">
<div style="padding-top:20px;padding-left:60px;"><c:out value="${bus. np_name}"/>　　<c:out value="${bus. np_class_no}"/></div>
<div style="padding-top:8px;padding-left:28px;"><h1><c:out value="${bus. np_hiragana}"/>　
<c:out value="${bus. np_no1}"/>-　<c:out value="${bus. np_no2}"/></h1></div>
</div>
</dl>
</c:forEach>
<input type="button" value="OK" onclick="doSubmit('next');"/>
<input type="button" value="戻る" onclick="doSubmit('prev');"/ >
<input type="hidden" name="bus_id" value='<c:out value="${param.bus_id}"/>'/>
</form>

</fieldset>
</body>
</html>