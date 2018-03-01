<template>
  <div>
    <TopMirror kind="业务场景" :name="name"></TopMirror>
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
        <label for="name">业务场景名称:</label>
        <input class="xlevel" id="name">
      </div>
      <div class="ylevel">
        <label for="data">相关角色:</label>
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
      <div class="up"><span>相关业务活动：</span></div>
      <div class="box2-body">
          <div class="col-name">
            <span class="focus-color">选课退课（预选）</span>
          </div>
          <div class="col-operate">
            <span  class="focus-color" @click="del(index)">删除</span>
          </div>
      </div>
      <p>
        <router-link to='/project/scenario/activity'>
          <Button type="primary">新增业务活动</Button>
        </router-link>
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
    padding: 30px 0;
    text-align: left;
    border-bottom: 1px solid lightgray;
  }
  .box2-body {
    padding-top: 5px;
  }
  .col-name {
    width: 40%;
  }
  .col-operate {
    width: 15%;
  }
  .up {
    height: 40px;
    font-size: 16px;
    font-weight: bold;
  }
</style>
<script>
  import TopMirror from '@/components/TopMirror'
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
      TopMirror,
      Button
    },
    beforeMount: function () {
      console.log(this.type)
      this.type = this.$route.params.type
      this.name = this.type === 'new' ? '新增' : '预选课阶段'
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
