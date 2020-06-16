package com.example.vebprojekat.service;

import com.example.vebprojekat.entity.Admin;

import java.util.List;

public interface AdminService {
    Admin create(Admin admin) throws Exception;

    Admin findOne(Long id);

    Admin update(Admin admin) throws Exception;

    void delete(Long id);

    List<Admin> findAll();
}
