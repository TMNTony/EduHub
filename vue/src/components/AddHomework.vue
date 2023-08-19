<template>
  <div class="content-container">
    <content-sidebar />
    <div>
      <div v-if="!show" class="content-main">
        <form v-on:submit.prevent="saveHomework()">
          <text-editor
            v-model="currentHomework.assignment"
            :mode="'homework'"
            :homework="currentHomework.assignment"
            @update:homework="updateHomework($event)"
          />
          <div class="save">
            <label for="points"
              >How many points is this assignment worth?</label
            >
            <input
              id="points"
              type="number"
              v-model="possiblePoints"
              :min="0"
            />
            <input
              class="button"
              @click="saveHomework"
              type="submit"
              value="Save Assignment"
            />
          </div>
        </form>
      </div>
      <div class="content" v-else v-html="currentHomework.assignment"></div>
      <submit-homework v-if="show" class="submit-homework" />
    </div>
  </div>
</template>

<script>
import ContentSidebar from "./ContentSidebar.vue";
import SubmitHomework from "./SubmitHomework.vue";
import TextEditor from "./TextEditor.vue";

export default {
  name: "add-homework",
  components: {
    TextEditor,
    SubmitHomework,
    ContentSidebar,
  },
  data() {
    return {
      isAdmin: false,
      possiblePoints: 0,
      currentHomework: {},
      storePages: [],
    };
  },
  methods: {
    updateHomework(newContent) {
      this.currentHomework.assignment = newContent;
    },
    toggleEdit() {
      this.editing = !this.editing;
    },
    fetchHomeworkData() {
      const curriculumId = this.$route.params.currid;
      const homeworkId = this.$route.params.homeworkId;

      this.$store.commit("SET_ACTIVE_CURRICULUM", curriculumId);
      this.$store.commit("SET_ACTIVE_HOMEWORK", homeworkId);

      const fetchedHomework = this.$store.getters.getHomeworkById;

      if (fetchedHomework) {
        this.currentHomework = fetchedHomework;
      } else {
        console.error("Page not found for homeworkId:", homeworkId);
      }
      console.log(this.$store.state.activeHomework);
    },
    saveHomework() {
      let payload = {
        assignment: this.currentHomework.assignment,
        points: this.possiblePoints,
      };
      this.$store.commit("SAVE_HOMEWORK", payload);
      this.$router.push({
        name: "content",
        params: {
          id: this.$route.params.id,
          currid: this.$route.params.currid,
          pageNum: 1,
        },
      });
    },
  },
  created() {
    this.isAdmin = this.$store.getters.isAdmin;
    this.fetchHomeworkData();
  },
  computed: {
    studentView() {
      return this.$store.state.studentView;
    },
    show() {
      return !this.isAdmin || this.studentView;
    },
  },
  watch: {
    $route() {
      this.fetchHomeworkData();
    },
  },
};
</script>

<style>
.submit-homework {
  display: flex;
  flex-direction: column;
  margin-top: 5%;
  margin-left: 20%;
  margin-right: 5%;
  justify-content: center;
  align-items: center;
}
.save {
  color: white;
  margin: 10px;
 text-align: center;
}

</style>
