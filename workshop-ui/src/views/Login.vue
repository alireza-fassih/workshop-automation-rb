<template>
<v-container fluid fill-height>
  <v-layout align-center justify-center>
    <v-flex xs12 sm8 md4>
      <v-card class="elevation-12">
        <v-toolbar dark color="success">
          <v-toolbar-title>ورود با سامانه</v-toolbar-title>
        </v-toolbar>
        <v-card-text>
          <v-form class="rtl">
            <v-text-field v-model="data.username" prepend-icon="person" name="login" label="نام کاربری" type="text"></v-text-field>
            <v-text-field v-model="data.password" id="password" prepend-icon="lock" name="password" label="گذرواژه" type="password"></v-text-field>
          </v-form>
        </v-card-text>
        <v-card-actions>
          <v-btn color="success" @click.stop="login" >ورود</v-btn>
        </v-card-actions>
      </v-card>
    </v-flex>
  </v-layout>
</v-container>
</template>

<script>
import Axios from "axios"
import {RefineObject} from "../util/ObjectUtil";

export default {
  data: () => ({
    data : {
      username : null,
      password : null
    }
  }),
  methods: {
    login : function() {
      let reqBody = RefineObject(this.data);
    }
  }
}
</script>

<style>
i {
  margin-left: 10px;
}
</style>

