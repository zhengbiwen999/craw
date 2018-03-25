package com.springboot.utils.json;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.springboot.utils.date.DateUtil;
import com.springboot.utils.error.ThrowableUtil;
import com.springboot.utils.log.MwLogger;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import java.util.*;

//import com.alibaba.fastjson.parser.deserializer.DateFormatDeserializer;

/**
 * JsonUtil的fastjson实现
 */
public class FastJsonUtil extends AbstractJsonUtil {


    public static void main(String[] args) {

        FastJsonUtil jsonUtil = new FastJsonUtil();

        List<String> test = new ArrayList<>();

        System.out.println(jsonUtil.isInterface(test.getClass(), "java.util.List"));
    }

    public boolean isInterface(Class c, String szInterface) {
        Class[] face = c.getInterfaces();//获取类c实现的所有接口
        for (int i = 0, j = face.length; i < j; i++) {
            //判断类接口中是否有与要判断的接口一致
            if (face[i].getName().equals(szInterface)) {
                return true;
            } else {
                //判断其父类是否实现了这个接口
                Class[] face1 = face[i].getInterfaces();
                for (int x = 0; x < face1.length; x++) {
                    if (face1[x].getName().equals(szInterface)) {
                        return true;
                    } else if (isInterface(face1[x], szInterface)) {
                        return true;
                    }
                }
            }
        }
        if (null != c.getSuperclass()) {
            //递归，依次判断是否实现父类接口
            return isInterface(c.getSuperclass(), szInterface);
        }
        return false;
    }

    //private static final Logger logger = new MwLogger(FastJsonUtil.class);

    @Override
    public String toJson(Object object) {
        if (null == object) {
            return null;
        }
        return JSON.toJSONString(object, SerializerFeature.DisableCircularReferenceDetect);
    }

    @Override
    public String toJson(Object thriftObject, boolean removeThriftProperty) {
        if (thriftObject == null){
            return null;
        }
        if (removeThriftProperty) {
            PropertyFilter profilter = (Object object, String name, Object value) -> {

//                if (object instanceof TBase && name.startsWith("set") && name.length() > 3) {
//                    return false;
//                }
//                if (object instanceof TBase && name.endsWith("Iterator") && value instanceof Iterator) {
//                    return false;
//                }
                return true;
            };
            return JSON.toJSONString(thriftObject, profilter, SerializerFeature.DisableCircularReferenceDetect);
//            if (thriftObject instanceof TBase) {
//                Object object = JSON.toJSON(thriftObject);
//                removeThriftProperty(object);
//                return toJson(object);
//            } else if (isInterface(thriftObject.getClass(), "java.util.List")) {
//                List list = (List) thriftObject;
//                if (CollectionUtils.isNotEmpty(list)) {
//                    if (list.get(0) instanceof TBase) {
//                        Object object = JSON.toJSON(thriftObject);
//                        removeThriftProperty(object);
//                        return toJson(object);
//                    }
//                }
//                return toJson(thriftObject);
//            } else {
//                return toJson(thriftObject);
//            }

        } else {
            return toJson(thriftObject);
        }

    }


    private void removeThriftProperty(Object json) {
        try {
            if (json instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) json;
                List<String> list = new ArrayList<>(jsonObject.keySet().size());
                list.addAll(jsonObject.keySet());

                for (String key : list) {
                    if (key.startsWith("set") && key.length() > 3) {
                        jsonObject.remove(key);
                    }
                    Object obj = jsonObject.get(key);
                    if (obj instanceof JSONArray) {
                        JSONArray arr = (JSONArray) jsonObject.get(key);
                        for (int i = 0; i < arr.size(); i++) {
                            removeThriftProperty(arr.get(i));
                        }
                        jsonObject.remove(key + "Iterator");
                        jsonObject.remove(key + "Size");
                    } else if (obj instanceof JSONObject) {
                        removeThriftProperty(obj);
                    }
                }

            } else if (json instanceof JSONArray) {
                JSONArray arr = (JSONArray) json;
                for (int i = 0; i < arr.size(); i++) {
                    removeThriftProperty(arr.get(i));
                }
            }
        } catch (Exception e) {
            //logger.error("removeThriftProperty error:" + ThrowableUtil.getStackTrace(e) + ",json=>" + toJson(json));
        }
    }

    /**
     * 默认以'yyyy-MM-dd HH:mm:ss'格式序列化
     *
     * @param object
     * @return
     */
    @Override
    public String toJson4DateFormat(Object object) {
        return toJson4DateFormat(object, Collections.EMPTY_MAP);
    }

    /**
     * 只针对日期处理
     * valueFormat表示对应的字段和格式,for example: {"birthday", "yyyy-MM-dd"}
     * 1. valueFormat为null, 则日期将自动转成序列化成long类型
     * 2. valueFormat为空map或者不包含的日期字段, 则日期均将默认以'yyyy-MM-dd HH:mm:ss'的格式学序列化
     * 3. valueFormat为非空map, 则日期将以指定的格式进行序列化
     * <p>
     * 可优化传参,map<string, list<string>>, 暂时不这么做
     *
     * @param object
     * @param valueFormat
     * @return
     */
    @Override
    public String toJson4DateFormat(Object object, Map<String, Object> valueFormat) {
        if (valueFormat == null || object == null) {
            return toJson(object);
        }
        ValueFilter valueFilter = (obj, propertyName, propertyValue) -> {
            if (valueFormat.containsKey(propertyName)) {
                return DateUtil.formatDate((Date) propertyValue, valueFormat.get(propertyName).toString());
            }
            return propertyValue;
        };

        return JSON.toJSONString(object, valueFilter);
    }

    /**
     * 序列化字段属性忽略,推荐使用@JsonIgnore, 但是为了保证不侵入entry, 也可以使用代码形式
     *
     * @param object
     * @param ignorePropertyNames
     * @return
     */
    @Override
    public String toJsonIgnoreProperty(Object object, String... ignorePropertyNames) {
        if (ArrayUtils.isEmpty(ignorePropertyNames) || object == null) {
            return toJson(object);
        }
        PropertyFilter propertyFilter = (obj, propertyName, propertyValue) -> {
            if (ArrayUtils.indexOf(ignorePropertyNames, propertyName) == -1) {
                return true;
            }
            return false;
        };
        return JSON.toJSONString(object, propertyFilter);
    }

    /**
     * 序列化包含的字段, 避免大量无用的字段写入缓存
     *
     * @param object
     * @param includePropertyNames
     * @return
     */
    @Override
    public String toJsonIncludeProperty(Object object, String... includePropertyNames) {
        if (ArrayUtils.isEmpty(includePropertyNames) || object == null) {
            return toJson(object);
        }
        PropertyFilter propertyFilter = (obj, propertyName, propertyValue) -> {
            if (ArrayUtils.indexOf(includePropertyNames, propertyName) != -1) {
                return true;
            }
            return false;
        };
        return JSON.toJSONString(object, propertyFilter);
    }

    /**
     * 类似@JSONField, 用于避免实体类污染
     *
     * @param object
     * @param replacePropertyMap
     * @return
     */
    @Override
    public String toJsonReplaceProperty(Object object, Map<String, Object> replacePropertyMap) {
        if (MapUtils.isEmpty(replacePropertyMap) || object == null) {
            return toJson(object);
        }

        NameFilter nameFilter = (object1, propertyName, propertyValue) -> {
            if (replacePropertyMap.containsKey(propertyName)) {
                return replacePropertyMap.get(propertyName).toString();
            }
            return propertyName;
        };

        return JSON.toJSONString(object, nameFilter);
    }

    @Override
    public Object fromJson(String jsonString) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        return JSON.parse(jsonString);
    }

    @Override
    public <T> T fromJson(String jsonString, Class<T> c) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        return JSON.parseObject(jsonString, c);
    }

    @Override
    public <T> T fromJson(String jsonString, TypeReference<T> typeReference) {
        try {
            return JSON.parseObject(jsonString, typeReference);
        } catch (Exception e) {
            //logger.error("fromJson error=>" + ThrowableUtil.getStackTrace(e) + ", jsonString=>" + jsonString + ",typeReference=>" + typeReference);
            throw e;
        }
    }


    @Override
    public String getJsonByKey(String jsonString, String key) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        JSONObject jsonObject = JSON.parseObject(jsonString);
        return jsonObject.getString(key);
    }

}