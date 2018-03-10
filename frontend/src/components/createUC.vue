<template>
  <div class="view">
    <div class="head">
      <span class="title"> {{ title }}</span>
      <span class="close" @click="close">X</span>
    </div>
    <div>
      <div class="row">
        <div class="colum1">Name</div>
        <div class="colum2">
          <Input v-model="name"></Input>
        </div>
      </div>
      <div class="row">
        <div class="colum1">Description</div>
        <div class="colum2">
          <Input v-model="description"></Input>
        </div>
      </div>
      <div class="row">
        <div class="colum1">Primary Actor</div>
        <div class="colum2">
          <Select v-model="actor">
            <Option v-for="item in actors" :value="item.id" :key="item.id">{{ item.name }}</Option>
          </Select>
        </div>
      </div>
      <div class="row">
        <div class="colum1">Secondary Actors</div>
        <div class="colum2">
          <Select v-model="primaryActor" multiple>
            <Option v-for="item in primarys" :value="item.id" :key="item.id">{{ item.name }}</Option>
          </Select>
        </div>
      </div>
      <div class="row">
        <div class="colum1">Dictionary</div>
        <div class="colum2">
          <Select v-model="dictionary" multiple>
            <Option v-for="item in dicts" :value="item.id" :key="item.id">{{ item.name }}</Option>
          </Select>
        </div>
      </div>
    </div>
    <div class="btn">
      <Button @click="saveData">保存</Button>
      <Button @click="cancel">取消</Button>
    </div>
  </div>
</template>
<style scoped>
  .view {
    width: 60%;
    margin: 80px auto;
    padding: 30px;
    background-color: white;
    z-index: 1;
    overflow: auto;
  }
  .head {
    padding-right: 10px;
    font-size: 18px;
    color: blue;
  }
  .head span {
    cursor: pointer;
  }
  .row {
    display: flex;
    justify-content: flex-start;
    height: 50px;
    line-height: 50px;
  }
  .colum1 {
    width: 25%;
    text-align: left;
  }
  .colum2 {
    flex-grow: 1;
  }
  .btn {
    margin-top: 20px;
  }
  .close {
    float: right;
  }
</style>
<script>
  import {Button,　Select, Option, Input} from 'iview'
  export default{
    data () {
      return {
        actor: [],
        primarys: [],
        dicts: [],
        name: null,
        description: null,
        actor: null,
        primaryActor: [],
        dictionary: []
      }
    },
    props: {
      title: {
        type: String,
        default: '新建用例'
      },
      pId: {
        type: Number,
        default: 1
      }
    },
    components: {
      Button,
      Select,
      Option,
      Input
    },
    mounted () {
      this.initData()
    },
    methods: {
      initData: function () { //获取role等
        this.$http.post('project/getRoleAndData', {projectId: this.pId})
          .then((response) => {
            let datas = response.data
            this.actors =  datas.role
            this.primarys = datas.role
            this.dicts = datas.data
          })
      },
      saveData: function () {
        let datas = {
          name: this.name,
          projectId:this.pId,
          description: this.description,
          primaryActorId: this.actor,
          secondaryActorIds: this.primaryActor,
          dictionary: this.dictionary,
          creatorId: localStorage.getItem("id"),
          rucmSpec: {
            Brief: {
              colum: ["Usecase Name","Brief Description","Precondition","Primary Actor","Secondary Actors","Dependency","Generalization","Input","output","DataDictionary"],
              data: [this.name, this.description, null, this.actor, this.primaryActor.join(),null,null,this.dictionary.join()]
            },
            BasicFlow: {colum:[1],data:[]},
            SpecificAlternativeFlows: [{
              colum:[1],data:[]
            }],
            GlobalAlternativeFlows: [{
              colum:[1],data:[]
            }],
            BoundedAlternativeFlows: [{
              colum:[1],data:[]
            }]
          },
          useState:'1'
        }
        this.$http.post('usecase/new', datas)
          .then((response) => {
            this.$emit("closeNew")
          })
      },
      cancel: function () {
        this.name = null
        this.description = null
        this.actor = null
        this.dictionary = []
        this.primaryActor = []
      },
      close () {
        this.$emit("closeNew")
      }
    }
  }
</script>
