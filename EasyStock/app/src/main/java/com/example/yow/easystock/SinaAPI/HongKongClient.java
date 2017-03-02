package com.example.yow.easystock.SinaAPI;

import android.widget.Toast;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 12205 on 2016/8/19.
 */
public class HongKongClient {

    private final static String STOCK_URL = "http://hq.sinajs.cn/list=";

    private final static int CONNECTION_TIMEOUT = 5000;
    private final static int SO_TIMEOUT = 30000;

    private HttpClient mHttpClient;

    private static HongKongClient mInstance;

    private HongKongClient(){

        mHttpClient = new HttpClient();

        mHttpClient.getHttpConnectionManager().getParams().setConnectionTimeout(CONNECTION_TIMEOUT);
        mHttpClient.getHttpConnectionManager().getParams().setSoTimeout(SO_TIMEOUT);
    }

    public synchronized static HongKongClient getInstance() {

        if(mInstance != null) {
            return mInstance;
        }
        return new HongKongClient();
    }

    public List<HongKongInfo> getStockInfo(String[] stockCodes) throws HttpException, IOException, ParseStockInfoException{

        String url = STOCK_URL + generateStockCodeRequest(stockCodes);

        HttpMethod method = new GetMethod(url);
        int statusCode = mHttpClient.executeMethod(method);
        if(statusCode != HttpStatus.SC_OK) {
            method.releaseConnection();
            Toast.makeText(null, "ceshi", Toast.LENGTH_LONG).show();
            return null;
        }

        InputStream is = method.getResponseBodyAsStream();
        InputStreamReader reader = new InputStreamReader(new BufferedInputStream(is), Charset.forName("gbk"));
        BufferedReader bReader = new BufferedReader(reader);

        List<HongKongInfo> list = parseSinaStockInfosFromReader(bReader);
        bReader.close();
        method.releaseConnection();

        return list;
    }

    private String generateStockCodeRequest(String[] stockCodes){

        if(stockCodes == null || stockCodes.length == 0) {
            return null;
        }

        StringBuilder sb = new StringBuilder(stockCodes[0]);
        final int length = stockCodes.length;

        for(int i = 1; i != length; ++i) {
            sb.append(',');
            sb.append(stockCodes[i]);
        }

        return sb.toString();
    }

    private List<HongKongInfo> parseSinaStockInfosFromReader(BufferedReader reader) throws IOException, ParseStockInfoException {

        ArrayList<HongKongInfo> list = new ArrayList<HongKongInfo>(10);
        String sourceLine = null;

        while((sourceLine = reader.readLine()) != null) {
            list.add(HongKongInfo.parseStockInfo(sourceLine));
            //	break;
        }

        return list;
    }

}
