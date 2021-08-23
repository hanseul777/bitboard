package org.zerock.bitboard.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
@WebFilter(filterName = "signin", urlPatterns = {"/board/register"}) // register와 read페이지를 들어가려고 하면 필터가 적용
public class SigninFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("Signin Filter-------------run------------");

        //ServletRequest와 ServletResponse는 HttpServletRequest보다 더 넓은 범위여서 다운캐스팅해줘야함.
        //HttpServletRequest가 브라우저가 보낸 정보를 모두 담아두고 -> session()이 정보를 처리함
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        //브라우저가 가지고 있는 세션값(req.getSession())와 서버늬 세션저장소에 들어있는 세션값(데이터값)을 비교 -> 맞으면 로그인을 한 상태
        HttpSession session = req.getSession();

        //not yet login
        if(session.getAttribute("member") == null) {
            res.sendRedirect("/login");
            return;
        }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
