package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * job
 * @author 
 */
@Data
public class Job implements Serializable {
    private String jobName;

    private String jobId;

    private static final long serialVersionUID = 1L;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
}