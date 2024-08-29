<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

		<div id="header" class="clearfix">
			<h1>
				<a href="http://localhost:8888${pageContext.request.contextPath}/main"> MySite </a>
			</h1>
			
			<!-- 로그인ㅇㅇ -->
			<c:if test="${sessionScope.authUser != null}">
			
				<ul>
					<li>${sessionScope.authUser.name} 님 안녕하세열 ^오^</li>
					<li><a href="http://localhost:8888${pageContext.request.contextPath}/user/logout" class="btn_s">로그아웃</a></li>
				<li><a href="http://localhost:8888${pageContext.request.contextPath}/user/modifyform" class="btn_s">회원정보수정</a></li>
			</ul>		
			
			<!-- 로그인 ㄴㄴ -->
			</c:if>
			
			<c:if test="${sessionScope.authUser == null}">
			
				<ul>
					<li><a href="http://localhost:8888${pageContext.request.contextPath}/loginform" class="btn_s">로그인</a></li>
					<li><a href="http://localhost:8888${pageContext.request.contextPath}/joinform" class="btn_s">회원가입</a></li>
				</ul>
			</c:if>
			
			
		</div>
		<!-- //header -->

		<div id="nav">
			<ul class="clearfix">
				<li><a href="">입사지원서</a></li>
				<li><a href="http://localhost:8888${pageContext.request.contextPath}/board/list">게시판</a></li>
				<li><a href="">갤러리</a></li>
				<li><a href="http://">방명록</a></li>
			</ul>
		</div>
		<!-- //nav -->
