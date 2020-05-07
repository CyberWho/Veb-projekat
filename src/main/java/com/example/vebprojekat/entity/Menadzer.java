package com.example.vebprojekat.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("Menadzer")
public class Menadzer extends Korisnik {
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Bioskop bioskop;

    public Menadzer(Long id, String korisnickoIme, String lozinka, String ime, String prezime, String kontaktTel, String email, Date datum, Uloga uloga, Boolean aktivan, Bioskop bioskop) {
        super(id, korisnickoIme, lozinka, ime, prezime, kontaktTel, email, datum, uloga, aktivan);
        this.bioskop = bioskop;
    }

    public Bioskop getBioskop() {
        return bioskop;
    }

    public void setBioskop(Bioskop bioskop) {
        this.bioskop = bioskop;
    }
}
