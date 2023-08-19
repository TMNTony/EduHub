<template>
  <form v-on:submit.prevent="submitHomework()">
    <input
      v-model="link"
      type="text"
      placeholder="Paste Google Doc Link here"
    />
    <input type="submit" value="Submit Assignment" class="button"/>
  </form>
</template>

<script>
export default {
  name: "submit-homework",
  data() {
    return {
      link: ''
    };
  },
  methods: {
    submitHomework() {
      this.$store.commit("SAVE_SUBMISSION", this.link);
    },
    getStudentSubmission() {
     return this.$store.getters.getSubmissionByUserId;
    },
  },
  created() {
    const submission = this.getStudentSubmission();
    console.log(this.$store.state.activeHomework)
    // this.link = submission.link;
    console.log(submission)
  
  },
};
</script>

<style></style>
