package org.zerock.bitboard.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Log4j2
@WebFilter(filterName = "logFilter" ,urlPatterns = {"/*"}) //전체에 적용 -> 뭐를 호출하던간에 필터작동하게 만든다.
public class LogFilter implements Filter { //컨트롤러에게 가는 중간에 필터가 걸리는 것

    //필터의 시작부분 : 한 번만 실행
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("----------------------------------");
        log.info("----------------------------------");
        log.info("init filter");
        log.info("----------------------------------");
        log.info("----------------------------------");
    }

    @Override
    //매번 실제로 동작하는 필터부분
    //chain : filter에 걸렸는데 다음으로 보낼건지 말건지
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("doFilter-----------------------");
        log.info("doFilter-----------------------");
        log.info("doFilter-----------------------");
        log.info("doFilter-----------------------");

        //한글체크필터 : 예전에는 doPost마다 적용했던 것을 필터에 한 번만 적어놓으면 모든 post방식에 적용이 되는 것
        request.setCharacterEncoding("UTF-8");

        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
