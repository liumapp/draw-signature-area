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
        <sign-area v-for="(signer, index) in signers" :index="index" :w="200" :h="100" :x="signer.x" :y="signer.y" v-on:dragging="onDrag" :parent="true" :resizable="false">
          {{ signer.name }}签署区域
          <br>
          X is {{ signer.x }}
          <br>
          Y is {{ signer.y }}
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
import signArea from '@/dragging-elements/'
import util from '@/libs/util'
export default {
  name: 'drawArea',
  data: function () {
    return {
      testData: {},
      currentPic: '',
      picNumbers: 0,
      signers: [
        {
          name: "张三",
          x: 75,
          y: 500
        },
        {
          name: "李四",
          x: 325,
          y: 500
        },
        {
          name: "王五",
          x: 75,
          y: 650
        }
      ]
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
    onDrag: function (x, y, index) {
      this.signers[index].x = x;
      this.signers[index].y = y;
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
