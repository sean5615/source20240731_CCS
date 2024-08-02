<%/*
----------------------------------------------------------------------------------
File Name		: ccs115r_01m1.jsp
Author			: alex
Description		: CCS115R_學分抵免推廣教育學分採認科目檢核一覽表 - 處理邏輯頁面
Modification Log	:

Vers		Date       	By            	Notes
--------------	--------------	--------------	----------------------------------
0.0.1		096/11/01	alex    	Code Generate Create
0.0.2		099/04-06	PEILING		修改「職別」欄位資料去掉<教師>兩個字
----------------------------------------------------------------------------------
*/%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="MS950"%>
<%@ include file="/utility/header.jsp"%>
<%@ include file="/utility/modulepageinit.jsp"%>
<%@page import="com.nou.ccs.dao.*"%>
<%@page import="com.nou.cou.dao.*"%>
<%@page import="com.nou.sys.dao.*"%>

<%!
/** 處理列印功能 */
private void doPrint(JspWriter out, DBManager dbManager, Hashtable requestMap, HttpSession session) throws Exception
{
	try
	{
		Connection	conn	=	dbManager.getConnection(AUTCONNECT.mapConnect("CGU", session));

        CCST012GATEWAY CCST012GATEWAY   =   new  CCST012GATEWAY(dbManager,conn);

        Vector result = CCST012GATEWAY.getCcst012ccs115rPrint(requestMap);

		/** 初始化 rptFile */
		RptFile		rptFile	=	new RptFile(session.getId());
		rptFile.setColumn("FACUITY,ASYS,CRSNO,CRS_NAME,CRD,COUT103_CHECK,RMK");

            for( int i = 0; i < result.size() ; i++ )
            {
                    Hashtable resultHt = (Hashtable)result.get(i);
                    rptFile.add(resultHt.get("FACULTY_NAME").toString() +"&nbsp;");
                    rptFile.add(resultHt.get("ASYS").toString() +"&nbsp;");
                    rptFile.add(resultHt.get("CRSNO").toString() +"&nbsp;");
                    rptFile.add(resultHt.get("CRS_NAME").toString()+"&nbsp;");
                    rptFile.add(resultHt.get("CRD").toString() +"&nbsp;");
                    rptFile.add(resultHt.get("COUT103_CHECK").toString() +"&nbsp;");
                    rptFile.add(resultHt.get("RMK").toString() +"&nbsp;");
            }

		if (rptFile.size() == 0)
		{
			out.println("<script>top.close();alert(\"無符合資料可供列印!!\");</script>");
			return;
		}

		/** 初始化報表物件 */
		report		report_	=	new report(dbManager, conn, out, "ccs115r_01r1", report.onlineHtmlMode);

		/** 靜態變數處理 */
		Hashtable	ht	=	getDynamic(dbManager, conn, requestMap );
		report_.setDynamicVariable(ht);

		/** 開始列印 */
		report_.genReport(rptFile);
	}
	catch (Exception ex)
	{
		throw ex;
	}
	finally
	{
		dbManager.close();
	}
}


	//取得靜態變數
private Hashtable getDynamic(DBManager dbManager, Connection conn, Hashtable requestMap) throws Exception
{
	Hashtable	ht	=	null;
	DBResult	rs	=	null;

	try
	{

		String	AYEAR		=	Utility.checkNull(requestMap.get("AYEAR"), "");			//學年
		String	SMS			=	Utility.checkNull(requestMap.get("SMS"), "");			//學期

		ht	=	new Hashtable();



		// --------------------------------------- 報表標題 - 開始

		String	title	=	"";


			title	=	"國立空中大學";



		//學年
		title	+=	String.valueOf(Integer.parseInt(AYEAR)) + "學年度";


		//學期
		title	+=	getSMS(dbManager, conn, SMS);


		title	+=	"學分抵免推廣教育學分採認科目檢核一覽表";

		// --------------------------------------- 報表標題 - 結束
		ht.put("TITLE", title);											//報表標題
		return ht;
	}
	catch(Exception e)
	{
		throw e;
	}
}
//取得學期
private String getSMS(DBManager dbManager, Connection conn, String SMS) throws Exception
{
	DBResult	rs	=	null;

	try
	{
		SYST001DAO	SYST001DAO	=	new SYST001DAO(dbManager, conn);
		SYST001DAO.setResultColumn("CODE_NAME");
		SYST001DAO.setKIND("SMS");
		SYST001DAO.setCODE(SMS);

		rs	=	SYST001DAO.query();

		if (rs.next())
			return rs.getString("CODE_NAME");
		else
			return "";
	}
	catch(Exception e)
	{
		throw e;
	}
	finally
	{
		if (rs != null)
			rs.close();
	}
}
%>