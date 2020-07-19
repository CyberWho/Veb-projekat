package com.example.vebprojekat.service;

import com.example.vebprojekat.entity.Korisnik;

import java.util.List;

public interface KorisnikService {
    Korisnik create(Korisnik korisnik);

    Korisnik findOne(Long id);

    Korisnik findByUsername(String username);

    Korisnik update(Korisnik korisnik) throws Exception;

    void delete(Long id);

    List<Korisnik> findAll();
}
