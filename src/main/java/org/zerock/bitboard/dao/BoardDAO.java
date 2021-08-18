package org.zerock.bitboard.dao;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.zerock.bitboard.dto.BoardDTO;
import org.zerock.bitboard.dto.PageDTO;

import java.util.List;

@Log4j2
public enum BoardDAO {
    INSTANCE;

    //계속사용해야 해서 상수로 고정시켜놓음. BoardMapper의 namespace사용
    private static final String PREFIX ="org.zerock.bitboard.dao.BoardMapper";

    public void insert(BoardDTO boardDTO) throws RuntimeException {
        //SqlSession이 connection의 기능을해준다.
        try (SqlSession session = MyBatisLoader.INSTANCE.getFactory().openSession(true)){
            session.insert(PREFIX+".insert",boardDTO);
        }catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
    public BoardDTO select(Integer bno) throws RuntimeException {
        BoardDTO dto = null;
        try (SqlSession session = MyBatisLoader.INSTANCE.getFactory().openSession(true)){
            dto = session.selectOne(PREFIX+".select",bno); // 한 개의 항목만 가져오면 selectOne
        }catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return dto;
    }

    public List<BoardDTO> list(PageDTO pageDTO) throws RuntimeException {
        List<BoardDTO> list = null;
        try (SqlSession session = MyBatisLoader.INSTANCE.getFactory().openSession(true)){
            list = session.selectList(PREFIX+".list",pageDTO);
        }catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return list;
    }

    public void delete(Integer bno) throws RuntimeException{
        try (SqlSession session = MyBatisLoader.INSTANCE.getFactory().openSession(true)){
            session.selectList(PREFIX+".delete",bno);
        }catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
    public void update(BoardDTO dto)throws RuntimeException{
        try (SqlSession session = MyBatisLoader.INSTANCE.getFactory().openSession(true)){
            session.selectList(PREFIX+".update",dto);
        }catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

}