
package com.chunjin.crawer;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.chunjin.crawer.db.RecordInfo;
import org.apache.http.HttpHost;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawerMainData {


  private static volatile BlockingQueue<HttpHost> qproxy = new ArrayBlockingQueue<HttpHost>(5000, true);

  private CrawerMainData() throws Exception {
    for (int i = 0; i < 1; i++) {
      //first,get proxy from servers
      getProxyfromServer();
      Thread.sleep(1000);
    }
  }

  private void begin() throws Exception {
    ExecutorService pool = Executors.newFixedThreadPool(10);

    List<Integer> list = new ArrayList<Integer>();
    for (int i = 0; i < list.size(); i++) {
      List<Integer> temp = new ArrayList<Integer>();
      temp.add(list.get(i));
      pool.execute(new CrawerWeb(temp));
    }
  }

  private class CrawerWeb implements Runnable {

    private String listUrl = "http://epub.sipo.gov.cn/overTran.action";
    private String contentUrl = "http://epub.sipo.gov.cn/fullTran.action";
    private HttpHost threadproxy;
    private int threadIndex;
    private int startIndex;
    private List<Integer> tList;

    public CrawerWeb(int index) throws Exception {
      super();
      this.threadproxy = getHttpHost();
      this.threadIndex = index;
    }

    public CrawerWeb(int index, int starEndex) throws Exception {
      super();
      this.threadproxy = getHttpHost();
      this.threadIndex = index;
      this.startIndex = starEndex;
    }

    public CrawerWeb(List<Integer> tlist) throws Exception {
      super();
      this.threadproxy = getHttpHost();
      this.tList = tlist;
    }


    public void run() {
      for (int page : this.tList) {
        try {
          crawer(String.valueOf(page));
        } catch (Exception e) {
          Log.info("errors happens page ," + page + ": try again " + e.getMessage());
          continue;
        }
      }
    }

    public void crawer(String pagenow) throws Exception {

      Map<String, String> map = new HashMap<String, String>();
      map.put("strWord", "");
      map.put("numFM", "0");
      map.put("numXX", "");
      map.put("numWG", "");
      map.put("pageSize", "20");
      map.put("pageNow", pagenow);
      map.put("numSortMethod", "0");
      map.put("strLicenseCode", "");
      map.put("selected", "FM");
      map.put("numType", "18");
      Elements rows = new Elements();
      Boolean flag = false;
      while (!flag) {
        try {
          String List = HttpClientUtil.doPost(listUrl, map, this.threadproxy);
          Document doc = Jsoup.parse(List);
          rows = doc.select("td[class=td_td]").select("a[href]");
          if (rows == null || rows.size() < 1) {
            flag = false;
            this.threadproxy = getHttpHost();
          } else {
            flag = true;
          }
        } catch (Exception e) {
          flag = false;
          this.threadproxy = getHttpHost();
        }
      }
      ArrayList<String> noList = new ArrayList<String>();
      for (Element key : rows) {
        noList.add(key.text());
      }
      try {
        RecordInfo.insertoMainData(noList, pagenow);
        System.out.println("Done ： Thread ：" + threadIndex + "    No." + pagenow);
      } catch (Exception e) {
        Log.error("Error ： Thread ：" + threadIndex + "  page" + pagenow + " " + e.getMessage());
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) throws Exception {
    CrawerMainData c = new CrawerMainData();
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
//    for (HttpHost proxy : MipuProxy.geProxyList()) {
//      qproxy.put(proxy);
//    }
  }


}

