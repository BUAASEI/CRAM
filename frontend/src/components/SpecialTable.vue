<template>
  <div class="view">
    <div class="title">
      <div>{{ title }} </div>
      <div class="btn" v-if="tag === 'RFS'">
        <span  @click="addSpec">+新增</span>
        <span @click="delSpec">-删除</span>
      </div>
    </div>
    <div class="table">
      <div class="tag">
        <span>{{ tag }}</span>
        <input v-if="tag === 'RFS'">
      </div>
      <Table :colums = "colums" :datas = "datas" addAble></Table>
      <div class="flex">
        <div class="item">PostCondition</div>
        <div class="text"><input v-model="postCondition"></div>
      </div>
    </div>
  </div>
</template>
<style scoped>
  input {
    outline: none;
    border: none;
  }
  .view {
    display: flex;
    justify-content: space-between;
  }
  .title {
    width: 23%;
    padding: 0 10px;
    background-color: #bcbcbc;
  }
  .table {
    flex-grow: 1;
  }
  .tag {
    font-weight: bolder;
    color: blue;
    border: 1px solid #ccc;
  }
  .btn {
    display: flex;
    justify-content: space-between;
    color: blue;
    cursor: pointer;
  }
  .flex {
    display: flex;
    justify-content: space-between;
    height: 30px;
    border: 1px solid #ccc;
  }
  .item {
    width: 23%;
    line-height: 30px;
    font-weight: bolder;
    padding-left: 10px;
    color: blue;
    border-right: 1px solid #ccc;
  }
  .text {
    flex-grow: 1;
  }
  .text>input {
    outline: none;
    border: none;
  }
</style>
<script>
  import Table from '@/components/Table'
  export default{
    data () {
      return {
        postCondition: ''
      }
    },
    props: {
      colums: {
        type: Array,
        default: []
      },
      datas: {
        type: Array,
        default: []
      },
      title: {
        type: String,
        default: ''
      },
      tag: {
        type: String,
        default: ''
      },
      pos: {
        type: Number,
        default: 0
      }
    },
    components: {
      Table
    },
    methods: {
      addSpec () {
        let obj = {
          title: this.title,
          pos: this.pos
        }
        this.$emit('add', obj)
      },
      delSpec () {
        let obj = {
          title: this.title,
          pos: this.pos
        }
        this.$emit('del', obj)
      }
    }
  }
</script>
