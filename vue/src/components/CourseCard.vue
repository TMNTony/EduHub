<template>
  <div class="module">
    <div v-if="!show" class="delete" @click.stop="deleteCourse()">
      <div class="ellipsis">...</div>
      <div class="delete-dropdown">Delete Course?</div>
    </div>
    <router-link
      v-bind:to="{ name: 'course', params: { id: course.id } }"
      class="course-link"
    >
      <h2 class="course-name">{{ course.name }}</h2>

    </router-link>
  </div>
</template>

<script>
import CourseService from "@/services/CourseService";
export default {
  name: "course-card",
  data() {
    return {
      courseId: this.course.id,
      isAdmin: false,
    };
  },
  props: {
    course: Object,
  },
  methods: {
    async deleteCourse() {
      try {
        await CourseService.deleteCourse(this.course.id);
        await this.$store.commit("DELETE_COURSE", this.courseId);
      } catch (error) {
        console.error("An error occurred while deleting the course:", error);
        // Handle the error, such as showing a notification to the user
      }
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
    this.isAdmin = this.$store.getters.isAdmin;
  },
};
</script>

<style>
.card {
  border: 2px solid #333;
  border-radius: 10px;
  background-color: #f9f9f9;
  width: 250px;
  height: 200px;
  margin: 20px;
  display: grid;
  bottom: 40px;
  left: 10%;
  font-size: 1rem;
  place-items: center;
  text-decoration: none;
  color: #a447a6;
  transition: border 0.2s ease, transform 0.3s ease-in-out;
  box-shadow: 0px 5px 10px 0px rgba(0, 0, 0, 0.5);
}
.card:hover {
  border: 3px solid #a447a6;
  transform: translateY(-5px);
  box-shadow: 0px 10px 20px 2px rgba(0, 0, 0, 0.25);
}

.card .course-name {
  font-size: 1.5rem;
  margin-bottom: 0.5rem;
}

.card .course-description {
  font-size: 1rem;
  text-align: center;
}
.card img {
  height: 30%;
  width: 30%;
  transition: transform 0.3s ease-in-out;
}

.card:hover img {
  transform: scale(1.2);
}

.card .course-name {
  font-size: 1.5rem;
  color: #a447a6;
}

.card .course-description {
  font-size: 1rem;
  color: #a447a6;
}
.ellipsis {
  font-size: 2.5rem;
  padding-right: 2rem;
}
.ellipsis:hover {
  display: none;
}

.delete-dropdown {
  display: none;
}
.delete:hover > .delete-dropdown {
  display: inline;
}
.delete:hover > .ellipsis {
  display: none;
}
.delete {
  width: 100%;
  text-align: right;
  cursor: pointer;
}
</style>
