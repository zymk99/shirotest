<template>
    <div >
        <div v-for="item in rownum">{{item}}</div>
    </div>
</template>

<script>
    module.exports={
        props:{
            rownum:Number
        },
        data:{

        }
    }
</script>