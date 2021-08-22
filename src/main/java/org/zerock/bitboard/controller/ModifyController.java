package org.zerock.bitboard.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.bitboard.dto.BoardDTO;
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/board/modify.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BoardDTO boardDTO = BoardDTO.builder()
                .title(request.getParameter("title"))
                .content(request.getParameter("content"))
                .build();

        BoardService.INSTANCE.update(boardDTO);

        request.setAttribute("title", boardDTO.getTitle());
        request.setAttribute("content", boardDTO.getContent());

        request.getRequestDispatcher("/WEB-INF/board/modify.jsp").forward(request,response);

        response.sendRedirect("/board/list");

    }
}
