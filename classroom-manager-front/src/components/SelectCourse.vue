<template>
  <el-main>
    <h2>选课</h2>
    <div>
      <span>查询：</span>
      <el-input placeholder="Search..." size="medium" style="width: 200px;" suffix-icon="el-icon-search"
        v-model="searchWord">
      </el-input>
      <el-button round type="primary" size="medium" icon="el-icon-search" @click="handleSearch"></el-button>
    </div>
    <el-divider></el-divider>
    <div class="sc-container">
      <el-table :data="courseList" style="width: 100%" current-row-key="cId">
        <el-table-column prop="cId" label="课程ID" width="80px"></el-table-column>
        <el-table-column prop="cName" label="课程名称"></el-table-column>
        <el-table-column prop="teacherId" label="教师ID"></el-table-column>
        <el-table-column prop="credit" label="学分" width="50px"></el-table-column>
        <el-table-column prop="classRoom" label="教室"></el-table-column>
        <el-table-column prop="limit" label="人数" width="50px"></el-table-column>
        <el-table-column prop="type" label="选修类型"></el-table-column>
        <el-table-column prop="academy" label="学院"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button size="mini" @click="handleSelectCourse(scope.$index, scope.row)">选课
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </el-main>
</template>

<script>
import axios from 'axios'
import { nanoid } from 'nanoid';
import Cookies from 'js-cookie'
export default {
  components: {},
  props: {},
  data() {
    return {
      loginUser:null,
      searchWord: '',
      scData: {
        scId: '',
        cId: '',
        uId: '',
        score: 0
      },//选课提交数据
      pagination: {
        currentPage: 1,
        pageSize: 5,
        total: 30
      }, //分页
      courseList: [], //选课列表

    };
  },
  watch: {},
  computed: {},
  methods: {
    getCourseList(condition) {
      axios({ method: 'GET', url: '/sc/list', params: { q: condition } }).then(rsp => {
        // console.log(rsp.data)
        if (rsp.data.success) this.courseList = rsp.data.data;
      }, err => {
        this.$message.error(err.message)
      })
    },

    handleSelectCourse(index, row) {
      console.log(index, row,nanoid(6))
      this.scData.cId = row.cId;
      this.scData.uId = this.loginUser.id
      this.scData.scId = nanoid(6);
      axios({
        method: 'post',
        url: '/sc',
        data: this.scData
      }).then(rsp => {
        console.log('rsp:', rsp.data)
        if (rsp.data.success) {
          this.$message({ message: '选课成功！', type: 'success' });
        } else {
          this.$message("选择失败")
        }
      }, err => {
        this.$message.error(err.message)
      }).finally(() => {
      })
      console.log('scData', this.scData)
    },
    //查询课程
    handleSearch() {
      console.log(this.searchWord);
      this.getCourseList(this.searchWord)
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
    this.setLoginUser();
    this.getCourseList('');
   }
}
</script>

<style lang="scss" scoped></style>