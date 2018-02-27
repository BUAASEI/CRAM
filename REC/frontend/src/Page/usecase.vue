<template>
  <div>
    <TopMirror kind="用例" :name="name"></TopMirror>
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
    <div class="box1 scroll">
      <div class="rucm-head">Use Case Specitication</div>
      <div class="flow rucm-basic-info">
        <Table :colums="colums1" :datas="datas1"></Table>
      </div>
      <div class="flow rucm-basicflow">
        <SpecialTable title="Basic Flow" tag="Step" :colums="colums2" :datas="datas2"></SpecialTable>
      </div>
      <div class=" flow rucm-boundedflow"v-for = "(item,index) in specData1">
        <SpecialTable @add="add" @del="del" title="Bounded Alternative Flow" tag="RFS"  :pos="index" :colums="item.colum" :datas="item.data"></SpecialTable>
      </div>
      <div class="flow rucm-specificflow" v-for = "(item,index) in specData2">
        <SpecialTable @add="add" @del="del" title="Specific Alternative Flow" tag="RFS" :pos="index" :colums="item.colum" :datas="item.data"></SpecialTable>
      </div>
      <div class=" flow rucm-Globalflow" v-for = "(item,index) in specData3">
        <SpecialTable @add="add" @del="del" title="Global Alternative Flow" tag="RFS"  :pos="index" :colums="item.colum" :datas="item.data"></SpecialTable>
      </div>
    </div>
    <div class="box3">
      <div class="up"><span>相关业务活动：</span></div>
      <p>
        <span class="focus-color">维护课程信息</span>
      </p>
      <p>
        <button>关联业务活动</button>
      </p>
    </div>
    <div class="box4">
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
    width: 80%;
    min-height: 200px;
    max-height: 900px;
    margin: 20px auto;
    text-align: left;
    overflow: auto;
  }
  .rucm-head {
    height: 30px;
    text-align: center;
  }
  .flow {
    margin: 20px 0;
  }
  .box3, .box4 {
    padding: 20px 0;
    text-align: left;
    border-bottom: 1px solid lightgray;
  }
  .up {
    height: 40px;
    font-size: 16px;
    font-weight: bold;
  }
  .box2-bottom {
    padding: 0 20px;
  }
  .box2-head {
    display: flex;
    justify-content: flex-start;
  }
  .box2-body {
    padding-top: 20px;
  }
  .box2-context {
    display: flex;
    justify-content: flex-start;
  }
  .col-order {
    width: 5%;
  }
  .col-role {
    width: 40%;
  }
  .col-system {
    width: 40%;
  }
  .col-operate {
    width: 15%;
  }
</style>
<script>
import TopMirror from '@/components/TopMirror'
import Table from '@/components/Table'
import SpecialTable from '@/components/SpecialTable'
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
      ],
      colums1: ['Use Case Name','Brief Description','Precondition','Primary Actor','Secondary Actors','Dependency','Generalization'],
      datas1: [],
      colums2: [1],
      datas2: [''],
      specData1: [
        {
          colum: [1],
          data: []
        }
      ],
      specData2: [
        {
          colum: [1],
          data: []
        }
      ],
      specData3: [
        {
          colum: [1],
          data: []
        }
      ]
    }
  },
  components: {
    TopMirror,
    Button,
    Table,
    SpecialTable
  },
  beforeMount: function () {
    this.type = this.$route.params.type
    this.name = this.type === 'new' ? '新增课程信息' : '更新课程信息'
  },
  methods: {
    del: function (index) {
      // do something
      this.infos.splice(index, 1)
    },
    add (obj) {
      if (obj.title === 'Bounded Alternative Flow') {
        this.specData1.splice(obj.pos+1, 0, {colum: [1],data: ['']})
      } else if (obj.title === 'Specific Alternative Flow') {
        this.specData2.splice(obj.pos+1, 0, {colum: [1],data: ['']})
      } else {
        this.specData3.splice(obj.pos+1, 0, {colum: [1],data: ['']})
      }
    },
    del (obj) {
      if (obj.title === 'Bounded Alternative Flow') {
        this.specData1.splice(obj.pos, 1)
      } else if (obj.title === 'Specific Alternative Flow') {
        this.specData2.splice(obj.pos, 1)
      } else {
        this.specData3.splice(obj.pos, 1)
      }
    }
  }
}
</script>
