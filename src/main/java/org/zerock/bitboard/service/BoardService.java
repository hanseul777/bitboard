package org.zerock.bitboard.service;

import lombok.extern.log4j.Log4j2;
import org.zerock.bitboard.dao.BoardDAO;
import org.zerock.bitboard.dto.BoardDTO;
import org.zerock.bitboard.dto.PageDTO;

import java.util.List;

@Log4j2
public enum BoardService {
    INSTANCE;

    public List<BoardDTO> getList(PageDTO pageDTO) throws RuntimeException {

        //BoardDTO의 정보를 사이즈크기만큼으로 나눠서(페이징) 다시 리스트에 담아준다.
        return BoardDAO.INSTANCE.list(pageDTO);

    }
}
