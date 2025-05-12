<template>
    <div class="image-gallery">
      <img 
        v-for="(img, index) in imageList" 
        :key="index"
        :alt="'古神耄耋艺术图 ' + (index + 1)" 
        class="耄耋"
        :src="img"
        loading="lazy"
      />
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted, initCustomFormatter } from 'vue'
  
  const imageList = ref([])
  
  // 修改后的图片路径匹配模式
  const images = import.meta.glob('@/assets/军事耄耋/军事耄耋*.{jpg,png}', { eager: true })
  
  onMounted(() => {
    // 获取所有匹配的图片
   imageList.value = Object.values(images).map(mouble =>mouble.default)
  })
  </script>

  <style scoped>
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