package kr.mingo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.mingo.entity.Account;

public interface AccountRepo extends JpaRepository<Account, Integer> {

  Account findByEmail(String email);

}
