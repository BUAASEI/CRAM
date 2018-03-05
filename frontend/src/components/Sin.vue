<!--
Copyright: glee su
Author: glee su
Email: csuruo@gmail.com
Date: 2018-01
Description: 登录页
-->

<template>
  <div class="s-in">
    <p v-show="msg!=''">{{msg0}}</p>
    <p class="gap">
      <span class="text-left">用户名:&nbsp</span>
      <input v-model="user" type="text"/>
      <span class="error-msg">{{msg1}}</span>
    </p>
    <p class="gap">
      <span class="text-left">密码:&nbsp&nbsp</span>
      <input @keyup.enter="gLogin" v-model="psd" type="password"/>
      <span class="error-msg">{{msg2}}</span>
    </p>
    <p class="gap">
      <button class="go-login" @click="gLogin">登录</button>
      <a @click="findPsd" class="find-psd">忘记密码?</a>
    </p>
  </div>
</template>

<style scoped>
  .s-in{
    padding: 20px;
    border:1px solid dodgerblue;
  }
  input{
    position: absolute;
    left:80px;
    top:0;
    width:240px;
    height:30px;
    border:1px solid gray;
    border-radius: 6px;
    padding: 5px;
  }
  p{
    position: relative;
    height: 42px;
    line-height: 42px;
    margin-bottom: 10px;
  }
  .go-login{
    width:180px;
    height:30px;
    line-height: 30px;
    font-size: 20px;
    border-radius: 6px;
    background-color: rgb(30, 144, 255);
  }
  .find-psd{
    cursor: pointer;
  }
  .text-left {
    position: absolute;
    left: 0;
    line-height: 30px;
  }
</style>

<script>
    import Vue from 'vue';
    import $ from 'jquery';
    export default {
      data () {
        return {
          user:'',
          psd:'',
          msg0:'',
          msg1:'',
          msg2:'',
          msg:''
        }
      },
      ready: function() {
      },
      beforeDestroy: function() {
      },
      methods: {
        // 找回密码
        findPsd:function(){
          confirm("请联系管理员");
        },
        gLogin:function () {
          let vm =this;
          if(vm.user!=''){
            vm.msg1='';
          }
          else{
            vm.msg1='用户名不能为空';
          }
          if(vm.psd!=''){
            vm.msg2='';
          }
          else{
            vm.msg2='密码不能为空'
          }
//                发送ajax,若code=404,msg0='用户名和密码不一致'
          let info={
            UserName:vm.user,
            Password:vm.psd
          }
          if(vm.msg1==''&&vm.msg2==''){
            vm.$http.post('login', info)
              .then((response) => {
                console.log(response)
                if(response.data.Msg=='Success!'){
                  let date=vm.getDate();
                  Vue.http.headers.common['ID'] = '';
                  localStorage.setItem('UserName',vm.user);
                  localStorage.setItem('curTime',date);
                  localStorage.setItem('id',response.data.SysUserID);
                  Vue.http.headers.common.ID= localStorage.getItem('id');
                  vm.$router.push({path:'./sysuser'});
                }
                else{
                  confirm(response.data.Msg);
                }
              })
          }
        },
        // 获取时间
        getDate:function(){
          var now = new Date();
          var year = now.getFullYear();       //年
          var month = now.getMonth() + 1;     //月
          var day = now.getDate();            //日
          var hh = now.getHours();            //时
          var mm = now.getMinutes();          //分
          var ss = now.getSeconds();
          var clock = year + "-";
          if(month < 10)
            clock += "0";
          clock += month + "-";
          if(day < 10)
            clock += "0";
          clock += day + " ";
          if(hh < 10)
            clock += "0";
          clock += hh + ":";
          if (mm < 10)
            clock += "0";
          clock += mm+":";
          if(ss<0)
            clock +="0"
          clock +=ss;
          return(clock);
        }
      }
    }
</script>

