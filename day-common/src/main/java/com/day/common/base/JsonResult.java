package com.day.common.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 通用返回结果
 *
 * @author wangjunming
 * @since 2020/10/12 13:24
 */
@ApiModel(description = "通用返回值对象")
public class JsonResult<R> {

    @ApiModelProperty(value = "是否成功",example = "")
    private boolean success;

    @ApiModelProperty(value = "是否成功的编码",example = "")
    private int code;

    @ApiModelProperty(value = "返回的错误信息",example = "")
    private String msg;

    @ApiModelProperty(value = "返回的数据",example = "")
    private R data;

    private static <R> JsonResult<R> defaultSuccess() {
        return new JsonResult<R>().setSuccess(true).setCode(200);
    }

    public static JsonResult<Object> success() {
        return defaultSuccess().setMsg("success");
    }

    public static <R> JsonResult<Object> success(R data) {
        return success().setData(data);
    }

    public static JsonResult<Object> successMsg(String msg) {
        return defaultSuccess().setMsg(msg);
    }

    public static <R> JsonResult<Object> successDataMsg(R data,String msg) {
        return defaultSuccess().setData(data).setMsg(msg);
    }

    public static <R> JsonResult<R> ofFail(int code, String msg) {
        JsonResult<R> jsonResult = new JsonResult<>();
        jsonResult.setSuccess(false);
        jsonResult.setCode(code);
        jsonResult.setMsg(msg);
        return jsonResult;
    }

    public boolean isSuccess() {
        return success;
    }

    public JsonResult<R> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public int getCode() {
        return code;
    }

    public JsonResult<R> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public JsonResult<R> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public R getData() {
        return data;
    }

    public JsonResult<R> setData(R data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "JsonResult{" + "success=" + success + ", code=" + code + ", msg='" + msg + '\'' + ", data=" + data + '}';
    }

}
