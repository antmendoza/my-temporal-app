package com.antmendoza.temporal.infrastructure.adapter;

import com.antmendoza.temporal.application.service.CreateTreatment;
import com.antmendoza.temporal.application.service.CreateTreatmentRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TreatmentRestController {

    private final CreateTreatment createTreatment;


    public TreatmentRestController(CreateTreatment createTreatment) {
        this.createTreatment = createTreatment;
    }

    @PostMapping("/patients/{patientId}/workflows")
    public String startTreatment(
            @PathVariable(name = "patientId") String patientId,
            @RequestBody StartTreatmentRequest startTreatmentRequest) {

        return this.createTreatment.execute(new CreateTreatmentRequest(patientId, startTreatmentRequest.treatmentId()));
    }

}
