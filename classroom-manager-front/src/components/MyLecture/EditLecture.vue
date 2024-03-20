<template>
  <!--编辑对话框-->
  <el-dialog th:title="编辑课程" :visible.sync="editDialogVisible" width="500px" :before-close="handleClose">

    <el-form label-width="80px" :model="lectureData"  method="post">
      <el-form-item label="教师ID:">
        <el-input th:text="${session.loginUser.getId()}" v-model="lectureData.teacherId" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="课程ID:" size="medium">
        <el-input placeholder="请输入课程ID" v-model="lectureData.cId" :disabled="true"></el-input>
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

    <span slot="footer" class="dialog-footer">
      <el-button @click="editDialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="handleUpdateLecture">确 定</el-button>
    </span>


  </el-dialog>
</template>

<script>
import Cookies from 'js-cookie'
import axios from 'axios';
export default {
  components: {},
  props: {},
  data() {
    return {

      editDialogVisible: false, //编辑对话框

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
    handleShowEditDialog(lecture) {
      this.lectureData = lecture //将选中的课程传给lectureData
      this.editDialogVisible = true

    },
    //编辑课程
    handleUpdateLecture() {
      // console.log(this.lectureData)
      axios({ method: 'POST', url: '/lecture/upd', data: this.lectureData }).then(rsp => {
        console.log(rsp.data)
        if (rsp.data.success) {
          this.$message({ message: '编辑成功！', type: 'success' });
          this.editDialogVisible = false;
        } else {
          this.$message("编辑失败")
        }
      }, err => {
        this.$message.error(err.message)
      }).finally(() => {
        this.$bus.$emit('getLectureList','') //通知leccture组件更新数据
      })
    },

  },


created() { },
mounted() {
  console.log('EditLecture 挂载')
  this.$bus.$on('handleShowEditDialog', this.handleShowEditDialog) //接受弹出的通知
},
beforeDestroy(){
  this.$bus.$off('handleShowEditDialog')
}
}
</script>

<style lang="scss" scoped></style>