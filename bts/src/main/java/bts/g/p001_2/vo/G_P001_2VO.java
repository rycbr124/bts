package bts.g.p001_2.vo;

import org.springframework.stereotype.Component;

@Component("g_p001_2VO")
public class G_P001_2VO {
   private String member_id;
   private String content_id;
   public String getMember_id() {
      return member_id;
   }
   public void setMember_id(String member_id) {
      this.member_id = member_id;
   }
   public String getContent_id() {
      return content_id;
   }
   public void setContent_id(String content_id) {
      this.content_id = content_id;
   }

}