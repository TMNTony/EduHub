<template>
  <div class="curriculum-container">
    <div class="card-lists">
      <h1>Curriculum</h1>
      <curriculum-module
        v-for="curriculum in curricula"
        :key="curriculum.curriculumId"
        :curriculum="curriculum"
      />
      <router-link v-if="!show" class="card" :to="{ name: 'add-module' }">
        <img
          src="../assets/add.png"
          style="background-color: #a447a6; padding: 50px; border-radius: 25%"
          alt="Add Module"
        />
      </router-link>
    </div>
    <div class="course-description">{{ thisCourse.description }}</div>
    <!-- <text-editor class="content"></text-editor> -->
  </div>
</template>
<script>
import CurriculumModule from "../components/CurriculumModule.vue";
// import TextEditor from '../components/TextEditor.vue';


export default {
  props: {},
  data() {
    return {
      isAdmin: false,
      thisCourse: {},
    };
  },
  components: {
    CurriculumModule,
    // TextEditor
  },
  methods: {

  },
  computed: {
    curricula() {
      return this.$store.getters.curricula;
    },
    studentView() {
      return this.$store.state.studentView;
    },
    show() {
      return !this.isAdmin || this.studentView;
    },
  },
  created() {
    const activeCourse = this.$route.params.id;
    this.$store.commit("SET_ACTIVE_COURSE", activeCourse);
    this.isAdmin = this.$store.getters.isAdmin;
    this.thisCourse = this.$store.getters.getCourse;
    this.$store.dispatch("fetchCurriculum", activeCourse);
  },
};
</script>

<style>
.curriculum-container {
  display: grid;
  grid-template-columns: 1fr 5fr;
  justify-content: center;
  margin: 5rem;
}
.course-description {
  background-color: rgb(255, 255, 255);
  margin-left: 5%;
  margin-right: 5%;
  margin-top: 10%;
  height: 50%;
  border-radius: 10px;
  padding: 20px;
}

h1 {
  color: #a447a6;
  
}
</style>
