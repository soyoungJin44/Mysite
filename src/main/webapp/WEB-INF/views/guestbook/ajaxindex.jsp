<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="http://localhost:8888${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="http://localhost:8888${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">

<!-- Axios 라이브러리 포함 -->
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>


</head>

<body>
	<div id="wrap">

		<!-- 헤더부분 불러오기 -->
			<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
	
		<div id="container" class="clearfix">
			<div id="aside">
				<h2>방명록</h2>
				<ul>
					<li>일반방명록</li>
					<li>ajax방명록</li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">
				
				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<form id="guestbookForm" action="${pageContext.request.contextPath}/api/guestbook/write" method="">
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">이름</label></td>
									<td><input id="input-uname" type="text" name="name" value="" ></td>
									<th><label class="form-text" for="input-pass" >패스워드</label></td>
									<td><input id="input-pass"type="password" name="pass" value="" ></td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center"><button type="submit">등록</button></td>
								</tr>
							</tbody>
							
						</table>
						<!-- //guestWrite -->
						<input type="hidden" name="action" value="add">
						
					</form>	
					
					<!-- //guestRead -->
					<div id="guestbookListArea">
						afterbegin(시작후)
					</div>
					<!-- //guestRead -->
					
					
				</div>
				<!-- //guestbook -->
			
			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<!-- 푸터부분 불러오기 -->
			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
	</div>
	<!-- //wrap -->
	
	<script>
	document.addEventListener('DOMContentLoaded', function(){
		
		console.log('ok');
		
		//####서버로 데이터 요청 => 데이터만 줘  // 지금까지는 화면+데이터 줘
		
		axios({
	        method: 'get',           // put, post, delete                   
	        url: '/mysite/api/guestbooks/list',  //api : 데이터만 오게하는주소다 라고 지어준이름 ,, list 가져올거다
	        headers: {"Content-Type" : "application/json; charset=utf-8"}, //전송타입
	        //params: guestbookVo,  //get방식 파라미터로 값이 전달
	        //data: guestbookVo,   //put, post, delete 방식 자동으로 JSON으로 변환 전달
	    
	        responseType: 'json' //수신타입
	    }).then(function (response) {
	        console.log(response.data); //수신데이타
	     
	        for(let i=0; i<response.data.length; i++){
	        	//console.log(response.data[i].name);
	        	
	        	let guestbookVo = response.data[i]
	        	render(guestbookVo);
	        	
	        }
	        
	        
	     
	    
	    }).catch(function (error) {
	        console.log(error);
	    
	    });
		
		//*************** 전송버튼(form, submit) 클릭 했을 때 *********************
		
		let guestbookForm = document.querySelector('#guestbookForm');
		guestbookForm.addEventListener('submit',function(){
			
			event.preventDefault();
			console.log('dddd123')
			
			//############## 이름 뽑아오기 #################
			let name = document.querySelector('#input-uname').value;
			console.log(name)
			
			let password = document.querySelector('#input-pass').value;
			console.log(password);
			
			let content = document.querySelector('textarea[name="content"]').value;
			console.log(content);
			
			let guestbookVo = {
					name: name,
					password: password,
					content: content
			};
			
			console.log(guestbookVo);
			
			//전송
			
			axios({
		        method: 'get',           // put, post, delete                   
		        url: '${pageContext.request.contextPath}/api/guestbooks/write',
		        headers: {"Content-Type" : "application/json; charset=utf-8"}, //전송타입
		        params: guestbookVo,  //get방식 파라미터로 값이 전달
		        //data: guestbookVo,   //put, post, delete 방식 자동으로 JSON으로 변환 전달
		    
		        responseType: 'json' //수신타입
		    }).then(function (response) {
		        console.log(response); //수신데이타
		    
		    }).catch(function (error) {
		        console.log(error);
		    
		    });
			
		});
		
		
		
		
	});
	
	

	
	//그리기(리스트)
	function render(guestbookVo){
		let listArea = document.querySelector('#guestbookListArea');
		console.log(guestbookVo)
		
		let str = '';
		
		str += '<table class="guestRead">';
		str += '	<colgroup>';
		str += '		<col style="width: 10%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 10%;">';
		str += '	</colgroup>';
		str += '	<tr>';
		str += '		<td>'+ guestbookVo.no + '</td>';
		str += '		<td>'+ guestbookVo.name +'</td>';
		str += '		<td>'+ guestbookVo.reg_date +'</td>';
		str += '		<td><a href=""> [삭제] </a></td>';
		str += '	</tr>';
		str += '	<tr>';
		str += '		<td colspan=4 class="text-left">'+ guestbookVo.content +'</td>';
		str += '	</tr>';
		str += '</table>';
			
		listArea.insertAdjacentHTML('afterbegin', str);	
};
		
		
	
	
	
	
	
	</script>
	
	
	
	
</body>

</html>