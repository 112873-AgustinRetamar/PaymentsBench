package com.bench.zuul.Filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PostElapsedTimeFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(PostElapsedTimeFilter.class);

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info("Going into post filter");
        Long startingTime = (Long) request.getAttribute("startingTime");
        Long finishingTime = System.currentTimeMillis();
        Long elapsedTime = startingTime - finishingTime;
        log.info(String.format("Elapsed time in seconds %s", elapsedTime.doubleValue()/1000.00));
        //log.info(String.format("Elapsed time in milliseconds %ms", elapsedTime));
        return null;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public String filterType() {
        return "post";
    }

}