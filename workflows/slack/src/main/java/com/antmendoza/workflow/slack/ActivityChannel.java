package com.antmendoza.workflow.slack;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface ActivityChannel {


    @ActivityMethod
    void pullMessages(SlackChannel slackChannel, Period period);
}
