package com.vasquez.msyanki.business.impl;

import com.vasquez.msyanki.business.YankiService;
import com.vasquez.msyanki.entity.Yanki;
import com.vasquez.msyanki.repository.YankiRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Yanki service implementation.
 *
 * @author Vasquez
 * @version 1.0.
 */
@Log4j2
@Service
public class YankiServiceImpl implements YankiService {

  private final YankiRepository yankiRepository;

  /**
   * Yanki service constructor.
   *
   * @param yankiRepository account
   */
  public YankiServiceImpl(YankiRepository yankiRepository) {
    this.yankiRepository = yankiRepository;
  }

  @Override
  public Mono<Yanki> save(Yanki request) {
    request.setBalance(0.0);
    return yankiRepository.save(request);
  }

  @Override
  public Mono<Yanki> update(Yanki request, String id) {
    log.info("yanki request, {}", request);
    return this.findById(id).flatMap(yanki -> {
      yanki.setBalance(request.getBalance());
      return yankiRepository.save(yanki);
    });
  }

  @Override
  public Mono<Yanki> findById(String id) {
    return yankiRepository.findById(id);
  }

  @Override
  public Flux<Yanki> findAll() {
    return yankiRepository.findAll();
  }

  @Override
  public Mono<Void> deleteById(String id) {
    return yankiRepository.deleteById(id);
  }

  @Override
  public Mono<Yanki> findByPhoneNumber(String phoneNumber) {
    return yankiRepository.findByPhoneNumber(phoneNumber);
  }

}

