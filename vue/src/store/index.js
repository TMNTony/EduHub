import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import CourseService from '@/services/CourseService';
import CurriculumService from '@/services/CurriculumService';
// import UserService from '@/services/UserService'
Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));
const isAdmin = localStorage.getItem('isAdmin') === 'true';

if (currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    isAdmin: isAdmin,
    activeCourse: 0,
    activeCurriculum: 0,
    activePage: 0,
    activeHomework: 0,
    studentView: false,
    pages: [{
      curriculumId: 1,
      pageNum: 1,
      title: 'Test Title',
      content: 'Test Content',
    },
    {
      curriculumId: 2,
      pageNum: 1,
      content: 'Test Chapter 2',
    },
    {
      curriculumId: 3,
      pageNum: 1,
      content: 'Test Chapter 1',
    },
    {
      curriculumId: 4,
      pageNum: 1,
      content: 'Test Chapter 2',
    },],
    courses: [
      {
        name: "Algebra 101",
        description: 'Fundamentals of Algebra',
        difficulty: 'easy',
        cost: 'free',
        id: 1
      },
      {
        name: "Algebra 201",
        description: 'Intermediate Algebra',
        difficulty: 'moderate',
        cost: 'free',
        id: 2
      }
    ],
    curriculum: [
      {
        courseId: 1,
        curriculumId: 1,
        title: 'Algebra 101 Chapter 1',
      },
      {
        courseId: 1,
        curriculumId: 2,
        title: 'Algebra 101 Chapter 2',
      },
      {
        courseId: 2,
        curriculumId: 3,
        title: 'Algebra 201 Chapter 1',
      },
      {
        courseId: 2,
        curriculumId: 4,
        title: 'Algebra 201 Chapter 2',
      },
    ],
    homework: [
      {
        homeworkId: 1,
        curriculumId: 1,
        assignment: 'add assignment here'
      },
      {
        homeworkId: 2,
        curriculumId: 1,
        assignment: 'add assignment here'
      },
      {
        homeworkId: 3,
        curriculumId: 1,
        assignment: 'add assignment here'
      },
      {
        homeworkId: 4,
        curriculumId: 1,
        assignment: 'add assignment here'
      },
    ],
    submissions: [
      {
        homeworkId: 1, // Replace with actual homework ID
        userId: 4, // Assuming the user ID is 4
        link: "https://www.google.com",
        score: 10,
        possiblePoints: 10 // Replace with the desired value for possiblePoints
      },
      {
        homeworkId: 2, // Replace with actual homework ID
        userId: 4, // Assuming the user ID is 4
        link: "https://www.google.com",
        score: 7,
        possiblePoints: 10 // Replace with the desired value for possiblePoints
      },
      {
        homeworkId: 3, // Replace with actual homework ID
        userId: 4, // Assuming the user ID is 4
        link: "https://www.google.com",
        score: 8,
        possiblePoints: 10 // Replace with the desired value for possiblePoints
      },
      {
        homeworkId: 4, // Replace with actual homework ID
        userId: 4, // Assuming the user ID is 4
        link: "https://www.google.com",
        score: null,
        possiblePoints: 10 // Replace with the desired value for possiblePoints
      },
      {
        homeworkId: 1, // Replace with actual homework ID
        userId: 5, // Assuming the user ID is 4
        link: "https://www.google.com",
        score: 6,
        possiblePoints: 10 // Replace with the desired value for possiblePoints
      },
      {
        homeworkId: 2, // Replace with actual homework ID
        userId: 5, // Assuming the user ID is 4
        link: "https://www.google.com",
        score: 7,
        possiblePoints: 10 // Replace with the desired value for possiblePoints
      },
      {
        homeworkId: 3, // Replace with actual homework ID
        userId: 5, // Assuming the user ID is 4
        link: "https://www.google.com",
        score: 8,
        possiblePoints: 10 // Replace with the desired value for possiblePoints
      },
      {
        homeworkId: 4, // Replace with actual homework ID
        userId: 5, // Assuming the user ID is 4
        link: "https://www.google.com",
        score: null,
        possiblePoints: 10 // Replace with the desired value for possiblePoints
      },
    ],
    students: [
      {
        userId: 1,
        userName: 'Timmy Johnson',
        courses: [1],
      },
      {
        userId: 5,
        userName: 'Timmy Johnson',
        courses: [1],
      }
    ]
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      state.isAdmin = user.authorities[0].name === "ROLE_ADMIN";
      localStorage.setItem('user', JSON.stringify(user));
      localStorage.setItem('isAdmin', state.isAdmin);
    },
    SET_STUDENTS(state, users) {
      state.students = users
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    TOGGLE_VIEW(state) {
      state.studentView = !state.studentView
    },
    SET_ACTIVE_COURSE(state, courseId) {
      state.activeCourse = courseId;
    },
    SET_ACTIVE_CURRICULUM(state, curriculumId) {
      state.activeCurriculum = curriculumId
    },
    SET_ACTIVE_PAGE(state, pageNum) {
      state.activePage = pageNum
    },
    SET_ACTIVE_HOMEWORK(state, homeworkId) {
      state.activeHomework = homeworkId
    },
    SET_COURSES(state, courses) {
      state.courses = courses;
    },
    SET_CURRICULUM(state, curriculum) {
      state.curriculum = curriculum;
    },
    ADD_COURSE(state, newCourse) {
      state.courses.push(newCourse)
    },
    DELETE_COURSE(state, deletedCourse) {
      const courseToDeleteIndex = state.courses.findIndex(course => course.id === deletedCourse);

      if (courseToDeleteIndex !== -1) {
        state.courses.splice(courseToDeleteIndex, 1);
        const curriculaToDelete = state.curriculum.filter(c => c.courseId === deletedCourse);

        curriculaToDelete.forEach(curriculum => {
          const curriculumIndex = state.curriculum.indexOf(curriculum);
          if (curriculumIndex !== -1) {
            state.curriculum.splice(curriculumIndex, 1);
          }
        });
      }
    },
    ADD_CURRICULUM(state, newCurriculum) {
      state.curriculum.push(newCurriculum)
    },
    DELETE_CURRICULUM(state, curriculumId) {
      const curriculumToDeleteIndex = state.curriculum.findIndex(c => c.curriculumId === curriculumId);
      if (curriculumToDeleteIndex !== -1) {
        state.curriculum.splice(curriculumToDeleteIndex, 1)
      }
    },
    ADD_PAGE(state, page) {
      state.pages.push(page);
    },
    SAVE_PAGE(state, page) {
      const savedPage = state.pages.find(
        (p) => p.curriculumId === page.curriculumId && p.pageNum === page.pageNum
      );
      if (savedPage) {
        savedPage.content = page.content;
      }
    },
    DELETE_PAGE(state) {
      const pageToDeleteIndex = state.pages.findIndex(p => p.curriculumId === state.activeCurriculum && p.pageNum === state.activePage);

      if (pageToDeleteIndex !== -1) {
        state.pages.splice(pageToDeleteIndex, 1); // Use 1 as the second argument

        for (let i = 0; i < state.pages.length; i++) {
          if (state.pages[i].pageNum > state.pages[pageToDeleteIndex].pageNum - 1 && state.pages[i].curriculumId === state.activeCurriculum) {
            state.pages[i].pageNum -= 1;
          }
        }
      }
    },
    ADD_HOMEWORK(state, newHomework) {
      state.homework.push(newHomework);
    },
    SAVE_HOMEWORK(state, payload) {
      const savedHomework = state.homework.find(hw => hw.curriculumId === state.activeCurriculum && hw.homeworkId === state.activeHomework);

      if (savedHomework) {
        savedHomework.assignment = payload.assignment;

        // Iterate through students
        for (let i = 0; i < state.students.length; i++) {
          if (state.students[i].courses.includes(state.activeCourse)) {
            // Check if an existing submission already exists
            const existingSubmissionIndex = state.submissions.findIndex(
              submission => submission.homeworkId === state.activeHomework && submission.userId === state.students[i].userId
            );
            console.log(existingSubmissionIndex)
            if (existingSubmissionIndex !== -1) {
              // Update the existing submission
              state.submissions[existingSubmissionIndex].possiblePoints = payload.points;
            } else {
              // Create a new submission
              const submission = {
                homeworkId: state.activeHomework,
                userId: state.students[i].userId,
                link: "https://www.google.com",
                score: null,
                possiblePoints: payload.points
              };

              state.submissions.push(submission);
            }
          }
        }
      }
    },
    SAVE_SUBMISSION(state, newSubmission) {
      const submissionIndex = state.submissions.findIndex(
        s => s.homeworkId === newSubmission.homeworkId && s.userId === newSubmission.userId
      );

      if (submissionIndex !== -1) {
        state.submissions[submissionIndex].submitted = 'submitted'
      }
    },
    SAVE_GRADE(state, homework) {
      const submissionIndex = state.submissions.findIndex(
        s => s.homeworkId === homework.homeworkId && s.userId === homework.userId
      );
      if (submissionIndex !== -1) {
        state.submissions[submissionIndex].score = homework.score
      }
    }
  },
  getters: {
    isAdmin(state) {
      return state.isAdmin;
    },
    newCourseId(state) {
      return state.courses.length + 1;
    },
    getCourse(state) {
      return state.courses.find(c => c.id = state.activeCourse)
    },
    curricula(state) {
      return state.curriculum.filter(c => c.courseId == state.activeCourse);
    },
    currentCurriculumId(state) {
      return state.curriculum.find(c => c.curriculumId == state.activeCurriculum);
    },
    newCurriculaId(state) {
      return state.curriculum.length + 1;
    },
    getCurriculumName(state) {
      const data = state.curriculum.find(c => c.curriculumId == state.activeCurriculum)
      return data.title;
    },
    getPage(state) {
      return state.pages.find(p => p.curriculumId === state.activeCurriculum && p.pageNum === state.activePage)
    },
    totalCurriculumPages(state) {
      let pages = state.pages.filter(p => p.curriculumId == state.activeCurriculum);
      return pages.length;
    },
    getCurriculumPages(state) {
      return state.pages.filter(p => p.curriculumId == state.activeCurriculum);
    },
    newHomeworkId(state) {
      return state.homework.length + 1;
    },
    getHomeworkByCurriculum(state) {
      return state.homework.filter(hw => hw.curriculumId == state.activeCurriculum);
    },
    getHomeworkById(state) {
      return state.homework.find(hw => hw.homeworkId === state.activeHomework)
    },
    getSubmissionByUserId(state) {
      return state.submissions.find(s => s.homeworkId === state.activeHomework && s.userId === state.user.id)
    },
    getSubmissionsByUserId(state) {
      return state.submissions.filter(s => s.userId === state.user.id)

    },
    getSubmissionsForCourseByUserId(state, getters) {
      const curricula = getters.curricula;
      const curriculumIds = curricula.map(curriculum => curriculum.curriculumId);
      const matchingHomeworkIds = [];
      for (const homework of state.homework) {
        if (curriculumIds.includes(homework.curriculumId)) {
          matchingHomeworkIds.push(homework.homeworkId);
        }
      }

      const userSubmissions = [];
      for (const submission of state.submissions) {
        if (matchingHomeworkIds.includes(submission.homeworkId)) {
          userSubmissions.push(submission);
        }
      }

      return userSubmissions.filter(s => s.userId === state.user.id)

    },
    getSubmissionsByHomeworkId(state) {
      return state.homework.filter(s => s.homeworkId == state.activeHomework)
    },
    getSubmissionsByCurriculumId(state) {
      const matchingHomeworkIds = state.homework
        .filter(hw => hw.curriculumId === state.activeCurriculum)
        .map(hw => hw.homeworkId);

      const submissionsForMatchingHomeworks = state.submissions.filter(submission => {
        return matchingHomeworkIds.includes(submission.homeworkId);
      });

      return submissionsForMatchingHomeworks;
    },
    getSubmissionsForCourse(state, getters) {
      const curricula = getters.curricula;
      const curriculumIds = curricula.map(curriculum => curriculum.curriculumId);
      const matchingHomeworkIds = [];
      for (const homework of state.homework) {
        if (curriculumIds.includes(homework.curriculumId)) {
          matchingHomeworkIds.push(homework.homeworkId);
        }
      }

      const userSubmissions = [];
      for (const submission of state.submissions) {
        if (matchingHomeworkIds.includes(submission.homeworkId)) {
          userSubmissions.push(submission);
        }
      }

      return userSubmissions;
    },
    getUngradedSubmissions(state) {
      return state.submissions.filter(submission => submission.score === null);
    },
  },
  actions: {
    async fetchCourses({ commit }) {
      try {
        const response = await CourseService.getCourses();
        if (response.status === 200) {
          const courseArray = response.data.map(course => ({
            name: course.name,
            description: course.description,
            difficulty: course.difficulty,
            cost: course.cost,
            id: course.id
          }));
          commit('SET_COURSES', courseArray);
        }

      } catch (error) {
        console.error("error occured", error);
      }
    },
    async deleteCourse({ commit }, courseId) {
      try {
        const response = await CourseService.deleteCourse(courseId);
        if (response.status === 204) {
          commit('DELETE_COURSE', courseId);
        }
      } catch (error) {
        console.error("Error", error);
      }

    },
    async fetchCurriculum({ commit }, courseId) {
      try {
        const response = await CurriculumService.getCurriculumByCourseId(courseId);
        if (response.status === 200) {
          const curriculumArray = response.data.map(curriculum => ({
            courseId: curriculum.courseId,
            curriculumId: curriculum.curriculumId,
            title: curriculum.title,
            // content: curriculum.content,
          }));
          commit('SET_CURRICULUM', curriculumArray);
        }

      } catch (error) {
        console.error("error occured", error);
      }
    },
    // async fetchStudents({ commit }) {
    //   try {
    //     const response = await UserService.getUsers();
    //     if (response.status === 200) {
    //       const userArray = response.data.map(user => ({
    //         userId: user.userId,
    //         userName: user.userName,
    //         role: user.userRole,
    //       }));
    //       const studentArray = userArray.filter(user => user.userRole === 'ROLE_USER')
    //       commit('SET_STUDENTS', studentArray);
    //       console.log(this.state.students)
    //     }

    //   } catch (error) {
    //     console.error("error occured", error);
    //   }
    // },
  }
})
