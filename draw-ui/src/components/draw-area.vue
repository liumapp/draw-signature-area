/**
 * @file draw-area.vue
 * @author liumapp
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 2018/9/28
 */
<style lang="less">

</style>
<template>
<div>
  <br>
  <Row>
    <Col span="14" offset="5">
      <div class="picContainer">
        <img :src="currentPic">
        <sign-area :w="200" :h="100" v-on:dragging="onDrag" v-on:resizing="onResize" :parent="true" :resizable="false">
            X: {{ x }} / Y: {{ y }} - Width: {{ width }} / Height: {{ height }}</p>
        </sign-area>
        <sign-area :w="200" :h="100" v-on:dragging="onDrag" v-on:resizing="onResize" :parent="true" :resizable="false">
            X: {{ x }} / Y: {{ y }} - Width: {{ width }} / Height: {{ height }}</p>
        </sign-area>
      </div>
    </Col>
  </Row>
  <br>
  <Row>
    <Col span="14" offset="5">
      <Page :total="picNumbers" :page-size="1" show-elevator @on-change="onChangePage"></Page>
    </Col>
  </Row>
  <br>
  <Row>
    <Col span="2" offset="14">
    <Button type="default" @click="back">返回</Button>
    </Col>
    <Col span="2">
    <Button type="primary" @click="">提交</Button>
    </Col>
  </Row>
</div>
</template>
<script>
import testData from '@/column/DrawData'
import signArea from '@/lm-sign-area/'
import util from '@/libs/util'
export default {
  name: 'drawArea',
  data: function () {
    return {
      testData: {},
      currentPic: '',
      picNumbers: 0,
      width: 0,
      height: 0,
      x: 0,
      y: 0
    }
  },
  components: {
    signArea
  },
  created () {
    this.initTestData();
  },
  methods: {
    back () {
      this.$emit('prev');
    },
    initTestData () {
      this.testData = testData;
      this.currentPic = util.ajaxUrl + "/resource/getPic?filename=" + this.testData.picNames[0];
      this.picNumbers= this.testData.picNumbers;
    },
    onResize: function (x, y, width, height) {
      this.x = x
      this.y = y
      this.width = width
      this.height = height
    },
    onDrag: function (x, y) {
      this.x = x
      this.y = y
    },
    onChangePage: function (pages) {
      this.currentPic = util.ajaxUrl + "/resource/getPic?filename=" + this.testData.picNames[--pages];
    }
  }
}
</script>
<style lang="less">
  .picContainer {
    height: 841.92px;
    width: 595.32px;
    position: relative;
    border: 1px solid gray;
    img {
      height: 839.92px;
      width: 593.32px;
    }
  }
</style>
