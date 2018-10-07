<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href='<c:url value="/webjars/bootstrap/3.3.6/css/bootstrap.min.css"/>'>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style type="text/css">
.container-fluid {
	margin: auto;
	margin-top: 20px;
}

table {
	text-align: center;
}

td {
	width: 150px;
	padding: 2px;
}

#menu {
	margin-bottom : 20px;
}

#searchDesc{
	margin-left: 300px;
}
</style>
</head>
<body>

	<header>
		<%@ include file="../include/header.jsp"%>
	</header>

	<form id="formHidden" method="get">
		<!-- jquery에서 수간호사 select list를 생성하기 위한 hidden tags-->
		<input type="hidden" id="headNum" name="headNum" value="${dto.headNum}"> <input
			type="hidden" id="nurseNum" name="nurseNum" value="${dto.nurseNum}"> <input
			type="hidden" id="assistNum" name="assistNum" value="${dto.assistNum}"> <input
			type="hidden" id="startDate" name="startDate" value="${dto.startDate}"> <input
			type="hidden" id="endDate" name="endDate" value="${dto.endDate}">
	</form>

	<!-- 테이블이 한 줄에 두개만 나오도록 -->
	<c:set var="tIndex" value="0">
	</c:set>

	<!-- list 호출을 위한 index 선언 -->
	<c:set var="nIndex" value="0"></c:set>
	
	
	<div class="container-fluid">
		
		<!-- menu -->
 		<div class="row" id="menu">
			<div class="col-sm-6">
				<select id="nurseCategory" onchange="categoryChange()">
					<option>선택</option>
					<option>수간호사</option>
					<option>일반 간호사</option>
					<option>간호 조무사</option>
					<option>전체보기</option>
				</select> 
				<select id="selectList">
					<option>---</option>
				</select>
				<button class="btn btn-warning" id="btnSearch">검색</button>
				<button class="btn btn-primary" id="btnHome">HOME으로 가기</button>
				
				<c:if test="${target != '' }">
					<span class="text-info" id="searchDesc">${target}의 일정</span>				
				</c:if>
			</div> 
			
		</div>
		
		
		
		<div class="row" >
			<c:forEach items="${list}" var="i">

				<div class="col-sm-6">
					<table class="table table-bordered">

						<!-- 출력할 년월 정보 -->
						<thead>
							<tr class="success">
								<td colspan="7">${i.year}년${i.month}월</td>
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
								<c:forEach begin="${i.startDate}" end="${i.endDate}" step="1"
									var="d">
										
										<!-- 근무 없는 날 근무인 날 td 따로 생성 -->
										<c:choose>
											<c:when test="${searchList[nIndex] == 'Rest' }">
											<td>
												<h5>${d}</h5>
											</c:when>
											<c:otherwise>
											<td class="info">
												<strong>${d }</strong>
												<h5 class="boundary">
													<c:choose>
														<c:when test="${searchList[nIndex] == 'N' }">
															<span class="text-success">
																N
															</span>
														</c:when>
														<c:when test="${searchList[nIndex] == 'D' }">
															<span class="text-danger">
																D
															</span>
														</c:when>
														<c:when test="${searchList[nIndex] == 'E' }">
															<span class="text-primary">
																E
															</span>
														</c:when>
													
													</c:choose>
												</h5>
											</c:otherwise>
										</c:choose>

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
					
					<c:set var="tIndex" value="${tIndex +1}"></c:set>
					<c:if test="${tIndex == 2 }">
						<c:set var="tIndex" value="0"></c:set>
						</div><div class="row">
					</c:if>
					
				</div>
			</c:forEach>
		</div>
	</div>

</body>
<script type="text/javascript"
	src="<c:url value="/webjars/jquery/1.9.1/jquery.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/webjars/bootstrap/3.3.6/js/bootstrap.min.js"/>"></script>
<script type="text/javascript">
	$(function() {
		
		function search() {
			
			var target = $("#selectList option:selected").val();
			var dto = $("#formHidden").serialize();
			
			if(target == '---' || target == null){
				alert("검색 조건을 선택해주세요.");
			} else{
				location.href = "${path}/search.do?"+ dto + "&target=" + target;
			}
		}// search ends
		
		$("#btnHome").click(function() {
			location.href = "${path}/";
		});// home ends
		
		$("#btnSearch").click(function() {
			search();
		});// search ends
		
	})//document ready

	function categoryChange() {
		
		// headNum 만큼 h1, h2... 생성
		var headNum = $("#headNum").val();
		var head = [];
		
		// 간호사와 간호조무사는 1~4팀
		var nurse = [ "N1", "N2", "N3", "N4" ];
		var assist = [ "A1", "A2", "A3", "A4" ];
		
		// 선택한 값 가져옴
		var select = $("#nurseCategory").val();
		
		// 값에 따라 배열 할당할 변수
		var list;
		
		if (select == "수간호사") {
			// 수간호사 리스트 생성
			for (var i = 0; i < headNum; i++) {
				head[i] = "H" + (i+1);
			}
			list = head;
			
		} else if (select == "일반 간호사") {
			list = nurse;
			
		} else if (select == "간호 조무사") {
			list = assist;
			
		} else if (select == "전체보기") {
			// 전체보기하면 다시 원래대로
			$("#formHidden").attr("action", "${path}/create.do");
			$("#formHidden").submit();
			return;
		} else {
			return;
		}

		$("#selectList").empty();
		
		var option;
		for (var i = 0; i < list.length; i++) {
			option += "<option >" + list[i] + "</option>";
		}
		
		$("#selectList").append(option);

	}//change ends
	
</script>


</html>