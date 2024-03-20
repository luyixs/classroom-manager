<template>
  <div class="">
    <h3>{{ name }}</h3>
    <input type="text" v-model="msg">
    <button @click="handleSendMsg">Send</button>
    <button @click="handleCloseSocket">Close</button>
  </div>
</template>


<script>
import { nanoid } from 'nanoid';

var webSocket;
let webSocketUrl = 'ws://localhost:8080/ws/'

export default {
  components: {},
  props: {},
  data() {
    return {
      msg: '',
      name: '',
      reviveMsg: '',
    };
  },
  watch: {},
  computed: {},
  methods: {
    initData() {
      // console.log(this)
      this.name = nanoid(3)
      if ('WebSocket' in window) {
        webSocketUrl = webSocketUrl + this.name
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
        console.log(webSocket)
      };
      webSocket.onmessage = function (e) {
        console.log('收到消息：' + e.data);
        if (typeof e.data === String) {
          console.log("Received data string");
        }
        if (e.data instanceof ArrayBuffer) {
          var buffer = e.data;
          console.log("Received arraybuffer");
        }
      }
      webSocket.onerror = function () {
        console.log('onError');
      }

      console.log(webSocket)
    },

    handleSendMsg() {
      webSocket.send(this.msg)
    },
    handleCloseSocket() {
      webSocket.close()
    }
    // onOpen(){
    //   console.log('onOpen')
    // },
    // onClose(){
    //   console.log('onClose');
    // },
    // onMessage(message){
    //   console.log('收到消息',message);
    //   this.reviveMsg=message
    // },
    // onError(){
    //   console.log('onError');
    // }
  },
  created() { },
  mounted() {
    this.initData()
  }
}
</script>

<style lang="scss" scoped></style>



<!-- 
后端onOpen发送信息格式 {"users": [{"username": "zhang"},{ "username": "admin"}]}

后端onMessage收到的信息格式 {"to": "admin", "text": "聊天文本"}

后端onMessage发送的信息格式 {"from": "zhang", "text": "hello"}

JSONUtil.paseObj(jsonstr) 将json字符串转换为 JSONObject
jsonobj.getStr(keystr) 从jsonobj中获取key的value值
jsonobj.set(key,value) 设置jsonobj的key和value 返回jsonobj








 -->