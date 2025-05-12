<template>
    <div class="image-container">
      <img :src="Image" alt="耄耋图片" class="cat-image"/>
      <button @click="handleClick" class="switch-button">
        {{ clickText }} {{ clickcount }}/3
      </button>
      <button @click="feedClick" class="feed-button">投喂</button>
    </div>
  </template>
  
  <script>
  import { ref, computed } from 'vue'
  import Image1 from '@/assets/养成耄耋/养成耄耋 (1).jpg'
  import Image2 from '@/assets/养成耄耋/养成耄耋 (2).jpg'
  import Image3 from '@/assets/养成耄耋/养成耄耋(3).png'
  
  export default {
    setup() {
      // 按钮文本数组
      const clickTexts = ["哈！", "哈！！", "哈！！！"]
      // 点击计数器
      const clickcount = ref(0)
      // 图片切换状态
      const currentImage = ref(Image1)
  
      // 计算当前显示的按钮文本
      const clickText = computed(() => {
        return clickTexts[clickcount.value % clickTexts.length]
      })
  
      // 点击事件处理
      const handleClick = () => {
        clickcount.value++
        
        // 点击3次后切换图片并重置计数
        if (clickcount.value >= 3) {
          currentImage.value = currentImage.value === Image1 ? Image2 : Image1
          clickcount.value = 0
        }
      }
  
      // 投喂事件处理
      const feedClick = () => {
        currentImage.value = Image3
      }
  
      return {
        Image: currentImage,
        clickcount,
        clickText,
        handleClick,
        feedClick
      }
    }
  }
  </script>
  
  <style scoped>
  .image-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    min-height: 100vh;
    padding: 20px;
    box-sizing: border-box;
  }
  
  .cat-image {
    width: auto;
    height: 75vh; /* 占据视口高度的75% */
    max-width: 90%; /* 防止过宽 */
    object-fit: contain; /* 保持比例不变形 */
    object-position: center;
    margin-bottom: 30px;
    box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
    transition: transform 0.3s ease;
  }
  
  .cat-image:hover {
    transform: scale(1.03);
  }
  
  .switch-button {
    padding: 15px 30px;
    background-color: #4CAF50;
    color: white;
    border: none;
    border-radius: 10px;
    font-size: 1.2rem;
    cursor: pointer;
    transition: all 0.3s;
    z-index: 10;
    margin-bottom: 15px;
  }
  
  .feed-button {
    padding: 15px 30px;
    background-color: #f39c12;
    color: white;
    border: none;
    border-radius: 10px;
    font-size: 1.2rem;
    cursor: pointer;
    transition: all 0.3s;
    z-index: 10;
  }
  
  /* 响应式调整 */
  @media (max-width: 768px) {
    .cat-image {
      height: 70vh;
      max-width: 95%;
    }
    
    .switch-button,
    .feed-button {
      padding: 12px 25px;
      font-size: 1rem;
    }
  }
  </style>