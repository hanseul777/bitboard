package dao;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.zerock.bitboard.dao.BoardDAO;
import org.zerock.bitboard.dto.AttachDTO;
import org.zerock.bitboard.dto.BoardDTO;
import org.zerock.bitboard.dto.PageDTO;

@Log4j2
public class BoardDAOTests {

    @Test
    public void testInsertWithAttach() {
        BoardDTO boardDTO = BoardDTO.builder()
                .title("Test")
                .content("Contnet")
                .writer("Writer")
                .build();
        Integer bno = BoardDAO.INSTANCE.insert(boardDTO);

        //가짜데이터를 3개정도 만들어서 넣어줌
        for(int i = 0; i <3; i++){
            AttachDTO attachDTO = AttachDTO.builder()
                    .bno(bno) // 원래 bno는 insert가 실행할 때 생긴다 -> 첨부파일이 들어가고 insert가 실행되어야 생성.
                    .fname("file"+i+".jpg")
                    .savename(System.currentTimeMillis()+"_file"+i+".jpg")
                    .imgyn(true)
                    .build();
            //원래는 boardDTO.addAttach(attachDTO)로 들어갔었다.
        }// 이거만으로 DB에 insert를 할 수 있다. -> 이대로 save를 할 수 있음!

        //---------기존 Controller(BoardDTO를 제대로 구성해 줄 수 았는 역할)가 처리할 수 있는 범위는 여기까지---------------------

        log.info("=============================================");
        log.info(boardDTO);

        //
    }

    @Test
    public void testInsert() {
        BoardDTO boardDTO = BoardDTO.builder()
                .title("Test")
                .content("Contnet")
                .writer("Writer")
                .build();
        BoardDAO.INSTANCE.insert(boardDTO);

        log.info("=============================================");
        log.info(boardDTO); //bno가 들어가있는 것을 볼 수 있는데 그게 새로 만들어진 bno
    }
    @Test
    public void testSelect() {
        log.info(BoardDAO.INSTANCE.select(3));
    }

    @Test
    public void testList() {
        PageDTO pageDTO = PageDTO.builder().page(3).build();
        //PageDTO pageDTO = PageDTO.builder().build();로 적으면 기본값인 0-10개의 게시글이 출력 / page(3) : 3페이지에 해당하는 게시글을 보여줘라
        //페이지를 람다식으로 돌려서
        BoardDAO.INSTANCE.list(pageDTO).forEach(boardDTO -> log.info(boardDTO));
    }
    @Test
    public void testDelete() {
        BoardDAO.INSTANCE.delete(1);
    }

    @Test
    public void testUpdate() {
        BoardDTO boardDTO =
                BoardDTO.builder().bno(3).title("Update").content("update").build();
        BoardDAO.INSTANCE.update(boardDTO);
    }
}
