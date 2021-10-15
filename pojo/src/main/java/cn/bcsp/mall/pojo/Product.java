package cn.bcsp.mall.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Product {
    private Integer productId;  //商品ID
    private String productName; //商品名字
    private String productTitle;  //商品标题
    private Double productPrice;   //商品的价格
    private Double productSalePrice;  //商品的销售价格
    private Date productCreateDate;  //商品的创建时间
    private Category productCategory; //商品的分类
    private Byte productIsEnabled;  //商品的状态

    private List<PropertyValue> propertyValueList=new ArrayList<>();
    private List<ProductImage> singleProductImageList=new ArrayList<>(); //商品的预览图片
    private List<ProductImage> detailProductImageList=new ArrayList<>(); //商品的详细图片
    private List<Review> reviewList=new ArrayList<>();
    private List<ProductOrderItem> productOrderItemList=new ArrayList<>();
    //销量数
    private Integer productSaleCount;
    //评价数
    private Integer productReviewCount;

    ///////////////////////////////////////////////////////////
    //商品的分类ID
    private Integer productCategoryId;

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Object setProductSaleCo;

    public Product(Integer productId, String productName, String productTitle, Double productPrice, Double productSalePrice, Date productCreateDate, Category productCategory, Byte productIsEnabled, List<PropertyValue> propertyValueList, List<ProductImage> singleProductImageList, List<ProductImage> detailProductImageList, List<Review> reviewList, List<ProductOrderItem> productOrderItemList, Integer productSaleCount, Integer productReviewCount, Object setProductSaleCo) {
        this.productId = productId;
        this.productName = productName;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.productSalePrice = productSalePrice;
        this.productCreateDate = productCreateDate;
        this.productCategory = productCategory;
        this.productIsEnabled = productIsEnabled;
        this.propertyValueList = propertyValueList;
        this.singleProductImageList = singleProductImageList;
        this.detailProductImageList = detailProductImageList;
        this.reviewList = reviewList;
        this.productOrderItemList = productOrderItemList;
        this.productSaleCount = productSaleCount;
        this.productReviewCount = productReviewCount;
        this.setProductSaleCo = setProductSaleCo;
    }

    public Product(Integer productId) {
        this.productId = productId;
    }

    public Product() {
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Double getProductSalePrice() {
        return productSalePrice;
    }

    public void setProductSalePrice(Double productSalePrice) {
        this.productSalePrice = productSalePrice;
    }

    public Date getProductCreateDate() {
        return productCreateDate;
    }

    public void setProductCreateDate(Date productCreateDate) {
        this.productCreateDate = productCreateDate;
    }

    public Category getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(Category productCategory) {
        this.productCategory = productCategory;
    }

    public Byte getProductIsEnabled() {
        return productIsEnabled;
    }

    public void setProductIsEnabled(Byte productIsEnabled) {
        this.productIsEnabled = productIsEnabled;
    }

    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }

    public void setPropertyValueList(List<PropertyValue> propertyValueList) {
        this.propertyValueList = propertyValueList;
    }

    public List<ProductImage> getSingleProductImageList() {
        return singleProductImageList;
    }

    public void setSingleProductImageList(List<ProductImage> singleProductImageList) {
        this.singleProductImageList = singleProductImageList;
    }

    public List<ProductImage> getDetailProductImageList() {
        return detailProductImageList;
    }

    public void setDetailProductImageList(List<ProductImage> detailProductImageList) {
        this.detailProductImageList = detailProductImageList;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public List<ProductOrderItem> getProductOrderItemList() {
        return productOrderItemList;
    }

    public void setProductOrderItemList(List<ProductOrderItem> productOrderItemList) {
        this.productOrderItemList = productOrderItemList;
    }

    public Integer getProductSaleCount() {
        return productSaleCount;
    }

    public void setProductSaleCount(Integer productSaleCount) {
        this.productSaleCount = productSaleCount;
    }

    public Integer getProductReviewCount() {
        return productReviewCount;
    }

    public void setProductReviewCount(Integer productReviewCount) {
        this.productReviewCount = productReviewCount;
    }

    public Object getSetProductSaleCo() {
        return setProductSaleCo;
    }

    public void setSetProductSaleCo(Object setProductSaleCo) {
        this.setProductSaleCo = setProductSaleCo;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productTitle='" + productTitle + '\'' +
                ", productPrice=" + productPrice +
                ", productSalePrice=" + productSalePrice +
                ", productCreateDate=" + productCreateDate +
                ", productCategory=" + productCategory +
                ", productIsEnabled=" + productIsEnabled +
                ", propertyValueList=" + propertyValueList +
                ", singleProductImageList=" + singleProductImageList +
                ", detailProductImageList=" + detailProductImageList +
                ", reviewList=" + reviewList +
                ", productOrderItemList=" + productOrderItemList +
                ", productSaleCount=" + productSaleCount +
                ", productReviewCount=" + productReviewCount +
                ", setProductSaleCo=" + setProductSaleCo +
                '}';
    }

    public Product(String productName,String productTitle,
                   Category category,Double productSalePrice,
                   Double productPrice,Byte productIsEnabled,Date productCreateDate){
        this.productName = productName;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.productSalePrice = productSalePrice;
        this.productCreateDate = productCreateDate;
        this.productCategory = category;
        this.productIsEnabled = productIsEnabled;
    }

    public Product(Integer productId,String productName,String productTitle,
                   Category category,Double productSalePrice,
                   Double productPrice,Byte productIsEnabled,Date productCreateDate){
        this.productId = productId;
        this.productName = productName;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.productSalePrice = productSalePrice;
        this.productCreateDate = productCreateDate;
        this.productCategory = category;
        this.productIsEnabled = productIsEnabled;
    }
}
