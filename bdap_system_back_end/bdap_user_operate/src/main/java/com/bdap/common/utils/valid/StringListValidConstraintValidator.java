package com.bdap.common.utils.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StringListValidConstraintValidator implements ConstraintValidator<StringListValid, String> {

  private final Set<String> set = new HashSet<>();

  @Override
  public void initialize(StringListValid constraintAnnotation) {
    String[] values = constraintAnnotation.values();
    set.addAll(Arrays.asList(values));
  }

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    return set.contains(s);
  }
}
