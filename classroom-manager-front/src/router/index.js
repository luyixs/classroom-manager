import VueRouter from "vue-router";
import NoteDetails from '../components/Note/NoteDetails'

const router = new VueRouter({
  routes: [
    {
      name:'d',
      path: '/d/:nId',
      component: NoteDetails
    }
  ]
})
router.beforeEach((to, from ,next)=>{
  // console.log('beforeEach',to,from,next)
  next()
})
export default router

