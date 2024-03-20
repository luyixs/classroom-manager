<template>
  <div class="">
    <br>
    <div>
      <span class="signinfo" v-if="stuStates.length != 0">【正在进行的签到】 签到码：{{ signInData.siCode }} 课程ID：{{ signInData.cId }}
        发起者：{{ signInData.uId }} 创建时间：{{ signInData.createTime }} 期限：{{ signInData.expire }}分钟 </span>
      <el-table :data="stuStates" style="width: 100%" current-row-key="id">
        <el-table-column prop="user.id" label="学号"></el-table-column>
        <el-table-column prop="user.name" label="姓名"></el-table-column>
        <el-table-column prop="user.className" label="班级"></el-table-column>
        <el-table-column prop="state" label="签到状态"></el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import Cookies from 'js-cookie'

export default {
  components: {},
  props: {},
  data() {
    return {
      loginUser: null,
      signInData: {
        siCode: '',
        cId: '',
        uId: '',
        createTime: '',
        expire: ''
      },
      stuStates: []
    };
  },
  watch: {},
  computed: {},
  methods: {
    updateSignState(stateList) {
      this.stuStates = []
      stateList.forEach(el => {
        this.stuStates.push(JSON.parse(el))
      })
      console.log(this.stuStates)
    },
    handleSetSignCode(data) {
      this.signInData = data
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
    this.$bus.$on('updateSignState', this.updateSignState)
    this.$bus.$on('handleSetSignCode', this.handleSetSignCode)
  },
  beforeDestroy() {
    this.$bus.$off('updateSignState')
    this.$bus.$off('handleSetSignCode')
  }
}
</script>

<style>
.signinfo {
  font-size: 12px;
  color: grey;
}
</style>