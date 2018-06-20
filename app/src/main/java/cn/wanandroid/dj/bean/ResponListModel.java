package cn.wanandroid.dj.bean;

import java.util.List;

/**
 * @author daiji
 * @date 2018/5/14
 */
public class ResponListModel<T> {
    private List<T> data;
    private String errorMsg;
    private int errorCode;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
