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
    <Col span="8" offset="8">
      this is draw area
    </Col>
  </Row>
  <Row>
    <Col span="2" offset="14">
    <Button type="default" @click="back">返回</Button>
    </Col>
    <Col span="2">
    <Button type="primary" @click="submitPic">提交</Button>
    </Col>
  </Row>
</div>
</template>
<script>
export default {
  name: 'drawArea',
  methods: {
    back () {
      this.$emit('prev');
    }
  }
}
</script>
