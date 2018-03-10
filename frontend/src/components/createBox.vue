<template>
  <div class="view">
    <div class="head">
      <span class="title"> {{ title }}</span>
      <span class="close" @click="close">X</span>
    </div>
    <div>
      <div v-for="item in datas" class="row">
        <div class="colum1">{{ item }}</div>
        <div class="colum2">
          <Input v-model="outputs[item]"></Input>
        </div>
      </div>
    </div>
    <div class="btn">
      <Button @click="saveData">保存</Button>
      <Button @click="cancel">取消</Button>
    </div>
  </div>
</template>
<style scoped>
  .view {
    width: 60%;
    margin: 80px auto;
    padding: 30px;
    background-color: white;
    z-index: 1;
    overflow: auto;
  }
  .head {
    padding-right: 10px;
    font-size: 18px;
    color: blue;
  }
  .head span {
    cursor: pointer;
  }
  .row {
    display: flex;
    justify-content: flex-start;
    height: 50px;
    line-height: 50px;
  }
  .colum1 {
    width: 25%;
    text-align: left;
  }
  .colum2 {
    flex-grow: 1;
  }
  .btn {
    margin-top: 20px;
  }
  .close {
    float: right;
  }
</style>
<script>
  import {Button, Input} from 'iview'
  export default{
    data () {
      return {
        outputs: {}
      }
    },
    props: {
      title: {
        type: String,
        default: ''
      },
      url: {
        type: String,
        default: ''
      },
      datas: {
        type: Array,
        default: function () {
          return []
        }
      }
    },
    components: {
      Button,
      Input
    },
    methods: {
      saveData: function () {
        let obj = this.outputs
        obj['creatorId'] = localStorage.getItem('id')
        obj['projectId'] = localStorage.getItem('pId')
        this.$http.post(this.url, obj)
          .then((response) => {
            this.$emit("close")
          })
      },
      cancel: function () {
        this.outputs = []
      },
      close () {
        this.$emit("close")
      }
    }
  }
</script>
