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
      url: ''
    };
  },
  created () {
    this.url = this.uploadUrl;
  },
  methods: {
    handleSuccess (response, file) {
      this.$Message.success('上传成功!');
      this.$emit('imgInfo', file);
    },
    handleBeforeUpload (file) {
      console.log(file);
      const format = file.type === 'application/msword';
      const size = file.size / 1024 / 1024 < 10;

      if (!format) {
        this.$Message.error('上传文档只能是 DOC 格式!')
      }
      if (!size) {
        this.$Message.error('上传文档大小不能超过 10MB!')
      }

      this.handleFileToBase64(file);
      return false;
    },
    handleFileToBase64 (file) {
      let reader = new FileReader();
      let _vue = this;
      reader.readAsDataURL(file);
      reader.onload = function () {
        _vue.fileList.push({name: file.name, content: reader.result, convertId: _vue.cid});
        _vue.readyUploadFile.push({name: file.name});
      }
    },
    submitPic () {
      if (!this.isEmpty()) {
        util.post('upload/multybase64', this.fileList).then(res => {
          this.$Message.success('file upload success!');
          this.$emit('setDocData',this.handleDocList());
          this.$emit('next');
        });
      } else {
        this.$Message.error('请至少上传一个doc文件');
      }
    },
  }

}
</script>
