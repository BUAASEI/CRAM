<!--左侧导航组件-->
<template>
  <div class="nav-view">
    <div>
      <router-link to="/project/historys">演化历史</router-link>
    </div>
    <div :class="target === 'st'? 'locked' : ''">
      <router-link to="/project/home">场景</router-link>
    </div>
    <div :class="target === 're'? 'locked' : ''">
      <router-link to="/project/role">角色</router-link>
    </div>
    <div :class="target === 'dt'? 'locked' : ''">
      <router-link to="/project/data">数据</router-link>
    </div>
    <div :class="target === 'qs'? 'locked' : ''">
      <router-link to="/project/problem">问题</router-link>
    </div>
    <div :class="target === 'mt'? 'locked' : ''">
      <router-link to="/project/solution">解决方案</router-link>
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
