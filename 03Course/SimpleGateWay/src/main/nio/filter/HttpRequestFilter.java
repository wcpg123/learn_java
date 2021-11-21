package main.nio.filter;

import io.netty.handler.codec.http.FullHttpRequest;

public interface HttpRequestFilter {
    
    boolean filter(FullHttpRequest fullRequest);
    
}
