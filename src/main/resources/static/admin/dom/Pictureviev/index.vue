<!--       自定图片查看     -->
<template>
    <div >
        <ul class="water_ul">
            <li  v-for="item in mylistdata" :style="itemwidth" >{{item.height}}</li>
        </ul>
    </div>
</template>
<script>
    module.exports={

    }
</script>