package cn.liweiqin.testselectphoto.ui.adpater;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;

import java.util.List;

import cn.liweiqin.testselectphoto.PhotoFinal;
import cn.liweiqin.testselectphoto.R;
import cn.liweiqin.testselectphoto.utils.PhotoUtil;


/**
 * Created by Qinda on 2016/2/16.
 */
public class PhotoShowListAdpater extends ViewHolderAdapter<PhotoShowListAdpater.PhotoViewHolder, String> {

    private int mScreenWidth;
    private int mRowWidth;
    private List<String> mSelectList;

    public PhotoShowListAdpater(Context context, List<String> list, int mScreenWidth) {
        super(context, list);
        this.mScreenWidth = mScreenWidth;
        this.mSelectList = list;
        this.mRowWidth = mScreenWidth / 3;
    }

    @Override
    public int getCount() {
        return super.getCount() + 1;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = inflate(R.layout.item_photo, viewGroup);
        setHeight(view);
        return new PhotoViewHolder(view);
    }

    public void setHeight(View view) {
        int height = mScreenWidth / 3 - 8;
        view.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));
    }


    @Override
    public void onBindViewHolder(PhotoViewHolder viewHolder, int position) {
        if (position == mSelectList.size()) {
            viewHolder.iv_thumb.setImageResource(R.drawable.icon_addpic_unfocused);
            if (position == PhotoFinal.PICTURE_MAX_SIZE) {
                viewHolder.iv_thumb.setVisibility(View.GONE);
            }
        } else {
            PhotoUtil.display((Activity) getContext(), mSelectList.get(position), viewHolder.iv_thumb, mRowWidth, mRowWidth);
        }
    }

    public class PhotoViewHolder extends ViewHolderAdapter.ViewHolder {

        public ImageView iv_thumb;
        public ImageView iv_check;

        public PhotoViewHolder(View view) {
            super(view);
            iv_check = (ImageView) view.findViewById(R.id.iv_check);
            iv_check.setVisibility(View.GONE);
            iv_thumb = (ImageView) view.findViewById(R.id.iv_thumb);
        }
    }
}
