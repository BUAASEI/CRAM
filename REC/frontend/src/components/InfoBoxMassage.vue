<!--窗口组件-->
<template>
  <div class="view">
    <div class="thead">
      <div class="thead-left">站内信</div>
      <div @click="close" class="thead-right">X</div>
    </div>
    <div class="box1">
      <div class="ylevel">
        <label for="applyid">申请ID:</label>
        <input v-model="applyid" class="xlevel" id="applyid">
      </div>
      <div class="ylevel">
        <label for="projectname">项目名称:</label>
        <input v-model="projectname" class="xlevel" id="projectname">
      </div>
      <div class="ylevel">
        <label for="projectrole">项目角色:</label>
        <input v-model="projectrole" class="xlevel" id="projectrole">
      </div>
      <div class="ylevel">
        <label for="name">用户名:</label>
        <input v-model="name" class="xlevel" id="name">
      </div>
      <div class="ylevel">
        <label for="projectexp">项目经验:</label>
        <textarea v-model="projectexp" class="xlevel text" id="projectexp" rows="4"></textarea>
      </div>
      <div class="ylevel">
        <label for="familiardomain">熟悉领域:</label>
        <textarea v-model="familiardomain" class="xlevel text" id="familiardomain" rows="4"></textarea>
      </div>
    </div>
    <div class="box3">
      <Button @click="reset">重置</Button>
      <Button>确定</Button>
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
    min-height: 100px;
    max-height: 220px;
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

</style>
<script>
  import {CheckboxGroup, Checkbox, Button} from 'iview'
  export default{
    data () {
      return {
        infos: [/*后台获取*/
          {
            id: 1,
            name: 's'
          },
          {
            id: 2,
            name: 'x'
          }
        ],
        name: null,
        discribe: null,
        vertical: []
      }
    },
    components: {
      CheckboxGroup,
      Checkbox,
      Button
    },
    methods: {
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
