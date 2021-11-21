package main.nio.filter;

import io.netty.handler.codec.http.FullHttpRequest;

public class HeaderHttpRequestFilter implements HttpRequestFilter {

    @Override
    public  boolean filter(FullHttpRequest fullRequest) {
       if(fullRequest.headers().contains("kimmking")){
           return true;
       }else{
           return  false;
       }
    }
}
