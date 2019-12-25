package com.chunjin.crawer;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {

    public static String PROXY_USERNAME = "758676742@qq.com";
    public static String PROXY_PASSWORD = "lovegcj2008";

    public static String postAuth(String webUrl, HashMap<String, String> param, HttpHost proxyHost) throws Exception {


        String proxy_ip = proxyHost.getHostName();
        int proxy_port = proxyHost.getPort();
        CredentialsProvider provider = new BasicCredentialsProvider();
        provider.setCredentials(new AuthScope(proxy_ip, proxy_port),
                new UsernamePasswordCredentials(HttpClientUtil.PROXY_USERNAME, HttpClientUtil.PROXY_PASSWORD));
        CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(provider).build();

        URL url = new URL(webUrl);
        HttpHost target = new HttpHost(url.getHost(), url.getDefaultPort(), url.getProtocol());
        HttpHost proxy = new HttpHost(proxy_ip, proxy_port);
        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
        HttpPost request = new HttpPost(url.getPath());
        if (param != null) {
            List<NameValuePair> paramList = new ArrayList<NameValuePair>();
            for (String key : param.keySet()) {
                paramList.add(new BasicNameValuePair(key, param.get(key)));
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
            request.setEntity(entity);
        }
//      HttpGet request = new HttpGet(url.getPath());
        request.setConfig(config);
        request.addHeader("Host", "proxy.mimvp.com");
        request.addHeader("User-Agent",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36");
        HttpResponse response = client.execute(target, request);
//        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
        String result = EntityUtils.toString(response.getEntity());
        System.out.println(result);
        return result;
//        } else {

//            return "44444";
//        }

    }


    public static String doGet(String url, Map<String, String> param) {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();
            HttpGet httpGet = new HttpGet(uri);
            httpGet.setHeader("User-Agent",
                    "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static String doGet(String url, Map<String, String> param, HttpHost proxy) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        try {

            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();
            HttpGet httpGet = new HttpGet(uri);
            httpGet.setConfig(RequestConfig.custom().setProxy(proxy).build());
            httpGet.setHeader("User-Agent",
                    "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
            // e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static String doGet(String url, HttpHost porxy) throws Exception {
        return doGet(url, null, porxy);
    }


    public static String doPost(String url, Map<String, String> param, HttpHost proxy) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(RequestConfig.custom().setProxy(proxy).build());
        if (param != null) {
            List<NameValuePair> paramList = new ArrayList<NameValuePair>();
            for (String key : param.keySet()) {
                paramList.add(new BasicNameValuePair(key, param.get(key)));
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
            httpPost.setEntity(entity);
        }
        httpPost.setHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
        response = httpClient.execute(httpPost);
        resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        response.close();
        return resultString;
    }

    public static String doPost(String url, Map<String, String> param) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            HttpPost httpPost = new HttpPost(url);
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            }
            httpPost.setHeader("User-Agent",
                    "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response.close();

        }

        return resultString;
    }

    public static String doPost(String url) throws Exception {
        return doPost(url, null);
    }

    public static String doPostJson(String url, String json) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

}