/**
 * @file index.js
 * @author liumapp
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 2018/10/9
 */

import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

// 数组排重
function getFilterArray (array) {
  const res = [];
  const json = {};
  for (let i = 0; i < array.length; i++){
    const _self = array[i];
    if(!json[_self]){
      res.push(_self);
      json[_self] = 1;
    }
  }
  return res;
}

const store = new Vuex.Store({
  state: {
    //the document currently being draw
    drawIndex: null,
    docList: []
  },
  getters: {
    docs: state => {
      return state.docList;
    },
    drawIndex: state => {
      return state.drawIndex;
    }
  },
  mutations: {
    UPDATE_DOC_LIST (state, data) {
      state.docList = data;
    },
    UPDATE_DOC (state, data, index) {
      state.docList[index] = data;
    },
    UPDATE_DRAW_INDEX (state, data) {
      state.drawIndex = data;
    }
  },
  actions: {
    uploadDocList (context, data) {
      context.commit('UPDATE_DOC_LIST', data);
    },
    updateDoc (context, data, index) {
      context.commit('UPDATE_DOC', data, index);
    },
    choiceDocToDraw (context, index) {
      context.commit('UPDATE_DRAW_INDEX', index);
    }
  }
});

export default store;
