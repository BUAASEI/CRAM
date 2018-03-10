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
          <span class="focus-blue">当前项目：</span>
          <select class="select" v-model="pIdx">
            <option v-for="item in options" :value="item.id">{{ item.projectName }}</option>
          </select>
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
  .select {
    width: 80px;
    margin-right: 30px;
  }
</style>
<script>
  export default{
    data () {
      return {
        pIdx: null,
        options: null // 存放下拉框内容
      }
    },
    props: {
      kind: {
        type: String,
        default: '管理中心'
      },
      name: {
        type: String,
        default: '北航学生选课系统'
      }
    },
    components: {
    },
    mounted () {
      //请求项目类型
      let uId = localStorage.getItem('id')
      this.getProjects(uId)
    },
    watch: {
      pIdx: function (val) {
        this.$emit('chageProject', val)
      }
    },
    methods: {
      getProjects: function (id) {
        let obj =  {UserId: id}
        this.$http.post('man/proinfo', obj)
          .then((response) => {
            this.options = response.data
          })
          .catch((err) => {
            console.log(err)
          })
      }
    }
  }
</script>
