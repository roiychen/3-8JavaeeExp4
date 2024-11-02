package cn.edu.xmu.javaee.productdemoaop.mapper.Join;

import cn.edu.xmu.javaee.productdemoaop.mapper.Join.po.JoinPo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface ProductJoinMapper {
    @SelectProvider(type= NewProductSqlProvider.class, method="selectProductWithOnSale")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "shopId", column = "shop_id"),
            @Result(property = "goodsId", column = "goods_id"),
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "templateId", column = "template_id"),
            @Result(property = "skuSn", column = "sku_sn"),
            @Result(property = "name", column = "name"),
            @Result(property = "originalPrice", column = "original_price"),
            @Result(property = "weight", column = "weight"),
            @Result(property = "barcode", column = "barcode"),
            @Result(property = "unit", column = "unit"),
            @Result(property = "originPlace", column = "origin_place"),
            @Result(property = "creatorId", column = "creator_id"),
            @Result(property = "creatorName", column = "creator_name"),
            @Result(property = "modifierId", column = "modifier_id"),
            @Result(property = "modifierName", column = "modifier_name"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "status", column = "status"),
            @Result(property = "commissionRatio", column = "commission_ratio"),
            @Result(property = "shopLogisticId", column = "shop_logistic_id"),
            @Result(property = "freeThreshold", column = "free_threshold"),

            // goods_onsale 表的映射
            @Result(property = "onsale.id", column = "onsale_id"),
            @Result(property = "onsale.price", column = "price"),
            @Result(property = "onsale.beginTime", column = "begin_time"),
            @Result(property = "onsale.endTime", column = "end_time"),
            @Result(property = "onsale.quantity", column = "quantity"),
            @Result(property = "onsale.type", column = "type"),
            @Result(property = "onsale.creatorId", column = "onsale_creator_id"),
            @Result(property = "onsale.creatorName", column = "onsale_creator_name"),
            @Result(property = "onsale.modifierId", column = "onsale_modifier_id"),
            @Result(property = "onsale.modifierName", column = "onsale_modifier_name"),
            @Result(property = "onsale.gmtCreate", column = "onsale_gmt_create"),
            @Result(property = "onsale.gmtModified", column = "onsale_gmt_modified"),
            @Result(property = "onsale.maxQuantity", column = "max_quantity"),
            @Result(property = "onsale.invalid", column = "invalid"),

            // 其他产品的映射
            @Result(property = "otherProduct.id", column = "otherProduct_id"),
            @Result(property = "otherProduct.skuSn", column = "otherProduct_sku_sn"),
            @Result(property = "otherProduct.name", column = "otherProduct_name"),
            @Result(property = "otherProduct.originalPrice", column = "otherProduct_original_price"),
            @Result(property = "otherProduct.weight", column = "otherProduct_weight"),
            @Result(property = "otherProduct.barcode", column = "otherProduct_barcode"),
            @Result(property = "otherProduct.unit", column = "otherProduct_unit"),
            @Result(property = "otherProduct.originPlace", column = "otherProduct_origin_place"),
            @Result(property = "otherProduct.creatorId", column = "otherProduct_creator_id"),
            @Result(property = "otherProduct.creatorName", column = "otherProduct_creator_name"),
            @Result(property = "otherProduct.modifierId", column = "otherProduct_modifier_id"),
            @Result(property = "otherProduct.modifierName", column = "otherProduct_modifier_name"),
            @Result(property = "otherProduct.gmtCreate", column = "otherProduct_gmt_create"),
            @Result(property = "otherProduct.gmtModified", column = "otherProduct_gmt_modified")
    })
    List<JoinPo> NewSelectProduct(@Param("productName") String productName,
                                  @Param("currentTime")String currentTime);


}
