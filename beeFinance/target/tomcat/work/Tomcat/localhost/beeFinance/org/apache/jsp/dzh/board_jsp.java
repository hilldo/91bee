/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2015-11-19 05:52:43 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.dzh;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class board_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("\r\n");
      out.write("$(function(){\r\n");
      out.write("\t\r\n");
      out.write("\t// 1、处理当前时间\r\n");
      out.write("\tvar currTime = $(\"#currTime\");\r\n");
      out.write("\tsetInterval(function(){\r\n");
      out.write("\t\tcurrTime.html(new Date().format(\"yyyy-MM:dd hh:mm:ss\"));\r\n");
      out.write("\t},1000);\r\n");
      out.write("\t\r\n");
      out.write("\t// 2、增加自选股\r\n");
      out.write("\t$(\"#addstock\").click(function(){\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t\ttype: \"get\",\r\n");
      out.write("\t\t\turl: mystockurl+\"/addStock.do\",\r\n");
      out.write("\t\t\tdata: {code: currCode},\r\n");
      out.write("\t\t\tdataType: \"jsonp\",\r\n");
      out.write("\t\t\tjsonpCallback: \"jsonpCallback\",\r\n");
      out.write("\t\t\tsuccess: function(data) {\r\n");
      out.write("\t\t\t\t// Object { resultMsg=\"自选股添加成功\",  resultCode=0}\r\n");
      out.write("\t\t\t\tif (resultCode == 0) {\r\n");
      out.write("\t\t\t\t\t$(\"#addstock\").hide();\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\tvar arr = [];\r\n");
      out.write("\t\t\t\t\tarr.push(\"<tr id='mystock_\"+currCode+\"'>\");\r\n");
      out.write("\t\t\t\t\tarr.push(\"<td class='obj'></td><td class='ZhongWenJianCheng'></td><td class='ZuiXinJia'></td><td class='ZhangFu'></td>\");\r\n");
      out.write("\t\t\t\t\tarr.push(\"<td class='ZhenFu'></td><td class='no-pd'><a href='javascript:void(0)' class='del-stock-btn hide'></a></td></tr>\");\r\n");
      out.write("\t\t\t\t\t$(\"#mystockBody\").prepend(arr.join(\"\"));\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\teditStock();\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\tpageCodes.push(currCode);\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\tmystockDynaDataStore.cancel();\r\n");
      out.write("\t\t\t\t\tmystockDynaDataStoreSubscribe(mystockDynaDataStore, pageCodes);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\t// 3、动态行情\r\n");
      out.write("\t$(\"div.sk-board\").attr(\"id\", \"board_\"+currCode);\r\n");
      out.write("\tvar dynaDataStore = new DataStore({\r\n");
      out.write("        serviceUrl: \"/stkdata\"\r\n");
      out.write("    });\r\n");
      out.write("\t\r\n");
      out.write("\tdynaDataStoreSubscribe(dynaDataStore, currCode);\r\n");
      out.write("\t\r\n");
      out.write("\tfunction dynaDataStoreSubscribe(dynaDataStore, stkCode) {\r\n");
      out.write("\t\tdynaDataStore.subscribe({\r\n");
      out.write("\t\t\tobj: stkCode,\r\n");
      out.write("\t\t\tfield: \"ZhongWenJianCheng,ZuiXinJia,KaiPanJia,ZuiGaoJia,ZuiDiJia,ZuoShou,JunJia,ZhangDie,ZhangFu,ZhenFu,ChengJiaoLiang,XianShou,ChengJiaoE,HuanShou,LiangBi,NeiPan,WaiPan,ShiYingLv,ShiJingLv,ZhangTing,DieTing,WeiBi,WeiCha,ZongShiZhi,LiuTongShiZhi,WeiTuoMaiRuJia1,WeiTuoMaiRuJia2,WeiTuoMaiRuJia3,WeiTuoMaiRuJia4,WeiTuoMaiRuJia5,WeiTuoMaiRuLiang1,WeiTuoMaiRuLiang2,WeiTuoMaiRuLiang3,WeiTuoMaiRuLiang4,WeiTuoMaiRuLiang5,WeiTuoMaiChuJia1,WeiTuoMaiChuJia2,WeiTuoMaiChuJia3,WeiTuoMaiChuJia4,WeiTuoMaiChuJia5,WeiTuoMaiChuLiang1,WeiTuoMaiChuLiang2,WeiTuoMaiChuLiang3,WeiTuoMaiChuLiang4,WeiTuoMaiChuLiang5\"\r\n");
      out.write("\t\t}, {}, function(data) {\r\n");
      out.write("\t\t\tif (data instanceof Error) {\r\n");
      out.write("\t\t\t\tsetTimeout(function(){\r\n");
      out.write("\t\t\t\t\tdynaDataStoreSubscribe(dynaDataStore, stkCode);\r\n");
      out.write("\t\t\t\t}, 3000);\r\n");
      out.write("\t\t\t} else {\r\n");
      out.write("\t\t\t\tfor (x in data) {\r\n");
      out.write("\t\t\t\t\tvar dynaData = data[x];\r\n");
      out.write("\t\t\t\t\tif (dynaData) {\t\r\n");
      out.write("\t\t\t\t\t\tvalDynaData(dynaData);\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}   \r\n");
      out.write("\t\r\n");
      out.write("\t//根据推送的动态行情数据填充响应的元素\r\n");
      out.write("\tfunction valDynaData(d) {\r\n");
      out.write("\t\tvar board_stk = $(\"#board_\"+currCode);\r\n");
      out.write("\t\tvar wudangpankou = $(\"#wudangpankou\");\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//处理个股board\r\n");
      out.write("\t\tif (board_stk.length>0) {\r\n");
      out.write("\t\t\t// 如果最新价是NaN，做停牌处理 \r\n");
      out.write("\t\t\tvar flag = isTingPai(d.ZuiXinJia, d.WeiTuoMaiRuJia1, d.WeiTuoMaiChuJia1);\r\n");
      out.write("\t\t\tboard_stk.find(\"span.sk-board-stockname\").html(d.ZhongWenJianCheng).end()\r\n");
      out.write("\t\t\t.find(\"span.sk-board-stockcode\").html(currCode).end().find(\".realprice\").html(flag ? \"停牌\" : formatNumber(d.ZuiXinJia)).addClass(d.ZhangFu>0?\"red\":\"green\").end()\r\n");
      out.write("\t\t\t.find(\"p.changeAmt\").html(formatNumber(d.ZhangDie)).addClass(d.ZhangFu>0?\"red\":\"green\").end()\r\n");
      out.write("\t\t\t.find(\"p.changeRate\").html(formatNumber(d.ZhangFu,null,null,\"%\")).addClass(d.ZhangFu>0?\"red\":\"green\").end()\r\n");
      out.write("\t\t\t.find(\"em.KaiPanJia\").html(formatNumber(d.KaiPanJia)).end().find(\"em.ZuiGaoJia\").html(formatNumber(d.ZuiGaoJia)).end()\r\n");
      out.write("\t\t\t.find(\"em.HuanShou\").html(formatNumber(d.HuanShou,null,null,\"%\")).end().find(\"em.ChengJiaoLiang\").html(formatNumber(d.ChengJiaoLiang,null,\"M\",\"万手\")).end()\r\n");
      out.write("\t\t\t.find(\"em.ZuoShou\").html(formatNumber(d.ZuoShou)).end().find(\"em.ZuiDiJia\").html(formatNumber(d.ZuiDiJia)).end()\r\n");
      out.write("\t\t\t.find(\"em.LiangBi\").html(formatNumber(d.LiangBi)).end().find(\"em.ChengJiaoE\").html(formatNumber(d.ChengJiaoE,null,\"W\",\"万\")).end()\r\n");
      out.write("\t\t\t.find(\"em.ZhangTing\").html(formatNumber(d.ZhangTing)).end().find(\"em.DieTing\").html(formatNumber(d.DieTing)).end()\r\n");
      out.write("\t\t\t.find(\"em.ShiYingLv\").html(formatNumber(d.ShiYingLv)).end().find(\"em.ShiJingLv\").html(formatNumber(d.ShiJingLv)).end()\r\n");
      out.write("\t\t\t.find(\"em.ZongShiZhi\").html(formatNumber(d.ZongShiZhi,null,\"W\",\"亿\")).end().find(\"em.LiuTongShiZhi\").html(formatNumber(d.LiuTongShiZhi,null,\"W\",\"亿\")).end()\r\n");
      out.write("\t\t\t;\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t//处理五档盘口\r\n");
      out.write("\t\t\tif (wudangpankou.length>0) {\r\n");
      out.write("\t\t\t\twudangpankou.find(\"span.WeiBi\").html(formatNumber(d.WeiBi)).end().find(\"span.WeiCha\").html(formatNumber(d.WeiCha)).end()\r\n");
      out.write("\t\t\t\t.find(\"span.WeiTuoMaiChuJia5\").html(formatNumber(d.WeiTuoMaiChuJia5)).end().find(\"span.WeiTuoMaiChuLiang5\").html(formatNumber(d.WeiTuoMaiChuLiang5,0,100)).end()\r\n");
      out.write("\t\t\t\t.find(\"span.WeiTuoMaiChuJia4\").html(formatNumber(d.WeiTuoMaiChuJia4)).end().find(\"span.WeiTuoMaiChuLiang4\").html(formatNumber(d.WeiTuoMaiChuLiang4,0,100)).end()\r\n");
      out.write("\t\t\t\t.find(\"span.WeiTuoMaiChuJia3\").html(formatNumber(d.WeiTuoMaiChuJia3)).end().find(\"span.WeiTuoMaiChuLiang3\").html(formatNumber(d.WeiTuoMaiChuLiang3,0,100)).end()\r\n");
      out.write("\t\t\t\t.find(\"span.WeiTuoMaiChuJia2\").html(formatNumber(d.WeiTuoMaiChuJia2)).end().find(\"span.WeiTuoMaiChuLiang2\").html(formatNumber(d.WeiTuoMaiChuLiang2,0,100)).end()\r\n");
      out.write("\t\t\t\t.find(\"span.WeiTuoMaiChuJia1\").html(formatNumber(d.WeiTuoMaiChuJia1)).end().find(\"span.WeiTuoMaiChuLiang1\").html(formatNumber(d.WeiTuoMaiChuLiang1,0,100)).end()\r\n");
      out.write("\t\t\t\t.find(\"b.ZuiXinJia\").html(formatNumber(d.ZuiXinJia)).end()\r\n");
      out.write("\t\t\t\t.find(\"span.WeiTuoMaiRuJia1\").html(formatNumber(d.WeiTuoMaiRuJia1)).end().find(\"span.WeiTuoMaiRuLiang1\").html(formatNumber(d.WeiTuoMaiRuLiang1,0,100)).end()\r\n");
      out.write("\t\t\t\t.find(\"span.WeiTuoMaiRuJia2\").html(formatNumber(d.WeiTuoMaiRuJia2)).end().find(\"span.WeiTuoMaiRuLiang2\").html(formatNumber(d.WeiTuoMaiRuLiang2,0,100)).end()\r\n");
      out.write("\t\t\t\t.find(\"span.WeiTuoMaiRuJia3\").html(formatNumber(d.WeiTuoMaiRuJia3)).end().find(\"span.WeiTuoMaiRuLiang3\").html(formatNumber(d.WeiTuoMaiRuLiang3,0,100)).end()\r\n");
      out.write("\t\t\t\t.find(\"span.WeiTuoMaiRuJia4\").html(formatNumber(d.WeiTuoMaiRuJia4)).end().find(\"span.WeiTuoMaiRuLiang4\").html(formatNumber(d.WeiTuoMaiRuLiang4,0,100)).end()\r\n");
      out.write("\t\t\t\t.find(\"span.WeiTuoMaiRuJia5\").html(formatNumber(d.WeiTuoMaiRuJia5)).end().find(\"span.WeiTuoMaiRuLiang5\").html(formatNumber(d.WeiTuoMaiRuLiang5,0,100)).end()\r\n");
      out.write("\t\t\t\t;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("});\r\n");
      out.write("\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("<!-- sk-board -->\r\n");
      out.write("<div class=\"sk-board\">\r\n");
      out.write("\r\n");
      out.write("    <div class=\"sk-board-stock\"> \r\n");
      out.write("    \t<span class=\"sk-board-stockname\"></span> \r\n");
      out.write("        <span class=\"sk-board-stockcode\">-</span>\r\n");
      out.write("        <div class=\"sk-board-buttons\">\r\n");
      out.write("        \t<a id=\"addstock\" class=\"addstock\" href=\"javascript:;\">+自选股</a>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    \r\n");
      out.write("    <div class=\"sk-board-main\">\r\n");
      out.write("        <div class=\"realprice\"></div>\r\n");
      out.write("        <div class=\"sk-board-detail\">\r\n");
      out.write("            <p class=\"changeAmt\"></p>\r\n");
      out.write("            <p class=\"changeRate\"></p>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div id=\"currTime\" class=\"time-now\"></div>\r\n");
      out.write("    </div>\r\n");
      out.write("    \r\n");
      out.write("    <div class=\"sk-board-market\">\r\n");
      out.write("        <table>\r\n");
      out.write("            <col width=\"96\" />\r\n");
      out.write("            <col width=\"96\" />\r\n");
      out.write("            <col width=\"96\" />\r\n");
      out.write("            <col width=\"104\" />\r\n");
      out.write("            <col width=\"136\" />\r\n");
      out.write("            <col width=\"128\" />\r\n");
      out.write("            <col width=\"\" />\r\n");
      out.write("            <tbody>\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <td><span>今开：<em class=\"KaiPanJia\"></em></span></td>\r\n");
      out.write("                    <td><span>最高：<em class=\"ZuiGaoJia\"></em></span></td>\r\n");
      out.write("                    <td><span>涨停：<em class=\"ZhangTing\"></em></span></td>\r\n");
      out.write("                    <td><span>换手：<em class=\"HuanShou\"></em></span></td>\r\n");
      out.write("                    <td><span>成交量：<em class=\"ChengJiaoLiang\"></em></span></td>\r\n");
      out.write("                    <td><span>市盈：<em class=\"ShiYingLv\"></em></span></td>\r\n");
      out.write("                    <td><span>总市值：<em class=\"ZongShiZhi\"></em></span></td>\r\n");
      out.write("                </tr>\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <td><span>昨收：<em class=\"ZuoShou\"></em></span></td>\r\n");
      out.write("                    <td><span>最低：<em class=\"ZuiDiJia\"></em></span></td>\r\n");
      out.write("                    <td><span>跌停：<em class=\"DieTing\"></em></span></td>\r\n");
      out.write("                    <td><span>量比：<em class=\"LiangBi\"></em></span></td>\r\n");
      out.write("                    <td><span>成交额：<em class=\"ChengJiaoE\"></em></span></td>\r\n");
      out.write("                    <td><span>市净：<em class=\"ShiJingLv\"></em></span></td>\r\n");
      out.write("                    <td><span>流通市值：<em class=\"LiuTongShiZhi\"></em></span></td>\r\n");
      out.write("                </tr>\r\n");
      out.write("            </tbody>\r\n");
      out.write("        </table>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("<!-- sk-board -->");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}