<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript">
   $(function(){
      $("#buttonSubmit").on("click" , function(){
         $.ajax({ // ajax 의 기본 형식 
            // 서버에게 보내는 정보 
            url:'./ajaxServer.jsp',
            type:'post',
            data:{nickName : '멍멍이'},
            // 클라이언트가 받는 정보 
            dataType : 'json',
            success:function(data){  //data(매개변수 아무렇게 적어도 됨) = {"nickName": "멍멍이"}객체를 통으로 받음
            	console.log(data.nickName); 
            	console.log("요청 성공");
              	 $("#nickName").text(data.nickName);
            }
         });
      });
   });
</script>

</head>

<body>
   <input id="buttonSubmit" type="button" value="제출">
   <p id="nickName"></p>
   <p>ajax 통신 성공</p>
   
   
   
 <!-- Asynchrooous JavaScript and XML
	   - xml형식의 데이터 비동기 처리를 위해(위의 데이터 처리 과정에 영향을 받지 않고 밑에 데이터 처리가 진행됨)
	   - json(JavaScript Object Notation)
	    : 문자형식의 데이터를 효율적으로 주고 받는 구조
	    : 배열이나 객체형식으로 작성되어지는 데이터

   {}: 객체
   []: 배열

   중첩구조
   [{ }]
   {[ ]}

   (1) - 객체
   {
   	"name": "고등어", "age": 45, "address": "서울"
   }  
   접근방식 : object.name / object[ "name" or 변수]


   (2) - 배열 구조
   [
   	"html", "java", "javascript"
   ]  
   접근방식: object[indexNumber]


   (3) - 객체의 키,벨류 구조(벨류에 배열을 넣어 중첩)
   {
   	"firstName" : "홍",
   	"lastName" : "길동",
   	"books" : ["html", "java", "javascript"]
   }  
   접근방식: object.book[indexNumber]


   (4)- 배열 구조에서 객체{ key : value }를 중첩
   [
   	{"city":"서울", "hotplace":"홍대"},
   	{"city":"부산", "hotplace":"해운대"},	
   	{"city":"제주도", "hotplace":"성산일출봉"},	
   ]   
   접근방식: object[indexNumber].city  / object[indexNumber]["city"]


   $.ajax({
       type: 'post',   //타입 (get, post, put 등)
       url: '/test'.   //요철할 서버 url
       async: true, // 비동기화 여부(default : true)
       headers: {   // Http header
           "Content-Type" : "application/json",
           "X-HTTP-Method-Override" : "POST"
       },
       dataType : 'text,   //데이터 타입(html, xml, json, text 등등)
       data : JSON.stringify({   //보낼 데이터 (Object, String, Array)
           "no": no,
           "name" : name,
           "nick" : nick
       }),
       success: function(result){   //결과 성공 콜백함수
           console.log(result);
       },
       error: function(request, status, erro){   // 결과 에러 콜백함수
           console.log(error);
       }
   })    


   톰캣 오류시
   해당 프로젝트 마우스 오른쪽 누르고
   properties - project Facets - runtimes에 9.0버전 체크*/ -->
</body>
</html>