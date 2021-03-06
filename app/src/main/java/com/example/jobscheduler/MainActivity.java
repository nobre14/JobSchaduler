package com.example.jobscheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void iniciaJob(View view){
        ComponentName componentName = new ComponentName(this, ExemploJobService.class);
        JobInfo info = new JobInfo.Builder(123, componentName)
                .setRequiresCharging(true)
               // .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED) requer WIFI
                .setPersisted(true)
                .setPeriodic(15 * 60 * 1000)
                .build();

        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultadoCodigo = scheduler.schedule(info);
        if(resultadoCodigo == JobScheduler.RESULT_SUCCESS){
            Log.d(TAG, "Job Scheduled");
        }else{
            Log.d(TAG, "Job Scheduled FALHOU");
        }
    }

    public void cancelaJob(View view){
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(123);
        Log.d(TAG, "Job cancelado");
    }
}
