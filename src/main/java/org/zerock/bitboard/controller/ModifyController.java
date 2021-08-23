package org.zerock.bitboard.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.bitboard.dto.BoardDTO;
import org.zerock.bitboard.dto.PageDTO;
import org.zerock.bitboard.service.BoardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebServlet(value = "/board/modify")
public class ModifyController extends HttpServlet {

    private Integer getInt(String str){
        try {
            int value = Integer.parseInt(str);
            if (value <= 0) {
                return null;
            }
            return value;
        }catch (Exception e){
            return null;
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //파라미터 수집
        Integer bno = getInt(request.getParameter("bno"));
        Integer page = getInt(request.getParameter("page"));
        Integer size = getInt(request.getParameter("size"));

        PageDTO pageDTO = PageDTO.builder().build();

        if(page!=null) {pageDTO.setPage(page);}
        if(size!=null) {pageDTO.setSize(size);}//null이면 디폴트값으로만 결과가 나옴

        //게시물가져오기
        BoardDTO boardDTO = BoardService.INSTANCE.read(bno);

        //택배상자에 담아서 보내주기
        request.setAttribute("boardDTO",boardDTO);
        request.setAttribute("pageDTO", pageDTO);

        request.getRequestDispatcher("/WEB-INF/board/modify.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer bno = getInt(request.getParameter("bno"));
        String modifyTitle = request.getParameter("title");
        String modifyContent = request.getParameter("content");

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(bno)
                .title(modifyTitle)
                .content(modifyContent)
                .build();

        BoardService.INSTANCE.modify(boardDTO);

        request.setAttribute("title", boardDTO.getTitle());
        request.setAttribute("content", boardDTO.getContent());

        response.sendRedirect("/board/list");

    }
}
