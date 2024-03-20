<template>
  <div class="chat-container">
    <div class="chat-msg">
      <ul>
        <li class="chat-msg-item" v-for="item in chatMsg">

          <div class="chat-msg-other" v-if="item.from != loginUser.id">
            <el-avatar size="large"> {{ item.from }}</el-avatar>
            <div class="chat-msg-other-text chat-text">{{ item.text }}</div>
          </div>
          <div class="chat-msg-own" v-if="item.from == loginUser.id">
            <div class="chat-msg-own-text chat-text">{{ item.text }}</div>
            <el-avatar size="large">{{ item.from }}</el-avatar>
          </div>

        </li>
      </ul>
    </div>

    <div class="chat-input">

      <el-input class="textarea" type="textarea" @keyup.ctrl.enter.native="handleSend" :rows="4" placeholder="请输入内容(Ctrl+Enter发送)" v-model="sendMsg.text"  clearable>
      </el-input>
      <el-button class="sendbtn" icon="el-icon-s-promotion" circle @click="handleSend"></el-button>

    </div>

  </div>
</template>

<script>
import Cookies from 'js-cookie'
export default {
  components: {},
  props: [],
  data() {
    return {
      sendMsg: {
        isMassMsg: true,
        from: '',
        to: '',
        text: '',
      },
      chatMsg: [],
      loginUser: null
    }
  },
  watch: {},
  computed: {},
  methods: {

    setLoginUser(loginUser) {
      this.loginUser = loginUser
    },

    handleSend() {
      if(this.loginUser==null){
        this.$message('未登录，请登录后使用该功能')
        return
      }
      this.sendMsg.from = this.loginUser.id
      this.$bus.$emit('handleSendMsg', this.sendMsg)
      this.sendMsg.text = ''
    },
    handleReceive(chatMsg) {
      // console.log('ChatInput handleReceive收到消息:' + chatMsg)
      this.chatMsg = chatMsg
    },


  },
  created() { },
  mounted() {
    this.$bus.$on("handleReceive", this.handleReceive)
    this.$bus.$on("setLoginUser", this.setLoginUser)
  },
  beforeDestroy() {
    this.$bus.$off('handleReceive')
    this.$bus.$off('setLoginUser')
  }

}
</script>

<style scoped>
.chat-container {
  width: 70%;
  height: 55vh;
  /* border: 1px solid red; */

}

.chat-msg {
  height: 40vh;
  border: 1px solid rgb(231, 231, 231);
  border-radius: 5px;
  padding: 5px;
  overflow: hidden scroll;
  scrollbar-width: none;
  /* firefox 隐藏滚动条*/
  -ms-overflow-style: none;
  /*IE 10+ 隐藏滚动条*/
}

.chat-msg ul{
  /* border: 1px solid red; */
  padding: 0;
  
}

.chat-msg::-webkit-scrollbar {
  display: none;
  /*Chrome fafari 隐藏滚动条*/
}

.chat-input {
  height: 100px;
  /* background-color: rgb(231, 231, 231); */
  /* border: 1px solid rgb(32, 104, 111); */
  position: relative;
  /* overflow: hidden; */
}

.chat-msg-item {
  width: 100%;
  /* border: 1px solid; */
  list-style: none;
  /* margin: 5px 0px 0px 0px; */
}

.chat-msg-other {
  display: flex;
  justify-content: start;
  align-items: center;
}

.chat-msg-own {
  display: flex;
  justify-content: end;
  align-items: center;
}

.chat-text {
  background-color: rgb(117, 222, 117);
  color: rgb(93, 91, 91);
  font-size: 12px;
  font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  height: 30px;
  line-height: 30px;
  border-radius: 30px;
  padding: 0px 5px;
}

.chat-msg-other-text,.chat-msg-own-text{
  margin:0px  10px;

}

.chat-msg-own-text {
  /* margin-right: 5px; */
}



.sendbtn {
  position: absolute;
  right: 10px;
  bottom: 10px;
}
</style>