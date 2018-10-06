<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href='<c:url value="/webjars/bootstrap/3.3.6/css/bootstrap.min.css"/>'>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style type="text/css">
	.container-fluid {
		margin: auto;
		margin-top: 20px;
	}
	
	table{
		text-align: center;
	}
	
	td{
		width: 150px;
		padding : 2px;
	}
	
	.boundary{
		
	}
</style>
</head>
<body>
	
	<header>
		<%@ include file="../include/header.jsp" %>
		<button class="btn btn-primary" id="btnHome">HOME으로 가기</button>
	</header>
	
	<!-- 테이블이 한 줄에 두개만 나오도록 -->
	<c:set var="tIndex" value="0"> </c:set>
	
	<!-- list 호출을 위한 index 선언 -->
	<c:set var="nIndex" value="0"></c:set> 
	
	<div class="container-fluid">
			<div class="row">
			<c:forEach items="${list}" var="i">
				
				<div class="col-sm-6">
					<table class="table table-bordered table-striped" >
					
						<!-- 출력할 년월 정보 -->
						<thead>
							<tr>
								<td colspan="7">${i.year}년 ${i.month}월</td>
							</tr>
							
						<!-- 날짜 구분 -->	
							<tr>
								<td>일</td>
								<td>월</td>
								<td>화</td>
								<td>수</td>
								<td>목</td>
								<td>금</td>
								<td>토</td>
							</tr>
						</thead>
						
						<!-- 날짜 그리기 -->
						<tbody>
							<tr>
								<!-- 기간 시작하는 날 앞까지는 공백처리  -->
								<c:forEach begin="1" end="${i.startDay -1}" step="1">
									<td></td>
								</c:forEach>

								<!-- 달력 줄바꿈을 위한 변수 index 선언 -->
								<c:set var="index" value="${i.startDay}"></c:set>
								
								
								<!-- 그 뒤로 마지막 날까지 날짜 생성  -->
								<c:forEach begin="${i.startDate}" end="${i.endDate}" step="1" var="d">
									<td>
										<h5> ${d} </h5>
										
										<h5 class="boundary"> 
											N : 
											<span class="text-success">
												 ${nurseList[nIndex].night }
											</span>
											<span class="text-danger">
												${assistList[nIndex].night }
											</span>
										</h5>
										
										<h5 class="boundary"> 
											D : 
											<span class="text-success">
												 ${nurseList[nIndex].day }
											</span>
											<span class="text-danger">
												${assistList[nIndex].day }
											</span>
											<span class="text-primary">
												${headList[nIndex].day }
											</span>
										</h5>
										
										<h5 class="boundary"> 
											E : 
											<span class="text-success">
												 ${nurseList[nIndex].evening }
											</span>
											<span class="text-primary">
												${assistList[nIndex].evening }
											</span>
										</h5>

									</td>
									<!-- index += index + 1 -->
									<c:set var="index" value="${index + 1 }"></c:set>
									<c:set var="nIndex" value="${nIndex + 1 }"></c:set>
										
									<!-- 토요일인 경우 다음주  줄바꿈을 위해 새로운 tr 생성 -->
									<c:if test="${(index -1) % 7 ==0 }">
										</tr><tr>
									</c:if>
									
								</c:forEach>						
							</tr>
						</tbody>
					</table>				
					<c:set var="tIndex" value="${tIndex +1}"> </c:set>
					<c:if test="${tIndex == 2 }">
						</div><div class="row">
					</c:if>
				</div>
			</c:forEach>
		
		</div>
	</div>

</body>
<script type="text/javascript" src="<c:url value="/webjars/jquery/1.9.1/jquery.min.js"/>" ></script>
<script type="text/javascript" src="<c:url value="/webjars/bootstrap/3.3.6/js/bootstrap.min.js"/>"></script>
<script type="text/javascript">
	$(function() {
		$("#btnHome").click(function() {
			location.href = "${path}/";
		})
	})
</script>


</html>