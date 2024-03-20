<template>
  <el-carousel>
    <el-carousel-item v-for="item in carouselList" :key="item.title">
      <div class="itembox">
        <h3>{{ item.title }}</h3>
        <p class="description"> {{ item.description }}</p>
      </div>
    </el-carousel-item>
  </el-carousel>
</template>

<script>
export default {
  components: {},
  props: {},
  data() {
    return {
      carouselList: [
        {
          title: '课程服务',
          description: '提供教师对课程的管理服务、学生进行选课退课服务'
        },
        {
          title: '签到服务',
          description: '提供教师发起签到、学生签到服务'
        },
        {
          title: '笔记服务',
          description: '提供用户对笔记管理的服务'
        },
        {
          title: '聊天室',
          description: '为用户提供聊天室，可在线聊天的服务'
        }
      ]
    };
  },
  watch: {},
  computed: {},
  methods: {},
  created() { },
  mounted() { }
}
</script>

<style >


.el-carousel__item h3 {
  color: #475669;
  font-size: 32px;
  opacity: 0.75;
  line-height: 50px;
  margin: 100px 0px 0px 0px;
  text-align: center;
}

.el-carousel__item p {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  text-align: center;
}


.el-carousel__item:nth-child(2n) {
  background:linear-gradient(to right ,#fcdbdb,#f4c7f0);
}

.el-carousel__item:nth-child(2n+1) {
  background:linear-gradient(to right ,#d1daf7,#a7a7f3);
}

.description {
  background: linear-gradient(to right, #bccaf8, #e568dc) no-repeat right bottom ;
  background-size: 0 2px;
  transition: background-size 1300ms;
}

.description:hover {
  background-position-x: left; 
  background-size: 100% 2px;
}
</style>