<template>
  <v-app id="inspire">

    <MainNavigation ref="navigation" />

    <v-toolbar color="blue-grey" dark fixed app clipped-right>
      <v-spacer></v-spacer>
      <v-toolbar-title>Toolbar</v-toolbar-title>
      <v-toolbar-side-icon @click.stop="toggleNavigation"></v-toolbar-side-icon>
    </v-toolbar>

    <v-content>
      <v-container fluid fill-height>
        <router-view></router-view>
        <!-- <v-layout justify-center align-center>
          <v-flex shrink>
            <v-tooltip right>
              <v-btn
                slot="activator"
                :href="source"
                icon
                large
                target="_blank"
              >
                <v-icon large>code</v-icon>
              </v-btn>
              <span>Source</span>
            </v-tooltip>
            <v-tooltip right>
              <v-btn slot="activator" icon large href="https://codepen.io/johnjleider/pen/KQrPKJ" target="_blank">
                <v-icon large>mdi-codepen</v-icon>
              </v-btn>
              <span>Codepen</span>
            </v-tooltip>
          </v-flex>
        </v-layout> -->
      </v-container>
    </v-content>

    <v-footer color="blue-grey" class="white--text" app>
      <span>Vuetify</span>
      <v-spacer></v-spacer>
      <span>&copy; 2017</span>
    </v-footer>
    
  </v-app>
</template>

<script>
import MainNavigation from "../components/MainNavigation"

export default {
  components : {
    MainNavigation
  },
  data: () => ({
  }),
  methods: {
    toggleNavigation : function() {
      this.$refs.navigation.toggleNav();
    }
  },
  props: {
    source: String
  }
}
</script>
