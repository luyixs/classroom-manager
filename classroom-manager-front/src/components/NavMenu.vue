<template>
  <!-- 导航栏 -->
  <!-- <el-card> -->
  <el-menu default-active="1" class="el-menu-vertical-demo menu" @select="handleSelect">
    <el-menu-item index="/">
      <i class="el-icon-house"></i>
      <span slot="title">首页</span>
    </el-menu-item>
    <el-submenu index="1">
      <template slot="title">
        <i class="el-icon-reading"></i>
        <span>课程服务</span>
      </template>
      <el-menu-item-group>
        <template slot="title">学生服务</template>
        <el-menu-item index="/sc">
          <el-link href="#" :underline="false">选课</el-link>
        </el-menu-item>
        <el-menu-item index="/mycourse">
          <el-link href="#" :underline="false">我的选课</el-link>
        </el-menu-item>
      </el-menu-item-group>
      <el-menu-item-group title="教师服务">
        <el-menu-item index="/mylecture">
          <el-link href="#" target="" :underline="false">我的授课</el-link>
        </el-menu-item>
      </el-menu-item-group>
    </el-submenu>


    

    <el-menu-item index="/note/">
      <i class="el-icon-notebook-1"></i>
      <span slot="title">笔记</span>
    </el-menu-item>

    <el-menu-item index="/signin">
      <i class="el-icon-s-claim"></i>
      <span slot="title">签到</span>
    </el-menu-item>
    <el-menu-item index="/chatroom">
      <i class="el-icon-chat-dot-square"></i>
      <span slot="title">聊天室</span>
    </el-menu-item>
  </el-menu>
  <!-- </el-card> -->
</template>

<script>
export default {
  components: {},
  props: {},
  data() {
    return {
    };
  },
  watch: {},
  computed: {},
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
      this.redirectURL(key)
      console.log("redirected")
    },
    redirectURL(url) {
      console.log(url)
      // alert(url)
      window.location.href = window.location.origin + url;
    }
  },
  created() { },
  mounted() { }
}
</script>

<style>
.menu {
  border: none;
}
</style>