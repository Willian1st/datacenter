<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<input name="id" style="display: none;" type="text"
	value="${dollar}record.id}">
#foreach ($column in $table.columns) #if ($column.type=='SELECT')
<div class="form-group">
	<label class="control-label col-xs-3">$column.remark#if
		($readonly!= 'true') #if ($column.nullable==0) <span
		class="text-danger"> *</span> #end #end
	</label>
	<div class="col-xs-4">
		<input class="form-control input-sm hidden" type="text"
			value="${dollar}record.$column.name}"> <select
			id="$column.select" #if ($readonly== 'true')disabled#end
			name="$column.name" class="input-sm form-control col-xs-4">
			<option value="">请选择</option>
			<c:forEach items="${dollar}$column.select}" var="item" varStatus="i">
				<option value="${dollar}item.key}"
					<c:if test="${dollar}record.$column.name eq item.key}">selected</c:if>>${dollar}item.value}</option>
			</c:forEach>
		</select>
	</div>
</div>
#end #if ($column.type=='UPLOAD')
<div class="form-group">
	<label class="control-label col-xs-3">$column.remark#if
		($readonly!= 'true') #if ($column.nullable==0) <span
		class="text-danger"> *</span> #end #end
	</label>
	<div class="col-xs-4">
		<input name="$column.name" class="form-control input-sm "
			type="hidden" value="${dollar}record.$column.name}">
		<div id="$column.name" command="uploadDiv"></div>
	</div>
</div>
#end #if ($column.type=='HIDE')
<div class="form-group hidden">
	<label class="control-label col-xs-3">$column.remark</label>
	<div class="col-xs-4">
		<input #if ($readonly== 'true')readonly#end name="$column.name"
			class="form-control input-sm #if ($column.type=='DATETIME')form_datetime#end"
			maxlength="$column.length" type="text"
			value="${dollar}record.$column.name}">
	</div>
</div>
#end #if ($column.type!='SELECT' && $column.type!='UPLOAD' &&
$column.type!='HIDE')
<div class="form-group">
	<label class="control-label col-xs-3">$column.remark#if
		($readonly!= 'true') #if ($column.nullable==0) <span
		class="text-danger"> *</span> #end #end
	</label>
	<div class="col-xs-4">
		#if ($column.type=='TEXT')
		<textarea #if ($readonly== 'true')readonly#end name="$column.name"
			rows="3"
			class="form-control input-sm #if ($column.type=='DATETIME')form_datetime#end #if ($column.nullable==0)required#end"
			maxlength="$column.length" type="text"
			#if ($column.nullable==0)required#end>${dollar}record.$column.name}</textarea>
		#end #if ($column.type!='TEXT') <input #if ($readonly==
			'true')readonly#end name="$column.name"
			class="form-control input-sm #if ($column.type=='DATETIME')form_datetime#end #if ($column.nullable==0)required#end"
			maxlength="$column.length"
			type="#if ($column.type=='INT')number#end#if ($column.type!='INT')text#end"
			value="${dollar}record.$column.name}"
			#if ($column.nullable==0)required#end> #end
	</div>
</div>
#end #end
