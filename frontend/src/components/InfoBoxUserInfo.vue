<!-- 窗口组件 -->
<!-- 修改个人信息 -->
<template>
  <div class="view">
    <div class="thead">
      <div class="thead-left">修改个人信息</div>
      <div @click="close" class="thead-right">X</div>
    </div>
    <div class="box1">
        <input v-model="id" id="id" type="hidden">
      <div class="ylevel">
        <label for="name">用户名:</label>
        <input v-model="name" class="xlevel" id="name" disabled="disabled">
      </div>
      <div class="ylevel">
        <label for="phone">电话:</label>
        <input v-model="phone" class="xlevel" id="phone">
      </div>
      <div class="ylevel">
        <label for="mail">邮箱:</label>
        <input v-model="mail" class="xlevel" id="mail">
      </div>
      <div class="ylevel">
        <label for="pw">密码:</label>
        <input v-model="pw" class="xlevel" type="password" id="pw">
      </div>
      <div class="ylevel">
        <label for="familiarDomain">领域:</label>
        <input v-model="familiarDomain" class="xlevel"  id="familiarDomain">
      </div>
      <div class="ylevel">
        <label for="projectExp">项目经验:</label>
        <textarea v-model="projectExp" class="xlevel text" id="projectExp" rows="2"></textarea>
      </div>
    </div>
    <div class="box3">
      <Button @click="reset">重置</Button>
      <Button @click="submit">确定</Button>
    </div>
  </div>
</template>
<style scoped>
  .view {
    width: 60%;
    margin: 80px auto;
    background-color: wheat;
    z-index: 1;
    overflow: auto;
  }
  .thead {
    display: flex;
    justify-content: space-between;
    padding: 10px;
    font-size: 16px;
    text-align: center;
    border-bottom: 1px solid gray;
    overflow: auto;
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
    height: 200px;
    text-align: left;
    overflow: auto;
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
    overflow: auto;
  }
  /*.box2 {*/
    /*min-height: 100px;*/
    /*max-height: 220px;*/
    /*text-align: left;*/
    /*color: gray;*/
    /*overflow: auto;*/
  /*}*/
  /*.box-head, .info-item {*/
    /*display: flex;*/
    /*justify-content: flex-start;*/
    /*padding-left: 10px;*/
  /*}*/
  /*.box-body {*/
    /*padding-top: 10px;*/
  /*}*/
  /*.col-id {*/
    /*margin-left: 20px;*/
  /*}*/
  /*.col-name {*/
    /*margin-left: 50px;*/
  /*}*/
  .box3 {
    position: relative;
    padding-bottom: 20px;
  }

</style>
<script>
  import {CheckboxGroup, Checkbox, Button} from 'iview'
  export default{
    data () {
      return {
        id:'',
        name:'',
        phone:'',
        mail:'',
        password:'',
        familiarDomain:'',
        projectExp:'',
      }
    },
    components: {
      CheckboxGroup,
      Checkbox,
      Button
    },
    mounted() {
      this.initData();
    },
    methods: {
      initData(){
         var userId = localStorage.getItem("id");
        this.$http.post('sysuser/getuser',{"userId":userId})
          .then((response) => {
            var user = response.data;
            this.id = user.id;
            this.name = user.name;
            this.phone = user.phone;
            this.mail = user.email;
            this.pw = user.password;
            this.familiarDomain = user.familiardomain;
            this.projectExp = user.projectexp;
          })
      },
      reset () {
        this.phone = null
        this.pw = null
        this.mail = null
        this.familiarDomain =null;
        this.projectExp = null;
      },
      submit () {
        let body = {
          UserId: this.id,
          Name: this.name,
          Phone: this.phone,
          Email: this.mail,
          Password: this.pw,
          FamiliarDomain:this.familiarDomain,
          ProjectExp:this.projectExp
        };
        /*ajax*/
        this.$http.post('sysuser/modinfo',body)
          .then((response) => {
              confirm(response.data.Msg);

          })
      },
      close () {
        this.$emit('closeIbox', 1)
      }
    }
  }
</script>
