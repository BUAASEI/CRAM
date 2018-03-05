package cn.edu.buaa.rec.model;

public class UserProjectMan {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_project_man.id
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_project_man.user_id
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    private Long userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_project_man.project_id
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    private Long projectId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_project_man.is_man
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    private Integer isMan;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_project_man.isApproved
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    private Integer isapproved;

    public UserProjectMan() {
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_project_man.id
     *
     * @return the value of user_project_man.id
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_project_man.id
     *
     * @param id the value for user_project_man.id
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_project_man.user_id
     *
     * @return the value of user_project_man.user_id
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_project_man.user_id
     *
     * @param userId the value for user_project_man.user_id
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_project_man.project_id
     *
     * @return the value of user_project_man.project_id
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    public Long getProjectId() {
        return projectId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_project_man.project_id
     *
     * @param projectId the value for user_project_man.project_id
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_project_man.is_man
     *
     * @return the value of user_project_man.is_man
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    public Integer getIsMan() {
        return isMan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_project_man.is_man
     *
     * @param isMan the value for user_project_man.is_man
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    public void setIsMan(Integer isMan) {
        this.isMan = isMan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_project_man.isApproved
     *
     * @return the value of user_project_man.isApproved
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    public Integer getIsapproved() {
        return isapproved;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_project_man.isApproved
     *
     * @param isapproved the value for user_project_man.isApproved
     *
     * @mbggenerated Wed Feb 28 00:40:34 CST 2018
     */
    public void setIsapproved(Integer isapproved) {
        this.isapproved = isapproved;
    }
}