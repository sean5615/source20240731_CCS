<%/*
----------------------------------------------------------------------------------
File Name		: ccs115r_01m1.jsp
Author			: alex
Description		: CCS115R_�Ǥ���K���s�Ш|�Ǥ��Ļ{����ˮ֤@���� - �B�z�޿譶��
Modification Log	:

Vers		Date       	By            	Notes
--------------	--------------	--------------	----------------------------------
0.0.1		096/11/01	alex    	Code Generate Create
0.0.2		099/04-06	PEILING		�ק�u¾�O�v����ƥh��<�Юv>��Ӧr
----------------------------------------------------------------------------------
*/%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="MS950"%>
<%@ include file="/utility/header.jsp"%>
<%@ include file="/utility/modulepageinit.jsp"%>
<%@page import="com.nou.ccs.dao.*"%>
<%@page import="com.nou.cou.dao.*"%>
<%@page import="com.nou.sys.dao.*"%>

<%!
/** �B�z�C�L�\�� */
private void doPrint(JspWriter out, DBManager dbManager, Hashtable requestMap, HttpSession session) throws Exception
{
	try
	{
		Connection	conn	=	dbManager.getConnection(AUTCONNECT.mapConnect("CGU", session));

        CCST012GATEWAY CCST012GATEWAY   =   new  CCST012GATEWAY(dbManager,conn);

        Vector result = CCST012GATEWAY.getCcst012ccs115rPrint(requestMap);

		/** ��l�� rptFile */
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
			out.println("<script>top.close();alert(\"�L�ŦX��ƥi�ѦC�L!!\");</script>");
			return;
		}

		/** ��l�Ƴ����� */
		report		report_	=	new report(dbManager, conn, out, "ccs115r_01r1", report.onlineHtmlMode);

		/** �R�A�ܼƳB�z */
		Hashtable	ht	=	getDynamic(dbManager, conn, requestMap );
		report_.setDynamicVariable(ht);

		/** �}�l�C�L */
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


	//���o�R�A�ܼ�
private Hashtable getDynamic(DBManager dbManager, Connection conn, Hashtable requestMap) throws Exception
{
	Hashtable	ht	=	null;
	DBResult	rs	=	null;

	try
	{

		String	AYEAR		=	Utility.checkNull(requestMap.get("AYEAR"), "");			//�Ǧ~
		String	SMS			=	Utility.checkNull(requestMap.get("SMS"), "");			//�Ǵ�

		ht	=	new Hashtable();



		// --------------------------------------- ������D - �}�l

		String	title	=	"";


			title	=	"��ߪŤ��j��";



		//�Ǧ~
		title	+=	String.valueOf(Integer.parseInt(AYEAR)) + "�Ǧ~��";


		//�Ǵ�
		title	+=	getSMS(dbManager, conn, SMS);


		title	+=	"�Ǥ���K���s�Ш|�Ǥ��Ļ{����ˮ֤@����";

		// --------------------------------------- ������D - ����
		ht.put("TITLE", title);											//������D
		return ht;
	}
	catch(Exception e)
	{
		throw e;
	}
}
//���o�Ǵ�
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