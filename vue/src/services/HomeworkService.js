import axios from 'axios';

export default {

  getHomeworkByParentId(curriculumId) {
    return axios.get(`/homework/id/parentId/${curriculumId}`)
  },
  getHomeworkByUserId(userId){
      return axios.get(`/homework/id/${userId}`)
  },
  createHomework(homework) {
    return axios.post('/homework', homework)
  },

  updatehomeowkr(homeworkId){
      return axios.put(`/homework/${homeworkId}`)
  },
  deleteHomework(homeworkId){
      return axios.delete(`/homework/${homeworkId}`)
  },
  
  
  

}