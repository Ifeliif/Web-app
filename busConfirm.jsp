<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/pages/common/header.jsp"></jsp:include>
<%@include file="/WEB-INF/pages/common/define.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
function doSubmit(choice){

	if(choice=='n'){
		document.fm.action="BusInsertServlet";
	}
	else{
		document.fm.action="BusInputServlet";
	}
	document.fm.submit();
}
</script>
<title>Insert title here</title>
</head>
<body>
<h3>バス登録確認</h3>
<p>以下の情報を登録します。よろしいですか?</p>
<fieldset>
<legend>バス情報</legend>
<dl>
<dt>シート列数</dt>
<dd><c:out value="${param.seat_row }" /></dd>
<dt>一列あたりシート数</dt>
<dd><c:out value="${param.seat_colum }" /></dd>
<dt>ナンバー</dt>
<div style="border:1px solid black; text-align:justify; width:200px; height :120px">
<div style="padding-top:20px;padding-left:60px;"><c:out value="${param. np_name}"/>　　<c:out value="${param. np_class_no}"/></div>
<div style="padding-top:8px;padding-left:28px;"><h1><c:out value="${param. np_hiragana}"/>　
<c:out value="${param. np_no1}"/>-　<c:out value="${param. np_no2}"/></h1></div>
</div>
</dl>



<form name="fm" action="BusInsertServlet" method="post">
<input type="hidden" name="seat_colum" value='<c:out value="${param.seat_colum}"/>'/>
<input type="hidden" name="seat_row" value='<c:out value="${param.seat_row}"/>'/>
<input type="hidden" name="np_name" value='<c:out value="${param.np_name}"/>'/>
<input type="hidden" name="np_class_no" value='<c:out value="${param.np_class_no}"/>'/>
<input type="hidden" name="np_hiragana" value='<c:out value="${param.np_hiragana}"/>'/>
<input type="hidden" name="np_no1" value='<c:out value="${param.np_no1}"/>'/>
<input type="hidden" name="np_no2" value='<c:out value="${param.np_no2}"/>'/>

<input type="button" value="OK" onclick="doSubmit('n');"/>
<input type="button" value="戻る" onclick="doSubmit('prev');"/>

</form>



</fieldset>
</body>
</html>