package com.chunjin.crawer.db;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class getDBInfo {

    public static HashMap<String, Integer> getthreadStats() throws Exception {

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        Connection conn = (Connection) JdbcUtil.getConnection();
        PreparedStatement pst = (PreparedStatement) conn.prepareStatement("select thread,maxnum from threadSchedual where final>maxnum");
        ResultSet set = pst.executeQuery();
        while (set.next()) {
            map.put(set.getString("thread"), set.getInt("maxnum"));
        }
        JdbcUtil.CloseConnection(conn, pst, set);
        return map;
    }

    public static List<Integer> gettaskpage() throws Exception {
        List<Integer> list = new ArrayList<Integer>();
//        HashMap<String, Integer> map = new HashMap<String, Integer>();
        Connection conn = (Connection) JdbcUtil.getConnection();
        PreparedStatement pst = (PreparedStatement) conn.prepareStatement("select thread,maxnum,final-maxnum len from threadSchedual where final>maxnum");
        ResultSet set = pst.executeQuery();
        while (set.next()) {
            int start = set.getInt("maxnum");
            int length = set.getInt("len");
            for (int i = 1; i <= length; i++) {
                list.add(start + i);
            }
        }
        JdbcUtil.CloseConnection(conn, pst, set);
        return list;
    }

    public static HashMap<String, String> getPatentNum() throws Exception {
        HashMap<String, String> map = new HashMap<String, String>();
        Connection conn = (Connection) JdbcUtil.getConnection();
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT MOD(rownum,500) thread ,GROUP_CONCAT(PatentNum)  numlist FROM PNO   ");
        sb.append(" WHERE rownum < 100000                                     ");
        sb.append(" GROUP BY MOD(rownum,500)                                  ");
        PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sb.toString());
        ResultSet set = pst.executeQuery();
        while (set.next()) {
            String tIndex = set.getString("thread");
            String patentnum = set.getString("numlist");
            map.put(tIndex, patentnum);
        }
        JdbcUtil.CloseConnection(conn, pst, set);
        return map;
    }



}
