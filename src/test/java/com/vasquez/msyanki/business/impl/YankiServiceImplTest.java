package com.vasquez.msyanki.business.impl;

import com.vasquez.msyanki.business.YankiService;
import com.vasquez.msyanki.entity.Yanki;
import com.vasquez.msyanki.repository.YankiRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class YankiServiceImplTest {

  @Autowired
  private YankiService yankiService;
  @MockBean
  private YankiRepository yankiRepository;

  private final String YANKI_ID = "1";
  private final String DOCUMENT_TYPE = "DNI";
  private final String DOCUMENT_NUMBER = "12345678";
  private final String PHONE_NUMBER = "123456789";
  private final String IMEI_NUMBER = "123456789012";
  private final String EMAIL = "alcibar@gmail.com";
  private final String ASSOCIATED_ACCOUNT = "1";
  private Yanki yanki;

  @BeforeEach
  void setUp() {
    yanki = new Yanki();
    yanki.setYankiId(YANKI_ID);
    yanki.setDocumentType(DOCUMENT_TYPE);
    yanki.setDocumentNumber(DOCUMENT_NUMBER);
    yanki.setPhoneNumber(PHONE_NUMBER);
    yanki.setImeiNumber(IMEI_NUMBER);
    yanki.setEmail(EMAIL);
    yanki.setAssociatedDebitCard(ASSOCIATED_ACCOUNT);
  }

  @Test
  @DisplayName("Test save method")
  void save() {
    //Arrange - Initialize
    when(yankiRepository.save(yanki)).thenReturn(Mono.just(yanki));

    //Act - Call method
    Mono<Yanki> yankiMono = yankiService.save(yanki);

    //Assert - Assert or confirm response
    StepVerifier.create(yankiMono)
      .consumeNextWith(yank -> {
        assertEquals(yank.getEmail(), EMAIL);
      })
      .verifyComplete();
  }

  @Test
  @DisplayName("Test update method")
  void update() {
    when(yankiRepository.save(yanki)).thenReturn(Mono.just(yanki));
    when(yankiRepository.findById(YANKI_ID)).thenReturn(Mono.just(yanki));

    Mono<Yanki> yankiMono = yankiService.update(yanki, YANKI_ID);

    StepVerifier.create(yankiMono)
      .consumeNextWith(yank -> {
        assertEquals(yank.getEmail(), EMAIL);
      })
      .verifyComplete();
  }

  @Test
  @DisplayName("Test findById method")
  void findById() {
    when(yankiRepository.findById(YANKI_ID)).thenReturn(Mono.just(yanki));

    Mono<Yanki> yankiMono = yankiService.findById(YANKI_ID);

    StepVerifier.create(yankiMono)
      .consumeNextWith(yank -> {
        assertEquals(yank.getEmail(), EMAIL);
      })
      .verifyComplete();
  }

  @Test
  @DisplayName("Test findAll method")
  void findAll() {
    when(yankiRepository.findAll()).thenReturn(Flux.just(yanki));

    Flux<Yanki> yankiMono = yankiService.findAll();

    StepVerifier.create(yankiMono)
      .consumeNextWith(yank -> {
        assertEquals(yank.getEmail(), EMAIL);
      })
      .verifyComplete();
  }

  @Test
  @DisplayName("Test deleteById method")
  void deleteById() {
    when(yankiRepository.deleteById(YANKI_ID)).thenReturn(Mono.empty());

    Mono<Void> yankiMono = yankiService.deleteById(YANKI_ID);

    StepVerifier.create(yankiMono)
      .verifyComplete();
  }

  @Test
  @DisplayName("Test findByPhoneNumber method")
  void findByPhoneNumber() {
    when(yankiRepository.findByPhoneNumber(YANKI_ID)).thenReturn(Mono.just(yanki));

    Mono<Yanki> yankiMono = yankiService.findByPhoneNumber(YANKI_ID);

    StepVerifier.create(yankiMono)
      .consumeNextWith(yank -> {
        assertEquals(yank.getEmail(), EMAIL);
      })
      .verifyComplete();
  }
}