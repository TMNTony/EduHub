<template>
  <div>
    <div id="login" class="login-container">
      <div class="image-container">
        <router-link :to="{ name: 'home' }">
          <img src="../assets/newlogo.png" alt="Logo" class ="logo-image" />
        </router-link>
      </div>
      <div class="login-content">
        <form @submit.prevent="login">
          <h1 class ="login-title">Please Sign In</h1>
          <div role="alert" v-if="invalidCredentials">
            Invalid username and password!
          </div>
          <div role="alert" v-if="this.$route.query.registration">
            Thank you for registering, please sign in.
          </div>
          <div class="form-input-group">
            <label for="username">Username</label>
            <input
              type="text"
              id="username"
              v-model="user.username"
              required
              autofocus
              class= "input-field"
            />
          </div>
          <div class="form-input-group">
            <label for="password">Password</label>
            <input
              type="password"
              id="password"
              v-model="user.password"
              required
              class= "input-field"
            />
          </div>
          <button type="submit" class= "login-button">Sign in</button>
          <p class="signup-link">
            <router-link :to="{ name: 'register' }"
              >Need an account? Sign up.</router-link
            >
          </p>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>

<style scoped>
.form-input-group {
  margin-bottom: 1rem;
}
label {
  margin-right: 0.5rem;
}
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #1c1c1c;
}
.image-container {
  width: 20%;
  padding: 2rem;
  text-align: center;
}

.logo-image {
  max-width: 100%;
  height: auto;
}

.login-content {
  width: 50%;
  height: 60%;
  padding: 4rem;
  box-shadow: 0 15px 30px rgba(0, 0, 0, 0.2), 0 10px rgba(0, 0, 0, 0.2);
  background: linear-gradient(to bottom,#1c1c1c,#333333);
  display: flex;
  flex-direction:column;
  justify-content: center;
  align-items: center;
  border-radius: 10px;
  color: #A447A6;
}

.login-title {
  font-size: 1.5rem;
  margin-bottom: 1.5rem;
  color: #A447A6;
   align-items: center;
  justify-content: center;
  display: flex;
}

.alert {
  padding: 0.5rem;
  margin-bottom: 1rem;
  color: #f44336;
  background-color: #ffeeee;
  border: 1px solid #f44336;
  border-radius: 4px;
}

.form-input-group {
  margin-bottom: 1.5rem;
}

.input-field {
  width: 100%;
  padding: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.login-button {
  padding: 0.5rem 1rem;
  background-color: #A447A6;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.signup-link {
  margin-top: 1rem;
  text-align: center;
}
.signup-link a {
  color: #A447A6;
  text-decoration: none;
}

.login-content:hover {
  border: 2px solid #A447A6;
}

</style>