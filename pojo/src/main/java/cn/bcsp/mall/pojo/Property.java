package cn.bcsp.mall.pojo;

import java.util.List;

public class Property {
    private Integer propertyId; //属性(参数)ID
    private String propertyName; //属性(参数)名字
    private Category propertyCategory;  //属性(参数)对应分类
    private List<PropertyValue> propertyValueList; //属性(参数)对应的值的集合

    public Property() {
    }

    public Property(Integer propertyId, String propertyName, Category propertyCategory, List<PropertyValue> propertyValueList) {
        this.propertyId = propertyId;
        this.propertyName = propertyName;
        this.propertyCategory = propertyCategory;
        this.propertyValueList = propertyValueList;
    }

    public Property(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Category getPropertyCategory() {
        return propertyCategory;
    }

    public void setPropertyCategory(Category propertyCategory) {
        this.propertyCategory = propertyCategory;
    }

    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }

    public void setPropertyValueList(List<PropertyValue> propertyValueList) {
        this.propertyValueList = propertyValueList;
    }

    @Override
    public String toString() {
        return "Property{" +
                "propertyId=" + propertyId +
                ", propertyName='" + propertyName + '\'' +
                ", propertyCategory=" + propertyCategory +
                ", propertyValueList=" + propertyValueList +
                '}';
    }
}
