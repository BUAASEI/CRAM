<!--窗口组件-->
<template>
  <div class="view">
    <div class="thead">
      <div class="thead-left">站内信</div>
      <div @click="close" class="thead-right">X</div>
    </div>
    <div class="detail-body">
    <div class="detail-head">
      <div class="col-users">用户名</div>
      <div class="col-projects">项目名</div>
      <div class="col-roles">申请角色</div>
      <div class="col-operate">是否同意</div>
    </div>
    <div class="detail-context">
      <div class="detail-item" v-for="item in Message1" :key=item.id>
        <div class="col-users">{{ item.userName }}</div>
        <div class="col-projects">{{ item.projectName }}</div>
        <div class="col-roles">{{ item.roleName }}</div>
        <div class="col-operate">
          <div class = "col-agree">
            <input  id="boxId1" type="checkBox" v-bind:value="item.agree"/>
            <span class="col-name">Y</span>
          </div>
          <div class="col-disagree">
            <input  id="boxId2" type="checkBox" v-bind:value="item.disagree"/>
            <span class="col-name">N</span>
          </div>
        </div>
      </div>
    </div>
      <div class="box3">
      <Button @click="reset">重置</Button>
      <Button>确定</Button>
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
    padding: 10px;
    font-size: 16px;
    text-align: center;
    border-bottom: 1px solid gray;
  }
  .thead-left {
    color: blue;
  }
  .thead-right {
    cursor: pointer;
    color: red;
  }
  .box1 {
    position: relative;
    margin: 20px 0;
    height: 300px;
    text-align: left;
  }
  .ylevel {
    padding: 10px;
  }
  .ylevel label {
    color: gray;
  }
  .xlevel {
    position: absolute;
    left: 100px;
    width: 400px;
  }
  .text {
    height: 150px;
  }
  .box2 {
    min-height: 50px;
    max-height: 200px;
    text-align: left;
    color: gray;
    overflow: auto;
  }
  .box-head, .info-item {
    display: flex;
    justify-content: flex-start;
    padding-left: 10px;
  }
  .box-body {
    padding-top: 10px;
  }
  .col-id {
    margin-left: 20px;
  }
  .col-name {
    margin-left: 50px;
  }
  .box3 {
    padding-bottom: 20px;
  }

  .context-title {
    width: 15%;
    display: flex;
    flex-direction: column;
    align-items: flex-start;
  }
  .context-title>div {
    margin-top: 10px;
  }
  .context-detail {
    flex-grow: 1;
  }
  .detail {
    padding-top: 10px;
    text-align: left;
    height: 300px;
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
    width: 25%;
  }
  .col-roles {
    width: 25%;
  }
  .col-projects {
    width: 15%;
  }
  .col-operate {
    width: 25%;
  }

  .col-disagree{
    width:12%;
  }

</style>
<script>
  import {CheckboxGroup, Checkbox, Button} from 'iview'
  let a1=   [
    {
      userName:'asd',
      projectName: '预选课',
      roleName:'zxc',
      id:'2'

    }

  ]
  export default{

      data () {
      return {
        Message1:[],
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
        $http.post('', info)
          .then((response) => {
            var applyRole = response.data.applyRole;
            //需要加上Id，怎樣處理checkbox
            for(int i=0;i<applyRole.length;i++){
              var role = apllyRole[i].split(',');

              this.Message1.userName = role[0];
              this.Message1.roleName = role[1];
              this.Message1.projectName = role[2];
            }

            var applyMan = response.data.applyMan;
            for(int i=0;i<applyMan.length;i++){
              var man = apllyMan[i].split(',');
              this.Message2.userName = man[0];
              this.Message2.Manager = man[1];
              this.Message2.projectName = man[2];
            }
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
