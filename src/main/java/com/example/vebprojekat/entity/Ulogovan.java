package com.example.vebprojekat.entity;

import java.util.List;

public class Ulogovan {
    static public Boolean ulogovan;
    static public Long id;
    static public Uloga uloga;
    static public String korisnicko_ime;
    static public String lozinka;
    static public List<Korisnik> approvalList;

    public Ulogovan(){
        ulogovan = false;
        approvalList = null;
    }


    public Boolean getUlogovan() {
        return ulogovan;
    }

    public void setUlogovan(Boolean created) {
        this.ulogovan = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        Ulogovan.id = id;
    }

    public Uloga getUloga() {
        return uloga;
    }

    public void setUloga(Uloga uloga) {
        Ulogovan.uloga = uloga;
    }

    public String getKorisnicko_ime() {
        return korisnicko_ime;
    }

    public void setKorisnicko_ime(String korisnicko_ime) {
        Ulogovan.korisnicko_ime = korisnicko_ime;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        Ulogovan.lozinka = lozinka;
    }

    public static List<Korisnik> getApprovalList() {
        return approvalList;
    }

    public static void setApprovalList(List<Korisnik> approvalList) {
        Ulogovan.approvalList = approvalList;
    }
}
