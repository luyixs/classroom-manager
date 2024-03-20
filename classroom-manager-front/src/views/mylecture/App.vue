<template>
  <div class="index-app">
    <el-container>

      <el-header >Class Manager</el-header>

      <el-container>
        <!-- 侧边栏 -->
        <el-aside>

          <!-- 头像盒子 -->
          <Avatar></Avatar>

          <!-- 登录盒子 -->
          <IndexLogin></IndexLogin>

          <!-- 导航栏 -->
          <NavMenu></NavMenu>

        </el-aside>

        <!-- Main  -->
        <el-container>
          <Lecture></Lecture>
          <AddLecture></AddLecture>
          <EditLecture></EditLecture>
          

        </el-container>

      </el-container>

      <el-footer>Class Manager</el-footer>


    </el-container>
  </div>
</template>

<script>
import axios from 'axios' 
import nanoid from "nanoid";
import Avatar from '../../components/Avatar.vue'
import IndexLogin from '../../components/IndexLogin.vue'
import NavMenu from '../../components/NavMenu.vue'
import Lecture from '../../components/MyLecture/Lecture.vue';
import AddLecture from '../../components/MyLecture/AddLecture.vue';
import EditLecture from '../../components/MyLecture/EditLecture.vue';

export default {
  components: {
    Avatar,
    IndexLogin,
    NavMenu,
    Lecture,
    AddLecture,
    EditLecture,
  },
  props: {},
  data() {
    return {
    

    };
  },
  watch: {},
  computed: {},
  methods: {
   
  },
  created() {
  },
  mounted() {
  }
}
</script>

<style scoped>
@import '../../assets/css/public.css';
</style>