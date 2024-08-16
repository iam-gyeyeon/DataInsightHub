import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import axios from "./utils/axios"; // Axios 인스턴스 import

import CoreuiVue from '@coreui/vue'
import CIcon from '@coreui/icons-vue'
import { iconsSet as icons } from '@/assets/icons'
import DocsExample from '@/components/DocsExample'

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.use(CoreuiVue)

// Axios를 전역적으로 등록
app.config.globalProperties.$axios = axios; // `$axios` 이름으로 등록

app.provide('icons', icons)
app.component('CIcon', CIcon)
app.component('DocsExample', DocsExample)

app.mount('#app')
