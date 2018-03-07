<!--窗口组件-->
<template>
  <div class="view">
    <div class="thead">
      <div class="thead-left">站内信</div>
      <div class="thead-middle">项目名称</div>
      <select v-model="project" class="thead-button">
        <option v-for="item in Projects" v-bind:value="item.id" @selectProject="selectProject" :data="item.id">{{item.name}}</option>
      </select>
      <div @click="close" class="thead-right">X</div>
    </div>
    <div class="detail-body">
    <div class="detail-head">
      <div class="col-users">用户名</div>
      <div class="col-roles">申请角色</div>
      <!--<div class="col-domain">熟悉领域</div>-->
      <!--<div class="col-exp">项目经验</div>-->
      <div class="col-operate">是否同意</div>
    <div class="detail-context">
      <div class="detail-item" v-for="item in Message1" :key=item.id>
        <div class="col-users">{{ item.userName }}</div>
        <div class="col-roles">{{ item.roleName }}</div>
        <!--<div class="col-domain">{{item.familiarDomain}}</div>-->
        <!--<div class="col-exp">{{item.projectExp}}</div>-->
        <div class="col-operate">
            <input  id="boxId1" type="checkBox" v-bind:value="item.agree"/>
            <span class="col-name">Y</span>
            <input  id="boxId2" type="checkBox" v-bind:value="item.disagree"/>
            <span class="col-name">N</span>
          <!--</div>-->
        </div>
      </div>
    </div>
      <div class="box3">
      <Button @click="reset">重置</Button>
      <Button>确定</Button>
      </div>
    </div>
  </div>
  </div>
</template>
<style scoped>
  .view {
    width: 60%;
    margin: 80px auto;
    background-color: wheat;
    z-index: 1;
  }
  .thead {
    display: flex;
    justify-content: space-between;
    padding: 5px;
    font-size: 16px;
    text-align: center;
    border-bottom: 1px solid gray;
    overflow: auto;
  }
  .thead-left {
    color: blue;
  }
  .thead-middle{
    color:blue;
  }
  .thead-right {
    cursor: pointer;
    color: red;
  }
  .thead-button{
    margin:5px;
  }

  .col-name {
    margin-left: 5px;
  }
  .box3 {
    position: relative;
    padding-bottom: 10px;
  }
  .context-title>div {
    margin-top: 10px;
  }
  .detail-body {
    margin-top: 20px;
  }
  .detail-head,.detail-item {
    display: flex;
    justify-content: flex-start;
  }
  .detail-head {
    height: 50px;
    line-height: 50px;
    background-color: lightgray;
  }
  .detail-head>div {
    padding: 0 10px;
    border-left: 1px solid #fff;
  }
  .detail-context {
    max-height: 240px;
    overflow: auto;
  }
  .detail-item {
    height: 40px;
    line-height: 40px;
    border-bottom: 1px solid lightgray;
  }
  .detail-item>div {
    padding: 0 10px;
    border-left: 1px solid lightgray;
  }
  .col-users {
    width: 30%;
  }
  .col-roles {
    width: 30%;
  }
  .col-operate {
    width: 40%;
  }

  .col-domain{
    width: 20%;
  }
  .col-exp{
    width:20%;
  }

</style>
<script>
  import {CheckboxGroup, Checkbox, Button} from 'iview'
  let a1=   [
    {
      userName:'asd',
      projectName: '预选课',
      roleName:'zxc',
      id:2

    }

  ]
  export default{

      data () {
      return {
        Projects:[
          {
            id:'',
            name:'',

        }],
        Message1:a1,
        Message2:[]
      }
    },
    components: {
      CheckboxGroup,
      Checkbox,
      Button
    },
    mounted(){
      // this.initData();
    },
    methods: {
      initData()  {
        var userId = localStorage.getItem('id');
        this.$http.post('', info)
          .then((response) => {

            this.Projects = response.data;
            // var applyRole = response.data.applyRole;
            // //需要加上Id，怎樣處理checkbox
            // for(var i=0;i<applyRole.length;i++){
            //   var role = apllyRole[i].split(',');
            //
            //   this.Message1.userName = role[0];
            //   this.Message1.roleName = role[1];
            //   this.Message1.projectName = role[2];
            // }
            //
            // var applyMan = response.data.applyMan;
            // for(var i=0;i<applyMan.length;i++){
            //   var man = apllyMan[i].split(',');
            //   this.Message2.userName = man[0];
            //   this.Message2.Manager = man[1];
            //   this.Message2.projectName = man[2];
            // }
          })
      },
      selectProject(data){

        this.$http.post('sysuser/mail/applyinfo', {projectId:data})
          .then((response) => {
              this.Message1 = response.data.Role;
              this.Message2 = response.data.Man;

          })

      },
      reset () {


      },
      submit () {
        let body = {

        }
        /*ajax*/
        this.$http.post('sysuser/',body)
          .then((response) => {
            confirm(response.data.Msg);
          })

      },
      close () {
        this.$emit('closeIbox', 4)
      }
    }
  }
</script>
