package com.antmendoza.workflow.slack;

import java.io.Serializable;

public record WorkflowInput (SlackChannels slackChannels, Period period) {
}
