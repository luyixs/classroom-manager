<template>
  <div class="">
    <div class="md-container">
      <div>
        <el-input placeholder="请输入标题" v-model="note.title" size="mini">
          <template slot="prepend">标题</template>
        </el-input>
      </div>
      <br>
      <div>
        标签：<el-select v-model="noteTags" class="select-tag" size="mini" multiple filterable allow-create
          default-first-option placeholder="请选择文章标签">
          <el-option v-for="item in tagOptions" :key="item.value" :label="item.label" :value="item.value"></el-option>
        </el-select>
      </div>
      <br>

      <mavon-editor class="md" v-model="note.content" @save="handleSave">
        <!-- 左工具栏后加入自定义按钮  -->
        <template slot="left-toolbar-after">
          <el-button round @click="handlePublish" size="mini">发布</el-button>
        </template>
      </mavon-editor>
      <el-divider></el-divider>
      <!-- 回显 方式一 -->
      <!-- <mavon-editor v-model="note.content" navigation defaultOpen="preview" :toolbarsFlag="false" :editable="false"
      :subfield="false"></mavon-editor> -->
      <!-- 回显 方式二 -->
      <!-- <mavon-editor v-html="contentHTML"></mavon-editor> -->
    </div>
  </div>
</template>

<script>

import { mavonEditor } from 'mavon-editor'
import axios from 'axios';
import Cookies from 'js-cookie'
import nanoid from 'nanoid';
import { customAlphabet } from 'nanoid'

export default {
  components: {},
  props: {},
  data() {
    return {
      loginUser: null,
      note: {
        nId: '',
        uId: '',
        title: '',
        content: '',
        tag: '',
        editTime: '',
        isPublic: false
      },
      noteTags: [],
      contentHTML: '',
      tagOptions: [{
        value: 'Vue',
        label: 'Vue'
      }, {
        value: 'Spring Boot',
        label: 'Spring Boot'
      }, {
        value: 'Java',
        label: 'Java'
      }],
    };
  },
  watch: {
    noteTags: {
      handler(newVal, oldVal) {
        this.note.tag = JSON.stringify(newVal)
      },
      immediate: true
    }
  },
  computed: {
  },
  methods: {
    handleSave(v) {
      this.pushNote(this.note.isPublic)
      console.log(v)

    },
    handlePublish() {
      this.pushNote(true)
      this.$bus.$emit('getNotes');
      this.$bus.$emit('getMyNotes')
      console.log('publish')
    },
    handleSetNote(n) {
      console.log(n)
      // n.tag = JSON.stringify(n.tag)

      this.noteTags = n.tag
      this.note.nId = n.nId;
      this.note.uId = n.uId;
      this.note.title = n.title;
      this.note.content = n.content;
      this.note.editTime = n.editTime;
      this.note.isPublic = n.isPublic;

      //[BUG] [Vue warn]: Duplicate keys detected: '"'. This may cause an update error.  found in  ---> <NoteList> at .
      //有错，但是能运行???
      // this.noteTags = n.tag
    },

    pushNote(isPublic = false) {
      console.log(isPublic);
      this.note.uId = this.loginUser.id
      let date = new Date();
      this.note.editTime = date.toLocaleString();
      this.note.isPublic = isPublic
      this.note.tag = JSON.stringify(this.noteTags);
      axios({ method: 'POST', url: '/note/push', data: this.note }).then(rsp => {
        if (rsp.data.success) {
          console.log(rsp.data.data)
          this.$message({ message: '保存成功', type: 'success' })
          //收起该组件
          this.$bus.$emit('handleSetEditorVisible', false)
          this.$bus.$emit('getNotes');
          this.$bus.$emit('getMyNotes')

        }
      }, err => { this.$message.error(err.message) }).finally(() => {
        // this.note.tag = 
      })
    },

    generateNoteId() {
      const nanoid = customAlphabet('0123456789', 6) //挂载时随机生成笔记编号
      this.note.nId = nanoid();
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
    this.generateNoteId()
    this.note.tag = ''
    this.$bus.$on('handleSetNote', this.handleSetNote)
    // console.log(this.note);
    // const markdownIt = mavonEditor.getMarkdownIt()
    // markdownIt.set({ break: false })

  },
  beforeDestroy() {
    this.$bus.$off('handleSetNote')
  }
}
</script>

<style scoped>
.md-container {
  border: 1px solid rgb(229, 233, 248);
  height: 50vh;
  overflow: hidden scroll;
  margin-top: 10px;
}

.md {
  height: 50vh;
}

.select-tag {
  width: 20vw;
}
</style>