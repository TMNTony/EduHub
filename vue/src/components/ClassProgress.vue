<template>
  <div class="student-work">
    <h1>Student Grades</h1>
    <div v-if="studentSubmissions.length === 0">No Grades To Display</div>
    <table v-else>
      <thead>
        <tr>
          <th>Student Name</th>
          <th>Total Score</th>
          <th>Percentage</th>
          <th v-for="homeworkId in uniqueHomeworkIds" :key="homeworkId">
            Homework #{{ homeworkId }}
          </th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(submissions, userId) in submissionsByUser" :key="userId">
          <td>{{ userId }}</td>
          <td>
            {{ getTotalPointsEarned(submissions) }} /
            {{ getTotalPossiblePoints(submissions) }}
          </td>
          <td>{{ getPercentage(submissions) }}%</td>
          <td v-for="homeworkId in uniqueHomeworkIds" :key="homeworkId">
            {{ getSubmissionScore(submissions, homeworkId) }} /
            {{ getSubmissionPossiblePoints(submissions, homeworkId) }}
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: "student-progress",
  data() {
    return {
      studentSubmissions: [],
    };
  },
  computed: {
    // getUsername() {
    //   return userId => {
    //     // Implement a method to retrieve the username for a given userId
    //     // You can fetch the user information from your store or API
    //   };
    // },
    uniqueHomeworkIds() {
      const seen = new Set();
      const ids = [];
      for (const submission of this.studentSubmissions) {
        if (!seen.has(submission.homeworkId)) {
          seen.add(submission.homeworkId);
          ids.push(submission.homeworkId);
        }
      }
      return ids;
    },
    submissionsByUser() {
      const submissionsByUser = {};
      for (const submission of this.studentSubmissions) {
        if (!(submission.userId in submissionsByUser)) {
          submissionsByUser[submission.userId] = [];
        }
        submissionsByUser[submission.userId].push(submission);
      }
      return submissionsByUser;
    },
    getTotalPossiblePoints() {
      return (submissions) => {
        return submissions.reduce(
          (total, submission) => total + submission.possiblePoints,
          0
        );
      };
    },
    getTotalPointsEarned() {
      return (submissions) => {
        return submissions.reduce(
          (total, submission) => total + submission.score,
          0
        );
      };
    },
    getPercentage() {
      return (submissions) => {
        const totalPointsEarned = this.getTotalPointsEarned(submissions);
        const totalPossiblePoints = this.getTotalPossiblePoints(submissions);
        const percentage = (totalPointsEarned / totalPossiblePoints) * 100;
        return percentage.toFixed(1);
      };
    },
  },
  methods: {
    getSubmissionScore(submissions, homeworkId) {
      const matchingSubmission = submissions.find(
        (submission) => submission.homeworkId === homeworkId
      );
      if (matchingSubmission) {
        return matchingSubmission.score;
      }
    },

    getSubmissionPossiblePoints(submissions, homeworkId) {
      const matchingSubmission = submissions.find(
        (submission) => submission.homeworkId === homeworkId
      );
      if (matchingSubmission) {
        return matchingSubmission.possiblePoints;
      }
    },
    async getSubmissions() {
      this.studentSubmissions = await this.$store.getters
        .getSubmissionsForCourse;
  
    },
  },
  created() {
    const courseId = this.$route.params.id;
    this.$store.commit("SET_ACTIVE_COURSE", courseId);
    this.getSubmissions();
  },
};
</script>

<style>
.student-work {
  display: flex;
  flex-direction: column;
  margin: 10px;
  align-items: center;
  
}
.student-submissions {
  margin: 0 20px;
}
</style>
