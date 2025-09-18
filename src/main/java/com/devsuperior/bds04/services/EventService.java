package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.CityRepository;
import com.devsuperior.bds04.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    @Autowired
    private CityRepository cityRepository;

    public Page<EventDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(x -> new EventDTO(x));
    }

    public EventDTO insert(EventDTO dto) {
        City city = cityRepository.getReferenceById(dto.getCityId());
        Event entity = new Event();
        entity.setName(dto.getName());
        entity.setCity(city);
        entity.setDate(dto.getDate());
        entity.setUrl(dto.getUrl());
        entity = repository.save(entity);
        return new EventDTO(entity);
    }

}
