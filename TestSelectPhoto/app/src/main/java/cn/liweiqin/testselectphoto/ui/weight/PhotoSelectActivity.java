package cn.liweiqin.testselectphoto.ui.weight;

import android.Manifest;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.liweiqin.testselectphoto.BasePhotoActivity;
import cn.liweiqin.testselectphoto.FunctionConfig;
import cn.liweiqin.testselectphoto.PhotoFinal;
import cn.liweiqin.testselectphoto.R;
import cn.liweiqin.testselectphoto.model.PhotoFolderInfo;
import cn.liweiqin.testselectphoto.model.PhotoInfo;
import cn.liweiqin.testselectphoto.ui.adpater.FolderListAdapter;
import cn.liweiqin.testselectphoto.ui.adpater.PhotoListAdapter;
import cn.liweiqin.testselectphoto.utils.EasyPermissions;
import cn.liweiqin.testselectphoto.utils.PhotoUtil;


/**
 * 这里就是展示图片的界面了
 * <p/>
 * Created by liweiqin on 2016/1/31.
 */
public class PhotoSelectActivity extends BasePhotoActivity implements View.OnClickListener {

    private final int HANLDER_TAKE_PHOTO_EVENT = 1000;
    private final int HANDLER_REFRESH_LIST_EVENT = 1002;

    /**
     * loading...
     */
    private TextView tv_empty_view;
    /**
     * back
     */
    private ImageView iv_back;
    /**
     * finish to select picture
     */
    private TextView tv_select_finish;

    /**
     * finish to selct picture folder
     */
    private TextView tv_photo_folder;

    /***
     * photo gridview
     */
    private GridView gv_photo_list;

    /**
     * show picture folder
     */
    private LinearLayout ll_folder_panel;
    /**
     * photoFolder  listview
     */
    private ListView lv_folder_list;

    /**
     * 当前展示图片的list
     */
    private List<PhotoInfo> mCurrentList;
    private PhotoListAdapter mPhotoListAdapter;


    /**
     * 所有的图片文件列表
     */
    private List<PhotoFolderInfo> mAllPhotoFolderList;
    private FolderListAdapter mFolderListAdapter;

    private FunctionConfig mFunctionConfig;
    /**
     * current selected picture
     */
    private HashMap<String, PhotoInfo> mSelectPhotoMap = new HashMap<>();


    private Handler mHanlder = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == HANDLER_REFRESH_LIST_EVENT) {
                refreshSelectCount();
                mPhotoListAdapter.notifyDataSetChanged();
                mFolderListAdapter.notifyDataSetChanged();
                if (mAllPhotoFolderList.get(0).getPhotoInfoList() == null || mAllPhotoFolderList.get(0).getPhotoInfoList().size() == 0) {
                    tv_empty_view.setText("没有照片");
                }
                gv_photo_list.setEnabled(true);
                tv_select_finish.setEnabled(true);
                lv_folder_list.setEnabled(true);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFunctionConfig = PhotoFinal.getFunctionConfig();
        setContentView(R.layout.activity_select);
        initView();
        setClikListener();

        mCurrentList = new ArrayList<PhotoInfo>();
        mPhotoListAdapter = new PhotoListAdapter(PhotoSelectActivity.this, mCurrentList, mSelectPhotoMap, mScreenWidth);
        gv_photo_list.setEmptyView(tv_empty_view);
        gv_photo_list.setAdapter(mPhotoListAdapter);

        mAllPhotoFolderList = new ArrayList<PhotoFolderInfo>();
        mFolderListAdapter = new FolderListAdapter(PhotoSelectActivity.this, mAllPhotoFolderList);
        lv_folder_list.setAdapter(mFolderListAdapter);

        refreshSelectCount();
        requestPhotoPermission();


    }

    private void requestPhotoPermission() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            getPhotos();
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(this, "请求访问相册",
                    PhotoFinal.PERMISSIONS_CODE_PHOTO, Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }

    private void refreshSelectCount() {

    }

    private void setClikListener() {
        iv_back.setOnClickListener(this);
        tv_select_finish.setOnClickListener(this);
        tv_photo_folder.setOnClickListener(this);

    }

    private void initView() {
        tv_empty_view = (TextView) findViewById(R.id.tv_empty_view);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_select_finish = (TextView) findViewById(R.id.tv_select_finish);
        tv_photo_folder = (TextView) findViewById(R.id.tv_photo_folder);
        gv_photo_list = (GridView) findViewById(R.id.gv_photo_list);
        ll_folder_panel = (LinearLayout) findViewById(R.id.ll_folder_panel);
        lv_folder_list = (ListView) findViewById(R.id.lv_folder_list);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.tv_select_finish:
                break;
            case R.id.iv_back:
                break;
            case R.id.tv_photo_folder:
                break;
            default:
                break;
        }
    }

    /**
     * 获取照片集
     * <p/>
     * 先清除图片集列表 再加载图片集列表 清除单前图集的所有图片 再加载当前图片
     */
    public void getPhotos() {
        tv_select_finish.setEnabled(false);
        gv_photo_list.setEnabled(false);
        lv_folder_list.setEnabled(false);
        new Thread() {
            @Override
            public void run() {
                super.run();
                mAllPhotoFolderList.clear();
                List<PhotoFolderInfo> allFolderList = PhotoUtil.getAllPhotoFolder(PhotoSelectActivity.this, mSelectPhotoMap);
                mAllPhotoFolderList.addAll(allFolderList);

                mCurrentList.clear();
                if (allFolderList.size() > 0) {
                    if (allFolderList.get(0).getPhotoInfoList() != null) {
                        mCurrentList.addAll(allFolderList.get(0).getPhotoInfoList());
                    }
                }
                refreshAdapter();
            }
        }.start();

    }

    private void refreshAdapter() {
        mHanlder.sendEmptyMessageDelayed(HANDLER_REFRESH_LIST_EVENT, 100);
    }

    @Override
    public void onPermissionsGranted(List<String> list) {
        getPhotos();
    }

    @Override
    public void onPermissionsDenied(List<String> list) {
        tv_empty_view.setText("无法打开相册");
        tv_select_finish.setVisibility(View.GONE);
    }

}
