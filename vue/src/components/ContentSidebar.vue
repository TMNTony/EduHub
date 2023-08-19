<template>
  <aside class="sidebar">
    <nav class="nav">
      <ul>
        <li>{{ curriculumName }}</li>
        <li>Content</li>
        <li>
          <router-link 
            v-for="page in storePages"
            v-bind:key="'page_' + page.pageNum"
            :to="{
              name: 'content',
              params: {
                id: $route.params.id,
                currid: $route.params.currid,
                pageNum: page.pageNum,
              },
            }"
          >
            {{ page.title ? page.title : "Page " + page.pageNum }}</router-link
          >
        </li>
        <li>Assignments</li>
        <li>
          
          <router-link 
            v-for="homework in storeHomework"
            v-bind:key="'homework_' + homework.homeworkId"
            :to="{
              name: 'homework',
              params: {
                id: $route.params.id,
                currid: $route.params.currid,
                homeworkId: homework.homeworkId,
              },
            }"
            >Homework #{{homework.homeworkId}}
          </router-link>
        </li>
        <li>
          <router-link 
            :to="{ name: 'course', params: { id: $route.params.id } }"
          >
            Curriculum
          </router-link>
        </li>
      </ul>
    </nav>
  </aside>
</template>

<script>
export default {
  name: "content-sidebar",
  data() {
    return {
      isAdmin: false,
      storePages: [],
      storeHomework: [],
      curriculumName: "Dummy Data",
    };
  },
  methods: {
    fetchData() {
      const curriculumId = this.$route.params.currid;
      const courseId = this.$route.params.id;
      this.$store.commit("SET_ACTIVE_CURRICULUM", curriculumId);
      this.$store.commit("SET_ACTIVE_COURSE", courseId);
      this.storePages = this.$store.getters.getCurriculumPages;
      this.storeHomework = this.$store.getters.getHomeworkByCurriculum;
      this.curriculumName = this.$store.getters.getCurriculumName;
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
    this.fetchData();
    this.isAdmin = this.$store.getters.isAdmin;
  },
};
</script>

<style>
.sidebar {
  position: fixed;
  width: 15%;
  height: 100vh;
  background: #a447a6;
  font-size: .85rem;
}
.nav {
  position: fixed;
  text-align: left;
  top: 50%;
  transform: translateY(-50%);
  font-weight: bold;
}

.nav ul {
  list-style: none;
}
.nav ul li {
  position: relative;
  margin: 3.2em 0;
}
.nav ul li a {
  line-height: 5em;
  text-transform: uppercase;
  text-decoration: none;
  letter-spacing: 0.4em;
  color: rgba(255, 255, 255, 0.35);
  display: block;
  transition: all ease-out 300ms;
}
.nav ul li.active a {
  color: white;
}
.nav ul li:not(.active)::after {
  opacity: 0.2;
}
.nav ul li:not(.active):hover a {
  color: rgba(255, 255, 255, 0.75);
}
.nav ul li::after {
  content: "";
  position: absolute;
  width: 100%;
  height: 0.2em;
  background: black;
  left: 0;
  bottom: 0;
  background-image: linear-gradient(to right, #5e42a6, #b74e91);
}
</style>
