<template>
  <div class="content-container">
    <div class="content-grade" v-if="homeworkToGrade.length > 0">
      <div class="submissions" v-for="homework in homeworkToGrade" v-bind:key="homework.userId">
        <div>{{ homework.userId }}</div>
        <a :href="homework.link" target="_blank">View Submission</a>
        <input
          type="number"
          v-model="homework.score"
          :min="0"
          :max="homework.possiblePoints"
          
        />
        <div>/{{homework.possiblePoints}}</div>
        <button @click="submitGrade(homework)" >Submit Grade</button>
      </div>
      
    </div>
    <div v-else class="grade">You're all caught up!</div>
  </div>
</template>

<script>


export default {
  name: "grade-homework",
  components: {
   
  },
  data() {
    return {
      homeworkToGrade: [],
    };
  },
  methods: {
    fetchHomeworkToGrade() {
      this.homeworkToGrade = this.$store.getters.getUngradedSubmissions;
    },
      resetData() {
      this.homeworkToGrade = [];
    },
    async submitGrade(homework) {
      homework.score = parseFloat(homework.score);
      this.$store.commit("SAVE_GRADE", homework);
      this.resetData();
      this.fetchHomeworkToGrade();
    }
  },
  computed: {},
  created() {
    this.fetchHomeworkToGrade();

  },
};
</script>

<style>
.grade {
  background-color: rgb(255, 255, 255);
  margin-left: 20%;
  margin-right: 20%;
  margin-top: 10%;
  height: 100%;
  border-radius: 10px;
  padding: 20px;
}
.content-grade {
  background-color: rgb(255, 255, 255);
  margin-left: 20%;
  margin-right: 20%;
  margin-top: 10%;
  height: 100%;
  border-radius: 10px;
  padding: 20px;
}
.submissions {
  display: flex;
  flex-direction: row;
}
</style>
