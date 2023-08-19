import axios from 'axios';

export default {

  getCurriculumById(courseId) {
    return axios.get(`/curriculum/id/${courseId}`)
  },

  createCurriculum(curriculum) {
    return axios.post('/curriculum', curriculum)
  },

  updateCurriculum(curriculumId){
      return axios.put(`/curriculum/${curriculumId}`)
  },
  deleteCurriculum(curriculumId){
      return axios.delete(`/curriculum/${curriculumId}`)
  },
  getCurriculumByCourseId(courseId){
    return axios.get(`curriculum/bycourse/${courseId}`)
  }
  
  

}

