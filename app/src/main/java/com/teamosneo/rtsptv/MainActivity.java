package com.teamosneo.rtsptv;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.print.PrinterId;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.teamosneo.rtsptv.job.ControlCameraJobScheduler;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnCancleJob;

    public static final int JOB_ID = 101;
    private JobScheduler jobScheduler;
    private JobInfo jobInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCancleJob = findViewById(R.id.btnCancleJob);
        btnCancleJob.setOnClickListener(this);

        ComponentName componentName = new ComponentName(this, ControlCameraJobScheduler.class);

        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, componentName);
        builder.setPeriodic(1000);
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        builder.setPersisted(true);

        jobInfo = builder.build();
        jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(jobInfo);
        Toast.makeText(this, "Job Started!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCancleJob:
                cancleJob();
                break;
        }
    }

    private void cancleJob() {
        jobScheduler.cancel(JOB_ID);
        Toast.makeText(this, "Job Cancled", Toast.LENGTH_SHORT).show();
    }
}
