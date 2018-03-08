<!--选课系统主页面-->
<template>
  <div>
    <TopProject name="北航学生选课系统" @showIbox="showIbox"></TopProject>
    <div class="context">
      <div class="context-nav">
        <NavProject target="st"></NavProject>
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
                <div class="col-name">{{ item.businessName }}</div>
                <div class="col-roles">{{ item.roleName }}</div>
                <div class="col-datas">{{ item.dataName }}</div>
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
                <div class="col-name">{{ item.usecaseName }}</div>
                <div class="col-roles">{{ item.roleName }}</div>
                <div class="col-datas">{{ item.dataName }}</div>
                <div class="col-operate">
                  <span>查看</span>
                  <span @click="editUsecase(item.usecaseId)">修改</span>
                  <span>删除</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div  v-if="show" class="box">
      <div class="subWindow" v-if="showThree">
        <InfoBoxNewProject @closeIbox(1)="close"></InfoBoxNewProject>
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
import TopProject from '@/components/TopProject'
import NavProject from '@/components/NavProject'
import {Button} from 'iview'
 import InfoBoxEvolution from '@/components/InfoBoxEvolution'

export default{
  data () {
    return {
      projectId:'1',
      BusinessData:[1],
      UsageData:[1]
    }
  },
  components: {
    TopProject,
    NavProject,
    Button,
    InfoBoxEvolution
  },
  mounted() {

    // this.projectId = this.$route.params.projectId;
    // var userId = localStorage.getItem("id");
    // this.reqInfo(projectId,userId);
    this.reqInfo(1,3);
    this.close()
  },
  methods: {
    editScenario: function (id) {
      // do something
      // 路由跳转
      this.$router.push({ name: 'business', params: {type: 'edit'} })
    },
    editUsecase: function (id) {
      // do something
      // 路由跳转
      alert(id);
      this.$router.push({ name: 'usecase', params: {type: 'edit',id: id} })
    },
    reqInfo: function (projectId,userId) {
      let info={
          ProjectId:projectId,
          UserId:userId
      };
      this.$http.post('project/home',info)
       .then((response) => {
         this. BusinessData = response.data.businessForms;
         // alert(this. BusinessData);
         this.UsageData =response.data.usecaseForms;
         })
      }
    },
    showIbox (idx) {
        this.show = true
        if (idx === 1) {
          this.showOne = true
        }
      },
      close (idx) {
        this.show = false
        if (idx === 1) {
          this.showOne = false
        }
      }
}
</script>
