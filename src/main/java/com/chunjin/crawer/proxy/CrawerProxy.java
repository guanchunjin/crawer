package com.chunjin.crawer.proxy;

import org.apache.http.HttpHost;

import java.io.IOException;
import java.util.List;

public interface CrawerProxy {


  List<HttpHost> geProxyList() throws IOException;

}
