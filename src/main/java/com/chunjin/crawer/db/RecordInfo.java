package com.chunjin.crawer.db;

import java.util.List;

import com.chunjin.crawer.PatentObject;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class RecordInfo {

    public static void insertDetail(List<PatentObject> poList) throws Exception {

        if (poList.size() < 1)
            return;
        Connection conn = (Connection) JdbcUtil.getConnection();
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO patentevent ( ");
        sb.append("  PatentNum,                          ");
        sb.append("  occurDate,                          ");
        sb.append("  type,                               ");
        sb.append("  comments                            ");
        sb.append(")                                     ");
        sb.append("VALUES  (?,?,?,?)                       ");
        PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sb.toString());
        for (PatentObject e : poList) {
            pst.setString(1, e.getPatentNum());
            pst.setString(2, e.getOccurDate());
            pst.setString(3, e.getType());
            pst.setString(4, e.getComments());
            pst.addBatch();
        }
        pst.executeBatch();
//        System.out.println("insert rows " + poList.size());
        JdbcUtil.CloseConnection(conn, pst, null);
    }


    public static void insertoMainData(List<String> poList,String pagenum) throws Exception {

        if (poList.size() < 1)
            return;
        Connection conn = (Connection) JdbcUtil.getConnection();
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO Patent ( ");
        sb.append("  PatentNum ,page,STATUS)                         ");
        sb.append("VALUES  (?,?,'N')                       ");
        PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sb.toString());
        for (String s : poList) {
            pst.setString(1, s);
            pst.setString(2,pagenum);
            pst.addBatch();
        }
        pst.executeBatch();
        System.out.println("insert rows " + poList.size());
        JdbcUtil.CloseConnection(conn, pst, null);
    }


    public static void getExists(List<String> poList,String pagenum) throws Exception {

        if (poList.size() < 1)
            return;
        Connection conn = (Connection) JdbcUtil.getConnection();
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO Patent ( ");
        sb.append("  PatentNum ,page,STATUS)                         ");
        sb.append("VALUES  (?,?,'N')                       ");
        PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sb.toString());
        for (String s : poList) {
            pst.setString(1, s);
            pst.setString(2,pagenum);
            pst.addBatch();
        }
        pst.executeBatch();
        System.out.println("insert rows " + poList.size());
        JdbcUtil.CloseConnection(conn, pst, null);
    }
}
