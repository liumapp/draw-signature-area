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
    drawFile: null,
    docList: []
  },

});

export default store;
