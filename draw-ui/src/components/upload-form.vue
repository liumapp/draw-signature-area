/**
 * @file upload-form.vue
 * @author liumapp
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 2018/9/28
 */
<template>
  <div class="upload-form">
    <Row>
      <Col span="18" offset="9">
      <Form ref="checkMsgForm" :model="checkMsgForm">
        <FormItem label="文档" prop="document">
          <doc-upload id="doc-upload"
                      :uploadUrl="uploadUrl"
                      @imgInfo="handleDoc" ></doc-upload>
        </FormItem>
        <FormItem>
          <Button type="primary" @click="handleSubmit('checkMsgForm')">下一步</Button>
        </FormItem>
      </Form>
      </Col>
    </Row>
  </div>
</template>
<script>
import util from '@/libs/util';
import docUpload from '@/components/doc-upload';
export default {
  name: 'uploadForm',
  components: {
    docUpload
  },
  data () {
    return {
      uploadUrl: util.ajaxUrl + 'upload/doc',
      checkMsgForm: {
        doc: null
      }
    }
  },
  methods: {
    handleSubmit (name) {
      console.log(this.checkMsgForm);
      console.log(this.checkMsgForm.doc == null);
      /*this.$refs[name].validate((valid) => {
        if (valid) {
          util.post('/info', this.checkMsgForm).then(res => {
            this.$Message.success('Success!');
            this.$emit('setFormData', this.checkMsgForm);
            this.$emit('next');
          });
        } else {
          this.$Message.error('Fail!');
        }
      });*/
    },
    handleDoc (file) {
      this.checkMsgForm.doc=file;
    }
  }
}

</script>
<style lang="less">
  .upload-form {
    margin-top: 30px;
  }
</style>
