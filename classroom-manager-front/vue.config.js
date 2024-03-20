const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false, //关闭语法检查
  devServer: {
    port:8081, //配置端口号
    proxy:  'http://localhost:8080'
  
  },
  pages: {
    index: {
      // page 的入口
      entry: 'src/views/index/main.js',
      // 模板来源
      template: 'src/views/index/index.html',
      // 在 dist/index.html 的输出
      filename: 'index.html',
      // 当使用 title 选项时，
      // template 中的 title 标签需要是 <title><%= htmlWebpackPlugin.options.title %></title>
      title: 'Index Page',
    },
    a: {
      entry: 'src/views/a/main.js',
      template: 'src/views/a/a.html',
      filename: 'a.html',
      title: 'A',

    },
    login:{
      entry: 'src/views/login/main.js',
      template: 'src/views/login/login.html',
      filename: 'login.html',
      title: 'LOGIN && REGISTER',
    },
    sc:{
      entry: 'src/views/sc/main.js',
      template: 'src/views/sc/sc.html',
      filename: 'sc.html',
      title: '选课',
    },
    mycourse:{
      entry: 'src/views/mycourse/main.js',
      template: 'src/views/mycourse/mycourse.html',
      filename: 'mycourse.html',
      title: '我的课程',
    },
    mylecture:{
      entry: 'src/views/mylecture/main.js',
      template: 'src/views/mylecture/mylecture.html',
      filename: 'mylecture.html',
      title: '我的授课',
    },
    chatroom:{
      entry: 'src/views/chatroom/main.js',
      template: 'src/views/chatroom/chatroom.html',
      filename: 'chatroom.html',
      title: '聊天室',
    },
    note:{
      entry: 'src/views/note/main.js',
      template: 'src/views/note/note.html',
      filename: 'note.html',
      title: 'Note',
    },
    signin:{
      entry: 'src/views/signin/main.js',
      template: 'src/views/signin/signin.html',
      filename: 'signin.html',
      title: '签到',
    },



  }

})
