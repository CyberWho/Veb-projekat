package com.example.vebprojekat.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue("Admin")
public class Admin extends Korisnik {
    @OneToOne
    private Bioskop bioskop;

    @OneToMany(mappedBy = "admin_approve", cascade=CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Korisnik> approvalList;

    @Column
    private Boolean super_admin;

    public Admin(){}

    public Admin(Long id, String korisnickoIme, String lozinka, String ime, String prezime, String kontaktTel, String email, Date datum, UlogaEnum.Uloga uloga, Boolean aktivan, Bioskop bioskop) {
        super(id, korisnickoIme, lozinka, ime, prezime, kontaktTel, email, datum, uloga, aktivan);
        this.bioskop = bioskop;
        super_admin = false;
    }

    public Bioskop getBioskop() {
        return bioskop;
    }

    public void setBioskop(Bioskop bioskop) {
        this.bioskop = bioskop;
    }

    public List<Korisnik> getApprovalList() {
        return approvalList;
    }

    public void setApprovalList(List<Korisnik> approvalList) {
        this.approvalList = approvalList;
    }

    public Boolean getSuper_admin() {
        return super_admin;
    }

    public void setSuper_admin(Boolean superAdmin) {
        this.super_admin = superAdmin;
    }
}
