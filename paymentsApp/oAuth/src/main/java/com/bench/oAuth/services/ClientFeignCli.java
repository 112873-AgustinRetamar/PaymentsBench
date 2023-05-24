package com.bench.oAuth.services;

import com.bench.oAuth.models.ClientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name ="service-clients")
public interface ClientFeignCli {
    @GetMapping("/api/clients/findByEmail/{email}")
    public ClientDTO findByEmail(@PathVariable String email);
}
