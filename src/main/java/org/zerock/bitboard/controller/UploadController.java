package org.zerock.bitboard.controller;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.Collection;

@Log4j2
@WebServlet(name = "upload", value = "/upload")
@MultipartConfig(fileSizeThreshold = 1024*1024)//첨부파일을 만들기 위해 사용하는 것. 파일사이즈에 제한을 줄 수도 있다.
public class UploadController extends HttpServlet { // 첨부파일 업로드는 무조건 post방식으로 사용한다.


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/upload.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uploadfolder = "/Users/hanseul/upload"; // upload할 폴더를 선택
        byte[] buffer = new byte[1024*8]; //파일복사를 위해 선언
        Collection<Part>parts = req.getParts();

        parts.forEach(part -> {
            log.info(part);

            String type = part.getContentType();
            // type이 null : 우리가 사용하던 일반적인 파일형식이라는 말
            // 일반파일과 이미지파일의 업로드가 다르다.
            if(type == null) {
                log.info("partName : " + part.getName()); // 일반파일을 처리할 때만 사용한다.
                return; // null이 아니면 밑으로 내려간다.
            }

            String fileName = part.getSubmittedFileName();

//            System.currentTimeMillis() 같은파일 중복으로 올리기 위해 사용 -> System.currentTimeMillis()+"_"+fileName로 파일이름을 지정
            String uploadFileName = System.currentTimeMillis()+"_"+fileName;

            log.info(fileName);

            //원본파일 저장
            try (InputStream in = part.getInputStream();
                 OutputStream fos = new FileOutputStream(uploadfolder+ File.separator+uploadFileName); //원본파일의 오리지널네임
                 //File.separator 파일경로
            ) {
                while (true){
                    int count = in.read(buffer);
                    if(count == -1){break;}
                    fos.write(buffer,0,count);
                }
            }catch(Exception e){

            }//원본파일 저장 끝

            //썸네일은 이미지파일에서만 만들어져야한다. -> 이미지로 시작하는 애들만 썸네일생성
            if(type.startsWith("image")){

                try{
                    Thumbnails.of(new File(uploadfolder+ File.separator+uploadFileName))//오리지널 파일
                            .size(100,100)//100픽셀짜리로 만들어 줌
                           .toFile(new File(uploadfolder+ File.separator+"s_"+uploadFileName));
                }catch (Exception e){}

            }



            log.info("-------------------------------");// 파일하나씩 구분선
        });//for each
    }
}