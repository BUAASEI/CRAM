<!--左侧导航组件-->
<template>
  <div class="nav-view">
    <span @click="slider(idx)" v-for="(item, idx) in target" :class="pos === idx ? 'locked' : 'active'">{{ item }}</span>
  </div>
</template>
<style scoped>
  .nav-view {
    margin-top: 20px;
  }
  .nav-view span {
    display: block;
    height: 40px;
    line-height: 40px;
    text-align: left;
    cursor: pointer;
  }

  .locked {
    color: brown;
  }

  .active {
    color: blue;
  }
</style>
<script>
  export default{
    data () {
      return {
        pos: 1
      }
    },
    props: {
      target: {
        type: Array,
        default: function () {
          return ['管理的项目', '参与的项目', '其余项目']
        }
      }
    },
    components: {
    },
    methods: {
      slider (pos) {
        this.pos = pos
        this.$emit("sliderData", pos + 1)
      }
    }
  }
</script>
