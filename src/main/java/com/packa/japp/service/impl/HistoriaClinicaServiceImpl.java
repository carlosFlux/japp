package com.packa.japp.service.impl;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.resource.Observation;
import ca.uhn.fhir.parser.IParser;
import com.packa.japp.domain.HistoriaClinica;
import com.packa.japp.naivechain.Block;
import com.packa.japp.repository.HistoriaClinicaRepository;
import com.packa.japp.service.HistoriaClinicaService;
import com.packa.japp.service.dto.HistoriaClinicaDTO;
import com.packa.japp.service.mapper.HistoriaClinicaMapper;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Service Implementation for managing HistoriaClinica.
 */
@Service
@Transactional
public class HistoriaClinicaServiceImpl implements HistoriaClinicaService{

    private final Logger log = LoggerFactory.getLogger(HistoriaClinicaServiceImpl.class);

    private final HistoriaClinicaRepository historiaClinicaRepository;

    private final HistoriaClinicaMapper historiaClinicaMapper;

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String URI_API = "http://192.168.0.45:3002";

    private final FhirContext fhirCtx = FhirContext.forDstu2();

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
        /*HistoriaClinica historiaClinica = historiaClinicaMapper.toEntity(historiaClinicaDTO);
        historiaClinica = historiaClinicaRepository.save(historiaClinica);
        return historiaClinicaMapper.toDto(historiaClinica);*/



        Observation localObservation = new Observation();
        localObservation.setComments("nueva observacion");

        String encoded = fhirCtx.newJsonParser().setPrettyPrint(true).encodeResourceToString(localObservation);


        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        StringBuilder localSb = new StringBuilder();

        localSb.append("{\"data\" : ");
        localSb.append(encoded);
        localSb.append("}");

        HttpEntity<String> requestBody = new HttpEntity<>(localSb.toString(), headers);

        String localRestResponse = restTemplate.postForObject(URI_API + "/mineBlock", requestBody, String.class);

        log.debug("Save response :: ", localRestResponse);

        historiaClinicaDTO.setId(Long.valueOf("44444"));

        return historiaClinicaDTO;

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
/*        return historiaClinicaRepository.findAll().stream()
            .map(historiaClinicaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));

*/
        Block[] result = restTemplate.getForObject(URI_API + "/blocks", Block[].class);

        IParser parser = fhirCtx.newJsonParser();

        List<HistoriaClinicaDTO> historiasClinicas = new ArrayList<>();

        for (Block item : result) {

            Map itemMap = (Map) item.getData();

            Observation localObservation = parser.parseResource(Observation.class, JSONObject.toJSONString(itemMap));

            HistoriaClinicaDTO historiaClinicaDTO = new HistoriaClinicaDTO();

            historiaClinicaDTO.setId(Long.valueOf(item.getIndex()));

            historiasClinicas.add(historiaClinicaDTO);

        }

        return historiasClinicas;
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
