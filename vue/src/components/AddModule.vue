<template>
<div class= "new-class-container">
  <form class="new-course-form" v-on:submit.prevent="addModule()">
    <input
      type="text"
      class="name-input"
      placeholder="Module Name"
      v-model="newModule.title"
    />
    <input type="submit" value="Create Module" class="submit-button" />
    <router-link
      :to="{ name: 'course', params: { id: this.$route.params.id } }"
    >
      Cancel
    </router-link>
  </form>
  </div>
</template>

<script>
export default {
  name: "add-module",
  data() {
    return {
      newModule: {
        courseId: this.$route.params.id,
        curriculumId: this.getId(),
        title: "",
      },
    };
  },
  methods: {
    getId() {
      return this.$store.getters.newCurriculaId;
    },
    addModule() {
      const firstPage = {
        curriculumId: this.getId(),
        pageNum: 1,
        content: "First Page",
      };
      this.$store.commit("ADD_CURRICULUM", this.newModule);
      this.newModule = {};
      this.$store.commit("ADD_PAGE", firstPage);
      this.$router.push({
        name: "course",
        params: { id: this.$route.params.id },
      });
    },
  },
};
</script>

<style>

.new-class-container{
    display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}
.new-course-form{
  display: flex;
  flex-direction: column;
  width: 600px;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
  background-color: #f9f9f9;
  font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
}

</style>
