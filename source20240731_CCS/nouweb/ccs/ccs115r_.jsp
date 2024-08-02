<%/*
----------------------------------------------------------------------------------
File Name		: ccs115r
Author			: alex
Description		: CCS115R_�Ǥ���K���s�Ш|�Ǥ��Ļ{����ˮ֤@���� - �D�n����
Modification Log	:

Vers		Date       	By            	Notes
--------------	--------------	--------------	----------------------------------
0.0.1		096/11/01	alex    	Code Generate Create
----------------------------------------------------------------------------------
*/%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="MS950"%>
<%@ include file="/utility/header.jsp"%>
<%@ include file="/utility/titleSetup.jsp"%>
<%@page import="java.util.Vector"%>
<%
	/** �ǻ� Key �Ѽ� */
	String	keyParam	=	com.acer.util.Utility.checkNull(request.getParameter("keyParam"), "");

	/**�Ǧ~�Ǵ���l*/
    	java.text.SimpleDateFormat dateTimeFormat = new java.text.SimpleDateFormat("yyyyMMdd");
	java.util.Calendar cal = java.util.Calendar.getInstance();
	String today = dateTimeFormat.format(cal.getTime());
   	com.acer.log.MyLogger logger = new com.acer.log.MyLogger("CCS115R");
    	com.acer.db.DBManager dbManager = new com.acer.db.DBManager(logger);  
	com.nou.sys.SYSGETSMSDATA sys = new com.nou.sys.SYSGETSMSDATA(dbManager);
	sys.setSYS_DATE(today);
	// 1.��� 2.�e�� 3.��� 4.�e�Ǧ~ 5.��Ǧ~	
	sys.setSMS_TYPE("1");
	int result = sys.execute();

	if(result == 1) 
	{
        		if(!keyParam.equals("") && keyParam.length() > 0) 
        		{
            		keyParam += "&AYEAR=" + sys.getAYEAR() + "&SMS=" + sys.getSMS();
        		} 
        		else 
        		{
            		keyParam = "?AYEAR=" + sys.getAYEAR() + "&SMS=" + sys.getSMS();
        		}
	}

	session.setAttribute("CCS115R_02_SELECT", "SOL#SELECT CODE AS SELECT_VALUE, CODE_NAME AS SELECT_TEXT FROM SYST001 WHERE KIND = 'SMS' ORDER BY SELECT_VALUE, SELECT_TEXT");

	session.setAttribute("CCS115R_03_SELECT", "SOL#SELECT FACULTY_CODE AS SELECT_VALUE, FACULTY_NAME AS SELECT_TEXT FROM SYST003 WHERE ASYS = '1' ORDER BY SELECT_VALUE, SELECT_TEXT");

	com.nou.aut.AUTGETRANGE autgetrange = (com.nou.aut.AUTGETRANGE)session.getAttribute("AUTGETRANGE");
	Vector	checkVt	=	new	Vector();
	String FACULTY_CODE = "";
	checkVt = autgetrange.getDEP_CODE("2","3");

	if (checkVt.size() > 0)
	{	
		/** �w�d�ϥΪ̦��h�ӾǨt�� */
		for (int i = 0; i < checkVt.size(); i++)
		{
			if(!"90".equals((String)checkVt.get(i))){
				if (i > 0)
					FACULTY_CODE += ",";
				
				FACULTY_CODE += (String)checkVt.get(i);
			}
		}
	}
	if(!keyParam.equals("") && keyParam.length() > 0) {
        keyParam += "&FACULTY_CODE="+FACULTY_CODE;
    } else {
        keyParam = "?FACULTY_CODE="+FACULTY_CODE;
    }
%>
<script>
	top.hideView();
	/** �ɦV�Ĥ@�ӳB�z������ */
	top.mainFrame.location.href	=	'ccs115r_01v1.jsp<%=keyParam%>';
</script>