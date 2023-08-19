import axios from 'axios';
export default{
    getCourses(){
        return axios.get('/course');
    },
    getCourseById(id){
        return axios.get(`/course/id/${id}`)
    },
    getCourseByName(name){
        return axios.get(`/course/name/${name}`)
    },
    createCourse(course) {
        return axios.post('/course',course);
      },
      deleteCourse(courseId){
          return axios.delete(`/course/${courseId}`);
      }
      
}