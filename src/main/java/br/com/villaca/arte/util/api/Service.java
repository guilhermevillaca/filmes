package br.com.villaca.arte.util.api;

import org.springframework.data.domain.Page;

import java.util.List;

public interface Service<PK, RESPONSE, REQUEST> {
    RESPONSE findById(PK id);
    List<RESPONSE> findAll();
    Page<RESPONSE> findAllPaginated(int page, int size);
    RESPONSE create(REQUEST request);
    RESPONSE update(PK id, REQUEST request);
    void delete(PK id);
}
