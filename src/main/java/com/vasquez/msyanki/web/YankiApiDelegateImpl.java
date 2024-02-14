package com.vasquez.msyanki.web;


import com.vasquez.msyanki.api.YankiApiDelegate;
import com.vasquez.msyanki.business.YankiService;
import com.vasquez.msyanki.model.YankiModel;
import com.vasquez.msyanki.web.mapper.YankiMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Yanki api delegate.
 *
 * @author Vasquez
 * @version 1.0.
 */
@Service
public class YankiApiDelegateImpl implements YankiApiDelegate {

  private final YankiService yankiService;

  public YankiApiDelegateImpl(YankiService yankiService) {
    this.yankiService = yankiService;
  }

  @Override
  public Mono<ResponseEntity<YankiModel>> addYanki(Mono<YankiModel> yankiRequest, ServerWebExchange exchange) {
    return yankiRequest
      .map(YankiMapper::toEntity)
      .flatMap(yankiService::save)
      .map(YankiMapper::toResponse)
      .map(ResponseEntity::ok);
  }

  @Override
  public Mono<ResponseEntity<Void>> deleteYankiById(String yankiId, ServerWebExchange exchange) {
    return yankiService.deleteById(yankiId)
      .map(ResponseEntity::ok);
  }

  @Override
  public Mono<ResponseEntity<YankiModel>> getYankiById(String yankiId, ServerWebExchange exchange) {
    return yankiService.findById(yankiId)
      .map(YankiMapper::toResponse)
      .map(ResponseEntity::ok);
  }

  @Override
  public Mono<ResponseEntity<Flux<YankiModel>>> getAllYankis(ServerWebExchange exchange) {
    return Mono.just(yankiService.findAll()
        .map(YankiMapper::toResponse))
      .map(ResponseEntity::ok);
  }

  @Override
  public Mono<ResponseEntity<YankiModel>> updateYanki(String yankiId, Mono<YankiModel> yankiRequest, ServerWebExchange exchange) {
    return yankiRequest
      .map(YankiMapper::toEntity)
      .flatMap(acc -> yankiService.update(acc, yankiId))
      .map(YankiMapper::toResponse)
      .map(ResponseEntity::ok);
  }

  @Override
  public Mono<ResponseEntity<YankiModel>> getYankiByPhoneNumber(String phoneNumber, ServerWebExchange exchange) {
    return yankiService.findByPhoneNumber(phoneNumber)
      .map(YankiMapper::toResponse)
      .map(ResponseEntity::ok);
  }
}
