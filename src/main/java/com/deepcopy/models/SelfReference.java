package com.deepcopy.models;

public class SelfReference {

    private SelfReference selfReference;

    public SelfReference getSelfReference() {
        return selfReference;
    }

    public void setSelfReference(SelfReference selfReference) {
        this.selfReference = selfReference;
    }

}
