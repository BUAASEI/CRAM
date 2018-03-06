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
      <div class="detail-item" v-for="item in Message" :key=item.id>
        <div class="col-users">{{ item.userName }}</div>
        <div class="col-projects">{{ item.projectName }}</div>
        <div class="col-roles">{{ item.roleName }}</div>
        <div class="col-operate">
        <input  id="boxId" type="checkBox" v-bind:value="item.id"/>
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
        Message:a1
      }
    },
    components: {
      CheckboxGroup,
      Checkbox,
      Button
    },
    mounted:{
      // this.initData();
    },
    methods: {
      initData()  {
        var userId = localStorage.getItem('id');
        $http.post('', info)
          .then((response) => {
            this.Message = respone.data;
          })
      },
      reset () {
        this.userid = null
        this.name = null
        this.phone = null
        this.pw = null
        this.mail = null
      },
      submit () {
        let body = {
          UserId: this.userid,
          Name: this.name,
          Phone: this.phone,
          Email: this.mail,
          Password: this.pw,
          CreatorId: 1/*登陆时获得*/
        }
        /*ajax*/
      },
      close () {
        this.$emit('closeIbox', 4)
      }
    }
  }
</script>
