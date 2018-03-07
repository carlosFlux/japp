package com.packa.japp.service.impl;

import com.packa.japp.service.HistoriaClinicaService;
import com.packa.japp.domain.HistoriaClinica;
import com.packa.japp.repository.HistoriaClinicaRepository;
import com.packa.japp.service.dto.HistoriaClinicaDTO;
import com.packa.japp.service.mapper.HistoriaClinicaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing HistoriaClinica.
 */
@Service
@Transactional
public class HistoriaClinicaServiceImpl implements HistoriaClinicaService{

    private final Logger log = LoggerFactory.getLogger(HistoriaClinicaServiceImpl.class);

    private final HistoriaClinicaRepository historiaClinicaRepository;

    private final HistoriaClinicaMapper historiaClinicaMapper;

    public HistoriaClinicaServiceImpl(HistoriaClinicaRepository historiaClinicaRepository, HistoriaClinicaMapper historiaClinicaMapper) {
        this.historiaClinicaRepository = historiaClinicaRepository;
        this.historiaClinicaMapper = historiaClinicaMapper;
    }

    /**
     * Save a historiaClinica.
     *
     * @param historiaClinicaDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public HistoriaClinicaDTO save(HistoriaClinicaDTO historiaClinicaDTO) {
        log.debug("Request to save HistoriaClinica : {}", historiaClinicaDTO);
        HistoriaClinica historiaClinica = historiaClinicaMapper.toEntity(historiaClinicaDTO);
        historiaClinica = historiaClinicaRepository.save(historiaClinica);
        return historiaClinicaMapper.toDto(historiaClinica);
    }

    /**
     * Get all the historiaClinicas.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<HistoriaClinicaDTO> findAll() {
        log.debug("Request to get all HistoriaClinicas");
        return historiaClinicaRepository.findAll().stream()
            .map(historiaClinicaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one historiaClinica by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public HistoriaClinicaDTO findOne(Long id) {
        log.debug("Request to get HistoriaClinica : {}", id);
        HistoriaClinica historiaClinica = historiaClinicaRepository.findOne(id);
        return historiaClinicaMapper.toDto(historiaClinica);
    }

    /**
     * Delete the historiaClinica by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete HistoriaClinica : {}", id);
        historiaClinicaRepository.delete(id);
    }
}
