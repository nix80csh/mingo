/**
 * 
 */
package kr.mingo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.mingo.dto.AuthDto.EncryptionDto;
import kr.mingo.dto.AuthDto.SigninDto;
import kr.mingo.dto.AuthDto.SignupDto;
import kr.mingo.service.AuthService;
import kr.mingo.util.EncryptionSha256Util;


/**
 * @Project : mingo-api
 * @FileName : AuthController.java
 * @Date : 2018. 10. 28.
 * @작성자 : 조성훈
 * @설명 :
 **/

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired AuthService authService;

  @RequestMapping(value = "/pingtester", method = RequestMethod.GET)
  public String pingtester() {
    return "test";
  }

  @RequestMapping(value = "/encryptionSha256", method = RequestMethod.POST)
  public EncryptionDto encryptionSha256(@RequestBody EncryptionDto encryptionDto) {
    String encStr = EncryptionSha256Util.getEncSHA256(encryptionDto.getBeforeEncryt());
    encryptionDto.setAfterEncryt(encStr);
    return encryptionDto;
  }

  @RequestMapping(value = "/signup", method = RequestMethod.POST)
  public SignupDto signup(@RequestBody SignupDto signupDto) {
    return authService.signup(signupDto);
  }

  @RequestMapping(value = "/signin", method = RequestMethod.POST)
  public SigninDto signin(@RequestBody SigninDto signinDto) {
    return authService.signin(signinDto);
  }

}
