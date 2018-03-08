<!--选课系统主页面-->
<template>
  <div>
    <TopProject name="北航学生选课系统"></TopProject>
    <div class="context">
      <div class="context-nav">
        <NavProject target="st"></NavProject>
      </div>
      <div class="context-detail">

          <div class="tree">
            <div class="title">演化历史</div>
            <Tree class="tree-struct":data="data"></Tree>
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
  .tree-struct {
    font-size: 15px;
  }
  .col-operate>span {
    color: dodgerblue;
    cursor: pointer;
  }
  .tree{
    text-align: left;
    padding-left: 100px;
    padding-top: 60px;
  }
  .title{
    font-size: 25px;
  }

</style>
<script>
  import TopProject from '@/components/TopProject'
  import NavProject from '@/components/NavProject'
  import {Button} from 'iview'
  import {Tree} from 'iview'
  export default {
    name: "evolution",
    data() {
      return {
        data: [
          {
            title: 'parent 1',
            expand: false,
            children: [
              {
                title: 'parent 1-1',
                expand: false,
                children: [
                  {
                    title: 'leaf 1-1-1',
                    expand: false,
                    children: [
                      {
                        title: 'leaf 1-1-1-1'
                      },
                      {
                        title: 'leaf 1-1-2-1'
                      }
                    ]
                  },
                  {
                    title: 'leaf 1-1-2'
                  }
                ]
              },
              {
                title: 'parent 1-2',
                expand: false,
                children: [
                  {
                    title: 'leaf 1-2-1'
                  },
                  {
                    title: 'leaf 1-2-1'
                  }
                ]
              }
            ]
          }
        ]
      }
    },
    components: {
      Tree: Tree,
      TopProject,
      NavProject,
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
      }
    }
  }
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

</script>
