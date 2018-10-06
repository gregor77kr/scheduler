<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href='<c:url value="/webjars/bootstrap/3.3.6/css/bootstrap.min.css"/>'>
<link rel="stylesheet" type="text/css" href="<c:url value="/webjars/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker.min.css" />">
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
<script type="text/javascript" src="<c:url value="/webjars/jquery-ui/1.11.4/jquery-ui.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/webjars/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.min.js" />"></script>

<script type="text/javascript">
	$(function() {
		
		function validCheck() {
			var headNum = $("#headNum").val();
			var nurseNum = $("#nurseNum").val();
			var assistNum = $("#assistNum").val();
			
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			
			$("#help").css("color", "red");
			
			var regNumber = /^[0-9]*$/;
			
			if (!regNumber.test(headNum) || headNum < 2){
				$("#help").html("수간호사의 수는 2 이상의 정수입니다.");	
				return false;
			}
			
			if(!regNumber.test(nurseNum) || nurseNum < 4){
				$("#help").html("일반간호사의 수는 4 이상의 정수입니다.");	
				return false;
			}
			
			if(!regNumber.test(assistNum) || assistNum < 4){
				$("#help").html("간호조무사의 수는 4 이상의 정수입니다.");	
				return false;
			}
			
			if(startDate == ''){
				$("#help").html("시작일을 선택해주세요.");
				return false;
			} else if(endDate == ''){
				$("#help").html("종료일을 선택해주세요");
				return false;
			}
			
			$("#help").html("");
			
			return true;
		}
		
		// startDate datepicker
		$("#startDate").datepicker({
			format : "yyyymmdd",
			autoclose : true,
			endDate : "today",
			todayHighlight : true

		});
		
		// endDate datepicker
		$("#endDate").datepicker({
			format : "yyyymmdd",
			autoclose : true,
			todayHighlight : true

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