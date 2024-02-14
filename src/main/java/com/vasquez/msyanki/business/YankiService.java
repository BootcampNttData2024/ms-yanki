package com.vasquez.msyanki.business;

import com.vasquez.msyanki.entity.Yanki;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Yanki service.
 *
 * @author Vasquez
 * @version 1.0.
 */
public interface YankiService {

  Mono<Yanki> save(Yanki request);

  Mono<Yanki> update(Yanki request, String yankiId);

  Mono<Yanki> findById(String yankiId);

  Flux<Yanki> findAll();

  Mono<Void> deleteById(String yankiId);

  Mono<Yanki> findByPhoneNumber(String phoneNumber);

}
