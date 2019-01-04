<template>
    <v-navigation-drawer v-model="isOpen" fixed right clipped app>
      <v-list dense>
        <v-list-tile @click.stop="toggleNav">
          <v-list-tile-action>
            <v-icon>exit_to_app</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title>Open Temporary Drawer</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
      </v-list>
    </v-navigation-drawer>
</template>

<script>
export default {
    data: () => ({
        isOpen : false
    }), 
    methods: {
        toggleNav : function() {
            this.isOpen = !this.isOpen;
        }
    }
}
</script>
