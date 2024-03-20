<template>
  <el-main>
    <h2>我的课程</h2>

    <div>
      <span>查询：</span>
      <el-input placeholder="Search..." size="medium" style="width: 200px;" suffix-icon="el-icon-search"
        v-model="searchWord">
      </el-input>
      <el-button round type="primary" size="medium" icon="el-icon-search" @click="handleSearch"></el-button>
      <el-button round type="primary" size="medium" icon="el-icon-refresh" @click="getMyCourse('')"></el-button>
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
            <el-button size="mini" @click="handleDropCourse(scope.$index, scope.row)">退课
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>


  </el-main>
</template>

<script>
import axios from 'axios'
import nanoid from "nanoid";
export default {
  components: {},
  props: {},
  data() {
    return {
      searchWord: '',
      pagination: {
        currentPage: 1,
        pageSize: 5,
        total: 30
      }, //分页
      courseList: [], //选课列表
      roleOptions: [{ value: 'student', label: '学生' }, { value: 'teacher', label: '教师' }, {
        value: 'admin',
        label: '管理员'
      }],
      radio: 1,
      formData: {
        id: '',
        name: '',
        pwd: '',
        className: '',
        academy: '',
        major: '',
        tel: '',
        email: '',
        role: 'student'
      },

    };
  },
  watch: {},
  computed: {},
  methods: {

    getMyCourse(condition) {
      // console.log('getMycourse')
      axios({ method: 'GET', url: '/sc/mycourse/list', params: { q: condition } }).then(rsp => {
        console.log(rsp.data)
        if (rsp.data.success) this.courseList = rsp.data.data;
      }, err => {
        this.$message.error(err.message)
      })
    },
    //退课
    handleDropCourse(index, row) {
      // this.$message(index, row)
      console.log(index, row)
      axios({
        method: 'DELETE',
        url: '/sc',
        data: row.scId
      }).then(rsp => {
        console.log('rsp:', rsp.data)
        if (rsp.data.success) {
          this.$message({ message: '退课成功！', type: 'success' });
        } else {
          this.$message("退课失败")
        }
      }, err => {
        this.$message.error(err.message)
      }).finally(() => {
        this.getMyCourse("")
      })
    },

    //查询课程
    handleSearch() {
      console.log(this.searchWord);
      this.getMyCourse(this.searchWord);
    },
  },
  created() { },
  mounted() {
    this.getMyCourse("");
   }
}
</script>

<style lang="scss" scoped></style>