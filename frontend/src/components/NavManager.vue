<!--左侧导航组件-->
<template>
  <div class="nav-view">
    <div :class="target === 'st'? 'locked' : ''">
      <router-link to="">角色申请</router-link>
    </div>
    <div>
      <router-link to="">管理员申请</router-link>
    </div>
    <div>
      <router-link to="">项目申请</router-link>
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
