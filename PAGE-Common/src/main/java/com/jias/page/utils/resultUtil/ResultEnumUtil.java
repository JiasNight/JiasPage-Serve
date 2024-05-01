package com.jias.page.utils.resultUtil;

import com.jias.page.enums.ResultEnum;

public class ResultEnumUtil {
  public static <T extends ResultEnum> String getByCode(Integer code, Class<T> t) {
    for (T item : t.getEnumConstants()) {
      if (item.getCode() == code) {
        return item.getMessage();
      }
    }
    return "";
  }
}
