<template>
    <div class="image-gallery">
      <img 
        v-for="(img, index) in imageList" 
        :key="index"
        :alt="'小耄耋艺术图 ' + (index + 1)" 
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
  const images = import.meta.glob('@/assets/小耄耋/小耄耋*.{jpg,png}', { eager: true })
  
  onMounted(() => {
    // 获取所有匹配的图片
   imageList.value = Object.values(images).map(mouble =>mouble.default)
  })
  </script>

  <style scoped>
  /* 保持之前的样式不变 */
  .image-gallery {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
    gap: 16px;
    padding: 16px;
    min-height: 100vh;
  }
  
  .maodie-image {
    width: 100%;
    height: auto;
    aspect-ratio: 1;
    object-fit: cover;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    transition: transform 0.3s ease;
  }
  
  .maodie-image:hover {
    transform: scale(1.03);
    box-shadow: 0 4px 8px rgba(0,0,0,0.2);
  }
  </style>