package com.packa.japp.service.mapper;

import com.packa.japp.domain.*;
import com.packa.japp.service.dto.HistoriaClinicaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity HistoriaClinica and its DTO HistoriaClinicaDTO.
 */
@Mapper(componentModel = "spring", uses = {PacienteMapper.class, SintomaMapper.class, MedicoMapper.class, InstitucionMapper.class})
public interface HistoriaClinicaMapper extends EntityMapper<HistoriaClinicaDTO, HistoriaClinica> {

    @Mapping(source = "paciente.id", target = "pacienteId")
    @Mapping(source = "sintoma.id", target = "sintomaId")
    @Mapping(source = "medico.id", target = "medicoId")
    @Mapping(source = "institucion.id", target = "institucionId")
    HistoriaClinicaDTO toDto(HistoriaClinica historiaClinica); 

    @Mapping(source = "pacienteId", target = "paciente")
    @Mapping(source = "sintomaId", target = "sintoma")
    @Mapping(source = "medicoId", target = "medico")
    @Mapping(source = "institucionId", target = "institucion")
    HistoriaClinica toEntity(HistoriaClinicaDTO historiaClinicaDTO);

    default HistoriaClinica fromId(Long id) {
        if (id == null) {
            return null;
        }
        HistoriaClinica historiaClinica = new HistoriaClinica();
        historiaClinica.setId(id);
        return historiaClinica;
    }
}
