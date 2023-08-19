<template>
  <div id="app">
    <top-nav id="top-nav" />
    <router-view id="router-view" />
  </div>
</template>
<script>
import TopNav from "./components/TopNav.vue";

export default {
  components: {
    TopNav,
  },
};
</script>
<style>

html, body {
  margin: 0;
  padding: 0;
  background: linear-gradient(to bottom, #1c1c1c, #333333);
  height: 100%;  
  width: 100%;
  font-family:'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif

}

#top-nav {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 7%;
  background-color: #a447a6;;
  color: white;
  padding: 10px;
  z-index: 1000; 
  padding: 1rem;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
