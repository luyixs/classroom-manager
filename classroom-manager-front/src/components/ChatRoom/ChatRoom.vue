<template>
  <el-main>
    <!-- <h2>聊天室</h2> -->
    <!-- <el-switch v-model="isOnLine" active-text="进入聊天室" @change="handleOnLine" active-color="#13ce66"
      inactive-color="#ff4949">
    </el-switch> -->
    <span>聊天室</span>
    <br><br>
    <div class="chat-box">
      <ChatInput chatMsg="chatMsg"></ChatInput>
      <ChatUser></ChatUser>
    </div>
  </el-main>
</template>

<script>
import Cookies from 'js-cookie'
import ChatInput from './ChatInput.vue';
import ChatUser from './ChatUser.vue';


var webSocket;
let webSocketUrl = 'ws://localhost:8080/ws/'
// let webSocketUrl = 'ws://'+window.location.hostname+':8080/ws/'


export default {
  components: {
    ChatInput,
    ChatUser,

  },
  props: {},
  data() {
    return {
      isOnLine: false,
      chatMsg: [],
      onLineUsers: [],
      loginUser: null,
    };
  },
  watch: {},
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
        console.log('收到消息：' + e.data);
        // console.log(this) //写成匿名函数的this是vue  如果写成function形式，则this是websocket
        let msgjson = JSON.parse(e.data);
        if (!msgjson.hasOwnProperty('users')) {
          this.chatMsg.push(msgjson) //如果不是传来的用户列表信息则是聊天信息，将聊天信息添加到数组中
          this.$bus.$emit('handleReceive', this.chatMsg)
        }else{

          this.$bus.$emit('getUserList',msgjson.users)  
        }

      }
      webSocket.onerror = function () {
        console.log('onError');
      }
    },

    handleOnLine() {
      // this.isOnLine = !this.isOnLine
      console.log(this.isOnLine);
      if (this.isOnLine) {
        webSocket.onopen()
      } else {
        webSocket.close()
      }
    },
    handleSendMsg(sendMsg) {
      webSocket.send(JSON.stringify(sendMsg))
    },
    onMessage(data) {
      console.log(data)
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
    this.$bus.$on('handleSendMsg', this.handleSendMsg)
    this.$bus.$emit('setLoginUser',this.loginUser)
  },
  beforeDestroy() {
    this.$bus.$off('handleSendMsg')
  }
}
</script>

<style scoped>
.chat-box {
  display: flex;
  justify-content: start;
}
</style>