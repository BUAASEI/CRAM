// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import 'iview/dist/styles/iview.css'
import VueResource from 'vue-resource'
import './style/style.css'


Vue.config.productionTip = false
Vue.use(VueResource)
Vue.http.options.root = 'http://192.168.109.190:8088/cram/';

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
