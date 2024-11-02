package cn.edu.xmu.javaee.productdemoaop.mapper.Join.po;

import cn.edu.xmu.javaee.productdemoaop.mapper.generator.po.OnSalePo;
import cn.edu.xmu.javaee.productdemoaop.mapper.generator.po.ProductPo;

import java.time.LocalDateTime;
import java.util.List;

public class JoinPo {
    // 商品基本属性
    private ProductPo otherProduct;
    private OnSalePo onSaleList;
    private Long id;
    private Long shopId;

    private Long goodsId;
    private Long categoryId;
    private Long templateId;
    private String skuSn;
    private String name;
    private Long originalPrice;
    private Long weight;
    private String barcode;
    private String unit;
    private String originPlace;
    private Long creatorId;
    private String creatorName;
    private Long modifierId;
    private String modifierName;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
    private Byte status;
    private int commissionRatio;
    private Long shopLogisticId;
    private Long freeThreshold;



    // onsale 属性
    private Onsale onsale;
    public ProductPo getOtherProduct() {
        return otherProduct;
    }
    public void setOtherProduct(ProductPo otherProduct) {
        this.otherProduct = otherProduct;
    }
    public OnSalePo getOnSaleList()
    {
        return onSaleList;
    }
    public void setOnSaleList(OnSalePo onSaleList){
        this.onSaleList = onSaleList;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public String getSkuSn() {
        return skuSn;
    }

    public void setSkuSn(String skuSn) {
        this.skuSn = skuSn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Long originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getOriginPlace() {
        return originPlace;
    }

    public void setOriginPlace(String originPlace) {
        this.originPlace = originPlace;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Long getModifierId() {
        return modifierId;
    }

    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    public String getModifierName() {
        return modifierName;
    }

    public void setModifierName(String modifierName) {
        this.modifierName = modifierName;
    }

    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public int getCommissionRatio() {
        return commissionRatio;
    }

    public void setCommissionRatio(int commissionRatio) {
        this.commissionRatio = commissionRatio;
    }

    public Long getShopLogisticId() {
        return shopLogisticId;
    }

    public void setShopLogisticId(Long shopLogisticId) {
        this.shopLogisticId = shopLogisticId;
    }

    public Long getFreeThreshold() {
        return freeThreshold;
    }

    public void setFreeThreshold(Long freeThreshold) {
        this.freeThreshold = freeThreshold;
    }

    // 商品基本属性的 getter 和 setter 方法
    // ...

    // Onsale 内部类
    public static class Onsale {
        public Onsale() {
            // 默认构造函数
        }
        private Long id;
        private Long price;
        private LocalDateTime beginTime;
        private LocalDateTime endTime;
        private Integer quantity;
        private Integer type;
        private Long creatorId;
        private String creatorName;
        private Long modifierId;
        private String modifierName;
        private LocalDateTime gmtCreate;
        private LocalDateTime gmtModified;
        private Integer maxQuantity;
        private Boolean invalid;


        // Onsale 属性的 getter 和 setter 方法
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getPrice() {
            return price;
        }

        public void setPrice(Long price) {
            this.price = price;
        }

        public LocalDateTime getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(LocalDateTime beginTime) {
            this.beginTime = beginTime;
        }

        public LocalDateTime getEndTime() {
            return endTime;
        }

        public void setEndTime(LocalDateTime endTime) {
            this.endTime = endTime;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public Long getCreatorId() {
            return creatorId;
        }

        public void setCreatorId(Long creatorId) {
            this.creatorId = creatorId;
        }

        public String getCreatorName() {
            return creatorName;
        }

        public void setCreatorName(String creatorName) {
            this.creatorName = creatorName;
        }

        public Long getModifierId() {
            return modifierId;
        }

        public void setModifierId(Long modifierId) {
            this.modifierId = modifierId;
        }

        public String getModifierName() {
            return modifierName;
        }

        public void setModifierName(String modifierName) {
            this.modifierName = modifierName;
        }

        public LocalDateTime getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(LocalDateTime gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public LocalDateTime getGmtModified() {
            return gmtModified;
        }

        public void setGmtModified(LocalDateTime gmtModified) {
            this.gmtModified = gmtModified;
        }

        public Integer getMaxQuantity() {
            return maxQuantity;
        }

        public void setMaxQuantity(Integer maxQuantity) {
            this.maxQuantity = maxQuantity;
        }

        public Boolean getInvalid() {
            return invalid;
        }

        public void setInvalid(Boolean invalid) {
            this.invalid = invalid;
        }
    }

    // onsale 属性的 getter 和 setter 方法
    public Onsale getOnsale() {
        return onsale;
    }

    public void setOnsale(Onsale onsale) {
        this.onsale = onsale;
    }
}
