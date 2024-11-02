//School of Informatics Xiamen University, GPL-3.0 license
package cn.edu.xmu.javaee.productdemoaop.dao;

import cn.edu.xmu.javaee.core.exception.BusinessException;
import cn.edu.xmu.javaee.core.model.ReturnNo;
import cn.edu.xmu.javaee.productdemoaop.dao.bo.OnSale;
import cn.edu.xmu.javaee.productdemoaop.dao.bo.Product;
import cn.edu.xmu.javaee.productdemoaop.dao.bo.User;
import cn.edu.xmu.javaee.productdemoaop.mapper.Join.ProductJoinMapper;
import cn.edu.xmu.javaee.productdemoaop.mapper.generator.ProductPoMapper;
import cn.edu.xmu.javaee.productdemoaop.mapper.Join.po.JoinPo;
import cn.edu.xmu.javaee.productdemoaop.mapper.generator.po.ProductPo;
import cn.edu.xmu.javaee.productdemoaop.mapper.generator.po.ProductPoExample;
import cn.edu.xmu.javaee.productdemoaop.mapper.manual.ProductAllMapper;
import cn.edu.xmu.javaee.productdemoaop.mapper.manual.po.ProductAllPo;
import cn.edu.xmu.javaee.productdemoaop.util.CloneFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Ming Qiu
 **/
@Repository
public class ProductDao {

    private final static Logger logger = LoggerFactory.getLogger(ProductDao.class);

    private ProductPoMapper productPoMapper;

    private ProductJoinMapper productJoinMapper;

    private OnSaleDao onSaleDao;

    private ProductAllMapper productAllMapper;

    @Autowired
    public ProductDao(ProductPoMapper productPoMapper, OnSaleDao onSaleDao, ProductAllMapper productAllMapper, ProductJoinMapper productJoinMapper) {
        this.productPoMapper = productPoMapper;
        this.onSaleDao = onSaleDao;
        this.productAllMapper = productAllMapper;
        this.productJoinMapper = productJoinMapper;
    }

    /**
     * 用GoodsPo对象找Goods对象
     * @param name
     * @return  Goods对象列表，带关联的Product返回
     */
    public List<Product> retrieveProductByName(String name, boolean all) throws BusinessException {
        List<Product> productList = new ArrayList<>();
        ProductPoExample example = new ProductPoExample();
        ProductPoExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        List<ProductPo> productPoList = productPoMapper.selectByExample(example);
        for (ProductPo po : productPoList){
            Product product = null;
            if (all) {
                product = this.retrieveFullProduct(po);
            } else {
                product = CloneFactory.copy(new Product(), po);
            }
            productList.add(product);
        }
        logger.debug("retrieveProductByName: productList = {}", productList);
        return productList;
    }




    /**
     * 用GoodsPo对象找Goods对象
     * @param  productId
     * @return  Goods对象列表，带关联的Product返回
     */
    public Product retrieveProductByID(Long productId, boolean all) throws BusinessException {
        Product product = null;
        ProductPo productPo = productPoMapper.selectByPrimaryKey(productId);
        if (null == productPo){
            throw new BusinessException(ReturnNo.RESOURCE_ID_NOTEXIST, "产品id不存在");
        }
        if (all) {
            product = this.retrieveFullProduct(productPo);
        } else {
            product = CloneFactory.copy(new Product(), productPo);
        }

        logger.debug("retrieveProductByID: product = {}",  product);
        return product;
    }


    private Product retrieveFullProduct(ProductPo productPo) throws DataAccessException{
        assert productPo != null;
        Product product =  CloneFactory.copy(new Product(), productPo);
        List<OnSale> latestOnSale = onSaleDao.getLatestOnSale(productPo.getId());
        product.setOnSaleList(latestOnSale);

        List<Product> otherProduct = this.retrieveOtherProduct(productPo);
        product.setOtherProduct(otherProduct);

        return product;
    }

    private List<Product> retrieveOtherProduct(ProductPo productPo) throws DataAccessException{
        assert productPo != null;

        ProductPoExample example = new ProductPoExample();
        ProductPoExample.Criteria criteria = example.createCriteria();
        criteria.andGoodsIdEqualTo(productPo.getGoodsId());
        criteria.andIdNotEqualTo(productPo.getId());
        List<ProductPo> productPoList = productPoMapper.selectByExample(example);
        return productPoList.stream().map(po->CloneFactory.copy(new Product(), po)).collect(Collectors.toList());
    }

    /**
     * 创建Goods对象
     * @param product 传入的Goods对象
     * @return 返回对象ReturnObj
     */
    public Product createProduct(Product product, User user) throws BusinessException{

        Product retObj = null;
        product.setCreator(user);
        product.setGmtCreate(LocalDateTime.now());
        ProductPo po = CloneFactory.copy(new ProductPo(), product);
        int ret = productPoMapper.insertSelective(po);
        retObj = CloneFactory.copy(new Product(), po);
        return retObj;
    }

    /**
     * 修改商品信息
     * @param product 传入的product对象
     * @return void
     */
    public void modiProduct(Product product, User user) throws BusinessException{
        product.setGmtModified(LocalDateTime.now());
        product.setModifier(user);
        ProductPo po = CloneFactory.copy(new ProductPo(), product);
        int ret = productPoMapper.updateByPrimaryKeySelective(po);
        if (ret == 0 ){
            throw new BusinessException(ReturnNo.RESOURCE_ID_NOTEXIST);
        }
    }

    /**
     * 删除商品，连带规格
     * @param id 商品id
     * @return
     */
    public void deleteProduct(Long id) throws BusinessException{
        int ret = productPoMapper.deleteByPrimaryKey(id);
        if (ret == 0) {
            throw new BusinessException(ReturnNo.RESOURCE_ID_NOTEXIST);
        }
    }

    public List<Product>  findProductByName_manual(String name) throws BusinessException {
        List<Product> productList;
        ProductPoExample example = new ProductPoExample();
        ProductPoExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        List<ProductAllPo> productPoList = productAllMapper.getProductWithAll(example);
        productList =  productPoList.stream().map(o->CloneFactory.copy(new Product(), o)).collect(Collectors.toList());
        logger.debug("findProductByName_manual: productList = {}", productList);
        return productList;
    }

    public List<Product> JoinProductByName(String name)throws BusinessException{
        //返回的product列表
        List<Product>productList;
        //时间设定
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = formatter.format(now);

        // 获取数据库查询结果
        List<JoinPo>JoinPoList =productJoinMapper.NewSelectProduct(name,currentTime);
        JoinPo joinPo =JoinPoList.get(0);
        if(JoinPoList.isEmpty()){
            throw new BusinessException(ReturnNo.RESOURCE_ID_NOTEXIST,"产品名称不存在");
        }

        // 用于聚合主产品的 Map，key 是主产品的 id
        Map<Long, ProductAllPo> productMap = new HashMap<>();

        ProductAllPo productAllPo = ProductAllPo.builder()
                .id(joinPo.getId())
                .skuSn(joinPo.getSkuSn())
                .name(joinPo.getName())
                .originalPrice(joinPo.getOriginalPrice())
                .weight(joinPo.getWeight())
                .barcode(joinPo.getBarcode())
                .unit(joinPo.getUnit())
                .originPlace(joinPo.getOriginPlace())
                .creatorId(joinPo.getCreatorId())
                .modifierId(joinPo.getModifierId())
                .modifierName(joinPo.getModifierName())
                .gmtCreate(joinPo.getGmtCreate())
                .gmtModified(joinPo.getGmtModified())
                .commissionRatio(joinPo.getCommissionRatio())
                .freeThreshold(joinPo.getFreeThreshold())
                .status(joinPo.getStatus())
                .otherProduct(new ArrayList<>())   // 初始化为空列表
                .onSaleList(new ArrayList<>())     // 初始化为空列表
                .build();
        for(JoinPo po:JoinPoList){
            if (po.getOtherProduct() != null) {
                logger.debug("Adding otherProduct: {}", po.getOtherProduct());  // 打印 otherProduct 数据
                productAllPo.getOtherProduct().add(po.getOtherProduct());
            }
            if (po.getOnSaleList() != null) {
                productAllPo.getOnSaleList().add(po.getOnSaleList());
            }
        }

        List<ProductAllPo> productAllPoList = new ArrayList<>();
        productAllPoList.add(productAllPo);
        productList = productAllPoList.stream().map(o->CloneFactory.copy(new Product(), o)).collect(Collectors.toList());
        logger.debug("findProductByID_manual: product = {}", productAllPoList);
        return productList;
    }

    /**
     * 用GoodsPo对象找Goods对象
     * @param  productId
     * @return  Goods对象列表，带关联的Product返回
     */
    public Product findProductByID_manual(Long productId) throws BusinessException {
        Product product = null;
        ProductPoExample example = new ProductPoExample();
        ProductPoExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(productId);
        List<ProductAllPo> productPoList = productAllMapper.getProductWithAll(example);

        if (productPoList.size() == 0){
            throw new BusinessException(ReturnNo.RESOURCE_ID_NOTEXIST, "产品id不存在");
        }
        product = CloneFactory.copy(new Product(), productPoList.get(0));
        logger.debug("findProductByID_manual: product = {}", product);
        return product;
    }
}
