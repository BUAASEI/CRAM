<template>
  <div>
    <TopManager name="buaa"></TopManager>
    <div class="context">
      <div class="context-nav">
        <NavManager target="st"></NavManager>
      </div>
      <div class="context-detail">
        <div class="detail">
          <div class="detail-body">
            <div class="detail-head">
              <div class="col-operate">操作</div>
              <div class="col-user">申请用户</div>
              <div class="col-role">申请角色</div>
              <div class="col-familiar">熟悉领域</div>
              <div class="col-experience">项目经验</div>
              <div class="col-explanation">说明</div>
            </div>
            <div class="detail-context">
              <div class="detail-item" v-for="item in UsageData" :key=item.id>
                <div class="col-operate">
                  <span @click="view(item.id)">同意</span>
                  <span>拒绝</span>
                </div>
                <div class="col-user">{{ item.user }}</div>
                <div class="col-role">{{ item.role }}</div>
                <div class="col-familiar">{{ item.familiar }}</div>
                <div class="col-experience">{{ item.experience }}</div>
                <div class="col-experience">{{ item.explanation }}</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import TopManager from '@/components/TopManager'
  import NavManager from '@/components/NavManager'
  import {Button} from 'iview'
  import InfoBoxNewProject from '@/components/InfoBoxNewProject'
  import InfoBoxNewDomain from '@/components/InfoBoxNewDomain'
  import InfoBoxUserInfo from '@/components/InfoBoxUserInfo'
  import InfoBoxMassage from '@/components/InfoBoxMassage'

  export default {
    data() {
      return {
        UsageData: [
          {
            id: 1,
            user: 'suruo',
            role: '学生',
            familiar: '需求获取',
            experience: 'CRAM',
            explanation: '我是一名在校研究生'
          },
        ]
      }
    },
    components: {
      TopManager,
      NavManager,
      Button,
      InfoBoxNewProject,
      InfoBoxNewDomain,
      InfoBoxUserInfo,
      InfoBoxMassage
    },
    mounted() {
      this.close()
    },
    methods: {
      view: function (id) {
        // do something
        // 路由跳转
        this.$router.push({name: 'project', params: {type: 'view'}})
      },
      showIbox(idx) {
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
      close(idx) {
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

  .detail-head, .detail-item {
    display: flex;
    justify-content: flex-start;
  }

  .detail-head {
    height: 50px;
    line-height: 50px;
    background-color: lightgray;
  }

  .detail-head > div {
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

  .detail-item > div {
    padding: 0 10px;
    border-left: 1px solid lightgray;
  }

  .col-user {
    width: 10%;
  }
  .col-role {
    width: 10%;
  }
  .col-familiar {
    width: 20%;
  }
  .col-experience {
    width: 40%;
  }
  .col-explanation {
    width: 40%;
  }
   .col-operate {
    width: 5%;
  }

  .col-operate > span {
    color: dodgerblue;
    cursor: pointer;
  }

  .box {
    position: fixed;
    top: 150px;
    left: 400px;
    width: 1000px;
    height: 700px;
    background-color: rgba(0, 0, 0, 0.6);
  }
</style>
