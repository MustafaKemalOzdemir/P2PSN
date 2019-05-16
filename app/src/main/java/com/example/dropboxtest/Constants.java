package com.example.dropboxtest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Constants {
   //public static String ACCESS_TOKEN="tcZu55if91AAAAAAAAAArEXtixa3ooxC_NEsU6Tr9wPy60q8wX4rUj8iPqOYLbjf";
   public static String ACCESS_TOKEN="";
   public static String Main_File_Path="/Apps/P2PSN";
   public static String Friends_Folder_Path="/Apps/P2PSN/Friends";
   public static String Personal_Folder_path="/Apps/P2PSN/Personal";
   public static String Personal_Friends_Folder_path="/Apps/P2PSN/Personal/Friends.txt";

   public static String User;

   public static ArrayList<Friend> arrayFriends=new ArrayList<>();




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
   public static String getPersonalTemplate(){
      JSONObject jsonObject=new JSONObject();
      JSONArray jsonArray=new JSONArray();
      try {
         jsonObject.put("friends",jsonArray);
      } catch (JSONException e) {
         e.printStackTrace();
      }
      return  jsonObject.toString();
   }

}
