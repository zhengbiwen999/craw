package com.zbw.mysqlCrudDemo.domain;

public class AreaEntity {
    /**
     * 城市编号
     */
    private Integer cityId;

    /**
     * 区编号
     */
    private Integer districtId;

    /**
     * 区名称
     */
    private String districtName;

    private String areaSort;

    /**
     * 获取城市编号
     *
     * @return fiCityId - 城市编号
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * 设置城市编号
     *
     * @param cityId 城市编号
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * 获取区编号
     *
     * @return fiDistributeId - 区编号
     */
    public Integer getDistrictId() {
        return districtId;
    }

    /**
     * 设置区编号
     *
     * @param districtId 区编号
     */
    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    /**
     * 获取区名称
     *
     * @return fsAreaName - 区名称
     */
    public String getDistrictName() {
        return districtName;
    }

    /**
     * 设置区名称
     *
     * @param districtName 区名称
     */
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    /**
     * @return fsAreaSort
     */
    public String getAreaSort() {
        return areaSort;
    }

    /**
     * @param areaSort
     */
    public void setAreaSort(String areaSort) {
        this.areaSort = areaSort;
    }
}