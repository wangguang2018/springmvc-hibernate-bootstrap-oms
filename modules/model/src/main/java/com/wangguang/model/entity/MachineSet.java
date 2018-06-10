package com.wangguang.model.entity;


import com.wangguang.model.BaseEntity;
import com.wangguang.model.enums.EnumOpenExposureSetting;
import com.wangguang.model.enums.EnumexposureMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

/**
 * 娃娃机设置
 */
@Entity
public class MachineSet extends BaseEntity {
    /**
     * 商品ID
     */
    private Integer machineId;

    private Machine machine;

    /**
     * 状态 1：等待处理，2：设置成功 3：设置失败 4:
     */
    private Integer status = 0;

    /**
     * 游戏模式
     */
    private Integer gameMode = 1;
    /**
     * 强力电压
     */
    private String strongVoltage;

    /**
     * 弱力电压
     */
    private String smallVoltage;


    /**
     * 强转弱时间
     */
    private String changeTime;

    /**
     * 中奖概率
     */
    private Integer probability = 0;


    /**
     * 主摄像头曝光
     */
    private Integer masterExposure;

    /**
     * 副摄像头曝光
     */
    private Integer slaveExposure;

    /**
     * 分辨率
     */
    private Integer encodeLevel;


    /**
     * 曝光值开关
     *@see com.wangguang.model.enums.EnumOpenExposureSetting
     */
    private Integer openExposureSetting = EnumOpenExposureSetting.OFF.value;

    /**
     * 曝光选项
     * @see com.wangguang.model.enums.EnumexposureMode
     */
    private Integer exposureMode = EnumexposureMode.userDefined.getValue();


    /**
     * 主摄像头亮度(0-255)
     */
    private Integer masterBrightness;

    /**
     * 从摄像头亮度(0-255)
     */
    private Integer slaveBrightness;

    /**
     * 主摄像头饱和度(0-255)
     */
    private Integer masterSaturability;

    /**
     * 从摄像头饱和度(0-255)
     */
    private Integer slaveSaturability;

    /**
     *自定义分辨率宽度
     */
    private Integer width;

    /**
     *自定义分辨率高度
     */
    private Integer height;

    /**
     * 是否使用测试流
     * @see com.wangguang.model.enums.TestStreamSwitchEnum
     */
    private Integer testStreamSwitch;

    /**
     * 旋转角度
     * @see com.wangguang.model.enums.EnumStreamAngleOfRotationType
     */
    private Integer rotationAngle;

    /**
     * 到顶转弱抓力: 0关闭，1开启
     */
    private Byte changeWeak;




    public MachineSet(){

    }

    public Byte getChangeWeak() {
        return changeWeak;
    }

    public void setChangeWeak(Byte changeWeak) {
        this.changeWeak = changeWeak;
    }

    public Integer getTestStreamSwitch() {
        return testStreamSwitch;
    }

    public void setTestStreamSwitch(Integer testStreamSwitch) {
        this.testStreamSwitch = testStreamSwitch;
    }

    public Integer getRotationAngle() {
        return rotationAngle;
    }

    public void setRotationAngle(Integer rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    public Integer getOpenExposureSetting() {
        return openExposureSetting;
    }

    public void setOpenExposureSetting(Integer openExposureSetting) {
        this.openExposureSetting = openExposureSetting;
    }

    public Integer getExposureMode() {
        return exposureMode;
    }

    public void setExposureMode(Integer exposureMode) {
        this.exposureMode = exposureMode;
    }

    public Integer getMasterBrightness() {
        return masterBrightness;
    }

    public void setMasterBrightness(Integer masterBrightness) {
        this.masterBrightness = masterBrightness;
    }

    public Integer getSlaveBrightness() {
        return slaveBrightness;
    }

    public void setSlaveBrightness(Integer slaveBrightness) {
        this.slaveBrightness = slaveBrightness;
    }

    public Integer getMasterSaturability() {
        return masterSaturability;
    }

    public void setMasterSaturability(Integer masterSaturability) {
        this.masterSaturability = masterSaturability;
    }

    public Integer getSlaveSaturability() {
        return slaveSaturability;
    }

    public void setSlaveSaturability(Integer slaveSaturability) {
        this.slaveSaturability = slaveSaturability;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getMasterExposure() {
        return masterExposure;
    }

    public void setMasterExposure(Integer masterExposure) {
        this.masterExposure = masterExposure;
    }

    public Integer getSlaveExposure() {
        return slaveExposure;
    }

    public void setSlaveExposure(Integer slaveExposure) {
        this.slaveExposure = slaveExposure;
    }

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "machine_id", insertable = false, updatable = false,referencedColumnName = "id")
    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public Integer getEncodeLevel() {
        return encodeLevel;
    }

    public void setEncodeLevel(Integer encodeLevel) {
        this.encodeLevel = encodeLevel;
    }

    @Column(name="machine_id")
    public Integer getMachineId() {
        return machineId;
    }


    public void setMachineId(Integer machineId) {
        this.machineId = machineId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getGameMode() {
        return gameMode;
    }

    public void setGameMode(Integer gameMode) {
        this.gameMode = gameMode;
    }

    public String getStrongVoltage() {
        return strongVoltage;
    }

    public void setStrongVoltage(String strongVoltage) {
        this.strongVoltage = strongVoltage;
    }

    public String getSmallVoltage() {
        return smallVoltage;
    }

    public void setSmallVoltage(String smallVoltage) {
        this.smallVoltage = smallVoltage;
    }

    public String getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }

    public Integer getProbability() {
        return probability;
    }

    public void setProbability(Integer probability) {
        this.probability = probability;
    }

}
