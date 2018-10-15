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
    <div style="height: 500px; width: 500px; border: 1px solid red; position: relative;">
      <Deformation :w="100" :h="100" v-on:dragging="onDrag" v-on:resizing="onResize" :parent="true">
        <p>Hello! I'm a flexible component. You can drag me around and you can resize me.<br>
          X: {{ x }} / Y: {{ y }} - Width: {{ width }} / Height: {{ height }}</p>
      </Deformation>
      <Deformation :w="100" :h="100" v-on:dragging="onDrag" v-on:resizing="onResize" :parent="true">
        <p>Hello! I'm a flexible component. You can drag me around and you can resize me.<br>
          X: {{ x }} / Y: {{ y }} - Width: {{ width }} / Height: {{ height }}</p>
      </Deformation>
    </div>
    <Col span="8" offset="8">
      this is draw area
    </Col>
  </Row>
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
import Deformation from 'deformation'

export default {
  name: 'drawArea',
  data: function () {
    return {
      testData: {},
      width: 0,
      height: 0,
      x: 0,
      y: 0
    }
  },
  components: {
    Deformation
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
    }
  }
}
</script>
