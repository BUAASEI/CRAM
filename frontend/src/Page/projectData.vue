<!--选课系统主页面-->
<template>
  <div>
    <TopProject name="北航学生选课系统"></TopProject>
    <div class="context">
      <div class="context-nav">
        <NavProject target="st"></NavProject>
      </div>
      <div class="context-detail">
        <div class="detail">
          <div class="detail-btn"><Button type="primary">新增数据</Button></div>
          <div class="detail-body">
            <div class="detail-head">
              <div class="col-name">数据名称</div>
              <div class="col-description">数据描述</div>
              <div class="col-business">所属业务场景</div>
              <div class="col-usecase">所属用例场景</div>
              <div class="col-operate">操作</div>
            </div>
            <div class="detail-context">
              <div class="detail-item" v-for="item in userDatas" :key=item.id>
                <div class="col-name">{{ item.name }}</div>
                <div class="col-description">{{ item.description }}</div>
                <div class="col-businesses">{{ item.businesses }}</div>
                <div class="col-usecases">{{ item.usecases }}</div>
                <div class="col-operate">
                  <!--<span @click="editScenario(item.id)">设置</span>-->
                  <span>修改</span>
                  <span>删除</span>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="detail">
          <div class="detail-body">
            <div class="detail-head">
              <div class="col-name">数据名称</div>
              <div class="col-description">数据描述</div>
              <div class="col-business">所属业务场景</div>
              <div class="col-usecase">所属用例场景</div>
              <div class="col-operate">操作</div>
            </div>
            <div class="detail-context">
              <div class="detail-item" v-for="item in listDatas" :key=item.id>
                <div class="col-name">{{ item.name }}</div>
                <div class="col-description">{{ item.description }}</div>
                <div class="col-businesses">{{ item.businesses }}</div>
                <div class="col-usecases">{{ item.usecases }}</div>
                <div class="col-operate">
                  <!--<span @click="editUsecase(item.id)">设置</span>-->
                  <span>修改</span>
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
    width: 10%;
  }
  .col-description {
    width: 20%;
  }
  .col-businesses {
    width: 30%;
  }
  .col-usecases {
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

  export default{
    data () {
      return {
        userDatas: [],
        listDatas: []
      }
    },
    components: {
      TopProject,
      NavProject,
      Button
    },
    mounted() {
      this.getDatas(3,3);
    },
    methods: {

      getDatas:function (projectId,userId) {
        let info={
          projectId:projectId,
          userId:userId
        };
        this.$http.post('project/data',info)
          .then((response) => {
            this.listDatas = response.data.listDatas;

            this.userDatas=response.data.userDatas;

          })
      },
      editScenario: function (id) {
        // do something
        // 路由跳转
        this.$router.push({ name: 'scenario', params: {type: 'edit'} })
      },
      editUsecase: function (id) {
        // do something
        // 路由跳转
        this.$router.push({ name: 'usecase', params: {type: 'edit'} })
      }
    }
  }
</script>
