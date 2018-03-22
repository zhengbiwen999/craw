package com.springboot.utils.trace;

import com.springboot.utils.uuid.UUIDUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by yangjunming on 2017/1/18.
 */
public enum TraceUtil {

    INSTANCE;

    private ThreadLocal<String> traceId = new ThreadLocal<>();

    public void setRequestId(String requestId) {
        traceId.set(requestId);
    }

    public String getRequestId() {
        String requestId = traceId.get();
        if (StringUtils.isEmpty(requestId)) {
            traceId.set(UUIDUtil.getUuid());
        }
        return traceId.get();
    }

    public void clear() {
        traceId.remove();
    }


}
