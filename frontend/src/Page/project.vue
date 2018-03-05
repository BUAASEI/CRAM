<!--选课系统主页面-->
<template>
  <div>
    <Top name="北航学生选课系统"></Top>
    <div class="context">
      <div class="context-nav">
        <Nav target="st"></Nav>
      </div>
      <div class="context-detail">
        <div class="detail">
          <div class="detail-btn"><Button type="primary">新增业务场景</Button></div>
          <div class="detail-body">
            <div class="detail-head">
              <div class="col-name">业务场景名称</div>
              <div class="col-roles">相关角色</div>
              <div class="col-datas">相关数据</div>
              <div class="col-operate">操作</div>
            </div>
            <div class="detail-context">
              <div class="detail-item" v-for="item in BusinessData" :key=item.id>
                <div class="col-name">{{ item.name }}</div>
                <div class="col-roles">{{ item.roles }}</div>
                <div class="col-datas">{{ item.datas }}</div>
                <div class="col-operate">
                  <span>查看</span>
                  <span @click="editScenario(item.id)">修改</span>
                  <span>删除</span>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="detail">
          <div class="detail-btn"><Button type="primary">新增用例</Button></div>
          <div class="detail-body">
            <div class="detail-head">
              <div class="col-name">用例名称</div>
              <div class="col-roles">相关角色</div>
              <div class="col-datas">相关数据</div>
              <div class="col-operate">操作</div>
            </div>
            <div class="detail-context">
              <div class="detail-item" v-for="item in UsageData" :key=item.id>
                <div class="col-name">{{ item.name }}</div>
                <div class="col-roles">{{ item.roles }}</div>
                <div class="col-datas">{{ item.datas }}</div>
                <div class="col-operate">
                  <span>查看</span>
                  <span @click="editUsecase(item.id)">修改</span>
                  <span>删除</span>
                </div>
              </div>
            </div>

          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped>
  .context {
    display: flex;
    justify-content: flex-start;
  }
  .context-nav {
    width: 150px;
  }
  .context-title {
    width: 15%;
    display: flex;
    flex-direction: column;
    align-items: flex-start;
  }
  .context-title>div {
    margin-top: 10px;
  }
  .context-detail {
    flex-grow: 1;
  }
  .detail {
    padding-top: 10px;
    text-align: left;
    height: 300px;
  }
  .detail-body {
    margin-top: 20px;
  }
  .detail-head,.detail-item {
    display: flex;
    justify-content: flex-start;
  }
  .detail-head {
    height: 50px;
    line-height: 50px;
    background-color: lightgray;
  }
  .detail-head>div {
    padding: 0 10px;
    border-left: 1px solid #fff;
  }
  .detail-context {
    max-height: 240px;
    overflow: auto;
  }
  .detail-item {
    height: 40px;
    line-height: 40px;
    border-bottom: 1px solid lightgray;
  }
  .detail-item>div {
    padding: 0 10px;
    border-left: 1px solid lightgray;
  }
  .col-name {
    width: 20%;
  }
  .col-roles {
    width: 40%;
  }
  .col-datas {
    width: 30%;
  }
  .col-operate {
    width: 10%;
  }
  .col-operate>span {
    color: dodgerblue;
    cursor: pointer;
  }

</style>
<script>
import Top from '@/components/Top'
import Nav from '@/components/Nav'
import {Button} from 'iview'
export default{
  data () {
    return {
      // stage: [
      //   {
      //     projectId: 1,
      //     projectName: '预选课'
      //   },
      //   {
      //     projectId: 2,
      //     projectName: '选课'
      //   },
      //   {
      //     projectId: 3,
      //     projectName: '四六级考试报名'
      //   }
      // ],
      BusinessData: [
        {
          id: 1,
          name: '预选课',
          roles: '校教务部，院系教务',
          datas: '课程信息，学生选课结果'
        },
        {
          id: 2,
          name: '选课',
          roles: '校教务部，院系教务',
          datas: '学生选课结果'
        }
      ],
      UsageData: [
        {
          id: 1,
          name: '提交选课申请',
          roles: '学生，校教务部，院系教务',
          datas: '学生选课信息'
        },
        {
          id: 2,
          name: '选课结束',
          roles: '学生，开课老师，计算中心',
          datas: '学生选课结果'
        }
      ],
      // showStage: false
    }
  },
  components: {
    Top,
    Nav,
    Button
  },
  methods: {
    editScenario: function (id) {
      // do something
      // 路由跳转
      this.$router.push({ name: 'scenario', params: {type: 'edit'} })
    },
    editUsecase: function (id) {
      // do something
      // 路由跳转
      this.$router.push({ name: 'usecase', params: {type: 'edit'} })
    },
    reqInfo: function (id) {
      //ajax请求
      let a1 = [
        {
          id: 1,
          name: '毕业选课结果',
          roles: '校教务部，院系教务',
          datas: '学生选课结果'
        },
        {
          id: 2,
          name: '选课结束',
          roles: '学生，开课老师，计算中心',
          datas: '学生选课结果'
        },
        {
          id: 3,
          name: '选课结束',
          roles: '学生，开课老师，计算中心',
          datas: '学生选课结果'
        }
      ]
      let a2 = [
        {
          id: 1,
          name: '毕业选课结果',
          roles: '校教务部，院系教务',
          datas: '学生选课结果'
        },
        {
          id: 2,
          name: '选课结束',
          roles: '学生，开课老师，计算中心',
          datas: '学生选课结果'
        }
      ]
      this. BusinessData = a1
      this.UsageData = a2
      // this.showStage = true
    }
  }
}
</script>
