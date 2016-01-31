package cn.liweiqin.testselectphoto.utils;

import android.os.Environment;

/**
 * Created by liweiqin on 2016/1/31.
 */
public class DeviceUtils {
    public static boolean existSDCard() {
        return Environment.getExternalStorageState().equals("mounted");
    }
}
