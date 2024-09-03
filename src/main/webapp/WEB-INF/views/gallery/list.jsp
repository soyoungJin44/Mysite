<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="http://localhost:8888${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="http://localhost:8888${pageContext.request.contextPath}/assets/css/gallery.css" rel="stylesheet" type="text/css">

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
				<h2>갤러리</h2>
				<ul>
					<li><a href="">일반갤러리</a></li>
					<li><a href="">파일첨부연습</a></li>
				</ul>
			</div>
			<!-- //aside -->
			
			<div id="content">
				<div id="content-head">
					<h3>일반갤러리</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>갤러리</li>
							<li class="last">일반갤러리</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->

				
				<div id="gallery">
					<div id="list">
				
						
							<button id="btnImgUpload">이미지올리기</button>
							<div class="clear"></div>
							
							<div id="myModal" class="modal">

                  				<div id="guestbook" class="modal-content">
                     				<button class="closeBtn" type="button">×</button>
                     				
                     				<div class="m-body">
                        				<label class="user-write" for="write-content" >글작성</label>
                        				<input id="write-content" type="text" name="content" value=""><br>
                        				
                        				<label class="user-photo" for="photo"> 이미지 선택</label>
                        				<input id="photo" type="file" name="orgName" value="">
                     				</div>
                     				<form id="btn-insertform" action="" method="">
                     					<div class="m-footer">
                        					<button id="btn-insert" class="btninsert" type="button">등록</button>
                    					</div>
                   					</form>
                  				</div>

               				</div>

				
						<ul id="viewArea">
							
							<!-- 이미지반복영역 -->
								<li>
									<div class="view" >
										<img class="imgItem" src="../../assets/image/Gangho-dong.jpg">
										<div class="imgWriter">작성자: <strong>유재석</strong></div>
									</div>
								</li>

								<li>
									<div class="view" >
										<img class="imgItem" src="../../assets/image/Jeongjae-Lee.jpg">
										<div class="imgWriter">작성자: <strong>유재석</strong></div>
									</div>
								</li>

								<li>
									<div class="view" >
										<img class="imgItem" src="../../assets/image/JeonSoMin.jpg">
										<div class="imgWriter">작성자: <strong>유재석</strong></div>
									</div>
								</li>

								<li>
									<div class="view" >
										<img class="imgItem" src="../../assets/image/JiseokJin.jpg">
										<div class="imgWriter">작성자: <strong>유재석</strong></div>
									</div>
								</li>

								<li>
									<div class="view" >
										<img class="imgItem" src="../../assets/image/JungWooSung.jpg">
										<div class="imgWriter">작성자: <strong>유재석</strong></div>
									</div>
								</li>
							<!-- 이미지반복영역 -->
							
							
						</ul>
					</div>
					<!-- //list -->
				</div>
				<!-- //	gallery -->

			</div>
			<!-- //content  -->

		</div>
		<!-- //container  -->
	

		<!-- 푸터부분 불러오기 -->
			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->
	</div>
	<!-- //wrap -->


<script>

document.addEventListener('DOMContentLoaded', function(){
	console.log('동트리 완성')
	
	//클릭 => 모달창 띄우기
	
	let btnupload = document.querySelector('#btnImgUpload');
	console.log(btnupload);
	btnupload.addEventListener('click', uploadready);
	
	//클릭 => 모달창닫기
	let btnTag = document.querySelector('.closeBtn');
	btnTag.addEventListener('click', byemodal);
	
	
	
	
	
	
	
	
// ########### 코드 모아두기 ##############
	
	//모달창 닫기
	function byemodal(event){
		console.log(event.target.tagName);
		
		if(event.target.tagName == 'BUTTON'){
			console.log('닫힐준비완??');
			let byebyemodal = document.querySelector('#myModal');
			byebyemodal.style.display = 'none';
		}
			
	}
	
	// 모달창 띄우기
	function uploadready(event){
		console.log(event.target.tagName);
		
		if(event.target.tagName == 'BUTTON'){
			console.log('버튼 꾸욱')
			let modalTag = document.querySelector('#myModal');
			console.log(modalTag);
			modalTag.style.display = 'block';
		}
			
		
	}
	
	
	
	
});
	
</script>	



</body>





</html>

