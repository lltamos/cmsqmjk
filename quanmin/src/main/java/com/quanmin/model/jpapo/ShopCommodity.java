package com.quanmin.model.jpapo;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by yang on 2017/7/24.
 */
@Entity
@Table(name = "shop_commodity")
public class ShopCommodity {

    private Long id;
    private String name;
    private Double price;
    private Integer totalNum;//库存
    private Integer totalSalesNum;//卖出总数
    private Long specificationId;
    private String specificationName;
    private Long colorId;
    private String colorName;
    private Long sizeId;
    private String sizeName;//尺寸
    private String modelName;//型号
    private Integer status;//1正常，2 售罄,3下架
    private Date statusCreateTime;
    private Date statusUpdateTime;
    private Long areaProvinceId;
    private String areaProvinceName;
    private Long areaCityId;
    private String areaCityName;
    private String number;//编号
    private String company;//供应商
    private String lableId;
    private String lableName;
    private String info;
    private String coverUrl;//图片地址
    private String detailUrl;
    private String parameterUrl;
    private String publicCommodityHardUrl;
    private String packingList;
    private String afterSalesService;
    private String directions;
    private String publicCommodityMedicineUrl;
    private String alias;
    private Long categoryId;
    private String categoryName;
    private String position;
    private String shape;
    private String machining;
    private String taste;
    private String meridian;
    private String effect;
    private String useing;
    private String taboo;
    private Integer type;
    private Integer delStatus;
    private Date createTime;
    private Date updateTime;
    private String ext1;
    private String ext2;
    private String ext3;
    private String parametersProductName;//商品名称
    private String parametersBrand;//品牌
    private String parametersOrigin;//产地
    private String parametersMaterial;//材质
    private String parametersWaterproofLevel;//防水等级
    private String parametersBattery;//电池
    private String parametersStandbyTime;//待机时间
    private String parametersStorageTemperature;//存储温度
    private String parametersBluetooth;//蓝牙
    private String parametersProcessor;//处理器
    private String parametersScreenSize;//屏幕尺寸
    private String parametersScreenMaterial;//屏幕材质
    private String parametersConnection;//连接方式
    private String parametersPowerSupply;//电源
    private String parametersProductSize;//产品尺寸
    private String parametersProductWeight;//产品重量
    private String parametersMonitoringFunction;//监测功能
    private String parametersScreenType;//屏幕类型
    private String parametersMedicalDeviceRegistrationNumber;//医疗器械注册编号
    private String parametersExecutionStandardNumber;//执行标准号
    private String parametersOtherparameters;//其他参数


    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 1024)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "price", nullable = true)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "total_num", nullable = true)
    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    @Basic
    @Column(name = "total_sales_num", nullable = true)
    public Integer getTotalSalesNum() {
        return totalSalesNum;
    }

    public void setTotalSalesNum(Integer totalSalesNum) {
        this.totalSalesNum = totalSalesNum;
    }

    @Basic
    @Column(name = "specification_id", nullable = true)
    public Long getSpecificationId() {
        return specificationId;
    }

    public void setSpecificationId(Long specificationId) {
        this.specificationId = specificationId;
    }

    @Basic
    @Column(name = "specification_name", nullable = true, length = 1024)
    public String getSpecificationName() {
        return specificationName;
    }

    public void setSpecificationName(String specificationName) {
        this.specificationName = specificationName;
    }

    @Basic
    @Column(name = "color_id", nullable = true)
    public Long getColorId() {
        return colorId;
    }

    public void setColorId(Long colorId) {
        this.colorId = colorId;
    }

    @Basic
    @Column(name = "color_name", nullable = true, length = 1024)
    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    @Basic
    @Column(name = "size_id", nullable = true)
    public Long getSizeId() {
        return sizeId;
    }

    public void setSizeId(Long sizeId) {
        this.sizeId = sizeId;
    }

    @Basic
    @Column(name = "size_name", nullable = true, length = 1024)
    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    @Basic
    @Column(name = "model_name", nullable = true, length = 1024)
    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "status_create_time", nullable = true)
    public Date getStatusCreateTime() {
        return statusCreateTime;
    }

    public void setStatusCreateTime(Date statusCreateTime) {
        this.statusCreateTime = statusCreateTime;
    }

    @Basic
    @Column(name = "status_update_time", nullable = true)
    public Date getStatusUpdateTime() {
        return statusUpdateTime;
    }

    public void setStatusUpdateTime(Date statusUpdateTime) {
        this.statusUpdateTime = statusUpdateTime;
    }

    @Basic
    @Column(name = "area_province_id", nullable = true)
    public Long getAreaProvinceId() {
        return areaProvinceId;
    }

    public void setAreaProvinceId(Long areaProvinceId) {
        this.areaProvinceId = areaProvinceId;
    }

    @Basic
    @Column(name = "area_province_name", nullable = true, length = 1024)
    public String getAreaProvinceName() {
        return areaProvinceName;
    }

    public void setAreaProvinceName(String areaProvinceName) {
        this.areaProvinceName = areaProvinceName;
    }

    @Basic
    @Column(name = "area_city_id", nullable = true)
    public Long getAreaCityId() {
        return areaCityId;
    }

    public void setAreaCityId(Long areaCityId) {
        this.areaCityId = areaCityId;
    }

    @Basic
    @Column(name = "area_city_name", nullable = true, length = 1024)
    public String getAreaCityName() {
        return areaCityName;
    }

    public void setAreaCityName(String areaCityName) {
        this.areaCityName = areaCityName;
    }

    @Basic
    @Column(name = "number", nullable = true, length = 1024)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Basic
    @Column(name = "lable_id", nullable = true, length = 1024)
    public String getLableId() {
        return lableId;
    }

    public void setLableId(String lableId) {
        this.lableId = lableId;
    }

    @Basic
    @Column(name = "lable_name", nullable = true, length = 1024)
    public String getLableName() {
        return lableName;
    }

    public void setLableName(String lableName) {
        this.lableName = lableName;
    }

    @Basic
    @Column(name = "info", nullable = true, length = 1024)
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Basic
    @Column(name = "cover_url", nullable = true, length = 1024)
    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    @Basic
    @Column(name = "detail_url", nullable = true, length = 1024)
    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    @Basic
    @Column(name = "parameter_url", nullable = true, length = 1024)
    public String getParameterUrl() {
        return parameterUrl;
    }

    public void setParameterUrl(String parameterUrl) {
        this.parameterUrl = parameterUrl;
    }

    @Basic
    @Column(name = "public_commodity_hard_url", nullable = true, length = 1024)
    public String getPublicCommodityHardUrl() {
        return publicCommodityHardUrl;
    }

    public void setPublicCommodityHardUrl(String publicCommodityHardUrl) {
        this.publicCommodityHardUrl = publicCommodityHardUrl;
    }

    @Basic
    @Column(name = "packing_list", nullable = true, length = 1024)
    public String getPackingList() {
        return packingList;
    }

    public void setPackingList(String packingList) {
        this.packingList = packingList;
    }

    @Basic
    @Column(name = "after_sales_service", nullable = true, length = 1024)
    public String getAfterSalesService() {
        return afterSalesService;
    }

    public void setAfterSalesService(String afterSalesService) {
        this.afterSalesService = afterSalesService;
    }

    @Basic
    @Column(name = "directions", nullable = true, length = 1024)
    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    @Basic
    @Column(name = "public_commodity_medicine_url", nullable = true, length = 1024)
    public String getPublicCommodityMedicineUrl() {
        return publicCommodityMedicineUrl;
    }

    public void setPublicCommodityMedicineUrl(String publicCommodityMedicineUrl) {
        this.publicCommodityMedicineUrl = publicCommodityMedicineUrl;
    }

    @Basic
    @Column(name = "alias", nullable = true, length = 1024)
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Basic
    @Column(name = "category_id", nullable = true)
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "category_name", nullable = true, length = 1024)
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Basic
    @Column(name = "position", nullable = true, length = 1024)
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Basic
    @Column(name = "shape", nullable = true, length = 1024)
    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    @Basic
    @Column(name = "machining", nullable = true, length = 1024)
    public String getMachining() {
        return machining;
    }

    public void setMachining(String machining) {
        this.machining = machining;
    }

    @Basic
    @Column(name = "taste", nullable = true, length = 1024)
    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    @Basic
    @Column(name = "meridian", nullable = true, length = 1024)
    public String getMeridian() {
        return meridian;
    }

    public void setMeridian(String meridian) {
        this.meridian = meridian;
    }

    @Basic
    @Column(name = "effect", nullable = true, length = 1024)
    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }


    @Basic
    @Column(name = "taboo", nullable = true, length = 1024)
    public String getTaboo() {
        return taboo;
    }

    public void setTaboo(String taboo) {
        this.taboo = taboo;
    }

    @Basic
    @Column(name = "type", nullable = true)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "del_status", nullable = true)
    public Integer getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }

    @Basic
    @Column(name = "create_time", nullable = true)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time", nullable = true)
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "ext1", nullable = true, length = 1024)
    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    @Basic
    @Column(name = "ext2", nullable = true, length = 1024)
    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    @Basic
    @Column(name = "ext3", nullable = true, length = 1024)
    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }

    @Basic
    @Column(name = "company", nullable = true, length = 1024)
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Basic
    @Column(name = "useing", nullable = true, length = 1024)
    public String getUseing() {
        return useing;
    }

    public void setUseing(String useing) {
        this.useing = useing;
    }

    public String getParametersProductName() {
        return parametersProductName;
    }

    public void setParametersProductName(String parametersProductName) {
        this.parametersProductName = parametersProductName;
    }

    public String getParametersBrand() {
        return parametersBrand;
    }

    public void setParametersBrand(String parametersBrand) {
        this.parametersBrand = parametersBrand;
    }

    public String getParametersOrigin() {
        return parametersOrigin;
    }

    public void setParametersOrigin(String parametersOrigin) {
        this.parametersOrigin = parametersOrigin;
    }

    public String getParametersMaterial() {
        return parametersMaterial;
    }

    public void setParametersMaterial(String parametersMaterial) {
        this.parametersMaterial = parametersMaterial;
    }

    public String getParametersWaterproofLevel() {
        return parametersWaterproofLevel;
    }

    public void setParametersWaterproofLevel(String parametersWaterproofLevel) {
        this.parametersWaterproofLevel = parametersWaterproofLevel;
    }

    public String getParametersBattery() {
        return parametersBattery;
    }

    public void setParametersBattery(String parametersBattery) {
        this.parametersBattery = parametersBattery;
    }

    public String getParametersStandbyTime() {
        return parametersStandbyTime;
    }

    public void setParametersStandbyTime(String parametersStandbyTime) {
        this.parametersStandbyTime = parametersStandbyTime;
    }

    public String getParametersStorageTemperature() {
        return parametersStorageTemperature;
    }

    public void setParametersStorageTemperature(String parametersStorageTemperature) {
        this.parametersStorageTemperature = parametersStorageTemperature;
    }

    public String getParametersBluetooth() {
        return parametersBluetooth;
    }

    public void setParametersBluetooth(String parametersBluetooth) {
        this.parametersBluetooth = parametersBluetooth;
    }

    public String getParametersProcessor() {
        return parametersProcessor;
    }

    public void setParametersProcessor(String parametersProcessor) {
        this.parametersProcessor = parametersProcessor;
    }

    public String getParametersScreenSize() {
        return parametersScreenSize;
    }

    public void setParametersScreenSize(String parametersScreenSize) {
        this.parametersScreenSize = parametersScreenSize;
    }

    public String getParametersScreenMaterial() {
        return parametersScreenMaterial;
    }

    public void setParametersScreenMaterial(String parametersScreenMaterial) {
        this.parametersScreenMaterial = parametersScreenMaterial;
    }

    public String getParametersConnection() {
        return parametersConnection;
    }

    public void setParametersConnection(String parametersConnection) {
        this.parametersConnection = parametersConnection;
    }

    public String getParametersPowerSupply() {
        return parametersPowerSupply;
    }

    public void setParametersPowerSupply(String parametersPowerSupply) {
        this.parametersPowerSupply = parametersPowerSupply;
    }

    public String getParametersProductSize() {
        return parametersProductSize;
    }

    public void setParametersProductSize(String parametersProductSize) {
        this.parametersProductSize = parametersProductSize;
    }

    public String getParametersProductWeight() {
        return parametersProductWeight;
    }

    public void setParametersProductWeight(String parametersProductWeight) {
        this.parametersProductWeight = parametersProductWeight;
    }

    public String getParametersMonitoringFunction() {
        return parametersMonitoringFunction;
    }

    public void setParametersMonitoringFunction(String parametersMonitoringFunction) {
        this.parametersMonitoringFunction = parametersMonitoringFunction;
    }

    public String getParametersScreenType() {
        return parametersScreenType;
    }

    public void setParametersScreenType(String parametersScreenType) {
        this.parametersScreenType = parametersScreenType;
    }

    public String getParametersMedicalDeviceRegistrationNumber() {
        return parametersMedicalDeviceRegistrationNumber;
    }

    public void setParametersMedicalDeviceRegistrationNumber(String parametersMedicalDeviceRegistrationNumber) {
        this.parametersMedicalDeviceRegistrationNumber = parametersMedicalDeviceRegistrationNumber;
    }

    public String getParametersExecutionStandardNumber() {
        return parametersExecutionStandardNumber;
    }

    public void setParametersExecutionStandardNumber(String parametersExecutionStandardNumber) {
        this.parametersExecutionStandardNumber = parametersExecutionStandardNumber;
    }

    public String getParametersOtherparameters() {
        return parametersOtherparameters;
    }

    public void setParametersOtherparameters(String parametersOtherparameters) {
        this.parametersOtherparameters = parametersOtherparameters;
    }
}

