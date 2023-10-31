package com.stackroute.authenticationservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.stackroute.authenticationservice.Datamodel.UserModel;

public interface UserRepository extends JpaRepository<UserModel , String> {
//	UserDao save(String email);

}
