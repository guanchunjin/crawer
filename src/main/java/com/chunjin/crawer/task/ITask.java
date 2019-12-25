package com.chunjin.crawer.task;

import org.apache.http.HttpHost;

public interface ITask {


  void webCrawer() throws Exception;

  HttpHost getHttpHost();
}
