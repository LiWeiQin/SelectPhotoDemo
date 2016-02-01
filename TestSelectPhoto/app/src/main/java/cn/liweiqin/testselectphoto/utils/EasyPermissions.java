package cn.liweiqin.testselectphoto.utils;

/**
 * Created by liweiqin on 2016/2/1.
 */
public class EasyPermissions {
    private static final java.lang.String TAG = "EasyPermissions";

    public EasyPermissions() { /* compiled code */ }

    public static boolean hasPermissions(android.content.Context context, java.lang.String... perms) { /* compiled code */
        return false;
    }

    public static void requestPermissions(android.app.Activity activity, java.lang.String rationale, int requestCode, java.lang.String... perms) { /* compiled code */ }

    public static void onRequestPermissionsResult(int requestCode, java.lang.String[] permissions, int[] grantResults, android.app.Activity activity) { /* compiled code */ }

    private static void executePermissionsRequest(android.app.Activity activity, java.lang.String[] perms, int requestCode) { /* compiled code */ }

    private static void runAnnotatedMethods(android.app.Activity activity, int requestCode) { /* compiled code */ }

    public static interface PermissionCallbacks extends android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback {
        void onPermissionsGranted(java.util.List<java.lang.String> list);

        void onPermissionsDenied(java.util.List<java.lang.String> list);
    }
}