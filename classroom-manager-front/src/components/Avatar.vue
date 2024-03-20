<template>
  <!-- 头像盒子 -->
  <el-card class="avatar-box" v-if="null != loginUser" >
    <div>
      <el-avatar :size="80">{{ loginUser.name }}</el-avatar>
    </div>
    <div><br><span>{{ loginUser.id }}</span><br><span>{{ loginUser.role }}</span><br>
      <el-link href="" icon="el-icon-user" :underline="false">个人信息</el-link><br>
      <el-button type="text" @click="handleOutline">注销</el-button>
      <!-- <el-link th:href="@{/login/out}" icon="el-icon-remove-outline" :underline="false">注销</el-link> -->

    </div>
  </el-card>
</template>

<script>
import axios from 'axios'
import Cookies from 'js-cookie'
export default {
  components: {},
  props: [],
  data() {
    return {
      loginUser: null
    };
  },
  watch: {},
  computed: {},
  methods: {
    handleOutline() {
      axios({ method: 'GET', url: '/login/out' }).then(r => { 
        this.$message('注销成功')
        this.setCookie('loginUser',null)
        // this.setLoginUser()

        //切换组件 给IndexLogin发送消息
        // this.$bus.$emit('setLoginUser')
        //刷新页面
        location.href= location.origin
        console.log(this);
      }, err => { }).finally({
      })
    },

    setLoginUser() {
      this.loginUser = this.getCookie('loginUser')
    },
    setCookie(key, value) {
      Cookies.set(key, JSON.stringify(value))
    },
    getCookie(key) {
      // console.log('Avatar getCookie 被调用')
      let cookieValue = Cookies.get(key)
      if (cookieValue == undefined) {
        return null //cookie未定义
      }
      return JSON.parse(Cookies.get(key))
    },

  },
  created() {
  },
  mounted() {
    this.$bus.$on('setLoginUser', this.setLoginUser)
    this.setLoginUser();
  },
  beforeDestroy() {
    this.$bus.$off('setLoginUser')
  }
};
</script>

<style scoped>
.avatar-box {
  text-align: center;
}
</style>