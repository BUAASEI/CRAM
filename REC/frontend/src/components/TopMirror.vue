<template>
  <div>
    <div class="basic title">
      <span>score >></span>
      <span class="focus-blue">{{ name }}</span>
    </div>
    <div class="basic info">
      <div class="left">
      </div>
      <div class="right">
        <div class="right-name">{{ kind }}：{{ name }}</div>
        <div class="right-tag">
          <span class="focus-blue">{{ creator }}</span>
          <span>当前角色：{{ role }} </span>
          <span class="focus-btn">转换角色</span>
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
      default: '用况场景'
    },
    name: {
      type: String,
      default: '北京大学学生选课系统'
    },
    creator: {
      type: String,
      default: 'dean'
    },
    role: {
      type: String,
      default: '校教务部'
    }
  },
  components: {
  },
  methods: {
  }
}
</script>
