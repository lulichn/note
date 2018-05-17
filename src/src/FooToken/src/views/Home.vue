<template>
  <div class="home">
    <h3>This is an customer page</h3>
    <FooToken/>
  </div>
</template>

<script>
import FooToken from '../components/FooToken.vue'

export default {
  name: 'home',
  components: {
    FooToken
  }
}
</script>
