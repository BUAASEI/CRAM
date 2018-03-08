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
        <input v-model="RFS" v-if="tag !== 'Step'">
      </div>
      <Table ref="table" @tableData="tableData" :data = "targets" addAble></Table>
      <div class="flex">
        <div class="item">后置条件</div>
        <div class="text"><input v-model="PostCondition"></div>
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
        targets: {},
        PostCondition: '',
        RFS: ''
      }
    },
    props: {
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
      },
      data: {
        type: Object,
        default: function() {
          return {};
        }
      }
    },
    created () {
      this.targets = {
        colum: this.data.colum,
        data: this.data.data
      }
      if (this.tag !== 'Step') {
        this.RFS = this.data.RFS
      }
      this.PostCondition = this.data.PostCondition
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
      },
      sends () {
        this.$refs.table.sends()
      },
      tableData (obj) {
        let data = {
          colum: this.colums,
          data: this.datas
        }
        if (this.tag !== 'Step') {
          obj['RFS'] = this.RFS
        }
        obj['PostCondition'] = this.PostCondition
        this.$emit('otherData', obj, this.title, this.pos)
      }
    }
  }
</script>
