<template>
  <div class="image-gallery">
    <img 
      v-for="(img, index) in imageList" 
      :key="index"
      :alt="'普通耄耋艺术图 ' + (index + 1)" 
      class="耄耋"
      :src="img"
      loading="lazy"
    />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const imageList = ref([])

// 修正1：修正了拼写错误 import.meta.glob（原代码是 import.meta.gola）
const images = import.meta.glob('@/assets/普通耄耋/普通耄耋(*).{png,jpg}', { eager: true })

onMounted(() => {
  // 生成图片URL列表
  for (const path in images) {
    imageList.value.push(images[path].default)
  }
  
  // 调试用：打印加载的图片数量
  console.log('已加载图片数量:', imageList.value.length)
})
</script>

<style>
  .image-gallery {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
    gap: 16px;
    padding: 16px;
    height: 100%;
    min-height: 100vh;
    overflow-y: auto;
  }
  
  .耄耋 {
    width: 100%;
    height: auto;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  }
</style>