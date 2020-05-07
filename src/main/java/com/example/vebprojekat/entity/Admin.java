package com.example.vebprojekat.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
@DiscriminatorValue("Admin")
public class Admin extends Korisnik {
    @OneToOne
    private Bioskop bioskop;

    public Admin(Long id, String korisnickoIme, String lozinka, String ime, String prezime, String kontaktTel, String email, Date datum, Uloga uloga, Boolean aktivan, Bioskop bioskop) {
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
