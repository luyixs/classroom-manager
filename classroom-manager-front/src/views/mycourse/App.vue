<template>
  <div class="course-app">
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
          <MyCourse></MyCourse>
          

        </el-container>

      </el-container>

      <el-footer>Class Manager</el-footer>


    </el-container>
  </div>
</template>

<script>
import Avatar from '../../components/Avatar.vue'
import IndexLogin from '../../components/IndexLogin.vue'
import NavMenu from '../../components/NavMenu.vue'
import MyCourse from '../../components/MyCourse/MyCourse.vue'

export default {
  components: {
    Avatar,
    IndexLogin,
    NavMenu,
    MyCourse
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