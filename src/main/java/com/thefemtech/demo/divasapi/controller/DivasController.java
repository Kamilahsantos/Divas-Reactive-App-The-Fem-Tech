package com.thefemtech.demo.divasapi.controller;

import com.thefemtech.demo.divasapi.model.Divas;
import com.thefemtech.demo.divasapi.repository.DivasRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@Slf4j
public class DivasController {

    @Autowired
    private DivasRepository divasRepository;

    @PostMapping("/diva")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Divas> createDiva(@Valid @RequestBody Divas divas) {
        log.info("Creating a new Diva");
        return divasRepository.save(divas);
    }

    @GetMapping("/diva")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Divas> getAllDivas() {
        log.info("Listing all Divas");
        return divasRepository.findAll();
    }

    @GetMapping("/diva/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ResponseEntity<Divas>> getDivaById(@PathVariable(value = "id") String divaId) {
        log.info("Get diva with id {} ", divaId);
        return divasRepository.findById(divaId)
                .map(savedDiva -> ResponseEntity.ok(savedDiva))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/diva/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Divas>> updateDiva(@PathVariable(value = "id") String divaId,
                                                  @Valid @RequestBody Divas divas) {
        return divasRepository.findById(divaId)
                .flatMap(existingDiva -> {
                    existingDiva.setName(divas.getName());
                    log.info("Update diva with id {} ", divaId);
                    return divasRepository.save(existingDiva);
                })
                .map(updateDiva -> new ResponseEntity<>(updateDiva, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/diva/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteDiva(@PathVariable(value = "id") String divaId) {

        log.info("Delete diva with id {} ", divaId);
        return divasRepository.findById(divaId)
                .flatMap(existingDiva ->
                        divasRepository.delete(existingDiva)
                                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/stream/diva", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Flux<Divas> streamAllDivas() {
        log.info("------------------------Stream of Divas------------");
        return divasRepository.findAll();
    }


}
