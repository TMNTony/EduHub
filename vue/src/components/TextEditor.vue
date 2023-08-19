<template>
  <main id="sample">
    <editor
      api-key="vc91q08hd6uey57md65ljphk6byh7xots8qwkvy02x9cm402"
      :init="{
        height: 500,
        menubar: true,
        plugins: [
          'a11ychecker',
          'advlist',
          'advcode',
          'advtable',
          'autolink',
          'checklist',
          'export',
          'lists',
          'link',
          'image',
          'charmap',
          'preview',
          'anchor',
          'searchreplace',
          'visualblocks',
          'powerpaste',
          'fullscreen',
          'formatpainter',
          'insertdatetime',
          'media',
          'table',
          'help',
          'wordcount',
        ],
        toolbar:
          'undo redo | casechange blocks | bold italic backcolor | \
           alignleft aligncenter alignright alignjustify | \
           bullist numlst checklist outdent indent | removeformat | a11ycheck code table help',
      }"
      :initial-value="initialValue"
      @input="updateContent"
    />
  </main>
</template>

<script>
import Editor from "@tinymce/tinymce-vue";
export default {
  name: "text-editor",
  components: {
    Editor,
  },
  computed: {
    initialValue() {
      // Determine the appropriate initial value based on the mode
      return this.mode === "homework" ? this.homework : this.content;
    },
  },
  props: {
    content: String,
    homework: String,
    mode: String,
  },
  methods: {
    updateContent(newContent) {
      if (this.mode == 'homework') {
        this.$emit("update:homework", newContent);
      } else {
        this.$emit("update:content", newContent);
      }
    },
  },
};
</script>

<style scoped>
.logo {
  display: block;
  margin: 0 auto 2rem;
}

@media (min-width: 1024px) {
  #sample {
    display: flex;
    flex-direction: column;
    place-items: center;
    width: 1000px;
  }
}
</style>
