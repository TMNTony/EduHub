<template>
  <div class="content-container">
    <content-sidebar id="sidebars"/>
    <div v-if="!show" class="content-main">
      <div>
        <input class="button" type="submit" @click="savePage" value="Save Page" />
        <input class="button" type="submit" @click="deletePage" value="Delete Page" />
        <button class="button" v-if="$route.params.pageNum == totalPages()" @click="addPage">
          Add Page
        </button>
        <button class="button" @click="addHomework">Add Homework</button>
      </div>
      <input type="text" v-model="currentPage.title" placeholder="Title" />
      <text-editor
        v-model="currentPage.content"
        :content="currentPage.content"
        @update:content="currentPage.content = $event"
        class="editor"
      />
      <div v-if="show" class="curriculum-content">
        <div>{{ currentPage.title }}</div>
        <div v-html="currentPage.content"></div>
      </div>
      <div>
        <router-link
        class="directions"
          v-if="$route.params.pageNum > 1"
          :to="{
            name: 'content',
            params: {
              id: $route.params.id,
              currid: $route.params.currid,
              pageNum: $route.params.pageNum - 1,
            },
          }"
        >
          Previous Page
        </router-link>
        <router-link
        class="directions"
          v-if="$route.params.pageNum < totalPages()"
          :to="{
            name: 'content',
            params: {
              id: $route.params.id,
              currid: $route.params.currid,
              pageNum: currentPage.pageNum + 1,
            },
          }"
        >
          Next Page
        </router-link>
      </div>
    </div>
    <div v-else class="content">
      <div v-if="currentPage" v-html="currentPage.content"></div>
    </div>
  </div>
</template>

<script>
import TextEditor from "./TextEditor.vue";

import ContentSidebar from "./ContentSidebar.vue";

export default {
  name: "content-page",
  components: {
    TextEditor,
    ContentSidebar,
  },

  props: {},
  data() {
    return {
      editing: false,
      isAdmin: false,
      currentPage: {
        curriculumId: 0,
        pageNum: 0,
        title: "",
        content: "",
      },
    };
  },
  methods: {
    updatePageContent(newContent) {
      this.currentPage.content = newContent;
    },
    savePage() {
      this.$store.commit("SAVE_PAGE", this.currentPage);
      // this.$router.push({
      //   name: "course",
      //   params: { id: this.$route.params.id },
      // });
    },
    toggleEdit() {
      this.editing = !this.editing;
    },
    totalPages() {
      return this.$store.getters.totalCurriculumPages;
    },
    addPage() {
      const newPageNum = this.currentPage.pageNum + 1;
      const newPage = {
        curriculumId: this.currentPage.curriculumId,
        pageNum: newPageNum,
        title: "",
        content: "New Page",
      };
      this.$store.commit("ADD_PAGE", newPage);
      this.$router.push({
        name: "content",
        params: {
          id: this.$route.params.id,
          currid: newPage.curriculumId,
          pageNum: newPageNum,
        },
      });
    },
    deletePage() {
      this.$store.commit("DELETE_PAGE");
      this.$router.push({
        name: "course",
        params: { id: this.$route.params.id },
      });
    },
    addHomework() {
      const newHomeworkId = this.$store.getters.newHomeworkId;
      const newHomework = {
        homeworkId: newHomeworkId,
        curriculumId: this.$route.params.currid,
        assignment: "add assignment here",
      };
      this.$store.commit("ADD_HOMEWORK", newHomework);
      this.$router.push({
        name: "homework",
        params: {
          id: this.$route.params.id,
          currid: this.$route.params.currid,
          homeworkId: newHomeworkId,
        },
      });
    },
    fetchPageData() {
      const curriculumId = this.$route.params.currid;
      const pageNum = this.$route.params.pageNum;
      this.$store.commit("SET_ACTIVE_CURRICULUM", curriculumId);
      this.$store.commit("SET_ACTIVE_PAGE", pageNum);
      const fetchedPage = this.$store.getters.getPage;
      this.currentPage = fetchedPage;
      this.isAdmin = this.$store.getters.isAdmin;
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
  watch: {
    $route() {
      this.fetchPageData();
    },
  },
  created() {
    this.fetchPageData();
  },
};
</script>

<style>
.directions {
  color: white;
}
.content-container {
  display: grid;
  
}
.editor {
  margin-top: 10px;
  
}

.content-main {
  display: flex;
  flex-direction: column;
  margin-left: 20%;
  margin-right: 5%;
  margin-top: 5%;
  height: 100%;
  justify-content: center;
  align-items: center;
}
.content {
  background-color: rgb(255, 255, 255);
  margin-left: 20%;
  margin-right: 5%;
  margin-top: 10%;
  height: 100%;
  border-radius: 10px;
  padding: 20px;
}


.button{
  align-items: center;
  appearance: none;
  border-radius: 4px;
  border-style: none;
  box-shadow: rgba(0, 0, 0, .2) 0 3px 1px -2px,rgba(0, 0, 0, .14) 0 2px 2px 0,rgba(0, 0, 0, .12) 0 1px 5px 0;
  box-sizing: border-box;
  color: #fff;
  cursor: pointer;
  display: inline-flex;
  font-family: 'Times New Roman', Times, serif;
  font-size: .875rem;
  font-weight: 500;
  height: 36px;
  justify-content: center;
  letter-spacing: .0892857em;
  line-height: normal;
  min-width: 64px;
  outline: none;
  overflow: visible;
  padding: 0 16px;
  position: relative;
  text-align: center;
  text-decoration: none;
  text-transform: uppercase;
  transition: box-shadow 280ms cubic-bezier(.4, 0, .2, 1);
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
  vertical-align: middle;
  will-change: transform,opacity;
 margin-bottom: 10px;
 margin-left: 10px;
 margin-top: 10px;
}

.button:hover {
  box-shadow: rgba(0, 0, 0, .2) 0 2px 4px -1px, rgba(0, 0, 0, .14) 0 4px 5px 0, rgba(0, 0, 0, .12) 0 1px 10px 0;
}

.button:disabled {
  background-color: rgba(0, 0, 0, .12);
  box-shadow: rgba(0, 0, 0, .2) 0 0 0 0, rgba(0, 0, 0, .14) 0 0 0 0, rgba(0, 0, 0, .12) 0 0 0 0;
  color: rgba(0, 0, 0, .37);
  cursor: default;
  pointer-events: none;
}

.button:not(:disabled) {
  background-color: #a447a6;
}

.button:focus {
  box-shadow: rgba(0, 0, 0, .2) 0 2px 4px -1px, rgba(0, 0, 0, .14) 0 4px 5px 0, rgba(0, 0, 0, .12) 0 1px 10px 0;
}

.button:active {
  box-shadow: rgba(0, 0, 0, .2) 0 5px 5px -3px, rgba(0, 0, 0, .14) 0 8px 10px 1px, rgba(0, 0, 0, .12) 0 3px 14px 2px;
  background: #A46BF5;
}
</style>
