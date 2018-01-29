<!--左侧导航组件-->
<template>
  <div class="nav-view">
    <div :class="target === 'st'? 'locked' : ''">
      <router-link to="/system/stage">场景</router-link>
    </div>
    <div>
      <router-link to="">角色</router-link>
    </div>
    <div>
      <router-link to="">数据</router-link>
    </div>
    <div>
      <router-link to="">问题</router-link>
    </div>
    <div>
      <router-link to="">解决方案</router-link>
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
