package com.chunjin.crawer.task;

import com.chunjin.crawer.Log;
import org.apache.http.HttpHost;

public abstract class BaseTask implements ITask, Runnable {

  protected String url;


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void webCrawer() throws Exception {
  }

  public void run() {
    try {
      webCrawer();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public HttpHost getHttpHost() {
    return new HttpHost("locolhost", 80);
  }

}
