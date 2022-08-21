package com.antmendoza.temporal.infrastructure.adapter;

import com.antmendoza.api.PatientDTO;
import com.antmendoza.temporal.domain.TreatmentRepository;
import com.antmendoza.temporal.service.CreateTreatmentRequest;
import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.client.WorkflowStub;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TemporalTreatmentRepository implements TreatmentRepository {

    private final WorkflowClient workflowClient;
    @Value("task_queue")
    private String task_queue;

    public TemporalTreatmentRepository(WorkflowClient workflowClient) {
        this.workflowClient = workflowClient;
    }

    @Override
    public String save(CreateTreatmentRequest createTreatmentRequest) {


        final String treatmentId = createTreatmentRequest.treatmentId();
        if (treatmentId.equals("Treatment1")) {


            final WorkflowOptions workflowOptions =
                    WorkflowOptions.newBuilder()
                            .setWorkflowId(createTreatmentRequest.buildWorkflowId())
                            .setTaskQueue(task_queue)
                            .build();

            final WorkflowStub workflow =
                    workflowClient.newUntypedWorkflowStub(treatmentId, workflowOptions);


            final WorkflowExecution execution = workflow.start(new PatientDTO(createTreatmentRequest.patientId()));


            return execution.getRunId();

        }

        throw new RuntimeException("Workflow case not implemented " + treatmentId);

    }
}
