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
          title: '导出选课结果',
          expand: true,
          children: [
            {
              title: '相关数据',
              expand: true,
              children: [
                {
                  title: '选课结果',
                  expand: true,
                  children: [
                    {
                      title: 'leaf 1-1-1-1'
                    },
                    {
                      title: 'leaf 1-1-2-1'
                    }
                  ]
                }
                // ,
                // {
                //   title: 'leaf 1-1-2'
                // }
              ]
            },
            {
              title: '相关角色',
              expand: true,
              children: [
                {
                  title: 'leaf 1-2-1'
                },
                {
                  title: 'leaf 1-2-1'
                }
              ]
            },
            {
              title: 'Older： 查看选课结果',
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
        },
          {
            title: '选课',
            expand: true,
            children: [
              {
                title: '相关数据',
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
                title: '相关角色',
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
