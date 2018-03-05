package cn.edu.buaa.rec.model;

import java.util.Date;

public class Project {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project.id
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project.name
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project.description
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project.build_time
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    private Date buildTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project.update_time
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project.creator_id
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    private Long creatorId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project.domain_id
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    private Long domainId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project.function
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    private String function;

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", buildTime=" + buildTime +
                ", updateTime=" + updateTime +
                ", creatorId=" + creatorId +
                ", domainId=" + domainId +
                ", function='" + function + '\'' +
                '}';
    }

    public Project() {
        System.out.println(1);
    }

    public Project(String name, String description, Long domainId, Long creatorId) {
        System.out.println(2);
        this.name = name;
        this.description = description;
        this.domainId = domainId;
        this.creatorId = creatorId;
    }

    public Project(Long id, String name, String description, Date buildTime, Date updateTime, Long creatorId, Long domainId, String function) {
        System.out.println(3);
        this.id = id;
        this.name = name;
        this.description = description;
        this.buildTime = buildTime;
        this.updateTime = updateTime;
        this.creatorId = creatorId;
        this.domainId = domainId;
        this.function = function;
    }

    public Project(Long id, String name, String description, java.sql.Date buildTime, java.sql.Date updateTime, Long creatorId, Long domainId, String function) {
        System.out.println(4);
        this.id = id;
        this.name = name;
        this.description = description;
        this.buildTime = buildTime;
        this.updateTime = updateTime;
        this.creatorId = creatorId;
        this.domainId = domainId;
        this.function = function;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project.id
     *
     * @return the value of project.id
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project.id
     *
     * @param id the value for project.id
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project.name
     *
     * @return the value of project.name
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project.name
     *
     * @param name the value for project.name
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project.description
     *
     * @return the value of project.description
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project.description
     *
     * @param description the value for project.description
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project.build_time
     *
     * @return the value of project.build_time
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    public Date getBuildTime() {
        return buildTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project.build_time
     *
     * @param buildTime the value for project.build_time
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    public void setBuildTime(Date buildTime) {
        this.buildTime = buildTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project.update_time
     *
     * @return the value of project.update_time
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project.update_time
     *
     * @param updateTime the value for project.update_time
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project.creator_id
     *
     * @return the value of project.creator_id
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    public Long getCreatorId() {
        return creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project.creator_id
     *
     * @param creatorId the value for project.creator_id
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    public void setCreatorId(Long creatorId) {
        System.out.println("set creator");
        this.creatorId = creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project.domain_id
     *
     * @return the value of project.domain_id
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    public Long getDomainId() {
        return domainId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project.domain_id
     *
     * @param domainId the value for project.domain_id
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    public void setDomainId(Long domainId) {
        this.domainId = domainId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project.function
     *
     * @return the value of project.function
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    public String getFunction() {
        return function;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project.function
     *
     * @param function the value for project.function
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    public void setFunction(String function) {
        this.function = function == null ? null : function.trim();
    }

}