package cn.liweiqin.testselectphoto.ui.weight;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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


/**
 * 这里就是展示图片的界面了
 * <p/>
 * Created by liweiqin on 2016/1/31.
 */
public class PhotoSelectActivity extends BasePhotoActivity implements View.OnClickListener {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFunctionConfig = PhotoFinal.getFunctionConfig();
        setContentView(R.layout.activity_select);
        initView();
        setClikListener();


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
}
