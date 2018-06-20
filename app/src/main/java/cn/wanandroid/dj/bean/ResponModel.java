package cn.wanandroid.dj.bean;

/**
 * @author daiji
 * @date 2018/5/14
 */
public class ResponModel<T> {
    private T data;
    private String errorMsg;
    private int errorCode;

    public T getData() {
        return data;
    }

    public void setData(T data) {
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
