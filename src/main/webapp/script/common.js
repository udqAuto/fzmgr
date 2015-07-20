/*
 * 
 * 通用
 * 
 * 
 * 
 */
/**
 * 当前用户
 */


function getUrlArgs() {
	var url = decodeURI(location.search);
	// var url = location.search; //获取url中"?"符后的字串
	var theRequest = new Object();
	if (url.indexOf("?") != -1) {
		var str = url.substr(1);
		strs = str.split("&");
		for ( var i = 0; i < strs.length; i++) {
			theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);

		}
	}
	return theRequest;
}

function reLogin() {
	var top = getTopWindow();
	top.location.href = 'login.jsp';
}

function getTopWindow() {
	var p = window;
	while (p != p.parent) {
		p = p.parent;
	}
	return p;
}

function loadStockOrg() {

	var orgs = [];

	$.ajax({
		url : 'organization/loadStockOrgList.do',
		type : "POST",
		dataType : "json",
		async : false,
		success : function(req) {
			if (req.isSuccess) {
				orgs = req.rows;
			}
		}
	});
	return orgs;
}
function loadAllOrgById(oId){
	var orgs = []; 
	$.ajax({
		url : 'organization/getOrgListById.do?orgId='+oId,
		type : "POST",
		dataType : "json",
		async : false,
		success : function(req) {
			if (req.isSuccess) {
				orgs = req.rows;
			}
		}
	});
	return orgs;
}
function loadAllOrg() {

	var orgs = [];

	$.ajax({
		url : 'organization/getList.do',
		type : "POST",
		dataType : "json",
		async : false,
		success : function(req) {
			if (req.isSuccess) {
				orgs = req.rows;
			}
		}
	});
	return orgs;
}
function getCurrentUser() {
	var user = {};
	$.ajax({
		url : 'user/getCurrentUser.do',
		type : "POST",
		dataType : "json",
		async : false,
		success : function(req) {
			if (req.isSuccess) {
				user = req.data;
				
			} else {
				alert(req.message);
			}
		}
	});
	return user;
}

function gRequestData(req) {
	var data = {};
	if (req.isSuccess) {
		data = req.data;
	} else if (req.isSessionExpired) {
		reLogin();
	} else {
		$.messager.alert("提示", req.msg, "error");
	}
	return data;
}

function getCurrentTime() {
	var user = [];
	$.ajax({
		url : 'general/getCurrentTime.do',
		type : "POST",
		dataType : "json",
		async : false,
		success : function(req) {
			if (req.isSuccess) {
				user = req.data;
			} else {

			}
		}
	});
	return user;
}

/**
 * 将后台的org列表转换成tree格式
 * 
 * @param items
 */
function buildTree(items) {
	var ss = [];

	if (items == null || items.length == 0) {
		return ss;
	}
	var count = items.length;
	/**
	 * 如果已经排过序的话，第一个肯定在根节点上 以这个parentid作为后续rog是否在根节点上的依据。
	 */
	var rootParent = items[0].parentId;

	for ( var i = 0; i < count; i++) {
		var node = items[i];
		node.text = node.name;
		node.children = [];
		node.children2 = [];

		if (node.parentId == rootParent) {
			node.parentObj = rootParent;
			node.nodeLevel = 1;
			ss.push(node);
		}

		for ( var j = 0; j < count; j++) {
			var tmp = items[j];
			if (tmp.parentId == node.id) {
				tmp.parentObj = node;
				tmp.nodeLevel = node.nodeLevel + 1;
				node.children.push(tmp);
				node.children2.push(tmp);
			}
		}

	}

	return ss;
}

function getBaseData(urlStr, name, id) {
	$.ajax({
		url : urlStr,
		type : "POST",
		dataType : "json",
		async : false,
		success : function(req) {
			var html = "<option value=\"0\">请选择" + name + "</option>";
			if (req.length > 0) {
				for ( var i = 0; i < req.length; i++) {
					html += "<option value=\"" + req[i].id + "\">"
							+ req[i].name + "</option>";
				}
			}
			$("#" + id).append(html);
		}
	});
}

function getBaseDataCombobox(urlStr, id) {
	$("#" + id).combobox({
		valueField : 'id',
		textField : 'name',
		async : false,
		url : urlStr
	});
}
/**
 * 日期比较
 * 
 * @param interval
 * @param objDate
 * @returns
 */
Date.prototype.dateDiff = function(interval, objDate) {
	var dtEnd = new Date(objDate);
	if (isNaN(dtEnd))
		return undefined;
	switch (interval) {
	case "s":
		return parseInt((dtEnd - this) / 1000);
	case "n":
		return parseInt((dtEnd - this) / 60000);
	case "h":
		return parseInt((dtEnd - this) / 3600000);
	case "d":
		return parseInt((dtEnd - this) / 86400000);
	case "w":
		return parseInt((dtEnd - this) / (86400000 * 7));
	case "m":
		return (dtEnd.getMonth() + 1)
				+ ((dtEnd.getFullYear() - this.getFullYear()) * 12)
				- (this.getMonth() + 1);
	case "y":
		return dtEnd.getFullYear() - this.getFullYear();
	}
	;
};
/**
 * 返回日期是否相差天数，忽略时，分，秒
 */
Date.prototype.dateDiffOfDay = function(endDate) {
	var dtBegin = new Date(this.getFullYear(), this.getMonth(), this.getDate());
	var dtEnd = new Date(endDate.getFullYear(), endDate.getMonth(), endDate
			.getDate());
	return dtBegin.dateDiff('d', dtEnd);
};

Date.prototype.add = function(interval, number) {
	switch (interval.toLowerCase()) {
	case "y":
		return new Date(this.setFullYear(this.getFullYear() + number));
	case "m":
		return new Date(this.setMonth(this.getMonth() + number));
	case "d":
		return new Date(this.setDate(this.getDate() + number));
	case "w":
		return new Date(this.setDate(this.getDate() + 7 * number));
	case "h":
		return new Date(this.setHours(this.getHours() + number));
	case "n":
		return new Date(this.setMinutes(this.getMinutes() + number));
	case "s":
		return new Date(this.setSeconds(this.getSeconds() + number));
	case "l":
		return new Date(this.setMilliseconds(this.getMilliseconds() + number));
	}
};

Date.prototype.toSimpleString = function() {
	var y = this.getFullYear();
	var m = this.getMonth() + 1;
	var d = this.getDate();
	var hh = this.getHours();
	var mm = this.getMinutes();
	return y + "-" + m + "-" + d + " " + hh + ":" + mm;
};

Date.prototype.toYMD = function() {
	var y = this.getFullYear();
	var m = this.getMonth() + 1;
	var d = this.getDate();

	return "" + y + (m > 10 ? m : "0" + m) + (d > 10 ? d : "0" + d);
};

function dateAdd(interval, number, date) {
	switch (interval.toLowerCase()) {
	case "y":
		return new Date(date.setFullYear(date.getFullYear() + number));
	case "m":
		return new Date(date.setMonth(date.getMonth() + number));
	case "d":
		return new Date(date.setDate(date.getDate() + number));
	case "w":
		return new Date(date.setDate(date.getDate() + 7 * number));
	case "h":
		return new Date(date.setHours(date.getHours() + number));
	case "n":
		return new Date(date.setMinutes(date.getMinutes() + number));
	case "s":
		return new Date(date.setSeconds(date.getSeconds() + number));
	case "l":
		return new Date(date.setMilliseconds(date.getMilliseconds() + number));
	}
};

function gYmdToStr(ymd) {
	var str = ymd.toString();
	var str2 = str.substr(0, 4) + "-" + str.substr(4, 2) + "-"
			+ str.substr(6, 2);

	return str2;
};

function DateHelp() {

	function intervalDay(d1, d2) {

	}
};

function gCreateDate(dateStr) {
	return new Date(Date.parse(dateStr.replace(/-/g, "/")));
}

function isSignIn(callback) {

	

}

function getCurrentUser(){
	var user=null;
	$.ajax({
		url : "index/isSignIn.do",
		type : "POST",
		dataType : "json",
		async : false,
		success : function(req) {
			if (req.isSuccess) {
				user =req.data;
			} else {
				$.messager.alert("系统提示", "获取用户登录信息失败，请重新登录", "info");
				top.location.href = "login.jsp";
			}
		}
	});
	return user;
}

function checkAuthorize2(key){
	var user=getCurrentUser();
	var isAuthorize=false;
	$.ajax({
		url : "authorize/isAuthorize.do",
		type : "POST",
		dataType : "json",
		data:{'userId':user.id,'key':key},
		async : false,
		success : function(req) {
			if (req.isSuccess) {
				isAuthorize=req.data;
			} else {
				$.messager.alert('提示ʾ', req.msg, "warning");
			}
		}
	});
	if(!isAuthorize){
		$.messager.alert('提示ʾ', "你没有权限", "warning");
	}
	return isAuthorize;
}

function checkAuthorize(key,userId){
	var isAuthorize=false;
	$.ajax({
		url : "authorize/isAuthorize.do",
		type : "POST",
		dataType : "json",
		data:{'userId':userId,'key':key},
		async : false,
		success : function(req) {
			if (req.isSuccess) {
				isAuthorize=req.data;
			} else {
				$.messager.alert('提示ʾ', req.msg, "warning");
			}
		}
	});
	return isAuthorize;
}