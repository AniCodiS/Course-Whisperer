package com.freeuni.coursewhisperer.controller;

import com.freeuni.coursewhisperer.data.entity.common.AbstractIdEntity;
import com.freeuni.coursewhisperer.data.mapper.AbstractMapper;
import com.freeuni.coursewhisperer.service.AbstractService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.List;

@Slf4j
@MappedSuperclass
@PreAuthorize("isAuthenticated()")
public abstract class AbstractController<E extends AbstractIdEntity, ID extends Serializable, M, D> {

    final AbstractService<E, ID, M, D> service;
    final AbstractMapper<E, M, D> mapper;

    public AbstractController(AbstractService<E, ID, M, D> service, AbstractMapper<E, M, D> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Hidden
    @PostMapping("/create")
    public ResponseEntity<D> save(@RequestBody D dto) {
        M res = service.save(mapper.dtoToModel(dto));
        return ResponseEntity.ok().body(mapper.modelToDto(res));
    }

    @Hidden
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable ID id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @Hidden
    @GetMapping("/all")
    @ResponseBody
    public List<D> all() {
        return mapper.modelToDto(service.all());
    }

    @Hidden
    @GetMapping("/page")
    @ResponseBody
    public Page<D> list(@PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return service.list(pageable).map(mapper::modelToDto);
    }

    @Hidden
    @GetMapping("/count")
    @ResponseBody
    public long countQuery() {
        return service.countQuery();
    }

}