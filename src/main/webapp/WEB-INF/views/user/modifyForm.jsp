<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="http://localhost:8888/mysite/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="http://localhost:8888/mysite/assets/css/user.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div id="wrap">
	
	<!-- 헤더부분 불러오기 -->
			<c:import url="/WEB-INF/views/include/header.jsp"></c:import>

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>회원</h2>
				<ul>
					<li>회원정보</li>
					<li>로그인</li>
					<li>회원가입</li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">
			
				<div id="content-head">
					<h3>회원정보</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>회원</li>
							<li class="last">회원정보</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				 <!-- //content-head -->
	
				<div id="user">
					<div id="modifyForm">
						<form action="${pageContext.request.contextPath}/user/modify" method="get">
	
							<!-- 아이디 -->
							<div class="form-group">
								<label class="form-text" for="input-uid">아이디</label> 
								<span class="text-large bold">${id.id}</span>
							</div>
	
							<!-- 비밀번호 -->
							<div class="form-group">
								<label class="form-text" for="input-pass">패스워드</label> 
								<input type="text" id="input-pass" name="password" value="${id.password }" placeholder=""	>
							</div>
	
							<!--이름 -->
							<div class="form-group">
								<label class="form-text" for="input-name">이름</label> 
								<input type="text" id="input-name" name="name" value="${id.name }" placeholder="">
							</div>
	
							<!-- //나이 -->
							<div class="form-group">
								<span class="form-text">성별</span> 
								
								<label for="rdo-male">남</label> 
								<input type="radio" id="rdo-male" name="gender" value="남" <c:if test="${id.gender == '남' }">checked </c:if>> 
								
								<label for="rdo-female">여</label> 
								<input type="radio" id="rdo-female" name="gender" value="여" <c:if test="${id.gender == '여' }">checked </c:if> > 
								<input type="hidden" name="no" value="${sessionScope.authUser.no}" >
							</div>
	
							<!-- 버튼영역 -->
							<div class="button-area">
								<button type="submit" id="btn-submit">회원정보수정</button>
							</div>
							
						</form>
					
					
					</div>
					<!-- //modifyForm -->
				</div>
				<!-- //user -->
			</div>
			<!-- //content  -->

		</div>
		<!-- //container  -->

			<!-- 푸터부분 불러오기 -->
			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		
		</div>
		<!-- //footer -->
		
	</div>
	<!-- //wrap -->

</body>

</html>