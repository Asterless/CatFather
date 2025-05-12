<template>
  <div class="image-gallery">
    <img 
      v-for="(img, index) in imageList" 
      :key="index"
      :alt="'古神耄耋艺术图 ' + (index + 1)" 
      class="耄耋"
      :src="img"
      londing="lazy"

    />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const imageList = ref([])

// 使用 import.meta.glob 动态导入图片
const images = import.meta.glob('@/assets/古神耄耋/古神耄耋 (*).{jpg,png}', { eager: true })

onMounted(() => {
  // 获取所有匹配的图片
  for (const path in images) {
    imageList.value.push(images[path].default)
  }
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
