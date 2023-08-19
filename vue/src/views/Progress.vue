<template>
  <div>
    <class-progress v-if="isAdmin" ></class-progress>
    <student-progress v-else></student-progress>
  </div>
</template>

<script>
import ClassProgress from "../components/ClassProgress.vue";
import StudentProgress from "../components/StudentProgress.vue";

export default {
  components: {
    StudentProgress,
    ClassProgress,
  },
  data() {
    return {
      isAdmin: false,
    };
  },
  created() {
    this.isAdmin = this.$store.getters.isAdmin;
  },
};
</script>

<style></style>
