<template>
  <div v-if="$store.state.token !== ''" class="nav-container">
    <img src="../assets/blacklogo.png" alt="Logo" class ="nav-image" />
    <div class="nav-links">
      <router-link v-bind:to="{ name: 'home' }" class="nav-link"
        >Home</router-link
      >
      <router-link v-bind:to="{ name: 'logout' }" class="nav-link"
        >Logout</router-link
      >
      <div v-if="$route.params.id !== undefined">
       
        <router-link
          v-if="!show"
          :to="{
            name: 'grade-homework',
            params: { id: $route.params.id },
          }"
          class="nav-link"
          >Grade Homework</router-link
        >
      </div>
      <div v-if="$route.params.id !== undefined">
        
        <router-link
          v-if="$route.params.id !== undefined"
          v-bind:to="{ name: 'progress', params: { id: $route.params.id } }"
          class="nav-link"
          >Course Progress</router-link
        >
      </div>
    </div>
    <div v-if="isAdmin" class="toggle-container">
      <div>Student View</div>
      <label for="toggleSwitch" class="switch">
        <input
          type="checkbox"
          id="toggleSwitch"
          v-model="studentView"
          @change="studentToggle"
        />
        <span class="slider round"></span>
      </label>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isAdmin: false,
    };
  },
  computed: {
    studentView: {
      get() {
        return this.$store.state.studentView;
      },
      set() {},
    },
    show() {
      return !this.isAdmin || this.studentView;
    },
  },
  methods: {
    studentToggle() {
      this.$store.commit("TOGGLE_VIEW");
      console.log(this.studentView);
    },
  },
  created() {
    this.isAdmin = this.$store.getters.isAdmin;
    console.log(this.$store.state.user.id);
  },
};
</script>

<style>
.nav-container {
  display: flex;
  justify-content: center;
  align-items: center;
}

.nav-links {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
}
.nav-link {
  text-decoration: none;
  color: white;
  margin: 0 1rem;
  transition: opacity 0.2s ease;
}
.nav-image {
  height: 50px;
  margin-left: 20px;
}

.nav-link:hover {
  opacity: 0.8;
}
/* The switch - the box around the slider */
.switch {
  position: relative;
  display: inline-block;
  width: 60px;
  height: 34px;
}

/* Hide default HTML checkbox */
.switch input {
  opacity: 0;
  width: 0;
  height: 0;
}

/* The slider */
.slider {
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #ccc;
  -webkit-transition: 0.4s;
  transition: 0.4s;
}

.slider:before {
  position: absolute;
  content: "";
  height: 26px;
  width: 26px;
  left: 4px;
  bottom: 4px;
  background-color: white;
  -webkit-transition: 0.4s;
  transition: 0.4s;
}

input:checked + .slider {
  background-color: #2196f3;
}

input:focus + .slider {
  box-shadow: 0 0 1px #2196f3;
}

input:checked + .slider:before {
  -webkit-transform: translateX(26px);
  -ms-transform: translateX(26px);
  transform: translateX(26px);
}

/* Rounded sliders */
.slider.round {
  border-radius: 34px;
}

.slider.round:before {
  border-radius: 50%;
}
.toggle-container {
  display: flex;
 flex-direction: column;
  align-items: center;
  margin-right: 3rem; /* Adjust the margin as needed */
}
</style>
