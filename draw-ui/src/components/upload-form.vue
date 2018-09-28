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
      <Form ref="checkMsgForm" :model="checkMsgForm" :rules="checkMsgRule">
        <FormItem label="文档" prop="document">
          <doc-upload id="doc-upload"
                      :uploadUrl="uploadUrl"
                      @imgInfo="doc" ></doc-upload>
        </FormItem>
        <FormItem>
          <Button type="primary" @click="handleSubmit('checkMsgForm')">Submit</Button>
          <Button type="ghost" @click="handleReset('checkMsgForm')">Reset</Button>
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
      uploadUrl: util.ajaxUrl + 'file/',
      checkMsgForm: {
        doc: ''
      }
    }
  },
  methods: {
    handleSubmit (name) {
      this.$refs[name].validate((valid) => {
        if (valid) {
          util.post('/info', this.checkMsgForm).then(res => {
            this.$Message.success('Success!');
            this.$emit('setFormData', this.checkMsgForm);
            this.$emit('next');
          });
        } else {
          this.$Message.error('Fail!');
        }
      });
    },
    handleReset (name) {
      this.$refs[name].resetFields();
    },
    handlePic (file) {
      this.checkMsgForm.headPic=file.name;
    }
  }
}

</script>
<style lang="less">
  .upload-form {
    margin-top: 30px;
  }
</style>
