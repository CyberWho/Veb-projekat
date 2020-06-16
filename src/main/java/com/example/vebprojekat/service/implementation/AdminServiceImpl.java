package com.example.vebprojekat.service.implementation;

import com.example.vebprojekat.entity.Admin;
import com.example.vebprojekat.repository.AdminRepository;
import com.example.vebprojekat.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin create(Admin admin) throws Exception{
        if(admin.getId() != null) throw new Exception("ID mora biti null!");

        Admin novi = this.adminRepository.save(admin);
        return novi;
    }

    @Override
    public Admin findOne(Long id){
        Admin novi = this.adminRepository.getOne(id);
        return novi;
    }

    @Override
    public Admin update(Admin admin) throws Exception{
        Admin zaAzurirati = this.adminRepository.getOne(admin.getId());
        if(zaAzurirati == null) throw new Exception("Traženi admin ne postoji!");

        zaAzurirati.setIme(admin.getIme());
        zaAzurirati.setPrezime(admin.getPrezime());
        zaAzurirati.setAktivan(admin.getAktivan());
        zaAzurirati.setDatum(admin.getDatum());
        zaAzurirati.setEmail(admin.getEmail());
        zaAzurirati.setKorisnickoIme(admin.getKorisnickoIme());
        zaAzurirati.setLozinka(admin.getLozinka());
        zaAzurirati.setUloga(admin.getUloga());
        zaAzurirati.setKontaktTel(admin.getKontaktTel());
        zaAzurirati.setBioskop(admin.getBioskop());

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

}
