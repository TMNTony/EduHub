<template>
  <div class="course-container">
    <div class="card-lists">
      <h1>Courses</h1>
      <course-card
        v-for="course in $store.state.courses"
        v-bind:key="course.name"
        v-bind:course="course"
      />
      <router-link class="card" v-if="!show" v-bind:to="{ name: 'add-course' }">
        <img
          src="../assets/add.png"
          style="background-color: #a447a6; padding: 50px; border-radius: 25%"
          alt="Add Module"
        />
      </router-link>
    </div>
    <div class="welcome">Welcome</div>
  </div>
</template>

<script>
import CourseCard from "./CourseCard.vue";
export default {
  name: "course-list",
  components: {
    CourseCard,
  },
  data() {
    return {
      isAdmin: false,
    };
  },
  methods: {
    fetchCourses() {
      this.$store.dispatch("fetchCourses");
    },
  },
  computed: {
    studentView() {
      return this.$store.state.studentView;
    },
    show() {
      return !this.isAdmin || this.studentView;
    },
  },
  created() {
    this.isAdmin = this.$store.getters.isAdmin;
    this.fetchCourses();
  },
};
</script>

<style>
.course-container {
  display: grid;
  grid-template-columns: 1fr 5fr;
  justify-content: center;
  margin: 5rem;
  
}
.welcome {
  background-color: rgb(255, 255, 255);
  margin-left: 5%;
  margin-right: 5%;
  margin-top: 10%;
  height: 50%;
  border-radius: 10px;
  padding: 20px;
}
</style>
