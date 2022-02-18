package com.flt.chainofresponsiblity;


import java.util.ArrayList;
import java.util.List;

public class ChainOfResponsibility {
    public static void main(String[] args) {
        Msg msg = new Msg("武汉华神智能科技有限公司 <script> 官网:igbs.com 每天都在996 T-T ");
        FilterChain chain1 = new FilterChain();
        chain1.add(new HTTMLFilter()).add(new SensitiveFilter());
        FilterChain chain2 = new FilterChain();
        chain2.add(new HttpFilter()).add(new FaceFilter());
        chain1.add(chain2);
        chain1.doFilter(msg);
        System.out.println(msg.getMsg());
    }

}

class Msg {
    private String name;
    private String msg;

    public Msg(String msg) {
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

interface Filter {
    boolean doFilter(Msg msg);
}

class HTTMLFilter implements Filter {
    @Override
    public boolean doFilter(Msg msg) {
        String s = msg.getMsg();
        s = s.replaceAll("<", "[");
        s = s.replaceAll(">", "]");
        msg.setMsg(s);
        return true;
    }
}

class SensitiveFilter implements Filter {
    @Override
    public boolean doFilter(Msg msg) {
        String s = msg.getMsg();
        s = s.replaceAll("996", "955");
        msg.setMsg(s);
        return true;
    }
}

class FaceFilter implements Filter {
    @Override
    public boolean doFilter(Msg msg) {
        String s = msg.getMsg();
        s = s.replaceAll("T-T ", "^_^");
        msg.setMsg(s);
        return true;
    }
}

class HttpFilter implements Filter {
    @Override
    public boolean doFilter(Msg msg) {
        String s = msg.getMsg();
        s = s.replaceAll("igbs.com", "http://www.igbs.com");
        msg.setMsg(s);
        return true;
    }
}

class FilterChain implements Filter {
    private List<Filter> filters = new ArrayList<>();

    @Override
    public boolean doFilter(Msg msg) {
        for (Filter filter : filters) {
            if (!filter.doFilter(msg)) {
                return false;
            }
        }
        return true;
    }

    public FilterChain add(Filter filter) {
        this.filters.add(filter);
        return this;
    }
}

