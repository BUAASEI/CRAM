<template>
  <div class="view">
    <div class="table-colum">
      <div class="row" v-for="(col,index) in colums" :key='index'>{{ col }}</div>
    </div>
    <div class="table-data">
      <div class="row" v-for="(item,index) in datas" :key='index'>
        <input v-model="datas[index]">
        <div class="btn" v-if="addAble && index=== datas.length-1">
          <span @click="addRow">+</span>
          <span v-if="addAble && index !== 0" @click="delRow">-</span>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped>
  .view {
    display: flex;
    justify-content: space-between;
  }
  .table-colum {
    width: 23%;
  }
  .table-data {
    flex-grow: 1;
  }
  .row {
    display: flex;
    justify-content: space-between;
    padding: 0 10px;
    height: 30px;
    line-height: 30px;
    border: 1px solid #ccc;
  }
  .row>input {
    width: 70%;
    border: none;
    outline: none;
  }
  .btn {
    font-weight: bolder;
    font-size: 16px;
    color: blue;
    cursor: pointer;
  }
  .btn>span {
    display: inline-block;
    width: 30px;
    text-align: center;
  }
</style>
<script>
  export default{
    data () {
      return {
      }
    },
    props: {
      colums: {
        type: Array,
        default: [1]
      },
      datas: {
        type: Array,
        default: []
      },
      addAble: {
        type: Boolean,
        default: false
      }
    },
    created () {
      let len = this.colums.length
      while (this.datas.length < len) {
        this.datas.push('')
      }
    },
    components: {
    },
    methods: {
      addRow () {
        let len = this.colums.length
        this.colums.push(len+1)
        this.datas.push('')
      },
      delRow () {
        this.colums.splice(-1)
        this.datas.splice(-1)
      }
    }
  }
</script>
