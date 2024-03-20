<template>
  <el-main>

    <!-- 选项卡 -->
    <el-tabs v-show="tabVisible" v-model="activeName" @tab-click="handleClick" stretch tab-position="top">
      <el-tab-pane label="推荐" name="notes">
        <el-divider><i class="el-icon-notebook-2"></i></el-divider>

        <NoteList :noteList="notes" :edit-mode="false"></NoteList>
      </el-tab-pane>
      <el-tab-pane label="我的" name="mynote">
        <el-button circle icon="el-icon-plus" @click="editNoteVisible = !editNoteVisible"></el-button>
        <EditNote v-if="editNoteVisible"></EditNote>
        <el-divider><i class="el-icon-notebook-2"></i></el-divider>
        <NoteList v-show="!editNoteVisible" :noteList="myNotes" :editMode="true"></NoteList>
      </el-tab-pane>
    </el-tabs>

    <!-- <NoteDetails v-show="detailVisible"></NoteDetails> -->

    <div>
      <router-view></router-view>
    </div>




  </el-main>
</template>

<script>
import Cookies from 'js-cookie'
import axios from 'axios';


import NoteList from './NoteList.vue';
import EditNote from './EditNote.vue';
import NoteDetails from './NoteDetails.vue';

export default {
  components: { NoteList, EditNote, NoteDetails },
  props: {},
  data() {
    return {
      loginUser: null,
      activeName: 'notes',
      tabVisible: true,
      editNoteVisible: false,
      detailVisible: false,
      notes: [],
      myNotes: []
    };
  },
  watch: {
    $route: {
      immediate: true,
      handler(to, from) {
        // console.log(to, from);
        if (to.path.startsWith('/d')) {
          this.tabVisible = false
        } else if (to.path == '/') {
          this.tabVisible = true
        }
      }
    }
  },
  computed: {},
  methods: {
    handleClick(tab, event) {
      this.setLoginUser()
      if (this.activeName == 'mynote' && this.loginUser == null) {
        this.$message('请先登录！')
        this.$nextTick(function () {
          this.activeName = 'notes'
        })
        // return
      }

    },
    handleSetEditorVisible(flag) {
      this.editNoteVisible = flag
    },
    handleTabVisible() {
      // this.tabVisible = !this.tabVisible
      // console.log(this.tabVisible)
    },

    getNotes() {
      // console.log('getNotes')
      axios({ method: 'GET', url: '/note/list', params: {} }).then(rsp => {
        if (rsp.data.success) {
          // console.log(rsp.data.data)
          this.notes = rsp.data.data.map(note => { note.tag = JSON.parse(note.tag) })
          this.notes = rsp.data.data
          // console.log(this.noteList)
        }
      }, err => { this.$message.error(err.message) }).finally(() => {
      })
    },
    getMyNotes() {
      // console.log('getMyNotes')
      if (this.loginUser == null) { return }
      axios({ method: 'GET', url: '/note/my', params:{"uId":this.loginUser.id}}).then(rsp => {
        if (rsp.data.success) {
          this.myNotes = rsp.data.data.map(note => { note.tag = JSON.parse(note.tag) })
          this.myNotes = rsp.data.data
        }
      }, err => { this.$message.error(err.message) }).finally(() => {
      })
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
    this.getNotes()
    this.getMyNotes()
    this.$bus.$on('handleSetEditorVisible', this.handleSetEditorVisible)
    this.$bus.$on('getNotes',this.getNotes)
    this.$bus.$on('getMyNotes',this.getMyNotes)
  },
  beforeDestroy() {
    this.$bus.$off('handleSetEditorVisible')
    this.$bus.$off('getNotes',this.getNotes)
    this.$bus.$off('getMyNotes',this.getMyNotes)
  },


}
</script>

<style scoped>
.relative {
  position: relative;
}

.s {
  height: 1000px;
}

.el-tag {
  margin: 0px 2px;
  /* text-align: end; */
  display: inline-block;
  /* border: 1px solid red; */
}
</style>


<!--
  【BUG】 根据路由动态显示tab 和detail组件



 -->