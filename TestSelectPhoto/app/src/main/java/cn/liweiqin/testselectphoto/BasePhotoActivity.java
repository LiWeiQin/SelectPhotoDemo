package cn.liweiqin.testselectphoto;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import java.util.List;

import cn.liweiqin.testselectphoto.utils.DeviceUtils;
import cn.liweiqin.testselectphoto.utils.EasyPermissions;
import cn.liweiqin.testselectphoto.utils.MediaScanner;

/**
 * Created by liweiqin on 2016/1/31.
 */
public abstract class BasePhotoActivity extends Activity implements EasyPermissions.PermissionCallbacks {

    protected int mScreenWidth = 720;
    protected int mScreenHeight = 1280;

    private MediaScanner mMediaScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMediaScanner = new MediaScanner(this);
        DisplayMetrics dm = DeviceUtils.getScreenPix(this);
        mScreenWidth = dm.widthPixels;
        mScreenHeight = dm.heightPixels;
    }

    @Override
    public void onPermissionsDenied(List<String> list) {

    }

    @Override
    public void onPermissionsGranted(List<String> list) {

    }
}
