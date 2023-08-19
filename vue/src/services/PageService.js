import axios from 'axios';

export default {

  getPageCurriculumById(curriculumId) {
    return axios.get(`/pages/${curriculumId}`)
  },

  createPage(page) {
    return axios.post('/pages', page)
  },

  updatePage(pageId){
      return axios.put(`/pages/${pageId}`)
  },
  deletePage(pageId){
      return axios.delete(`/pages/${pageId}`)
  }
  
  

}