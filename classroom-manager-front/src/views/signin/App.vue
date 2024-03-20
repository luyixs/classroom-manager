<template>
  <div class="signin-app">
    <el-container>

      <el-header>ClassManager</el-header>

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
          <SignIn></SignIn>
          <AddSignIn></AddSignIn>

          

        </el-container>

      </el-container>

      <el-footer>ClassManager</el-footer>


    </el-container>
  </div>
</template>

<script>
import axios from 'axios'
import nanoid from "nanoid";
import Avatar from '../../components/Avatar.vue'
import IndexLogin from '../../components/IndexLogin.vue'
import NavMenu from '../../components/NavMenu.vue'
import SignIn from '../../components/SignIn/SignIn.vue';
import AddSignIn from '../../components/SignIn/AddSignIn.vue';
import SignInStateList from '../../components/SignIn/SignInStateList.vue';

export default {
  components: {
    Avatar,
    IndexLogin,
    NavMenu,
    SignIn,
    AddSignIn,
    SignInStateList
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

<style scoped>
@import '../../assets/css/public.css';
</style>