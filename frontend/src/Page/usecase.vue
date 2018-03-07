<template>
  <div>
    <TopMirror kind="用例" :name="name"></TopMirror>
    <div class="top-btn">
      <div>
        <button>
          <router-link to="/evolution">演化历史</router-link>
        </button>
        <button>缺陷检测</button>
      </div>
      <div>
        <button @click="saveData">保存</button>
        <button>取消</button>
      </div>
    </div>
    <div class="box1 scroll">
      <div class="rucm-head">Use Case Specitication for CRAM</div>
      <div class="flow rucm-basic-info">
        <Table v-model="brief" ref="table" @tableData="tableData" :data="brief"></Table>
      </div>
      <div class="flow rucm-basicflow">
        <SpecialTable ref="bflow"  @otherData="otherData" title="Basic Flow" tag="Step" :data="basicFlow"></SpecialTable>
      </div>
      <div class=" flow rucm-boundedflow"v-for = "(item,index) in specData1">
        <SpecialTable  ref="flow"  @add="add" @del="del" @otherData="otherData" title="Bounded Alternative Flow" tag="RFS"  :pos="index" :data="item"></SpecialTable>
      </div>
      <div class="flow rucm-specificflow" v-for = "(item,index) in specData2">
        <SpecialTable  ref="flow"  @add="add" @del="del" @otherData="otherData" title="Specific Alternative Flow" tag="RFS" :pos="index" :data="item"></SpecialTable>
      </div>
      <div class=" flow rucm-Globalflow" v-for = "(item,index) in specData3">
        <SpecialTable  ref="flow"  @add="add" @del="del"  @otherData="otherData" title="Global Alternative Flow" tag="RFS"  :pos="index" :data="item"></SpecialTable>
      </div>
    </div>
    <div class="box3">
      <div class="up"><span>相关业务场景：</span></div>
      <p>
        <span class="focus-color">维护课程信息</span>
      </p>
      <p>
        <button>关联业务场景</button>
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
        fr: '1',
        type: '',
        name: '',
        id:'',

        brief: {
          colum:['Usecase Name','Brief Description','Precondition','Primary Actor','Secondary Actors','Dependency','Generalization','Input','OutPut','DataDictionary'],
          data: [],
        },
        basicFlow: {
          colum: [1],
          data: [''],
          PostCondition:''
        },
        specData1: [
          {
            colum: [1],
            data: [],
            PostCondition:''
          }
        ],
        specData2: [
          {
            colum: [1],
            data: [],
            PostCondition:''
          }
        ],
        specData3: [
          {
            colum: [1],
            data: [],
            PostCondition:''
          }
        ],
        dict: [],
        cases: {
          Brief: {},
          BasicFlow: {},
          BoundedAlternativeFlows: [],
          SpecificAlternativeFlows: [],
          GlobalAlternativeFlows: [],
          DataDictionary: []
        },
        count: 0
      }
    },
    components: {
      TopMirror,
      Button,
      Table,
      SpecialTable
    },
    mounted(){
      this.type = this.$route.params.type;
      this.id = this.$route.params.id;
      // alert(this.id);
      this.initData(this.id);
    },
    // beforeMount: function () {
    //
    //   // this.initData(this.id);
    //   // this.name = this.type === 'new' ? '新增课程信息' : '更新课程信息'
    // },
    methods: {
      initData: function(id){
        this.$http.post('usecase/getusecase',{"usecaseId":id})
          .then((response) => {
            var usecase = response.data;
            if(usecase!=null){
              this.id = usecase.id;
              alert("iddddd:"+id);
              var rucmJson = usecase.rucmSpec;
               alert(rucmJson);
              // var rucmJson = JSON.parse(rucmSpec);

              this.basicFlow = rucmJson.basicFlow;
              this.specData1 = rucmJson.specData1;
              this.specData2 = rucmJson.specData2;
              this.specData3 = rucmJson.specData3;
              this.dict = rucmJson.DataDictionary;
              this.cases = rucmJson.cases;
              this.brief = rucmJson.brief;
              alert("brief:"+this.brief.toString());
            }
          })
      },
      add (obj) {
        if (obj.title === 'Bounded Alternative Flow') {
          this.specData1.splice(obj.pos+1, 0, {colum: [1],data: [''],postCondition:''})
        } else if (obj.title === 'Specific Alternative Flow') {
          this.specData2.splice(obj.pos+1, 0, {colum: [1],data: [''],postCondition:''})
        } else {
          this.specData3.splice(obj.pos+1, 0, {colum: [1],data: [''],postCondition:''})
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
      },
      saveData () {

        this.$refs.table.sends()
        this.$refs.bflow.sends()
        console.log(this.$refs.flow)
        this.$refs.flow.forEach(item => {
          item.sends()
        })
        let rucmSpec={
          brief:this.brief,
          basicFlow : this.basicFlow,
          specData1 : this.specData1,
          specData2 : this.specData2,
          specData3 : this.specData3,
          cases: this.cases
        }
         alert("this.brief save:"+this.brief);
        // var rucmSpec = JSON.stringify(rucm);
        let usecase={
          id: this.id,
          rucmSpec:rucmSpec
        };
        this.$http.post('usecase/updateusecase',usecase)
          .then((response) => {
            confirm(response.data.Msg);
          })
      },
      tableData (data) {
        this.cases.Brief = data
        this.count++
        if (this.count === 2 + this.specData1.length + this.specData2.length + this.specData3.length) {
          // ajax

        }
      },
      otherData (data, title, pos) {
        if (title === 'Basic Flow') {
          this.cases.BasicFlow = data

        } else if (title === 'Bounded Alternative Flow') {
          this.cases.BoundedAlternativeFlows[pos] = data
        } else if (title === 'Specific Alternative Flow') {
          this.cases.SpecificAlternativeFlows[pos] = data
        } else {
          this.cases.GlobalAlternativeFlows[pos] = data
        }
        this.count++
        if (this.count === 2 + this.specData1.length + this.specData2.length + this.specData3.length) {
          // ajax

        }
      }
    }
  }
</script>
