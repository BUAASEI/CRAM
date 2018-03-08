<!--左侧导航组件-->
<!-- 暂时没有设计这个页面，不需要实现 -->
<template>
  <div class="nav-view">
    <div :class="target === 'st'? 'locked' : ''">
      <router-link to="">领域</router-link>
    </div>
    <div>
      <router-link to="">项目</router-link>
    </div>
    <div>
      <router-link to="">数据</router-link>
    </div>
  </div>
</template>
<style scoped>
  .nav-view>div {
    height: 40px;
    line-height: 40px;
    text-align: left;
    color: blue;
  }

  .locked a{
    color: brown;
  }

</style>
<script>
  export default{
    data () {
      return {
      }
    },
    props: {
      target: {
        type: String,
        default: 'st'
      }
    },
    components: {
    },
    methods: {
    }
  }
</script>
