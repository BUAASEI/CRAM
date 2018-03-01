<template>
  <div>
    <TopMirrorSysUser name="苏若" @showIbox="showIbox"></TopMirrorSysUser>
    <div class="context">
      <div class="context-nav">
        <NavMirrorSysUser target="st"></NavMirrorSysUser>
      </div>
      <div class="context-detail">
        <div class="detail">
          <div class="detail-body">
            <div class="detail-head">
              <div class="col-name">项目名称</div>
              <div class="col-operate">操作</div>
            </div>
            <div class="detail-context">
              <div class="detail-item" v-for="item in UsageData" :key=item.id>
                <div class="col-name">{{ item.name }}</div>
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
    <div  v-if="show" class="box">
      <div class="subWindow" v-if="showThree">
        <InfoBoxNewProject @closeIbox="close"></InfoBoxNewProject>
      </div>
      <div class="subWindow2" v-if="showTwo">
        <InfoBoxNewDomain @closeIbox="close"></InfoBoxNewDomain>
      </div>
      <div class="subWindow1" v-if="showOne">
        <InfoBoxUserInfo @closeIbox="close"></InfoBoxUserInfo>
      </div>
      <div class="subWindow1" v-if="showFour">
        <InfoBoxMassage @closeIbox="close"></InfoBoxMassage>
      </div>
    </div>
  </div>

</template>

<script>
  import TopMirrorSysUser from '@/components/TopMirrorSysUser'
  import NavMirrorSysUser from '@/components/NavMirrorSysUser'
  import {Button} from 'iview'
  import InfoBoxNewProject from '@/components/InfoBoxNewProject'
  import InfoBoxNewDomain from '@/components/InfoBoxNewDomain'
  import InfoBoxUserInfo from '@/components/InfoBoxUserInfo'
  import InfoBoxMassage from '@/components/InfoBoxMassage'
  export default{
    data () {
      return {
        UsageData: [
          {
            id: 1,
            name: '北航学生选课系统'
          },
        ],
        show: false,
        showThree: false
      }
    },
    components: {
      TopMirrorSysUser,
      NavMirrorSysUser,
      Button,
      InfoBoxNewProject,
      InfoBoxNewDomain,
      InfoBoxUserInfo,
      InfoBoxMassage
    },
    mounted () {
      this.close()
    },
    methods: {
      view: function (id) {
        // do something
        // 路由跳转
        this.$router.push({ name: 'project', params: {type: 'view'} })
      },
      showIbox (idx) {
        this.show = true
        if (idx === 3) {
          this.showThree = true
        }
        if (idx === 2) {
          this.showTwo = true
        }
        if (idx === 1) {
          this.showOne = true
        }
        if (idx === 4) {
          this.showFour = true
        }
      },
      close (idx) {
        this.show = false
        if (idx === 3) {
          this.showThree = false
        }
        if (idx === 2) {
          this.showTwo = false
        }
        if (idx === 1) {
          this.showOne = false
        }
        if (idx === 4) {
          this.showFour = false
        }
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
  .col-name {
    width: 20%;
  }
  .col-operate {
    width: 10%;
  }
  .col-operate>span {
    color: dodgerblue;
    cursor: pointer;
  }
  .box {
    position: fixed;
    top: 150px;
    left: 400px;
    width: 1000px;
    height: 700px;
    background-color: rgba(0,0,0,0.6);
  }
</style>
