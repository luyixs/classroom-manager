<template>
  <!--添加授课对话框  -->
  <el-dialog title="添加授课" :visible.sync="addDialogVisible" width="500px" :before-close="handleClose">
    <el-form label-width="80px" :model="lectureData"  method="post">
      <el-form-item label="教师ID:">
        <el-input  v-model="lectureData.teacherId" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="课程ID:" size="medium">
        <el-input placeholder="请输入课程ID" v-model='lectureData.cId'>
          <el-select v-model="lectureData.cId" @change="handleChangeCourseId" slot="prepend" placeholder="从已有的选择"
            style="width:150px ;">
            <el-option v-for="lecture in lectureOptions.myLectures" :key="lecture.cId" :label="lecture.cId"
              :value="lecture.cId">
              <span>{{ lecture.cId }}</span><span style="float: right; color: #8492a6; font-size: 13px">{{ lecture.cName
              }}</span>
            </el-option>
          </el-select>
          <el-button slot="append" icon="el-icon-refresh" @click="handleRefreshCourseId"></el-button>
        </el-input>
      </el-form-item>
      <el-form-item label="课程名称:" size="medium">
        <el-input placeholder="请输入课程名称" v-model="lectureData.cName" clearable></el-input>
      </el-form-item>
      <el-form-item label="学分:" size="medium" style="width: 280px;">
        <el-select v-model="lectureData.credit" placeholder="请选择">
          <el-option v-for="item in lectureOptions.credit" :key="item" :label="item" :value="item">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="人数限制:" size="medium">
        <el-input-number v-model="lectureData.limit" controls-position="right" :min="1" :max="200">
        </el-input-number>
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
      </el-form-item>
      <el-form-item label="选修类型" size="medium">
        <el-radio v-model="lectureData.type" label="必修">必修</el-radio>
        <el-radio v-model="lectureData.type" label="选修">选修</el-radio>
      </el-form-item>
      <el-form-item label="可选院系:" size="medium">
        <el-select v-model="lectureData.academy" placeholder="院系" size="mini" style="width: 100px;">
          <el-option v-for="academy in lectureOptions.academy" :key="academy" :label="academy" :value="academy">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="课程介绍:">
        <el-input type="textarea" placeholder="请输入课程介绍..." v-model="lectureData.cIntro" maxlength="200" show-word-limit>
        </el-input>

      </el-form-item>
    </el-form>

    <p class="red">
      <b>注意：</b><span>若只是添加课程节次，不修改课程，则课程ID、课程名称、人数限制、学分应与之前保持一致，只需要更改教室和上课时间，否则会创建一门新的课程而不是添加已有的课程节次</span>

    </p>

    <span slot="footer" class="dialog-footer">
      <el-button @click="addDialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="handleAddLecture">确 定</el-button>
    </span>

  </el-dialog>
</template>

<script>
import { nanoid } from 'nanoid';
import Cookies from 'js-cookie'
import axios from 'axios';
export default {
  components: {},
  props: {},
  data() {
    return {
      addDialogVisible: false, //是否显示添加授课对话框

      loginUser: null,

      lectureOptions: {
        week: ['周一', '周二', '周三', '周四', '周五'],
        section: ['第一大节', '第二大节', '第三大节', '第四大节', '第五大节', '第六大节'],
        // myLectures: [{cId: 'qwe123', cName: 'java'}, {cId: 'asd123', cName: 'C#'}],
        myLectures: [],
        credit: [1, 2, 3, 4],
        academy: ['全部', '计算机学院', '艺术学院', '医学院', '理学院'],
        lectureDayofWeek: '周一',
        lectureSection: '第一大节',
      },

      lectureData: {
        cId: '',
        cName: '',
        teacherId: '', //从后台获取
        credit: null,
        limit: null,
        classRoom: '',
        timeRange: '',
        type: '必修',
        academy: '',
        cIntro: '课程介绍...',
      },
    };
  },
  watch: {
    lectureOptions: {
      deep: true,
      immediate: true,
      handler(newVal, oldVal) {
        // console.log(newVal,' ',oldVal)
        this.lectureData.timeRange = newVal.lectureDayofWeek + '_' + newVal.lectureSection
      }
    }
  },
  computed: {},
  methods: {

    handleClose(done) {
      this.$confirm('确认关闭？').then(_ => {
        done();
      }).catch(_ => {
      });
    },
    handleChangeCourseId() {
      //根据选择的课程id填充对应的课程信息
    },
    handleRefreshCourseId() {
      this.lectureData.cId = nanoid(6)
      // 清除lectureData部分信息
    },
    //添加课程
    handleAddLecture() {
      console.log(this.lectureData)
      axios({ method: 'POST', url: '/lecture/add', data: { lecture: this.lectureData } }).then(rsp => {
        console.log(rsp.data)
        if (rsp.data.success) {
          this.$message({ message: '添加成功！', type: 'success' });
          this.addDialogVisible = false;
        } else {
          this.$message("添加失败")
        }
      }, err => {
        this.$message.error(err.message)
      }).finally(() => {
        this.$bus.$emit('getLectureList','') //通知leccture组件更新数据
      })
    },
    //弹出添加课程对话框初始化数据
    handleShowAddDialog(){
      //清空lectureData中的值
      Object.keys(this.lectureData).forEach(key => this.lectureData[key] = null)
      var sourceTpl = { teacherId: this.loginUser.id, type: '必修', cIntro: '课程介绍...',timeRange:'周一_第一大节' }
      //将默认模板分配给表单
      this.lectureData = Object.assign(this.lectureData, sourceTpl)
      if (this.loginUser.role == 'student') {
        this.$message("你的身份为学生，不能添加授课！");
        return;
      } else {
        this.addDialogVisible = true
      }
    },

    setLoginUser() {
      this.loginUser = this.getCookie('loginUser')
    },
    setCookie(key, value) {
      Cookies.set(key, JSON.stringify(value))
    },
    getCookie(key) {
      console.log('AddLecture getCookie 被调用')
      let cookieValue = Cookies.get(key)
      if (cookieValue == undefined) {
        return null //cookie未定义
      }
      return JSON.parse(Cookies.get(key))
    },

  },
  created() { },
  mounted() { 
    console.log('AddLecture 挂载')
    this.setLoginUser()
    this.$bus.$on('handleShowAddDialog',this.handleShowAddDialog) //接受弹出的通知
  },
  beforeDestroy(){
    this.$bus.$off('handleShowAddDialog')
  }
}
</script>

<style  scoped>
.red{
    color: red;
}</style>