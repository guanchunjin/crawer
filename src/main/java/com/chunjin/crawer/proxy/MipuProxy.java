package com.chunjin.crawer.proxy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class MipuProxy implements CrawerProxy {

  private String proxy_url = "https://proxyapi.mimvp.com/api/fetchopen.php?orderid=868112210839024119&num=300&http_type=1&request_method=3";

  public String getProxy_url() {
    return proxy_url;
  }

  public void setProxy_url(String proxy_url) {
    this.proxy_url = proxy_url;
  }

  public List<HttpHost> geProxyList() throws IOException {
    List<HttpHost> arrproxy = new ArrayList<HttpHost>();
    CloseableHttpClient client = HttpClients.createDefault();
    HttpGet request = new HttpGet(proxy_url);
    HttpResponse response = client.execute(request);
    if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
      String result = EntityUtils.toString(response.getEntity());
      String[] proxy_list;
      proxy_list = result.split("\n");
      for (String sproxy : proxy_list) {
        if (sproxy.contains(":")) {
          String ip = sproxy.split(":")[0].trim();
          String port = sproxy.split(":")[1].trim();
          HttpHost proxy = new HttpHost(ip, Integer.valueOf(port), "HTTP");
          arrproxy.add(proxy);
        }
      }
    }
    client.close();
    return arrproxy;
  }

  public static void main(String[] args) throws Exception {
  }

}
