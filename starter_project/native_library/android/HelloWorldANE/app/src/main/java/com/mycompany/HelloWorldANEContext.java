package com.mycompany;

import android.content.Intent;

import com.adobe.air.AndroidActivityWrapper;
import com.adobe.air.TRActivityResultCallback;
import com.adobe.air.TRStateChangeCallback;
import com.tuarua.frekotlin.FreKotlinContext;
import com.tuarua.frekotlin.FreKotlinMainController;


public class HelloWorldANEContext extends FreKotlinContext implements TRActivityResultCallback, TRStateChangeCallback {
    private AndroidActivityWrapper aaw;
    private FreKotlinMainController controller;

    @SuppressWarnings("WeakerAccess")
    public HelloWorldANEContext(String name, FreKotlinMainController controller, String[] functions) {
        super(name, controller, functions);
        this.controller = controller;
        aaw = AndroidActivityWrapper.GetAndroidActivityWrapper();
        aaw.addActivityResultListener(this);
        aaw.addActivityStateChangeListner(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
    }

    @Override
    public void onActivityStateChanged(AndroidActivityWrapper.ActivityState activityState) {
        super.onActivityStateChanged(activityState);
        switch (activityState) {
            case STARTED:
                this.controller.onStarted();
                break;
            case RESTARTED:
                this.controller.onRestarted();
                break;
            case RESUMED:
                this.controller.onResumed();
                break;
            case PAUSED:
                this.controller.onPaused();
                break;
            case STOPPED:
                this.controller.onStopped();
                break;
            case DESTROYED:
                this.controller.onDestroyed();
                break;
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        if (aaw != null) {
            aaw.removeActivityResultListener(this);
            aaw.removeActivityStateChangeListner(this);
            aaw = null;
        }
    }
}
