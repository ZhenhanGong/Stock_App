package com.example.yow.easystock.SinaAPI;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

/**
 * Created by 12205 on 2016/7/22.
 */
public class SinaStockClient {

    private final static String STOCK_URL = "http://hq.sinajs.cn/list=";

    private final static String STOCK_MINITE_URL = "http://image.sinajs.cn/newchart/min/n/";
    private final static String STOCK_DAILY_URL = "http://image.sinajs.cn/newchart/daily/n/";
    private final static String STOCK_WEEKLY_URL = "http://image.sinajs.cn/newchart/weekly/n/";
    private final static String STOCK_MONTHLY_URL = "http://image.sinajs.cn/newchart/monthly/n/";

    public final static int IMAGE_TYPE_MINITE = 0x85;
    public final static int IMAGE_TYPE_DAILY = 0x86;
    public final static int IMAGE_TYPE_WEEKLY = 0x87;
    public final static int IMAGE_TYPE_MONTHLY = 0x88;

    private final static int CONNECTION_TIMEOUT = 5000;
    private final static int SO_TIMEOUT = 30000;

    private HttpClient mHttpClient;

    private static SinaStockClient mInstance;

    private SinaStockClient(){
        mHttpClient = new HttpClient();

        mHttpClient.getHttpConnectionManager().getParams().setConnectionTimeout(CONNECTION_TIMEOUT);
        mHttpClient.getHttpConnectionManager().getParams().setSoTimeout(SO_TIMEOUT);

    }

    /**
     * 获取客户端实例。
     *
     *
     */
    public synchronized static SinaStockClient getInstance() {

        if(mInstance != null) {
            return mInstance;
        }
        return new SinaStockClient();
    }

    /**
     * 通过股票代码，获取行情信息。
     *
     */
    public List<SinaStockInfo> getStockInfo(String[] stockCodes) throws HttpException, IOException, ParseStockInfoException {
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

        List<SinaStockInfo> list = parseSinaStockInfosFromReader(bReader);
        bReader.close();
        method.releaseConnection();

        return list;
    }

    /**
     * 获取股票分时图或K线图。
     */
    public Bitmap getStockImage(String stockCode, int imageType) throws HttpException, IOException {
        String baseRequestUrl = null;
        switch(imageType) {
            case IMAGE_TYPE_MINITE:
                baseRequestUrl = STOCK_MINITE_URL;
                break;
            case IMAGE_TYPE_DAILY:
                baseRequestUrl = STOCK_DAILY_URL;
                break;
            case IMAGE_TYPE_WEEKLY:
                baseRequestUrl = STOCK_WEEKLY_URL;
                break;
            case IMAGE_TYPE_MONTHLY:
                baseRequestUrl = STOCK_MONTHLY_URL;
                break;
        }

        if(TextUtils.isEmpty(baseRequestUrl)) {
            return null;
        }

        String fullRequestUrl = baseRequestUrl + stockCode + ".gif";

        return getBitmapFromUrl(fullRequestUrl);
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

    private List<SinaStockInfo> parseSinaStockInfosFromReader(BufferedReader reader) throws IOException, ParseStockInfoException {

        ArrayList<SinaStockInfo> list = new ArrayList<SinaStockInfo>(10);
        String sourceLine = null;

        while((sourceLine = reader.readLine()) != null) {
            list.add(SinaStockInfo.parseStockInfo(sourceLine));
            //	break;
        }

        return list;
    }

    private Bitmap getBitmapFromUrl(String url) throws HttpException, IOException {

        HttpMethod method = new GetMethod(url);
        int statusCode = mHttpClient.executeMethod(method);
        if(statusCode != HttpStatus.SC_OK) {
            method.releaseConnection();
            return null;
        }

        InputStream in = method.getResponseBodyAsStream();
        BufferedInputStream bis = new BufferedInputStream(in);

        Bitmap bm = BitmapFactory.decodeStream(bis);

        bis.close();
        method.releaseConnection();

        return bm;
    }

}
