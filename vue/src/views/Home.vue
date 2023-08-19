<template>
  <div>
    <course-list />
  </div>
</template>

<script>
import CourseList from "../components/CourseList.vue";

export default {
  name: "home",
  components: {
    CourseList,
  },
  created() {
    this.$store.dispatch("fetchCourses");
  },
};
</script>
<style>

</style>
