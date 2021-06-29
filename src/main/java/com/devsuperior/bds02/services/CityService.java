package com.devsuperior.bds02.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.services.excepitions.DatabaseException;
import com.devsuperior.bds02.services.excepitions.ResourceNotFoundException;

@Service
public class CityService {
	
	@Autowired
	private CityRepository repository;
	
	@Transactional
	public void  delete(Long id) {
		try {
		repository.deleteById(id);
		} catch (EmptyResultDataAccessException e ) {
			throw new ResourceNotFoundException("Id not found "  + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integraty Violation");
		}
	}
	
	@Transactional(readOnly = true)
	public Page<CityDTO> findAllPaged(Pageable pageable){
		Page<City> list = repository.findAll(pageable);	
		return list.map(x -> new CityDTO(x));		
	}

	
}
