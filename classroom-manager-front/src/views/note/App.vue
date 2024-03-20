<template>
  <div id="app" class="index-app">
    <el-container>

      <el-header>Class Manager</el-header>

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

        <!--Index Main  -->
        <el-container>
          <Note></Note>
        </el-container>

      </el-container>

      <el-footer>Class Manager</el-footer>


    </el-container>

  </div>
</template>

<script>
import axios from 'axios'
import Cookies from 'js-cookie'
import Avatar from '../../components/Avatar.vue'
import IndexLogin from '../../components/IndexLogin.vue'
import NavMenu from '../../components/NavMenu.vue'
import Note from '../../components/Note/Note.vue'

export default {
  components: {
    Avatar,
    IndexLogin,
    NavMenu,
    Note,
  },
  props: {},
  data() {
    return {

    };
  },
  watch: {},
  computed: {},
  methods: {},
  created() { },
  mounted() { }
}
</script>

<style>
@import '../../assets/css/public.css';
</style>