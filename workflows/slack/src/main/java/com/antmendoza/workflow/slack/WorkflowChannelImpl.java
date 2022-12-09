package com.antmendoza.workflow.slack;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class WorkflowChannelImpl implements WorkflowChannel {

    final ActivityChannel activity = Workflow.newActivityStub(ActivityChannel.class,
            ActivityOptions.newBuilder()
                    .setStartToCloseTimeout(Duration.ofMinutes(1)).build());

    @Override
    public void execute(SlackChannel slackChannel, Period period) {

        activity.pullMessages(slackChannel, period);

    }
}
