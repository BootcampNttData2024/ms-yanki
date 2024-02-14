package com.vasquez.msyanki.web.mapper;

import com.vasquez.msyanki.entity.Yanki;
import com.vasquez.msyanki.model.YankiModel;
import com.vasquez.msyanki.model.YankiModel;
import org.springframework.beans.BeanUtils;

/**
 * Yanki mapper.
 *
 * @author Vasquez
 * @version 1.0.
 */
public class YankiMapper {

  YankiMapper() {
  }


  /**
   * To entity.
   *
   * @param yankiRequest yankiRequest
   * @return Yanki
   */
  public static Yanki toEntity(YankiModel yankiRequest) {
    Yanki yanki = new Yanki();
    BeanUtils.copyProperties(yankiRequest, yanki);
    return yanki;
  }

  /**
   * To response.
   *
   * @param yanki yanki
   * @return Yanki model
   */
  public static YankiModel toResponse(Yanki yanki) {
    YankiModel yankiResponse = new YankiModel();
    BeanUtils.copyProperties(yanki, yankiResponse);
    return yankiResponse;
  }

}
