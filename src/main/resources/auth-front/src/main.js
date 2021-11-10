import Vue from 'vue'
import App from './App.vue'
import router from './router'
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './assets/css/gloable.css'
import axios from 'axios'

axios.defaults.baseURL="http://localhost:8001/"
Vue.config.productionTip = false
Vue.prototype.$axios = axios
Vue.use(Element)
new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
