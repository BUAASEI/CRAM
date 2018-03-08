<!--左侧导航组件-->
<template>
  <div class="nav-view">
    <div :class="target === 'st'? 'locked' : ''">
      <router-link to="">管理的项目</router-link>
    </div>
    <div>
      <router-link to="">参与的项目</router-link>
    </div>
    <div>
      <router-link to="">其余项目</router-link>
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
