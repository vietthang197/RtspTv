package com.teamosneo.rtsptv.job;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.widget.Toast;

public class ControlCameraJobScheduler extends JobService {

    private MJobExecutor mJobExecutor;

    @Override
    public boolean onStartJob(final JobParameters params) {
        mJobExecutor = new MJobExecutor() {
            @Override
            protected void onPostExecute(String s) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                jobFinished(params, false);
            }
        };

        mJobExecutor.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        mJobExecutor.cancel(true);
        return true;
    }
}
