package org.zerock.bitboard.dao;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;

@Log4j2
public enum TimeDAO {
    INSTANCE;

    public String getTime() throws RuntimeException{
        String result = null;

        //자동으로 close를 해주는 것
        try (SqlSession session = MyBatisLoader.INSTANCE.getFactory().openSession()) {
            result = session.selectOne("org.zerock.bitboard.dao.TimeMapper.getTime"); //spacename + id = 풀네임
            log.info("=====================");
            log.info(result);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
