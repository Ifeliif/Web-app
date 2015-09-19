<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/common/define.jsp"%>
<jsp:include page="/WEB-INF/pages/common/membersHeader.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

function doSubmit(choice){
	var flag=0;
	var member_id=document.fm.member_id.value;

	if(document.fm.member_id.value!=""){
		flag=1;
	}
	if(choice=='reserve'){
		if(flag==1){
			document.fm.action="ReserveServlet";
		}
		else{
			document.fm.action="MembersListServlet";
			alert("会員を選択してください");
		}
	}

	if(choice=='membersFocus'){
		document.fm2.action="MembersListFocusServlet";
	}
	else{
		document.fm.submit();
	}
}
</script>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<body>
	<form name="fm1">
	<h3>会員一覧</h3>
	</form>
	<br>
	<fieldset style="width:40em;height:4em">
	<legend>検索条件</legend>
	<form name="fm2" method="post">
	　会員ID　　　<input type="text" name="member_id" style="width:8em;height:1em"/>
	　　　　　氏名　　　<input type="text" name="member_name" style="width:8em;height:1em"/>
	　　　<input type="submit" value="絞り込む" onclick="doSubmit('membersFocus')"/><br>
	※会員IDは一覧にあるものを半角英数字のみで入力してください<br>
	※氏名は一覧にあるものを全角のみで入力してください
	</form>
	</fieldset><br>

	<div id="message">

	<c:out value="${oneTimeMessage}"></c:out></div>


	<form name="fm" >
	<table border="1">
	<tr>
	<th>選択</th><th>会員ID</th><th>氏名</th><th>電話番号</th><th>メールアドレス</th>
	</tr>
	<c:forEach items="${ membersList }" var="members">
	<tr>
	<td><input type="radio" name="member_id" value="${ members.member_id }"></td>
	<td><c:out value="${members.member_id }" /></td>
	<td><c:out value="${members.member_name }" /></td>
	<td><c:out value="${members.telephone }" /></td>
	<td><c:out value="${members.mail }" /></td>
	</tr>
	</c:forEach>
	</table>
	<br>
	<input type="button" value="予約状況" onclick="doSubmit('reserve')"/>


	</form>
</body>
</html>

