package cn.liweiqin.testselectphoto.ui.weight;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.liweiqin.testselectphoto.BasePhotoActivity;
import cn.liweiqin.testselectphoto.core.CoreConfig;
import cn.liweiqin.testselectphoto.core.FunctionConfig;
import cn.liweiqin.testselectphoto.core.PhotoFinal;
import cn.liweiqin.testselectphoto.R;
import cn.liweiqin.testselectphoto.model.PhotoInfo;
import cn.liweiqin.testselectphoto.ui.adpater.PhotoShowListAdpater;

public class MainActivity extends BasePhotoActivity implements AdapterView.OnItemClickListener {

    public final static int REQUEST_CODE_CAMERA = 1000;//打开照相机的标识符
    public final static int REQUEST_CODE_MUTI = 1001;//打开相册的标识符
    /**
     * 存放选择的照片
     */
    private ArrayList<String> sekectList = new ArrayList<String>();

    private GridView selectView;
    private PhotoShowListAdpater listAdpater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selectView = (GridView) findViewById(R.id.gv_selected);
        listAdpater = new PhotoShowListAdpater(MainActivity.this, sekectList, mScreenWidth);
        selectView.setAdapter(listAdpater);
        selectView.setOnItemClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 加载配置的信息
     */
    private FunctionConfig initConfig() {
        final FunctionConfig.Builder functionBuilder = new FunctionConfig.Builder();
        final FunctionConfig functionConfig = functionBuilder.setMaxSize(PhotoFinal.PICTURE_MAX_SIZE)//设置最大选择数
                .setSelected(sekectList)//设置选泽的照片集
                .build();
        final CoreConfig.Builder corConfigBuilder = new CoreConfig.Builder(MainActivity.this);
        final CoreConfig coreConfig = corConfigBuilder.setDebug(true)//设置调试
                .setFunctionConfig(functionConfig)//设置参数配置
                .setTakePhotoFolder(null)//设置拍照的存放地址
                .build();
        PhotoFinal.init(coreConfig);
        return functionConfig;
    }

    /**
     * 回调
     */
    private PhotoFinal.OnHanlderResultCallback mOnHanlderResultCallback = new PhotoFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            if (reqeustCode == PhotoSelectActivity.HANDLER_REFRESH_LIST_EVENT) {
                //是选择图片回来的照片
                sekectList.clear();
                for (PhotoInfo info : resultList) {
                    sekectList.add(info.getPhotoPath());
                }
                listAdpater.notifyDataSetChanged();
                // Toast.makeText(getApplicationContext(), "size:" + resultList.size(), Toast.LENGTH_LONG).show();
            } else if (reqeustCode == PhotoSelectActivity.HANLDER_TAKE_PHOTO_EVENT) {
                //是拍照带回来的照片
                sekectList.add(resultList.get(0).getPhotoPath());
                listAdpater.notifyDataSetChanged();
            }

        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            Toast.makeText(MainActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PhotoShowListAdpater.PhotoViewHolder vh = (PhotoShowListAdpater.PhotoViewHolder) view.getTag();
        if (position == sekectList.size() && vh.iv_thumb.getVisibility() != View.GONE) {
            final FunctionConfig functionConfig = initConfig();
            PhotoFinal.openMuti(REQUEST_CODE_MUTI, functionConfig, mOnHanlderResultCallback);
        }
    }
}
