/**
 * 
 */
package kr.mingo.dto;

import lombok.Data;

/**
 * @Project : mingo-api
 * @FileName : SignupDto.java
 * @Date : 2018. 11. 6.
 * @작성자 : 조성훈
 * @설명 :
 **/

public class AuthDto {

  @Data
  public static class SignupDto {
    private String email;
    private String password;
    private String type;
    private Boolean isRegist;
  }

  @Data
  public static class EncryptionDto {
    private String beforeEncryt;
    private String afterEncryt;
  }

  @Data
  public static class SigninDto {
    private Integer idfAccount;
    private String email;
    private String password;
    private String xAuthToken;
  }


}
