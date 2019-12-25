package com.chunjin.crawer.task;

import com.chunjin.crawer.HttpClientUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JPatentMainData extends BaseTask {


  private Map<String, String> getQueryParamMap() {
    String page = "2";
    Map<String, String> queryMap = new HashMap<String, String>();
    queryMap.put("strWord", "");
    queryMap.put("numFM", "0");
    queryMap.put("numXX", "");
    queryMap.put("numWG", "");
    queryMap.put("pageSize", "20");
    queryMap.put("pageNow", page);
    queryMap.put("numSortMethod", "0");
    queryMap.put("strLicenseCode", "");
    queryMap.put("selected", "FM");
    queryMap.put("numType", "18");
    return queryMap;
  }

  @Override
  public void webCrawer() throws Exception {
    Map<String, String> map = getQueryParamMap();
    Elements rows = new Elements();
    Boolean flag = false;
    while (!flag) {
      String List = HttpClientUtil.doPost(url, map, getHttpHost());
      Document doc = Jsoup.parse(List);
      rows = doc.select("td[class=td_td]").select("a[href]");
      if (rows == null || rows.size() < 1) {
        flag = false;
        Thread.sleep(10000);
      } else {
        flag = true;
      }
    }

    Jedis jedis = new Jedis("192.168.56.102", 6379);
    List<String> noList = new ArrayList<String>();
    for (Element key : rows) {
      noList.add(key.text());
      jedis.incr("PATENTID");
      jedis.sadd("PATENTID", key.text());
    }

  }

}
