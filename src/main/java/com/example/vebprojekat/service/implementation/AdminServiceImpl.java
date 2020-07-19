package com.example.vebprojekat.service.implementation;

import com.example.vebprojekat.entity.Admin;
import com.example.vebprojekat.entity.Korisnik;
import com.example.vebprojekat.entity.UlogaEnum;
import com.example.vebprojekat.repository.AdminRepository;
import com.example.vebprojekat.service.AdminService;
import com.example.vebprojekat.service.KorisnikService;
import com.example.vebprojekat.service.MenadzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private MenadzerService menadzerService;

    @Override
    public Admin create(Korisnik korisnik) throws Exception{
        if(korisnik == null) throw new Exception ("Niste uneli korisnika!");

        Admin novi = new Admin();
        novi.setKorisnicko_ime(korisnik.getKorisnicko_ime());
        novi.setLozinka(korisnik.getLozinka());
        novi.setIme(korisnik.getIme());
        novi.setPrezime(korisnik.getPrezime());
        novi.setKontakt_tel(korisnik.getKontakt_tel());
        novi.setEmail(korisnik.getEmail());
        novi.setDatum(korisnik.getDatum());
        novi.setUloga(UlogaEnum.Uloga.ADMIN);
        novi.setAktivan(korisnik.getAktivan());

        return adminRepository.save(novi);
    }

    @Override
    public Admin findOne(Long id){
        Admin novi = this.adminRepository.getOne(id);
        return novi;
    }

    @Override
    public Admin update(Admin admin) throws Exception{
        Admin zaAzurirati = this.adminRepository.getOne(admin.getId());
        delete(admin.getId());

        if(zaAzurirati == null) throw new Exception("Traženi admin ne postoji!");

        zaAzurirati.setIme(admin.getIme());
        zaAzurirati.setPrezime(admin.getPrezime());
        zaAzurirati.setAktivan(admin.getAktivan());
        zaAzurirati.setDatum(admin.getDatum());
        zaAzurirati.setEmail(admin.getEmail());
        zaAzurirati.setKorisnicko_ime(admin.getKorisnicko_ime());
        zaAzurirati.setLozinka(admin.getLozinka());
        zaAzurirati.setUloga(admin.getUloga());
        zaAzurirati.setKontakt_tel(admin.getKontakt_tel());
        zaAzurirati.setBioskop(admin.getBioskop());
        zaAzurirati.setApprovalList(admin.getApprovalList());
        zaAzurirati.setApproved(admin.getApproved());

        Admin novi = this.adminRepository.save(zaAzurirati);

        return novi;
    }

    @Override
    public void delete(Long id){
        this.adminRepository.deleteById(id);
    }

    @Override
    public List<Admin> findAll(){
        List<Admin> admini = this.adminRepository.findAll();
        return admini;
    }

    @Override
    public Admin findByUsername(String username){
        List<Admin> admini = this.adminRepository.findAll();
        for(Admin admin : admini){
            if(username.equals(admin.getKorisnicko_ime())){
                return admin;
            }
        }
        return null;
    }

    @Override
    public void addToApprovalList(Korisnik korisnik) throws Exception {
        Admin superAdmin = findByUsername("SuperAdmin");
        superAdmin.getApprovalList().add(korisnik);
        korisnik.setAdmin_approve(superAdmin);

        korisnikService.update(korisnik);
        update(superAdmin);
    }

    @Override
    public Boolean approveRegistration(Korisnik korisnik) throws Exception {
        Admin superAdmin = findByUsername("SuperAdmin");

        if(korisnik == null) return false;

        korisnik.setApproved(true);
        korisnik.setAdmin_approve(null);
        superAdmin.getApprovalList().remove(korisnik);
        if (korisnik.getUloga() == UlogaEnum.Uloga.MENADZER) {
            menadzerService.create(korisnik);
        } else if(korisnik.getUloga() == UlogaEnum.Uloga.ADMIN){
            create(korisnik);
        }
        update(superAdmin);

        /*List<Korisnik> korisnici = superAdmin.getApprovalList();

        for(Korisnik kor : korisnici){
            if(korisnik.getId() == kor.getId()){
                korisnici.remove(kor);
                kor.setApproved(true);
                kor.setAdmin_approve(null);
                superAdmin.setApprovalList(korisnici);
                korisnikService.update(kor);
                update(superAdmin);
                return true;
            }
        }*/
        return true;
    }

}
