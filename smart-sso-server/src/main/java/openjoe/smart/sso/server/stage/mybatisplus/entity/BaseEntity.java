package openjoe.smart.sso.server.stage.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * MybatisPlus基础持久化基类
 * * @author huynx */
public class BaseEntity extends Entity {

    public static String CREATE_TIME = "createTime";
    public static String UPDATE_TIME = "updateTime";

    @ApiModelProperty("Created Time")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    protected Date createTime;

    @ApiModelProperty("Updated Time")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    protected Date updateTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
