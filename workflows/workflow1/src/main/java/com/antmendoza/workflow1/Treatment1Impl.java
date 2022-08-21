package com.antmendoza.workflow1;

import com.antmendoza.api.PatientDTO;
import com.antmendoza.api.Treatment1;
import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

import static java.time.Duration.ofSeconds;

public class Treatment1Impl implements Treatment1 {


    private final Treatment1Activity activity = Workflow.newActivityStub(Treatment1Activity.class,
            ActivityOptions.newBuilder()
                    .setStartToCloseTimeout(ofSeconds(2)).build());
    private boolean patientContacted;


    @Override
    public String start(PatientDTO patient) {
        String execute = activity.contactPatient();
        
        Workflow.await(()-> this.patientContacted);
        
        return "Hello World!";
    }



    @Override
    public void patientContacted(){
        this.patientContacted = true;
    }
}
