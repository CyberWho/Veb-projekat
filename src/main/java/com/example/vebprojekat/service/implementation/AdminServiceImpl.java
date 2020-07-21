package com.example.vebprojekat.service.implementation;

import com.example.vebprojekat.entity.Admin;
import com.example.vebprojekat.entity.Korisnik;
import com.example.vebprojekat.entity.Menadzer;
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
        novi.setApproved(korisnik.getApproved());
        novi.setAdmin_approve(korisnik.getAdmin_approve());

        return adminRepository.save(novi);
    }

    @Override
    public Admin findOne(Long id){
        Admin novi = this.adminRepository.getOne(id);
        return novi;
    }

    @Override
    public Admin update(Admin admin) throws Exception{
        Admin zaAzurirati = adminRepository.getOne(admin.getId());
        //delete(admin.getId());

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

        return adminRepository.save(zaAzurirati);
    }

    @Override
    public void delete(Long id){
        adminRepository.deleteById(id);
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
        if(superAdmin==null) throw new Exception("SuperAdmin je null!");
        superAdmin.getApprovalList().add(korisnik);
        korisnik.setAdmin_approve(superAdmin);

        korisnikService.update(korisnik);
        update(superAdmin);
    }

    @Override
    public Long approveRegistration(Korisnik korisnik) throws Exception {
        Admin superAdmin = findByUsername("SuperAdmin");

        if(korisnik == null) return null;

        korisnik.setApproved(true);
        korisnik.setAdmin_approve(null);
        superAdmin.getApprovalList().remove(korisnik);
        Menadzer novi = new Menadzer();

        novi.setKorisnicko_ime(korisnik.getKorisnicko_ime());
        novi.setLozinka(korisnik.getLozinka());
        novi.setIme(korisnik.getIme());
        novi.setPrezime(korisnik.getPrezime());
        novi.setKontakt_tel(korisnik.getKontakt_tel());
        novi.setEmail(korisnik.getEmail());
        novi.setDatum(korisnik.getDatum());
        novi.setUloga(UlogaEnum.Uloga.MENADZER);
        novi.setAktivan(korisnik.getAktivan());
        novi.setApproved(korisnik.getApproved());
        novi.setAdmin_approve(korisnik.getAdmin_approve());

        if (korisnik.getUloga() == UlogaEnum.Uloga.MENADZER) {
            menadzerService.save(novi);
        } else if(korisnik.getUloga() == UlogaEnum.Uloga.ADMIN){
            //create(korisnik);
        }
        update(superAdmin);

        return novi.getId();
    }

    @Override
    public void deleteIfMoreThanOne(Admin korisnik){
        Integer count=-1;
        for(Admin kor : adminRepository.findAll()){
            if(kor.getKorisnicko_ime().equals(korisnik.getKorisnicko_ime())) count++;
        }

        for(int i = 0; i < count; i++){
            korisnik.setAdmin_approve(null);
            korisnik.setApproved(true);
            delete(findByUsername(korisnik.getKorisnicko_ime()).getId());
        }

    }

}
