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
        <!--<div class="right-tag">-->
          <!--<span class="focus-btn">领域</span>-->
          <!--<span class="focus-btn">项目</span>-->
          <!--<span class="focus-btn">数据</span>-->
        <!--</div>-->
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
        default: '主页'
      },
      name: {
        type: String,
        default: '苏若'
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
    }
  }
</script>
