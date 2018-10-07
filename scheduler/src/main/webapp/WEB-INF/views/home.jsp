<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href='<c:url value="/webjars/bootstrap/3.3.6/css/bootstrap.min.css"/>'>
<link rel="stylesheet" type="text/css" href="<c:url value="/webjars/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker.css" />">
<meta name="viewport" content="width=device-width, initial-scale=1">

<style type="text/css">
	.container{
		margin: auto;
		margin-top: 20px;
	}
	
	#startDate, #endDate{
		display: inline;
		width : 200px;
	}
</style>
</head>
<body>
	
	<header>
		<%@ include file="../include/header.jsp" %>
	</header>
	
	<div class="container">
		<div class="col-sm-10"> 
		
			<!-- form class -->
			<form class="form-horizontal" method="get" id="inputForm">
			
				<!-- 간호사의 수 입력 -->
				<div class="col-sm-4">
				
					<!-- 수간호사의 수 -->
					<div class="form-group">
						<label class="control-label col-sm-6" >수간호사의 수 :</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" name="headNum" id="headNum">
						</div>
					</div>
					
					<!-- 일반간호사의 수 -->
					<div class="form-group">
						<label class="control-label col-sm-6">일반간호사의 수 :</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" name="nurseNum" id="nurseNum">
						</div>
					</div>
					
					<!-- 간호조무사의 수 -->
					<div class="form-group">
						<label class="control-label col-sm-6">간호조무사의 수 :</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" name="assistNum" id="assistNum">
						</div>
					</div>
					
				<button type="button" class="btn btn-primary" id="create">생성</button>
				<button type="button" class="btn btn-warning" id="initialize">초기화</button>								
				</div>
				
				<!-- 기간 입력 -->
				<div class="col-sm-8">
					<div class="form-group">
						<label class="control-label col-sm-2">기간 :</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" placeholder="시작일" id="startDate" name="startDate" readonly="readonly">
							~
							<input type="text" class="form-control" placeholder="종료일" id="endDate" name="endDate" readonly="readonly">
						</div>
					</div>
				</div> 
				
			</form>			
		</div>
		
		<div class="col-sm-4">
			<span class="help-block" id="help">
				
			</span>
		</div>
		
	</div>
	

</body>
<script type="text/javascript" src="<c:url value="/webjars/jquery/1.9.1/jquery.min.js"/>" ></script>
<script type="text/javascript" src="<c:url value="/webjars/bootstrap/3.3.6/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/webjars/jquery-ui/1.11.4/jquery-ui.js"/>"></script>
<script type="text/javascript" src="<c:url value="/webjars/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.js" />"></script>

<script type="text/javascript">
	$(function() {
		
		$("#initialize").click(function() {
			 $("#headNum").val('');
			 $("#nurseNum").val('');
			 $("#assistNum").val('');
			 $("#startDate").val('');
			 $("#endDate").val('');
		});//초기화
		
		// 유효성 검증
		function validCheck() {
			var headNum = $("#headNum").val();
			var nurseNum = $("#nurseNum").val();
			var assistNum = $("#assistNum").val();
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			
			$("#help").css("color", "red");
			
			var regNumber = /^[0-9]*$/;
			
			// null length type 확인
			if (!regNumber.test(headNum) || headNum < 2 || headNum > 50){
				$("#help").html("수간호사의 수는 2~50 사이의 정수입니다.");	
				$("#headNum").val('');
				$("#headNum").focus();
				return false;
			}
			
			if(!regNumber.test(nurseNum) || nurseNum < 4 || nurseNum > 1000){
				$("#help").html("일반간호사의 수는 4~1000 사이의 정수입니다.");
				$("#nurseNum").val('');
				$("#nurseNum").focus();
				return false;
			}
			
			if(!regNumber.test(assistNum) || assistNum < 4 || assistNum > 1000){
				$("#help").html("간호조무사의 수는 4~1000 사이의 정수입니다.");
				$("#assistNum").val('');
				$("#assistNum").focus();
				return false;
			}
			
			if(startDate == ''){
				$("#help").html("시작일을 선택해주세요.");
				return false;
			} else if(endDate == ''){
				$("#help").html("종료일을 선택해주세요");
				return false;
			}
			
			// 시작일 < 종료일인 경우
			// 시작일과 종료일이 1년 이내인 경우만 인정
			var sdate = new Date(startDate.substr(0,4), startDate.substr(4,2) -1, startDate.substr(6,2));
			var edate = new Date(endDate.substr(0,4), endDate.substr(4,2) -1, endDate.substr(6,2));
			
			var gap_years = edate.getFullYear() - sdate.getFullYear();
			var gap_months = edate.getMonth() - sdate.getMonth();
			
			var interval = gap_years*12 + gap_months;
			
			if(sdate.getTime() > edate.getTime()){
				$("#help").html("시작일이 종료일보다 클 수 없습니다.");
				$("#startDate").val('');
				$("#endDate").val('');
				return false;
			} else if(interval > 12){
				$("#help").html("최대 기간은 1년 입니다.");
				$("#startDate").val('');
				$("#endDate").val('');
				return false;
			}
			
			$("#help").html("");
			
			return true;
		}
		
		// startDate datepicker
		$("#startDate").datepicker({
			format : "yyyymmdd",
			autoclose : true,
			todayHighlight : true
		});
				
		// endDate datepicker
		$("#endDate").datepicker({
			format : "yyyymmdd",
			autoclose : true,
			todayHighlight : true,
		});
		
		$("#create").click(function() {
			if(validCheck()){
				$("#inputForm").attr("action", "${path}/create.do");
				$("#inputForm").submit();				
			}
		});
		
	});

</script>

</html>