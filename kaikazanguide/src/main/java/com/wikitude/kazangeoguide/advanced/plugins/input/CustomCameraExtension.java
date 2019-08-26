package com.wikitude.kazangeoguide.advanced.plugins.input;

import com.wikitude.architect.ArchitectView;
import com.wikitude.common.plugins.PluginManager;
import com.wikitude.kazangeoguide.advanced.ArchitectViewExtension;
import com.wikitude.kazangeoguide.R;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

/**
 * This Activity is the java counterpart of the 13_PluginsAPI_4_CustomCamera AR-Experience.
 * It registers a native Plugin which uses camera frames from a custom camera implementation
 * and will render the camera frame and the augmentations in the Plugin Code.
 *
 * Please Note that the custom camera implementations are very minimal and a more advanced Camera implementation
 * should be used in apps.
 */
public class CustomCameraExtension extends ArchitectViewExtension {

    private static final String TAG = "CustomCamera";

    private FrameInputPluginModule inputModule;

    public CustomCameraExtension(Activity activity, ArchitectView architectView) {
        super(activity, architectView);
    }

    @Override
    public void onPostCreate() {
        /*
         * Registers the plugin with the name "customcamera".
         * The library containing the native plugin is libwikitudePlugins.so.
         */
        architectView.registerNativePlugins("wikitudePlugins", "customcamera", new PluginManager.PluginErrorCallback() {
            @Override
            public void onRegisterError(int errorCode, String errorMessage) {
                Toast.makeText(activity, R.string.error_loading_plugins, Toast.LENGTH_SHORT).show();
/*
                Log.e(TAG, "Plugin failed to load. Reason: " + errorMessage);
*/
            }
        });

        initNative();
        inputModule = new FrameInputPluginModule(activity, getInputModuleHandle());
    }

    /**
     * Called from c++ onCameraReleased of the CameraFrameInputPluginModule.
     */
    public void onSDKCameraReleased() {
        inputModule.start();
    }

    /**
     * Called from c++ on pause of the Plugin.
     */
    public void onInputPluginPaused() {
        inputModule.stop();
    }

    /**
     * Called from c++ on resume of the Plugin.
     */
    public void onInputPluginResumed() {
        inputModule.start();
    }

    /**
     * Called from c++ on destroy of the Plugin.
     */
    public void onInputPluginDestroyed() {

    }

    private native void initNative();
    private native long getInputModuleHandle();
}
