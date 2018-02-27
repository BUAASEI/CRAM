<template>
  <div>
    <div class="basic title">
      <span>mvrec >></span>
      <span class="focus-blue">{{ name }}</span>
    </div>
    <div class="basic info">
      <div class="left">
      </div>
      <div class="right">
        <div class="right-name">{{ kind }}：{{ name }}</div>
        <div class="right-tag">
          <span>领域：{{ domain }}</span>
          <span>创建时间：{{ dates }}</span>
          <span>创建人：{{ creator }}</span>
          <span>角色：{{ rolename }}</span>
          <span class="focus-blue">切换角色
            <select v-model="selected">
              <option v-for="item in other_roles" v-bind:value="item">{{item}}</option>
            </select>
          </span>
          <span class="focus-blue">管理员申请</span>
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
      default: '项目'
    },
    name: {
      type: String,
      default: '北航学生选课系统'
    },
    domain: {
      type: String,
      default: '高等教育'
    },
    dates: {
      type: String,
      default: '2017-4-23'
    },
    creator: {
      type: String,
      default: 'suruo'
    },
    rolename: {
      type: String,
      default: '学生'
    },
    other_roles: {
      type: Array,
      default: ['学生','老师','管理员']
    }
  },
  components: {
  },
  methods: {
  }
}
</script>
