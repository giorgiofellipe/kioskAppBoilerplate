package com.giorgiofellipe.kioskAppBoilerplate;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {
    //Volume buttons
    private final List mBlockedKeys = new ArrayList(Arrays.asList(KeyEvent.KEYCODE_VOLUME_DOWN, KeyEvent.KEYCODE_VOLUME_UP));

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Removes App Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Toggles app to Fullscreen mode removing status bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Prevents screen turn off
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        /**
         * Set the app to cover the lock screen
         * What it does: when pushing the power button will cause screen dimming,
         * but after pushing it again the app will still be visible.
         */
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);

        setContentView(R.layout.main);
    }

    /**
     * Prevent exiting the app when back button is pressed
     */
    @Override
    public void onBackPressed() {
        //Do nothing
    }

    /**
     * Disable the home button
     *
     * this manner only works until the Android 4.0, therefore needs to
     * do in another ways, such setting the app as home screen
     */
    @Override
    public void onAttachedToWindow() {
        getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);
        super.onAttachedToWindow();
    }

    /**
     * Disable the volume buttons
     * Another buttons can be disabled here, such as camera buttons,
     * you only need to add KeyEvent which refers to that button to
     * the mBlockedKeys Array
     *
     * @param event KeyEvent
     * @return
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (mBlockedKeys.contains(event.getKeyCode())) {
            return true;
        } else {
            return super.dispatchKeyEvent(event);
        }
    }
}
