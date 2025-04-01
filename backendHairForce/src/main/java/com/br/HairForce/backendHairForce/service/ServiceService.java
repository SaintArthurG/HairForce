package com.br.HairForce.backendHairForce.service;

import com.br.HairForce.backendHairForce.entity.Service;
import com.br.HairForce.backendHairForce.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class ServiceService {
    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public Service createService(Service service){
        return serviceRepository.save(service);
    }
}
