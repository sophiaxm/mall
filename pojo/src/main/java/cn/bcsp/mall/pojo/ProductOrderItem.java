package cn.bcsp.mall.pojo;

//购物车订单的商品 信息
public class ProductOrderItem {
    private Integer productOrderItemId;  //ID
    private Short productOrderItemNumber; //订单或者是购物车中的商品的数量
    private Double productOrderItemPrice; //商品的价格

    private Product productOrderItemProduct;  //关联的商品的信息
    private ProductOrder productOrderItemOrder;
    private User productOrderItemUser;
    private String productOrderItemUserMessage;
    //订单产品是否已经评价
    private Boolean isReview;
    /////////////////////////////////////////////
    private Integer productOrderItemProductId; //订单或者是购物车中的商品的ID
    private Integer productOrderItemOrderId;//订单的ID,购车中的商品此属性值是null
    private Integer productOrderItemUserId; //订单或者是购物车对应的用户的ID

    public Integer getProductOrderItemProductId() {
        return productOrderItemProductId;
    }

    public void setProductOrderItemProductId(Integer productOrderItemProductId) {
        this.productOrderItemProductId = productOrderItemProductId;
    }

    public Integer getProductOrderItemOrderId() {
        return productOrderItemOrderId;
    }

    public void setProductOrderItemOrderId(Integer productOrderItemOrderId) {
        this.productOrderItemOrderId = productOrderItemOrderId;
    }

    public Integer getProductOrderItemUserId() {
        return productOrderItemUserId;
    }

    public void setProductOrderItemUserId(Integer productOrderItemUserId) {
        this.productOrderItemUserId = productOrderItemUserId;
    }

    public ProductOrderItem() {
    }

    public ProductOrderItem(Integer productOrderItemId, Short productOrderItemNumber, Double productOrderItemPrice, Product productOrderItemProduct, ProductOrder productOrderItemOrder, User productOrderItemUser, String productOrderItemUserMessage, Boolean isReview) {
        this.productOrderItemId = productOrderItemId;
        this.productOrderItemNumber = productOrderItemNumber;
        this.productOrderItemPrice = productOrderItemPrice;
        this.productOrderItemProduct = productOrderItemProduct;
        this.productOrderItemOrder = productOrderItemOrder;
        this.productOrderItemUser = productOrderItemUser;
        this.productOrderItemUserMessage = productOrderItemUserMessage;
        this.isReview = isReview;
    }

    public ProductOrderItem(Integer productOrderItemId) {
        this.productOrderItemId = productOrderItemId;
    }
    public Integer getProductOrderItemId() {
        return productOrderItemId;
    }

    public void setProductOrderItemId(Integer productOrderItemId) {
        this.productOrderItemId = productOrderItemId;
    }

    public Short getProductOrderItemNumber() {
        return productOrderItemNumber;
    }

    public void setProductOrderItemNumber(Short productOrderItemNumber) {
        this.productOrderItemNumber = productOrderItemNumber;
    }

    public Double getProductOrderItemPrice() {
        return productOrderItemPrice;
    }

    public void setProductOrderItemPrice(Double productOrderItemPrice) {
        this.productOrderItemPrice = productOrderItemPrice;
    }

    public Product getProductOrderItemProduct() {
        return productOrderItemProduct;
    }

    public void setProductOrderItemProduct(Product productOrderItemProduct) {
        this.productOrderItemProduct = productOrderItemProduct;
    }

    public ProductOrder getProductOrderItemOrder() {
        return productOrderItemOrder;
    }

    public void setProductOrderItemOrder(ProductOrder productOrderItemOrder) {
        this.productOrderItemOrder = productOrderItemOrder;
    }

    public User getProductOrderItemUser() {
        return productOrderItemUser;
    }

    public void setProductOrderItemUser(User productOrderItemUser) {
        this.productOrderItemUser = productOrderItemUser;
    }

    public String getProductOrderItemUserMessage() {
        return productOrderItemUserMessage;
    }

    public void setProductOrderItemUserMessage(String productOrderItemUserMessage) {
        this.productOrderItemUserMessage = productOrderItemUserMessage;
    }

    public Boolean getIsReview() {
        return isReview;
    }

    public void setIsReview(Boolean review) {
        isReview = review;
    }

    @Override
    public String toString() {
        return "ProductOrderItem{" +
                "productOrderItemId=" + productOrderItemId +
                ", productOrderItemNumber=" + productOrderItemNumber +
                ", productOrderItemPrice=" + productOrderItemPrice +
                ", productOrderItemProduct=" + productOrderItemProduct +
                ", productOrderItemOrder=" + productOrderItemOrder +
                ", productOrderItemUser=" + productOrderItemUser +
                ", productOrderItemUserMessage='" + productOrderItemUserMessage + '\'' +
                ", isReview=" + isReview +
                '}';
    }
}
