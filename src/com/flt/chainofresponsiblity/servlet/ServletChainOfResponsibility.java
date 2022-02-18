package com.flt.chainofresponsiblity.servlet;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ServletChainOfResponsibility {
    public static void main(String[] args) {
        Request request = new Request();
        Response response = new Response();
        FilterChain chain = new FilterChain();
        chain.add(new Filter1()).add(new Filter2());
        chain.doFilter(request, response);
    }
}

interface Filter {
    void doFilter(Request request, Response response, FilterChain chain);
}

class FilterChain {
    private List<Filter> filters = new ArrayList<>();
    private int index = -1;

    public FilterChain add(Filter filter) {
        filters.add(filter);
        if (index == -1) {
            index = 0;
        }
        return this;
    }


    public void doFilter(Request request, Response response) {
        if (index == -1 || !(index < filters.size())) return;
        Filter filter = filters.get(index);
        index++;
        filter.doFilter(request, response, this);

    }
}

class Request {
    public String str = "Original";
}

class Response {
    public String str = "Original";
}

class Filter1 implements Filter {
    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        request.str = request.str + "E1";
        System.out.println("request:" + request.str);
        chain.doFilter(request, response);
        response.str = response.str + "O2";
        System.out.println("response:" + response.str);

    }
}

class Filter2 implements Filter {
    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        request.str = request.str + "E2";
        System.out.println("request:" + request.str);
        chain.doFilter(request, response);
        response.str = response.str + "O1";
        System.out.println("response:" + response.str);
    }
}
