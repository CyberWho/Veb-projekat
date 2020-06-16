package com.example.vebprojekat.service.implementation;

import com.example.vebprojekat.entity.Bioskop;
import com.example.vebprojekat.repository.BioskopRepository;
import com.example.vebprojekat.service.BioskopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BioskopServiceImpl implements BioskopService {

    @Autowired
    private BioskopRepository bioskopRepository;

    @Override
    public Bioskop create(Bioskop bioskop) throws Exception{
        if(bioskop.getId() != null) throw new Exception("ID mora biti null!");

        Bioskop novi = this.bioskopRepository.save(bioskop);
        return novi;
    }

    @Override
    public Bioskop findOne(Long id){
        Bioskop novi = this.bioskopRepository.getOne(id);
        return novi;
    }

    @Override
    public Bioskop update(Bioskop bioskop) throws Exception{
        Bioskop zaAzurirati = this.bioskopRepository.getOne(bioskop.getId());
        if(zaAzurirati == null) throw new Exception("Traženi bioskop ne postoji!");

        zaAzurirati.setAdresa(bioskop.getAdresa());
        zaAzurirati.setBr_telefona(bioskop.getBr_telefona());
        zaAzurirati.setEmail(bioskop.getEmail());
        zaAzurirati.setNaziv(bioskop.getNaziv());
        zaAzurirati.setSale(bioskop.getSale());
        zaAzurirati.setMenadzeri(bioskop.getMenadzeri());

        Bioskop novi = this.bioskopRepository.save(zaAzurirati);

        return novi;
    }

    @Override
    public void delete(Long id){
        this.bioskopRepository.deleteById(id);
    }

    @Override
    public List<Bioskop> findAll(){
        List<Bioskop> bioskopi = this.bioskopRepository.findAll();
        return bioskopi;
    }
}
