package com.bench.oAuth.services;

import com.bench.oAuth.models.ClientDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserDetailsService {
    private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private ClientFeignCli client;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ClientDTO clientDTO = client.findByEmail(email);
        if(clientDTO ==null){
            log.error("MS Authentification: User NO Found, check data");
            throw new UsernameNotFoundException("Error to login, User no exits "+email+" in the system");
        }

        List<GrantedAuthority> authorities = clientDTO.getRoles()
                .stream()
                .map(role->new SimpleGrantedAuthority(role.getName()))
                .peek(authority -> log.info(" MS Autentification Role: "+ authority.getAuthority()))
                .collect(Collectors.toList());
        log.info(" MSAutentification: User authentificate: "+email);
        return new User(clientDTO.getEmail(), clientDTO.getPassword(),true, true, true,true, authorities);
    }
}
