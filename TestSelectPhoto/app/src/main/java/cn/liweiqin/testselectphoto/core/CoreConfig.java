package cn.liweiqin.testselectphoto.core;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by liweiqin on 2016/1/31.
 */
public class CoreConfig {

    private boolean debug;
    private Context context;
    private File takePhotoFolder;
    private int animRes;
    private FunctionConfig functionConfig;

    private CoreConfig(Builder mBuilder) {
        this.debug = mBuilder.debug;
        this.context = mBuilder.context;
        this.takePhotoFolder = mBuilder.takePhotoFolder;
        this.animRes = mBuilder.animRes;
        this.functionConfig = mBuilder.functionConfig;

        if (takePhotoFolder == null) {
            takePhotoFolder = new File(Environment.getExternalStorageDirectory(), "/DCIM/" + "SchoPhoto/");
        }
        if (!takePhotoFolder.exists()) {
            takePhotoFolder.mkdirs();
        }


    }

    public FunctionConfig getFunctionConfig() {
        return functionConfig;
    }

    public int getAnimRes() {
        return animRes;
    }

    public File getTakePhotoFolder() {
        return takePhotoFolder;
    }

    public Context getContext() {
        return context;
    }


    public boolean isDebug() {
        return debug;
    }


    public static class Builder {
        private boolean debug;
        private Context context;
        private File takePhotoFolder;
        private int animRes;
        private FunctionConfig functionConfig;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setFunctionConfig(FunctionConfig functionConfig) {
            this.functionConfig = functionConfig;
            return this;
        }

        public Builder setAnimRes(int animRes) {
            this.animRes = animRes;
            return this;
        }


        public Builder setTakePhotoFolder(File takePhotoFolder) {
            this.takePhotoFolder = takePhotoFolder;
            return this;
        }

        public Builder setDebug(boolean debug) {
            this.debug = debug;
            return this;
        }

        public CoreConfig build() {
            return new CoreConfig(this);
        }
    }

}
