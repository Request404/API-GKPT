package com.bdap.common.utils.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdNumberValidConstraintValidation implements ConstraintValidator<IdNumberValid, String> {

  @Override
  public boolean isValid(String IdNumber, ConstraintValidatorContext constraintValidatorContext) {
    if (IdNumber == null || "".equals(IdNumber)) {
      return false;
    }
    String regularExpression = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
        "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
    boolean matches = IdNumber.matches(regularExpression);
    if (matches) {

      if (IdNumber.length() == 18) {
        try {
          char[] charArray = IdNumber.toCharArray();
          //前十七位加权因子
          int[] idCardWi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
          //这是除以11后，可能产生的11位余数对应的验证码
          String[] idCardY = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
          int sum = 0;
          for (int i = 0; i < idCardWi.length; i++) {
            int current = Integer.parseInt(String.valueOf(charArray[i]));
            int count = current * idCardWi[i];
            sum += count;
          }
          char idCardLast = charArray[17];
          int idCardMod = sum % 11;
          return idCardY[idCardMod].toUpperCase().equals(String.valueOf(idCardLast).toUpperCase());
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }
    }
    return matches;
  }
}
