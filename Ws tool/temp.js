var code=$('[style="margin-left: 40px;"]');
var json=[];
$.each(code,function(i,v){
	var html=$(v).html();
	if(html.indexOf("账号：")!=-1){
		var name=html.substr(html.indexOf("账号：")+3,html.indexOf("密码：")-3);
		var code=html.substr(html.indexOf("密码：")+3);
		console.log(name);	
		console.log(code);
		json.push({
			name:name,
			code:code
		});
	}
	
});
console.log("account="+JSON.stringify(json));

var account=[{"name":"rdo038178@163.com","code":"yk111000"},{"name":"jptse57@163.com","code":"yk111000"},{"name":"zho051003@163.com","code":"yk111000"},{"name":"lei981543@163.com","code":"yk111000"},{"name":"jie270435@163.com","code":"yk111000"},{"name":"ton691072@163.com","code":"yk111000"},{"name":"gvd422821@163.com","code":"hmwb66"},{"name":"dss348934@163.com","code":"krmz81"},{"name":"wxs020894@163.com","code":"xfpx97"},{"name":"jvf760648@163.com","code":"kwec62"},{"name":"nkq687257@163.com","code":"ympl99"},{"name":"ozv204452@163.com","code":"zpti59"},{"name":"s10153447dute@163.com","code":"aaaa8888"},{"name":"tgav6549233liang@163.com","code":"aaaa8888"},{"name":"bmwug09508@163.com","code":"yk111000"},{"name":"guf929322@163.com","code":"yk111000"},{"name":"ren789339@163.com","code":"yk111000"},{"name":"qis425742@163.com","code":"yk111000"},{"name":"qis425742@163.com","code":"yk111000"},{"name":"smo464648@163.com","code":"yk111000"},{"name":"zjd602342@163.com","code":"yk111000"},{"name":"sia044068@163.com","code":"yk111000"},{"name":"egz790354@163.com","code":"yk111000"},{"name":"ben310925@163.com","code":"yk111000"},{"name":"wei927262@163.com","code":"yk111000"},{"name":"den796624@163.com","code":"yk111000"},{"name":"yin920489@163.com","code":"yk111000"},{"name":"lia349388@163.com","code":"yk111000"},{"name":"li3007173@163.com","code":"yk111000"},{"name":"wei539920@163.com","code":"yk111000"},{"name":"wei927262@163.com","code":"yk111000"},{"name":"den796624@163.com","code":"yk111000"},{"name":"yin920489@163.com","code":"yk111000"},{"name":"lia349388@163.com","code":"yk111000"},{"name":"li3007173@163.com","code":"yk111000"},{"name":"wei539920@163.com","code":"yk111000"},{"name":"bion07660962f@163.com","code":"aaaa8888"},{"name":"zpfp46615486@163.com","code":"aaaa8888"},{"name":"ti5178939dih@163.com","code":"aaaa8888"},{"name":"rjrv7800064lian@163.com","code":"aaaa8888"},{"name":"exot73901190@163.com","code":"aaaa8888"},{"name":"tmr21331106cheng@163.com","code":"aaaa8888"},{"name":"fuluxn159371@163.com","code":"abc58588"},{"name":"fuluxn937371@163.com","code":"abc58588"},{"name":"fuluyo604848@163.com","code":"abc58588"},{"name":"fumipf595826@163.com","code":"abc58588"},{"name":"funangw84826@163.com","code":"abc58588"},{"name":"midc5429159b@163.com","code":"aaaa8888"},{"name":"midc5429159b@163.com","code":"aaaa8888"},{"name":"ukg60791556mibei@163.com","code":"aaaa8888"},{"name":"fd3372060qieyexi@163.com","code":"aaaa8888"},{"name":"ha7189009jiaom@163.com","code":"aaaa8888"},{"name":"cw0866444yan@163.com","code":"aaaa8888"},{"name":"f31939081tangzha@163.com","code":"aaaa9999"},{"name":"wsrp94595805he@163.com","code":"aaaa8888"},{"name":"fuluxn159371@163.com","code":"abc58588"},{"name":"fuluxn937371@163.com","code":"abc58588"},{"name":"fuluyo604848@163.com","code":"abc58588"},{"name":"fumipf595826@163.com","code":"abc58588"},{"name":"funangw84826@163.com","code":"abc58588"},{"name":"lul044973@163.com","code":"hheb52"},{"name":"mft532457@163.com","code":"wbkg64"},{"name":"diy28658979n@163.com","code":"aaaa8888"},{"name":"m2310069hexu@163.com","code":"aaaa8888"},{"name":"c22317312lia@163.com","code":"aaaa8888"},{"name":"jgsg02533666@163.com","code":"aaaa8888"}];
$.each(account,function(i,v){
	setInterval(function(){
		$("#YT-ytaccount").val(v.name);
		$("#YT-ytpassword").val(v.code);
		$("#YT-nloginSubmit").click();
	},1000);
});