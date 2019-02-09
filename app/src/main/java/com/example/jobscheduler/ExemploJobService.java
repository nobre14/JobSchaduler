package com.example.jobscheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class ExemploJobService extends JobService {
    private static final String TAG = "ExemploJobService";
    private boolean jobCancelado = false;

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "Job iniciado");
        realizarTarefaEmBackground(params);

        return true;
    }

    private void realizarTarefaEmBackground(final JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int x = 0; x < 10; x++) {
                    Log.d(TAG, "run: " + x);
                    if(jobCancelado){
                        return;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Log.d(TAG, "Job terminado");
                jobFinished(params, false);
            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "Job cancelado depois de concluído");
        jobCancelado = true;
        return true;
    }
}
