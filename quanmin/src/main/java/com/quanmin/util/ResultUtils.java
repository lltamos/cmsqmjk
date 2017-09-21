package com.quanmin.util;

import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
 * 返回工具
 *
 * @author heasy
 */
public class ResultUtils implements Serializable {

    private String msg; // 错误信息
    private String resultCode;// 返回代码
    private boolean success;// 是否成功
    private Integer count;// 总页数
    private Object value;// 返回数据


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public static ResultUtils returnSucess(String msg, Object data) {
        ResultUtils res = new ResultUtils();
        res.setMsg( msg );
        res.setSuccess( Commons.DATA_TRUE );
        res.setResultCode( Commons.DATA_SUCCESS_CODE );

        if (data instanceof Page) {
            Page page = (Page) data;
            List content = page.getContent();
            int totalPages = page.getTotalPages();
            res.setValue( content );
            res.setCount( totalPages );
        } else {
            res.setValue( data );
        }
        return res;

    }
    public static ResultUtils returnSucess(String resultCode,String msg, Object data) {
        ResultUtils res = new ResultUtils();
        res.setMsg( msg );
        res.setSuccess( Commons.DATA_TRUE );
        res.setResultCode( resultCode );

        if (data instanceof Page) {
            Page page = (Page) data;
            List content = page.getContent();
            int totalPages = page.getTotalPages();
            res.setValue( content );
            res.setCount( totalPages );
        } else {
            res.setValue( data );
        }
        return res;

    }


    /**
     *
     * @param data
     * @param count 总页数
     * @return
     */
    public static ResultUtils returnSucess(Object data, int count) {
        ResultUtils resultUtils = returnSucess( data );
        resultUtils.setCount( count );
        return resultUtils;

    }

    public static ResultUtils returnSucess(Object data) {

        return returnSucess( Commons.DATA_SUCCESS_STR, data );

    }

    public static ResultUtils returnSucess() {

        return returnSucess( Commons.DATA_SUCCESS_STR, null );

    }

    public static ResultUtils returnFail(String msg) {

        ResultUtils res = new ResultUtils();
        res.setMsg( msg );
        res.setSuccess( Commons.DATA_FALSE );
        res.setResultCode( Commons.DATA_ERROR_CODE );
        return res;

    }

    public static ResultUtils returnFail() {
        return returnFail( Commons.DATA_ERROR_STR );
    }

    public static ResultUtils returnException() {
        return returnFail( Commons.DATA_EXCPTION_STR );
    }
}
