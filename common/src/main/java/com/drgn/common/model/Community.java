package com.drgn.common.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 社区信息
 *
 * @author wydrgn
 * @date 2021/7/24 11:27
 */
@TableName("clefki.community")
public class Community {
    @TableId("id")
    private String id;
    @TableField("name")
    private String name;// 社区名称id

    // TODO: 2021/7/24 其他补充数据


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
