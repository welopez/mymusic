/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ar.edu.unnoba.pdyc.mymusic.service;

import ar.edu.unnoba.pdyc.mymusic.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author Lenovo
 */
public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    void create(User user) throws Exception;
}
