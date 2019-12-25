package com.chunjin.crawer;

import com.chunjin.crawer.db.RecordInfo;
import com.chunjin.crawer.db.getDBInfo;
import org.apache.http.HttpHost;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CrawerDetailData {
    private static volatile BlockingQueue<HttpHost> qproxy = new ArrayBlockingQueue<HttpHost>(5000, true);

    public CrawerDetailData() throws Exception {
        for (int i = 0; i < 5; i++) {
            getProxyfromServer();
            Thread.sleep(5000);
        }
    }

    public void begin() throws Exception {

        ExecutorService pool = Executors.newFixedThreadPool(500);
        HashMap<String, String> map = getDBInfo.getPatentNum();

        for (Map.Entry<String, String> en : map.entrySet()) {
            String treadnum = en.getKey();
            String patentnum = en.getValue();
            pool.execute(new CrawerWeb(treadnum, patentnum));
            Thread.sleep((long) (4000* Math.random()));
            System.out.println("thread :"+treadnum+" start");
        }
    }

    private class CrawerWeb implements Runnable {

        private String contentUrl = "http://epub.sipo.gov.cn/fullTran.action";
        private HttpHost threadproxy;
        private String threadIndex;
        private String patentNum;

        public CrawerWeb(String threadId, String patent) throws Exception {
            super();
            this.threadproxy = getHttpHost();
            this.threadIndex = threadId;
            this.patentNum = patent;
        }

        public void run() {

            for (String num : this.patentNum.split(",")) {
                try {
                    crawer(String.valueOf(num));
                } catch (Exception e) {
                    Log.info(this.threadIndex + " errors ," + num + ": try again " + e.getMessage());
                    continue;
                }
            }
        }

        public void crawer(String patenNo) throws Exception {

            HashMap<String, String> map = new HashMap<String, String>();
            map.put("an", patenNo);
            Elements tables = new Elements();
            Boolean flag = false;
            while (!flag){
                try {
                    String List = HttpClientUtil.doPost(contentUrl, map, this.threadproxy);
                    Document doc = Jsoup.parse(List);
                    tables = doc.select("div[class=table_flztxx]");
                    if (tables == null || tables.size() < 1) {
                        flag = false;
                        System.out.println(this.threadIndex+": get the proxy");
                        this.threadproxy = getHttpHost();
                    } else {
                        flag = true;
                    }
                } catch (Exception e) {
                    flag = false;
                    System.out.println(this.threadIndex+": get the proxy");
                    this.threadproxy = getHttpHost();
                }
            }
            List<PatentObject> poList = new ArrayList<PatentObject>();
            for (Element table : tables) {
                Elements e = table.select("td[bgcolor=#FFFFFF]");
                PatentObject p = new PatentObject(e.get(1).text(), e.get(5).text(), e.get(7).text(),
                        e.get(8).text());
                poList.add(p);
            }
            try{
                RecordInfo.insertDetail(poList);
//                System.out.println("threadIndex ：Done   No." + patenNo);
            } catch (Exception e) {
                Log.error("Error ： Thread ：" + threadIndex + "  page" + patenNo + " " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        CrawerDetailData c = new CrawerDetailData();
        c.begin();
    }

    public static HttpHost getHttpHost() throws Exception {
        if (qproxy.size() < 50) {
            synchronized (qproxy) {
                if (qproxy.size() < 50) {
                    getProxyfromServer();
                }
            }
        }
        HttpHost proxy = qproxy.take();
        return proxy;
    }

    private static void getProxyfromServer() throws Exception {

//        for (HttpHost proxy : MipuProxy.geProxyList()) {
//            qproxy.put(proxy);
//        }
    }

}
