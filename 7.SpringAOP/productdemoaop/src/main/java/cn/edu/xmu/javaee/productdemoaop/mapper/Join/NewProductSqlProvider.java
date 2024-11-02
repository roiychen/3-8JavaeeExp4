package cn.edu.xmu.javaee.productdemoaop.mapper.Join;

import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.jdbc.SQL;
import java.util.Date;

@Mapper
public class NewProductSqlProvider {
    public static String selectProductWithOnSale(Map<String, Object> params)
    {
        String currentTime = (String) params.get("currentTime");
        String productName = (String) params.get("productName");
        return new SQL(){{
            SELECT(
                    "p.id, " +
                            "p.shop_id, " +
                            "p.goods_id, " +
                            "p.category_id, " +
                            "p.template_id, " +
                            "p.sku_sn, " +
                            "p.name, " +
                            "p.original_price, " +
                            "p.weight, " +
                            "p.barcode, " +
                            "p.unit, " +
                            "p.origin_place, " +
                            "p.creator_id, " +
                            "p.creator_name, " +
                            "p.modifier_id, " +
                            "p.modifier_name, " +
                            "p.gmt_create, " +
                            "p.gmt_modified, " +
                            "p.status, " +
                            "p.commission_ratio, " +
                            "p.shop_logistic_id, " +
                            "p.free_threshold, " +
                            "o.id as onsale_id, " +
                            "o.price, " +
                            "o.begin_time, " +
                            "o.end_time, " +
                            "o.quantity, " +
                            "o.type, " +
                            "o.creator_id as onsale_creator_id, " +
                            "o.creator_name as onsale_creator_name, " +
                            "o.modifier_id as onsale_modifier_id, " +
                            "o.modifier_name as onsale_modifier_name, " +
                            "o.gmt_create as onsale_gmt_create, " +
                            "o.gmt_modified as onsale_gmt_modified, " +
                            "o.max_quantity, " +
                            "o.invalid,"+
                            // 其他产品字段
                            "gp.id AS otherProduct_id, " +
                            "gp.sku_sn AS otherProduct_sku_sn, " +
                            "gp.name AS otherProduct_name, " +
                            "gp.original_price AS otherProduct_original_price, " +
                            "gp.weight AS otherProduct_weight, " +
                            "gp.barcode AS otherProduct_barcode, " +
                            "gp.unit AS otherProduct_unit, " +
                            "gp.origin_place AS otherProduct_origin_place, " +
                            "gp.creator_id AS otherProduct_creator_id, " +
                            "gp.creator_name AS otherProduct_creator_name, " +
                            "gp.modifier_id AS otherProduct_modifier_id, " +
                            "gp.modifier_name AS otherProduct_modifier_name, " +
                            "gp.gmt_create AS otherProduct_gmt_create, " +
                            "gp.gmt_modified AS otherProduct_gmt_modified"
            );
            FROM("goods_product p");
            LEFT_OUTER_JOIN("goods_onsale o ON p.id = o.product_id");
            LEFT_OUTER_JOIN("goods_product gp ON p.goods_id = gp.goods_id AND gp.id != p.id");
            WHERE("p.name = #{productName}");
            WHERE("o.begin_time <= #{currentTime}");
            WHERE("o.end_time >= #{currentTime}");
            WHERE("o.invalid = '0'");
            ORDER_BY("o.end_time DESC");
        }}.toString();
    }
}
