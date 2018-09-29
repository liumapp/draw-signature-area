/**
 * @file doc-upload.vue
 * @author liumapp
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 2018/9/28
 */
<template>
  <Upload
          accept=".doc, .docx"
          :action="url"
          :on-success="handleSuccess"
          :before-upload="handleBeforeUpload">
    <Button type="ghost" icon="ios-cloud-upload-outline">上传文件</Button>
  </Upload>
</template>
<script>
import util from '@/libs/util.js';
export default {
  name: 'docUpload',
  props: [
    'uploadUrl'
  ],
  data () {
    return {
      url: '',
      fileInfo: null
    };
  },
  created () {
    this.url = this.uploadUrl;
  },
  methods: {
    handleBeforeUpload (file) {
      const format = file.type === 'application/msword';
      const size = file.size / 1024 / 1024 < 10;

      if (!format) {
        this.$Message.error('上传文档只能是 DOC 格式!')
      }
      if (!size) {
        this.$Message.error('上传文档大小不能超过 10MB!')
      }

      this.handleFileToBase64(file);
      this.$emit('docInfo', fileInfo);
      return false;
    },
    handleFileToBase64 (file) {
      let reader = new FileReader();
      let _vue = this;
      reader.readAsDataURL(file);
      reader.onload = function () {
        _vue.fileInfo = {name: file.name, content: reader.result};
      }
    }
  }

}
</script>
