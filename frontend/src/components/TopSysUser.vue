<template>
  <div>
    <div class="basic title">
      <span>cram >></span>
      <span class="focus-blue">{{ name }}</span>
    </div>
    <div class="basic info">
      <div class="left">
      </div>
      <div class="right">
        <div class="right-name">{{ kind }}</div>
        <div class="right-tag">
          <span @click="postIbox(1)" class="focus-btn">修改个人信息</span>
          <span @click="postIbox(2)" class="focus-btn">新建领域</span>
          <span @click="postIbox(3)" class="focus-btn">新建项目</span>
          <!--<span @click="postIbox(4)" class="focus-btn">站内信</span>-->
          <router-link to="/manager">管理中心</router-link>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped>
  .basic {
    text-align: left;
    border-bottom: 1px solid lightgray;
  }
  .focus-blue {
    color: blue;
  }
  .focus-btn {
    padding: 5px;
    cursor: pointer;
    color: #fff;
    background-color: blue;
  }
  .title {
    height: 30px;
    line-height: 30px;
  }
  .info {
    padding-top: 20px;
    height: 120px;
    display: flex;
    justify-content: space-between;
  }
  .left {
    width: 100px;
    height: 100px;
    background-image: url('../assets/slogo.jpg');
    background-size: cover;
  }
  .right {
    padding-left: 50px;
    flex-grow: 1;
  }
  .right-name {
    height: 80%;
    font-size: 36px;
  }
  .right-tag {
    height: 20%;
  }
  .right-tag>span {
    margin-right: 40px;
  }
</style>
<script>
  export default{
    data () {
      return {
      }
    },
    props: {
      kind: {
        type: String,
        default: '个人中心'
      },
      name: {
        type: String,
        default: 'buaa'
      },
      creator: {
        type: String,
        default: 'buaa'
      },
      role: {
        type: String,
        default: '校教务部'
      }
    },
    components: {
    },
    methods: {
      // 调用新建项目弹窗
      postIbox (idx) {
        this.$emit('showIbox', idx)
      }
    }
  }
</script>
