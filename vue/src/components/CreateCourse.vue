<template>

<div class ="new-course-container">
    <form class="new-course-form" v-on:submit.prevent="addCourse()">
        <input type="text" class="name-input" placeholder="Course Name" v-model="newCourse.name">
        <textarea name="decription-input" class="description-input" id="" cols="30" rows="10" placeholder="Course Decription" v-model="newCourse.description" />
        <label for="difficulty">Difficulty</label>
        <select name="difficulty" id="difficulty" v-model="newCourse.difficulty">
            <option value="easy">Easy</option>
            <option value="medium">Medium</option>
            <option value="difficult">Difficult</option>
        </select>
        <label for="cost">Cost</label>
        <select name="cost" id="cost" v-model="newCourse.cost">
            <option value= 0 >Free</option>
            <option value= 100 >$100</option>
            <option value= 200 >$200</option>
        </select>
        <div class = "buttons">  
<input type="submit" value="Create Course" class = "submit-button">
        <router-link :to="{name: 'home'}" class = "cancel-button">Cancel</router-link>
        </div>
        


    </form>
    </div>
</template>

<script>
import CourseService from '@/services/CourseService';
export default {
    name: 'create-course',
    data() {
        return {
            newCourse: {
                id: '',
                name: '',
                description: '',
                cost: ''
            },
            newlyCreatedCourse: null,
        }
    },
    methods: {
       async addCourse() {
            try{
                const response =  await CourseService.createCourse(this.newCourse);
                if(response.status === 201){
                    this.newCourse = response.data;
                    this.$router.push({name: 'home'});
                    this.$store.commit('ADD_COURSE', this.newCourse)
                }
            } catch(error){
                console.error("An error occured", error);
            }

            } 
            // this.$store.commit('ADD_COURSE', this.newCourse);
            // this.newCourse = {};
            // this.$router.push({name: 'home'})
        },
        getId() {
            return this.$store.getters.newCourseId;
        }
    }

</script>

<style>
.new-course-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}
.new-course-form {
    display: flex;
    flex-direction: column;
    width: 500px;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 8px;
    background-color: #f9f9f9;

    align-content: center;
    font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
     
}
.buttons {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

.submit-button {
  background-color: #A447A6;
  color: #fff;
  border: none;
  padding: 8px 16px;
  border-radius: 4px;
  cursor: pointer;
}

.cancel-button {
  color: #A447A6;
  text-decoration: none;
}


textarea {
    font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;

}
</style>