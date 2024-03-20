<template>
  <div class="detail-container">
    <div class="header">
      <div>
        <el-button icon="el-icon-back" circle @click="handleBack" size="medium"></el-button>
      </div>
      <div class="title-box">
        <h3>{{ note.title }}</h3>
      </div>
    </div>
    <div class="info">NID:{{ note.nId }}&nbsp;|&nbsp;笔者ID:{{ note.uId }} &nbsp;|&nbsp;编辑时间:{{ note.editTime }}
      &nbsp;|&nbsp; <el-tag v-for="tag in note.tag" :key="tag" effect="light" size="mini">{{ tag }}</el-tag> </div>

    <div>
      <mavon-editor v-model="note.content"  navigation defaultOpen="preview" :toolbarsFlag="false" :editable="false"
        :subfield="false"></mavon-editor>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  components: {},
  props: {},
  data() {
    return {
      note: {
        nId: '',
        uId: '',
        title: '',
        content: '',
        tag: '',
        editTime: '',
        isPublic: false
      },
    };
  },
  watch: {},
  computed: {},
  methods: {
    handleBack() {
      this.$router.back()
    },
    getNoteDetail() {
      axios({ method: 'GET', url: '/note/detail/' + this.$route.params.nId }).then(rsp => {
        this.note = rsp.data.data
        this.note.tag = JSON.parse(this.note.tag)
        console.log(this.note)

      }, err => {
        this.$message.error(err.message)
      }).finally(() => { })
    }

  },
  created() { },
  mounted() {
    this.getNoteDetail()
  },
  // beforeRouteEnter(to,from ,next){
  //   // console.log('to:',to,'from:',from)
  //   if(from.path=='/'){
  //     this.$bus.$emit('handleTabVisible')
  //   } 
  //   next()
  // }
}
</script>

<style scoped>
.header {
  /* border: 1px solid green; */
  display: flex;

}

.title-box {
  width: 100%;
  /* text-align: center; */
  padding: 0px;
  margin-left: 50px;
  border-bottom: 2px solid #728bc5;
}

.title-box h3 {
  /* border: 1px solid red; */
  margin: auto;
}

.el-tag {
  margin: 0px 2px;
  display: inline-block;
}

.info {
  margin: 5px 0px 5px 90px;
  font-size: 12px;
  color: #999999;
}</style>