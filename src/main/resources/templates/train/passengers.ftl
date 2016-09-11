<link rel="stylesheet" type="text/css" href="${request.contextPath}/static/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/static/css/bootstrap-theme.min.css" />
<script type="text/javascript">
$(function(){
	$("#choicePassenger").click(function(){
		var passengerTicketStr="";
		var oldPassengerStr="";
		var html="";
		var seatType=$("input[name='seatType']:checkbox:checked").val();
		$("input[name='passenger']:checkbox:checked").each(function(){
			passengerTicketStr+=seatType+",0,"+$(this).val()+","+$(this).attr("mobile_no")+",N_";
			oldPassengerStr+=$(this).val().substring(2)+",1_";
			var type=$("input[name='seatType']:checkbox:checked").attr("types")
			html+="<label for='avail_ticket' style='cursor: pointer;'>乘客名："+$(this).attr("passenger_name")+"   座位类型："+type+"</label>  ";
		});
		passengerTicketStr=passengerTicketStr.substring(0,passengerTicketStr.length-1);
		$("#passengerTicketStr").val(passengerTicketStr);
		$("#oldPassengerStr").val(oldPassengerStr);
		$('#myModal').modal('hide');
		$(".fr").html(html);
	})
});
</script>
<table width="80%" style="margin: 0 auto;" class="table table-bordered" id="passenger">
	<tr align="center">
		<td><input type="button" class="btn btn-default" role="button" id="choicePassenger" value="选择"></td>
		<td colspan="6">座位类型：
						<input type="checkbox" value="9" name="seatType" types="商务座"/>商务座
						<input type="checkbox" value="P" name="seatType" types="特等座" />特等座
						<input type="checkbox" value="M" name="seatType" types="一等座" />一等座
						<input type="checkbox" value="O" name="seatType" types="二等座" />二等座
						<input type="checkbox" value="6" name="seatType" types="高级软卧" />高级软卧
						<input type="checkbox" value="4" name="seatType" types="软卧" />软卧
						<input type="checkbox" value="3" name="seatType" types="硬卧" />硬卧
						<input type="checkbox" value="2" name="seatType" types="软座" />软座
						<input type="checkbox" value="1" name="seatType" types="硬座" checked="checked" />硬座</td>
	</tr>
	<tr align="center">
		<td>序号</td>
		<td>姓名</td>
		<td>证件类型</td>
		<td>证件号码</td>
		<td>手机/电话</td>
		<td>旅客类型</td>
		<td>核验状态</td>
	</tr>
	<#if !list??>
		<script type="text/javascript">
			alert("登录已过期，请重新登录");
			login();
		</script>
	</#if>
	<#if list??>
	 <#list list as passenger>
		<tr align="center">
			<td><input type="checkbox" name="passenger" passenger_name="${passenger.passenger_name}" value="${passenger.passenger_type},${passenger.passenger_name},${passenger.passenger_id_type_code},${passenger.passenger_id_no}" mobile_no="${passenger.mobile_no}"/>${passenger_index+1}</td>
			<td>${passenger.passenger_name }</td>
			<td>${passenger.passenger_id_type_name}</td>
			<td>${passenger.passenger_id_no }</td>
			<td>${passenger.mobile_no }${passenger.phone_no }</td>
			<td>${passenger.passenger_type_name}</td>
			<td>
				<#if passenger.passenger_flag=='0'>已通过</#if>
				<#if passenger.passenger_flag!='0'>未通过</#if>
			</td>
		</tr>
	 </#list>
	</#if>
</table>
