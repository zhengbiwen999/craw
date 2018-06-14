package com.springboot.common;

/**
 * Created by yangjunming on 2017/2/5.
 */
public class ErrorCode {

    /**
     * 约定"0"表示业务操作成功
     */
    public final static String SUCCESS = "0";

    /**
     * 通用的业务处理失败
     */
    public final static String FAIL = "-1";

    /**
     * 没有数据
     */
    public final static String NO_DATA = "-2";

    /**
     * 约定0开头的,代表各种未知错误
     */
    public final static String UNKNOWN = "000";

    /**
     * 签名校验错误
     */
    public final static String SIGN_CHECK_ERROR = "011";

    /**
     * SESSION 超时
     */
    public static final String SESSION_TIME_OUT = "-1001";

    /**
     * token请求超时
     */
    public static final String TOKEN_TIME_OUT = "-1002";

    /**
     * 请求超时
     */
    public static final String REQUEST_TIME_OUT = "-1003";

    /**
     * 没有找到指定的 handler
     */
    public static final String HANDLER_UNDEFINED = "-1004";

    /**
     * 没有权限
     */
    public static final String NO_PERMISSION = "-1005";

    /**
     * 门店不正确
     */
    public static final String WRONG_SHOP = "-1006";

    /**
     * 非小散门店
     */
    public static final String NOT_SMALL_SHOP = "-1007";

    /**
     * 约定2开头的,代表各种参数错误
     */
    public final static String PARAM_ERROR = "201"; //参数错误  多参数或者少参数
    public final static String PARAM_VALID_ERROR = "202"; //参数校验失败 参数格式有问题
    public final static String PARAM_EMPTY = "203"; //参数为空

    /**
     * 约定3开头的,代表各种Db层面的错误
     */
    public final static String DB_ERROR = "300"; //数据库层面的错误

    /**
     * 约定4开头的，代表websocket类的错误
     */
    public final static String WEBSOCKET_ERROR = "400"; //websocket层的错误


}