package org.zerock.bitboard.controller;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Log4j2
@WebServlet(name = "download", value = "/down")
public class DownloadController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //다운로드도 입출력프로그램과 똑같다.
        log.info("Download.......doGet......");

        String path = "/Users/hanseul/upload";
        String fileName = request.getParameter("fname");

        //이 경로에 이 파일네임을 줄 수 있어요~
        File targetFile = new File(path, fileName);
        OutputStream out = null;
        try{
            //경로 알았고 파일이름있으면 빨대꼽아서 데이터 in, out이 가능하다.
            //데이터를 보내주려면? 브라우저와 연결되어있는 OutputStream이 필요하다. -> response에 있음
            out = response.getOutputStream();
            Path targetPath = targetFile.toPath();
            //mime type을 지정
            String  contentType = Files.probeContentType(targetPath);
            log.info(contentType);

            response.setContentType(contentType);
            //예외처리 생각안하고 성공하는 케이스부터 시작
            Files.copy(targetPath,out);//빨대사용-> target한 파일을 path에 out(보내라)하세요.
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{out.close();} catch (Exception e){}
        }




    }
}
