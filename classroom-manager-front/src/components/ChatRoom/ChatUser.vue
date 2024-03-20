<template>
  <div>
    <div class="chat-users">
      <div class="chat-useritem" v-for="user in userList">
        <el-avatar size="large">{{ user.uId }}</el-avatar>
        <div class="chat-user-text">{{ user.uId }}</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  components: {},
  props: {},
  data() {
    return {
      userList: []
    };
  },
  watch: {},
  computed: {},
  methods: {
    getUserList(users) {
      // console.log('ChatUser users '+users)
      this.userList = users
    }
  },
  created() { },
  mounted() {
    this.$bus.$on('getUserList', this.getUserList)
  },
  beforeDestroy() {
    this.$bus.$off('getUserList')
  }
}
</script>

<style  scoped>
.chat-users {
  margin-left: 10px;
  border: 1px solid rgb(231, 231, 231);
  border-radius: 5px;
  width: 200px;
  height: 50vh;
  overflow: hidden scroll;
  scrollbar-width: none;
  /* firefox 隐藏滚动条*/
  -ms-overflow-style: none;
  /*IE 10+ 隐藏滚动条*/

}

.chat-users::-webkit-scrollbar {
  display: none;
}

.chat-useritem {
  border: 1px solid rgb(231, 231, 231);
  padding: 5px;
  display: flex;
}

.chat-user-text {
  /* border: 1px solid grey; */
  margin-left: 10px;
}
</style>