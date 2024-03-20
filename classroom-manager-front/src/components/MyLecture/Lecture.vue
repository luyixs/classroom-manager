<template>
  <el-main>
    <h2>我的授课</h2>
    <div>
      <span>查询：</span>
      <el-input placeholder="Search..." size="medium" style="width: 200px;" suffix-icon="el-icon-search"
        v-model="searchWord">
      </el-input>
      <el-button round type="primary" size="medium" icon="el-icon-search" @click="handleSearch"></el-button>
      <el-button round type="primary" size="medium" icon="el-icon-plus" @click="handleOpenAddDialog">添加授课
      </el-button>
    </div>

    <el-divider></el-divider>

    <div class="lecture-container">
      <div class="lecture-item" v-for="lecture in myLectureList">
        <h4>{{ lecture.cName }}</h4>
        <span>课程ID：{{ lecture.cId }}</span><br>
        <span>教师ID：{{ lecture.teacherId }}</span>
        <div class="btn-group">
          <el-button type="primary" icon="el-icon-edit" circle @click="handleOpenEditDialog(lecture)"></el-button>
          <template>
            <el-popconfirm @confirm="handleDeleteLecture(lecture.cId)" icon="el-icon-info" icon-color="red"
              title="您确定要删除该授课吗？该课程对应的所有学生的选课记录也会被删除！">
              <el-button type="danger" icon="el-icon-delete" circle slot="reference"></el-button>
            </el-popconfirm>
          </template>
        </div>
      </div>
    </div>
  </el-main>
</template>

<script>
import axios from 'axios';
export default {
  components: {},
  props: {},
  data() {
    return {
      searchWord: '',
      myLectureList: [],

    };
  },
  watch: {},
  computed: {},
  methods: {
    //获取我的授课列表
    getLectureList(condition) {
      console.log('getLectureList run...')
      axios({ method: 'GET', url: '/lecture/list', params: { q: condition } }).then(rsp => {
        console.log(rsp.data)
        if (rsp.data.success) this.myLectureList = rsp.data.data
      }, err => {
        this.$message.error(err.message)
      })
    },
    //查询
    handleSearch() {
      this.getLectureList(this.searchWord)
    },

    //删除课程
    handleDeleteLecture(cId) {
      this.$message(cId)
      axios({ method: 'DELETE', url: '/lecture/del', params: { cId: cId } }).then(rsp => {
        console.log(rsp.data)
        if (rsp.data.success) this.$message({ message: '删除成功！', type: 'success' })
      }, err => {
        this.$message.error(err.message)
      }).finally(() => {
        this.getLectureList("")
      })

    },

    //弹出添加课程对话框
    handleOpenAddDialog() {
      this.$bus.$emit('handleShowAddDialog')
    },

    //弹出编辑课程对话框
    handleOpenEditDialog(course) {
      this.$bus.$emit('handleShowEditDialog', course)
    },
  },
  created() {
  },
  mounted() {
    console.log('Lecture 挂载')
    this.getLectureList('')
    this.$bus.$on('getLectureList', this.getLectureList)
  },
  beforeDestroy() {
    this.$bus.$off('getLectureList')
  }
}

</script>

<style  scoped>
/* Mylecture */

/* lecture-container */
.lecture-container {
  /* height: 70vh; */
  overflow: hidden scroll;
  display: flex;
  flex-wrap: wrap;
  /* justify-content: flex-start; */
  /* background-color: rgb(186, 197, 219); */
}

/* lecture-item */
.lecture-item {
  background-color: white;
  border: 1px solid #afcff6;
  border-radius: 5px;
  width: 220px;
  height: 150px;
  margin: 10px;
  transition: .2s;
  text-align: center;
  overflow: hidden;
}

.lecture-item>h4 {
  color: lightslategray;
}

.lecture-item>span {
  font-size: 12px;
}

.lecture-item>.btn-group {
  /* display: none; */
  display: flex;
  justify-content:space-evenly;
  transform: translateY(50px);
  transition: .2s;
}

.lecture-item:hover {
  box-shadow: 5px 5px 10px #afcff6;
}

.lecture-item:hover .btn-group {
  transform: translateY(0);
}
</style>