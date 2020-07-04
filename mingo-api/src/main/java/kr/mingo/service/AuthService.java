/**
 * 
 */
package kr.mingo.service;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import kr.mingo.dto.AuthDto.SigninDto;
import kr.mingo.dto.AuthDto.SignupDto;
import kr.mingo.entity.Account;
import kr.mingo.exception.LogicErrorList;
import kr.mingo.exception.LogicException;
import kr.mingo.repository.AccountRepo;
import kr.mingo.util.TokenUtil;

/**
 * @Project : mingo-api
 * @FileName : authService.java
 * @Date : 2018. 11. 6.
 * @작성자 : 조성훈
 * @설명 :
 **/

@Service
@Transactional
public class AuthService {

  @Autowired AccountRepo accountRepo;
  @Autowired AuthenticationManager authenticationManager;


  public SignupDto signup(SignupDto signupDto) {

    // 중복회원 체크
    if (accountRepo.findByEmail(signupDto.getEmail()) != null)
      throw new LogicException(LogicErrorList.DuplicateEntity_Account);

    Account account = new Account();
    BeanUtils.copyProperties(signupDto, account);

    accountRepo.save(account);

    signupDto.setType(null);
    signupDto.setEmail(null);
    signupDto.setPassword(null);
    signupDto.setIsRegist(true);
    return signupDto;
  }

  public SigninDto signin(SigninDto signinDto) {


    // 인증
    UsernamePasswordAuthenticationToken token =
        new UsernamePasswordAuthenticationToken(signinDto.getEmail(), signinDto.getPassword());
    Authentication authentication = authenticationManager.authenticate(token);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    // 토큰생성
    Account account = accountRepo.findByEmail(signinDto.getEmail());
    String xAuthToken = TokenUtil.createToken(account.getEmail(), account.getPassword());

    BeanUtils.copyProperties(account, signinDto);
    signinDto.setEmail(account.getEmail());
    signinDto.setIdfAccount(account.getIdfAccount());
    signinDto.setXAuthToken(xAuthToken);
    signinDto.setPassword(null);

    return signinDto;
  }


}
