import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import mavonEditor from 'mavon-editor';
import 'mavon-editor/dist/css/index.css';
import VueRouter from 'vue-router';
import router from '../../router'

Vue.config.productionTip = false
Vue.use(ElementUI) //使用Element UI
Vue.use(mavonEditor)  //使用MarkDown 
Vue.use(VueRouter)

new Vue({
  render: h => h(App),
  router:router,
  beforeCreate(){
    Vue.prototype.$bus = this
  }
}).$mount('#app')
