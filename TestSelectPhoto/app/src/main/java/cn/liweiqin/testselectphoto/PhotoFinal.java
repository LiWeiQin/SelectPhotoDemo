package cn.liweiqin.testselectphoto;


import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import cn.liweiqin.testselectphoto.model.PhotoInfo;
import cn.liweiqin.testselectphoto.ui.weight.PhotoEditActivity;
import cn.liweiqin.testselectphoto.ui.weight.PhotoSelectActivity;
import cn.liweiqin.testselectphoto.utils.DeviceUtils;

/**
 * Created by liweiqin on 2016/1/31.
 */
public class PhotoFinal {

    public static final int PERMISSIONS_CODE_PHOTO = 2001;

    private static FunctionConfig mDefaultFunctionConfig;
    private static FunctionConfig mCurrentFunctionConfig;
    private static CoreConfig mCoreConfig;
    private static int mRequestCode;
    private static OnHanlderResultCallback mCallback;

    public static void init(CoreConfig coreConfig) {
        mCoreConfig = coreConfig;
        mDefaultFunctionConfig = coreConfig.getFunctionConfig();
        if (mCoreConfig.isDebug()) {
            Log.e(PhotoFinal.class.getSimpleName(), "init");
        }

    }

    public static CoreConfig getCoreConfig() {
        return mCoreConfig;
    }

    public static FunctionConfig getFunctionConfig() {
        return mCurrentFunctionConfig;
    }

    public static int getRequestCode() {
        return mRequestCode;
    }

    public static OnHanlderResultCallback getCallback() {
        return mCallback;
    }

    public static FunctionConfig copyGlobalFuncationConfig() {
        if (mDefaultFunctionConfig != null) {
            return mDefaultFunctionConfig.clone();
        }
        return null;
    }

    public static interface OnHanlderResultCallback {
        /**
         * 处理成功
         *
         * @param reqeustCode
         * @param resultList
         */
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList);

        /**
         * 处理失败或异常
         *
         * @param requestCode
         * @param errorMsg
         */
        public void onHanlderFailure(int requestCode, String errorMsg);
    }

    /**
     * 拍照
     *
     * @param requestCode
     * @param config
     * @param callback
     */
    public static void openCamera(int requestCode, FunctionConfig config, OnHanlderResultCallback callback) {
        if (!DeviceUtils.existSDCard()) {
            Toast.makeText(mCoreConfig.getContext(), "没有SD卡", Toast.LENGTH_SHORT).show();
            return;
        }
        mRequestCode = requestCode;
        mCallback = callback;
        mCurrentFunctionConfig = config;

        Intent intent = new Intent(mCoreConfig.getContext(), PhotoEditActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(PhotoEditActivity.TAKE_PHOTO_ACTION, true);
        mCoreConfig.getContext().startActivity(intent);

    }

    /**
     * 打开相册啦
     *
     * @param requestCode
     * @param config
     * @param callback
     */
    public static void openMuti(int requestCode, FunctionConfig config, OnHanlderResultCallback callback) {
        if (!DeviceUtils.existSDCard()) {
            Toast.makeText(mCoreConfig.getContext(), "没有SD卡", Toast.LENGTH_SHORT).show();
            return;
        }

        if (config == null && mDefaultFunctionConfig == null) {
            if (callback != null) {
                callback.onHanlderFailure(requestCode, "没有设置配置");
            }
            return;
        }
        mRequestCode = requestCode;
        mCallback = callback;
        mCurrentFunctionConfig = config;

        Intent intent = new Intent(mCoreConfig.getContext(), PhotoSelectActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mCoreConfig.getContext().startActivity(intent);

    }


}
