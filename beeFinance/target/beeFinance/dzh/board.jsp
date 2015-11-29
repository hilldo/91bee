<%@ page language="java" pageEncoding="UTF-8"%>

<script type="text/javascript">

$(function(){
	
	// 1、处理当前时间
	var currTime = $("#currTime");
	setInterval(function(){
		currTime.html(new Date().format("yyyy-MM:dd hh:mm:ss"));
	},1000);
	
	// 2、增加自选股
	$("#addstock").click(function(){
		$.ajax({
			type: "get",
			url: mystockurl+"/addStock.do",
			data: {code: currCode},
			dataType: "jsonp",
			jsonpCallback: "jsonpCallback",
			success: function(data) {
				// Object { resultMsg="自选股添加成功",  resultCode=0}
				if (resultCode == 0) {
					$("#addstock").hide();
					
					var arr = [];
					arr.push("<tr id='mystock_"+currCode+"'>");
					arr.push("<td class='obj'></td><td class='ZhongWenJianCheng'></td><td class='ZuiXinJia'></td><td class='ZhangFu'></td>");
					arr.push("<td class='ZhenFu'></td><td class='no-pd'><a href='javascript:void(0)' class='del-stock-btn hide'></a></td></tr>");
					$("#mystockBody").prepend(arr.join(""));
					
					editStock();
					
					pageCodes.push(currCode);
					
					mystockDynaDataStore.cancel();
					mystockDynaDataStoreSubscribe(mystockDynaDataStore, pageCodes);
				}
			}
		});
	});
	
	// 3、动态行情
	$("div.sk-board").attr("id", "board_"+currCode);
	var dynaDataStore = new DataStore({
		//dataType: "json",
        serviceUrl: "/stkdata"
    });
	
	dynaDataStoreSubscribe(dynaDataStore, currCode);
	
	function dynaDataStoreSubscribe(dynaDataStore, stkCode) {
		dynaDataStore.subscribe({
			obj: stkCode,
			field: "ZhongWenJianCheng,ZuiXinJia,KaiPanJia,ZuiGaoJia,ZuiDiJia,ZuoShou,JunJia,ZhangDie,ZhangFu,ZhenFu,ChengJiaoLiang,XianShou,ChengJiaoE,HuanShou,LiangBi,NeiPan,WaiPan,ShiYingLv,ShiJingLv,ZhangTing,DieTing,WeiBi,WeiCha,ZongShiZhi,LiuTongShiZhi,WeiTuoMaiRuJia1,WeiTuoMaiRuJia2,WeiTuoMaiRuJia3,WeiTuoMaiRuJia4,WeiTuoMaiRuJia5,WeiTuoMaiRuLiang1,WeiTuoMaiRuLiang2,WeiTuoMaiRuLiang3,WeiTuoMaiRuLiang4,WeiTuoMaiRuLiang5,WeiTuoMaiChuJia1,WeiTuoMaiChuJia2,WeiTuoMaiChuJia3,WeiTuoMaiChuJia4,WeiTuoMaiChuJia5,WeiTuoMaiChuLiang1,WeiTuoMaiChuLiang2,WeiTuoMaiChuLiang3,WeiTuoMaiChuLiang4,WeiTuoMaiChuLiang5"
		}, {}, function(data) {
			if (data instanceof Error) {
				setTimeout(function(){
					dynaDataStoreSubscribe(dynaDataStore, stkCode);
				}, 3000);
			} else {
				for (x in data) {
					var dynaData = data[x];
					if (dynaData) {	
						valDynaData(dynaData);
					}
				}
			}
		});
	}   
	
	//根据推送的动态行情数据填充响应的元素
	function valDynaData(d) {
		var board_stk = $("#board_"+currCode);
		var wudangpankou = $("#wudangpankou");
		
		//处理个股board
		if (board_stk.length>0) {
			// 如果最新价是NaN，做停牌处理 
			var flag = isTingPai(d.ZuiXinJia, d.WeiTuoMaiRuJia1, d.WeiTuoMaiChuJia1);
			if (!flag) {
				board_stk.find("span.sk-board-stockname").html(d.ZhongWenJianCheng).end()
				.find("span.sk-board-stockcode").html(currCode).end().find(".realprice").html(d.ZuiXinJia ? d.ZuiXinJia : "停牌").addClass(d.ZhangFu>0?"red":"green").end()
				.find("p.changeAmt").html(d.ZhangDie).addClass(d.ZhangFu>0?"red":"green").end()
				.find("p.changeRate").html(d.ZhangFu+"%").addClass(d.ZhangFu>0?"red":"green").end()
				.find("em.KaiPanJia").html(d.KaiPanJia).end().find("em.ZuiGaoJia").html(d.ZuiGaoJia).end()
				.find("em.HuanShou").html(d.HuanShou+"%").end().find("em.ChengJiaoLiang").html((d.ChengJiaoLiang/1000000).toFixed(2)+"万手").end()
				.find("em.ZuoShou").html(d.ZuoShou).end().find("em.ZuiDiJia").html(d.ZuiDiJia).end()
				.find("em.LiangBi").html(d.LiangBi).end().find("em.ChengJiaoE").html((d.ChengJiaoE/10000).toFixed(0)+"万").end()
				.find("em.ZhangTing").html(d.ZhangTing).end().find("em.DieTing").html(d.DieTing).end()
				.find("em.ShiYingLv").html((d.ShiYingLv).toFixed(2)).end().find("em.ShiJingLv").html((d.ShiJingLv).toFixed(2)).end()
				.find("em.ZongShiZhi").html((d.ZongShiZhi/10000).toFixed(2)+"亿").end().find("em.LiuTongShiZhi").html((d.LiuTongShiZhi/10000).toFixed(2)+"亿").end()
				;
				
				//处理五档盘口
				if (wudangpankou.length>0) {
					wudangpankou.find("span.WeiBi").html(d.WeiBi).end().find("span.WeiCha").html(d.WeiCha).end()
					.find("span.WeiTuoMaiChuJia5").html(d.WeiTuoMaiChuJia5).end().find("span.WeiTuoMaiChuLiang5").html(d.WeiTuoMaiChuLiang5/100).end()
					.find("span.WeiTuoMaiChuJia4").html(d.WeiTuoMaiChuJia4).end().find("span.WeiTuoMaiChuLiang4").html(d.WeiTuoMaiChuLiang4/100).end()
					.find("span.WeiTuoMaiChuJia3").html(d.WeiTuoMaiChuJia3).end().find("span.WeiTuoMaiChuLiang3").html(d.WeiTuoMaiChuLiang3/100).end()
					.find("span.WeiTuoMaiChuJia2").html(d.WeiTuoMaiChuJia2).end().find("span.WeiTuoMaiChuLiang2").html(d.WeiTuoMaiChuLiang2/100).end()
					.find("span.WeiTuoMaiChuJia1").html(d.WeiTuoMaiChuJia1).end().find("span.WeiTuoMaiChuLiang1").html(d.WeiTuoMaiChuLiang1/100).end()
					.find("b.ZuiXinJia").html(d.ZuiXinJia).end()
					.find("span.WeiTuoMaiRuJia1").html(d.WeiTuoMaiRuJia1).end().find("span.WeiTuoMaiRuLiang1").html(d.WeiTuoMaiRuLiang1/100).end()
					.find("span.WeiTuoMaiRuJia2").html(d.WeiTuoMaiRuJia2).end().find("span.WeiTuoMaiRuLiang2").html(d.WeiTuoMaiRuLiang2/100).end()
					.find("span.WeiTuoMaiRuJia3").html(d.WeiTuoMaiRuJia3).end().find("span.WeiTuoMaiRuLiang3").html(d.WeiTuoMaiRuLiang3/100).end()
					.find("span.WeiTuoMaiRuJia4").html(d.WeiTuoMaiRuJia4).end().find("span.WeiTuoMaiRuLiang4").html(d.WeiTuoMaiRuLiang4/100).end()
					.find("span.WeiTuoMaiRuJia5").html(d.WeiTuoMaiRuJia5).end().find("span.WeiTuoMaiRuLiang5").html(d.WeiTuoMaiRuLiang5/100).end()
					;
				}
			} else {
				board_stk.find("span.sk-board-stockname").html(d.ZhongWenJianCheng).end()
				.find("span.sk-board-stockcode").html(currCode).end().find(".realprice").html("停牌").end()
				.find("p.changeAmt").html("-").end().find("p.changeRate").html("-").end()
				.find("em.KaiPanJia").html("-").end().find("em.ZuiGaoJia").html("-").end()
				.find("em.HuanShou").html("-").end().find("em.ChengJiaoLiang").html("-").end()
				.find("em.ZuoShou").html("-").end().find("em.ZuiDiJia").html("-").end()
				.find("em.LiangBi").html("-").end().find("em.ChengJiaoE").html("-").end()
				.find("em.ZhangTing").html("-").end().find("em.DieTing").html("-").end()
				.find("em.ShiYingLv").html("-").end().find("em.ShiJingLv").html("-").end()
				.find("em.ZongShiZhi").html("-").end().find("em.LiuTongShiZhi").html("-").end()
				;
				//处理五档盘口
				if (wudangpankou.length>0) {
					wudangpankou.find("span.WeiBi").html("-").end().find("span.WeiCha").html("-").end()
					.find("span.WeiTuoMaiChuJia5").html("-").end().find("span.WeiTuoMaiChuLiang5").html("-").end()
					.find("span.WeiTuoMaiChuJia4").html("-").end().find("span.WeiTuoMaiChuLiang4").html("-").end()
					.find("span.WeiTuoMaiChuJia3").html("-").end().find("span.WeiTuoMaiChuLiang3").html("-").end()
					.find("span.WeiTuoMaiChuJia2").html("-").end().find("span.WeiTuoMaiChuLiang2").html("-").end()
					.find("span.WeiTuoMaiChuJia1").html("-").end().find("span.WeiTuoMaiChuLiang1").html("-").end()
					.find("b.ZuiXinJia").html("-").end()
					.find("span.WeiTuoMaiRuJia1").html("-").end().find("span.WeiTuoMaiRuLiang1").html("-").end()
					.find("span.WeiTuoMaiRuJia2").html("-").end().find("span.WeiTuoMaiRuLiang2").html("-").end()
					.find("span.WeiTuoMaiRuJia3").html("-").end().find("span.WeiTuoMaiRuLiang3").html("-").end()
					.find("span.WeiTuoMaiRuJia4").html("-").end().find("span.WeiTuoMaiRuLiang4").html("-").end()
					.find("span.WeiTuoMaiRuJia5").html("-").end().find("span.WeiTuoMaiRuLiang5").html("-").end()
					;
				}
			}
		}
	}
	
});

</script>

<!-- sk-board -->
<div class="sk-board">

    <div class="sk-board-stock"> 
    	<span class="sk-board-stockname"></span> 
        <span class="sk-board-stockcode">-</span>
        <div class="sk-board-buttons">
        	<a id="addstock" class="addstock" href="javascript:;">+自选股</a>
        </div>
    </div>
    
    <div class="sk-board-main">
        <div class="realprice"></div>
        <div class="sk-board-detail">
            <p class="changeAmt"></p>
            <p class="changeRate"></p>
        </div>
        <div id="currTime" class="time-now"></div>
    </div>
    
    <div class="sk-board-market">
        <table>
            <col width="96" />
            <col width="96" />
            <col width="96" />
            <col width="104" />
            <col width="136" />
            <col width="128" />
            <col width="" />
            <tbody>
                <tr>
                    <td><span>今开：<em class="KaiPanJia"></em></span></td>
                    <td><span>最高：<em class="ZuiGaoJia"></em></span></td>
                    <td><span>涨停：<em class="ZhangTing"></em></span></td>
                    <td><span>换手：<em class="HuanShou"></em></span></td>
                    <td><span>成交量：<em class="ChengJiaoLiang"></em></span></td>
                    <td><span>市盈：<em class="ShiYingLv"></em></span></td>
                    <td><span>总市值：<em class="ZongShiZhi"></em></span></td>
                </tr>
                <tr>
                    <td><span>昨收：<em class="ZuoShou"></em></span></td>
                    <td><span>最低：<em class="ZuiDiJia"></em></span></td>
                    <td><span>跌停：<em class="DieTing"></em></span></td>
                    <td><span>量比：<em class="LiangBi"></em></span></td>
                    <td><span>成交额：<em class="ChengJiaoE"></em></span></td>
                    <td><span>市净：<em class="ShiJingLv"></em></span></td>
                    <td><span>流通市值：<em class="LiuTongShiZhi"></em></span></td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
<!-- sk-board -->