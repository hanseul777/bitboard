package dao;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.zerock.bitboard.dao.BoardDAO;
import org.zerock.bitboard.dto.BoardDTO;
import org.zerock.bitboard.dto.PageDTO;

@Log4j2
public class BoardDAOTests {
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
