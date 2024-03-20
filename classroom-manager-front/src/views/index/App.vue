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
          <el-main>

            <Carousel></Carousel>
            <!-- <p>打造高效课堂</p> -->

          </el-main>


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
import Carousel from '../../components/Index/Carousel.vue'

export default {
  name: 'App',
  components: {
    Avatar,
    IndexLogin,
    NavMenu,
    Carousel
  },
  data() {
    return {

    };
  },
  methods: {


    // redirectURL(url) {
    //   console.log(window.location.origin + url)
    //   window.location.href = window.location.origin + url;
    // }


  },
  created() {
  },
  mounted() {


    this.$bus.$on('redirectURL', this.redirectURL)
  },
  beforeDestory() {
    this.$bus.$off('redirectURL')
  }

}
</script>

<style>
@import '../../assets/css/public.css';

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  /* text-align: center; */
  /* color: #2c3e50; */
  /* margin-top: 60px; */

}



/* .login-app{
    width: 400px ;
}  */
</style>
