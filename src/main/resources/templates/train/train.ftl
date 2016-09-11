<link rel="stylesheet" type="text/css" href="${request.contextPath}/static/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${request.contextPath}/static/css/bootstrap-theme.min.css" />
<script type="text/javascript" src="${request.contextPath}/static/js/train_utils.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/train_list.js"></script>
<#if list?? && (list?size>0) >
共计<span class="trainSize">${list?size}</span>个车次
<span class="fr"><input type="checkbox" class="check" id="avail_ticket"> 
	<label for="avail_ticket" style="cursor: pointer;">显示全部可预订车次 </label> 
</span>
</#if>
<style>
#train td{
vertical-align:middle; text-align:center;
}
</style>
<table style="margin: 0 auto;" class="table table-bordered" id="train">
<thead align="center">
	<td><input type="checkbox">车次</td>
	<td>出发站<br />到达站
	</td>
	<td>出发时间<br />到达时间
	</td>
	<td id="stratTimeSort">历时</td>
	<td>商务座</td>
	<td>特等座</td>
	<td>一等座</td>
	<td>二等座</td>
	<td>高级软卧</td>
	<td>软卧</td>
	<td>硬卧</td>
	<td>软座</td>
	<td>硬座</td>
	<td>无座</td>
	<td>其他</td>
	<td>备注</td>
</thead>
<tbody id="tbody">
<#if list??>
 <#list list as train>
	<tr align="center">
		<td><input type="checkbox" class="trainCode" value="${train.queryLeftNewDTO.station_train_code }">${train.queryLeftNewDTO.station_train_code }</td>
		<td><#if train.queryLeftNewDTO.from_station_name==train.queryLeftNewDTO.start_station_name>始</#if>
			${train.queryLeftNewDTO.from_station_name }<br />
			<#if train.queryLeftNewDTO.to_station_name==train.queryLeftNewDTO.end_station_name>终</#if>
			${train.queryLeftNewDTO.to_station_name }
		</td>
		<td>${train.queryLeftNewDTO.start_time }<br />${train.queryLeftNewDTO.arrive_time}
		</td>
		<td id="lishi" lishi="${train.queryLeftNewDTO.lishi}">${train.queryLeftNewDTO.lishi}</td>
		<td>${train.queryLeftNewDTO.swz_num }</td>
		<td>${train.queryLeftNewDTO.tz_num }</td>
		<td>${train.queryLeftNewDTO.zy_num }</td>
		<td>${train.queryLeftNewDTO.ze_num }</td>
		<td>${train.queryLeftNewDTO.gr_num }</td>
		<td>${train.queryLeftNewDTO.rw_num }</td>
		<td>${train.queryLeftNewDTO.yw_num }</td>
		<td>${train.queryLeftNewDTO.rz_num }</td>
		<td>${train.queryLeftNewDTO.yz_num }</td>
		<td>${train.queryLeftNewDTO.wz_num }</td>
		<td>${train.queryLeftNewDTO.qt_num }</td>
		<td id="secretStr" secretStr="${train.secretStr}"><#if train.secretStr==''>${train.buttonTextInfo}  </#if>
		<#if train.secretStr!=''><input type="button" class="btn btn-default" role="button" onclick="submitOrderRequest('${train.secretStr}','${train.queryLeftNewDTO.start_time }','${train.queryLeftNewDTO.train_no}','${train.queryLeftNewDTO.from_station_telecode}','${train.queryLeftNewDTO.to_station_telecode}','${train.queryLeftNewDTO.yp_info}','${train.queryLeftNewDTO.from_station_name}','${train.queryLeftNewDTO.to_station_name}','${train.queryLeftNewDTO.location_code}','${train.queryLeftNewDTO.station_train_code}')"  value="${train.buttonTextInfo}"></#if>
		</td>
	</tr>
 </#list>
</#if>
</tbody>
</table>