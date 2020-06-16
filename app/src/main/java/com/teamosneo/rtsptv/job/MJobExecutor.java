package com.teamosneo.rtsptv.job;

import android.os.AsyncTask;

public class MJobExecutor extends AsyncTask<Void, Void, String> {
    @Override
    protected String doInBackground(Void... voids) {
        return "Background task is running";
    }
}
