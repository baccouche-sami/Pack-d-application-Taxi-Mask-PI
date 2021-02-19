<template>
  <div class="the-navbar__user-meta flex items-center" v-if="activeUserInfo.photoURL">

    <div class="text-right leading-tight hidden sm:block">
      <p class="font-semibold">{{ currentUser.nom }}</p>
      <small>{{ currentUser.username }}</small>
    </div>

    <vs-dropdown vs-custom-content vs-trigger-click class="cursor-pointer">

      <div class="con-img ml-3">
        <img v-if="activeUserInfo.photoURL" key="onlineImg" :src="activeUserInfo.photoURL" alt="user-img" width="40" height="40" class="rounded-full shadow-md cursor-pointer block" />
      </div>

      <vs-dropdown-menu class="vx-navbar-dropdown">
        <ul style="min-width: 9rem">

          <li class="flex py-2 px-4 cursor-pointer hover:bg-primary hover:text-white" @click="profil">
            <feather-icon icon="UserIcon" svgClasses="w-4 h-4" />
            <span class="ml-2">Profil</span>
          </li>

          <vs-divider class="m-1" />

          <li
            class="flex py-2 px-4 cursor-pointer hover:bg-primary hover:text-white"
            @click="logout">
            <feather-icon icon="LogOutIcon" svgClasses="w-4 h-4" />
            <span class="ml-2">Logout</span>
          </li>
        </ul>
      </vs-dropdown-menu>
    </vs-dropdown>
  </div>
</template>

<script>
export default {
  watch: {
  },
  data() {
    return {

    }
  },
  computed: {
     currentUser() {
      return this.$store.state.auth.user;
    },
    activeUserInfo() {
      return this.$store.state.AppActiveUser
    }
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    }
  },
  methods: {
    profil() {
      this.$router.push('/profil');
    },
    logout() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/login');
    },
  }
}
</script>
