package cn.liweiqin.testselectphoto.ui.weight;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import cn.liweiqin.testselectphoto.BasePhotoActivity;
import cn.liweiqin.testselectphoto.R;


/**
 * 这里就是展示图片的界面了
 * <p/>
 * Created by liweiqin on 2016/1/31.
 */
public class PhotoSelectActivity extends BasePhotoActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
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

    

}
