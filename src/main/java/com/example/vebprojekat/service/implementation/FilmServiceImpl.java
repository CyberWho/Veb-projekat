package com.example.vebprojekat.service.implementation;

import com.example.vebprojekat.entity.Film;
import com.example.vebprojekat.repository.FilmRepository;
import com.example.vebprojekat.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {
    @Autowired
    private FilmRepository filmRepository;

    @Override
    public Film create(Film film) throws Exception{
        if(film.getId() != null) throw new Exception("ID mora biti null!");

        Film novi = this.filmRepository.save(film);
        return novi;
    }

    @Override
    public Film findOne(Long id){
        Film novi = this.filmRepository.getOne(id);
        return novi;
    }

    @Override
    public Film update(Film film) throws Exception{
        Film zaAzurirati = this.filmRepository.getOne(film.getId());
        if(zaAzurirati == null) throw new Exception("Traženi film ne postoji!");

        zaAzurirati.setNaziv(film.getNaziv());
        zaAzurirati.setOpis(film.getOpis());
        zaAzurirati.setProsecnaocena(film.getProsecnaocena());
        //zaAzurirati.setTerminske_liste(film.getTerminske_liste());
        zaAzurirati.setTrajanje(film.getTrajanje());
        zaAzurirati.setZanr(film.getZanr());

        Film novi = this.filmRepository.save(zaAzurirati);
        return novi;
    }

    @Override
    public void delete(Long id){
        this.filmRepository.deleteById(id);
    }

    @Override
    public List<Film> findAll(){
        List<Film> filmovi = this.filmRepository.findAll();
        return filmovi;
    }

}
