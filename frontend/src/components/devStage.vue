<template>
  <div class="view">
    <div class="head">
      <span class="title">演化场景</span>
      <span class="close" @click="close">X</span>
    </div>
    <div class="row part1">
      <div class="colum1">UseCase</div>
      <div class="colum2">
        <Select v-model="useCaseId">
          <Option v-for="item in ucOptions" :value="item.id" :key="item.id">{{ item.name }}</Option>
        </Select>
      </div>
    </div>
    <div class="row">
      <div class="colum1">问题</div>
      <div class="colum2">
        <Select v-model="problemId">
          <Option v-for="item in problemOptions" :value="item.id" :key="item.id">{{ item.name }}</Option>
        </Select>
      </div>
    </div>
    <div class="row">
      <div class="colum1">解决方案</div>
      <div class="colum2">
        <Select v-model="solutionId">
          <Option v-for="item in solutionOptions" :value="item.id" :key="item.id">{{ item.name }}</Option>
        </Select>
      </div>
    </div>
    <div class="relCase">
      <div class="row">
        <div class="colum1">Name</div>
        <div class="colum2">
          <Input v-model="name"></Input>
        </div>
      </div>
      <div class="row">
        <div class="colum1">Description</div>
        <div class="colum2">
          <Input v-model="description"></Input>
        </div>
      </div>
      <div class="row">
        <div class="colum1">Actor</div>
        <div class="colum2">
          <Select v-model="actor">
            <Option v-for="item in actors" :value="item.id" :key="item.id">{{ item.name }}</Option>
          </Select>
        </div>
      </div>
      <div class="row">
        <div class="colum1">Second Actor</div>
        <div class="colum2">
          <Select v-model="primaryActor" multiple>
            <Option v-for="item in primarys" :value="item.id" :key="item.id">{{ item.name }}</Option>
          </Select>
        </div>
      </div>
      <div class="row">
        <div class="colum1">Dictionary</div>
        <div class="colum2">
          <Select v-model="dictionary" multiple>
            <Option v-for="item in dicts" :value="item.id" :key="item.id">{{ item.name }}</Option>
          </Select>
        </div>
      </div>
    </div>
    <div class="btn">
      <Button @click="saveData">保存</Button>
      <Button @click="cancel">取消</Button>
    </div>
  </div>
</template>
<style scoped>
  .view {
    width: 70%;
    margin: 20px auto;
    padding: 20px;
    background-color: white;
  }
  .head {
    padding-right: 10px;
    font-size: 18px;
    color: blue;
  }
  .head span {
    cursor: pointer;
  }
  .part1 {
    margin: 5px 0;
    border-bottom: 2px solid gold;
  }
  .row {
    display: flex;
    justify-content: flex-start;
    height: 50px;
    line-height: 50px;
  }
  .colum1 {
    width: 25%;
    text-align: left;
  }
  .colum2 {
    flex-grow: 1;
  }
  .btn {
    margin-top: 20px;
  }
  .close {
    float: right;
  }
  .relCase {
    margin-top: 10px;
    padding-top: 10px;
    border-top: 2px dashed green;
  }
</style>
<script>
  import {Select, Option, Button, Input} from 'iview'
  export default{
    data () {
      return {
        useCaseId: null,
        problemId: null,
        solutionId: null,
        name: null,
        description: null,
        actor: null,
        primaryActor: null,
        dictionary: null,
        ucOptions: [],
        problemOptions: [],
        solutionOptions: [],
        actors: [],
        primarys: [],
        dicts: []
      }
    },
    components: {
      Select,
      Option,
      Button,
      Input
    },
    methods: {
      saveData: function () {

      },
      cancel: function () {

      },
      close: function () {
        this.$emit("closeDs")
      }

    }
  }
</script>
