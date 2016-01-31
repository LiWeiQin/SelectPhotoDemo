package cn.liweiqin.testselectphoto.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liweiqin on 2016/1/31.
 */
public class PhotoFolderInfo implements Serializable{

    public PhotoFolderInfo(){}
    private int folderId;
    private String FolderName;
    private PhotoInfo coverPhoto;
    private List<PhotoInfo> photoInfoList;

    public List<PhotoInfo> getPhotoInfoList() {
        return photoInfoList;
    }

    public void setPhotoInfoList(List<PhotoInfo> photoInfoList) {
        this.photoInfoList = photoInfoList;
    }

    public PhotoInfo getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(PhotoInfo coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public String getFolderName() {
        return FolderName;
    }

    public void setFolderName(String folderName) {
        FolderName = folderName;
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }
}
