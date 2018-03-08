<!-- 这个页面不用了，不过暂时-->
<template>
  <div>
    <TopScenario kind="业务活动" :name="name"></TopScenario>
    <div class="top-btn">
      <div>
        <button>演化历史</button>
        <button>演化</button>
      </div>
      <div>
        <button>更新</button>
        <button>取消</button>
      </div>
    </div>
    <div class="box1">
      <div class="ylevel">
        <label for="name">业务活动名称:</label>
        <input class="xlevel" id="name">
      </div>
      <div class="ylevel">
        <label for="prior">优先级:</label>
        <select class="xlevel" id="prior">
          <option>高</option>
          <option>低</option>
        </select>
      </div>
      <div class="ylevel">
        <label for="precon">前提条件:</label>
        <input  class="xlevel" id="precon">
      </div>
      <div class="ylevel">
        <label for="postcon">结束条件:</label>
        <input class="xlevel"  id="postcon">
      </div>
      <div class="ylevel">
        <label for="role">相关角色:</label>
        <input class="xlevel" id="role">
      </div>
      <div class="ylevel">
        <label for="data">相关数据:</label>
        <input class="xlevel" id="data">
      </div>
      <div class="ylevel">
        <label for="discribe">描述:</label>
        <textarea class="xlevel" id="discribe"></textarea>
      </div>
    </div>
    <div class="box2">
      <div class="up"><span>相关业务场景：</span></div>
      <p>
        <span class="focus-color">选课（预选）</span>
      </p>
    </div>
    <div class="box3">
      <div class="up"><span>评论：</span></div>
      <p>
        <button>发表评论</button>
      </p>
    </div>
  </div>
</template>
<style scoped>
  button {
    margin-right: 20px;
    padding: 5px;
    color: #fff;
    background-color: blue;
    outline: none;
  }
  p {
    height: 30px;
  }
  .top-btn{
    display: flex;
    justify-content: space-between;
    align-items: center;
    height: 60px;
    border-bottom: 1px solid lightgray;
  }
  .focus-color {
    cursor: pointer;
    color: blue;
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
    width: 500px;
  }
  .box2, .box3 {
    padding: 20px 0;
    text-align: left;
    border-bottom: 1px solid lightgray;
  }
  .up {
    height: 40px;
    font-size: 16px;
    font-weight: bold;
  }
</style>
<script>
  import TopScenario from '@/components/TopScenario'
  import {Button} from 'iview'
  export default{
    data () {
      return {
        type: '',
        name: '',
        infos: [
          {
            role: '查找某些数据',
            system: '展示该课程详细信息'
          }
        ]
      }
    },
    components: {
      TopScenario,
      Button
    },
    beforeMount: function () {
      console.log(this.type)
      this.type = this.$route.params.type
      this.name = this.type === 'new' ? '新增课程信息' : '选课退课（预选）'
    },
    methods: {
      del: function (index) {
        // do something
        this.infos.splice(index, 1)
      },
      add: function () {
        this.infos.push({})
      }
    }
  }
</script>
