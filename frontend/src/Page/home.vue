<template>
  <div>
    <TopHome name="主页"></TopHome>
    <div class="context">
      <div class="context-nav">
        <NavHome target="st"></NavHome>
      </div>
      <div class="context-detail">
        <div class="detail">
          <div class="detail-body">
            <div class="detail-head">
              <div class="col-domain">领域名称</div>
              <div class="col-project">包含项目</div>
              <div class="col-operate">操作</div>
            </div>
            <div class="detail-context">
              <div class="detail-item" v-for="item in DomainData" :key=item.id>
                <div class="col-domain">{{ item.domain }}</div>
                <div class="col-project">{{ item.project }}</div>
                <div class="col-operate">
                  <span @click="view(item.id)">查看</span>
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

<script>
  import TopHome from '@/components/TopHome'
  import NavHome from '@/components/NavHome'
  import {Button} from 'iview'
  export default{
    data () {
      return {
        DomainData: [
          {
            id: 1,
            domain: '高等教育',
            project: '北航选课系统'
          },
          {
            id: 2,
              domain: '嵌入式',
                project: '电梯系统'
          }
        ]
      }
    },
    components: {
      TopHome,
      NavHome,
      Button
    },
    methods: {
      view: function (id) {
        // do something
        // 路由跳转
        this.$router.push({ name: 'project', params: {type: 'view'} })
      }
    }
  }
</script>

<style scoped>
  .context {
    display: flex;
    justify-content: flex-start;
  }
  .context-nav {
    width: 150px;
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
  .col-domain {
    width: 10%;
  }
  .col-project {
    width: 50%;
  }
  .col-operate {
    width: 10%;
  }
  .col-operate>span {
    color: dodgerblue;
    cursor: pointer;
  }
</style>
