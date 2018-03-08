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
        <div class="right-name">{{ kind }}：{{ name }}</div>
        <div class="right-tag">
          <span>当前项目：{{ projectname }}</span>
          <span class="focus-blue">切换项目
            <select v-model="selected">
              <option v-for="item in other_projects" v-bind:value="item">{{item}}</option>
            </select>
          </span>
          <router-link to="/sysuser">个人中心</router-link>
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
        default: '管理中心'
      },
      name: {
        type: String,
        default: '北航学生选课系统'
      },
      projectname: {
        type: String,
        default: '选课系统'
      },
      other_projects: {
        type: Array,
        default: ['选课系统','电梯系统','版本控制系统']
      }
    },
    components: {
    },
    methods: {
    }
  }
</script>
