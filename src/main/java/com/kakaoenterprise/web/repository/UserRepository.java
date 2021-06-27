package com.kakaoenterprise.web.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kakaoenterprise.domain.user.User;
import com.kakaoenterprise.web.dto.UserDto;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUsername(String username);
	@Query(value="UPDATE User u SET u.nickname = :nickname WHERE u.id = :id", nativeQuery=false)
	@Modifying
	Integer update(String nickname,Long id);
	
	@Modifying
	@Query("delete from User b where b.snsid=:snsid")
	void deleteBySnsid(@Param("snsid") Long snsid);
	
	
	
}
