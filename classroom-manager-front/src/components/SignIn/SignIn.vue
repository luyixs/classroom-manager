<template>
  <el-main>
    <h3>签到</h3>
    <div class="sign-container">
      <el-row>
        <el-steps :active="3" simple class="el-steps">
          <el-step title="教师发起签到" icon="el-icon-plus"></el-step>
          <el-step title="学生签到" icon="el-icon-s-claim"></el-step>
          <el-step title="签到完成" icon="el-icon-finished"></el-step>
        </el-steps>
      </el-row>
      <br>
      <el-row>
        <el-tooltip class="item" effect="dark" content="只有教师才可以发起签到" placement="top-start">
          <el-button circle icon="el-icon-plus" @click="handleShow"></el-button>
        </el-tooltip>

        <el-tooltip class="item" effect="dark" content="学生签到" placement="top">
          <el-button round icon="el-icon-s-claim" @click="handleShowSign">签到</el-button>
        </el-tooltip>
      </el-row>


      <SignInStateList v-if="signInStateListVisible"></SignInStateList>

      <el-result v-if="isSigned" icon="success" title="签到成功" subTitle="您已签到成功">
        <template slot="extra">
        </template>
      </el-result>




      <el-dialog title="输入签到码" :visible.sync="stuDialogVisible" width="500px" :before-close="handleClose">
        <el-input v-model="inputCode"></el-input>
        <div class="btn-box">
          <el-button @click="stuDialogVisible = false">取 消</el-button>
          <el-button @click="handleSigned">确定</el-button>
        </div>
      </el-dialog>






    </div>
  </el-main>
</template>

<script>

var webSocket;
let webSocketUrl = 'ws://localhost:8080/signin/'
let state = { SIGNED: '已签', NOTSIGNED: '未签' }


import Cookies from 'js-cookie'
import axios from 'axios';
import SignInStateList from './SignInStateList.vue';


export default {
  components: { SignInStateList },
  props: {},
  data() {
    return {
      loginUser: null,
      stuDialogVisible: false,
      inputCode: '',
      userSignState: {
        user: null,
        code: '',
        state: '',
      },
      myLectures: [],
      signInStateListVisible: false,
      isSigned: false

    };
  },
  watch: {
    loginUser(newVal, oldVal) {
      this.signInStateListVisible = newVal.role == 'teacher'
    }
  },
  computed: {},
  methods: {
    initData() {
      if (this.loginUser == null) {
        this.$message("请先登录后在使用该功能")
        return
      }
      if ('WebSocket' in window) {
        webSocketUrl = webSocketUrl + this.loginUser.id
        if (webSocket != null) {
          webSocket.close();
          webSocket = null;
        }
        webSocket = new WebSocket(webSocketUrl)
      } else {
        console.log("Not support WebSocket");
      }
      webSocket.onopen = function () {
        console.log("onOpen");
      }
      webSocket.onclose = function () {
        webSocket.close()
        console.log('close');
      };

      webSocket.onmessage = (e) => {
        // console.log('收到消息：' + e.data);
        let data = JSON.parse(e.data)
        console.log(data)
        if (data.hasOwnProperty('userSignStates')) {
          this.$bus.$emit('updateSignState', data.userSignStates)
        }

      }
      webSocket.onerror = function () {
        console.log('onError');
      }
    },
    handleShow() {
      if (this.loginUser == null) {
        this.$message('请先登录后使用该功能！')
        return;
      } else if (this.loginUser.role == "student") {
        this.$message('你的身份为学生，不能发起签到！')
        return;
      }
      this.$bus.$emit('showSignInDialog', this.myLectures)
    },
    //输入签到码对话框
    handleShowSign() {
      if (this.loginUser == null) {
        this.$message('请先登录后使用该功能！')
        return;

      } else if (this.loginUser.role == "teacher") {
        this.$message('你的身份为教师，你只需要发起签到')
        return;
      }

      this.stuDialogVisible = true
    },
    handleClose(done) {
      this.$confirm('确认关闭？').then(_ => {
        done();
      }).catch(_ => {
      });
    },
    handleSigned() {
      this.userSignState.user = this.loginUser
      this.userSignState.code = this.inputCode.trim()
      this.userSignState.state = state.SIGNED;
      axios({ method: 'POST', url: '/signin/signed', data: this.userSignState }).then(rsp => {
        if (rsp.data.success) {
          this.$message({ message: '签到成功！', type: 'success' })
          this.stuDialogVisible = false
          this.isSigned = true
        }
      }, err => {
        this.$message.error(e.message)
      }).finally({})

    },
    //获取我的授课列表
    getLectureList(condition) {
      if (this.loginUser != null && this.loginUser.role == "teacher") {
        console.log('getLectureList run...')
        axios({ method: 'GET', url: '/lecture/list', params: { q: condition } }).then(rsp => {
          console.log(rsp.data)
          if (rsp.data.success) this.myLectures = rsp.data.data
        }, err => {
          this.$message.error(err.message)
        })
      }

    },
    getSignState() {
      if (this.loginUser != null && this.loginUser.role == 'teacher') {
        axios({ method: 'GET', url: '/signin/state', params: { uid: this.loginUser.id } }).then(rsp => {
          if (rsp.data.success) {
            console.log(rsp.data)
            this.$bus.$emit('handleSetSignCode', rsp.data.data)
          }
        }, err => { this.$message.error(err.message) })
      }
    },




    setLoginUser() {
      this.loginUser = this.getCookie('loginUser')
    },
    setCookie(key, value) {
      Cookies.set(key, JSON.stringify(value))
    },
    getCookie(key) {
      let cookieValue = Cookies.get(key)
      if (cookieValue == undefined) {
        return null //cookie未定义
      }
      return JSON.parse(Cookies.get(key))
    },


  },
  created() { },
  mounted() {
    this.setLoginUser()
    this.initData()
    this.getLectureList('')
    this.getSignState()
  }
}
</script>

<style  scoped>
/* 步骤条 */
.el-steps {
  background-color: white;
}

.sign-container {
  /* height: 1000px; */
  border: 1px solid rgb(255, 255, 255);
}

.btn-box {
  /* border: 1px solid red; */
  display: flex;
  justify-content: end;
  margin-top: 20px;
}
</style>