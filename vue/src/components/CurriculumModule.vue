<template>
  <div class="module">
    <!-- Delete Dropdown (Visible to Admin) -->
    <div v-if="!show" class="delete" @click.stop="deleteCurriculum()">
      <div class="ellipsis">...</div>
      <div class="delete-dropdown">Delete Page?</div>
    </div>

    <!-- Curriculum Link -->
    <router-link  
      :to="{
        name: 'content',
        params: { id: $route.params.id, currid: this.curriculum.curriculumId, pageNum: 1 },
      }"
    >
      <h2>{{ curriculum.title }}</h2>
    </router-link>
  </div>
</template>

<script>
export default {
  name: "curriculum-module",
  data() {
    return {
      isAdmin: false,
    };
  },
  props: {
    curriculum: Object,
  },
  methods: {
    deleteCurriculum() {
      this.$store.commit("DELETE_CURRICULUM", this.curriculum.curriculumId);
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
    const activeCourse = this.$route.params.id;
    this.$store.commit("SET_ACTIVE_COURSE", activeCourse);
    this.isAdmin = this.$store.getters.isAdmin;
  },
};
</script>

<style>
.module {
  border: 2px solid black;
  border-radius: 10px;
  width: 250px;
  height: 100px;
  margin: 20px;
  display: grid;
  bottom: 40px;
  left: 10%;
  font-size: 1rem;
  place-items: center;
  text-decoration: none;
  color: #A447A6;
  background-color: white;
  box-shadow: 0px 5px 10px 0px rgba(0, 0, 0, 0.5);
}
.module h2{
  color: #A447A6;
}
.module:hover {
  border: 3px solid #A447A6;
  transform: translateY(-5px);
  box-shadow: 0px 10px 20px 2px rgba(0, 0, 0, 0.25);
}
</style>
