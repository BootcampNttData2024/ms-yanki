package com.vasquez.msyanki.repository;

import com.vasquez.msyanki.entity.Yanki;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Yanki repository.
 *
 * @author Vasquez
 * @version 1.0.
 */
@Repository
public interface YankiRepository extends ReactiveMongoRepository<Yanki, String> {

  Mono<Yanki> findByPhoneNumber(String phoneNumber);

}
