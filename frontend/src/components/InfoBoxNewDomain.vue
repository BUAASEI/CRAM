<!--窗口组件-->
<template>
  <div class="view">
    <div class="thead">
      <div class="thead-left">新建领域</div>
      <div @click="close" class="thead-right">X</div>
    </div>
    <div class="box1">
      <div class="ylevel">
        <label for="name">领域名称:</label>
        <input v-model="name" class="xlevel" id="name">
      </div>
      <div class="ylevel">
        <label for="discribe">领域描述:</label>
        <textarea v-model="discribe" class="xlevel text" id="discribe" rows="4"></textarea>
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
            name: 's'
          },
          {
            id: 2,
            name: 'x'
          }
        ],
        name: null,
        discribe: null,
      }
    },
    components: {
      CheckboxGroup,
      Checkbox,
      Button
    },
    methods: {
      reset () {
        this.name = null
        this.discribe = null
      },
      submit () {
        var creatorId = localStorage.getItem('id');
        let body = {
          DomainName: this.name,
          Description: this.discribe,
          CreatorId: creatorId/*登陆时获得*/
        };
        /*ajax*/
        this.$http.post('sysuser/credom',body)
          .then((response) => {
            confirm(response.data.Msg);
          })
      },
      close () {
        this.$emit('closeIbox', 2)
      }
    }
  }
</script>
