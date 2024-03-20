<template>
  <!--发起签到对话框  -->
  <el-dialog title="发起签到" :visible.sync="dialogVisible" width="500px" :before-close="handleClose">
    <el-form label-width="80px" :model="signInData" method="post" size="medium">
      <el-form-item label="教师ID:">
        <el-input v-if="loginUser != null" v-model="loginUser.id" :disabled="true"></el-input>
      </el-form-item>

      <el-form-item label="课程ID:" size="medium">
        <el-select v-model="signInData.cId" placeholder="请选择">
          <el-option v-for="lecture in myLectures" :key="lecture.cId" :label="lecture.cName" :value="lecture.cId">
            <span style="float: left">{{ lecture.cName }}</span>
            <span style="float: right; color: #8492a6; font-size: 12px">{{ lecture.cId }}</span>
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="限时:" size="medium">
        <el-input-number v-model="signInData.expire" controls-position="right" :min="1" :max="60">
        </el-input-number>分钟
      </el-form-item>
      <el-form-item label="签到码:" size="medium">
        <el-input placeholder="请输入内容" v-model="signInData.siCode" :disabled="false">
          <el-button slot="append" icon="el-icon-refresh" @click="refreshSignInCode"></el-button>
        </el-input>
      </el-form-item>




      <!-- <el-form-item label="课程名称:" size="medium">
        <el-input placeholder="请输入课程名称" v-model="lectureData.cName" clearable></el-input>
      </el-form-item>

            <el-form-item label="时间限制:" size="medium" style="width: 280px;">
        <el-select v-model="signInData.expire" placeholder="请选择">
          <el-option v-for="item in 20" :key="item" :label="item" :value="item">
          </el-option>
        </el-select>
      </el-form-item>


      <el-form-item label="教室:" size="medium">
        <el-input placeholder="请输入教室" v-model="lectureData.classRoom" clearable></el-input>
      </el-form-item>
      <el-form-item label="上课时间:" size="medium">
        <el-select v-model="lectureOptions.lectureDayofWeek" placeholder="周一" size="mini" style="width: 100px;">
          <el-option v-for="day in lectureOptions.week " :key="day" :label="day" :value="day"></el-option>
        </el-select>
        <el-select v-model="lectureOptions.lectureSection" placeholder="第一大节" size="mini" style="width: 100px;">
          <el-option v-for="section in lectureOptions.section" :key="section" :label="section" :value="section">
          </el-option>
        </el-select>
      </el-form-item> -->
    </el-form>


    <span slot="footer" class="dialog-footer">
      <el-button @click="dialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="handleAddSignIn">确 定</el-button>
    </span>

  </el-dialog>
</template>

<script>
import Cookies from 'js-cookie'
import axios from 'axios';
import { nanoid } from 'nanoid';

export default {
  components: {},
  props: {},
  data() {
    return {
      loginUser: null,
      dialogVisible: false,
      signInData: {
        siCode: '',
        cId: '',
        uId: '',
        createTime: '',
        expire: ''
      },
      myLectures: []
    };
  },
  watch: {},
  computed: {},
  methods: {



    showSignInDialog(myLectureList) {
      this.dialogVisible = true
      this.myLectures = myLectureList

    },

    handleClose(done) {
      this.$confirm('确认关闭？').then(_ => {
        done();
      }).catch(_ => {
      });
    },
    handleAddSignIn() {
      this.signInData.uId = this.loginUser.id;
      let date = new Date();
      this.signInData.createTime = date.toLocaleString();

      axios({ method: 'POST', url: '/signin/create', data: { signInData: this.signInData } }).then(rsp => {
        if (rsp.data.success) {
          this.$message({ message: '已创建签到！请通知学生签到', type: 'success' })
          this.$alert('这是签到码: ' + this.signInData.siCode + ' 请通知您的学生签到', '签到码', { confirmButtonText: '确定', });
          this.$bus.$emit('handleSetSignCode',this.signInData)
          this.$bus.$emit('updateSignState',rsp.data.data.userSignStates)
          
        }
        
        console.log(rsp.data);
        this.dialogVisible = false
      }, err => { this.$message.error(err.message) }).finally(() => {

      })
      // console.log(this.signInData);
    },
    handleChangeCourseId() { },
    refreshSignInCode() {
      this.signInData.siCode = nanoid(6)
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

    console.log('mounted');
    this.setLoginUser()


    this.$bus.$on('showSignInDialog', this.showSignInDialog)
  },
  beforeDestroy() {
    this.$bus.$off('showSignInDialog')
  }
}
</script>

<style lang="scss" scoped></style>