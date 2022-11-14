package vkApiTest;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;


 class vkApiTest {
   String token = "your token";
   Integer app_id = Integer.valueOf("183707364");
   Integer userid= Integer.valueOf(("83354443"));
   TransportClient transportClient = new HttpTransportClient() ;
   VkApiClient vk =new VkApiClient(transportClient) ;
   UserActor userActor=new UserActor(userid, token) ;
   @Test
   void vkApiUSER () throws ClientException {
     String str = vk.users().get(new UserActor(userid, token))
         .userIds(String.valueOf(userid))
         .executeAsString();
     System.out.println(str);
    Assert.notNull(str);


   }
   @Test
   void vkApiGroup() throws ClientException {
     String strn = vk.groups().isMember(userActor, String.valueOf(app_id))
         .userId(Integer.valueOf(userid))
         .extended(true)
         .executeAsString();
     System.out.println(strn);
     Assert.notNull(strn);


   }
}
