package com.page.common.utils.resultUtil;


import com.page.common.enums.ResultEnum;

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
