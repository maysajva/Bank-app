package com.mj.javaapp.Bank_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mj.javaapp.Bank_app.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
