package com.packa.japp.domain;

import ca.uhn.fhir.model.dstu2.resource.Observation;
import ca.uhn.fhir.model.dstu2.valueset.ObservationStatusEnum;
import ca.uhn.fhir.model.primitive.BoundCodeDt;
import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class ObservationMixIn {

    @JsonIgnore
    public abstract Observation setStatus(ObservationStatusEnum theValue);
}
