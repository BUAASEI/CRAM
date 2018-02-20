package cn.edu.buaa.rec.model;

import java.util.Date;

public class Domain {

//    领域的id
    private Long id;

    private String name;

    private String useState;

    private Date buildTime;

    private Date updateTime;

    private Integer isapproved;

    private String description;

//    创建该领域的用户的id
    private Long creatorId;

    public Domain(String name, String description, Long id) {
        this.name = name;
        this.description = description;
        this.id = id;
    }

    public Domain(Long id, String name, String useState, Date buildTime, Date updateTime, Integer isapproved, String description, Long creatorId) {
        this.id = id;
        this.name = name;
        this.useState = useState;
        this.buildTime = buildTime;
        this.updateTime = updateTime;
        this.isapproved = isapproved;
        this.description = description;
        this.creatorId = creatorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUseState() {
        return useState;
    }

    public void setUseState(String useState) {
        this.useState = useState == null ? null : useState.trim();
    }

    public Date getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Date buildTime) {
        this.buildTime = buildTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsapproved() {
        return isapproved;
    }

    public void setIsapproved(Integer isapproved) {
        this.isapproved = isapproved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }
}