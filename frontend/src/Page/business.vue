<template>
  <div>
    <TopScenario kind="业务场景" :name="name"></TopScenario>
    <div class="top-btn">
      <div>
        <button>
          <router-link to="/evolution">演化历史</router-link>
        </button>
        <button>缺陷检测</button>
      </div>
      <div>
        <button>保存</button>
        <button>
          <router-link to="/project/home">返回</router-link>
        </button>
      </div>
    </div>
    <div class="box1">
      <div class="ylevel">
        <label for="name">业务场景名称:</label>
        <input class="xlevel" id="name">
      </div>
      <div class="ylevel">
        <label for="discription">场景概述:</label>
        <input class="xlevel" id="discription">
      </div>
      <div class="ylevel">
        <label for="discription">前置条件:</label>
        <input class="xlevel" id="precondition">
      </div>
      <div class="ylevel">
        <label for="primaryRole">主要角色:</label>
        <input class="xlevel" id="primaryRole">
      </div>
      <div class="ylevel">
        <label for="secondaryRole">次要角色:</label>
        <input class="xlevel" id="secondaryRole">
      </div>
      <div class="ylevel">
        <label for="dependency">依赖关系:</label>
        <input class="xlevel" id="dependency">
      </div>
      <div class="ylevel">
        <label for="dependency">输入数据:</label>
        <input class="xlevel" id="input">
      </div>
      <div class="ylevel">
        <label for="dependency">输出数据:</label>
        <input class="xlevel" id="output">
      </div>
      <div class="ylevel">
        <label for="data">相关数据:</label>
        <input class="xlevel" id="data">
      </div>
      <div class="flow businesss-flow">
        <SpecialTable ref="bflow"  @otherData="otherData" title="事件流" tag="Step" :data="basicFlow"></SpecialTable>
      </div>
    </div>
    <div class="box2">
      <div class="up"><span>相关用例场景：</span></div>
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
          <Button type="primary">新增用例场景</Button>
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
      this.name = this.type === 'new' ? '新增' : '选课退课'
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
