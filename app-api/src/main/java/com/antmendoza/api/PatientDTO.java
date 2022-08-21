package com.antmendoza.api;

import java.io.Serializable;

public class PatientDTO implements Serializable {

    private String patientId;

    public PatientDTO(){
    }


    public PatientDTO(String patientId){

        this.patientId = patientId;
    }




    public String getPatientId() {
        return patientId;
    }
}
