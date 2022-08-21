package com.antmendoza.workflow1;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface Treatment1Activity {
    @ActivityMethod
    String contactPatient();
}
