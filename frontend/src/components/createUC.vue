<template>
  <div class="view">
    <div class="thead">
      <div class="thead-left">新建用例</div>
      <div @click="close" class="thead-right">X</div>
    </div>
    <div>
      <Table ref="table" @tableData="tableData" :data = "targets"></Table>
    </div>
    <div>
      <Button @click="saveData">保存</Button>
      <Button @click="cancel">取消</Button>
    </div>
  </div>
</template>
<style scoped>
  .view {
    width: 60%;
    margin: 80px auto;
    background-color: white;
    z-index: 1;
    overflow: auto;
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
</style>
<script>
  import Table from '@/components/Table'
  import {Button} from 'iview'
  export default{
    data () {
      return {
        targets: {
          colum: ['name', 'description','actor','dictionary'],
          data: []
        }
      }
    },
    components: {
      Button,
      Table
    },
    methods: {
      saveData: function () {
        console.log(1)
        this.$refs.table.sends()
      },
      cancel: function () {
        this.$refs.table.dels()
        this.show = false;
        this.showOne = false;
      },
      tableData: function (datas) {
        console.log(1)
        this.$emit('newData', datas);
      },
      close () {
        this.$emit('closeNew');
      }
    }
  }
</script>
