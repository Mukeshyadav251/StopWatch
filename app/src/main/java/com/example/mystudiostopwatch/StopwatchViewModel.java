package com.example.mystudiostopwatch;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.os.Handler;


public class StopwatchViewModel extends ViewModel {
    private final MutableLiveData<Long>timeLiveData=new MutableLiveData<>(0L);
    private final Handler handler= new Handler();
        private boolean isRunning = false;
         private long Seconds=0;
            private final Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    if(isRunning){
                      Seconds++;
                      timeLiveData.setValue(Seconds);
                      handler.postDelayed(this,1000);
                    }
                }
            };

            public LiveData<Long> getTime(){
                return timeLiveData;
            }
            public void startstopwatch(){
                if(!isRunning){
                    isRunning=true;
                    handler.post(runnable);
                }
            }

            public void stopstopwatch(){

                    isRunning=false;
                    handler.removeCallbacks(runnable);
                }


public void resetstopwatch() {
    if (isRunning) {
        isRunning = false;
        handler.removeCallbacks(runnable);
    }
        Seconds = 0;
        timeLiveData.setValue(Seconds);
    }


@Override
      protected void onCleared(){
                super.onCleared();
                handler.removeCallbacks(runnable);
            }

    }


