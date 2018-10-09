/**
 * @file draw-area.vue
 * @author liumapp
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 2018/9/28
 */
<template>
<div>
  <br>
  <Row>
    <Col span="8" offset="8">
    <Upload
      multiple
      type="drag"
      action="#"
      accept=".doc, .docx"
      :before-upload="handleFileToBase64"
      :default-file-list="readyUploadFile"
      :on-remove="handleRmFile">
      <div style="padding: 20px 0">
        <Icon type="ios-cloud-upload" size="52" style="color: #3399ff"></Icon>
        <p>Click or drag files here to upload</p>
      </div>
    </Upload>
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
