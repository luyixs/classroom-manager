<template>
  <div class="note-container">
    <div class="note-item" v-for="note in noteList " :key="note.nId" @click="handleToDetail(note.nId)">
      <div>
        <!-- <router-link :to="{path:'/detail',query:{nId:note.nId}}">{{ note.title }}</router-link> -->
        <h3 class="title">
          <router-link class="title-link" :to="{ name: 'd', params: { nId: note.nId } }">{{ note.title }}</router-link>
          <div class="tag-container">
            <el-tag v-for="tag in note.tag" :key="tag" effect="light" size="mini">{{ tag }}</el-tag>
          </div>
        </h3>
      </div>
      <div class="info"><span class="author">笔者：{{ note.uId }}&nbsp;&nbsp; 编辑于：{{ note.editTime }}</span></div>
      <div class="content">{{ note.content }}</div>
      <div class="operation" v-if="editMode">
        <el-button icon="el-icon-edit" circle @click="handleEdit(note)"></el-button>
        <el-button icon="el-icon-delete" circle @click="handleDelete(note.nId)"></el-button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  components: {},
  props: ['noteList', 'editMode'],
  data() {
    return {
      // noteList: null,
      myNoteList: [],
    };
  },
  watch: {},
  computed: {},
  methods: {

    handleToDetail(nId) {
    },


    handleEdit(note) {
      // console.log(note)
      //向Note组件发送消息进入编辑模式
      this.$bus.$emit('handleSetEditorVisible',true)
      //将note传入EditNote组件中
      this.$nextTick(function(){
        this.$bus.$emit('handleSetNote',note)
      })
    },

    handleDelete(nId) {
      console.log(nId)
      axios({ method: 'DELETE', url: '/note/del', data: nId }).then(
        rsp => {
          console.log(rsp.data)
          this.$bus.$emit('getNotes'); this.$bus.$emit('getMyNotes')
        },
        err => { this.$message.error(err.message) }).finally(() => {

        })
    }

  },
  created() { },
  mounted() {
    // console.log(this.noteList);
  }
}
</script>

<style scoped>
.note-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.note-item {
  height: 100px;
  width: 95%;
  background-color: rgb(255, 255, 255);
  box-shadow: 0px 0px 5px 5px #f1f1f1;
  /* border: 1px solid red; */
  margin: 10px 0px;
  border-radius: 10px;
  transition: .3s;
  padding: 0px 10px;
  overflow: hidden;
  position: relative;
}

.note-item:hover {
  box-shadow: 0px 0px 5px 5px #dcdcdc;
}

.tag-container {
  /* border: 1px solid red; */
  display: inline-block;
  position: absolute;
  right: 20px;
}

.el-tag {
  margin: 0px 2px;
  /* text-align: end; */
  display: inline-block;
  /* border: 1px solid red; */
}

.info {
  /* border: 1px solid red; */
  margin: 0px;
}

.author {
  color: #999999;
  font-size: 12px;
}

.title {
  /* border: 1px solid red; */
  margin-bottom: 0px;
  position: relative;
}

.title-link {
  color: #333333;
  text-decoration: none;
  transition: .2s;
}

.title-link:hover {
  color: #8898d3;
}

.content {
  margin-top: 5px;
  font-size: 12px;
  color: #585858;
  /* border: 1px solid red; */
  white-space: nowrap;
  text-overflow: ellipsis;
  overflow: hidden;
}

.operation {
  position: absolute;
  right: 0px;
  bottom: -50px;
  margin-right: 20px;
  /* border: 1px solid red; */
  /* height: 100%; */
  transition: .2s;
}

.note-item:hover .operation {
  transform: translateY(-60px);
}
</style>