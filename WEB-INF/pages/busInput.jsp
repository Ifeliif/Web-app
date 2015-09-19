<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- <%@include file="/WEB-INF/pages/common/define.jsp" %>   -->

<jsp:include page="/WEB-INF/pages/common/header.jsp">
	<jsp:param value="バス登録" name="title_for_header_jsp" />
</jsp:include>

<script type="text/javascript">
function doSubmit(choice){
	if(choice=='next'){
		document.fm.action="BusConfirmServlet";
	}
	else{
		document.fm.action="BusListServlet";
		document.fm.submit();
	}

	var seat_row=document.fm.seat_row.value;
	var seat_colum=document.fm.seat_colum.value;
	var np_name=document.fm.np_name.value;
	var np_class_no=document.fm.np_class_no.value;
	var np_hiragana=document.fm.np_hiragana.value;
	var np_no1=document.fm.np_no1.value;
	var np_no2=document.fm.np_no2.value;

	/*flagが0なら画面遷移する*/
	var flag=0;

	/*各項目が空白なら遷移しない*/
	if(seat_row==""||seat_colum==""||np_name==""
		||np_class_no==""||np_hiragana==""||np_no1==""||np_no2==""){
		flag=1;
	}

	/*数字項目に数字じゃない文字が代入された場合遷移しない*/
	if(NotNumber(seat_colum)||NotNumber(seat_row)||NotNumber(np_class_no)||NotNumber(np_no1)||NotNumber(np_no2)){
		flag=1;
	}
	/*np_no1とnp_no2の桁数が2ではない場合遷移しない*/
	if(np_no1.length!=2||np_no2.length!=2||np_class_no.length!=2){
		flag=1;
	}
	//ひらがなでない場合遷移しない
	if(NotKanaCheck()){
		flag=1;
	}
	//文字の長さが長すぎる場合遷移しない
	if(seat_row.length>3||seat_colum.length>2||np_name.length>10||np_hiragana.length>1){
		flag=1;
	}
//np_nameが全角でない場合遷移しない
	if(NotZen(np_name)){
		flag=1;
	}
//np_nameが漢字・カタカナ・ひらがなでない場合遷移しない
	if(NotKanji(np_name)&&NotHiragana(np_name)&&NotKatakana(np_name)){
		flag=1;
	}

	if(flag==0){
		document.fm.submit();
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

function NotKanaCheck() {
	   var str = document.fm.np_hiragana.value;
	   if( str.match( /[^あ-ん　\s]+/ ) ) {
	      return true;
	   }
	   return false;
	}

function NotZen( str ){
	for(i=0; i<str.length; i++){
		if(escape(str.charAt(i)).length < 4){
			// 半角文字が含まれている
			return true;
		}
	}
	// 全て全角
	return false;
}


function NotKanji(c){ // c:判別したい文字
    var unicode = c.charCodeAt(0);
    if ( (unicode>=0x4e00  && unicode<=0x9fcf)  || // CJK統合漢字
         (unicode>=0x3400  && unicode<=0x4dbf)  || // CJK統合漢字拡張A
         (unicode>=0x20000 && unicode<=0x2a6df) || // CJK統合漢字拡張B
         (unicode>=0xf900  && unicode<=0xfadf)  || // CJK互換漢字
         (unicode>=0x2f800 && unicode<=0x2fa1f) )  // CJK互換漢字補助
        return false;

    return true;
}


function NotHiragana(c){
    var unicode = c.charCodeAt(0);
    if ( unicode>=0x3040 && unicode<=0x309f )
        return false;
    return true;
}


function NotKatakana(c){
    var unicode = c.charCodeAt(0);
    if ( unicode>=0x30a0 && unicode<=0x30ff )
        return false;

    return true;
}





</script>

<form name="fm" method="post">
	<h3>バス登録</h3>
	<div
		style="border: 1px solid black; padding: 20px; text-align: justify; width: 220px; height: 210px">
		シート列数<br> <input type="text" name="seat_row"
			style="width: 15em; height: 1em"
			value='<c:out value="${param.seat_row}"/>' /><br> 1列あたりシート数 <br>
		<input type="text" name="seat_colum" style="width: 15em; height: 1em"
			value='<c:out value="${param.seat_colum}"/>' /><br> <br>
		ナンバー<br>
		<div
			style="border: 1px solid black; text-align: justify; width: 200px; height: 100px">
			<br>
			　　　　　地名
			　　　　　分類番号<br>
			　　　　
			<input type="text" name="np_name" style="width: 6em; height: 1em"value='<c:out value="${param.np_name}"/>' />
			　<input type="text" name="np_class_no" style="width: 5em; height: 1em" value='<c:out value="${param.np_class_no}"/>' /><br> <br>
			　　　　かな
			　一連指定番号<br>
			　　　　<input type="text" name="np_hiragana" style="width: 1em; height: 1em" value='<c:out value="${param.np_hiragana}"/>' />
			　<input type="text" name="np_no1" style="width: 4em; height: 2em" value='<c:out value="${param.np_no1}"/>' />
			　ー　
			 <input type="text" name="np_no2" style="width: 4em; height: 2em"value='<c:out value="${param.np_no2}"/>' />
			</div>
		<br>
		<input type="button" value="登録" style="width: 6em;" onclick="doSubmit('next');" />
		<input type="button" value="戻る" style="width: 6em;" onclick="doSubmit('prev');" /> <br> <br>
	</div>

</form>

