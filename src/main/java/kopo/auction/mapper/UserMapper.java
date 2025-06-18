package kopo.auction.mapper;

import kopo.auction.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> findAll();

//    @Select("SELECT username FROM users WHERE id = #{id}")
    String findUsernameById(@Param("id") Long id);
}
