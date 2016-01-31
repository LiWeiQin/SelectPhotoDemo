package cn.liweiqin.testselectphoto;


import java.util.ArrayList;
import java.util.Collection;
import cn.liweiqin.testselectphoto.model.PhotoInfo;

/**
 * Created by liweiqin on 2016/1/31.
 */
public class FunctionConfig implements Cloneable {

    private int maxSize;
    private ArrayList<String> selectedList;//选择的照片
    private ArrayList<String> filterList;//缓存

    private FunctionConfig(final Builder mBuilder) {
        this.maxSize = mBuilder.maxSize;
        this.filterList = mBuilder.filterList;
        this.selectedList = mBuilder.selectedList;

    }

    public ArrayList<String> getFilterList() {
        return filterList;
    }

    public ArrayList<String> getSelectedList() {
        return selectedList;
    }

    public int getMaxSize() {
        return maxSize;
    }

    @Override
    public FunctionConfig clone() {
        FunctionConfig o = null;
        try {
            o = (FunctionConfig) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }


    public static class Builder {

        private int maxSize;
        private ArrayList<String> selectedList;//选择的照片
        private ArrayList<String> filterList;//缓存

        public Builder setFilterList(ArrayList<String> filterList) {
            this.filterList = filterList;
            return this;
        }

        public Builder setSelectedList(ArrayList<String> selectedList) {
            this.selectedList = selectedList;
            return this;
        }

        public Builder setMaxSize(int maxSize) {
            this.maxSize = maxSize;
            return this;
        }

        public Builder setSelected(ArrayList<String> selectedList) {
            if (selectedList != null) {
                this.selectedList = (ArrayList<String>) selectedList.clone();
            }
            return this;
        }

        public Builder setSelected(Collection<PhotoInfo> selectedList) {
            if (selectedList != null) {
                ArrayList<String> list = new ArrayList<>();
                for (PhotoInfo info : selectedList) {
                    if (info != null) {
                        list.add(info.getPhotoPath());
                    }
                }

                this.selectedList = list;
            }
            return this;
        }

        public FunctionConfig build() {
            return new FunctionConfig(this);
        }

    }

}
