/**
 * @file upload-form.vue
 * @author liumapp
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 2018/9/28
 */
<template>
  <Form ref="checkMsgForm" :model="checkMsgForm" :rules="checkMsgRule">
    <FormItem label="文档" prop="document">
      <doc-upload id="doc-upload"
                       :uploadUrl="uploadUrl"
                       @imgInfo="handlePic" ></doc-upload>
    </FormItem>
    <FormItem>
      <Button type="primary" @click="handleSubmit('checkMsgForm')">Submit</Button>
      <Button type="ghost" @click="handleReset('checkMsgForm')">Reset</Button>
    </FormItem>
  </Form>
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
    const validateAge = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('Age cannot be empty'));
      }
      // 模拟异步验证效果
      setTimeout(() => {
        if (!Number.isInteger(value)) {
          callback(new Error('Please enter a numeric value'));
        } else {
          if (value < 18) {
            callback(new Error('Must be over 18 years of age'));
          } else {
            callback();
          }
        }
      }, 200);
    };

    return {
      uploadUrl: util.ajaxUrl + 'file/',
      checkMsgForm: {
        name: '用户名称呢',
        age: 22,
        headPic: ''
      },
      checkMsgRule: {
        name: [
          {
            type: 'string',
            min: 5,
            max: 10,
            message: 'the username size shall be no more than 10 chars and no less than 5 chars ',
            trigger: 'blur'
          }
        ],
        age: [
          {
            validator: validateAge,
            trigger: 'blur'
          }
        ]
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

}
</script>
