package com.packa.japp.web.rest;


import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.resource.Observation;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.dstu2.valueset.NameUseEnum;
import com.packa.japp.naivechain.Block;
import org.junit.Ignore;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
public class RestClientTest {

/*
    @Ignore
    public void getBlockChain() throws Exception {
        final String uri = "http://192.168.0.45:3002/blocks";

        RestTemplate restTemplate = new RestTemplate();
        //String result = restTemplate.getForObject(uri, String.class);
        Block[] result = restTemplate.getForObject(uri, Block[].class);

        for (Block item : result) {
            System.out.println(item);
        }



        // HttpHeaders
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        // Request to return JSON format
        headers.setContentType(MediaType.APPLICATION_JSON);
        //headers.set("my_other_key", "my_other_value");

        // HttpEntity<String>: To get result as String.
        HttpEntity<Block> entity = new HttpEntity<Block>(headers);

        // RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Send request with GET method, and Headers.
        ResponseEntity<Block[]> response = restTemplate.exchange(uri,HttpMethod.GET, entity, Block[].class);

        Block[] result = response.getBody();



    }*/

    @Test
    public void encodingTest() throws Exception {

        FhirContext ctx = FhirContext.forDstu2();

        Patient patient = new Patient();
        patient.addIdentifier().setSystem("http://example.com/fictitious-mrns").setValue("MRN001");
        patient.addName().setUse(NameUseEnum.OFFICIAL).addFamily("Tester").addGiven("John").addGiven("Q");

        Observation localObservation = new Observation();
        localObservation.setComments("nueva observacion");

        String encoded = ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(localObservation);
        System.out.println(encoded);
    }
}
