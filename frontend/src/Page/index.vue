192168.109.25:8088/cram<!--
Copyright: glee su
Author: glee su
Email: csuruo@gmail.com
Date: 2018-01
Description: 登录页面
-->

<template>
  <div>
    <div class="wbody clearfix">
      <div class="door">
        <div class="menu clearfix">
          <span class="title">multi-view Cards based Requirement Acquisition and Modelling tool</span>
          <button @click="logUp" class="up-btn" :class="!account?'btn-active':''">注册</button>
          <button @click="logIn" class="in-btn" :class="account?'btn-active':''">登录</button>
        </div>
        <div class="tabel">
          <div v-if="account">
            <signin></signin>
          </div>
          <div v-else>
            <signup></signup>
          </div>
        </div>
      </div>
      <div class="tempIn">
        <router-link to='/sysuser'>
          <Button type="primary">登录</Button>
        </router-link>
      </div>
    </div>
  </div>
</template>
<style scoped>
  .wbody {
    width: 100%;
    min-height: 900px;
    background: url("../assets/homepage.jpg") no-repeat;
    background-size: 100% 100%;
    padding-top: 10%;
    box-sizing: border-box;
  }
  .title {
    color: #28a7e1;
    text-align: center;
    font-size: 25px;
    font-weight: bold;
    margin-bottom: 100px;
  }
  .door {
    float: right;
    margin-right: 200px;
    margin-top: 100px;
    width: 500px;
  }
  button {
    width: 60px;
    height: 40px;
    font-size: 20px;
    margin-left: 1px;
    color: black;
  }
  .in-btn {
    float: right;
  }
  .up-btn {
    float: right;
  }
  .btn-active {
    color: #fff;
    background-color: #28a7e1;
  }
  .tabel {
    min-width: 400px;
    background-color: #fff;
  }
</style>
<script>
  import {Button} from 'iview'
  //从这儿开始就不显示页面了: Sin.vue中的格式不对
  import Sign from '@/components/Signin'
  import Sigp from '@/components/Signup'
  export default {
    data() {
      return {
        account:true
      }
    },
    created:function () {
      localStorage.removeItem('UserName');
      localStorage.removeItem('id');
    },
    ready:function () {
    },
    beforeDestroy:function () {
    },
    components: {
      Button,
      signin:Sign,
      signup:Sigp
    },
    methods: {
      logUp: function () {
        this.account = false;
      },
      logIn:function () {
        this.account = true;
      }
    },
    events:{
      change_acount:function(){
        this.account = true;
      }
    }
  }
</script>
