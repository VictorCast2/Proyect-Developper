package com.App.Proyect_Developper.Repository;

import com.App.Proyect_Developper.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>, CrudRepository<UserModel, Long> {
    UserModel findByUsername(String username);
}