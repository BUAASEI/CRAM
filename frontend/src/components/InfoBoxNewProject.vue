<!--窗口组件-->
<template>
  <div class="view">
    <div class="thead">
      <div class="thead-left">新建项目</div>
      <div @click="close" class="thead-right">X</div>
    </div>
    <div class="box1">
      <div class="ylevel">
        <label for="name">项目名称:</label>
        <input v-model="name" class="xlevel" id="name">
      </div>
      <div class="ylevel">
        <label for="discribe">项目描述:</label>
        <textarea v-model="discribe" class="xlevel text" id="discribe" rows="4"></textarea>
      </div>
    </div>
    <div class="box2 scroll">
      <div class="thead">选择所属领域</div>
      <div class="box-body">
        <div class="box-head">
          <!--<div class="col-id">ID</div>-->
          <div class="col-name">名称</div>
        </div>
        <div class="box-context">
          <div class="info-item" >
            <CheckboxGroup v-model="vertical" >
              <div v-for="(item, index) in infos" :key="index">
              <!--  <Checkbox size="large" :label=item.id ></Checkbox>-->
                <input id="boxId" type="checkbox" v-bind:value="item.id" />
                <span class="col-name">{{ item.name }}</span>
              </div>
            </CheckboxGroup>
          </div>
        </div>
      </div>
    </div>
    <div class="box3">
      <Button @click="reset">重置</Button>
      <Button @click="submit">确定</Button>
    </div>
  </div>
</template>
<style scoped>
  .view {
    width: 60%;
    margin: 80px auto;
    background-color: wheat;
    z-index: 1;
  }
  .thead {
    display: flex;
    justify-content: space-between;
    padding: 10px;
    font-size: 16px;
    text-align: center;
    border-bottom: 1px solid gray;
  }
  .thead-left {
    color: blue;
  }
  .thead-right {
    cursor: pointer;
    color: red;
  }
  .box1 {
    position: relative;
    margin: 20px 0;
    height: 200px;
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
    width: 400px;
  }
  .text {
    height: 150px;
  }
  .box2 {
    min-height: 100px;
    max-height: 220px;
    text-align: left;
    color: gray;
    overflow: auto;
  }
  .box-head, .info-item {
    display: flex;
    justify-content: flex-start;
    padding-left: 10px;
  }
  .box-body {
    padding-top: 10px;
  }
  .col-id {
    margin-left: 20px;
  }
  .col-name {
    margin-left: 50px;
  }
  .box3 {
    padding-bottom: 20px;
  }

</style>
<script>
  import {CheckboxGroup, Checkbox, Button} from 'iview'
  export default{
    data () {
      return {
        infos: [/*后台获取*/
          {
            id: 1,
            name: '高等教育'
          },
          {
            id: 2,
            name: '嵌入式'
          }
        ],
        name: null,
        discribe: null,
        vertical: []
      }
    },
    components: {
      CheckboxGroup,
      Checkbox,
      Button
    },
    methods: {
      initData(){
        this.$http.post('sysuser/getdomain')
          .then((response)=>
        {
          //     var domain = response.data;
          //
        })
      },
      reset () {
        this.name = null
        this.discribe = null
        this.vertical = []
      },
      submit () {
        var userId = localStorage.getItem("id");
        let body = {
          ProjectName: this.name,
          Description: this.discribe,
          DomainId: 1,
          CreatorId: userId
        };
        /*ajax*/
        this.$http.post('sysuser/crepro',body)
          .then((response) => {
            confirm(response.data.Msg);
          })
      },
      close () {
        this.$emit('closeIbox', 3)
      }
    }
  }
</script>
