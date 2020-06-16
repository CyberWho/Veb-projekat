package com.example.vebprojekat.service;

import com.example.vebprojekat.entity.Gledalac;

import java.util.List;

public interface GledalacService {
    Gledalac create(Gledalac gledalac) throws Exception;

    Gledalac findOne(Long id);

    Gledalac update(Gledalac gledalac) throws Exception;

    void delete(Long id);

    List<Gledalac> findAll();
}
