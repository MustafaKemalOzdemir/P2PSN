package com.example.dropboxtest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Constants {
   public static String ACCESS_TOKEN="tcZu55if91AAAAAAAAAArEXtixa3ooxC_NEsU6Tr9wPy60q8wX4rUj8iPqOYLbjf";
   public static String Main_File_Path="/Apps/P2PSN";
   public static String Friends_Folder_Path="/Apps/P2PSN/Friends";

   //JSON Templates

   public static String GetFriendTemplate(){
      JSONObject friendTemplate=new JSONObject();
      JSONArray messages=new JSONArray();
      //JSONObject message
      try {
         friendTemplate.put("messages",messages);
      } catch (JSONException e) {
         e.printStackTrace();
      }

      return friendTemplate.toString();
   }

}
