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

	<style>
		/* 모달창 배경 회색부분 */
		.modal {
		   width: 100%; /* 가로전체 */
		   height: 100%; /* 세로전체 */
		   display: none; /* 시작할때 숨김처리 */
		   position: fixed; /* 화면에 고정 */
		   left: 0; /* 왼쪽에서 0에서 시작 */
		   top: 0; /* 위쪽에서 0에서 시작 */
		   z-index: 999; /* 제일위에 */
		   overflow: auto; /* 내용이 많으면 스크롤 생김 */
		   background-color: rgba(0, 0, 0, 0.4); /* 배경이 검정색에 반투명 */
		}
		
		/* 모달창 내용 흰색부분 */
		.modal .modal-content {
		   width: 400px;
		   margin: 100px auto; /* 상하 100px, 좌우 가운데 */
		   padding: 0px 20px 20px 20px; /* 안쪽여백 */
		   background-color: #ffffff; /* 배경색 흰색 */
		   border: 1px solid #888888; /* 테두리 모양 색 */
		}
		
		/* 닫기버튼 */
		.modal .modal-content .closeBtn {
		   text-align: right;
		   color: #aaaaaa;
		   font-size: 28px;
		   font-weight: bold;
		   cursor: pointer;
		   border: none;
		}
	</style>





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
					
					<!-- 모달 창 컨텐츠 -->
               		<div id="myModal" class="modal">

                  		<div id="guestbook" class="modal-content">
                     		<button class="closeBtn" type="button">×</button>
                     		<div  class="m-header">패스워드를 입력하세요</div>
                     		<div class="m-body">
                        		<input id="modalPw" class="m-password" type="password" name="password" value=""><br> <input id="ModalNo" class="m-no" type="text" name="no" value="">
                     		</div>
                     	
                     	<div class="m-footer">
                        	<button id="btn-delete" class="btnDelete" type="button">삭제</button>
                    	</div>
                  		</div>

               		</div>
					
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
	document.addEventListener('DOMContentLoaded',function(){
		
		//////////////////////////////// 리스트 가져오기///////////////////////////////////////////
		getListRender();
		
		//*************** 전송버튼(form, submit) 클릭 했을 때 *********************
		
		let guestbookForm = document.querySelector('#guestbookForm');
		guestbookForm.addEventListener('submit', addRender );
		

		//////모달창 띄우기 버튼 클릭이벤트 등록 ///
		let listArea = document.querySelector('#guestbookListArea');
		listArea.addEventListener('click', callModal );
		
		// 모달창 닫기버튼 이벤트
		let closeBtn = document.querySelector('.closeBtn');
		closeBtn.addEventListener('click', closeModal );
		
		// 삭제
		
		let btnDelete = document.querySelector('#btn-delete')
		console.log('딜리트버튼 잡기');
		
		btnDelete.addEventListener('click', deleteRemove );
		
		
	});
	
	
	
	
	// ####################### << 코드 정리 >> ##########################
	
	//딜리트
	
	function deleteRemove(event){
		console.log('ddddd');
		
		let modalPwTag = document.querySelector('#modalPw');
		let modalNoTag = document.querySelector('#ModalNo');
		
		let password = modalPwTag.value;
		let no = modalNoTag.value;
		
		let guestbookVo = {
				no: no,
				password: password
				
		};
		console.log(guestbookVo);
		
		// 디비에 보내서 삭제
		
		axios({
	        method: 'get',           // put, post, delete                   
	        url: '/mysite/api/guestbooks/remove',  //api : 데이터만 오게하는주소다 라고 지어준이름 ,, list 가져올거다
	        headers: {"Content-Type" : "application/json; charset=utf-8"}, //전송타입
	        params: guestbookVo,  //get방식 파라미터로 값이 전달
	        //data: guestbookVo,   //put, post, delete 방식 자동으로 JSON으로 변환 전달
	    
	        responseType: 'json' //수신타입
	    }).then(function (response) {
	        console.log(response.data); //수신데이타
	        
	        if(response.data == 1){
	        	// 화면에서 지우는 작업 해주기
	        	let delId = '#t-' + no;
	        	
	        	let removeTable = document.querySelector(delId);
	        	console.log(removeTable)
	        	
	        	//해당 테이블 지우기
	        	removeTable.remove();
	        	closeModal();
	        	
	        	
	        	
	        }else{
	        	
	        	alert('비번 틀림요')
	        	//모달창 닫기 기능 넣어주기
	        }
	     
	    }).catch(function (error) {
	        console.log(error);
	    });
		
	}
	
	
	
	
	
	
	
	
	
	
	//madal창 관리
	// -- 창 띄우기
		
	function callModal(event){
		console.log(event.target.tagName)
		
		if(event.target.tagName == 'BUTTON'){
			console.log('버튼 꾸욱')
			
			//버튼안에 태그에서 no값 가져오기
			let no = event.target.dataset.no;
			console.log(no);
			
			//모달창의 input태그 value에 버튼안에 no값 넣어주기
			let textTag =  document.querySelector('#ModalNo');
			textTag.value = no;
			
			// 모달창 띄우게 처리해주기 [css에 display를 block으로 변경해주기]
			let modalTag = document.querySelector('#myModal');
			modalTag.style.display = 'block';
			
			let passwordbox = document.querySelector('#modalPw');
			//console.log(passwordbox);
			passwordbox.value = "";
			
		};
		
	};
	
	//모달 창 닫기
	function closeModal(event){
		
		let modalTag = document.querySelector('#myModal')
		modalTag.style.display = 'none';
		
	};
	
	
	
	
	// listRender (리스트 가져와서 그리기)

	function getListRender(){
		
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
	        	render(guestbookVo, 'up');
	        	
	        }
	        
	    }).catch(function (error) {
	        console.log(error);
	    });
		
	}
	
	//이벤트 작동할 때 
	
	function addRender(event){
		
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
	        console.log(response.data);
	        
	        //입력받은 값은 response.data에 있기때문에
	        render(response.data, 'down');
	        
	        //화면에 있는 값 지우기
	        guestbookForm.reset();
	        
	        
	    }).catch(function (error) {
	        console.log(error);
	    
	    });
}
	
	
	
	//1개 그리기(리스트)
	function render(guestbookVo, dir){
		let listArea = document.querySelector('#guestbookListArea');
		console.log(guestbookVo)
		
		let str = '';
		
		str += '<table id="t-'+ guestbookVo.no +'" class="guestRead">';
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
		str += '		<td><button class="callModal" type="button" data-no="'+ guestbookVo.no +'"> 삭제(모달창 보이기) </button></td>'; //id로 주게되면 반복문 돌리면서 여러개가 되기때문에 class가 맞다.
		str += '	</tr>';
		str += '	<tr>';
		str += '		<td colspan=4 class="text-left">'+ guestbookVo.content +'</td>';
		str += '	</tr>';
		str += '</table>';
			
		if(dir == 'down'){
			listArea.insertAdjacentHTML('beforeend', str);
		}else if (dir == 'up'){
			listArea.insertAdjacentHTML('afterbegin', str);
		}else{
			console.log('방향체크 해주세용');
		}
};
		
		
	</script>
	
	
	
	
</body>

</html>