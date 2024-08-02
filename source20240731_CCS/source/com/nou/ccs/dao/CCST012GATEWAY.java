package com.nou.ccs.dao;

import com.acer.db.DBManager;
import com.acer.db.query.DBResult;
import com.acer.util.Utility;
import com.acer.apps.Page;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.Hashtable;

/*
 * (CCST012) Gateway/*
 *-------------------------------------------------------------------------------*
 * Author    : 國長      2007/05/04
 * Modification Log :
 * Vers     Date           By             Notes
 *--------- -------------- -------------- ----------------------------------------
 * V0.0.1   2007/05/04     國長           建立程式
 *                                        新增 getCcst012ForUse(Hashtable ht)
 * V0.0.2   2007/06/06     jeff           修改程式
 *                                        新增 getDataForCCS003(Vector vt, Hashtable ht)
 * V0.0.3   2007/06/27     芳如                               新增 getDataForCCS041M_Query2_1(Hashtable ht)
 *                                        新增 getDataForCCS041M_Query2_3(Hashtable ht)
 * V0.0.4	2007/07/27     barry          新增 getCodeToPrintForCcs113r(Hashtable ht)
 * V0.0.5	2007/09/28	sorgr		getCcs111rPrint(Vector vt, Hashtable ht)
 *--------------------------------------------------------------------------------
 */
public class CCST012GATEWAY {

    /** 資料排序方式 */
    private String orderBy = "";
    private DBManager dbmanager = null;
    private Connection conn = null;
    /* 頁數 */
    private int pageNo = 0;
    /** 每頁筆數 */
    private int pageSize = 0;

    /** 記錄是否分頁 */
    private boolean pageQuery = false;

    /** 用來存放 SQL 語法的物件 */
    private StringBuffer sql = new StringBuffer();

    /** <pre>
     *  設定資料排序方式.
     *  Ex: "AYEAR, SMS DESC"
     *      先以 AYEAR 排序再以 SMS 倒序序排序
     *  </pre>
     */
    public void setOrderBy(String orderBy) {
        if(orderBy == null) {
            orderBy = "";
        }
        this.orderBy = orderBy;
    }

    /** 取得總筆數 */
    public int getTotalRowCount() {
        return Page.getTotalRowCount();
    }

    /** 不允許建立空的物件 */
    private CCST012GATEWAY() {}

    /** 建構子，查詢全部資料用 */
    public CCST012GATEWAY(DBManager dbmanager, Connection conn) {
        this.dbmanager = dbmanager;
        this.conn = conn;
    }

    /** 建構子，查詢分頁資料用 */
    public CCST012GATEWAY(DBManager dbmanager, Connection conn, int pageNo, int pageSize) {
        this.dbmanager = dbmanager;
        this.conn = conn;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        pageQuery = true;
    }

    /**
     *
     * @param ht 條件值
     * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
     *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
     *         若該欄位有中文名稱，則其 KEY 請加上 _NAME, EX: SMS 其中文欄位請設為 SMS_NAME
     * @throws Exception
     */
    public Vector getCcst012ForUse(Hashtable ht) throws Exception {
        if(ht == null) {
            ht = new Hashtable();
        }
        Vector result = new Vector();
        if(sql.length() > 0) {
            sql.delete(0, sql.length());
        }
        sql.append(
            "SELECT C12.AYEAR, C12.SMS, C12.STNO, C12.APP_SEQ, C12.CRSNO, C12.PRIORITY, C12.CRD, C12.CRS_NAME_OTHER, C12.CRD_OTHER, C12.CRSNO_UNIV, C12.CRD_UNIV, C12.EDUBKGRD_GRADE, C12.SCHOOL_TYPE, C12.GRADE_OLD, C12.SMS_OLD, C12.GRAD_TYPE, C12.APP_FACULTY_TYPE, C12.APP_DISCIPLINE_CODE, C12.AUDIT_STATUS, C12.RE_AUDIT_STATUS, C12.AUDIT_OPINION, C12.RE_AUDIT_OPINION, C12.REPL_MARK, C12.REDUCE_CRD, C12.ADOPT_MK, C12.RMK, C12.UPD_RMK, C12.UPD_DATE, C12.UPD_TIME, C12.UPD_USER_ID, C12.ROWSTAMP " +
            "FROM CCST012 C12 " +
            "WHERE 1 = 1 "
        );
        if(!Utility.nullToSpace(ht.get("AYEAR")).equals("")) {
            sql.append("AND C12.AYEAR = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("SMS")).equals("")) {
            sql.append("AND C12.SMS = '" + Utility.nullToSpace(ht.get("SMS")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("STNO")).equals("")) {
            sql.append("AND C12.STNO = '" + Utility.nullToSpace(ht.get("STNO")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("APP_SEQ")).equals("")) {
            sql.append("AND C12.APP_SEQ = '" + Utility.nullToSpace(ht.get("APP_SEQ")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("CRSNO")).equals("")) {
            sql.append("AND C12.CRSNO = '" + Utility.nullToSpace(ht.get("CRSNO")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("PRIORITY")).equals("")) {
            sql.append("AND C12.PRIORITY = '" + Utility.nullToSpace(ht.get("PRIORITY")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("CRD")).equals("")) {
            sql.append("AND C12.CRD = '" + Utility.nullToSpace(ht.get("CRD")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("CRS_NAME_OTHER")).equals("")) {
            sql.append("AND C12.CRS_NAME_OTHER = '" + Utility.nullToSpace(ht.get("CRS_NAME_OTHER")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("CRD_OTHER")).equals("")) {
            sql.append("AND C12.CRD_OTHER = '" + Utility.nullToSpace(ht.get("CRD_OTHER")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("CRSNO_UNIV")).equals("")) {
            sql.append("AND C12.CRSNO_UNIV = '" + Utility.nullToSpace(ht.get("CRSNO_UNIV")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("CRD_UNIV")).equals("")) {
            sql.append("AND C12.CRD_UNIV = '" + Utility.nullToSpace(ht.get("CRD_UNIV")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("EDUBKGRD_GRADE")).equals("")) {
            sql.append("AND C12.EDUBKGRD_GRADE = '" + Utility.nullToSpace(ht.get("EDUBKGRD_GRADE")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("SCHOOL_TYPE")).equals("")) {
            sql.append("AND C12.SCHOOL_TYPE = '" + Utility.nullToSpace(ht.get("SCHOOL_TYPE")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("GRADE_OLD")).equals("")) {
            sql.append("AND C12.GRADE_OLD = '" + Utility.nullToSpace(ht.get("GRADE_OLD")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("SMS_OLD")).equals("")) {
            sql.append("AND C12.SMS_OLD = '" + Utility.nullToSpace(ht.get("SMS_OLD")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("GRAD_TYPE")).equals("")) {
            sql.append("AND C12.GRAD_TYPE = '" + Utility.nullToSpace(ht.get("GRAD_TYPE")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("APP_FACULTY_TYPE")).equals("")) {
            sql.append("AND C12.APP_FACULTY_TYPE = '" + Utility.nullToSpace(ht.get("APP_FACULTY_TYPE")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("APP_DISCIPLINE_CODE")).equals("")) {
            sql.append("AND C12.APP_DISCIPLINE_CODE = '" + Utility.nullToSpace(ht.get("APP_DISCIPLINE_CODE")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("AUDIT_STATUS")).equals("")) {
            sql.append("AND C12.AUDIT_STATUS = '" + Utility.nullToSpace(ht.get("AUDIT_STATUS")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("RE_AUDIT_STATUS")).equals("")) {
            sql.append("AND C12.RE_AUDIT_STATUS = '" + Utility.nullToSpace(ht.get("RE_AUDIT_STATUS")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("AUDIT_OPINION")).equals("")) {
            sql.append("AND C12.AUDIT_OPINION = '" + Utility.nullToSpace(ht.get("AUDIT_OPINION")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("RE_AUDIT_OPINION")).equals("")) {
            sql.append("AND C12.RE_AUDIT_OPINION = '" + Utility.nullToSpace(ht.get("RE_AUDIT_OPINION")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("REPL_MARK")).equals("")) {
            sql.append("AND C12.REPL_MARK = '" + Utility.nullToSpace(ht.get("REPL_MARK")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("REDUCE_CRD")).equals("")) {
            sql.append("AND C12.REDUCE_CRD = '" + Utility.nullToSpace(ht.get("REDUCE_CRD")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("ADOPT_MK")).equals("")) {
            sql.append("AND C12.ADOPT_MK = '" + Utility.nullToSpace(ht.get("ADOPT_MK")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("RMK")).equals("")) {
            sql.append("AND C12.RMK = '" + Utility.nullToSpace(ht.get("RMK")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("UPD_RMK")).equals("")) {
            sql.append("AND C12.UPD_RMK = '" + Utility.nullToSpace(ht.get("UPD_RMK")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("UPD_DATE")).equals("")) {
            sql.append("AND C12.UPD_DATE = '" + Utility.nullToSpace(ht.get("UPD_DATE")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("UPD_TIME")).equals("")) {
            sql.append("AND C12.UPD_TIME = '" + Utility.nullToSpace(ht.get("UPD_TIME")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("UPD_USER_ID")).equals("")) {
            sql.append("AND C12.UPD_USER_ID = '" + Utility.nullToSpace(ht.get("UPD_USER_ID")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("ROWSTAMP")).equals("")) {
            sql.append("AND C12.ROWSTAMP = '" + Utility.nullToSpace(ht.get("ROWSTAMP")) + "' ");
        }

        if(!orderBy.equals("")) {
            String[] orderByArray = orderBy.split(",");
            orderBy = "";
            for(int i = 0; i < orderByArray.length; i++) {
                orderByArray[i] = "C12." + orderByArray[i].trim();

                if(i == 0) {
                    orderBy += "ORDER BY ";
                } else {
                    orderBy += ", ";
                }
                orderBy += orderByArray[i].trim();
            }
            sql.append(orderBy.toUpperCase());
            orderBy = "";
        }

        DBResult rs = null;
        try {
            if(pageQuery) {
                // 依分頁取出資料
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            } else {
                // 取出所有資料
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }
            Hashtable rowHt = null;
            while (rs.next()) {
                rowHt = new Hashtable();
                /** 將欄位抄一份過去 */
                for (int i = 1; i <= rs.getColumnCount(); i++)
                    rowHt.put(rs.getColumnName(i), rs.getString(i));

                result.add(rowHt);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null) {
                rs.close();
            }
        }
        return result;
    }

    /**
     * 申請學分抵免 - 學分抵免及採認申請明細檔(CCST012)
     * @param vt 回傳值
     * @param ht 條件值
     * @throws Exception
     */
    public void getDataForCCS003(Vector vt, Hashtable ht) throws Exception {
	if(sql.length() > 0) {
		sql.delete(0, sql.length());
	}

	sql.append
	(
		"SELECT " +
		"a.rowid||'' AS RID, "+
		"a.APP_SEQ, " +
		"a.AYEAR, " +
		"a.SMS, " +
		"a.STNO, " +
		"a.PRIORITY, " +
		"a.CRSNO, " +
		"(SELECT CRS_NAME FROM COUT002 WHERE a.CRSNO = CRSNO AND ROWNUM = 1) as CRS_NAME, " +
		"a.CRD, " +
		"a.CRS_NAME_OTHER, " +
		"a.CRD_OTHER, " +
		"a.CRD_UNIV, " +
		"(SELECT CRS_NAME FROM COUT002 WHERE a.CRSNO_UNIV = CRSNO AND ROWNUM = 1) as CRSNO_UNIV_NAME, " +
		"(SELECT CODE_NAME FROM SYST001 WHERE a.SCHOOL_TYPE = CODE AND KIND = 'SCHOOL_TYPE' AND ROWNUM = 1) as SCHOOL_TYPE_C, " +
		"(SELECT CODE_NAME FROM SYST001 WHERE a.HEDU_TYPE = CODE AND KIND = 'HEDU_TYPE' AND ROWNUM = 1) as HEDU_TYPE_C, " +
		"a.GRADE_OLD, " +
		"a.SMS_OLD, " +
		"(SELECT CODE_NAME FROM SYST001 WHERE a.GRAD_TYPE = CODE AND KIND = 'GRAD_TYPE' AND ROWNUM = 1) as GRAD_TYPE_C, " +
		"b.APPREVIEW_REASON, " +
		"NVL(b.APP_SEQ,'NA') as B_APP_SEQ, " +
		"b.APP_SEQ_OLD as B_APP_SEQ_OLD, " +
		"b.AYEAR as B_AYEAR, " +
		"b.SMS as B_SMS, " +
		"b.STNO as B_STNO " +

		"FROM  CCST012 a, CCST014 b " +
		"WHERE " +
		"1	=	1 "
	);

	if(!Utility.checkNull(ht.get("STNO"), "").equals(""))
		sql.append("AND a.STNO	=	'" + Utility.dbStr(ht.get("STNO"))+ "' ");
	if(!Utility.checkNull(ht.get("AYEAR"), "").equals(""))
		sql.append("AND a.AYEAR	=	'" + Utility.dbStr(ht.get("AYEAR"))+ "' ");
	if(!Utility.checkNull(ht.get("SMS"), "").equals(""))
		sql.append("AND a.SMS	=	'" + Utility.dbStr(ht.get("SMS"))+ "' ");
	if(!Utility.checkNull(ht.get("APP_SEQ"), "").equals(""))
		sql.append("AND a.APP_SEQ	=	'" + Utility.dbStr(ht.get("APP_SEQ"))+ "' ");

	sql.append
	(
		"AND a.AYEAR = b.AYEAR (+) " +
		"AND a.SMS = b.SMS (+) " +
		"AND a.STNO = b.STNO (+) " +
		"AND a.CRSNO = b.CRSNO (+) " +
		"AND a.APP_SEQ = b.APP_SEQ_OLD (+) "
	);

	sql.append(" ORDER BY a.PRIORITY,a.rowid||'' ");

	DBResult rs = null;

	try {
            if(pageQuery) {
                // 依分頁取出資料
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            } else {
                // 取出所有資料
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }

            while (rs.next()) {
                Hashtable    content    = new Hashtable();
                /** 將欄位抄一份過去 */
                for (int i = 1; i <= rs.getColumnCount(); i++){
                    content.put(rs.getColumnName(i), rs.getString(i));
                }

                vt.add(content);
            }
	} catch (Exception e) {
            throw e;
	} finally {
            if(rs != null) {
                rs.close();
            }
	}
    }

    public void getDataForCCS003_(Vector vt, Hashtable ht) throws Exception {
	if(sql.length() > 0) {
		sql.delete(0, sql.length());
	}

	sql.append
	(
		"SELECT " +
		"a.APP_SEQ, " +
		"a.AYEAR, " +
		"a.SMS, " +
		"a.STNO, " +
		"a.PRIORITY, a.REDUCE_CRD, " +
		"a.CRSNO, " +
		"(SELECT CRS_NAME FROM COUT002 WHERE a.CRSNO = CRSNO AND ROWNUM = 1) as CRS_NAME, " +
		"a.CRD, " +
		"a.CRS_NAME_OTHER, " +
		"a.CRD_OTHER, " +
		"a.CRD_UNIV, " +
		"(SELECT CRS_NAME FROM COUT002 WHERE a.CRSNO_UNIV = CRSNO AND ROWNUM = 1) as CRSNO_UNIV_NAME, " +
		"NVL(a.CRD_OTHER, a.CRD_UNIV) as O_CRD, " +
		"NVL(a.CRS_NAME_OTHER, (SELECT CRS_NAME FROM COUT002 WHERE a.CRSNO_UNIV = CRSNO AND ROWNUM = 1)) as O_CRS_NAME, " +
		"(SELECT CODE_NAME FROM SYST001 WHERE a.HEDU_TYPE = CODE AND KIND = 'HEDU_TYPE' AND ROWNUM = 1) as SCHOOL_TYPE_C, " +
		"a.GRADE_OLD, " +
		"a.SMS_OLD, " +
		"(SELECT CODE_NAME FROM SYST001 WHERE a.GRAD_TYPE = CODE AND KIND = 'GRAD_TYPE' AND ROWNUM = 1) as GRAD_TYPE_C, " +
		"b.APPREVIEW_REASON, " +
		"NVL(b.APP_SEQ,'NA') as B_APP_SEQ, " +
		"b.APP_SEQ_OLD as B_APP_SEQ_OLD, " +
		"b.AYEAR as B_AYEAR, " +
		"b.SMS as B_SMS, " +
		"b.STNO as B_STNO, " +
		"b.CRSNO as B_CRSNO, " +
		"'" + Utility.dbStr(ht.get("APP_SEQ"))+ "' as C_APP_SEQ " +

		"FROM  CCST012 a, CCST014 b, CCST011 c11 " +
		"WHERE " +
		"1	=	1 "
	);

	if(!Utility.checkNull(ht.get("STNO"), "").equals(""))
		sql.append("AND a.STNO	=	'" + Utility.dbStr(ht.get("STNO"))+ "' ");
	if(!Utility.checkNull(ht.get("AYEAR"), "").equals(""))
		sql.append("AND a.AYEAR	=	'" + Utility.dbStr(ht.get("AYEAR"))+ "' ");
	if(!Utility.checkNull(ht.get("SMS"), "").equals(""))
		sql.append("AND a.SMS	=	'" + Utility.dbStr(ht.get("SMS"))+ "' ");

	sql.append
	(
		"AND a.AYEAR = b.AYEAR (+) " +
		"AND a.SMS = b.SMS (+) " +
		"AND a.STNO = b.STNO (+) " +
		"AND a.CRSNO = b.CRSNO (+) " +
		"AND a.APP_SEQ = b.APP_SEQ_OLD (+) " +
		"AND a.re_audit_status = '2' " +
		"and a.APP_SEQ = c11.APP_SEQ and a.AYEAR = c11.AYEAR and a.SMS = c11.SMS and a.STNO = c11.STNO "
	);

	sql.append(	" ORDER BY c11.APP_TYPE, a.priority, DECODE (b.app_seq, " +
				"NULL, " +
				"a.app_seq || a.crsno || a.ayear || a.sms || a.stno, " +
				"b.app_seq || b.app_seq_old || b.ayear || b.sms || b.stno || b.crsno ) " );
	System.out.println("getDataForCCS003_:"+sql.toString());
	DBResult rs = null;

	try {
            if(pageQuery) {
                // 依分頁取出資料
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            } else {
                // 取出所有資料
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }

            while (rs.next()) {
                Hashtable    content    = new Hashtable();
                /** 將欄位抄一份過去 */
                for (int i = 1; i <= rs.getColumnCount(); i++){
                    content.put(rs.getColumnName(i), rs.getString(i));
                }

                vt.add(content);
            }
	} catch (Exception e) {
            throw e;
	} finally {
            if(rs != null) {
                rs.close();
            }
	}
    }

    /**
     * 登錄學分抵免複審作業 - 取得第二頁查詢資料 (申請種類為 1 或 2) (CCST012, COUT002, SYST001)
     * @param ht 條件值
     * @return 回傳 DBResult 物件
     * @throws Exception
     */
    public DBResult getDataForCCS041M_Query2_1(Hashtable ht) throws Exception
    {
        DBResult    rs      =   null;


        try
        {
            //條件欄位
            String  AYEAR       =   Utility.checkNull((String)ht.get("AYEAR"), "");         //學年
            String  SMS         =   Utility.checkNull((String)ht.get("SMS"), "");           //學期
            String  STNO        =   Utility.checkNull((String)ht.get("STNO"), "");          //學號
            String  APP_SEQ     =   Utility.checkNull((String)ht.get("APP_SEQ"), "");       //申請序號
            String  RE_AUDIT_STATUS     =   Utility.checkNull((String)ht.get("RE_AUDIT_STATUS"), "");       //申請序號


            //科目
            StringBuffer    sb1 =   new StringBuffer();

            sb1.append("SELECT CRSNO, CRS_NAME FROM COUT002");


            //本校原修讀科目
            StringBuffer    sb2 =   new StringBuffer();

            sb2.append("SELECT CRSNO, CRS_NAME FROM COUT002");


            //學校種類
            StringBuffer    sb3 =   new StringBuffer();

            sb3.append
            (
                "SELECT CODE, CODE_NAME " +
                "FROM SYST001 " +
                "WHERE " +
                "KIND = 'SCHOOL_TYPE'"
            );

            //複審狀態
            StringBuffer    sb4 =   new StringBuffer();

            sb4.append
            (
                "SELECT CODE, CODE_NAME " +
                "FROM SYST001 " +
                "WHERE " +
                "KIND = 'ADOPT_AUDIT_STATUS'"
            );

            //最高學歷
            StringBuffer    sb5 =   new StringBuffer();

            sb5.append
            (
                "SELECT CODE, CODE_NAME " +
                "FROM SYST001 " +
                "WHERE " +
                "KIND = 'HEDU_TYPE'"
            );


            //將 SQL 清空
            if (sql.length() > 0)
                sql.delete(0, sql.length());


            sql.append
            (
                "SELECT " +
                "CCST012.AYEAR, CCST012.SMS, CCST012.STNO, CCST012.APP_SEQ,CCST012.PRIORITY, " +
                "CCST012.SCHOOL_TYPE, CCST012.RE_AUDIT_STATUS, CCST012.CRSNO, CCST012.CRD, " +
                "CCST012.CRS_NAME_OTHER, CCST012.CRD_OTHER, CCST012.CRSNO_UNIV, " +
                "CCST012.CRD_UNIV, CCST012.GRADE_OLD, CCST012.SMS_OLD, CCST012.REDUCE_CRD, " +
                "ccst012.upd_rmk, ccst012.audit_upd_rmk, CCST012.RE_AUDIT_OPINION, A.CRS_NAME, B.CRS_NAME CRSNO_UNIV_NM, " +
                "C.CODE_NAME SCHOOL_TYPE_NM, D.CODE_NAME RE_AUDIT_STATUS_NM, E.CODE_NAME HEDU_NAME, " +
                "Z.CODE_NAME SCHOOL_TYPE_NAME_1, "+
                "CCST012.rowid||'' AS PKID "+
                "FROM CCST012 " +
                "LEFT JOIN " +
                //科目
                "(" + sb1 + ") A " +
                "ON CCST012.CRSNO = A.CRSNO " +
                "LEFT JOIN " +
                //本校原修讀科目
                "(" + sb2 + ") B " +
                "ON CCST012.CRSNO_UNIV = B.CRSNO " +
                "LEFT JOIN " +
                //學校種類
                "(" + sb3 + ") C " +
                "ON CCST012.SCHOOL_TYPE = C.CODE " +
                "LEFT JOIN " +
                //複審狀態
                "(" + sb4 + ") D " +
                "ON CCST012.RE_AUDIT_STATUS = D.CODE " +
                "LEFT JOIN " +
                //最高學歷
                "(" + sb5 + ") E " +
                "ON CCST012.HEDU_TYPE = E.CODE " +
                "LEFT JOIN SYST001 Z ON CCST012.SCHOOL_TYPE=Z.CODE AND Z.KIND='HEDU_TYPE'  "+
                "WHERE " +
                "1 = 1 "
            );


            //加入條件 - 學年
            if (!"".equals(AYEAR))
                sql.append("AND CCST012.AYEAR = '" + AYEAR + "' ");

            //加入條件 - 學期
            if (!"".equals(SMS))
                sql.append("AND CCST012.SMS = '" + SMS + "' ");

            //加入條件 - 學號
            if (!"".equals(STNO))
                sql.append("AND CCST012.STNO = '" + STNO + "' ");

            //加入條件 - 申請序號
            if (!"".equals(APP_SEQ)){
                sql.append("AND CCST012.APP_SEQ = '" + APP_SEQ + "' ");
            }
            if (!"".equals(RE_AUDIT_STATUS)){
                sql.append("AND CCST012.re_audit_status in (" + RE_AUDIT_STATUS + ") ");
            }
            //排序
            if (!"".equals(orderBy))
            {
                sql.append("ORDER BY " + orderBy);
                orderBy = "";
            }


            System.out.println(sql.toString());


            // 依分頁取出資料
            if (pageQuery)
            {
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            }
            // 取出所有資料
            else
            {
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }


            return rs;
        }
        catch(Exception e)
        {
            throw e;
        }
    }


    /**
     * 登錄學分抵免複審作業 - 取得第二頁查詢資料 (申請種類為 4) (CCST012, CCST014, COUT002, SYST001)
     * @param ht 條件值
     * @return 回傳 DBResult 物件
     * @throws Exception
     */
    public DBResult getDataForCCS041M_Query2_3(Hashtable ht) throws Exception
    {
        DBResult    rs      =   null;


        try
        {
            //條件欄位
            String  AYEAR       =   Utility.checkNull((String)ht.get("AYEAR"), "");         //學年
            String  SMS         =   Utility.checkNull((String)ht.get("SMS"), "");           //學期
            String  STNO        =   Utility.checkNull((String)ht.get("STNO"), "");          //學號
            String  APP_SEQ     =   Utility.checkNull((String)ht.get("APP_SEQ"), "");       //申請序號
            String  RE_AUDIT_STATUS     =   Utility.checkNull((String)ht.get("RE_AUDIT_STATUS"), "");       //申請序號


            //科目
            StringBuffer    sb1 =   new StringBuffer();

            sb1.append("SELECT CRSNO, CRS_NAME FROM COUT002");


            //本校原修讀科目
            StringBuffer    sb2 =   new StringBuffer();

            sb2.append("SELECT CRSNO, CRS_NAME FROM COUT002");


            //學校種類
            StringBuffer    sb3 =   new StringBuffer();

            sb3.append
            (
                "SELECT CODE, CODE_NAME " +
                "FROM SYST001 " +
                "WHERE " +
                "KIND = 'SCHOOL_TYPE'"
            );


            //複審狀態
            StringBuffer    sb4 =   new StringBuffer();

            sb4.append
            (
                "SELECT CODE, CODE_NAME " +
                "FROM SYST001 " +
                "WHERE " +
                "KIND = 'ADOPT_AUDIT_STATUS'"
            );

            //最高學歷
            StringBuffer    sb5 =   new StringBuffer();

            sb5.append
            (
                "SELECT CODE, CODE_NAME " +
                "FROM SYST001 " +
                "WHERE " +
                "KIND = 'HEDU_TYPE'"
            );

            //將 SQL 清空
            if (sql.length() > 0)
                sql.delete(0, sql.length());


            sql.append
            (
                "SELECT " +
                "CCST014.AYEAR, CCST014.SMS, CCST014.STNO, CCST014.APP_SEQ, " +
                "CCST014.APP_SEQ_OLD, CCST014.CRSNO, CCST012.SCHOOL_TYPE, " +
                "CCST014.RE_AUDIT_STATUS, CCST012.CRD, CCST012.CRS_NAME_OTHER, " +
                "CCST012.CRD_OTHER, CCST012.CRSNO_UNIV, CCST012.CRD_UNIV, CCST012.GRADE_OLD, " +
                "CCST012.SMS_OLD, CCST012.REDUCE_CRD, CCST012.UPD_RMK, CCST012.AUDIT_UPD_RMK, CCST014.RE_AUDIT_OPINION, " +
                "A.CRS_NAME, B.CRS_NAME CRSNO_UNIV_NM, C.CODE_NAME SCHOOL_TYPE_NM, " +
                "D.CODE_NAME RE_AUDIT_STATUS_NM, e.code_name HEDU_NAME " +
                "FROM CCST012 JOIN CCST014 " +
                "ON " +
                "CCST012.AYEAR = CCST014.AYEAR AND " +
                "CCST012.SMS = CCST014.SMS AND " +
                "CCST012.APP_SEQ = CCST014.APP_SEQ_OLD AND " +
                "CCST012.STNO = CCST014.STNO AND " +
                "CCST012.CRSNO = CCST014.CRSNO " +
                "LEFT JOIN " +
                //科目
                "(" + sb1 + ") A " +
                "ON CCST012.CRSNO = A.CRSNO " +
                "LEFT JOIN " +
                //本校原修讀科目
                "(" + sb2 + ") B " +
                "ON CCST012.CRSNO_UNIV = B.CRSNO " +
                "LEFT JOIN " +
                //學校種類
                "(" + sb3 + ") C " +
                "ON CCST012.SCHOOL_TYPE = C.CODE " +
                "LEFT JOIN " +
                //複審狀態
                "(" + sb4 + ") D " +
                "ON CCST014.RE_AUDIT_STATUS = D.CODE " +
                "LEFT JOIN " +
                //最高學歷
                "(" + sb5 + ") E " +
                "ON CCST012.HEDU_TYPE = E.CODE " +
                "WHERE " +
                "1 = 1 "
            );


            //加入條件 - 學年
            if (!"".equals(AYEAR))
                sql.append("AND CCST012.AYEAR = '" + AYEAR + "' ");

            //加入條件 - 學期
            if (!"".equals(SMS))
                sql.append("AND CCST012.SMS = '" + SMS + "' ");

            //加入條件 - 學號
            if (!"".equals(STNO))
                sql.append("AND CCST012.STNO = '" + STNO + "' ");

            //加入條件 - 申請序號
            if (!"".equals(APP_SEQ))
                sql.append("AND CCST014.APP_SEQ = '" + APP_SEQ + "' ");

            //sql.append("AND CCST012.RE_AUDIT_STATUS = '" + RE_AUDIT_STATUS + "' ");
            //by poto 寫成  2
            sql.append("AND CCST012.RE_AUDIT_STATUS = '2' ");
            //排序
            if (!"".equals(orderBy))
            {
                sql.append("ORDER BY " + orderBy);
                orderBy = "";
            }


            System.out.println(sql.toString());


            // 依分頁取出資料
            if (pageQuery)
            {
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            }
            // 取出所有資料
            else
            {
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }


            return rs;
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    /**
     *
     * @param ht 條件值
     * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
     *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
     *         若該欄位有中文名稱，則其 KEY 請加上 _NAME, EX: SMS 其中文欄位請設為 SMS_NAME
     * @throws Exception
     */
    public Vector getCodeToPrintForCcs113r(Hashtable ht) throws Exception {
        Vector result = new Vector();
        if(ht == null) {
            ht = new Hashtable();
        }
        if(sql.length() > 0) {
            sql.delete(0, sql.length());
        }
        /*
        sql.append(
            "SELECT a.APP_SEQ, a.STNO, b.NAME, a.CRSNO, c.CRS_NAME, a.CRD, a.REDUCE_CRD " +
            "FROM CCST012 a, STUT002 b, COUT002 c, STUT003 d " +
            "WHERE a.HEDU_TYPE = '5' and d.STNO = a.STNO and b.IDNO = d.IDNO and c.CRSNO = a.CRSNO "
        );
        */
        sql.append(
            "SELECT a.APP_SEQ, a.STNO, b.NAME, a.CRSNO, c.CRS_NAME, a.CRD, "+
            //by poto 20090612     REDUCE_CRD = CRD_OTHER - CRD
            //但是發現 REDUCE_CRD 972並沒有填入 先寫成 CRD_OTHER - CRD  要找時間補
            //"a.REDUCE_CRD " +
            "a.REDUCE_CRD  AS REDUCE_CRD " +
            "FROM CCST012 a "+
            "JOIN STUT003 d ON d.STNO = a.STNO "+
            "JOIN STUT002 b ON b.IDNO = d.IDNO and b.BIRTHDATE = d.BIRTHDATE "+
            "JOIN COUT002 c ON c.CRSNO = a.CRSNO "+
            "WHERE 1=1 "+
            "AND a.SCHOOL_TYPE = '5'  "
            //"AND (a.RE_AUDIT_STATUS = '1'  or a.REDUCE_CRD IS NOT NULL) "
        );
        if(!Utility.nullToSpace(ht.get("AYEAR")).equals("")) {
            sql.append("AND a.AYEAR = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' ");
        }
        if(!Utility.nullToSpace(ht.get("SMS")).equals("")) {
            sql.append("AND a.SMS = '" + Utility.nullToSpace(ht.get("SMS")) + "' ");
        }
        sql.append(" ORDER BY a.APP_SEQ ");

        DBResult rs = null;
        try {
            if(pageQuery) {
                // 依分頁取出資料
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            } else {
                // 取出所有資料
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }
            Hashtable rowHt = null;
            while (rs.next()) {
                rowHt = new Hashtable();
                /** 將欄位抄一份過去 */
                for (int i = 1; i <= rs.getColumnCount(); i++)
                    rowHt.put(rs.getColumnName(i), rs.getString(i));
                result.add(rowHt);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null) {
                rs.close();
            }
        }
        return result;
    }

	public List getCcs111rPrint(Hashtable ht) throws Exception {
		DBResult rs = null;
		List vt = new Vector();
		try {
			if(sql.length() >0) {
				sql.delete(0, sql.length());
			}
			String aYr = Utility.nullToSpace(ht.get("AYEAR"));
			String sms = Utility.nullToSpace(ht.get("SMS"));
			String aSys = Utility.nullToSpace(ht.get("ASYS"));
			String condition = "";
			if(Utility.nullToSpace(ht.get("PRINT_TYPE")).equals("2")) {
				condition = "and (RS.TOT_EQ + RS.TOT_RED) > 40 ";
			}

			/* 失敗的 SQL... */		
/*			
			sql.append(
				"select " +
				"    ccs12.AYEAR, ccs12.SMS, ccs12.STNO, ccs12.APP_SEQ, \n" +
				"    stu2.NAME, ccs12.CURR_HEDU_LIST, RS.TOT_HEDU_LIST, ccs12.CURR_REPLMK_LIST, \n" +
				"    stu3.EDUBKGRD, stu3.REPL_CNT, \n" +
				"    RS.TOT_EQ, RS.TOT_RED, \n" +
				"    RS.EQ_0, RS.EQ_1, RS.EQ_2, RS.EQ_3, RS.EQ_4, RS.EQ_5, RS.EQ_UNKNOWN, \n" +
				"    RS.RED_0, RS.RED_1, RS.RED_2, RS.RED_3, RS.RED_4, RS.RED_5, RS.RED_UNKNOWN, \n" +
				"    ccs01Eq.J5_NOU_REPL, ccs01Eq.J5_NOUJC_REPL, \n" +
				"	 ccs01Eq.J3_NOU_REPL, ccs01Eq.J3_NOUJC_REPL, \n" +
				"	 ccs01Eq.NOUJC_NOU_REPL, ccs01Eq.NOU_NOUJC_REPL, \n" +
				"	 ccs01Eq.UNIV_EE_CRD_NOU_REPL, ccs01Eq.UNIV_EE_CRD_NOUJC_REPL, \n" +
				"	 ccs01Red.J5_REDUCE, ccs01Red.J3_REDUCE, ccs01Red.UNIV_REDUCE \n" +
				"from \n" +
				"		(select \n" +
				"            tmp.AYEAR, tmp.SMS, tmp.STNO, tmp.APP_SEQ, \n" +
				"            WMSYS.WM_CONCAT(tmp.REPL_MK) as CURR_REPLMK_LIST, \n" +
				"            WMSYS.WM_CONCAT(tmp.HEDU_TYPE) as CURR_HEDU_LIST \n" +
				"        from  \n" +
				"				 (select distinct \n" +
				"                    c12.AYEAR, c12.SMS, c12.STNO, c12.APP_SEQ, c12.HEDU_TYPE, c12.REPL_MK \n" +
				"                from CCST012 c12 \n" +
				"                where c12.AYEAR = '"+aYr+"' and c12.SMS = '"+sms+"' and (c12.RE_AUDIT_STATUS in ('1') or reduce_crd is not null )\n" +
				"                union  \n" +
				"                select distinct \n" +
				"                    c13.AYEAR, c13.SMS, c13.STNO, c13.APP_SEQ,\n " +
				"                    c13.SCHOOL_TYPE as HEDU_TYPE, c13.REPL_MK \n" +
				"                from CCST013 c13  \n" +
				"                where  \n" +
				"                    c13.AYEAR = '"+aYr+"' and c13.SMS = '"+sms+"' and \n" +
				"                    c13.AUDIT_STATUS = '2' and c13.APP_REDUCE_CRD <> '0') \n" +
				"            tmp \n" +
				"        group by tmp.AYEAR, tmp.SMS, tmp.STNO, tmp.APP_SEQ) \n" +
				"    ccs12 left outer join \n" +
				"    CCST001 ccs01Eq on ccs12.AYEAR = ccs01Eq.AYEAR and ccs01Eq.ASYS = '"+aSys+"' and \n" +
				"                ccs12.SMS = ccs01Eq.SMS left outer join \n" +
				"    CCST001 ccs01Red on ccs12.AYEAR = ccs01Red.AYEAR and ccs01Red.ASYS = '"+aSys+"' and \n" +
				"                ccs12.SMS = ccs01Red.SMS left outer join \n" +
				"        (select \n" +
				"            ccs.STNO, \n" +
				"            WMSYS.WM_CONCAT(ccs.HEDU_TYPE) as TOT_HEDU_LIST, \n" +
				"            sum(nvl(ccs.CRD, 0)) as TOT_EQ, \n" +
				"			 sum(nvl(ccs.REDUCE_CRD, 0)  + nvl(ccs.APPRV_REDUCE_CRD, 0)) as TOT_RED, \n" +
				"            sum(case when ccs.REPL_MK = '0' then nvl(ccs.CRD, 0)  else 0 end) as EQ_0, \n" +
				"            sum(case when ccs.REPL_MK = '0' then nvl(ccs.REDUCE_CRD, 0)  else 0 end) as RED_0, \n" +
				"            sum(case when ccs.REPL_MK = '1' then nvl(ccs.CRD, 0)  else 0 end) as EQ_1, \n" +
				"            sum(case when ccs.REPL_MK = '1' then nvl(ccs.REDUCE_CRD, 0)  else 0 end) as RED_1, \n" +
				"            sum(case when ccs.REPL_MK = '2' then nvl(ccs.CRD, 0)  else 0 end) as EQ_2, \n" +
				"            sum(case when ccs.REPL_MK = '2' then nvl(ccs.REDUCE_CRD, 0)  else 0 end) as RED_2, \n" +
				"            sum(case when ccs.REPL_MK = '3' then nvl(ccs.CRD, 0)  else 0 end) as EQ_3, \n" +
				"            sum(case when ccs.REPL_MK = '3' then nvl(ccs.REDUCE_CRD, 0)  else 0 end) as RED_3, \n" +
				"            sum(case when ccs.REPL_MK = '4' then 0  else 0  end) as EQ_4, \n" +
				"            sum(case when ccs.REPL_MK = '4' then nvl(ccs.APPRV_REDUCE_CRD, 0)  else 0 end) as RED_4, \n" +
				"            sum(case when ccs.REPL_MK = '5' then nvl(ccs.CRD, 0)  else 0  end) as EQ_5, \n " +
				"            sum(case when ccs.REPL_MK = '5' then nvl(ccs.REDUCE_CRD, 0)  else 0 end) as RED_5, \n" +
				"            sum(case when ccs.REPL_MK in ('0', '1', '2', '3', '4', '5') then 0 \n" +
				"                    else nvl(ccs.CRD, 0)  end) as EQ_UNKNOWN , \n" +
				"            sum(case when ccs.REPL_MK in ('0', '1', '2', '3', '4', '5') then 0 \n" +
				"                    else (nvl(ccs.REDUCE_CRD, 0) + nvl(ccs.APPRV_REDUCE_CRD, 0)) end) as RED_UNKNOWN \n" +
				"        from \n" +
				"				( \n" +
				"                select  \n"+
                "                c12.AYEAR, c12.SMS, c12.STNO, ''||''  AS APP_SEQ, ''||''  AS HEDU_TYPE, \n"+
                "                c12.REPL_MK, c12.CRSNO, NVL(c12.ADOPT_CRD,'0') +nvl(c12.REPL_CRD,'0') AS CRD, \n" +
                //by poto 科目先寫死
                "case when (c12.CRSNO != '040001' AND c12.CRSNO != '010001' AND  c12.CRSNO != '010002') then c12.REDUCE_CRD else 0 end AS REDUCE_CRD,\n " +
                "case when (c12.CRSNO = '040001' OR c12.CRSNO = '010001' OR c12.CRSNO = '010002') then c12.REDUCE_CRD else 0 end as APPRV_REDUCE_CRD  \n" +
                "                from CCST003 c12 WHERE c12.OLD_STNO IS NULL \n" +
                "                union  \n" +
                "                select distinct \n" +
				"                    c12.AYEAR, c12.SMS, c12.STNO, c12.APP_SEQ, c12.HEDU_TYPE, \n" +
				"                    c12.REPL_MK, c12.CRSNO, c12.CRD, c12.REDUCE_CRD, 0 as APPRV_REDUCE_CRD \n" +
				"                from CCST012 c12  \n" +
				"                where ( c12.RE_AUDIT_STATUS in ('1') or c12.REDUCE_CRD is not null) AND c12.AYEAR = '"+aYr+"' and c12.SMS = '"+sms+"' \n" +
				"                union  \n" +
				"                select distinct " +
				"                    c13.AYEAR, c13.SMS, c13.STNO, c13.APP_SEQ, c13.SCHOOL_TYPE as HEDU_TYPE, \n" +
				"                    c13.REPL_MK, null as CRSNO, 0 as CRD, 0 as REDUCE_CRD, c13.APPRV_REDUCE_CRD \n" +
				"                from CCST013 c13 \n" +
				"                where c13.AUDIT_STATUS = '2' and c13.APP_REDUCE_CRD <> '0' AND c13.AYEAR = '"+aYr+"' and c13.SMS = '"+sms+"') \n" +
				"            ccs \n" +
				"        group by ccs.STNO) \n" +
				"    RS on ccs12.STNO = RS.STNO left outer join \n" +
				"    STUT003 stu3 on ccs12.STNO = stu3.STNO left outer join \n" +
				"    STUT002 stu2 on stu3.IDNO = stu2.IDNO and stu3.BIRTHDATE = stu2.BIRTHDATE \n" +
				"where 1=1 and stu3.asys = '"+aSys+"' " + condition +"\n"+
				"order by \n" +
				"	ccs12.APP_SEQ ASC \n");
*/
			//by poto 改寫
			sql.append("select     \n");
			sql.append("	ccs12.AYEAR, ccs12.SMS, ccs12.STNO, ccs12.APP_SEQ,  \n");
			sql.append("	stu2.NAME, ccs12.CURR_HEDU_LIST, RS.TOT_HEDU_LIST, ccs12.CURR_REPLMK_LIST,  \n");
			sql.append("	stu3.EDUBKGRD, stu3.REPL_CNT,  \n");
			sql.append("	RS.TOT_EQ, RS.TOT_RED,  \n");
			sql.append("	RS.EQ_0, RS.EQ_1, RS.EQ_2, RS.EQ_3, RS.EQ_4, RS.EQ_5, RS.EQ_6, RS.EQ_7, RS.EQ_8, RS.EQ_UNKNOWN,  \n");
			sql.append("	RS.RED_0, RS.RED_1, RS.RED_2, RS.RED_3, RS.RED_4, RS.RED_5 , RS.RED_6, RS.RED_7, RS.RED_8, RS.RED_UNKNOWN, \n");
			sql.append("	ccs01Eq.J5_NOU_REPL, ccs01Eq.J5_NOUJC_REPL,  \n");
			sql.append("	ccs01Eq.J3_NOU_REPL, ccs01Eq.J3_NOUJC_REPL,  \n");
			sql.append("	ccs01Eq.NOUJC_NOU_REPL, ccs01Eq.NOU_NOUJC_REPL,  \n");
			sql.append("	ccs01Eq.UNIV_EE_CRD_NOU_REPL, ccs01Eq.UNIV_EE_CRD_NOUJC_REPL,  \n");
			sql.append("	ccs01Red.J5_REDUCE, ccs01Red.J3_REDUCE, ccs01Red.UNIV_REDUCE  \n");
			sql.append("from  \n");
			sql.append("	(  \n");
			sql.append("	select  \n");
			sql.append("	tmp.AYEAR, tmp.SMS, tmp.STNO, tmp.APP_SEQ,  \n");
			sql.append("	WMSYS.WM_CONCAT(tmp.REPL_MK) as CURR_REPLMK_LIST,  \n");
			sql.append("	WMSYS.WM_CONCAT(tmp.HEDU_TYPE) as CURR_HEDU_LIST  \n");
			sql.append("	from   \n");
			sql.append("		(    \n");
			sql.append("			select UNIQUE c12.AYEAR, c12.SMS, c12.STNO, c12.APP_SEQ, TO_NUMBER(c12.HEDU_TYPE) AS HEDU_TYPE, c12.REPL_MK  \n");
			sql.append("			from CCST012 c12  \n");
			sql.append("			where c12.AYEAR = '"+aYr+"' and c12.SMS = '"+sms+"' and (c12.RE_AUDIT_STATUS in ('1') or reduce_crd is not null ) \n");
			sql.append("			union   \n");
			sql.append("			select UNIQUE c23.AYEAR, c23.SMS, c23.STNO, c23.APP_SEQ,TO_NUMBER(c23.REPL_HEDU_TYPE) as HEDU_TYPE, c23.REPL_MK  \n");
			sql.append("			from CCST023 c23   \n");
			sql.append("			where c23.AYEAR = '"+aYr+"' and c23.SMS = '"+sms+"' and  c23.AUDIT_STATUS = '2' and c23.APP_REDUCE_CRD <> '0' \n");
			sql.append("		 )  tmp  \n");
			sql.append("		 group by tmp.AYEAR, tmp.SMS, tmp.STNO, tmp.APP_SEQ \n");
			sql.append("	) ccs12  \n");
			sql.append("	left join CCST001 ccs01Eq on ccs12.AYEAR = ccs01Eq.AYEAR and ccs01Eq.ASYS = '"+aSys+"' and ccs12.SMS = ccs01Eq.SMS  \n");
			sql.append("	left join CCST001 ccs01Red on ccs12.AYEAR = ccs01Red.AYEAR and ccs01Red.ASYS = '"+aSys+"' and ccs12.SMS = ccs01Red.SMS  \n");
			sql.append("	left join  \n");
			sql.append("		(select  \n");
			sql.append("			ccs.STNO,  \n");
			sql.append("			WMSYS.WM_CONCAT(ccs.HEDU_TYPE) as TOT_HEDU_LIST,  \n");
			sql.append("			sum(nvl(ccs.CRD, 0)) as TOT_EQ,  \n");
			sql.append("			sum(nvl(ccs.REDUCE_CRD, 0)  + nvl(ccs.APPRV_REDUCE_CRD, 0)) as TOT_RED,  \n");
			sql.append("			sum(case when ccs.REPL_MK = '0' then nvl(ccs.CRD, 0)  else 0 end) as EQ_0,  \n");
			sql.append("			sum(case when ccs.REPL_MK = '0' then nvl(ccs.REDUCE_CRD, 0)  else 0 end) as RED_0,  \n");
			sql.append("			sum(case when ccs.REPL_MK = '1' then nvl(ccs.CRD, 0)  else 0 end) as EQ_1,  \n");
			sql.append("			sum(case when ccs.REPL_MK = '1' then nvl(ccs.REDUCE_CRD, 0)  else 0 end) as RED_1,  \n");
			sql.append("			sum(case when ccs.REPL_MK = '2' then nvl(ccs.CRD, 0)  else 0 end) as EQ_2,  \n");
			sql.append("			sum(case when ccs.REPL_MK = '2' then nvl(ccs.REDUCE_CRD, 0)  else 0 end) as RED_2,  \n");
			sql.append("			sum(case when ccs.REPL_MK = '3' then nvl(ccs.CRD, 0)  else 0 end) as EQ_3,  \n");
			sql.append("			sum(case when ccs.REPL_MK = '3' then nvl(ccs.REDUCE_CRD, 0)  else 0 end) as RED_3,  \n");
			sql.append("			sum(case when ccs.REPL_MK = '4' then 0  else 0  end) as EQ_4,  \n");
			sql.append("			sum(case when ccs.REPL_MK = '4' then nvl(ccs.APPRV_REDUCE_CRD, 0)  else 0 end) as RED_4,  \n");
			sql.append("			sum(case when ccs.REPL_MK = '5' then nvl(ccs.CRD, 0)  else 0  end) as EQ_5,  \n");
			sql.append("			sum(case when ccs.REPL_MK = '5' then nvl(ccs.REDUCE_CRD, 0)  else 0 end) as RED_5,  \n");
			sql.append("			sum(case when ccs.REPL_MK = '6' then 0  else 0  end) as EQ_6,  \n");
			sql.append("			sum(case when ccs.REPL_MK = '6' then nvl(ccs.APPRV_REDUCE_CRD, 0)  else 0 end) as RED_6,  \n");
			sql.append("			sum(case when ccs.REPL_MK = '7' then 0  else 0  end) as EQ_7,  \n");
			sql.append("			sum(case when ccs.REPL_MK = '7' then nvl(ccs.APPRV_REDUCE_CRD, 0)  else 0 end) as RED_7,  \n");
			sql.append("			sum(case when ccs.REPL_MK = '8' then 0  else 0  end) as EQ_8,  \n");
			sql.append("			sum(case when ccs.REPL_MK = '8' then nvl(ccs.APPRV_REDUCE_CRD, 0)  else 0 end) as RED_8,   \n");
			sql.append("			sum(case when ccs.REPL_MK in ('0', '1', '2', '3', '4', '5', '6', '7', '8') then 0 else nvl(ccs.CRD, 0)  end) as EQ_UNKNOWN ,  \n");
			sql.append("			sum(case when ccs.REPL_MK in ('0', '1', '2', '3', '4', '5', '6', '7', '8') then 0 else (nvl(ccs.REDUCE_CRD, 0) + nvl(ccs.APPRV_REDUCE_CRD, 0)) end) as RED_UNKNOWN  \n");
			sql.append("		from  \n");
			sql.append("		(  \n");
			sql.append("			select   \n");
			sql.append("			c12.AYEAR, c12.SMS, c12.STNO, ''||''  AS APP_SEQ, TO_NUMBER(decode(c12.REPL_HEDU_TYPE,'05','',c12.REPL_HEDU_TYPE)) AS HEDU_TYPE,  \n");
			sql.append("			c12.REPL_MK, c12.CRSNO, NVL(c12.ADOPT_CRD,'0') +nvl(c12.REPL_CRD,'0') AS CRD,  \n");
			sql.append("			case when C12.REPL_MK NOT IN('4','6','7','8') then c12.REDUCE_CRD else 0 end AS REDUCE_CRD, \n");
			sql.append("			case when C12.REPL_MK IN('4','6','7','8') then c12.REDUCE_CRD else 0 end as APPRV_REDUCE_CRD  \n");
			sql.append("			from CCST003 c12  \n");
			sql.append("			WHERE c12.OLD_STNO IS NULL   \n");			
			sql.append("			union   \n");
			sql.append("			select unique c12.AYEAR, c12.SMS, c12.STNO, c12.APP_SEQ, TO_NUMBER(c12.SCHOOL_TYPE) AS HEDU_TYPE,c12.REPL_MK, c12.CRSNO, c12.CRD, c12.REDUCE_CRD, 0 as APPRV_REDUCE_CRD  \n");
			sql.append("			from CCST012 c12   \n");
			sql.append("			where ( c12.RE_AUDIT_STATUS in ('1') or c12.REDUCE_CRD is not null) AND c12.AYEAR = '"+aYr+"' and c12.SMS = '"+sms+"' and 0=(select count(1) from ccst003 where ayear = '"+aYr+"' and sms ='"+sms+"') \n");
			sql.append("			union all \n");
			sql.append("			select c23.AYEAR, c23.SMS, c23.STNO, c23.APP_SEQ, TO_NUMBER(decode(c23.REPL_HEDU_TYPE,'05','',c23.REPL_HEDU_TYPE)) as HEDU_TYPE,c23.REPL_MK, null as CRSNO, 0 as CRD, 0 as REDUCE_CRD, c23.APPRV_REDUCE_CRD  \n");
			sql.append("			from CCST023 c23  \n");
			sql.append("			where c23.AUDIT_STATUS = '2' and c23.APP_REDUCE_CRD <> '0' AND c23.AYEAR = '"+aYr+"' and c23.SMS = '"+sms+"' and 0=(select count(1) from ccst003 where ayear = '"+aYr+"' and sms ='"+sms+"') \n");
			sql.append("		)   ccs  \n");
			sql.append("		group by ccs.STNO) RS on ccs12.STNO = RS.STNO      \n");
			sql.append("	join STUT003 stu3 on ccs12.STNO = stu3.STNO  \n");
			sql.append("	join STUT002 stu2 on stu3.IDNO = stu2.IDNO and stu3.BIRTHDATE = stu2.BIRTHDATE  \n");
			sql.append("where 1=1 and stu3.asys = '"+aSys+"' " + condition +" \n");
			//sql.append(" and stu3.stno = '951213794'  \n");  //for test
			sql.append("order by ccs12.APP_SEQ ASC  \n");
			if(pageQuery) {
				rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
			} else {
				rs = dbmanager.getSimpleResultSet(conn);
				rs.open();
				rs.executeQuery(sql.toString());
			}

			Map rowHt = null;
			while (rs.next()) {
				rowHt = new Hashtable();
				for (int i = 1; i <= rs.getColumnCount(); i++) {
					rowHt.put(rs.getColumnName(i), rs.getString(i));
				}
				vt.add(rowHt);
			}
		} catch(Exception e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
		return vt;
	}

    public DBResult getDataForCCS003_MaxPriority(Hashtable ht) throws Exception {
	if(sql.length() > 0) {
		sql.delete(0, sql.length());
	}

	sql.append
	(
		"SELECT " +
		"MAX (a.PRIORITY+1) AS PRIORITY " +

		"FROM  CCST012 a, CCST014 b " +
		"WHERE " +
		"1	=	1 "
	);

	if(!Utility.checkNull(ht.get("STNO"), "").equals(""))
		sql.append("AND a.STNO	=	'" + Utility.dbStr(ht.get("STNO"))+ "' ");
	if(!Utility.checkNull(ht.get("AYEAR"), "").equals(""))
		sql.append("AND a.AYEAR	=	'" + Utility.dbStr(ht.get("AYEAR"))+ "' ");
	if(!Utility.checkNull(ht.get("SMS"), "").equals(""))
		sql.append("AND a.SMS	=	'" + Utility.dbStr(ht.get("SMS"))+ "' ");
	if(!Utility.checkNull(ht.get("APP_SEQ"), "").equals(""))
		sql.append("AND a.APP_SEQ	=	'" + Utility.dbStr(ht.get("APP_SEQ"))+ "' ");

	sql.append
	(
		"AND a.AYEAR = b.AYEAR (+) " +
		"AND a.SMS = b.SMS (+) " +
		"AND a.STNO = b.STNO (+) " +
		"AND a.CRSNO = b.CRSNO (+) " +
		"AND a.APP_SEQ = b.APP_SEQ_OLD (+) "
	);

	sql.append(" ORDER BY a.PRIORITY ");
	System.out.println("getDataForCCS003_MaxPriority:"+sql.toString());
	DBResult rs = null;

	try {
            if(pageQuery) {
                // 依分頁取出資料
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            } else {
                // 取出所有資料
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }

            return rs;

	} catch (Exception e) {
            throw e;
	}
    }


	/**取得本次申請通過的抵免學分*/
    public int getCrdThisYear(Hashtable ht) throws Exception {

	int CRD = 0;

	if(sql.length() > 0) {
		sql.delete(0, sql.length());
	}

	sql.append("SELECT SUM(a.CRD) AS CRD FROM ( ");

	sql.append
	(
		"SELECT " +
		"DISTINCT CRSNO, CRD " +
		"FROM  CCST012 " +
		"WHERE " +
		"1	=	1 "
	);

	if(!Utility.checkNull(ht.get("STNO"), "").equals(""))
		sql.append("AND STNO	=	'" + Utility.dbStr(ht.get("STNO"))+ "' ");
	if(!Utility.checkNull(ht.get("AYEAR"), "").equals(""))
		sql.append("AND AYEAR	=	'" + Utility.dbStr(ht.get("AYEAR"))+ "' ");
	if(!Utility.checkNull(ht.get("SMS"), "").equals(""))
		sql.append("AND SMS	=	'" + Utility.dbStr(ht.get("SMS"))+ "' ");

	sql.append("AND RE_AUDIT_STATUS	=	'1' AND HEDU_TYPE IN('1','2','3','4') ");

	sql.append(") a ");

	DBResult rs = null;

	try {
            if(pageQuery) {
                // 依分頁取出資料
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            } else {
                // 取出所有資料
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }

		if (rs.next())
		{
			CRD = rs.getInt("CRD");
		}

            return CRD;

	} catch (Exception e) {
            throw e;
	}
	finally
	{
		if (rs != null)
			rs.close();
	}
    }

	/**取得本次申請通過的抵免學分*/
    public int getCrdThisYearForCcs011m(Hashtable ht) throws Exception {

	int CRD = 0;

	if(sql.length() > 0) {
		sql.delete(0, sql.length());
	}

	sql.append("SELECT SUM(c.CRD) AS CRD FROM ( ");

	sql.append
	(
		"SELECT " +
		"DISTINCT a.CRSNO, a.CRD " +
		"FROM  CCST012 a, CCST011 b " +
		"WHERE " +
		"1	=	1 "
	);

	if(!Utility.checkNull(ht.get("STNO"), "").equals(""))
		sql.append("AND b.STNO	=	'" + Utility.dbStr(ht.get("STNO"))+ "' ");
	if(!Utility.checkNull(ht.get("AYEAR"), "").equals(""))
		sql.append("AND b.AYEAR	=	'" + Utility.dbStr(ht.get("AYEAR"))+ "' ");
	if(!Utility.checkNull(ht.get("SMS"), "").equals(""))
		sql.append("AND b.SMS	=	'" + Utility.dbStr(ht.get("SMS"))+ "' ");

	sql.append("AND b.APP_TYPE	=	'1' ");

	sql.append("AND a.RE_AUDIT_STATUS	=	'1' ");

	sql.append("AND a.AYEAR	=	b.AYEAR ");
	sql.append("AND a.SMS	=	b.SMS ");
	sql.append("AND a.STNO	=	b.STNO ");
	sql.append("AND a.APP_SEQ	=	b.APP_SEQ ");

	sql.append(") c ");
	System.out.println("getCrdThisYearForCcs011m:"+sql.toString());
	DBResult rs = null;

	try {
            if(pageQuery) {
                // 依分頁取出資料
                rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
            } else {
                // 取出所有資料
                rs = dbmanager.getSimpleResultSet(conn);
                rs.open();
                rs.executeQuery(sql.toString());
            }

		if (rs.next())
		{
			CRD = rs.getInt("CRD");
		}

            return CRD;

	} catch (Exception e) {
            throw e;
	}
	finally
	{
		if (rs != null)
			rs.close();
	}
    }
    
    /**
    *
    * CCS115R_學分抵免推廣教育學分採認科目檢核一覽表
    * @return 回傳 Vector 物件，內容為 Hashtable 的集合，<br>
    *         每一個 Hashtable 其 KEY 為欄位名稱，KEY 的值為欄位的值<br>
    *         若該欄位有中文名稱，則其 KEY 請加上 _NAME, EX: SMS 其中文欄位請設為 SMS_NAME
    * @throws Exception
    */
   public Vector getCcst012ccs115rPrint(Hashtable ht) throws Exception {
       if(ht == null) {
           ht = new Hashtable();
       }
       Vector result = new Vector();
       if(sql.length() > 0) {
           sql.delete(0, sql.length());
       }
       sql.append(
           " SELECT DISTINCT R3.FACULTY_NAME, '大學部' AS ASYS, M.AYEAR, M.SMS, R1.FACULTY_CODE, M.CRSNO AS CRSNO, R1.CRS_NAME, R1.CRD," +
           " TO_CHAR(DECODE(R2.COUT103,'','COU016M_登錄課程採計尚未設定',SUBSTR(R2.COUT103,1,3)||'學年已設'||R3.FACULTY_NAME||'主開')) AS COUT103_CHECK, '' AS RMK " +
           " FROM CCST012 m " +
           " JOIN COUT002 R1 ON R1.CRSNO = M.CRSNO " +
           " LEFT JOIN " +
           "      (SELECT  A1.FACULTY_CODE, A1.TOTAL_CRS_NO, A1.CRS_GROUP_CODE, A1.CRSNO, MAX(A1.AYEAR||DECODE(A1.SMS,'3','0',A1.SMS))||A1.FACULTY_CODE as cout103 FROM COUT103 A1  " +
           "           WHERE 1=1 " +
           "            AND A1.AYEAR||DECODE(A1.SMS,'3','0',A1.SMS) <= '" + Utility.nullToSpace(ht.get("AYEAR")) + "||" + Utility.nullToSpace(ht.get("SMS")) + "' AND A1.TOTAL_CRS_NO = '01' AND A1.CRS_GROUP_CODE = '002'  " +
           "            AND A1.CRSNO IN ( " +
           "                SELECT DISTINCT M1.CRSNO FROM CCST012 M1 " +
           "                JOIN STUT003 R1 ON R1.STNO = M1.STNO AND R1.ASYS = '1' " +
           "                 WHERE M1.SCHOOL_TYPE = '6'  /* 推廣教育學分班 */  " +
           "                 AND M1.RE_AUDIT_STATUS = '1' /* 複審通過 */ " +
           "                 AND M1.AYEAR = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' AND M1.SMS = '" + Utility.nullToSpace(ht.get("SMS")) + "' ) " +
           "                 GROUP BY A1.FACULTY_CODE, A1.TOTAL_CRS_NO, A1.CRS_GROUP_CODE, A1.CRSNO " +
           "                 ORDER BY A1.FACULTY_CODE, A1.TOTAL_CRS_NO, A1.CRS_GROUP_CODE, A1.CRSNO " +
           "       ) R2 ON R2.CRSNO = M.CRSNO " +
           " LEFT JOIN SYST003 R3 ON R3.FACULTY_CODE = R1.FACULTY_CODE " +
           " JOIN STUT003 R4 ON R4.STNO = M.STNO AND R4.ASYS = '1' " +
           " WHERE M.SCHOOL_TYPE = '6'  /* 推廣教育學分班 */ " +
           " AND M.RE_AUDIT_STATUS = '1' /* 複審通過 */ " +
           " AND M.AYEAR = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' " +
           " AND M.SMS = '" + Utility.nullToSpace(ht.get("SMS")) + "' " 
           );
       if(!Utility.nullToSpace(ht.get("FACULTY_CODE")).equals("")) {
           sql.append("AND R1.FACULTY_CODE = '" + Utility.nullToSpace(ht.get("FACULTY_CODE")) + "' ");
       }
       sql.append(
           " UNION  /*專科部*/ " +
           " SELECT DISTINCT R3.FACULTY_NAME, '專科部' AS ASYS, M.AYEAR, M.SMS, R1.FACULTY_CODE, M.CRSNO AS CRSNO, R1.CRS_NAME, R1.CRD," +
           " TO_CHAR(DECODE(R2.COUT103,'','COU007M_維護空專課程尚未設定',SUBSTR(R2.COUT103,1,3)||'學年已設定空專課程'))  AS COUT103_CHECK, '' AS RMK " +
           " FROM CCST012 m " +
           " JOIN COUT002 R1 ON R1.CRSNO = M.CRSNO " +
           " LEFT JOIN " +
           "      (SELECT  A1.FACULTY_CODE, A1.TOTAL_CRS_NO, A1.CRS_GROUP_CODE, A1.CRSNO, MAX(A1.AYEAR||DECODE(A1.SMS,'3','0',A1.SMS))||A1.FACULTY_CODE as cout103 FROM COUT103 A1  " +
           "           WHERE 1=1 " +
           "            AND A1.AYEAR||DECODE(A1.SMS,'3','0',A1.SMS) <= '" + Utility.nullToSpace(ht.get("AYEAR")) + "||" + Utility.nullToSpace(ht.get("SMS")) + "' AND A1.TOTAL_CRS_NO = '01' AND A1.CRS_GROUP_CODE = '002'  " +
           "            AND A1.CRSNO IN ( " +
           "                SELECT DISTINCT M1.CRSNO FROM CCST012 M1 " +
           "                JOIN STUT003 R1 ON R1.STNO = M1.STNO AND R1.ASYS = '2' " +
           "                 WHERE M1.SCHOOL_TYPE = '6'  /* 推廣教育學分班 */  " +
           "                 AND M1.RE_AUDIT_STATUS = '1' /* 複審通過 */ " +
           "                 AND M1.AYEAR = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' AND M1.SMS = '" + Utility.nullToSpace(ht.get("SMS")) + "' ) " +
           "                 GROUP BY A1.FACULTY_CODE, A1.TOTAL_CRS_NO, A1.CRS_GROUP_CODE, A1.CRSNO " +
           "                 ORDER BY A1.FACULTY_CODE, A1.TOTAL_CRS_NO, A1.CRS_GROUP_CODE, A1.CRSNO " +
           "       ) R2 ON R2.CRSNO = M.CRSNO " +
           " LEFT JOIN SYST003 R3 ON R3.FACULTY_CODE = R1.FACULTY_CODE " +
           " JOIN STUT003 R4 ON R4.STNO = M.STNO AND R4.ASYS = '2' " +
           " WHERE M.SCHOOL_TYPE = '6'  /* 推廣教育學分班 */ " +
           " AND M.RE_AUDIT_STATUS = '1' /* 複審通過 */ " +
           " AND M.AYEAR = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' " +
           " AND M.SMS = '" + Utility.nullToSpace(ht.get("SMS")) + "' " +
           "  "
           );

       //if(!Utility.nullToSpace(ht.get("AYEAR")).equals("")) {
       //    sql.append("AND M.AYEAR = '" + Utility.nullToSpace(ht.get("AYEAR")) + "' ");
       //}
       //if(!Utility.nullToSpace(ht.get("SMS")).equals("")) {
       //    sql.append("AND M.SMS = '" + Utility.nullToSpace(ht.get("SMS")) + "' ");
       //}
       if(!Utility.nullToSpace(ht.get("FACULTY_CODE")).equals("")) {
           sql.append("AND R1.FACULTY_CODE = '" + Utility.nullToSpace(ht.get("FACULTY_CODE")) + "' ");
       }

           sql.append(" ORDER BY 5,2,6 ");
       DBResult rs = null;
       try {
           if(pageQuery) {
               // 依分頁取出資料
               rs = Page.getPageResultSet(dbmanager, conn, sql.toString(), pageNo, pageSize);
           } else {
               // 取出所有資料
               rs = dbmanager.getSimpleResultSet(conn);
               rs.open();
               rs.executeQuery(sql.toString());
           }
           Hashtable rowHt = null;
           while (rs.next()) {
               rowHt = new Hashtable();
               /** 將欄位抄一份過去 */
               for (int i = 1; i <= rs.getColumnCount(); i++)
                   rowHt.put(rs.getColumnName(i), rs.getString(i));

               result.add(rowHt);
           }
       } catch (Exception e) {
           throw e;
       } finally {
           if(rs != null) {
               rs.close();
           }
       }
       return result;
   }

}