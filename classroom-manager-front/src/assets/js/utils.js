const LOGINUSER_KEY = 'loginUser'

function hello() {
  alert('hello')
}

//获取后台服务器session 并且更新前台服务器cookie
function getSessionLoginUser() {
  axios({ method: 'GET', url: '/session/loginUser' }).then(rsp => {
    this.loginUser = rsp.data
    this.setCookie('loginUser', this.loginUser)
  }, err => { this.$message.error(err.message) }).finally(() => { console.log(this.getCookie('loginUser')) })

  //通过js获取session（行不通） session和sessionStorage两者不是一回事 sessionStorage是页面协议
  // let loginUser = sessionStorage.getItem('loginUser')
  // console.table(loginUser)

  //问题：前后端session不一致
  //可能原因：1.session和sessionStorage两者就不是一回事 2.js不能直接读取session值 
  //解决方案：
  //  1.先请求api获取后端session 然后在设置前端session
}

module.exports = {
  hello,
  LOGINUSER_KEY
}