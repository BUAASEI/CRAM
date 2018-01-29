<!--窗口组件-->
<template>
  <div>
    <div class="thead">
      <div>新建项目</div>
      <div>X</div>
    </div>
    <div class="box1">
      <div class="ylevel">
        <label for="name">项目名称:</label>
        <input class="xlevel" id="name">
      </div>
      <div class="ylevel">
        <label for="discribe">项目描述:</label>
        <textarea class="xlevel" id="discribe"></textarea>
      </div>
    </div>
    <div class="box2">
      <div class="thead">选择所属领域</div>
      <div class="box-body">
        <div class="box-head">
          <div>ID</div>
          <div>名称</div>
        </div>
        <div class="box-context">
          <div></div>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped>
  textarea {
    height: 300px;
  }
  .box1 {
    position: relative;
    margin: 20px 0;
    text-align: left;
  }
  .ylevel {
    padding: 10px;
  }
  .ylevel label {
    color: gray;
  }
  .xlevel {
    position: absolute;
    left: 100px;
    width: 300px;
  }

</style>
<script>
  export default{
    data () {
      return {
      }
    },
    components: {
    },
    methods: {
    }
  }
</script>
