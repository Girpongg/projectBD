package com.example.projectbd;

public class job {
    private String jobid;
    private String namajob;

    public job(String jobid, String namajob) {
        this.jobid = jobid;
        this.namajob = namajob;
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    public String getNamajob() {
        return namajob;
    }

    public void setNamajob(String namajob) {
        this.namajob = namajob;
    }
}
