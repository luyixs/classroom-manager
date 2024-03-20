<template>
  <!-- 登录盒子 -->
  <el-card class="login-box" v-if="null == loginUser">
    <el-form ref="form" label-width="50px" :model="formData" action="http://localhost:8080" method="post">
      <el-form-item label="ID:" size="medium">
        <el-input placeholder="请输入学号/教工号" v-model="formData.id" clearable></el-input>
      </el-form-item>
      <el-form-item label="密码:" size="medium">
        <el-input placeholder="请输入密码" v-model="formData.pwd" show-password></el-input>
      </el-form-item>
      <el-form-item label="身份:">
        <el-select v-model="formData.role" placeholder="请选择" size="mini" style="width: 100px;">
          <el-option v-for="item in roleOptions" :key="item.value" :label="item.label" :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button  @click="handleLogin">登录</el-button>
        <el-button  @click="handleRegister">注册</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
import axios from 'axios'
import Cookies from 'js-cookie'
// import key from '../assets/js/key'
export default {
  components: {},
  props: [],
  data() {
    return {
      loginUser: null,
      roleOptions: [{ value: 'student', label: '学生' }, { value: 'teacher', label: '教师' }, {
        value: 'admin',
        label: '管理员'
      }],
      formData: {
        id: '',
        name: '',
        pwd: '',
        className: '',
        academy: '',
        major: '',
        tel: '',
        email: '',
        role: 'student'
      },
    };
  },
  watch: {},
  computed: {},
  methods: {

    handleLogin() {
      // console.log(this.formData);
      axios({
        method: 'POST',
        url: '/login/',
        data: {
          id: this.formData.id,
          pwd: this.formData.pwd,
          role: this.formData.role
        }
      }).then(rsp => {
        // console.log(rsp.data)
        if (rsp.data.success) {
          this.$message({ message: '登录成功！', type: 'success' })
          this.setCookie('loginUser',rsp.data.data) //设置后端返回的登录用户信息cookie
          this.setLoginUser()   //设置data中的用户信息
          this.$bus.$emit('setLoginUser') //通知Avatar设置用户信息
          
          //   //重新获取session和设置cookie
          //  this.getSessionLoginUser()
          //跳转页面
          // this.$bus.$emit('redirectURL', '/index')
        } else {
          this.$message("登录失败，请检查用户名和密码是否正确")
        }
      }, err => {
        this.$message.error(err.message)
      })
    },
    handleRegister() {
      this.redirectURL('/login')
    },

    redirectURL(url) {
      console.log(window.location.origin + url)
      window.location.href = window.location.origin + url;
    },


    setLoginUser() {
      this.loginUser = this.getCookie('loginUser')
    },

    setCookie(key, value) {
      Cookies.set(key, JSON.stringify(value))
    },
    getCookie(key) {
      // console.log('IndexLogin getCookie 被调用')
      let cookieValue = Cookies.get(key)
      if (cookieValue == undefined) {
        return null //cookie未定义
      }
      return JSON.parse(Cookies.get(key))
    },
    //获取后台服务器session 并且更新前台服务器cookie
    getSessionLoginUser() {
      console.log('IndexLogin getSessionLoginUser run...');
      axios({ method: 'GET', url: '/session/loginUser' }).then(rsp => {
        this.loginUser = rsp.data
        console.log(rsp.data);
        this.setCookie('loginUser', this.loginUser)
      }, err => { this.$message.error(err.message) }).finally(() => { console.log('loginUser:' + this.getCookie('loginUser')) })
    },



  },
  created() { },
  mounted() {
    this.setLoginUser()
    // this.$bus.$on('setLoginUser',this.setLoginUser)
  }
}
</script>

<style></style>