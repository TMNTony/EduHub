<template>
  <div class="student-work">
    <h1>My Grades</h1>
    <div v-if="studentSubmissions.length > 0">
      <table>
        <thead>
          <tr>
            <th>Student Name</th>
            <th>Total Score</th>
            <th>Percentage</th>
            <th
              v-for="submission in studentSubmissions"
              :key="submission.homeworkId"
            >
              Homework #{{ submission.homeworkId }}
            </th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>{{ studentName }}</td>
            <td>{{ totalPointsEarned }}/ {{ totalPossiblePoints }}</td>
            <td>{{ percentage }}%</td>
            <td
              v-for="submission in studentSubmissions"
              :key="submission.homeworkId"
            >
              {{ submission.score }}/ {{ submission.possiblePoints }}
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="no-grades" v-else>No Grades To Display</div>
  </div>
</template>

<script>
export default {
  name: "student-progress",
  data() {
    return {
      studentSubmissions: [],
      studentName: "",
    };
  },
  computed: {
    totalPossiblePoints() {
      return this.studentSubmissions.reduce(
        (total, submission) => total + submission.possiblePoints,
        0
      );
    },
    totalPointsEarned() {
      return this.studentSubmissions.reduce(
        (total, submission) => total + submission.score,
        0
      );
    },
    percentage() {
      const percentage =
        (this.totalPointsEarned / this.totalPossiblePoints) * 100;
      return percentage.toFixed(1);
    },
  },
  methods: {
    async getSubmissions() {
      this.studentSubmissions = await this.$store.getters
        .getSubmissionsForCourseByUserId;
    },
    async getUsername() {
      this.studentName = this.$store.state.user.username;
    },
  },
  created() {
    const courseId = this.$route.params.id;
    this.$store.commit("SET_ACTIVE_COURSE", courseId);
    this.getSubmissions();
    this.getUsername();
  },
};
</script>

<style>
.student-work {
  margin-top: 10rem;
  margin-left: 0rem;
}

.no-grades {
  color: white;
  font-size: 2rem;
}
/* Add borders to both tables */
table {
  border-collapse: collapse;
  border: 1px solid #ccc;
  width: 100%;
  margin: 5rem;
}

/* Style for table headers */
thead th {
  border: 1px solid #ccc;
  background-color: #f2f2f2;
  padding: 8px;
  text-align: left;
}

/* Style for table cells */
td {
  border: 1px solid #ccc;
  padding: 8px;
  background-color: white;
}
</style>
