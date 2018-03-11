<template>
  <div>
    <div class="basic title">
      <span>cram >></span>
      <span class="focus-blue">{{ name }}</span>
    </div>
    <div class="basic info">
      <div class="left">
      </div>
      <div class="right">
        <div class="right-name">{{ kind }}：{{ name }}</div>
        <div class="right-tag">
          <span>领域：{{ domain }}</span>
          <span>创建时间：{{ dates }}</span>
          <span>创建人：{{ creator }}</span>
          <span>当前角色：{{ rolename }}</span>
          <!--<span class="focus-blue">切换角色
            <select v-model="selected">
              <option v-for="item in other_roles" v-bind:value="item">{{item}}</option>
            </select>
          </span>
          <span class="focus-blue">申请管理员</span>-->
          <span @click="postIbox" class="focus-btn">演化场景</span>

          <router-link to="/sysuser">个人中心</router-link>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped>
  .basic {
    text-align: left;
    border-bottom: 1px solid lightgray;
  }
  .focus-blue {
    color: blue;
  }
  .title {
    height: 30px;
    line-height: 30px;
  }
  .info {
    padding-top: 20px;
    height: 120px;
    display: flex;
    justify-content: space-between;
  }
  .left {
    width: 100px;
    height: 100px;
    background-image: url('../assets/slogo.jpg');
    background-size: cover;
  }
  .right {
    padding-left: 50px;
    flex-grow: 1;
  }
  .right-name {
    height: 80%;
    font-size: 36px;
  }
  .right-tag {
    height: 20%;
  }
  .right-tag>span {
    margin-right: 40px;
  }
  .focus-btn {
    padding: 5px;
    cursor: pointer;
    color: #fff;
    background-color: blue;
  }
</style>
<script>
export default{
  data () {
    return {
      userId: null,
      projectId: null
    }
  },
  props: {
    kind: {
      type: String,
      default: '项目'
    },
    name: {
      type: String,
      default: '北航学生选课系统'
    },
    domain: {
      type: String,
      default: '高等教育'
    },
    dates: {
      type: String,
      default: '2017-4-23'
    },
    creator: {
      type: String,
      default: 'buaa'
    },
    rolename: {
      type: String,
      default: '校教务部'
    }
  },
  components: {
  },
  mounted(){
    var userId = localStorage.getItem("id");
    //this.reqBasic()
    this.initData(userId,this.projectId);
  },
  methods: {
    // 调用新建项目弹窗
      postIbox: function() {
        console.log(9)
        this.$emit('showIbox', 1)
      },
    initData(userId,projectId){

      let body = {
        ProjectName: this.name,
        Description: this.discribe,
        DomainId: 1,
        CreatorId: this.userId
      };
      /*ajax*/
     /* this.$http.post('sysuser/crepro',body)
        .then((response) => {
          confirm(response.data.Msg);
        })*/

    },
    reqBasic: function () {
      this.$http.post('project/basicinfo', {projectId: this.projectId, userId: this.userId})
        .then((response) => {
          this.staticData = response.data
        })
    }
  }
}
</script>
