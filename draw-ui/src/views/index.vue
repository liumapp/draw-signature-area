/**
 * @file index.vue
 * @author liumapp
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 2018/9/28
 */
<template>
  <div>
    <Row>
      <Col span="18" offset="3">
      <Card>
        <Steps :current="current">
          <Step title="上传doc文件" content="上传基础doc文件用于后续绘制签署区域"></Step>
          <Step title="绘制区域" content="后端转换为Pic后获取文件，并在其上绘制200px*100px矩形"></Step>
          <Step title="下载" content="绘制成功后供后端处理，在指定的区域上附加一张图片后下载最终PDF文件"></Step>
        </Steps>
        <upload-form v-if="current == 0" @setFormData="setFormData" @next="nextStep"></upload-form>
        <first-check v-if="current == 1" @next="nextStep" @prev="prevStep" :formData="formData"></first-check>
        <second-check v-if="current == 2" @next="nextStep" @prev="prevStep"></second-check>
        <result v-if="current == 3"></result>
      </Card>
      </Col>
    </Row>
  </div>
</template>
<script>
import util from '@/libs/util';
import uploadForm from '@/components/upload-form';
import drawArea from '@/components/draw-area';
import download from '@/components/download';
export default {
  name: 'index',
  components: {
    uploadForm, drawArea, download
  },
  data () {
    return {
      current: 0,
      formData: {}
    }
  },
  methods: {
    setFormData (data) {
      this.formData = data;
    },
    nextStep () {
      this.current++;
    },
    prevStep () {
      this.current--;
    }
  }
}
</script>
