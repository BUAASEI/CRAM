<template>
  <div class="view">
    <div class="tree">
      <div class="title">演化历史</div>
      <Tree :data="data"></Tree>
    </div>
  </div>
</template>

<script>
  import {Tree} from 'iview'
  export default {
    name: "evolution",
    data() {
      return {
        data: [
        {
          title: 'parent 1',
          expand: true,
          children: [
            {
              title: 'parent 1-1',
              expand: true,
              children: [
                {
                  title: 'leaf 1-1-1',
                  expand: true,
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
              expand: true,
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
     Tree: Tree
    },
  }
</script>

<style scoped>
  .view {
    padding: 20px;
  }
  .title {
    font-size: 18px;
    font-weight: bolder;
  }
  .tree {
    text-align: left;
  }
</style>
