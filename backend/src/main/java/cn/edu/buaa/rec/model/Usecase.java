package cn.edu.buaa.rec.model;

import java.util.Date;

public class Usecase {

    private Long id;

    private String name;

    private Date buildTime;

    private Date updateTime;

    private Long creatorId;

    private String usestate;

    private String description;

    private String rucmSpec;

    private Long projectId;
    private String input;
    private String output;

    public Usecase(Long id, String name,Long projectId,String description,String rucmSpec,String input,String output,String dictionary ){
        this.id = id;
        this.name = name;
        this.projectId = projectId;
        this.description = description;
        this.rucmSpec = rucmSpec;
        this.input = input;
        this.output = output;
        this.dataDictionary = dictionary;
    }
    public Usecase(String name ,String description , Long creatorId , Long projectId , String input , String output , String useState ){
        this.name = name;
        this.description = description;
        this.creatorId = creatorId;
        this.projectId = projectId;
        this.input = input;
        this.output = output;
        this.usestate = useState;
    }

    public Usecase(String name,String description ,Long projectId,Long creatortId,String rucmSpec){
        this.name = name;
        this.projectId = projectId;
        this.creatorId = creatortId;
        this.description = description;
        this.rucmSpec = rucmSpec;
    }


    public Usecase(Long id,String name, String description , Long creatorId , Long projectId ,
             String input , String output ,  String useState , String rucmSoec ){
        this.id = id;
        this.description = description;
        this.creatorId = creatorId;
        this.projectId = projectId;
        this.input = input;
        this.output = output;
        this.usestate = useState;
        this.rucmSpec = rucmSoec;
    }

    public Usecase(Long id,String rucmSpec){
        this.id = id;
        this.rucmSpec = rucmSpec;
    }
    public String getInput() {
        return input;
    }

    @Override
    public String toString() {
        return "Usecase{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", buildTime=" + buildTime +
                ", updateTime=" + updateTime +
                ", creatorId=" + creatorId +
                ", usestate='" + usestate + '\'' +
                ", description='" + description + '\'' +
                ", rucmSpec='" + rucmSpec + '\'' +
                ", input='" + input + '\'' +
                ", output='" + output + '\'' +
                ", dataDictionary='" + dataDictionary + '\'' +
                '}';
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getDataDictionary() {
        return dataDictionary;
    }

    public void setDataDictionary(String dataDictionary) {
        this.dataDictionary = dataDictionary;
    }

    private String dataDictionary;

    public Usecase() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }


    public String getRucmSpec() {
        return rucmSpec;
    }

    public void setRucmSpec(String rucmSpec) {
        this.rucmSpec = rucmSpec == null ? null : rucmSpec.trim();
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

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getUsestate() {
        return usestate;
    }

    public void setUsestate(String usestate) {
        this.usestate = usestate == null ? null : usestate.trim();
    }

//    public String getRucmSpec() {
//        return rucmSpec;
//    }
//
//    public void setRucmSpec(String rucmSpec) {
//        this.rucmSpec = rucmSpec;
//    }
//
//    private String rucmSpec;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}